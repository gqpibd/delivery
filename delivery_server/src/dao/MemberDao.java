package dao;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.imageio.ImageIO;

import communicator.SocketWriter;
import constants.Dml;
import db.DBClose;
import db.DBConnection;
import dto.ConsumerDto;
import dto.DelivererDto;
import dto.MemberDto;

//CREATE TABLE members( 
//	ID VARCHAR2(20) primary key, 
//	PW VARCHAR2(20) not null,
//	NAME varchar2(20) not null, 
//	ADDRESS varchar2(150), 
//	LOCATIONS varchar2(200),
//	DELIVERCOUNTS number(5), 
//	SCORE number(5,1), 
//	PHONE varchar2(20), 
//	AUTH number(2) not null, 
//);

public class MemberDao {
	private static final String PATH = "d:/images/";
	
	public void execute(int number, MemberDto dto, Socket sock) {
		switch (number) {
		case Dml.INSERT: // 회원가입
			insert(dto);
			receiveAndSaveImage(dto.getId(), sock);
			System.out.println(dto.getId() + "를 멤버 테이블에 추가하였습니다");
			break;
		case Dml.DELETE:
			break;
		case Dml.UPDATE:
			update(dto);
			break;
		case Dml.SELECT:
			select_login(dto, sock);
			break;
		case Dml.SELECT_IDCHEKCK:
			select_existingId(dto, sock);
			break;
		case Dml.SELECT_DELIVERER_INFO:
			select_delivererInfo(dto, sock);
			break;
		case Dml.UPDATE_SCORE:
			updateScore(dto);
			break;
		case Dml.SELECT_IMG:
			select_img(dto, sock);
			break;
		case Dml.UPDATE_IMG:
			update_img(dto,sock);
			break;
			
		}

	}

	private void update_img(MemberDto dto, Socket sock) {
		ObjectInputStream ois;
		try {
			ois = new ObjectInputStream(sock.getInputStream());
			BufferedImage im = ImageIO.read(ois);

			if (im == null) {
				System.out.println("이미지 파일을 받지 못했습니다");
				return;
			} else {
				System.out.println(PATH + dto.getId().replace(" ", "_") + ".png");
				ImageIO.write(im, "png", new File(PATH + dto.getId().replace(" ", "_") + ".png"));
				System.out.println("이미지 파일을 저장했습니다");
			}
		} catch (SocketException e) {
			System.out.println("커넥션 리셋됨");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	private void select_img(MemberDto dto, Socket sock) {
		String img = dto.getId().replaceAll(" ", "_") + ".png";
		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(sock.getOutputStream());
			File imgF = new File(PATH + img);
			BufferedImage im = null;
			if(imgF.exists()) {
				im = ImageIO.read(new File(PATH + img));
			}else {
				im = ImageIO.read(new File(PATH + "no_image.png"));
			}
			System.out.println(PATH + img);
			ImageIO.write(im, "png", oos);
			oos.flush();
			oos.close();
			System.out.println("이미지 보냄");
		} catch (IOException e) {
			e.printStackTrace();
			
		} 
		
	}

	private void select_delivererInfo(MemberDto dto, Socket sock) {
		String id = dto.getId();
		String sql = "SELECT LOCATIONS, DELIVERCOUNTS, SCORE " + " FROM MEMBERS " + " WHERE ID = ? ";

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		DelivererDto deliverer = null;
		try {
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			rs = psmt.executeQuery();
			if (rs.next()) {
				deliverer = new DelivererDto(dto, rs.getString(1));
				deliverer.setDeliveryCounts(rs.getInt(2));
				deliverer.setScore(rs.getDouble(3));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		SocketWriter.Write(sock, deliverer);
	}

	private void update(MemberDto dto) {
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		String sql = "";
		try {
			conn = DBConnection.getConnection();

			int auth = dto.getAuth();
			if (auth == MemberDto.CONSUMER) {
				ConsumerDto con = (ConsumerDto) dto;
				sql = " update members set address = ?, pw = ?, phone = ? where id = ? ";
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, con.getAddress());
				psmt.setString(2, con.getPw());
				psmt.setString(3, con.getPhone());
				psmt.setString(4, con.getId());
			} else if (auth == MemberDto.DELIVERER) {
				DelivererDto deli = (DelivererDto) dto;
				sql = " update members set locations = ?, pw = ?, phone = ? where id = ? ";
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, deli.getLocation());
				psmt.setString(2, deli.getPw());
				psmt.setString(3, deli.getPhone());
				psmt.setString(4, deli.getId());
			}

			psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}
	}

	public void updateScore(MemberDto dto) {

		String sql = " Update members set score = (select Avg(score) from orders where deliverer = ?) where id = ? ";

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(sql);

			psmt.setString(1, dto.getId());
			psmt.setString(2, dto.getId());

			rs = psmt.executeQuery();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}

	}

	private void select_existingId(MemberDto dto, Socket sock) {
		String id = dto.getId();
		String sql = "SELECT * " + " FROM MEMBERS " + " WHERE ID = ?";

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		boolean existingId = false;
		try {
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			rs = psmt.executeQuery();

			if (rs.next()) {
				existingId = true;
				System.out.println("존재하는 아이디 입니다.");
			} else {
				System.out.println("존재하지 않는 아이디 입니다.");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		SocketWriter.Write(sock, existingId);
	}

	private void insert(MemberDto dto) {
		String sql = null;

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {
			conn = DBConnection.getConnection();
			if (dto instanceof ConsumerDto) {
				ConsumerDto newMember = (ConsumerDto) dto;
				sql = " INSERT INTO MEMBERS (id, pw, name, phone, address, auth) VALUES (?,?,?,?,?,1) ";
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, newMember.getId());
				psmt.setString(2, newMember.getPw());
				psmt.setString(3, newMember.getName());
				psmt.setString(4, newMember.getPhone());
				psmt.setString(5, newMember.getAddress());
			} else {
				DelivererDto newMember = (DelivererDto) dto;
				sql = " INSERT INTO MEMBERS (id, pw, name, phone, locations, auth) VALUES (?,?,?,?,?,2) ";
				psmt = conn.prepareStatement(sql);
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, newMember.getId());
				psmt.setString(2, newMember.getPw());
				psmt.setString(3, newMember.getName());
				psmt.setString(4, newMember.getPhone());
				psmt.setString(5, newMember.getLocation());
			}

			rs = psmt.executeQuery();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}

	}

	public void select_login(MemberDto dto, Socket sock) {
		MemberDto loginUser = null;
		String id = dto.getId();
		String pw = dto.getPw();
		String sql = "SELECT name, phone, ADDRESS, LOCATIONS, auth " + " FROM MEMBERS " + " WHERE ID = '" + id
				+ "' AND PW = '" + pw + "' ";

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();

			if (rs.next()) {
				int auth = rs.getInt(5);
				if (auth == MemberDto.CONSUMER) {
					loginUser = new ConsumerDto(dto, rs.getString("address"));
				} else if (auth == MemberDto.DELIVERER) {
					loginUser = new DelivererDto(dto, rs.getString("locations"));
				}
				loginUser.setId(id);
				loginUser.setPw(pw);
				loginUser.setName(rs.getString(1));
				loginUser.setPhone(rs.getString(2));
				loginUser.setAuth(auth);
				System.out.println(loginUser.getId() + "님이 로그인 했습니다");
			} else {
				System.out.println("아이디 또는 패스워드가 틀렸습니다");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		SocketWriter.Write(sock, loginUser);
	}

	// 이미지 파일 받아서 저장
	public void receiveAndSaveImage(String name, Socket sock) {
		ObjectInputStream ois;
		try {
			ois = new ObjectInputStream(sock.getInputStream());
			BufferedImage im = ImageIO.read(ois);

			if (im == null) {
				System.out.println("이미지 파일을 받지 못했습니다");
				return;
			} else {
				System.out.println(PATH + name.replace(" ", "_") + ".png");
				ImageIO.write(im, "png", new File(PATH + name.replace(" ", "_") + ".png"));
				System.out.println("이미지 파일을 저장했습니다");
			}
		} catch (SocketException e) {
			System.out.println("커넥션 리셋됨");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

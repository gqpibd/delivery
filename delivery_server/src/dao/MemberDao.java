package dao;

import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import communicator.SocketWriter;
import constants.Dml;
import db.DBClose;
import db.DBConnection;
import dto.ConsumerDto;
import dto.DelivererDto;
import dto.MemberDto;

public class MemberDao {

	public void execute(int number, MemberDto dto, Socket sock) {
		switch (number) {
		case Dml.INSERT: // 회원가입
			insert(dto);
			System.out.println(dto.getId() + "를 멤버 테이블에 추가하였습니다");
			break;
		case Dml.DELETE:
			break;
		case Dml.UPDATE:
			break;
		case Dml.SELECT:
			select_login(dto, sock);			
			break;
		case Dml.SELECT_IDCHEKCK:
			select_existingId(dto,sock);
			break;
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
	
	/*CREATE TABLE members(
		    ID VARCHAR2(20) primary key,
		    PW VARCHAR2(20) not null,
		    NAME varchar2(20) not null,
		    ADDRESS varchar2(150),
		    LOCATIONS varchar2(200),
		    DELIVERCOUNTS number(5),
		    SCORE number(5,1),
		    PHONE varchar2(20),
		    AUTH number(2) not null,
		);*/

	private void insert(MemberDto dto) {
		String sql = null;
		

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {
			conn = DBConnection.getConnection();
			if(dto instanceof ConsumerDto) {
				ConsumerDto newMember = (ConsumerDto) dto;
				sql = " INSERT INTO MEMBERS (id, pw, name, phone, address, auth) VALUES (?,?,?,?,?,1) ";
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, newMember.getId());
				psmt.setString(2, newMember.getPw());
				psmt.setString(3, newMember.getName());
				psmt.setString(4, newMember.getPhone());
				psmt.setString(5, newMember.getAddress());	
			}else {
				DelivererDto newMember = (DelivererDto) dto;
				sql = " INSERT INTO MEMBERS (id, pw, name, phone, locations, auth) VALUES (?,?,?,?,?,2) ";
				psmt = conn.prepareStatement(sql);
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, newMember.getId());
				psmt.setString(2, newMember.getPw());
				psmt.setString(3, newMember.getName());
				psmt.setString(4, newMember.getPhone());
				psmt.setString(5, newMember.getLocations()[0]);	
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
		String sql = "SELECT NAME " + " FROM MEMBERS " + " WHERE ID = '" + id
				+ "' AND PW = '" + pw + "' ";

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();

			if (rs.next()) {
				loginUser = new MemberDto();
				loginUser.setId(id);
				loginUser.setPw(pw);
				
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

}

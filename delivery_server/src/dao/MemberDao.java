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
		}

	}

	private void insert(MemberDto dto) {
		// TODO Auto-generated method stub

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

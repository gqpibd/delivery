package dao;

import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import communicator.SocketWriter;
import constants.Dml;
import db.DBClose;
import db.DBConnection;

public class SelectionsDao { // 특수한 Select를 모아놓았다.
	
	public void execute(int number, Object dto, Socket sock) {

		switch (number) {
		case Dml.SELECT_ADDRESS: // 주소 검색
			selectAddress(dto.toString(), sock);
			System.out.println("주소 정보를 불러왔습니다");
			break;
		}
	}

	// 주소 정보
	public void selectAddress(Object obj, Socket sock) {
		String sql = "SELECT DISTINCT SIDO, SIGUNGU, LOAD, EUBMEONDONG" + " FROM LOADNAME_ADD " + " WHERE LOAD LIKE '%"
				+ obj.toString() + "%' " + " ORDER BY LOAD ";

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		ArrayList<String> list = new ArrayList<>();
		try {

			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();

			while (rs.next()) {
				if (rs.getString(4) == null) {
					//list.add(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3));
				} else {
					list.add(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3) + " (" + rs.getString(4)
							+ ")");
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);

		}

		SocketWriter.Write(sock, list);

	}

}

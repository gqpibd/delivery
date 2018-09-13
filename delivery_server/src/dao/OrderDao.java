package dao;

import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import communicator.SocketWriter;
import constants.Dml;
import db.DBClose;
import db.DBConnection;
import dto.OrderBBsDto;
import dto.OrderDto;

public class OrderDao {
	public static final int SELECT_POST = 4;
	
	public void execute(int number, OrderDto dto, Socket sock) {
		switch (number) {
		case Dml.INSERT:
			break;
		case Dml.DELETE:
			break;
		case Dml.UPDATE:
			break;
		case Dml.SELECT:
			select_posts(sock);
			break;
		case SELECT_POST:
			select_post(sock);
			break;
			
		}

	}
	/*
	CREATE TABLE ORDERS(
			REQNUMBER number(5) primary key,
			REQTYPE varchar2(20) not null,
			WRITER varchar2(20) not null,
			DELIVERER varchar2(20),
			PRICE number(8) not null,
			LOCATION varchar2(50) not null, -- OO��
			CONTENTS varchar2(1000) not null,
			APPLICANTS varchar2(400), -- ������ ��� (ID)
			STATE varchar2(20), -- ������� (��û,������,�Ϸ�)
			SCORE number(2),
			REVIEW varchar2(1000), 
			CONSTRAINT FK_WRITER FOREIGN KEY(WRITER) REFERENCES members(ID),
			CONSTRAINT FK_DELIVERER FOREIGN KEY(DELIVERER) REFERENCES members(ID)
		);
	*/
	
	public void select_posts(Socket sock) {
		List<OrderBBsDto> posts = new ArrayList<>();
		String sql = " SELECT REQNUMBER, STATE, TITLE, LOCATION, WRITER, Order_date " + " FROM ORDERS ";

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();

			while (rs.next()) {
		
				OrderBBsDto post = new OrderBBsDto();
				post.setReqNum(rs.getInt(1));
				post.setStatus(rs.getString(2));
				post.setTitle(rs.getString(3));
				post.setLocation(rs.getString(4));
				post.setConsumerId(rs.getString(5));
				post.setDate(rs.getString(6));
				System.out.println(post.toString());
				posts.add(post);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		SocketWriter.Write(sock, posts);
	}
	
	public void select_post(Socket sock) {
		OrderBBsDto post = new OrderBBsDto();
		String sql = " SELECT STATE, TITLE, LOCATION, WRITER, Order_date, price, applicants, contents " + " FROM ORDERS ";

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();

			while (rs.next()) {

				post.setStatus(rs.getString(1));
				post.setTitle(rs.getString(2));
				post.setLocation(rs.getString(3));
				post.setConsumerId(rs.getString(4));
				post.setDate(rs.getString(5));
				post.setPrice(rs.getInt(6));
				String applicants = rs.getString(7);
				if(applicants !=null) {
					post.setApplicants(applicants.split(","));
				}
				post.setContents(rs.getString(8));
				System.out.println(post.toString());
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		SocketWriter.Write(sock, post);
	}
}

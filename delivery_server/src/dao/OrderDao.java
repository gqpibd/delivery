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
	
	public void execute(int number, OrderDto dto, Socket sock) {
		switch (number) {
		case Dml.INSERT:
			insert_post(dto);
			break;
		case Dml.DELETE:
			break;
		case Dml.UPDATE:
			update_post((OrderBBsDto)dto);
			break;
		case Dml.SELECT:
			select_posts(sock);
			break;
		case Dml.SELECT_POST:
			select_post(sock, (OrderBBsDto)dto);
			break;
		case Dml.SELECT_POSTCONENT:
			select_postcontent(sock, (OrderBBsDto)dto);
			break;
		}

	}
	private void update_post(OrderBBsDto dto) {
		OrderBBsDto post = (OrderBBsDto) dto;
		String sql = " update orders set title = ?, location = ?, price = ?, contents = ? where reqnumber = ? ";

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		System.out.println("update : " + dto);
		try {
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(sql);
			
			psmt.setString(1, post.getTitle());
			psmt.setString(2, post.getLocation());
			psmt.setInt(3, post.getPrice());
			psmt.setString(4, post.getContents());
			psmt.setInt(5, post.getReqNum());			
			
			psmt.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		
	}
	
	private void insert_post(OrderDto dto) {
		OrderBBsDto post = (OrderBBsDto) dto;
		String sql = " insert into orders values( bbsSeq.nextval,?,null,?,?,?,null,'요청중',null,null,?,sysdate )";

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(sql);
			
			psmt.setString(1, post.getConsumerId());
			psmt.setInt(2, post.getPrice());
			psmt.setString(3, post.getLocation());
			psmt.setString(4, post.getContents());
			psmt.setString(5, post.getTitle());			
			
			psmt.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		
	}
	/*
	CREATE TABLE ORDERS(
			REQNUMBER number(5) primary key,
			WRITER varchar2(20) not null,
			DELIVERER varchar2(20),
			PRICE number(8) not null,
			LOCATION varchar2(50) not null, -- OO��
			CONTENTS varchar2(1000) not null,
			APPLICANTS varchar2(400), -- ������ ��� (ID)
			STATE varchar2(20), -- ������� (��û,������,�Ϸ�)
			SCORE number(2),
			REVIEW varchar2(1000), 
			TITLE varchar2(40),
			ORDER_DATE DATE,
			CONSTRAINT FK_WRITER FOREIGN KEY(WRITER) REFERENCES members(ID),
			CONSTRAINT FK_DELIVERER FOREIGN KEY(DELIVERER) REFERENCES members(ID)
		);
	*/
	
	public void select_posts(Socket sock) {
		List<OrderBBsDto> posts = new ArrayList<>();
		String sql = " SELECT REQNUMBER, STATE, TITLE, LOCATION, WRITER, Order_date " + " FROM ORDERS ORDER BY REQNUMBER DESC ";

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
	
	public void select_post(Socket sock, OrderBBsDto post) {
		
		String sql = " SELECT STATE, TITLE, LOCATION, WRITER, Order_date, price, applicants, contents, REQNUMBER " + " FROM ORDERS WHERE REQNUMBER = ?";

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, post.getReqNum());
			rs = psmt.executeQuery();

			if (rs.next()) {
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
				post.setReqNum(rs.getInt(9));
				System.out.println(post.toString());
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		SocketWriter.Write(sock, post);
	}
	

	
	public void select_postcontent(Socket sock, OrderBBsDto dto) {
		
		String sql = " SELECT STATE, TITLE, LOCATION, WRITER, Order_date, price, applicants, contents, reqnumber " + " FROM ORDERS WHERE CONTENTS LIKE '%" + 
				 dto.getContents() + "%'";

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		List<OrderBBsDto> list = new ArrayList<>();

		try {
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(sql);			
			rs = psmt.executeQuery();			

			while (rs.next()) {
				OrderBBsDto post = new OrderBBsDto();
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
				post.setReqNum(rs.getInt(9));
				System.out.println(post.toString());
				list.add(post);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		SocketWriter.Write(sock, list);
	}
}

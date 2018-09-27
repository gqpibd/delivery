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
import dto.OrderDto;
import dto.OrderDto;

public class OrderDao {

	public void execute(int number, OrderDto dto, Socket sock) {
		switch (number) {
		case Dml.INSERT:
			insert_post(dto);
			break;
		case Dml.DELETE:
			delete_post(dto);
			break;
		case Dml.UPDATE:
			update_post(dto);
			break;
		case Dml.SELECT:
			select_posts(sock);
			break;
		case Dml.SELECT_POST:
			select_post(sock, dto);
			break;
		case Dml.SELECT_POSTCONENT:
			select_postcontent(sock, dto);
			break;
		case Dml.SELECT_DELIVER_LIST:
			select_deliverList(sock, dto);
			break;
		case Dml.SELECT_MYORDER:
			select_MyOder(sock, dto);
		}

	}

	private void select_deliverList(Socket sock, OrderDto dto) {
		// String sql = " SELECT REQNUMBER, STATE, TITLE, LOCATION, Order_date " + "
		// FROM ORDERS WHERE NVL(ISDEL,0) = 0 AND DELIVERER=? ORDER BY REQNUMBER DESC";
		List<OrderDto> list = new ArrayList<>();
		String sql = " SELECT REQNUMBER, STATE, TITLE, LOCATION, Order_date, WRITER, DELIVERER "
				+ " FROM ORDERS WHERE (NVL(ISDEL,0) = 0 AND STATE = '요청중' AND APPLICANTS LIKE '%" + dto.getDelivererId()
				+ "%') " + "OR DELIVERER=? ORDER BY REQNUMBER DESC";
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		System.out.println("select : " + dto);
		try {
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, dto.getDelivererId());
			rs = psmt.executeQuery();

			while (rs.next()) {
				OrderDto post = new OrderDto();
				post.setReqNum(rs.getInt(1));
				post.setStatus(rs.getString(2));
				post.setTitle(rs.getString(3));
				post.setLocation(rs.getString(4));
				post.setDate(rs.getString(5));
				post.setConsumerId(rs.getString(6));
				post.setDelivererId(rs.getString(7));
				list.add(post);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		SocketWriter.Write(sock, list);
	}

	private void select_MyOder(Socket sock, OrderDto dto) {
		String id = dto.getConsumerId();
		String sql = " SELECT REQNUMBER, STATE, TITLE, WRITER, Order_date "
				+ " FROM ORDERS WHERE writer = ? and NVL(isdel,0) != 1 ";
		List<OrderDto> list = new ArrayList<>();

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			rs = psmt.executeQuery();

			while (rs.next()) {

				OrderDto post = new OrderDto();
				post.setReqNum(rs.getInt(1));
				post.setStatus(rs.getString(2));
				post.setTitle(rs.getString(3));
				post.setConsumerId(rs.getString(4));
				post.setDate(rs.getString(5));

				list.add(post);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		SocketWriter.Write(sock, list);

	}

	private void delete_post(OrderDto dto) {
		OrderDto post = (OrderDto) dto;
		String sql = " update orders set isdel = 1 where reqnumber = ? ";

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		System.out.println("update : " + dto);
		try {
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(sql);

			psmt.setInt(1, post.getReqNum());

			psmt.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}

	}

	private void update_post(OrderDto dto) {
		OrderDto post = (OrderDto) dto;
		String sql = " update orders set title = ?, location = ?, price = ?, contents = ?, applicants = ?, address = ?, state = ?, deliverer = ?, "
				+ " score = ? where reqnumber = ? ";

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(sql);

			psmt.setString(1, post.getTitle());
			psmt.setString(2, post.getLocation());
			psmt.setInt(3, post.getPrice());
			psmt.setString(4, post.getContents());
			if (post.getApplicants() != null) {
				psmt.setString(5, post.getApplicants());
			} else {
				psmt.setString(5, "");
			}
			psmt.setString(6, post.getAddress());
			psmt.setString(7, post.getStatus());
			psmt.setString(8, post.getDelivererId());
			psmt.setInt(9, post.getScore());			
			psmt.setInt(10, post.getReqNum());

			psmt.executeQuery();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}

	}

//	CREATE TABLE ORDERS(
//			REQNUMBER number(5) primary key,
//			WRITER varchar2(20) not null,
//			DELIVERER varchar2(20),
//			PRICE number(8) not null,
//			LOCATION varchar2(50) not null, 
//			CONTENTS varchar2(1000) not null,
//			APPLICANTS varchar2(400), 
//			STATE varchar2(20), 
//			SCORE number(2),
//			REVIEW varchar2(1000), 
//			ORDER_DATE date,
//			ISDEL NUMBER(1), -- 1이면 삭제, 0이면 있음
//			ADDRESS VARCHAR2(150),	
//			CONSTRAINT FK_WRITER FOREIGN KEY(WRITER) REFERENCES members(ID),
//			CONSTRAINT FK_DELIVERER FOREIGN KEY(DELIVERER) REFERENCES members(ID)
//		);
	private void insert_post(OrderDto dto) {
		OrderDto post = (OrderDto) dto;
		String sql = " insert into orders values( (SELECT NVL(MAX(REQNUMBER), 0) + 1 FROM orders) ,?,null,?,?,?,null,'요청중',null,null,?,sysdate,0,? )";

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
			psmt.setString(6, post.getAddress());

			psmt.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}

	}

	public void select_posts(Socket sock) {
		List<OrderDto> posts = new ArrayList<>();
		String sql = " SELECT REQNUMBER, STATE, TITLE, LOCATION, WRITER, Order_date "
				+ " FROM ORDERS WHERE NVL(ISDEL,0) = 0 ORDER BY REQNUMBER DESC ";

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();

			while (rs.next()) {
				OrderDto post = new OrderDto();
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

	public void select_post(Socket sock, OrderDto post) {

		String sql = " SELECT STATE, TITLE, LOCATION, WRITER, Order_date, price, applicants, contents, address, DELIVERER "
				+ " FROM ORDERS WHERE REQNUMBER = ?";

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
				if (applicants != null) {
					post.setApplicants(applicants);
				}
				post.setContents(rs.getString(8));
				// post.setReqNum(post.getReqNum());
				post.setAddress(rs.getString(9));
				post.setDelivererId(rs.getString(10));
				System.out.println(post.toString());
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		SocketWriter.Write(sock, post);
	}

	public void select_postcontent(Socket sock, OrderDto dto) {

		String sql = " SELECT STATE, TITLE, LOCATION, WRITER, Order_date, price, applicants, contents, reqnumber "
				+ " FROM ORDERS WHERE CONTENTS LIKE '%" + dto.getContents() + "%'";

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		List<OrderDto> list = new ArrayList<>();

		try {
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();

			while (rs.next()) {
				OrderDto post = new OrderDto();
				post.setStatus(rs.getString(1));
				post.setTitle(rs.getString(2));
				post.setLocation(rs.getString(3));
				post.setConsumerId(rs.getString(4));
				post.setDate(rs.getString(5));
				post.setPrice(rs.getInt(6));
				String applicants = rs.getString(7);
				if (applicants != null) {
					post.setApplicants(applicants);
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

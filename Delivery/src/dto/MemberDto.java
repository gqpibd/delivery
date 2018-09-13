package dto;

import java.io.Serializable;

public class MemberDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3348164012239018625L;

	public static final int MANAGER = 0;
	public static final int CONSUMER = 1;
	public static final int DELIVERER = 2;

	private String id;
	private String pw;
	private String name;
	private String phone;
	private int auth;

	public MemberDto() {
	};

	public MemberDto(MemberDto member) {
		this.id = member.id;
		this.pw = member.pw;
		this.name = member.name;
		this.phone = member.phone;
		this.auth = member.auth;
	}

	public MemberDto(String id, String pw, String name, String phone, int auth) {
		super();
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.phone = phone;
		this.auth = auth;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getName() {
		return name;
	}

	public int getAuth() {
		return auth;
	}

	@Override
	public String toString() {
		return "MemberDto [id=" + id + ", pw=" + pw + ", name=" + name + ", phone=" + phone + ", auth=" + auth + "]";
	}

}

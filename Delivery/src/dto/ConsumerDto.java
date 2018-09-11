package dto;

import java.io.Serializable;

public class ConsumerDto extends MemberDto implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 190639796950258573L;
	private int requestCounts;
	private String address;	
	
	public ConsumerDto(String id, String pw, String name, String phone, int requestCounts, String address) {
		super(id, pw, name, phone, CONSUMER);
		this.requestCounts = requestCounts;
		this.address = address;
	}
	
	public ConsumerDto(MemberDto member, int requestCounts, String address) {
		super(member);
		this.requestCounts = requestCounts;
		this.address = address;
	}
	
	public ConsumerDto(String id, String pw, String name, String phone, String address) {
		this(id, pw, name, phone, 0, address);		
	}
	
	public ConsumerDto(MemberDto member, String address) {
		this(member, 0, address);
	}

	public int getRequestCounts() {
		return requestCounts;
	}

	public void setRequestCounts(int requestCounts) {
		this.requestCounts = requestCounts;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "ConsumerDto [requestCounts=" + requestCounts + ", address=" + address + "]";
	}
	
	
}

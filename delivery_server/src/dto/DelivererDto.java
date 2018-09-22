package dto;

import java.io.Serializable;
import java.util.Arrays;

public class DelivererDto extends MemberDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2831883632781418013L;
	
	private int deliveryCounts;
	private double score;
	private String location;
	
	public DelivererDto(String id, String pw, String name, String phone, int deliveryCounts, double score, String location) {
		super(id, pw, name, phone, DELIVERER);
		this.deliveryCounts = deliveryCounts;
		this.score = score;
		this.location = location;		
	}
	
	public DelivererDto(MemberDto member, int deliveryCounts, double score, String location) {
		super(member);
		this.deliveryCounts = deliveryCounts;
		this.score = score;
		this.location = location;
	}
	
	public DelivererDto(String id, String pw, String name, String phone, String location) {
		this(id, pw, name, phone, 0, 0, location);		
	}
	
	public DelivererDto(MemberDto member, String location) {
		this(member, 0, 0, location);
	}

	public int getDeliveryCounts() {
		return deliveryCounts;
	}

	public void setDeliveryCounts(int deliveryCounts) {
		this.deliveryCounts = deliveryCounts;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "DelivererDto [deliveryCounts=" + deliveryCounts + ", score=" + score + ", location="
				+ location + "]";
	}
	
	

}

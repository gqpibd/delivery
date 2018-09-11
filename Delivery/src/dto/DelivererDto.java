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
	private String[] locations;
	
	public DelivererDto(String id, String pw, String name, String phone, int deliveryCounts, double score, String[] locations) {
		super(id, pw, name, phone, DELIVERER);
		this.deliveryCounts = deliveryCounts;
		this.score = score;
		this.locations = locations;		
	}
	
	public DelivererDto(MemberDto member, int deliveryCounts, double score, String[] locations) {
		super(member);
		this.deliveryCounts = deliveryCounts;
		this.score = score;
		this.locations = locations;
	}
	
	public DelivererDto(String id, String pw, String name, String phone, String[] locations) {
		this(id, pw, name, phone, 0, 0, locations);		
	}
	
	public DelivererDto(MemberDto member, String[] locations) {
		this(member, 0, 0, locations);
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

	public String[] getLocations() {
		return locations;
	}

	public void setLocations(String[] locations) {
		this.locations = locations;
	}

	@Override
	public String toString() {
		return "DelivererDto [deliveryCounts=" + deliveryCounts + ", score=" + score + ", locations="
				+ Arrays.toString(locations) + "]";
	}
	
	

}

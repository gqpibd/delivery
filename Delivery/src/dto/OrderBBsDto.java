package dto;

import java.io.Serializable;
import java.util.Arrays;

public class OrderBBsDto extends OrderDto implements Serializable{	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7372224262521338769L;
	//public static final int WAITING = 1; --> 
	//public static final int ONGOING = 2;
	//public static final int DONE = 3;
	//public static final int CANCELED = 4;
	
	private String contents;
	private int price;
	private String applicants;
	private String status;
	
	
	public OrderBBsDto() {	}
		
	public OrderBBsDto(int reqNum, String title, String type, String location, String consumerId, String delivererId,
			String date, String contents, int price, String applicants, String status) {
		super(reqNum, title, type, location, consumerId, delivererId, date);
		this.contents = contents;
		this.price = price;
		this.applicants = applicants;
		this.status = status;
	}
	
	public OrderBBsDto(OrderDto oDto, String contents, int price, String applicants, String status) {
		super(oDto);
		this.contents = contents;
		this.price = price;
		this.applicants = applicants;
		this.status = status;
	}
	
	public OrderBBsDto(OrderDto oDto, String contents, int price) { // 새 글이 올라온 경우
		this(oDto,contents,price,null,"요청");		
	}
	
	public OrderBBsDto(String title, String type, String location, String consumerId, String date, String contents, int price) {
		super(title, type, location, consumerId, date);
		this.contents = contents;
		this.price = price;
	}
	
	public OrderBBsDto(String title, String type, String location, String consumerId, String contents, int price) {
		super(title, type, location, consumerId);
		this.contents = contents;
		this.price = price;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getApplicants() {
		return applicants;
	}

	public void setApplicants(String applicants) {
		this.applicants = applicants;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	

	@Override
	public String toString() {
		return super.toString() + "OrderBBsDto [contents=" + contents + ", price=" + price + ", applicants=" + applicants
				+ ", status=" + status + "]";
	}
	
	

}
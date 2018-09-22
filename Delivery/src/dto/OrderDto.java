package dto;

import java.io.Serializable;

public class OrderDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1660530417086536129L;

	private int reqNum;
	private String title;
	private String type;
	private String location;
	private String consumerId;
	private String delivererId;
	private String date;
	private String contents;
	private int price;
	private String applicants;
	private String status;	
	private int score;
	private String review;
	private String address;

	

	public OrderDto() {
	}

	public int getReqNum() {
		return reqNum;
	}

	public void setReqNum(int reqNum) {
		this.reqNum = reqNum;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getConsumerId() {
		return consumerId;
	}

	public void setConsumerId(String consumerId) {
		this.consumerId = consumerId;
	}

	public String getDelivererId() {
		return delivererId;
	}

	public void setDelivererId(String delivererId) {
		this.delivererId = delivererId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
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

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "OrderDto [reqNum=" + reqNum + ", title=" + title + ", location=" + location + ", consumerId="
				+ consumerId + ", delivererId=" + delivererId + ", date=" + date + ", contents=" + contents + ", price="
				+ price + ", applicants=" + applicants + ", status=" + status + ", score=" + score + ", review="
				+ review + ", address=" + address + "]";
	}
}

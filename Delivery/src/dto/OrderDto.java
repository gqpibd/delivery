package dto;

import java.io.Serializable;

public class OrderDto implements Serializable{

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
	
	public OrderDto() {
	}
	
	public OrderDto(String title, String type, String location, String consumerId ){
		this.title = title;
		this.type = type;
		this.location = location;
		this.consumerId = consumerId;
		
	}	
	
	public OrderDto(String title, String type, String location, String consumerId, String date){
		this.title = title;
		this.type = type;
		this.location = location;
		this.consumerId = consumerId;
		this.date = date;
	}	
	
	public OrderDto(int reqNum, String title, String type, String location, String consumerId, String delivererId,
			String date) {
		this.reqNum = reqNum;
		this.title = title;
		this.type = type;
		this.location = location;
		this.consumerId = consumerId;
		this.delivererId = delivererId;
		this.date = date;
	}
	
	public OrderDto(OrderDto oDto) {
		this.reqNum = oDto.reqNum;
		this.title =  oDto.title;
		this.type =  oDto.type;
		this.location =  oDto.location;
		this.consumerId =  oDto.consumerId;
		this.delivererId =  oDto.delivererId;
		this.date = oDto.date;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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

	@Override
	public String toString() {
		return "RequestDto [reqNum=" + reqNum + ", title=" + title + ", type=" + type + ", location=" + location
				+ ", consumerId=" + consumerId + ", delivererId=" + delivererId + ", date=" + date + "]";
	}
	
	
	
}


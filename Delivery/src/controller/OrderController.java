package controller;

import dto.OrderBBsDto;
import service.OrderService;

public class OrderController {

	private OrderService OS = new OrderService();
	
	public OrderController() {}
	
	public Object getPostlist() {		
		return OS.getPostlist();
	}
	
	public OrderBBsDto getPost() {
		return OS.getPost();
	}
	
	
}

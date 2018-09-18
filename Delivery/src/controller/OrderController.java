package controller;

import dto.OrderBBsDto;
import service.OrderService;
import singleton.Singleton;
import view.OrderBBsView;
import view.OrderWriteView;

public class OrderController {

	private OrderService OS = new OrderService();
	
	public OrderController() {}
	
	public Object getPostlist() {		
		return OS.getPostlist();
	}
	
	public OrderBBsDto getPost() {
		return OS.getPost();
	}

	public void orderWriteView() {
		Singleton.getInstance().hideMainView();
		new OrderWriteView();
		
	}

	public void addPost(OrderBBsDto od) {
		OS.addPost(od);
		
	}
	
	
}

package controller;

import java.util.List;

import javax.swing.JFrame;

import dto.DelivererDto;
import dto.OrderDto;
import service.OrderService;
import singleton.Singleton;
import view.OrderWriteView;
import view.PostView;
import view.selectDeliver;

public class OrderController {

	private OrderService OS = new OrderService();
	
	public OrderController() {}
	
	public List<OrderDto> getPostlist() {		
		return OS.getPostlist();
	}
	
	public void postView(int postNum) {
		OrderDto dto = OS.getPost(postNum);
		new PostView(dto);
	}

	public void orderWriteView() {
		Singleton.getInstance().hideMainView();
		new OrderWriteView();
		
	}

	public void addPost(OrderDto od) {
		OS.addPost(od);		
	}
	
	public void backToMain(JFrame currentView) {
		currentView.dispose();
		Singleton.getInstance().showMainView();
	}

	public List<OrderDto> selectList(String option, String input) {
		
		return OS.selectList(option, input);
		
	}

	public void updatePostView(OrderDto dto) {
		new OrderWriteView(dto);
		
	}
	public void updatePost(OrderDto dto) {
		OS.updatePost(dto);
	}

	public List<OrderDto> getDeliverList(String id) {		
		return OS.getDeliverList(id);
	}

	public List<OrderDto> getOderList(){ 		
		return OS.getOderList();
	}
	
}

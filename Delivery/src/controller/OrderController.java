package controller;

import java.util.List;

import javax.swing.JFrame;

import dto.OrderBBsDto;
import service.OrderService;
import singleton.Singleton;
import view.OrderBBsView;
import view.OrderWriteView;
import view.PostView;

public class OrderController {

	private OrderService OS = new OrderService();
	
	public OrderController() {}
	
	public List<OrderBBsDto> getPostlist() {		
		return OS.getPostlist();
	}
	
	public void postView(int postNum) {
		OrderBBsDto dto = OS.getPost(postNum);
		new PostView(dto);
	}

	public void orderWriteView() {
		Singleton.getInstance().hideMainView();
		new OrderWriteView();
		
	}

	public void addPost(OrderBBsDto od) {
		OS.addPost(od);		
	}
	
	public void backToMain(JFrame currentView) {
		currentView.dispose();
		Singleton.getInstance().showMainView();
	}

	public List<OrderBBsDto> selectList(String option, String input) {
		
		return OS.selectList(option, input);
		
	}

	public void updatePostView(OrderBBsDto dto) {
		new OrderWriteView(dto);
		
	}
	public void updatePost(OrderBBsDto dto) {
		OS.updatePost(dto);
	}
	
	
}

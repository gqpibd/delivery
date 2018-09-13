package service;

import java.util.List;

import communicator.Communicator;
import dto.OrderBBsDto;
import dto.OrderDto;
import singleton.Singleton;

public class OrderService {
	
	public static final int SELECT_POST = 4;

	public Object getPostlist() {
		Singleton s = Singleton.getInstance();
		s.getComm().SendMessage(Communicator.SELECT, new OrderDto());
		Object o = s.getComm().receiveObject();
		return o;
	}
	
	public OrderBBsDto getPost() {
		Singleton s = Singleton.getInstance();
		s.getComm().SendMessage(SELECT_POST, new OrderDto());
		OrderBBsDto o = (OrderBBsDto)s.getComm().receiveObject();
		System.out.println(o);
		return o;
	}

}

package service;

import communicator.Communicator;
import dto.OrderBBsDto;
import dto.OrderDto;
import singleton.Singleton;

public class OrderService {

	public Object getPostlist() {
		Singleton s = Singleton.getInstance();
		s.getComm().SendMessage(Communicator.SELECT, new OrderDto());
		Object o = s.getComm().receiveObject();
		return o;
	}
	
	public OrderBBsDto getPost() {
		Singleton s = Singleton.getInstance();
		s.getComm().SendMessage(Communicator.SELECT_POST, new OrderDto());
		OrderBBsDto o = (OrderBBsDto)s.getComm().receiveObject();
		System.out.println(o);
		return o;
	}

}

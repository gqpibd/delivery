package service;

import java.util.ArrayList;
import java.util.List;

import communicator.Communicator;
import dto.MemberDto;
import dto.OrderBBsDto;
import dto.OrderDto;
import singleton.Singleton;

public class OrderService {

	private List<OrderBBsDto> list;

	public List<OrderBBsDto> getPostlist() {
		Singleton s = Singleton.getInstance();
		s.getComm().SendMessage(Communicator.SELECT, new OrderDto());
		list = (List<OrderBBsDto>) s.getComm().receiveObject();
		return list;
	}

	public OrderBBsDto getPost(int postNum) {
		Singleton s = Singleton.getInstance();
		OrderBBsDto dto = new OrderBBsDto();
		dto.setReqNum(postNum);
		s.getComm().SendMessage(Communicator.SELECT_POST, dto);
		OrderBBsDto o = (OrderBBsDto) s.getComm().receiveObject();
		System.out.println(o);
		return o;
	}
	
	public List<OrderBBsDto> getPostContains(String content) {
		Singleton s = Singleton.getInstance();
		OrderBBsDto dto = new OrderBBsDto();
		dto.setContents(content);
		s.getComm().SendMessage(Communicator.SELECT_POSTCONENT, dto);
		List<OrderBBsDto> list = (List<OrderBBsDto>) s.getComm().receiveObject();
		System.out.println(list);
		return list;
	}

	public void addPost(OrderBBsDto od) {

		Singleton s = Singleton.getInstance();
		s.getComm().SendMessage(Communicator.INSERT, od);

	}

	public List<OrderBBsDto> selectList(String option, String input) {
		
		List<OrderBBsDto> sel_list = new ArrayList<>();
		
		if(option.equals("작성자")) {
			for (int i = 0; i < list.size(); i++) {
				if(list.get(i).getConsumerId().contains(input)) {
					sel_list.add(list.get(i));
				}
			}
		}
		else if(option.equals("내용")) {
			sel_list = getPostContains(input);			
		}
		else if(option.equals("제목")) {
			for (int i = 0; i < list.size(); i++) {
				if(list.get(i).getTitle().contains(input)) {
					sel_list.add(list.get(i));
				
				}
			}
		}
		return sel_list;
	}

	public void updatePost(OrderBBsDto dto) {
		Singleton.getInstance().getComm().SendMessage(Communicator.UPDATE, dto);		
	}
	
	public List<OrderBBsDto> getOderList() {
		OrderBBsDto dto = new OrderBBsDto();
		dto.setConsumerId(Singleton.getInstance().getMemCtrl().getCurrentUser().getId());
		Singleton.getInstance().getComm().SendMessage(Communicator.SELECT_MYODER, dto);
		List<OrderBBsDto> list = (List<OrderBBsDto>)Singleton.getInstance().getComm().receiveObject();
		
		return list;
	}

	public List<OrderBBsDto> getDeliverList(String id) {
		OrderBBsDto dto = new OrderBBsDto();
		dto.setDelivererId(id);
		Communicator comm = Singleton.getInstance().getComm();
		comm.SendMessage(Communicator.SELECT_DELIVER_LIST, dto);
		return null;
	}

}

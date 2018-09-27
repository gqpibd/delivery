package service;

import java.util.ArrayList;
import java.util.List;

import communicator.Communicator;
import dto.MemberDto;
import dto.OrderDto;
import singleton.Singleton;

public class OrderService {

	private List<OrderDto> list;

	public List<OrderDto> getPostlist() {
		Singleton s = Singleton.getInstance();
		s.getComm().SendMessage(Communicator.SELECT, new OrderDto());
		list = (List<OrderDto>) s.getComm().receiveObject();
		return list;
	}

	public OrderDto getPost(int postNum) {
		Singleton s = Singleton.getInstance();
		OrderDto dto = new OrderDto();
		dto.setReqNum(postNum);
		s.getComm().SendMessage(Communicator.SELECT_POST, dto);
		OrderDto o = (OrderDto) s.getComm().receiveObject();
		System.out.println(o);
		return o;
	}
	
	public List<OrderDto> getPostContains(String content) {
		Singleton s = Singleton.getInstance();
		OrderDto dto = new OrderDto();
		dto.setContents(content);
		s.getComm().SendMessage(Communicator.SELECT_POSTCONENT, dto);
		List<OrderDto> resultList = (List<OrderDto>) s.getComm().receiveObject();
		System.out.println(resultList);
		return resultList;
	}

	public void addPost(OrderDto od) {

		Singleton s = Singleton.getInstance();
		s.getComm().SendMessage(Communicator.INSERT, od);

	}

	public List<OrderDto> selectList(String option, String input) {		
		List<OrderDto> sel_list = new ArrayList<>();
		if(input.equals("")) {
			sel_list = list;
		}else if(option.equals("작성자")) {
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

	public void updatePost(OrderDto dto) {
		Singleton.getInstance().getComm().SendMessage(Communicator.UPDATE, dto);
		MemberDto mdto = new MemberDto();
		mdto.setId(dto.getDelivererId());
		Singleton.getInstance().getComm().SendMessage(Communicator.UPDATE_SCORE, mdto);
	}
	
	public List<OrderDto> getOderList() {
		OrderDto dto = new OrderDto();
		dto.setConsumerId(Singleton.getInstance().getMemCtrl().getCurrentUser().getId());
		Singleton.getInstance().getComm().SendMessage(Communicator.SELECT_MYODER, dto);
		List<OrderDto> list = (List<OrderDto>)Singleton.getInstance().getComm().receiveObject();
		
		return list;
	}

	public List<OrderDto> getDeliverList(String id) {
		OrderDto dto = new OrderDto();
		dto.setDelivererId(id);
		Communicator comm = Singleton.getInstance().getComm();
		comm.SendMessage(Communicator.SELECT_DELIVER_LIST, dto);
		return (List<OrderDto>) comm.receiveObject();
	}

}

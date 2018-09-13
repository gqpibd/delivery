package service;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import communicator.Communicator;
import dto.MemberDto;
import singleton.Singleton;

public class MemberService {
	List<MemberDto> mList = new ArrayList<MemberDto>();
	MemberDto CurrentUser = null;

	public boolean login(MemberDto dto) { // 로그인
		Communicator comm = Singleton.getInstance().getComm();
		comm.SendMessage(comm.SELECT, dto);
		CurrentUser = (MemberDto) comm.receiveObject();
		if (CurrentUser != null) {
			JOptionPane.showMessageDialog(null, CurrentUser.getId() + "님 환영합니다");
			return true;
		}
		return false;
	}
}

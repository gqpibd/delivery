package service;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import communicator.Communicator;
import dto.MemberDto;
import singleton.Singleton;

public class MemberService {
	// private List<MemberDto> mList = new ArrayList<MemberDto>();
	private MemberDto CurrentUser = null;

	public boolean login(MemberDto dto) { // 로그인
		Communicator comm = Singleton.getInstance().getComm();
		comm.SendMessage(Communicator.SELECT, dto);
		CurrentUser = (MemberDto) comm.receiveObject();
		if (CurrentUser != null) {
			return true;
		}
		return false;
	}

	public MemberDto getCurrentUser() {
		return CurrentUser;
	}

	public boolean existingId(String id) {
		MemberDto dto = new MemberDto();
		dto.setId(id);
		Communicator comm = Singleton.getInstance().getComm();

		comm.SendMessage(Communicator.SELECT_IDCHEKCK, dto);
		boolean exsitingId = (Boolean) comm.receiveObject();

		return exsitingId;
	}

	public void insert(MemberDto dto) {
		Communicator comm = Singleton.getInstance().getComm();

		comm.SendMessage(Communicator.INSERT, dto);
	}
}

package controller;

import dto.MemberDto;
import service.MemberService;

public class MemberController {

	private MemberService memDao = new MemberService();

	public boolean login(MemberDto dto) {
		boolean loginSuccess = memDao.login(dto);

		if (loginSuccess) {
			//???
		}

		return loginSuccess;

	}

}

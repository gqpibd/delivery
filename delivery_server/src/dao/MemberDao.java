package dao;

import java.net.Socket;

import constants.Dml;
import dto.MemberDto;

public class MemberDao {

	public void execute(int number, MemberDto dto, Socket sock) {
		switch (number) {
		case Dml.INSERT: // 회원가입
			insert(dto);
			System.out.println(dto.getId() + "를 멤버 테이블에 추가하였습니다");
			break;
		case Dml.DELETE:
			break;
		case Dml.UPDATE:
			break;
		case Dml.SELECT:
			break;
		}

	}

	private void insert(MemberDto dto) {
		// TODO Auto-generated method stub

	}

}

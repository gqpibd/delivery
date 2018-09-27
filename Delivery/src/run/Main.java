package run;

import singleton.Singleton;

public class Main {

	public static void main(String[] args) {
		Singleton.getInstance().getComm().makeConnection();	// 소켓 연결
		Singleton.getInstance().getMemCtrl().showInitView(); // 초기화면 시작
	}

}

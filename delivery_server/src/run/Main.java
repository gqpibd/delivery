package run;

import communicator.SocketControl;
import db.DBConnection;

public class Main {

	public static void main(String[] args) {

		DBConnection.initConnection(); // DB 초기화
		new SocketControl().serverOpen(); // TCP 서버 시작

	}
}

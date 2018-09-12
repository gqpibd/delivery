package run;

import communicator.SocketControl;
import db.DBConnection;

public class Main {

	public static void main(String[] args) {
		DBConnection.initConnection();
		new SocketControl().serverOpen();
	}

}

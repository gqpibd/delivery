package run;

import singleton.Singleton;
import view.LoginView;

public class Main {

	public static void main(String[] args) {
		
		//new OrderBBsView();
//		new Communicator().makeConnection();
		Singleton.getInstance().getComm().makeConnection();
		new LoginView();
	}

}

package run;

import communicator.Communicator;
import singleton.Singleton;
import view.PostView;

public class Main {

	public static void main(String[] args) {
		
		//new OrderBBsView();

		new Communicator().makeConnection();
		Singleton.getInstance().getComm().makeConnection();
		//new LoginView();

		//new Communicator().makeConnection();
		//new MainView();
		new PostView();
	}

}

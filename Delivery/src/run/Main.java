package run;

import singleton.Singleton;
import view.PostView;

public class Main {

	public static void main(String[] args) {
		
		//new OrderBBsView();

		Singleton.getInstance().getComm().makeConnection();
		//new LoginView();

		//new Communicator().makeConnection();
		//new MainView();
		new PostView();
	}

}

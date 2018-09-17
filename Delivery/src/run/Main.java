package run;

import singleton.Singleton;
import view.InitView;
import view.OrderWriteView;
import view.PostView;

public class Main {

	public static void main(String[] args) {
		
		//new OrderBBsView();

		Singleton.getInstance().getComm().makeConnection();
		Singleton.getInstance().getMemCtrl().showInitView();
		//new OrderWriteView();
		//new Communicator().makeConnection();
		//new MainView();
		//new PostView();
	}

}

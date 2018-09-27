package run;

import singleton.Singleton;
import view.InitView;
import view.OrderWriteView;
import view.PostView;
import view.ReviewWriteDialog;

public class Main {

	public static void main(String[] args) {
		
	

		Singleton.getInstance().getComm().makeConnection();
		Singleton.getInstance().getMemCtrl().showInitView();
	
		
	}

}

package run;

import client.communicator.Communicator;
import view.OrderBBsView;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//new OrderBBsView();
		new Communicator().makeConnection();

	}

}

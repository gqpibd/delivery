package singleton;

import communicator.Communicator;
import controller.MemberController;
import controller.OrderController;
import controller.ReviewController;

public class Singleton {

	private static Singleton single = null;
	private MemberController MC;
	private OrderController OC;
	private ReviewController RC;
	private Communicator comm;
	
	private Singleton() {
		MC = new MemberController();
		OC = new OrderController();
		RC = new ReviewController();
		comm = new Communicator(); 
	}
	
	public static Singleton getInstance() {
		if(single == null) {
			single = new Singleton();
		}
		return single;
	}

	public MemberController getMemCtrl() {
		return MC;
	}
	
	public OrderController getOderCtrl() {
		return OC;
	}
	
	public ReviewController getReviewCtrl() {
		return RC;
	}
	public Communicator getComm() {
		return comm;
	}
	
}

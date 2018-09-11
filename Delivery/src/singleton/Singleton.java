package singleton;

import controller.MemberController;
import controller.RequestController;
import controller.ReviewController;

public class Singleton {

	private static Singleton single = null;
	private MemberController MC;
	private OrderController OC;
	private ReviewController RC;
	
	private Singleton() {
		MC = new MemberController();
		OC = new OrderController();
		RC = new ReviewController();
	}
	
	public static Singleton getInstance() {
		if(single == null) {
			single = new Singleton();
		}
		return single;
	}
	
}

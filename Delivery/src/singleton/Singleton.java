package singleton;

import controller.MemberController;
import controller.RequestController;
import controller.ReviewController;

public class Singleton {

	private static Singleton single = null;
	private MemberController MC;
	private RequestController RC;
	private ReviewController RVC;
	
	private Singleton() {
	}
	
	public static Singleton getInstance() {
		if(single == null) {
			single = new Singleton();
		}
		return single;
	}
	
}

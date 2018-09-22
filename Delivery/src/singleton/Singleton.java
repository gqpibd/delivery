package singleton;

import communicator.Communicator;
import controller.MemberController;
import controller.OrderController;
import view.MainView;

public class Singleton {

	private static Singleton single = null;
	private MemberController MC;
	private OrderController OC;
	private Communicator comm;
	private MainView mv;
	
	private Singleton() {
		MC = new MemberController();
		OC = new OrderController();
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
	
	public OrderController getOrderCtrl() {
		return OC;
	}
	
	public Communicator getComm() {
		return comm;
	}
	public void showMainView() {
		if(mv!=null) {
			mv.dispose();
		}
		mv = new MainView();		
		mv.setVisible(true);
	}
	public void hideMainView() {
		mv.setVisible(false);
	}
	public MainView getMainView() {
		return mv;
	}
	
}

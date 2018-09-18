package controller;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import dto.MemberDto;
import service.MemberService;
import view.InitView;
import view.JoinView;
import view.LoginView;
import view.MainView;

public class MemberController {

	private MemberService mService= new MemberService();
	LoginView loginView;
	JoinView joinView;
	InitView initView;
	
	public MemberController() {
		initView = new InitView();
		initView.setVisible(false);
	}

	public void login(MemberDto dto) {
		boolean loginSuccess = mService.login(dto);

		if (loginSuccess) {
			JOptionPane.showMessageDialog(null, mService.getCurrentUser().getId() + "님 환영합니다");
			new MainView();
			loginView.dispose();
		}else {
			JOptionPane.showMessageDialog(null,"아이디 또는 패스워드가 틀렸습니다");			
		}
	}
	
	public void showLoginView() {
		initView.setVisible(false);
		loginView = new LoginView();
	}

	public void showJoinView() {
		initView.setVisible(false);
		joinView = new JoinView();		
	}

	public void backToInitView(JFrame currentView) {
		currentView.dispose();
		initView.setVisible(true);
	}
	
	public void showInitView() {
		initView.setVisible(true);
	}

	public boolean existingId(String id) {

		return	mService.existingId(id);
	}

	public void insert(MemberDto dto) {
		mService.insert(dto);		
	}
}

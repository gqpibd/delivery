package view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import dto.MemberDto;
import singleton.Singleton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class MainView extends JFrame implements ActionListener {

	private JPanel bottom_panel = null;
	private JButton myreqbtn;
	private JButton mypagebtn;
	private JButton bbsbtn;
	public static int BOTTOM_WIDTH = 480;
	public static int BOTTOM_HEIGHT = 474;
	private JPanel top_panel;
	private JButton logout_btn;
	private JButton exit_btn;
	private JLabel userLabel;

	public MainView() {
		super("대신해드려요!!");
		JPanel contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// 하단패널
		bottom_panel = new JPanel();

		top_panel = new JPanel();
		top_panel.setBounds(6, 2, 480, 48);
		contentPane.add(top_panel);
		top_panel.setLayout(null);

		logout_btn = new JButton("로그아웃");
		logout_btn.setBounds(368, 7, 112, 35);
		top_panel.add(logout_btn);

		exit_btn = new JButton("종료");
		exit_btn.setBounds(0, 7, 124, 35);
		top_panel.add(exit_btn);
		
		MemberDto dto = Singleton.getInstance().getMemCtrl().getCurrentUser();
		String name = dto.getId();
		String auth = (dto.getAuth()==MemberDto.DELIVERER)?"배달원":"주문고객";
		userLabel = new JLabel(auth + " " + name + "님 로그인");
		userLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		userLabel.setBounds(136, 17, 220, 15);
		top_panel.add(userLabel);

		// 상단 패널
		JPanel tab_panel = new JPanel();
		tab_panel.setBounds(6, 51, 480, 64);
		contentPane.add(tab_panel);
		tab_panel.setLayout(new GridLayout(0, 3, 0, 0));

		bbsbtn = new JButton("배달요청");
		tab_panel.add(bbsbtn);

		myreqbtn = new JButton("내 주문 내역");
		tab_panel.add(myreqbtn);

		mypagebtn = new JButton("마이 페이지");
		tab_panel.add(mypagebtn);
		bottom_panel = new OrderBBsView();
		// bottom_panel.add(new OrderBBsView());
		bottom_panel.setBounds(6, 117, 480, 474);
		contentPane.add(bottom_panel);

		logout_btn.addActionListener(this);
		exit_btn.addActionListener(this);
		bbsbtn.addActionListener(this);
		myreqbtn.addActionListener(this);
		mypagebtn.addActionListener(this);

		setSize(506, 640);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton btn = (JButton) e.getSource();
		if (btn == bbsbtn || btn == myreqbtn || btn == mypagebtn) {
			bottom_panel.removeAll();
			int auth = Singleton.getInstance().getMemCtrl().getCurrentUser().getAuth();

			if (e.getSource() == bbsbtn) {
				bottom_panel.add(new OrderBBsView());
			} else if (e.getSource() == myreqbtn) {
				if (auth == MemberDto.DELIVERER) {
					bottom_panel.add(new MyOrdersView_Deliverer());
				} else {
					bottom_panel.add(new MyOrdersView());
				}
			} else if (e.getSource() == mypagebtn) {
				if (auth == MemberDto.DELIVERER) {
					bottom_panel.add(new MyPageView_deliverer());
				} else {
					bottom_panel.add(new MypageView());
				}
			}
			bottom_panel.setSize(BOTTOM_WIDTH, BOTTOM_HEIGHT);
			repaint();
		} else if (e.getSource() == logout_btn) {
			Singleton.getInstance().getMemCtrl().logout();
			Singleton.getInstance().hideMainView();
		} else if (e.getSource() == exit_btn) {
			System.exit(0);
		}

	}
	
	public void setOrderView() {
		bottom_panel.removeAll();
		int auth = Singleton.getInstance().getMemCtrl().getCurrentUser().getAuth();
		
		if (auth == MemberDto.DELIVERER) {
			bottom_panel.add(new MyOrdersView_Deliverer());
		} else {
			bottom_panel.add(new MyOrdersView());
		}
		bottom_panel.setSize(BOTTOM_WIDTH, BOTTOM_HEIGHT);
		repaint();
	}
}

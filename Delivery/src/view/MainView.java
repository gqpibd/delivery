package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import dto.MemberDto;
import singleton.Singleton;
import utils.images.LabelEventListener;

public class MainView extends JFrame implements ActionListener {

	private JPanel bottom_panel;
	private JLabel myreqbtn;
	private JLabel mypagebtn;
	private JLabel bbsbtn;
	public static int BOTTOM_WIDTH = 480;
	public static int BOTTOM_HEIGHT = 474;
	private JPanel top_panel;
	private JLabel logout_btn;
	private JLabel exit_btn;
	private JLabel userLabel;
	private static final String PATH = "main/";
	String myStr;

	public MainView() {
		super("대신해드려요!!");
		JPanel contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		setContentPane(contentPane);
		contentPane.setLayout(null);

		Font fontStyle = new Font("다음_Regular", Font.PLAIN, 14);
		setFont(fontStyle);

		top_panel = new JPanel();
		top_panel.setBackground(Color.WHITE);
		top_panel.setBounds(6, 2, 480, 48);
		contentPane.add(top_panel);
		top_panel.setLayout(null);

		logout_btn = new JLabel(new ImageIcon(getClass().getClassLoader().getResource(PATH + "logout.png")));
		logout_btn.setName(PATH + "logout.png");
		logout_btn.setBounds(355, 7, 125, 35);
		top_panel.add(logout_btn);

		exit_btn = new JLabel(new ImageIcon(getClass().getClassLoader().getResource(PATH + "exit.png")));
		exit_btn.setName(PATH + "exit.png");
		exit_btn.setBounds(0, 7, 125, 35);
		top_panel.add(exit_btn);

		MemberDto dto = Singleton.getInstance().getMemCtrl().getCurrentUser();
		String name = dto.getId();
		String auth = (dto.getAuth() == MemberDto.DELIVERER) ? "배달원" : "주문고객";
		userLabel = new JLabel(auth + " " + name + "님 로그인");
		userLabel.setFont(new Font("나눔스퀘어", Font.BOLD, 14));
		userLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		userLabel.setBounds(135, 17, 211, 15);
		top_panel.add(userLabel);

		
		JPanel tab_panel = new JPanel();
		tab_panel.setBackground(Color.WHITE);
		tab_panel.setBounds(6, 53, 480, 64);
		contentPane.add(tab_panel);
		tab_panel.setLayout(null);

		bbsbtn = new JLabel(new ImageIcon(getClass().getClassLoader().getResource(PATH + "bbs_over.png")));
		bbsbtn.setName(PATH + "bbs.png");
		bbsbtn.setBounds(0, 0, 160, 64);
		tab_panel.add(bbsbtn);

		if (dto.getAuth() == MemberDto.CONSUMER) {			
			myStr = "my_orders";
		} else {
			myStr = "my_delivery";
		}
		myreqbtn = new JLabel(new ImageIcon(getClass().getClassLoader().getResource(PATH + myStr +".png")));
		myreqbtn.setName(PATH + myStr +".png");
		myreqbtn.setBounds(160, 0, 160, 64);
		tab_panel.add(myreqbtn);

		mypagebtn = new JLabel(new ImageIcon(getClass().getClassLoader().getResource(PATH + "my_page.png")));
		mypagebtn.setName(PATH + "my_page.png");
		mypagebtn.setBounds(320, 0, 160, 64);
		tab_panel.add(mypagebtn);
		
		// 하단패널
		bottom_panel = new OrderBBsView();
		bottom_panel.setBounds(6, 117, 480, 474);
		contentPane.add(bottom_panel);

		logout_btn.addMouseListener(new LabelEventListener(this, logout_btn));
		exit_btn.addMouseListener(new LabelEventListener(this, exit_btn));
		bbsbtn.addMouseListener(new LabelEventListener(this, bbsbtn, true));
		myreqbtn.addMouseListener(new LabelEventListener(this, myreqbtn, true));
		mypagebtn.addMouseListener(new LabelEventListener(this, mypagebtn, true));

		setSize(506, 640);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object btn = e.getSource();
		if (btn == bbsbtn || btn == myreqbtn || btn == mypagebtn) {
			bottom_panel.removeAll();
			int auth = Singleton.getInstance().getMemCtrl().getCurrentUser().getAuth();

			if (e.getSource() == bbsbtn) {
				bottom_panel.add(new OrderBBsView());
				
				bbsbtn.setName(PATH + "bbs.png" + " focus");
				myreqbtn.setName(PATH + myStr +".png" + " blur");
				mypagebtn.setName(PATH + "my_page.png" + " blur");
				
				bbsbtn.setIcon(new ImageIcon(getClass().getClassLoader().getResource(PATH + "bbs_over.png")));
				myreqbtn.setIcon(new ImageIcon(getClass().getClassLoader().getResource(PATH + myStr +".png")));
				mypagebtn.setIcon(new ImageIcon(getClass().getClassLoader().getResource(PATH + "my_page.png")));
			} else if (e.getSource() == myreqbtn) {				
				if (auth == MemberDto.DELIVERER) {
					bottom_panel.add(new MyOrdersView_Deliverer());				
				} else {
					bottom_panel.add(new MyOrdersView());
				}
				
				bbsbtn.setName(PATH + "bbs.png" + " blur");
				myreqbtn.setName(PATH + myStr + ".png" + " focus");
				mypagebtn.setName(PATH + "my_page.png" + " blur");
				
				bbsbtn.setIcon(new ImageIcon(getClass().getClassLoader().getResource(PATH + "bbs.png")));
				myreqbtn.setIcon(new ImageIcon(getClass().getClassLoader().getResource(PATH + myStr + "_over.png")));
				mypagebtn.setIcon(new ImageIcon(getClass().getClassLoader().getResource(PATH + "my_page.png")));
			} else if (e.getSource() == mypagebtn) {
				if (auth == MemberDto.DELIVERER) {
					bottom_panel.add(new MyPageView_deliverer());
				} else {
					bottom_panel.add(new MypageView());
				}
				bbsbtn.setName(PATH + "bbs.png" + " blur");
				myreqbtn.setName(PATH + myStr + ".png" + " blur");
				mypagebtn.setName(PATH + "my_page.png" + " focus");
				
				bbsbtn.setIcon(new ImageIcon(getClass().getClassLoader().getResource(PATH + "bbs.png")));
				myreqbtn.setIcon(new ImageIcon(getClass().getClassLoader().getResource(PATH + myStr+".png")));
				mypagebtn.setIcon(new ImageIcon(getClass().getClassLoader().getResource(PATH + "my_page_over.png")));
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

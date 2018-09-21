package view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainView extends JFrame {
	
	JPanel bottom_panel=null;
	
	public MainView() {
		super("대신해드려요!!");
		getContentPane().setLayout(null);

		// 게시판 
		bottom_panel = new JPanel();
		bottom_panel.setLayout(new GridLayout());
		bottom_panel.add(new OrderBBsView());
		bottom_panel.setBounds(6, 125, 480, 487);
		getContentPane().add(bottom_panel);
		
		// 상단 패널 
		
		JPanel panel = new JPanel();
		panel.setBounds(6, 6, 480, 118);
		getContentPane().add(panel);
		
		JButton bbsbtn = new JButton("배달요청");
		panel.add(bbsbtn);
		bbsbtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				bottom_panel.remove(0);
				bottom_panel.add(new OrderBBsView());
				repaint();
				
			}
		});
	
		JButton myreqbtn = new JButton("사용요청 확인");
		panel.add(myreqbtn);
		myreqbtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				bottom_panel.removeAll();
				bottom_panel.add(new MyOrdersView());
				repaint();
			}
		});

		JButton mypagebtn = new JButton("마이 페이지");
		panel.add(mypagebtn);
		mypagebtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				bottom_panel.removeAll();
				bottom_panel.add(new MypageView());
				repaint();
			}
		});
		setBounds(0, 0, 500, 640);
		setVisible(true);
	}	
}

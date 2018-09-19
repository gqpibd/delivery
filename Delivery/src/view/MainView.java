package view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainView extends JFrame implements ActionListener{		
	JButton requestBtn;
	JButton button;
	JButton button_1;
	public MainView() {
		super("대신해드려요!!");
		getContentPane().setLayout(null);
		
		// 상단 패널 		
		JPanel panel = new JPanel();
		panel.setBounds(6, 6, 480, 118);
		getContentPane().add(panel);
		panel.setLayout(new GridLayout(0, 3, 0, 0));
		
		requestBtn = new JButton("배달요청");
		panel.add(requestBtn);
		
		button = new JButton("사용요청 확인");
		panel.add(button);
		
		button_1 = new JButton("마이 페이지");
		panel.add(button_1);
		
		// 게시판 
		JPanel bottom_panel = new OrderBBsView();
		bottom_panel.setBounds(6, 125, 480, 487);
		getContentPane().add(bottom_panel);
		
		//
		
		
		
		setBounds(0, 0, 500, 640);
		setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}

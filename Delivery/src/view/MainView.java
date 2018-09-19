package view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

<<<<<<< HEAD
public class MainView extends JFrame implements ActionListener{		
	JButton requestBtn;
	JButton button;
	JButton button_1;
=======
public class MainView extends JFrame {
	
	JPanel bottom_panel=null;
	//what is the problem? all
	
>>>>>>> refs/remotes/origin/h2gon
	public MainView() {
		super("대신해드려요!!");
		getContentPane().setLayout(null);
<<<<<<< HEAD
		
		// 상단 패널 		
=======
		// 게시판 
		bottom_panel = new OrderBBsView();
		bottom_panel.setBounds(6, 125, 480, 487);
		getContentPane().add(bottom_panel);
		
		// 상단 패널 
		
>>>>>>> refs/remotes/origin/h2gon
		JPanel panel = new JPanel();
		panel.setBounds(6, 6, 480, 118);
		getContentPane().add(panel);
		panel.setLayout(new GridLayout(0, 3, 0, 0));
		
<<<<<<< HEAD
		requestBtn = new JButton("배달요청");
		panel.add(requestBtn);
=======
		JButton bbsbtn = new JButton("배달요청");
		panel.add(bbsbtn);
		bbsbtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				bottom_panel.removeAll();
				bottom_panel.add(new OrderBBsView());
				
			}
		});
>>>>>>> refs/remotes/origin/h2gon
		
<<<<<<< HEAD
		button = new JButton("사용요청 확인");
		panel.add(button);
=======
		JButton myreqbtn = new JButton("사용요청 확인");
		panel.add(myreqbtn);
		myreqbtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
//				bottom_panel = new MyOrdersView();
//				bottom_panel.setBounds(6, 125, 480, 487);
//				getContentPane().add(bottom_panel);
				bottom_panel.removeAll();
				bottom_panel.add(new MyOrdersView());
				
			}
		});
>>>>>>> refs/remotes/origin/h2gon
		
<<<<<<< HEAD
		button_1 = new JButton("마이 페이지");
		panel.add(button_1);
=======
		JButton mypagebtn = new JButton("마이 페이지");
		panel.add(mypagebtn);
		mypagebtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
//				bottom_panel = new MypageView();
//				bottom_panel.setBounds(6, 125, 480, 487);
//				getContentPane().add(bottom_panel);
				bottom_panel.removeAll();
				bottom_panel.add(new MypageView());
				
			}
		});
>>>>>>> refs/remotes/origin/h2gon
		
		
		
		//
		
		
		
		setBounds(0, 0, 500, 640);
		setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}

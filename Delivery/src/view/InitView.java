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
import javax.swing.border.EmptyBorder;

import singleton.Singleton;
import utils.images.LabelEventListener;

public class InitView extends JFrame implements ActionListener{
	private JLabel login_label;
	private JLabel join_label;
	
	public static final String PATH = "init/";
	
	public InitView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // x버튼 누르면 종료
		JPanel contentPane;
		
		setSize(323, 388);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
				
		JLabel title_label = new JLabel(new ImageIcon(getClass().getClassLoader().getResource(PATH + "title.png")));
		title_label.setBounds(5, 5, 296, 58);
		contentPane.add(title_label);
		
		JLabel logoLabel = new JLabel(new ImageIcon(getClass().getClassLoader().getResource(PATH + "init.png")));
		logoLabel.setName(PATH + "init.png");
		logoLabel.setBounds(36, 60, 236, 201);
		contentPane.add(logoLabel);
		
		login_label = new JLabel(new ImageIcon(getClass().getClassLoader().getResource(PATH + "login.png")));
		login_label.setBounds(5, 271, 148, 71);
		contentPane.add(login_label);
		login_label.setName(PATH + "login.png");
		login_label.addMouseListener(new LabelEventListener(this,login_label));
		
		join_label = new JLabel(new ImageIcon(getClass().getClassLoader().getResource(PATH + "join.png")));
		join_label.setBounds(152, 271, 148, 71);
		contentPane.add(join_label);
		join_label.setName(PATH + "join.png");
		join_label.addMouseListener(new LabelEventListener(this,join_label));
		
		setVisible(true);		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == login_label) { // 로그인
			Singleton.getInstance().getMemCtrl().showLoginView();
		}else { // 회원가입
			Singleton.getInstance().getMemCtrl().showJoinView();
		}
	}
}

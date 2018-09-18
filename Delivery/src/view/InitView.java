package view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

	public InitView() {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel contentPane;
		
		setSize( 387, 202);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(2, 1, 0, 0));
		
		
		
		JLabel title_label = new JLabel("대신 해주세요!");
		contentPane.add(title_label);
		title_label.setFont(new Font("나눔손글씨 붓", Font.BOLD, 33));
		title_label.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel panel = new JPanel();
		contentPane.add(panel);
		panel.setLayout(new GridLayout(0, 2, 0, 0));
		
		login_label = new JLabel("로그인");
		login_label.addMouseListener(new LabelEventListener(this,login_label));
		panel.add(login_label);
		login_label.setHorizontalAlignment(SwingConstants.CENTER);
		
		join_label = new JLabel("회원가입");
		join_label.addMouseListener(new LabelEventListener(this,join_label));
		panel.add(join_label);
		join_label.setHorizontalAlignment(SwingConstants.CENTER);
		
		setVisible(true);
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == login_label) {
			Singleton.getInstance().getMemCtrl().showLoginView();
		}else {
			Singleton.getInstance().getMemCtrl().showJoinView();
		}
	}

}

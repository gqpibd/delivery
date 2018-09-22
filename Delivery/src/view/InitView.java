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
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		//title_label.setFont(new Font("나눔손글씨 붓", Font.BOLD, 33));
		title_label.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel panel = new JPanel();
		panel.setBounds(5, 271, 296, 71);
		contentPane.add(panel);
		panel.setLayout(null);
		
		login_label = new JLabel(new ImageIcon(getClass().getClassLoader().getResource(PATH + "login.png")));
		login_label.setName(PATH + "login.png");
		login_label.setBounds(0, 0, 148, 71);
		login_label.addMouseListener(new LabelEventListener(this,login_label));
		panel.add(login_label);
		login_label.setHorizontalAlignment(SwingConstants.CENTER);
		
		join_label = new JLabel(new ImageIcon(getClass().getClassLoader().getResource(PATH + "join.png")));
		join_label.setName(PATH + "join.png");
		join_label.setBounds(148, 0, 148, 71);
		join_label.addMouseListener(new LabelEventListener(this,join_label));
		panel.add(join_label);
		join_label.setHorizontalAlignment(SwingConstants.CENTER);
		
		
		JLabel logoLabel = new JLabel(new ImageIcon(getClass().getClassLoader().getResource(PATH + "init.png")));
		logoLabel.setName(PATH + "init.png");
		logoLabel.setBounds(36, 60, 236, 201);
		contentPane.add(logoLabel);
		
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

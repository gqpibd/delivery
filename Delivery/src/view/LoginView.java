package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import utils.images.LabelEventListener;

public class LoginView extends JFrame implements ActionListener {
	JTextField JTextF_ID;
	JPasswordField JTextF_PW;

	JLabel Jbut_login;
	JLabel Jbut_Account;
	JLabel Jbut_Exit;

	public LoginView() {
		super("로그인");

		//setContentPane(new JLabel(new ImageIcon(PATH + "loginView.jpg")));
		setResizable(false);
		getContentPane().setLayout(null);
		setResizable(false);

		JTextF_ID = new JTextField(10);
		JTextF_ID.setBounds(105, 60, 125, 25);
		getContentPane().add(JTextF_ID);

		JTextF_PW = new JPasswordField(10);
		JTextF_PW.addActionListener(this);
		JTextF_PW.setBounds(105, 90, 125, 25);
		getContentPane().add(JTextF_PW);

		JLabel JLabel_ID = new JLabel("아이디");
		JLabel_ID.setHorizontalAlignment(SwingConstants.RIGHT);
		JLabel_ID.setFont(new Font("나눔고딕 ExtraBold", Font.PLAIN, 14));
		JLabel_ID.setBounds(36, 58, 62, 27);
		getContentPane().add(JLabel_ID);

		JLabel JLabel_PW = new JLabel("비밀번호");
		JLabel_PW.setHorizontalAlignment(SwingConstants.RIGHT);
		JLabel_PW.setFont(new Font("나눔고딕 ExtraBold", Font.PLAIN, 14));
		JLabel_PW.setBounds(36, 92, 62, 18);
		getContentPane().add(JLabel_PW);

		//Jbut_Account = new JLabel(new ImageIcon(PATH + "signInBtn.jpg"));
		Jbut_Account = new JLabel();
		Jbut_Account.addMouseListener(new LabelEventListener(this));
		Jbut_Account.setBounds(80, 134, Jbut_Account.getIcon().getIconWidth(), Jbut_Account.getIcon().getIconHeight());
		getContentPane().add(Jbut_Account);

		// 로그인
		
		//Jbut_login = new JLabel(new ImageIcon(PATH + "logInBtn.jpg"));
		Jbut_login = new JLabel();
		Jbut_login.addMouseListener(new LabelEventListener(this));
		Jbut_login.setBounds(239, 54, Jbut_login.getIcon().getIconWidth(), Jbut_login.getIcon().getIconHeight());
		getContentPane().add(Jbut_login);

		//Jbut_Exit = new JLabel(new ImageIcon(PATH + "returnLoginBtn.jpg"));
		Jbut_Exit = new JLabel();
		Jbut_Exit.addMouseListener(new LabelEventListener(this));
		Jbut_Exit.setBounds(196, 134, Jbut_Exit.getIcon().getIconWidth(), Jbut_Exit.getIcon().getIconHeight());
		getContentPane().add(Jbut_Exit);

		setBackground(Color.WHITE);
		setSize(372, 239);
		setVisible(true);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

		setLocationRelativeTo(null);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}

}
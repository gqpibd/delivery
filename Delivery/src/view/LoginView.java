package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import dto.MemberDto;
import singleton.Singleton;
import utils.images.LabelEventListener;

public class LoginView extends JFrame implements ActionListener {
	JTextField JTextF_ID;
	JPasswordField JTextF_PW;

	JLabel Jbut_login;
	JLabel Jbut_Exit;

	public final String PATH = "login/";

	public LoginView() {
		super("로그인");
		getContentPane().setBackground(Color.DARK_GRAY);
		setSize(372, 239);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		Font fontStyle = new Font("다음_Regular", Font.PLAIN, 14);
		setFont(fontStyle);
		setResizable(false);

		JTextF_ID = new JTextField(10);
		JTextF_ID.setBounds(155, 41, 125, 25);
		getContentPane().add(JTextF_ID);

		JTextF_PW = new JPasswordField(10);
		JTextF_PW.addActionListener(this);
		JTextF_PW.setBounds(155, 76, 125, 25);
		getContentPane().add(JTextF_PW);

		JLabel JLabel_ID = new JLabel("아 이 디");
		JLabel_ID.setForeground(Color.WHITE);
		JLabel_ID.setHorizontalAlignment(SwingConstants.RIGHT);
		JLabel_ID.setBounds(65, 40, 62, 27);
		getContentPane().add(JLabel_ID);

		JLabel JLabel_PW = new JLabel("비밀번호");
		JLabel_PW.setForeground(Color.WHITE);
		JLabel_PW.setHorizontalAlignment(SwingConstants.RIGHT);
		JLabel_PW.setBounds(65, 79, 62, 18);
		getContentPane().add(JLabel_PW);

		// 로그인
		Jbut_login = new JLabel(new ImageIcon(getClass().getClassLoader().getResource(PATH + "login.png")));
		Jbut_login.setName(PATH + "login.png");
		Jbut_login.addMouseListener(new LabelEventListener(this,Jbut_login));	
		Jbut_login.setBounds(55, 145, 100, 43);
		getContentPane().add(Jbut_login);
		
		// 취속
		Jbut_Exit =  new JLabel(new ImageIcon(getClass().getClassLoader().getResource(PATH + "cancel.png")));
		Jbut_Exit.setName(PATH + "cancel.png");
		Jbut_Exit.addMouseListener(new LabelEventListener(this,Jbut_Exit));	
		Jbut_Exit.setBounds(210, 145, 100, 43);
		getContentPane().add(Jbut_Exit);

		setBackground(Color.WHITE);

		setVisible(true);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		Singleton s = Singleton.getInstance();
		String id = JTextF_ID.getText();
		String pw = new String(JTextF_PW.getPassword());
		MemberDto dto = new MemberDto();
		if (obj == Jbut_login || obj == JTextF_PW) {

			if (id.trim().equals("")) {
				JOptionPane.showMessageDialog(null, "아이디를 입력해주세요.");
			} else if (pw.trim().equals("")) {
				JOptionPane.showMessageDialog(null, "비밀번호를 입력해주세요.");
			} else if (!id.equals("") && !pw.equals("")) {
				dto.setId(id);
				dto.setPw(pw);
				s.getMemCtrl().login(dto);				
			}
		} else if (obj == Jbut_Exit) {
			s.getMemCtrl().backToInitView(this);
		}
	}

}
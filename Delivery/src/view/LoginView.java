package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import dto.MemberDto;
import singleton.Singleton;

public class LoginView extends JFrame implements ActionListener {
	JTextField JTextF_ID;
	JPasswordField JTextF_PW;

	JButton Jbut_login;
	JButton Jbut_Exit;

	public final String PATH = "images/";

	public LoginView() {
		super("로그인");
		setSize(372, 239);
		setLocationRelativeTo(null);
		// setContentPane(new JLabel(new ImageIcon(PATH + "loginView.jpg")));
		//setUndecorated(true);
		getContentPane().setLayout(null);
		setResizable(false);

		JTextF_ID = new JTextField(10);
		JTextF_ID.setBounds(147, 52, 125, 25);
		getContentPane().add(JTextF_ID);

		JTextF_PW = new JPasswordField(10);
		JTextF_PW.addActionListener(this);
		JTextF_PW.setBounds(147, 87, 125, 25);
		getContentPane().add(JTextF_PW);

		JLabel JLabel_ID = new JLabel("아이디");
		JLabel_ID.setHorizontalAlignment(SwingConstants.RIGHT);
		JLabel_ID.setBounds(57, 51, 62, 27);
		getContentPane().add(JLabel_ID);

		JLabel JLabel_PW = new JLabel("비밀번호");
		JLabel_PW.setHorizontalAlignment(SwingConstants.RIGHT);
		JLabel_PW.setBounds(57, 90, 62, 18);
		getContentPane().add(JLabel_PW);

		// 로그인

		// String loginImg = PATH + "loginBtn.png";
		// Jbut_login = new JLabel(new ImageIcon(loginImg));
		Jbut_login = new JButton();
		Jbut_login.setText("로그인");
		// Jbut_login.setText("로그인");
		// Jbut_login.addMouseListener(new
		// LabelEventListener(this,loginImg,Jbut_login));
		Jbut_login.addActionListener(this);
		// Jbut_login.setBounds(250, 40, Jbut_login.getIcon().getIconWidth(),
		// Jbut_login.getIcon().getIconHeight());
		Jbut_login.setBounds(57, 145, 100, 43);
		getContentPane().add(Jbut_login);

		// Jbut_Exit = new JLabel(new ImageIcon(PATH + "returnLoginBtn.jpg"));
		Jbut_Exit = new JButton();
		Jbut_Exit.setText("취소");
		Jbut_Exit.addActionListener(this);
		// Jbut_Exit.addMouseListener(new LabelEventListener(this));
		// Jbut_Exit.setBounds(196, 134, Jbut_Exit.getIcon().getIconWidth(),
		// Jbut_Exit.getIcon().getIconHeight());
		Jbut_Exit.setBounds(204, 145, 100, 43);
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
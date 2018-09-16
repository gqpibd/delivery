package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import singleton.Singleton;
import utils.images.LabelEventListener;

public class JoinView extends JFrame implements ActionListener {
	private JTextField id_field; // 아이디
	private JPasswordField pwd_field; // 비밀번호
	private JPasswordField pwd_check_field; // 비밀번호 확인
	private JTextField name_field; // 이름
	private JTextField address_field; // 주소
	private JTextField address_detail_field; // 상세 주소
	private JTextField phone_field; // 전화번호

	private JLabel check_btn; // 중복확인
	private JLabel join_btn; // 가입
	private JLabel back_btn; // 뒤로가기
	private JLabel search_btn; // 검색(주소)

	private boolean idChecked = false;

	// private static final String PATH = "images/accountView/";

	public JoinView() {
		setTitle("회원가입");
		getContentPane().setLayout(null);
		getContentPane().setBackground(Color.white);
		
		Font fontStyle = new Font("다음_Regular", Font.PLAIN, 14);
		getContentPane().setFont(fontStyle);

		id_field = new JTextField(10);
		id_field.setBounds(113, 53, 134, 21);
		getContentPane().add(id_field);

		pwd_field = new JPasswordField(10);
		pwd_field.setBounds(112, 84, 135, 21);
		getContentPane().add(pwd_field);

		pwd_check_field = new JPasswordField(10);
		pwd_check_field.setBounds(112, 115, 135, 21);
		getContentPane().add(pwd_check_field);

		name_field = new JTextField(10);
		name_field.setBounds(112, 146, 135, 21);
		getContentPane().add(name_field);

		address_field = new JTextField(10);
		address_field.setEditable(false);
		address_field.setBounds(113, 177, 236, 21);
		getContentPane().add(address_field);

		phone_field = new JTextField(10);
		phone_field.setBounds(113, 241, 134, 21);
		getContentPane().add(phone_field);

		address_detail_field = new JTextField(10);
		address_detail_field.setBounds(113, 210, 158, 21);
		getContentPane().add(address_detail_field);

		// check_btn = new JLabel(new ImageIcon(PATH + "signInBtn.jpg"));
		join_btn = new JLabel("회원가입");
		join_btn.setHorizontalAlignment(SwingConstants.CENTER);
		join_btn.addMouseListener(new LabelEventListener(this, check_btn));
		// check_btn.setBounds(248, 273, check_btn.getIcon().getIconWidth(),
		// check_btn.getIcon().getIconHeight());
		join_btn.setBounds(248, 273, 100, 25);

		// check_btn = new JLabel(new ImageIcon(PATH + "idCheckBtn.jpg"));
		check_btn = new JLabel("중복확인");
		check_btn.setHorizontalAlignment(SwingConstants.CENTER);
		check_btn.addMouseListener(new LabelEventListener(this, check_btn));
		// check_btn.setBounds(253, 48, check_btn.getIcon().getIconWidth(),
		// check_btn.getIcon().getIconHeight());
		check_btn.setBounds(253, 48, 100, 25);

		// back_btn = new JLabel(new ImageIcon(PATH + "mainReturnBtn.jpg"));
		back_btn = new JLabel("뒤로");
		back_btn.setHorizontalAlignment(SwingConstants.CENTER);
		back_btn.addMouseListener(new LabelEventListener(this, back_btn));
		// Jbut_Back.setBounds(12, 10, back_btn.getIcon().getIconWidth(),
		// back_btn.getIcon().getIconHeight());
		back_btn.setBounds(65, 272, 100, 25);

		// search_btn = new JLabel(new ImageIcon(PATH + "searchBtn.jpg"));
		search_btn = new JLabel("주소 검색");
		search_btn.setHorizontalAlignment(SwingConstants.CENTER);
		search_btn.addMouseListener(new LabelEventListener(this, search_btn));
		// search_btn.setBounds(283, 209, search_btn.getIcon().getIconWidth(),
		// search_btn.getIcon().getIconHeight());
		search_btn.setBounds(283, 209, 70, 25);
		getContentPane().add(search_btn);

		getContentPane().add(join_btn);
		getContentPane().add(check_btn);
		getContentPane().add(search_btn);
		getContentPane().add(back_btn);

		JLabel id_label = new JLabel("아이디");
		id_label.setHorizontalAlignment(SwingConstants.RIGHT);
		id_label.setBounds(12, 57, 89, 15);
		getContentPane().add(id_label);

		JLabel pw_label = new JLabel("비밀번호");
		pw_label.setHorizontalAlignment(SwingConstants.RIGHT);
		pw_label.setBounds(12, 88, 89, 15);
		getContentPane().add(pw_label);

		JLabel pwCheck_label = new JLabel("비밀번호 확인");
		pwCheck_label.setHorizontalAlignment(SwingConstants.RIGHT);
		pwCheck_label.setBounds(12, 122, 89, 15);
		getContentPane().add(pwCheck_label);

		JLabel name_label = new JLabel("이름");
		name_label.setHorizontalAlignment(SwingConstants.RIGHT);
		name_label.setBounds(12, 150, 89, 15);
		getContentPane().add(name_label);

		JLabel add_label = new JLabel("주소");
		add_label.setLabelFor(add_label);
		add_label.setHorizontalAlignment(SwingConstants.RIGHT);
		add_label.setBounds(12, 181, 89, 15);
		getContentPane().add(add_label);

		JLabel phone_label = new JLabel("연락처");
		phone_label.setHorizontalAlignment(SwingConstants.RIGHT);
		phone_label.setBounds(12, 244, 89, 15);
		getContentPane().add(phone_label);

		setSize(381, 360);
		setVisible(true);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

		setLocationRelativeTo(null);
	}

	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		Singleton single = Singleton.getInstance();

		// 보기쉽게 텍스트에있는거 전부다 스트린으로 옮김
		String id = id_field.getText().trim();
		String pw = new String(pwd_field.getPassword()).trim();
		String pw2 = new String(pwd_check_field.getPassword()).trim();
		String name = name_field.getText().trim();
		String address = address_field.getText().trim();
		String address2 = address_detail_field.getText().trim();
		String phone = phone_field.getText().trim();

		if (obj == check_btn) { // 아이디 중복 확인
			if (id.equals("")) { // 아이디를 입력하지 않은 경우
				JOptionPane.showMessageDialog(null, "아이디를 입력해주세요.");
			} else {
				boolean existingId = single.getMemCtrl().existingId(id);
				if (existingId == false) {
					JOptionPane.showMessageDialog(null, "사용 가능한 아이디입니다.");
					idChecked = true;
				} else if (existingId == true) {
					JOptionPane.showMessageDialog(null, "이미 존재하는 아이디입니다.");
					return;
				}
			}
		} else if (obj == join_btn) { // 회원가입
			if (id.equals("")) {
				JOptionPane.showMessageDialog(null, "아이디를 입력해 주세요.");
			} else if (idChecked == false) {
				JOptionPane.showMessageDialog(null, "중복확인을 눌러주세요.");
			} else if (name.equals("")) {
				JOptionPane.showMessageDialog(null, "이름을 입력해주세요");
			} else if (address.equals("") || address2.equals("")) {
				JOptionPane.showMessageDialog(null, "주소를 입력해주세요");
			} else if (phone.equals("")) {
				JOptionPane.showMessageDialog(null, "핸드폰 번호를 입력해주세요");
			} else if (!pw.equals(pw2)) {
				JOptionPane.showMessageDialog(null, "비밀번호가 일치하지 않습니다. 다시 확인해 주세요");
			} else if (!Pattern.matches("^01(?:0|1|[6-9])-(?:\\d{3}|\\d{4})-\\d{4}$", phone)) {
				JOptionPane.showMessageDialog(null, "전화번호 형식이 맞지 않습니다.\n 01X-XXXX-XXXX 형태로 입력해 주세요");
			} else {
				//MemberDto dto = new MemberDto(id, pw, name, 0, MemberDto., address + " " + address2, phone);
				//single.getMemCtrl().insert(dto);
				//single.backToMain(this);
				JOptionPane.showMessageDialog(null, "회원가입이 완료되었습니다.");
			}
		} else if (obj == back_btn) { // 뒤로가기
			Singleton.getInstance().getMemCtrl().backToInitView(this);
		} else if (obj == search_btn) { // 주소 검색
			SelectAddressDialog add = new SelectAddressDialog(this);
			address_field.setText(add.getAddress());
			address_detail_field.setText(add.getDetailAddress());
		}
	}
}
package view;

import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import communicator.Communicator;
import dto.ConsumerDto;
import singleton.Singleton;
import utils.images.ImageUtils;
import utils.images.LabelEventListener;

import java.awt.Color;

public class MypageView extends JPanel implements ActionListener {
	private JTextField idField;
	private JTextField name_field;
	private JPasswordField pwField;
	private JPasswordField pwFiled1;
	private JTextField phone_field;
	private JTextField addr_textField;
	private JTextField addr_detail_textField;

	private JLabel search_btn;
	private JLabel cancel_btn;
	private JLabel change_btn;

	private ConsumerDto dto = (ConsumerDto) Singleton.getInstance().getMemCtrl().getCurrentUser();
	public static final String PATH = "mypage/";

	public MypageView() {
		setBackground(Color.DARK_GRAY);
		setSize(MainView.BOTTOM_WIDTH, MainView.BOTTOM_HEIGHT);
		setLayout(null);
		Font fontStyle = new Font("다음_Regular", Font.PLAIN, 14);
		setFont(fontStyle);

		// 정보수정, 완료 버튼
		change_btn = new JLabel(new ImageIcon(getClass().getClassLoader().getResource(PATH + "modify.png")));
		change_btn.setName(PATH + "modify.png" + " change");
		change_btn.addMouseListener(new LabelEventListener(this, change_btn));
		change_btn.setBounds(82, 420, 117, 36);
		add(change_btn);

		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		panel.setBounds(10, 10, 456, 395);
		add(panel);
		panel.setLayout(null);

		idField = new JTextField(dto.getId());
		idField.setFont(new Font("나눔바른펜", Font.PLAIN, 14));
		idField.setEditable(false);
		idField.setColumns(10);
		idField.setBounds(141, 24, 213, 29);
		panel.add(idField);

		JLabel label = new JLabel("아이디");
		label.setFont(new Font("나눔스퀘어", Font.BOLD, 14));
		label.setForeground(Color.WHITE);
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setBounds(new Rectangle(0, 0, 200, 0));
		label.setBounds(12, 24, 106, 29);
		panel.add(label);

		JLabel label_1 = new JLabel("이름");
		label_1.setFont(new Font("나눔스퀘어", Font.BOLD, 14));
		label_1.setForeground(Color.WHITE);
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setBounds(new Rectangle(0, 0, 200, 0));
		label_1.setBounds(12, 79, 106, 25);
		panel.add(label_1);

		name_field = new JTextField(dto.getName());
		name_field.setFont(new Font("나눔바른펜", Font.PLAIN, 14));
		name_field.setEditable(false);
		name_field.setColumns(10);
		name_field.setBounds(141, 77, 213, 29);
		panel.add(name_field);

		pwField = new JPasswordField(dto.getPw());
		pwField.setColumns(10);
		pwField.setBounds(141, 130, 213, 29);
		panel.add(pwField);

		pwFiled1 = new JPasswordField(dto.getPw());
		pwFiled1.setColumns(10);
		pwFiled1.setBounds(141, 183, 213, 26);
		panel.add(pwFiled1);

		JLabel label_2 = new JLabel("비밀번호");
		label_2.setFont(new Font("나눔스퀘어", Font.BOLD, 14));
		label_2.setForeground(Color.WHITE);
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		label_2.setBounds(new Rectangle(0, 0, 200, 0));
		label_2.setBounds(19, 123, 99, 43);
		panel.add(label_2);

		JLabel label_3 = new JLabel("비밀번호 확인");
		label_3.setFont(new Font("나눔스퀘어", Font.BOLD, 14));
		label_3.setForeground(Color.WHITE);
		label_3.setHorizontalAlignment(SwingConstants.RIGHT);
		label_3.setBounds(new Rectangle(0, 0, 200, 0));
		label_3.setBounds(16, 184, 102, 25);
		panel.add(label_3);

		JLabel phone_label = new JLabel("전화번호");
		phone_label.setFont(new Font("나눔스퀘어", Font.BOLD, 14));
		phone_label.setForeground(Color.WHITE);
		phone_label.setHorizontalAlignment(SwingConstants.RIGHT);
		phone_label.setBounds(new Rectangle(0, 0, 200, 0));
		phone_label.setBounds(16, 235, 102, 25);
		panel.add(phone_label);

		phone_field = new JTextField(dto.getPhone());
		phone_field.setFont(new Font("나눔바른펜", Font.PLAIN, 14));
		phone_field.setColumns(10);
		phone_field.setBounds(141, 233, 213, 29);
		panel.add(phone_field);

		JLabel label_5 = new JLabel("주소");
		label_5.setFont(new Font("나눔스퀘어", Font.BOLD, 14));
		label_5.setForeground(Color.WHITE);
		label_5.setHorizontalAlignment(SwingConstants.RIGHT);
		label_5.setBounds(new Rectangle(0, 0, 200, 0));
		label_5.setBounds(12, 286, 106, 29);
		panel.add(label_5);

		addr_textField = new JTextField();
		addr_textField.setFont(new Font("나눔바른펜", Font.PLAIN, 14));
		addr_textField.setEditable(false);
		addr_textField.setColumns(10);
		addr_textField.setBounds(141, 286, 303, 29);
		panel.add(addr_textField);

		addr_detail_textField = new JTextField();
		addr_detail_textField.setFont(new Font("나눔바른펜", Font.PLAIN, 14));
		addr_detail_textField.setColumns(10);
		addr_detail_textField.setBounds(141, 339, 184, 29);
		panel.add(addr_detail_textField);

		String address = dto.getAddress();
		int loc = 0;
		if (address.contains(")")) {
			loc = address.indexOf(")");
			addr_textField.setText(address.substring(0, loc + 1));

		} else {
			loc = address.indexOf(' ');
			loc = address.indexOf(' ', loc + 1);
			loc = address.indexOf(' ', loc + 1) - 1;
		}

		if (loc + 2 < address.length()) {
			addr_detail_textField.setText(address.substring(loc + 2));
		}

		search_btn = new JLabel(new ImageIcon(getClass().getClassLoader().getResource(PATH + "search_add.png")));
		search_btn.setName(PATH + "search_add.png");
		search_btn.addMouseListener(new LabelEventListener(this, search_btn));
		search_btn.setVisible(false);
		search_btn.setBounds(355, 341, 89, 25);
		panel.add(search_btn);

		cancel_btn = new JLabel(new ImageIcon(getClass().getClassLoader().getResource(PATH + "cancel.png")));
		cancel_btn.setName(PATH + "cancel.png" + " change");
		cancel_btn.addMouseListener(new LabelEventListener(this, cancel_btn));
		cancel_btn.setBounds(281, 420, 117, 36);
		cancel_btn.setVisible(false);
		add(cancel_btn);

		setComponent(false);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == change_btn) {
			String btnType = change_btn.getName().split(" ")[1];
			if (btnType.equals("change")) {
				change_btn.setIcon(new ImageIcon(getClass().getClassLoader().getResource(PATH + "complete.png")));
				change_btn.setName(PATH + "complete.png" + " complete");
				setComponent(true);
			} else {
				change_btn.setIcon(new ImageIcon(getClass().getClassLoader().getResource(PATH + "modify.png")));
				change_btn.setName(PATH + "modify.png" + " change");

				String pw1 = new String(pwField.getPassword());
				String pw2 = new String(pwFiled1.getPassword());
				String phone = phone_field.getText();

				if (!pw1.equals(pw2)) {
					JOptionPane.showMessageDialog(null, "패스워드가 일치하지 않습니다");
					return;
				}
				if (!Pattern.matches("^01(?:0|1|[6-9])-(?:\\d{3}|\\d{4})-\\d{4}$", phone)) {
					JOptionPane.showMessageDialog(null, "전화번호 형식이 맞지 않습니다.\n 01X-XXXX-XXXX 형태로 입력해 주세요");
					return;
				}
				if (addr_detail_textField.getText().length() == 0) {
					JOptionPane.showMessageDialog(null, "상세 주소를 모두 입력해 주세요.");
					return;
				}

				setComponent(false);
				dto.setAddress(addr_textField.getText() + " " + addr_detail_textField.getText());
				dto.setPw(new String(pwField.getPassword()));

				Singleton.getInstance().getComm().SendMessage(Communicator.UPDATE, dto);
				JOptionPane.showMessageDialog(null, "수정완료");
			}
		} else if (e.getSource() == cancel_btn) {
			change_btn.setIcon(new ImageIcon(getClass().getClassLoader().getResource(PATH + "modify.png")));
			change_btn.setName(PATH + "modify.png" + " change");
			setComponent(false);
		} else if (e.getSource() == search_btn) { // 주소 선택 선택
			SelectAddressDialog add = new SelectAddressDialog();
			if (add.getAddress() != null) {
				addr_textField.setText(add.getAddress());
				addr_detail_textField.setText(add.getDetailAddress());
			}
		}
	}

	public void setComponent(boolean bool) {
		addr_detail_textField.setEditable(bool);
		pwField.setEditable(bool);
		pwFiled1.setEditable(bool);
		search_btn.setVisible(bool);
		phone_field.setEditable(bool);
		cancel_btn.setVisible(bool);
	}
}

package view;

import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

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

public class MypageView extends JPanel implements ActionListener{
	
	private JButton change_btn;
	
	private JTextField idField;
	private JTextField name_field;
	private JPasswordField pwField;
	private JPasswordField pwFiled1;
	private JTextField phone_field;
	private JTextField addr_textField;
	private JTextField addr_detail_textField;
	private JButton search_btn;
	private ConsumerDto dto = (ConsumerDto) Singleton.getInstance().getMemCtrl().getCurrentUser();
	private JButton cancel_btn;

	public MypageView() {
		setSize(MainView.BOTTOM_WIDTH, MainView.BOTTOM_HEIGHT);
		setLayout(null);
		
		JLabel title_label = new JLabel("회원정보관리");
		title_label.setFont(new Font("나눔고딕 Light", Font.BOLD, 19));
		title_label.setHorizontalAlignment(SwingConstants.CENTER);
		title_label.setBounds(0, 6, 480, 68);
		add(title_label);
		
		change_btn = new JButton("정보수정");
		change_btn.setBounds(82, 421, 117, 43);
		add(change_btn);
		change_btn.setName("change");
		change_btn.addActionListener(this);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 76, 456, 329);
		add(panel);
		panel.setLayout(null);
		
		idField = new JTextField(dto.getId());
		idField.setEditable(false);
		idField.setColumns(10);
		idField.setBounds(141, 14, 213, 29);
		panel.add(idField);
		
		JLabel label = new JLabel("아이디 :");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setBounds(new Rectangle(0, 0, 200, 0));
		label.setBounds(12, 14, 106, 29);
		panel.add(label);
		
		JLabel label_1 = new JLabel("이름 :");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setBounds(new Rectangle(0, 0, 200, 0));
		label_1.setBounds(12, 59, 106, 25);
		panel.add(label_1);
		
		name_field = new JTextField(dto.getName());
		name_field.setEditable(false);
		name_field.setColumns(10);
		name_field.setBounds(141, 57, 213, 29);
		panel.add(name_field);
		
		pwField = new JPasswordField(dto.getPw());
		pwField.setColumns(10);
		pwField.setBounds(141, 100, 213, 29);
		panel.add(pwField);
		
		pwFiled1 = new JPasswordField(dto.getPw());
		pwFiled1.setColumns(10);
		pwFiled1.setBounds(141, 143, 213, 26);
		panel.add(pwFiled1);
		
		JLabel label_2 = new JLabel("비밀번호 :");
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		label_2.setBounds(new Rectangle(0, 0, 200, 0));
		label_2.setBounds(19, 93, 99, 43);
		panel.add(label_2);
		
		JLabel label_3 = new JLabel("비밀번호 확인 :");
		label_3.setHorizontalAlignment(SwingConstants.RIGHT);
		label_3.setBounds(new Rectangle(0, 0, 200, 0));
		label_3.setBounds(16, 144, 102, 25);
		panel.add(label_3);
		
		JLabel phone_label = new JLabel("전화번호 :");
		phone_label.setHorizontalAlignment(SwingConstants.RIGHT);
		phone_label.setBounds(new Rectangle(0, 0, 200, 0));
		phone_label.setBounds(16, 185, 102, 25);
		panel.add(phone_label);
		
		phone_field = new JTextField(dto.getPhone());
		phone_field.setColumns(10);
		phone_field.setBounds(141, 183, 213, 29);
		panel.add(phone_field);
		
		JLabel label_5 = new JLabel("주소 :");
		label_5.setHorizontalAlignment(SwingConstants.RIGHT);
		label_5.setBounds(new Rectangle(0, 0, 200, 0));
		label_5.setBounds(12, 226, 106, 29);
		panel.add(label_5);
		
		addr_textField = new JTextField();
		addr_textField.setEditable(false);
		addr_textField.setColumns(10);
		addr_textField.setBounds(141, 226, 213, 29);
		panel.add(addr_textField);
		
		addr_detail_textField = new JTextField();
		addr_detail_textField.setColumns(10);
		addr_detail_textField.setBounds(141, 269, 213, 29);
		panel.add(addr_detail_textField);
		
		String address = dto.getAddress();
		int loc=0;
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
		
		search_btn = new JButton("검색");
		search_btn.setBounds(376, 226, 68, 29);
		search_btn.addActionListener(this);
		panel.add(search_btn);
		
		cancel_btn = new JButton("취소");
		cancel_btn.setName("change");
		cancel_btn.setBounds(281, 421, 117, 43);
		cancel_btn.addActionListener(this);
		add(cancel_btn);
		
		setComponent(false);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
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

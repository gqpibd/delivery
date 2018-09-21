package view;

import java.awt.Color;
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
import javax.swing.border.LineBorder;

import communicator.Communicator;
import dto.ConsumerDto;
import dto.DelivererDto;
import singleton.Singleton;
import utils.images.ImageUtils;
import utils.images.LabelEventListener;

public class MyPageView_deliverer extends JPanel implements ActionListener {

	private JButton change_btn;

	private JTextField idField;
	private JTextField name_field;
	private JPasswordField pwField;
	private JPasswordField pwFiled1;
	private JTextField phone_field;
	private JTextField locField;
	private JButton search_btn;
	private DelivererDto dto = (DelivererDto) Singleton.getInstance().getMemCtrl().getCurrentUser();
	private JLabel image;
	private JLabel imgLabel;
	private JTextField img_path_field;
	private JLabel search_img_btn;
	private JButton cancel_btn;

	public MyPageView_deliverer() {
		setSize(MainView.BOTTOM_WIDTH, MainView.BOTTOM_HEIGHT);
		setLayout(null);

		JLabel title_label = new JLabel("회원정보관리");
		title_label.setFont(new Font("나눔고딕 Light", Font.BOLD, 19));
		title_label.setHorizontalAlignment(SwingConstants.CENTER);
		title_label.setBounds(0, 6, 480, 68);
		add(title_label);

		change_btn = new JButton("정보수정");
		change_btn.setBounds(82, 431, 117, 36);
		add(change_btn);
		change_btn.setName("change");
		change_btn.addActionListener(this);

		JPanel panel = new JPanel();
		panel.setBounds(10, 78, 456, 343);
		add(panel);
		panel.setLayout(null);

		idField = new JTextField(dto.getId());
		idField.setEditable(false);
		idField.setColumns(10);
		idField.setBounds(141, 7, 213, 29);
		panel.add(idField);

		JLabel label = new JLabel("아이디 :");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setBounds(new Rectangle(0, 0, 200, 0));
		label.setBounds(12, 7, 106, 29);
		panel.add(label);

		JLabel label_1 = new JLabel("이름 :");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setBounds(new Rectangle(0, 0, 200, 0));
		label_1.setBounds(12, 46, 106, 25);
		panel.add(label_1);

		name_field = new JTextField(dto.getName());
		name_field.setEditable(false);
		name_field.setColumns(10);
		name_field.setBounds(141, 43, 213, 29);
		panel.add(name_field);

		pwField = new JPasswordField(dto.getPw());
		pwField.setEditable(false);
		pwField.setColumns(10);
		pwField.setBounds(141, 79, 213, 29);
		panel.add(pwField);

		pwFiled1 = new JPasswordField(dto.getPw());
		pwFiled1.setEditable(false);
		pwFiled1.setColumns(10);
		pwFiled1.setBounds(141, 115, 213, 26);
		panel.add(pwFiled1);

		JLabel label_2 = new JLabel("비밀번호 :");
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		label_2.setBounds(new Rectangle(0, 0, 200, 0));
		label_2.setBounds(19, 81, 99, 25);
		panel.add(label_2);

		JLabel label_3 = new JLabel("비밀번호 확인 :");
		label_3.setHorizontalAlignment(SwingConstants.RIGHT);
		label_3.setBounds(new Rectangle(0, 0, 200, 0));
		label_3.setBounds(16, 115, 102, 25);
		panel.add(label_3);

		JLabel phone_label = new JLabel("전화번호 :");
		phone_label.setHorizontalAlignment(SwingConstants.RIGHT);
		phone_label.setBounds(new Rectangle(0, 0, 200, 0));
		phone_label.setBounds(16, 150, 102, 25);
		panel.add(phone_label);

		phone_field = new JTextField(dto.getPhone());
		phone_field.setEditable(false);
		phone_field.setColumns(10);
		phone_field.setBounds(141, 148, 213, 29);
		panel.add(phone_field);

		JLabel label_5 = new JLabel("배달지역 :");
		label_5.setHorizontalAlignment(SwingConstants.RIGHT);
		label_5.setBounds(new Rectangle(0, 0, 200, 0));
		label_5.setBounds(12, 185, 106, 29);
		panel.add(label_5);

		locField = new JTextField(dto.getLocations()[0]);
		locField.setEditable(false);
		locField.setColumns(10);
		locField.setBounds(141, 183, 213, 29);
		panel.add(locField);

		search_btn = new JButton("검색");
		search_btn.setVisible(false);
		search_btn.setBounds(366, 185, 68, 29);
		search_btn.addActionListener(this);
		panel.add(search_btn);

		image = new JLabel("프로필사진 :");
		image.setHorizontalAlignment(SwingConstants.RIGHT);
		image.setBounds(new Rectangle(0, 0, 200, 0));
		image.setBounds(12, 224, 106, 25);
		panel.add(image);

		imgLabel = new JLabel("image");
		imgLabel.setBorder(new LineBorder(Color.black));
		imgLabel.setHorizontalAlignment(SwingConstants.CENTER);
		imgLabel.setBounds(141, 222, 113, 111);
		panel.add(imgLabel);

		img_path_field = new JTextField(10);
		img_path_field.setEditable(false);
		img_path_field.setVisible(false);
		img_path_field.setBounds(266, 224, 168, 29);
		panel.add(img_path_field);

		search_img_btn = new JLabel("이미지 검색");
		search_img_btn.addMouseListener(new LabelEventListener(this, search_img_btn));
		search_img_btn.setHorizontalAlignment(SwingConstants.CENTER);
		search_img_btn.setBounds(340, 265, 94, 25);
		search_img_btn.setVisible(false);
		panel.add(search_img_btn);

		cancel_btn = new JButton("취소");
		cancel_btn.setName("change");
		cancel_btn.setBounds(281, 431, 117, 36);
		cancel_btn.addActionListener(this);
		cancel_btn.setVisible(false);
		add(cancel_btn);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == change_btn) {
			if (change_btn.getName().equals("change")) {
				change_btn.setName("complete");
				change_btn.setText("완료");
				setComponent(true);

			} else {
				change_btn.setName("change");
				change_btn.setText("정보수정");

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

				setComponent(false);

				dto.setLocations(locField.getText().split(","));
				dto.setPw(new String(pwField.getPassword()));

				Singleton.getInstance().getComm().SendMessage(Communicator.UPDATE, dto);
				JOptionPane.showMessageDialog(null, "수정완료");
			}
		} else if (e.getSource() == search_img_btn) { // 이미지 검색 수행
			String path = ImageUtils.jFileChooserUtil();
			if (path.length() != 0) {
				img_path_field.setText(path.substring(path.lastIndexOf("\\") + 1)); // 전체 경로에서 파일 이름과 확장자명만 가져온다.
				ImageUtils.setResizedImage(imgLabel, new ImageIcon(path));
			}
		} else if (e.getSource() == cancel_btn) {
			change_btn.setName("change");
			change_btn.setText("정보수정");

			setComponent(false);

		} else if (e.getSource() == search_btn) { // 배달지역 선택
			SelectGuDialog add = new SelectGuDialog();
			if (add.getGuName() != null) {
				locField.setText(add.getGuName());
			}
		}
	}
	
	public void setComponent(boolean bool) {
		pwField.setEditable(bool);
		pwFiled1.setEditable(bool);
		search_btn.setVisible(bool);
		phone_field.setEditable(bool);
		search_img_btn.setVisible(bool);
		cancel_btn.setVisible(bool);
		img_path_field.setVisible(bool);
	}
}

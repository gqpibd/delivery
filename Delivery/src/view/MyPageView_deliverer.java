package view;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import communicator.Communicator;
import dto.DelivererDto;
import singleton.Singleton;
import utils.images.ImageUtils;
import utils.images.LabelEventListener;
import java.awt.Font;

public class MyPageView_deliverer extends JPanel implements ActionListener {

	private JTextField idField;
	private JTextField name_field;
	private JPasswordField pwField;
	private JPasswordField pwFiled1;
	private JTextField phone_field;
	private JComboBox<String> locBox;
	private JLabel image;
	private JLabel imgLabel;
	private JTextField img_path_field;
	
	private JLabel search_img_btn;
	private JLabel cancel_btn;
	private JLabel change_btn;
	
	private DelivererDto dto = (DelivererDto) Singleton.getInstance().getMemCtrl().getCurrentUser();
	public static final String PATH = "mypage/";

	public MyPageView_deliverer() {
		setBackground(Color.DARK_GRAY);
		setSize(MainView.BOTTOM_WIDTH, MainView.BOTTOM_HEIGHT);
		setLayout(null);
		
		// 정보수정, 완료 버튼
		change_btn = new JLabel(new ImageIcon(getClass().getClassLoader().getResource(PATH + "modify.png")));
		change_btn.setName(PATH + "modify.png" + " change");
		change_btn.addMouseListener(new LabelEventListener(this, change_btn));
		change_btn.setBounds(82, 431, 117, 36);
		add(change_btn);

		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		panel.setBounds(10, 10, 456, 411);
		add(panel);
		panel.setLayout(null);

		idField = new JTextField(dto.getId());
		idField.setFont(new Font("나눔스퀘어", Font.PLAIN, 14));
		idField.setEditable(false);
		idField.setColumns(10);
		idField.setBounds(141, 16, 213, 29);
		panel.add(idField);

		JLabel label = new JLabel("아이디");
		label.setFont(new Font("나눔스퀘어", Font.BOLD, 14));
		label.setForeground(Color.WHITE);
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setBounds(new Rectangle(0, 0, 200, 0));
		label.setBounds(12, 16, 106, 29);
		panel.add(label);

		JLabel label_1 = new JLabel("이름");
		label_1.setFont(new Font("나눔스퀘어", Font.BOLD, 14));
		label_1.setForeground(Color.WHITE);
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setBounds(new Rectangle(0, 0, 200, 0));
		label_1.setBounds(12, 63, 106, 25);
		panel.add(label_1);

		name_field = new JTextField(dto.getName());
		name_field.setFont(new Font("나눔스퀘어", Font.PLAIN, 14));
		name_field.setEditable(false);
		name_field.setColumns(10);
		name_field.setBounds(141, 61, 213, 29);
		panel.add(name_field);

		pwField = new JPasswordField(dto.getPw());
		pwField.setEditable(false);
		pwField.setColumns(10);
		pwField.setBounds(141, 106, 213, 29);
		panel.add(pwField);

		pwFiled1 = new JPasswordField(dto.getPw());
		pwFiled1.setEditable(false);
		pwFiled1.setColumns(10);
		pwFiled1.setBounds(141, 151, 213, 26);
		panel.add(pwFiled1);

		JLabel label_2 = new JLabel("비밀번호");
		label_2.setFont(new Font("나눔스퀘어", Font.BOLD, 14));
		label_2.setForeground(Color.WHITE);
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		label_2.setBounds(new Rectangle(0, 0, 200, 0));
		label_2.setBounds(19, 108, 99, 25);
		panel.add(label_2);

		JLabel label_3 = new JLabel("비밀번호 확인");
		label_3.setFont(new Font("나눔스퀘어", Font.BOLD, 14));
		label_3.setForeground(Color.WHITE);
		label_3.setHorizontalAlignment(SwingConstants.RIGHT);
		label_3.setBounds(new Rectangle(0, 0, 200, 0));
		label_3.setBounds(16, 152, 102, 25);
		panel.add(label_3);

		JLabel phone_label = new JLabel("전화번호");
		phone_label.setFont(new Font("나눔스퀘어", Font.BOLD, 14));
		phone_label.setForeground(Color.WHITE);
		phone_label.setHorizontalAlignment(SwingConstants.RIGHT);
		phone_label.setBounds(new Rectangle(0, 0, 200, 0));
		phone_label.setBounds(16, 195, 102, 25);
		panel.add(phone_label);

		phone_field = new JTextField(dto.getPhone());
		phone_field.setFont(new Font("나눔스퀘어", Font.PLAIN, 14));
		phone_field.setEditable(false);
		phone_field.setColumns(10);
		phone_field.setBounds(141, 193, 213, 29);
		panel.add(phone_field);

		JLabel label_5 = new JLabel("배달지역");
		label_5.setFont(new Font("나눔스퀘어", Font.BOLD, 14));
		label_5.setForeground(Color.WHITE);
		label_5.setHorizontalAlignment(SwingConstants.RIGHT);
		label_5.setBounds(new Rectangle(0, 0, 200, 0));
		label_5.setBounds(12, 238, 106, 29);
		panel.add(label_5);

		locBox = new JComboBox<String>();
		locBox.setBounds(141, 238, 213, 29);
		locBox = new JComboBox<String>();
		locBox.setFont(new Font("나눔스퀘어", Font.PLAIN, 14));		
		locBox.setEditable(false);
		locBox.setBounds(141, 238, 213, 29);
		locBox.setBackground(Color.white);
		locBox.setEnabled(false);
		panel.add(locBox);
		
		Communicator comm = Singleton.getInstance().getComm();
		comm.SendMessage(Communicator.SELECT_GU, "");
		ArrayList<String> results = (ArrayList<String>) comm.receiveObject();
		for (int i = 0; i < results.size(); i++) {
			locBox.addItem(results.get(i).toString());
		}

		locBox.setSelectedItem(dto.getLocation());

		image = new JLabel("프로필사진");
		image.setFont(new Font("나눔스퀘어", Font.BOLD, 14));
		image.setForeground(Color.WHITE);
		image.setHorizontalAlignment(SwingConstants.RIGHT);
		image.setBounds(new Rectangle(0, 0, 200, 0));
		image.setBounds(12, 289, 106, 25);
		panel.add(image);

		imgLabel = new JLabel("image");
		imgLabel.setForeground(Color.WHITE);
		imgLabel.setBorder(new LineBorder(Color.black));
		imgLabel.setHorizontalAlignment(SwingConstants.CENTER);
		imgLabel.setBounds(141, 283, 113, 111);
		panel.add(imgLabel);

		img_path_field = new JTextField(10);
		img_path_field.setFont(new Font("나눔스퀘어", Font.PLAIN, 14));
		img_path_field.setEditable(false);
		img_path_field.setVisible(false);
		img_path_field.setBounds(266, 287, 168, 29);
		panel.add(img_path_field);

		search_img_btn = new JLabel(new ImageIcon(getClass().getClassLoader().getResource(PATH + "search_img.png")));
		search_img_btn.setName(PATH + "search_img.png");
		search_img_btn.addMouseListener(new LabelEventListener(this, search_img_btn));
		search_img_btn.setBounds(340, 326, 94, 25);
		search_img_btn.setVisible(false);
		panel.add(search_img_btn);

		cancel_btn = new JLabel(new ImageIcon(getClass().getClassLoader().getResource(PATH + "cancel.png")));
		cancel_btn.setName(PATH + "cancel.png" + " change");
		cancel_btn.addMouseListener(new LabelEventListener(this, cancel_btn));
		cancel_btn.setBounds(281, 431, 117, 36);
		cancel_btn.setVisible(false);
		add(cancel_btn);

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

				setComponent(false);

				dto.setLocation((String)locBox.getSelectedItem());
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
			change_btn.setIcon(new ImageIcon(getClass().getClassLoader().getResource(PATH + "modify.png")));
			change_btn.setName(PATH + "modify.png" + " change");
			
			cancel_btn.setVisible(false);
			setComponent(false);

		}
	}
	
	public void setComponent(boolean bool) {
		pwField.setEditable(bool);
		pwFiled1.setEditable(bool);
		phone_field.setEditable(bool);
		search_img_btn.setVisible(bool);
		cancel_btn.setVisible(bool);
		img_path_field.setVisible(bool);
		locBox.setEnabled(bool);
	}
}

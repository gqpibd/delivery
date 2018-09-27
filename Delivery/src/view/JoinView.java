package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRootPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import communicator.Communicator;
import dto.ConsumerDto;
import dto.DelivererDto;
import dto.MemberDto;
import singleton.Singleton;
import utils.images.ImageUtils;
import utils.images.LabelEventListener;
import java.awt.SystemColor;

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
	private JLabel searchAdd_btn; // 검색(주소)
	private JLabel search_img_btn; // 이미지 검색
	private JLabel delivererBtn; // 배달회원 탭
	private JLabel consumerBtn; // 구매회원 탭

	private boolean idChecked = false;
	private JPanel top_panel;
	private JPanel bottom_panel;

	private JPanel consumer_panel;
	private JPanel deliverer_panel;

	private int memberType = MemberDto.CONSUMER;
	private JLabel label;
	private JComboBox<String> location_box;
	private JLabel profile_label;
	private JLabel img_label;
	private JTextField img_path_field;

	private static final String PATH = "join/";

	public JoinView() {
		JPanel contentPane;
		contentPane = new JPanel();

		int width = 381;
		setSize(396, 486);
		setContentPane(contentPane);
		setTitle("회원가입");
		getContentPane().setLayout(null);
		getContentPane().setBackground(Color.WHITE);

		top_panel = new JPanel();
		top_panel.setBackground(Color.WHITE);
		top_panel.setBounds(0, 0, width, 73);
		contentPane.add(top_panel);

		// 구매회원탭
		consumerBtn = new JLabel(
				new ImageIcon(getClass().getClassLoader().getResource(PATH + "member_order_over.png")));
		consumerBtn.setName(PATH + "member_order.png");
		consumerBtn.setBounds(0, 0, 190, 73);
		consumerBtn.addMouseListener(new LabelEventListener(this, consumerBtn, true));
		top_panel.setLayout(null);
		top_panel.add(consumerBtn);

		// 배달회원탭
		delivererBtn = new JLabel(new ImageIcon(getClass().getClassLoader().getResource(PATH + "member_deliver.png")));
		delivererBtn.setName(PATH + "member_deliver.png");
		delivererBtn.setBounds(190, 0, 190, 73);
		delivererBtn.addMouseListener(new LabelEventListener(this, delivererBtn, true));
		top_panel.add(delivererBtn);

		// 회원가입 버튼
		join_btn = new JLabel(new ImageIcon(getClass().getClassLoader().getResource(PATH + "join.png")));
		join_btn.setName(PATH + "join.png");
		join_btn.setBounds(220, 408, 100, 30);
		contentPane.add(join_btn);
		join_btn.addMouseListener(new LabelEventListener(this, join_btn));

		// 뒤로 버튼
		back_btn = new JLabel(new ImageIcon(getClass().getClassLoader().getResource(PATH + "back.png")));
		back_btn.setName(PATH + "back.png");
		back_btn.setBounds(60, 408, 100, 30);
		contentPane.add(back_btn);
		back_btn.addMouseListener(new LabelEventListener(this, back_btn));

		bottom_panel = new JPanel();
		bottom_panel.setBounds(0, 76, width, 322);
		bottom_panel.setBackground(Color.WHITE);
		contentPane.add(bottom_panel);
		bottom_panel.setLayout(null);

		id_field = new JTextField(10);
		id_field.setFont(new Font("나눔스퀘어", Font.PLAIN, 14));
		id_field.setBounds(113, 15, 134, 21);
		bottom_panel.add(id_field);

		pwd_field = new JPasswordField(10);
		pwd_field.setBounds(112, 46, 135, 21);
		bottom_panel.add(pwd_field);

		pwd_check_field = new JPasswordField(10);
		pwd_check_field.setBounds(112, 77, 135, 21);
		bottom_panel.add(pwd_check_field);

		name_field = new JTextField(10);
		name_field.setFont(new Font("나눔스퀘어", Font.PLAIN, 14));
		name_field.setBounds(112, 108, 135, 21);
		bottom_panel.add(name_field);

		phone_field = new JTextField(10);
		phone_field.setFont(new Font("나눔스퀘어", Font.PLAIN, 14));
		phone_field.setBounds(113, 139, 134, 21);
		bottom_panel.add(phone_field);

		// 아이디 중복확인
		check_btn = new JLabel(new ImageIcon(getClass().getClassLoader().getResource(PATH + "checkId.png")));
		check_btn.setName(PATH + "checkId.png");
		check_btn.addMouseListener(new LabelEventListener(this, check_btn));
		check_btn.setBounds(253, 13, 89, 25);
		bottom_panel.add(check_btn);

		JLabel id_label = new JLabel("아이디");
		id_label.setFont(new Font("나눔스퀘어", Font.BOLD, 14));
		id_label.setHorizontalAlignment(SwingConstants.RIGHT);
		id_label.setBounds(12, 18, 89, 15);
		bottom_panel.add(id_label);

		JLabel pw_label = new JLabel("비밀번호");
		pw_label.setFont(new Font("나눔스퀘어", Font.BOLD, 14));
		pw_label.setHorizontalAlignment(SwingConstants.RIGHT);
		pw_label.setBounds(12, 49, 89, 15);
		bottom_panel.add(pw_label);

		JLabel pwCheck_label = new JLabel("비밀번호 확인");
		pwCheck_label.setFont(new Font("나눔스퀘어", Font.BOLD, 14));
		pwCheck_label.setHorizontalAlignment(SwingConstants.RIGHT);
		pwCheck_label.setBounds(12, 80, 89, 15);
		bottom_panel.add(pwCheck_label);

		JLabel name_label = new JLabel("이름");
		name_label.setFont(new Font("나눔스퀘어", Font.BOLD, 14));
		name_label.setHorizontalAlignment(SwingConstants.RIGHT);
		name_label.setBounds(12, 111, 89, 15);
		bottom_panel.add(name_label);

		JLabel phone_label = new JLabel("연락처");
		phone_label.setFont(new Font("나눔스퀘어", Font.BOLD, 14));
		phone_label.setHorizontalAlignment(SwingConstants.RIGHT);
		phone_label.setBounds(12, 142, 89, 15);
		bottom_panel.add(phone_label);

		consumer_panel = new JPanel();
		consumer_panel.setBackground(Color.WHITE);
		consumer_panel.setBounds(0, 159, 381, 118);

		consumer_panel.setLayout(null);
		consumer_panel.setVisible(true);

		deliverer_panel = new JPanel();
		deliverer_panel.setBounds(0, 159, 381, 163);
		deliverer_panel.setBackground(Color.WHITE);
		bottom_panel.add(deliverer_panel);
		deliverer_panel.setLayout(null);

		label = new JLabel("배달 지역");
		label.setFont(new Font("나눔스퀘어", Font.BOLD, 14));
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setBounds(12, 14, 89, 15);
		deliverer_panel.add(label);

		
		location_box = new JComboBox<String>();
		location_box.setBackground(Color.WHITE);
		location_box.setFont(new Font("나눔스퀘어", Font.PLAIN, 14));		
		location_box.setBounds(113, 11, 135, 21);
		deliverer_panel.add(location_box);
		
		Communicator comm = Singleton.getInstance().getComm();
		comm.SendMessage(Communicator.SELECT_GU, "");
		ArrayList<String> results = (ArrayList<String>) comm.receiveObject();
		for (int i = 0; i < results.size(); i++) {
			location_box.addItem(results.get(i).toString());
		}

		profile_label = new JLabel("프로필 사진");
		profile_label.setFont(new Font("나눔스퀘어", Font.BOLD, 14));
		profile_label.setHorizontalAlignment(SwingConstants.RIGHT);
		profile_label.setBounds(12, 45, 89, 15);
		deliverer_panel.add(profile_label);

		img_label = new JLabel();
		img_label.setBorder(new LineBorder(Color.darkGray));
		img_label.setBounds(113, 45, 100, 100);
		deliverer_panel.add(img_label);

		img_path_field = new JTextField(10);
		img_path_field.setFont(new Font("나눔스퀘어", Font.PLAIN, 14));
		img_path_field.setEditable(false);
		img_path_field.setBounds(240, 52, 129, 21);
		deliverer_panel.add(img_path_field);
		deliverer_panel.setVisible(false);

		// 이미지 검색 버튼
		search_img_btn = new JLabel(new ImageIcon(getClass().getClassLoader().getResource(PATH + "search_img.png")));
		search_img_btn.setName(PATH + "search_img.png");
		search_img_btn.addMouseListener(new LabelEventListener(this, search_img_btn));
		search_img_btn.setBounds(240, 83, 129, 25);
		deliverer_panel.add(search_img_btn);

		JLabel add_label = new JLabel("주소");
		add_label.setFont(new Font("나눔스퀘어", Font.BOLD, 14));
		add_label.setBounds(12, 13, 89, 15);
		consumer_panel.add(add_label);
		add_label.setLabelFor(add_label);
		add_label.setHorizontalAlignment(SwingConstants.RIGHT);

		address_field = new JTextField(10);
		address_field.setFont(new Font("나눔스퀘어", Font.PLAIN, 14));
		address_field.setEditable(false);
		address_field.setBounds(113, 10, 236, 21);
		consumer_panel.add(address_field);

		address_detail_field = new JTextField(10);
		address_detail_field.setFont(new Font("나눔스퀘어", Font.PLAIN, 14));
		address_detail_field.setBounds(113, 40, 158, 21);
		consumer_panel.add(address_detail_field);

		searchAdd_btn = new JLabel(new ImageIcon(getClass().getClassLoader().getResource(PATH + "search_add.png")));
		searchAdd_btn.setName(PATH + "search_add.png");
		searchAdd_btn.addMouseListener(new LabelEventListener(this, searchAdd_btn));
		searchAdd_btn.setBounds(283, 38, 89, 25);
		consumer_panel.add(searchAdd_btn);

		bottom_panel.add(consumer_panel);

		setVisible(true);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

		setLocationRelativeTo(null);
	}

	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		Singleton single = Singleton.getInstance();

		String id = id_field.getText().trim();
		String pw = new String(pwd_field.getPassword()).trim();
		String pw2 = new String(pwd_check_field.getPassword()).trim();
		String name = name_field.getText().trim();
		String address = address_field.getText().trim();
		String address2 = address_detail_field.getText().trim();
		String phone = phone_field.getText().trim();
		String location = (String) location_box.getSelectedItem();

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
			} else if ((address.equals("") || address2.equals("")) && memberType == MemberDto.CONSUMER) {
				JOptionPane.showMessageDialog(null, "주소를 입력해주세요");
			} else if (phone.equals("")) {
				JOptionPane.showMessageDialog(null, "핸드폰 번호를 입력해주세요");
			} else if (!pw.equals(pw2)) {
				JOptionPane.showMessageDialog(null, "비밀번호가 일치하지 않습니다. 다시 확인해 주세요");
			} else if (!Pattern.matches("^01(?:0|1|[6-9])-(?:\\d{3}|\\d{4})-\\d{4}$", phone)) {
				JOptionPane.showMessageDialog(null, "전화번호 형식이 맞지 않습니다.\n 01X-XXXX-XXXX 형태로 입력해 주세요");
			} else if (location.equals("") && memberType == MemberDto.DELIVERER) {
				JOptionPane.showMessageDialog(null, "배달 지역을 입력해 주세요");
			} else {
				MemberDto dto = null;
				if (memberType == MemberDto.DELIVERER) {
					dto = new DelivererDto(id, pw, name, phone, location);
				} else {
					dto = new ConsumerDto(id, pw, name, phone, address + " " + address2);
				}
				single.getMemCtrl().insert(dto,img_path_field.getText());
				JOptionPane.showMessageDialog(null, "회원가입이 완료되었습니다.");
				Singleton.getInstance().getMemCtrl().backToInitView(this);
			}
		} else if (obj == back_btn) { // 뒤로가기
			Singleton.getInstance().getMemCtrl().backToInitView(this);
		} else if (obj == searchAdd_btn) { // 주소 검색
			SelectAddressDialog add = new SelectAddressDialog();
			address_field.setText(add.getAddress());
			address_detail_field.setText(add.getDetailAddress());
		}  else if (e.getSource() == search_img_btn) { // 이미지 검색 수행
			String path = ImageUtils.jFileChooserUtil();
			if (path.length() != 0) {
				img_path_field.setText(path); // 전체 경로에서 파일 이름과 확장자명만 가져온다.
				ImageUtils.setResizedImage(img_label, new ImageIcon(path));
			}
		} else if (obj == delivererBtn) {
			if (memberType != MemberDto.DELIVERER) {
				memberType = MemberDto.DELIVERER;
				deliverer_panel.setVisible(true);
				consumer_panel.setVisible(false);
				delivererBtn.setName(PATH + "member_deliver.png" + " focus");
				consumerBtn.setName(PATH + "member_order.png" + " blur");
				delivererBtn.setIcon(
						new ImageIcon(getClass().getClassLoader().getResource(PATH + "member_deliver_over.png")));
				consumerBtn.setIcon(new ImageIcon(getClass().getClassLoader().getResource(PATH + "member_order.png")));
			}
		} else if (obj == consumerBtn) {
			if (memberType != MemberDto.CONSUMER) {
				memberType = MemberDto.CONSUMER;
				consumer_panel.setVisible(true);
				deliverer_panel.setVisible(false);
				delivererBtn.setName(PATH + "member_deliver.png" + " blur");
				consumerBtn.setName(PATH + "member_order.png" + " focus");
				delivererBtn
						.setIcon(new ImageIcon(getClass().getClassLoader().getResource(PATH + "member_deliver.png")));
				consumerBtn.setIcon(
						new ImageIcon(getClass().getClassLoader().getResource(PATH + "member_order_over.png")));
			}
		}
	}
}
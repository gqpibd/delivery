package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import communicator.Communicator;
import dto.DelivererDto;
import dto.MemberDto;
import dto.OrderDto;
import singleton.Singleton;
import utils.images.LabelEventListener;

public class PostView extends JFrame implements ActionListener {

	private JLabel title_label;
	private JLabel address_label;
	private JLabel user_label;
	private JLabel status_label;
	private JLabel date_label;
	private JLabel price_label;
	private JLabel applicants_label;
	private JLabel label;

	private JTextArea textArea;

	private JLabel back_btn;
	private JLabel userchk_btn;
	private JLabel update_btn;
	private JLabel del_btn;
	private JLabel acc_btn;
	private JLabel comp_btn;

	private OrderDto orderDetail = null;

	private static final String PATH = "orderWrite/";

	public PostView(OrderDto orderDetail) {
		getContentPane().setBackground(Color.WHITE);
		this.orderDetail = orderDetail;
		getContentPane().setLayout(null);

		title_label = new JLabel("제목");
		title_label.setFont(new Font("나눔스퀘어", Font.BOLD, 16));
		title_label.setHorizontalAlignment(SwingConstants.CENTER);
		title_label.setBounds(6, 6, 468, 32);
		getContentPane().add(title_label);

		address_label = new JLabel("지역 :");
		address_label.setFont(new Font("나눔스퀘어", Font.PLAIN, 14));
		address_label.setBounds(34, 50, 127, 16);
		getContentPane().add(address_label);

		user_label = new JLabel("작성자 :");
		user_label.setFont(new Font("나눔스퀘어", Font.PLAIN, 14));
		user_label.setBounds(34, 82, 127, 16);
		getContentPane().add(user_label);

		status_label = new JLabel("상태 :");
		status_label.setFont(new Font("나눔스퀘어", Font.PLAIN, 14));
		status_label.setBounds(274, 82, 139, 16);
		getContentPane().add(status_label);

		date_label = new JLabel("작성 :");
		date_label.setFont(new Font("나눔스퀘어", Font.PLAIN, 14));
		date_label.setHorizontalAlignment(SwingConstants.RIGHT);
		date_label.setBounds(226, 50, 222, 16);
		getContentPane().add(date_label);

		price_label = new JLabel("금액 :");
		price_label.setFont(new Font("나눔스퀘어", Font.PLAIN, 14));
		price_label.setBounds(34, 116, 127, 16);
		getContentPane().add(price_label);

		applicants_label = new JLabel("신청자 :");
		applicants_label.setFont(new Font("나눔스퀘어", Font.PLAIN, 14));
		applicants_label.setBounds(274, 116, 174, 16);
		getContentPane().add(applicants_label);

		textArea = new JTextArea();
		textArea.setFont(new Font("나눔스퀘어", Font.PLAIN, 14));
		textArea.setBorder(new LineBorder(Color.black));
		textArea.setLineWrap(true);
		textArea.setEditable(false);
		textArea.setBounds(34, 175, 414, 226);
		getContentPane().add(textArea);

		setPost(orderDetail);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(6, 420, 468, 52);
		getContentPane().add(panel);
		panel.setLayout(null);

		back_btn = new JLabel(new ImageIcon(getClass().getClassLoader().getResource(PATH + "back.png")));
		back_btn.setName(PATH + "back.png");
		back_btn.addMouseListener(new LabelEventListener(this, back_btn));
		back_btn.setBounds(0, 0, 117, 52);
		panel.add(back_btn);

		userchk_btn = new JLabel(new ImageIcon(getClass().getClassLoader().getResource(PATH + "see_applicants.png")));
		userchk_btn.setName(PATH + "see_applicants.png");
		userchk_btn.addMouseListener(new LabelEventListener(this, userchk_btn));
		userchk_btn.setBounds(117, 0, 117, 52);
		panel.add(userchk_btn);

		update_btn = new JLabel(new ImageIcon(getClass().getClassLoader().getResource(PATH + "modify.png")));
		update_btn.setName(PATH + "modify.png");
		update_btn.addMouseListener(new LabelEventListener(this, update_btn));
		update_btn.setBounds(234, 0, 117, 52);
		panel.add(update_btn);

		del_btn = new JLabel(new ImageIcon(getClass().getClassLoader().getResource(PATH + "delete.png")));
		del_btn.setName(PATH + "delete.png" + " del");
		del_btn.addMouseListener(new LabelEventListener(this, del_btn));
		del_btn.setBounds(351, 0, 117, 52);
		panel.add(del_btn);

		MemberDto currentUser = Singleton.getInstance().getMemCtrl().getCurrentUser();

		String address = "";
		if (orderDetail.getStatus().equals("진행중") && currentUser.getId().equals(orderDetail.getDelivererId())) {
			address = orderDetail.getAddress();
		} else {
			address = orderDetail.getAddress();
			int loc;
			if (address.contains(")")) {
				loc = address.indexOf(")");
				address = address.substring(0, loc + 1);
			}
		}

		label = new JLabel("주소 : " + address);
		label.setFont(new Font("나눔스퀘어", Font.PLAIN, 14));
		label.setBounds(34, 149, 414, 16);
		getContentPane().add(label);

		acc_btn = new JLabel(new ImageIcon(getClass().getClassLoader().getResource(PATH + "accept.png")));
		acc_btn.setName(PATH + "accept.png");
		acc_btn.addMouseListener(new LabelEventListener(this, acc_btn));
		acc_btn.setBounds(361, 110, 87, 29);
		getContentPane().add(acc_btn);

		comp_btn = new JLabel(new ImageIcon(getClass().getClassLoader().getResource(PATH + "deliver_complete.png")));
		comp_btn.setName(PATH + "deliver_complete.png");
		comp_btn.addMouseListener(new LabelEventListener(this, comp_btn));
		comp_btn.setBounds(361, 76, 87, 29);
		getContentPane().add(comp_btn);

		comp_btn.setVisible(false);
		if (orderDetail.getConsumerId().equals(currentUser.getId()) && orderDetail.getStatus().equals("진행중")) {
			comp_btn.setVisible(true);
			status_label.setVisible(false);
		}

		JPanel hl = new JPanel();
		hl.setBackground(Color.DARK_GRAY);
		hl.setBounds(0, 43, 479, 2);
		getContentPane().add(hl);

		acc_btn.setVisible(false);
		if (orderDetail.getDelivererId() != null && orderDetail.getDelivererId().equals(currentUser.getId())
				&& orderDetail.getStatus().equals("요청중")) { // 배달자가 지정되어 있으면서 그 배달자가 현재 로그인한 유저이고 상태는 요청중이면
			acc_btn.setVisible(true); // 수락 버튼 활성화
			applicants_label.setVisible(false);
		}
		if (!(currentUser.getId().equals(orderDetail.getConsumerId()) && orderDetail.getStatus().equals("요청중"))) { // 현재 사용자가 작성자가 아니거나, 요청중이 아니면
			update_btn.setVisible(false); // 수정 버튼 등등 숨긴다.
			userchk_btn.setVisible(false);
			del_btn.setVisible(false);
		}
		if (currentUser.getAuth() == MemberDto.DELIVERER && orderDetail.getStatus().equals("요청중")
				&& orderDetail.getDelivererId() == null) {
			del_btn.setVisible(true);
			del_btn.setIcon(new ImageIcon(getClass().getClassLoader().getResource(PATH + "apply.png")));
			del_btn.setName(PATH + "apply.png" + " apply");
			if (orderDetail.getApplicants() != null) {
				String myId = currentUser.getId();
				String appArr[] = orderDetail.getApplicants().split(",");
				for (int i = 0; i < appArr.length; i++) {
					if (appArr[i].equals(myId)) {
						del_btn.setVisible(false);
						break;
					}
				}
			}
		}

		setSize(495, 517);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public void setPost(OrderDto post) {

		title_label.setText(post.getTitle());
		address_label.setText("지역: " + post.getLocation());
		user_label.setText("작성자: " + post.getConsumerId());
		status_label.setText("상태 : " + post.getStatus());
		date_label.setText("신청일: " + post.getDate());
		price_label.setText("금액: " + post.getPrice() + " 원");
		if (post.getDelivererId() != null && post.getStatus().equals("요청중")) {
			applicants_label.setText(post.getDelivererId() + "님의 수락 대기중");
		} else if (post.getStatus().equals("진행중")) {
			applicants_label.setText(post.getDelivererId() + "님이 배달중");			
		} else if (post.getStatus().equals("완료됨")){
			applicants_label.setText(post.getDelivererId() + " 님이 배달 완료");
		} else {
			int applicants = 0;
			if (post.getApplicants().trim().length()>0) {				
				applicants = post.getApplicants().split(",").length;
			}
			applicants_label.setText("신청자: " + applicants + " 명");
		}

		textArea.setText(post.getContents());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == back_btn) {
			dispose();
		} else if (e.getSource() == update_btn) {
			Singleton.getInstance().getOrderCtrl().updatePostView(orderDetail);
			dispose();
		} else if (e.getSource() == del_btn) {
			String btnType = del_btn.getName().split(" ")[1];
			if (btnType.equals("del")) {
				Singleton.getInstance().getComm().SendMessage(Communicator.DELETE, orderDetail);
				this.dispose();
				Singleton.getInstance().showMainView();
			} else {
				if (orderDetail.getApplicants() == null) {
					orderDetail.setApplicants(Singleton.getInstance().getMemCtrl().getCurrentUser().getId());
				} else {
					String myId = Singleton.getInstance().getMemCtrl().getCurrentUser().getId();

					orderDetail.setApplicants(orderDetail.getApplicants() + "," + myId);
				}
				Singleton.getInstance().getComm().SendMessage(Communicator.UPDATE, orderDetail);
				setPost(orderDetail);
				del_btn.setVisible(false);
			}
		}else if (e.getSource() == userchk_btn) {
			new selectDeliver(this,orderDetail.getApplicants());
		}else if (e.getSource() == acc_btn) {
			orderDetail.setStatus("진행중");
			Singleton.getInstance().getOrderCtrl().updatePost(orderDetail);
			acc_btn.setVisible(false);
			status_label.setText("진행중");
			applicants_label.setText(orderDetail.getDelivererId() + "님이 배달중");	
			Singleton.getInstance().getMainView().setOrderView();
		}else if (e.getSource() == comp_btn) {
			new ReviewWriteDialog(orderDetail, this);		
		}
	}
	
	public void setComlete() {
		orderDetail.setStatus("완료됨");
		Singleton.getInstance().getOrderCtrl().updatePost(orderDetail);
		comp_btn.setVisible(false);			
		status_label.setText("완료됨");
		applicants_label.setText(orderDetail.getDelivererId() + " 님이 배달 완료");
		Singleton.getInstance().getMainView().setOrderView(); // 메인화면 업데이트
	}
	
	public void setDeliverer(String deliverer) {
		orderDetail.setDelivererId(deliverer);
		Singleton.getInstance().getOrderCtrl().updatePost(orderDetail);
		applicants_label.setText(deliverer + "님 " + "수락대기중");
	}
}

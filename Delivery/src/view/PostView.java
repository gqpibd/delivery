package view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import dto.MemberDto;
import dto.OrderBBsDto;
import dto.OrderDto;
import singleton.Singleton;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;

import communicator.Communicator;

public class PostView extends JFrame implements ActionListener {

	JLabel title_label;
	JLabel address_label;
	JLabel user_label;
	JLabel status_label;
	JLabel date_label;
	JLabel price_label;
	JLabel applicants_label;

	JTextArea textArea;
	JButton back_btn;
	JButton userchk_btn;
	JButton update_btn;
	JButton del_btn;

	OrderBBsDto dto = null;

	public PostView(OrderBBsDto dto) {
		this.dto = dto;
		getContentPane().setLayout(null);

		title_label = new JLabel("제목");
		title_label.setHorizontalAlignment(SwingConstants.CENTER);
		title_label.setBounds(6, 6, 468, 32);
		getContentPane().add(title_label);

		address_label = new JLabel("지역 :");
		address_label.setBounds(30, 50, 127, 16);
		getContentPane().add(address_label);

		user_label = new JLabel("작성자 :");
		user_label.setBounds(30, 82, 127, 16);
		getContentPane().add(user_label);

		status_label = new JLabel("상태 :");
		status_label.setBounds(339, 82, 135, 16);
		getContentPane().add(status_label);

		date_label = new JLabel("작성 :");
		date_label.setBounds(226, 50, 194, 16);
		getContentPane().add(date_label);

		price_label = new JLabel("금액 :");
		price_label.setBounds(30, 112, 127, 16);
		getContentPane().add(price_label);

		applicants_label = new JLabel("신청자 :");
		applicants_label.setBounds(339, 112, 109, 16);
		getContentPane().add(applicants_label);

		textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setEditable(false);
		textArea.setBounds(34, 140, 414, 267);
		getContentPane().add(textArea);

		setPost(dto);

		JPanel panel = new JPanel();
		panel.setBounds(6, 420, 468, 52);
		getContentPane().add(panel);
		panel.setLayout(new GridLayout(1, 0, 0, 0));

		back_btn = new JButton("뒤로");
		panel.add(back_btn);
		back_btn.addActionListener(this);

		userchk_btn = new JButton("신청자 확인");
		panel.add(userchk_btn);
		userchk_btn.addActionListener(this);

		update_btn = new JButton("수정");
		panel.add(update_btn);
		update_btn.addActionListener(this);

		del_btn = new JButton("삭제");
		del_btn.setName("del");
		panel.add(del_btn);
		del_btn.addActionListener(this);

		MemberDto currentUser = Singleton.getInstance().getMemCtrl().getCurrentUser();

		if (!currentUser.getId().equals(dto.getConsumerId())) {
			update_btn.setVisible(false);
			userchk_btn.setVisible(false);
			del_btn.setVisible(false);
		}
		if (currentUser.getAuth() == MemberDto.DELIVERER) {
			del_btn.setVisible(true);
			del_btn.setName("apply");
			del_btn.setText("신청");			
			if(dto.getApplicants() != null) {
				String myId = currentUser.getId();
				String appArr[] = dto.getApplicants().split(",");
				for (int i = 0; i < appArr.length; i++) {
					if(appArr[i].equals(myId)) {
						del_btn.setVisible(false);
						JOptionPane.showMessageDialog(null, "이미 신청완료되었습니다");
						break;
					}
				}
			}
		}

		setBounds(0, 0, 480, 500);
		setVisible(true);
	}

	public void setPost(OrderBBsDto post) {

		title_label.setText(post.getTitle());
		address_label.setText("지역: " + post.getLocation());
		user_label.setText("작성자: " + post.getConsumerId());
		status_label.setText("상태 : " + post.getStatus());
		date_label.setText("신청일: " + post.getDate());
		price_label.setText("금액: " + post.getPrice() + " 원");
		int applicants = 0;
		if (post.getApplicants() != null) {
			applicants = post.getApplicants().split(",").length;
		}
		applicants_label.setText("신청자: " + applicants + " 명");
		textArea.setText(post.getContents());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == back_btn) {
			dispose();
		} else if (e.getSource() == update_btn) {
			Singleton.getInstance().getOrderCtrl().updatePostView(dto);
			dispose();
		} else if (e.getSource() == del_btn) {
			if (del_btn.getName().equals("del")) {
				Singleton.getInstance().getComm().SendMessage(Communicator.DELETE, dto);
				this.dispose();
				Singleton.getInstance().showMainView();
			} else {
				if(dto.getApplicants() == null) {
					dto.setApplicants(Singleton.getInstance().getMemCtrl().getCurrentUser().getId()); 
				}
				else {
					String myId = Singleton.getInstance().getMemCtrl().getCurrentUser().getId();
					
					dto.setApplicants(dto.getApplicants() + "," + myId);
				}
				Singleton.getInstance().getComm().SendMessage(Communicator.UPDATE,dto);
				setPost(dto);
				del_btn.setVisible(false);
			}
		}
		if(e.getSource() == userchk_btn) {
			String sel = Singleton.getInstance().getOrderCtrl().getSelDeliverView(dto.getApplicants());
			JOptionPane.showMessageDialog(null, sel);
			if(sel == null || sel.equals("")) {
				return;
			}else {
				applicants_label.setText(sel + "님 "+ "수락대기중");
			}
		}
		

	}
}

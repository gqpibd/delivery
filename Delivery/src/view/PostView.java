package view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import java.awt.GridLayout;

public class PostView extends JFrame {

	public PostView() {
		getContentPane().setLayout(null);
		
		JLabel title_label = new JLabel("제목");
		title_label.setBounds(153, 6, 183, 32);
		getContentPane().add(title_label);
		
		JLabel address_label = new JLabel("지역 :");
		address_label.setBounds(30, 50, 127, 16);
		getContentPane().add(address_label);
		
		JLabel user_label = new JLabel("작성자 :");
		user_label.setBounds(30, 82, 127, 16);
		getContentPane().add(user_label);
		
		JLabel status_label = new JLabel("상태 :");
		status_label.setBounds(300, 50, 135, 16);
		getContentPane().add(status_label);
		
		JLabel date_label = new JLabel("요청기한 :");
		date_label.setBounds(241, 82, 194, 16);
		getContentPane().add(date_label);
		
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBounds(34, 140, 414, 267);
		getContentPane().add(textArea);
		
		JPanel panel = new JPanel();
		panel.setBounds(6, 420, 468, 52);
		getContentPane().add(panel);
		panel.setLayout(new GridLayout(1, 0, 0, 0));
		
		JButton back_btn = new JButton("뒤로");
		panel.add(back_btn);
		
		JButton userchk_btn = new JButton("신청자 확인");
		panel.add(userchk_btn);
		
		JButton update_btn = new JButton("수정");
		panel.add(update_btn);
		
		JButton del_btn = new JButton("삭제");
		panel.add(del_btn);
		
		
		setBounds(0, 0, 480, 500);
		setVisible(true);
	}
}

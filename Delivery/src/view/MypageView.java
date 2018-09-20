package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.function.Consumer;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;

import communicator.Communicator;
import dto.ConsumerDto;
import dto.MemberDto;
import singleton.Singleton;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFrame;

public class MypageView extends JPanel implements ActionListener{
	private JTextField id_textField;
	private JTextField name_textField;
	private JTextField addr_textField;
	private JTextField addr_ch_textField;
	private JPasswordField PWD_textField;
	private JPasswordField PWD_CH_textField;
	
	JButton change_btn;
	JButton exit_btn;
	private JTextField textField;
	private JButton Search_btn;

	ConsumerDto dto = (ConsumerDto)Singleton.getInstance().getMemCtrl().getCurrentUser();
	
	public MypageView() {
		// 하단 패널
		
		setSize(480, 487);
		setLayout(null);
		
		JLabel title_label = new JLabel("My Page");
		title_label.setHorizontalAlignment(SwingConstants.CENTER);
		title_label.setBounds(0, 6, 480, 82);
		add(title_label);
		
		JLabel id_label = new JLabel("아이디 : ");
		id_label.setBounds(41, 100, 106, 29);
		add(id_label);
		
		id_textField = new JTextField(dto.getId());
		id_textField.setEditable(false);
		id_textField.setBounds(170, 100, 213, 29);
		add(id_textField);
		id_textField.setColumns(10);
		
		JLabel name_label = new JLabel("이름 : ");
		name_label.setBounds(41, 139, 106, 25);
		add(name_label);
		
		name_textField = new JTextField(dto.getName());
		name_textField.setEditable(false);
		name_textField.setColumns(10);
		name_textField.setBounds(170, 137, 213, 29);
		add(name_textField);
		
		JLabel addr_label = new JLabel("주소 : ");
		addr_label.setBounds(41, 183, 106, 46);
		add(addr_label);
		
		addr_textField = new JTextField(dto.getAddress());
		addr_textField.setColumns(10);
		addr_textField.setEditable(false);
		addr_textField.setBounds(170, 183, 213, 29);
		add(addr_textField);
		
		addr_ch_textField = new JTextField();
		addr_ch_textField.setColumns(10);
		addr_ch_textField.setEditable(false);
		addr_ch_textField.setBounds(170, 215, 213, 29);
		add(addr_ch_textField);
		
		JLabel PW1_label = new JLabel("비밀번호 : ");
		PW1_label.setBounds(45, 261, 99, 43);
		add(PW1_label);
		
		PWD_textField = new JPasswordField(dto.getPw());
		PWD_textField.setColumns(10);
		PWD_textField.setEditable(false);
		PWD_textField.setBounds(170, 268, 213, 29);
		add(PWD_textField);
		
		JLabel PW2_label = new JLabel("비밀번호 확인: ");
		PW2_label.setBounds(45, 310, 82, 25);
		add(PW2_label);
		
		PWD_CH_textField = new JPasswordField(dto.getPw());
		PWD_CH_textField.setColumns(10);
		PWD_CH_textField.setEditable(false);
		PWD_CH_textField.setBounds(170, 309, 213, 26);
		add(PWD_CH_textField);
		
		change_btn = new JButton("정보수정");
		change_btn.setBounds(146, 382, 117, 29);
		add(change_btn);
		change_btn.setName("change");
		change_btn.addActionListener(this);
		
		exit_btn = new JButton("종료");
		exit_btn.setBounds(279, 382, 117, 29);
		add(exit_btn);
		exit_btn.addActionListener(this);
		
		Search_btn = new JButton("검색");
		Search_btn.setBounds(395, 203, 68, 41);
		Search_btn.setEnabled(false);
		add(Search_btn);
		Search_btn.addActionListener(this);
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == exit_btn) {
			System.exit(0);
		}
		
		if(e.getSource() == change_btn) {
			if(change_btn.getName().equals("change")) {				
				change_btn.setName("complete");
				change_btn.setText("완료");	
				addr_ch_textField.setEditable(true);
				PWD_textField.setEditable(true);
				PWD_CH_textField.setEditable(true);
				Search_btn.setEnabled(true);
				
			}else {
				change_btn.setName("change");
				change_btn.setText("정보수정");
				
				String pw1 = new String(PWD_textField.getPassword());
				String pw2 = new String(PWD_CH_textField.getPassword());
				
				if(!pw1.equals(pw2)) {
					JOptionPane.showMessageDialog(null, "패스워드가 일치하지 않습니다");
					return;
				}
				
				addr_ch_textField.setEditable(false);
				PWD_textField.setEditable(false);
				PWD_CH_textField.setEditable(false);
				Search_btn.setEnabled(false);
				
				dto.setAddress(addr_textField.getText() + " " + addr_ch_textField.getText());
				dto.setPw(new String (PWD_textField.getPassword()));
				
				Singleton.getInstance().getComm().SendMessage(Communicator.UPDATE, dto);
				JOptionPane.showMessageDialog(null, "수정완료");
			}
				
		}
		if(e.getSource() == Search_btn) {
			SelectAddressDialog SAD = new SelectAddressDialog();
			addr_textField.setText(SAD.getAddress());
			addr_ch_textField.setText(SAD.getDetailAddress());
		}
			
			
			
		
	}
}
package view;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class OrderWriteView extends JFrame {
	private JTextField title_texF;
	private JTextField money_textF;
	
	private JLabel title_label;
	private JLabel addr_label;
	private JLabel cata_label;
	private JLabel content_label;
	private JLabel money_label;
	
	private JComboBox addr_combobox;	
	private JComboBox comboBox;
	
	private JTextArea content_textA;
	

	public OrderWriteView() {
		setTitle("게시글 작성");
		getContentPane().setLayout(null);
		
		title_texF = new JTextField();
		title_texF.setBounds(88, 32, 258, 26);
		getContentPane().add(title_texF);
		title_texF.setColumns(10);
		
		title_label = new JLabel("제목 :");
		title_label.setHorizontalAlignment(SwingConstants.RIGHT);
		title_label.setBounds(50, 37, 36, 16);
		getContentPane().add(title_label);
		
		addr_label = new JLabel("주소 :");
		addr_label.setHorizontalAlignment(SwingConstants.RIGHT);
		addr_label.setBounds(50, 76, 36, 16);
		getContentPane().add(addr_label);
		
		addr_combobox = new JComboBox();	// 주소 콤보박
		addr_combobox.setBounds(88, 72, 129, 27);
		getContentPane().add(addr_combobox);
		
		cata_label = new JLabel("카테고리 :");
		cata_label.setHorizontalAlignment(SwingConstants.RIGHT);
		cata_label.setBounds(15, 122, 71, 16);
		getContentPane().add(cata_label);
		
		comboBox = new JComboBox();	// 카테고리 콤보박
		comboBox.setBounds(88, 118, 129, 27);
		getContentPane().add(comboBox);
		
		content_label = new JLabel("내용");
		content_label.setBounds(35, 162, 61, 16);
		getContentPane().add(content_label);
		
		content_textA = new JTextArea();
		content_textA.setLineWrap(true);
		content_textA.setBounds(45, 190, 301, 156);
		getContentPane().add(content_textA);
		
		money_label = new JLabel("금액 :");
		money_label.setBounds(15, 378, 61, 16);
		getContentPane().add(money_label);
		
		money_textF = new JTextField();
		money_textF.setBounds(50, 374, 91, 26);
		getContentPane().add(money_textF);
		money_textF.setColumns(10);
		
		JButton chk_btn = new JButton("확인");
		chk_btn.setBounds(263, 373, 117, 29);
		getContentPane().add(chk_btn);
		
		
		
		
		setVisible(true);
		setBounds(0, 0, 400, 450);
	}
}

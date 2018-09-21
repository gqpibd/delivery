package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import javax.swing.SwingConstants;

import communicator.Communicator;
import dto.OrderDto;
import singleton.Singleton;

public class OrderWriteView extends JFrame implements ActionListener{
	private JTextField title_texF;
	private JTextField money_textF;
	
	private JLabel title_label;
	private JLabel addr_label;
	private JLabel cata_label;
	private JLabel content_label;
	private JLabel money_label;
	private JComboBox<String> addr_combobox;	
	
	private JTextArea content_textA;
	
	JButton chk_btn;
	JButton back_btn;
	
	JLabel writer_label;
	OrderDto dto;
	boolean isUpdate=false;
	
	public OrderWriteView(OrderDto dto) {
		this();
		this.dto = dto;
		setfield();
		isUpdate=true;
	}

	private void setfield() {
		title_texF.setText(dto.getTitle());
		writer_label.setText(dto.getConsumerId());
		addr_combobox.setSelectedItem(dto.getLocation());
		money_textF.setText(dto.getPrice() + "");
		content_textA.setText(dto.getContents());
	}

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
		
		addr_combobox = new JComboBox<>();	// 주소 콤보박
		addr_combobox.setBounds(88, 72, 129, 27);
		getContentPane().add(addr_combobox);
		additem();
		

		cata_label = new JLabel("작성자 :");
		cata_label.setHorizontalAlignment(SwingConstants.RIGHT);
		cata_label.setBounds(34, 122, 52, 16);

		getContentPane().add(cata_label);
		
		content_label = new JLabel("내용");
		content_label.setBounds(35, 162, 61, 16);
		getContentPane().add(content_label);
				
		content_textA = new JTextArea();
		content_textA.setLineWrap(true);
		content_textA.setBounds(45, 190, 301, 156);
				
		JScrollPane scrollPane = new JScrollPane(content_textA);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(34, 184, 317, 182);
		getContentPane().add(scrollPane); // 그 주소 선택하는거 어떻게하는거에요 ?
		
		money_label = new JLabel("금액 :");
		money_label.setBounds(15, 378, 61, 16);
		getContentPane().add(money_label);
		
		money_textF = new JTextField();
		money_textF.setBounds(50, 374, 91, 26);
		getContentPane().add(money_textF);
		money_textF.setColumns(10);
		
		chk_btn = new JButton("확인");
		chk_btn.setBounds(263, 373, 117, 29);
		getContentPane().add(chk_btn);
		chk_btn.addActionListener(this);
		
		back_btn = new JButton("취소");
		back_btn.setBounds(148, 373, 117, 29);
		getContentPane().add(back_btn);
		back_btn.addActionListener(this);
		
		writer_label = new JLabel(Singleton.getInstance().getMemCtrl().getCurrentUser().getId());
		writer_label.setBounds(88, 122, 258, 16);
		getContentPane().add(writer_label);
		
		setVisible(true);
		setBounds(0, 0, 400, 450);
	}
	
	public void additem() {
		Communicator comm = Singleton.getInstance().getComm();
		comm.SendMessage(Communicator.SELECT_GU, "");
		ArrayList<String> results = (ArrayList<String>) comm.receiveObject();
		String resArr[] = new String[results.size()];
		for (int i = 0; i < resArr.length; i++) {
			resArr[i] = results.get(i).toString();
			
			addr_combobox.addItem(resArr[i]);
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == chk_btn) {
			String title =title_texF.getText().trim();
			String content = content_textA.getText().trim();
			int money  = 0;
			if(content.equals("") || title.equals("")) {
				JOptionPane.showMessageDialog(null, "입력을 제대로 해주세요!!");
				return;
			}
			try{
				money = Integer.parseInt(money_textF.getText());
			}catch (NumberFormatException e1) {
				JOptionPane.showMessageDialog(null, "정확한 금액을 입력해주세요!! ");
				return;
			}
			
			OrderDto od = new OrderDto();
			od.setTitle(title);
			od.setLocation((String)addr_combobox.getSelectedItem());
			od.setConsumerId(writer_label.getText());
			od.setPrice(money);
			
			
			if(isUpdate) {
				dto.setTitle(title);
				dto.setContents(content);
				dto.setPrice(money);
				dto.setLocation((String)addr_combobox.getSelectedItem());
				
				Singleton.getInstance().getOrderCtrl().updatePost(dto);
				
			}else {
				Singleton.getInstance().getOrderCtrl().addPost(od);
			}				
			Singleton.getInstance().getOrderCtrl().backToMain(this);
			
		}else if(e.getSource() == back_btn) {
			Singleton.getInstance().showMainView();
			dispose();
		}
		
	}
}

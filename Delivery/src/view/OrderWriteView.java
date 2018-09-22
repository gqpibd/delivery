package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import dto.ConsumerDto;
import dto.OrderDto;
import singleton.Singleton;
import utils.images.LabelEventListener;

public class OrderWriteView extends JFrame implements ActionListener{

	private JLabel topLabel;
	private JLabel title_label;
	private JLabel loc_label;
	private JLabel cata_label;
	private JLabel money_label;
	private JLabel writer_label;

	private JTextField title_texF;
	private JTextField money_textF;
	private JTextField address_field;
	private JTextField address_detail_field;
	private JTextArea content_textA;
	
	private JLabel chk_btn;
	private JLabel back_btn;
	private JLabel search_btn;
	
	private OrderDto dto;
	private ConsumerDto Writer;
	private boolean isUpdate=false;	
	
	public static final String PATH = "orderWrite/";
	
	public OrderWriteView(OrderDto dto) {
		this();
		this.dto = dto;
		isUpdate=true;
		setfield();
	}

	private void setfield() {
		title_texF.setText(dto.getTitle());
		writer_label.setText(dto.getConsumerId());
		money_textF.setText(dto.getPrice() + "");
		content_textA.setText(dto.getContents());
		String address = "";
		if(isUpdate) { // 새 글 작성할 때는 작성자 주소를 넣어준다.
			System.out.println("update");
			address = dto.getAddress();			
		}else {
			System.out.println("new");
			address = Writer.getAddress();			
		}
		
		int loc=0;
		if (address.contains(")")) {
			loc = address.indexOf(")");
			address_field.setText(address.substring(0, loc + 1));	
		} else {
			loc = address.indexOf(' ');
			loc = address.indexOf(' ', loc + 1);
			loc = address.indexOf(' ', loc + 1) - 1;
		}
		if (loc + 2 < address.length()) {
			address_detail_field.setText(address.substring(loc + 2));
		}
	}

	public OrderWriteView() {
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setFont(new Font("나눔스퀘어", Font.PLAIN, 14));
		setTitle("게시글 작성");
		getContentPane().setLayout(null);
		setSize(386, 456);
		setLocationRelativeTo(null);
		
		Writer = (ConsumerDto) Singleton.getInstance().getMemCtrl().getCurrentUser();
		
		title_texF = new JTextField();
		title_texF.setFont(new Font("나눔스퀘어", Font.PLAIN, 14));
		title_texF.setBounds(73, 48, 260, 26);
		getContentPane().add(title_texF);
		title_texF.setColumns(10);
		
		title_label = new JLabel("제목");
		title_label.setFont(new Font("나눔스퀘어", Font.BOLD, 14));
		title_label.setHorizontalAlignment(SwingConstants.RIGHT);
		title_label.setBounds(15, 52, 46, 16);
		getContentPane().add(title_label);
		
		loc_label = new JLabel("주소");
		loc_label.setFont(new Font("나눔스퀘어", Font.BOLD, 14));
		loc_label.setHorizontalAlignment(SwingConstants.RIGHT);
		loc_label.setBounds(15, 93, 46, 16);
		getContentPane().add(loc_label);
		//additem();
		
		address_field = new JTextField(10);
		address_field.setFont(new Font("나눔스퀘어", Font.PLAIN, 14));
		address_field.setEditable(false);
		address_field.setBounds(73, 88, 260, 26);
		getContentPane().add(address_field);

		address_detail_field = new JTextField(10);
		address_detail_field.setFont(new Font("나눔스퀘어", Font.PLAIN, 14));
		address_detail_field.setBounds(73, 124, 158, 26);
		getContentPane().add(address_detail_field);
		
		
		cata_label = new JLabel("작성자 : ");
		cata_label.setFont(new Font("나눔스퀘어", Font.BOLD, 14));
		cata_label.setHorizontalAlignment(SwingConstants.RIGHT);
		cata_label.setBounds(15, 165, 58, 16);

		getContentPane().add(cata_label);
				
		content_textA = new JTextArea();
		content_textA.setLineWrap(true);
		content_textA.setBounds(45, 190, 301, 156);
				
		JScrollPane scrollPane = new JScrollPane(content_textA);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(27, 204, 324, 162);
		getContentPane().add(scrollPane); // 그 주소 선택하는거 어떻게하는거에요 ?
		
		money_label = new JLabel("금액");
		money_label.setFont(new Font("나눔스퀘어", Font.BOLD, 14));
		money_label.setBounds(15, 382, 61, 16);
		getContentPane().add(money_label);
		
		money_textF = new JTextField();
		money_textF.setFont(new Font("나눔스퀘어", Font.PLAIN, 14));
		money_textF.setBounds(54, 377, 91, 26);
		getContentPane().add(money_textF);
		money_textF.setColumns(10);
		
		chk_btn = new JLabel( new ImageIcon(getClass().getClassLoader().getResource(PATH + "confirm.png")));
		chk_btn.setName(PATH + "confirm.png");
		chk_btn.addMouseListener(new LabelEventListener(this, chk_btn));
		chk_btn.setBounds(177, 375, 88, 30);
		getContentPane().add(chk_btn);
		
		back_btn = new JLabel(new ImageIcon(getClass().getClassLoader().getResource(PATH + "cancel.png")));
		back_btn.setName(PATH + "cancel.png");
		back_btn.addMouseListener(new LabelEventListener(this, back_btn));
		back_btn.setBounds(270, 375, 88, 30);
		getContentPane().add(back_btn);
		
		writer_label = new JLabel(Singleton.getInstance().getMemCtrl().getCurrentUser().getId());
		writer_label.setFont(new Font("나눔스퀘어", Font.BOLD, 14));
		writer_label.setBounds(91, 165, 258, 16);
		getContentPane().add(writer_label);
		
		topLabel = new JLabel("주문서 작성");
		topLabel.setHorizontalAlignment(SwingConstants.CENTER);
		topLabel.setFont(new Font("나눔스퀘어", Font.BOLD, 15));
		topLabel.setBounds(0, 10, 370, 15);
		getContentPane().add(topLabel);
		
		search_btn = new JLabel(new ImageIcon(getClass().getClassLoader().getResource(PATH + "search.png")));
		search_btn.setName(PATH + "search.png");
		search_btn.addMouseListener(new LabelEventListener(this, search_btn));
		search_btn.setBounds(252, 122, 88, 30);
		getContentPane().add(search_btn);
		
		JLabel label = new JLabel("원");
		label.setFont(new Font("나눔스퀘어", Font.BOLD, 14));
		label.setBounds(151, 382, 36, 16);
		getContentPane().add(label);
		

		setVisible(true);
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
			od.setLocation(address_field.getText().split(" ")[1]);
			od.setConsumerId(writer_label.getText());
			od.setPrice(money);
			od.setContents(content);
			od.setAddress(address_field.getText() + " " + address_detail_field.getText());
			
			
			if(isUpdate) {
				dto.setTitle(title);
				dto.setContents(content);
				dto.setPrice(money);
				dto.setLocation(address_field.getText().split(" ")[1]);
				dto.setAddress(address_field.getText() + " " + address_detail_field.getText());
				
				Singleton.getInstance().getOrderCtrl().updatePost(dto);
				
			}else {
				Singleton.getInstance().getOrderCtrl().addPost(od);
			}				
			Singleton.getInstance().getOrderCtrl().backToMain(this);
			
		}else if(e.getSource() == back_btn) {
			Singleton.getInstance().showMainView();
			dispose();
		} else if (e.getSource() == search_btn) { // 주소 검색
			SelectAddressDialog add = new SelectAddressDialog();
			address_field.setText(add.getAddress());
			address_detail_field.setText(add.getDetailAddress());
		}
		
	}
}

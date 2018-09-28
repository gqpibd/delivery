package view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicBorders.RadioButtonBorder;

import communicator.Communicator;
import dto.OrderDto;
import oracle.net.aso.a;
import singleton.Singleton;
import utils.images.ImageUtils;
import utils.images.LabelEventListener;

import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Group;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;

public class ReviewWriteDialog extends JFrame implements ActionListener{

	public final String PATH = "review/"; //image file folder path
	int myScore;
	
	JLabel chk_btn;
	JLabel back_btn;
	
	int score;
	
	JLabel score_label;
	
	PostView parent;
	OrderDto orderDetail;
	
	public ReviewWriteDialog(OrderDto orderDetail, PostView parent) {
		getContentPane().setBackground(Color.WHITE);
		
		this.parent = parent;
		this.orderDetail = orderDetail;
		
		getContentPane().setLayout(null);
		
		JLabel title_label = new JLabel("만족도 평가");
		title_label.setFont(new Font("나눔스퀘어", Font.BOLD, 16));
		title_label.setHorizontalAlignment(SwingConstants.CENTER);
		title_label.setBounds(0, 6, 338, 54);
		getContentPane().add(title_label);
				
		JRadioButton radio_1 = new JRadioButton("1");
		radio_1.setBackground(Color.WHITE);
		radio_1.setFont(new Font("나눔스퀘어", Font.PLAIN, 14));
		radio_1.setBounds(10, 81, 56, 23);
		getContentPane().add(radio_1);
		radio_1.addActionListener(this);
		
		JRadioButton radio_2 = new JRadioButton("2");
		radio_2.setBackground(Color.WHITE);
		radio_2.setFont(new Font("나눔스퀘어", Font.PLAIN, 14));
		radio_2.setBounds(78, 81, 56, 23);
		getContentPane().add(radio_2);
		radio_2.addActionListener(this);
		
		JRadioButton radio_3 = new JRadioButton("3");
		radio_3.setBackground(Color.WHITE);
		radio_3.setFont(new Font("나눔스퀘어", Font.PLAIN, 14));
		radio_3.setBounds(146, 81, 56, 23);
		getContentPane().add(radio_3);
		radio_3.addActionListener(this);
		
		JRadioButton radio_4 = new JRadioButton("4");
		radio_4.setBackground(Color.WHITE);
		radio_4.setFont(new Font("나눔스퀘어", Font.PLAIN, 14));
		radio_4.setBounds(214, 81, 56, 23);
		getContentPane().add(radio_4);
		radio_4.addActionListener(this);
		
		JRadioButton radio_5 = new JRadioButton("5");
		radio_5.setBackground(Color.WHITE);
		radio_5.setFont(new Font("나눔스퀘어", Font.PLAIN, 14));
		radio_5.setSelected(true);
		radio_5.setBounds(282, 81, 56, 23);
		getContentPane().add(radio_5);
		radio_5.addActionListener(this);
		
		ButtonGroup group = new ButtonGroup();
		group.add(radio_1);
		group.add(radio_2);
		group.add(radio_3);
		group.add(radio_4);
		group.add(radio_5);
		
		chk_btn = new JLabel( new ImageIcon(getClass().getClassLoader().getResource(PATH + "confirm.png")));
		chk_btn.setName(PATH + "confirm.png");
		chk_btn.addMouseListener(new LabelEventListener(this, chk_btn));
		chk_btn.setBounds(193, 158, 88, 30);
		getContentPane().add(chk_btn);
		
		back_btn = new JLabel(new ImageIcon(getClass().getClassLoader().getResource(PATH + "cancel.png")));
		back_btn.setName(PATH + "cancel.png");
		back_btn.addMouseListener(new LabelEventListener(this, back_btn));
		back_btn.setBounds(38, 158, 88, 30);
		getContentPane().add(back_btn);		
		
		score_label = new JLabel();
		score_label.setBounds(33, 116, 30*5, 30);
		ImageIcon star = new ImageIcon(getClass().getClassLoader().getResource(PATH + "5.png"));
		score = 5;
		ImageUtils.setResizedImage(score_label, star);
		getContentPane().add(score_label);
		
		setSize(350, 250);
		setLocationRelativeTo(null);
		setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() instanceof JRadioButton) {
			score = Integer.parseInt(((JRadioButton) e.getSource()).getText());
			System.out.println(score);
			score_label.setSize(score*30, 30);
			ImageIcon star = new ImageIcon(getClass().getClassLoader().getResource(PATH + score + ".png"));
			ImageUtils.setResizedImage(score_label, star);
		}
		
		if(e.getSource() == back_btn) {
			dispose();
		}
		if(e.getSource() == chk_btn) {
			parent.setComlete();
			orderDetail.setScore(score);
			Singleton.getInstance().getOrderCtrl().updatePost(orderDetail);
			dispose();
		}
		
	}
	
}

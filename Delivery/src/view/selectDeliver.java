package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import singleton.Singleton;
import utils.images.LabelEventListener;

public class selectDeliver extends JFrame implements ActionListener{
	private JList list;
	private String app[];
	private String Deliver;
	
	private JLabel cancel_btn;
	private JLabel sel_btn;
	
	private JPanel profile_panel;
	private JLabel label;
	PostView parentPost;
	
	private static final String PATH = "selDeliverer/";
	
	public selectDeliver(PostView parentPost, String applicants) {
		this.parentPost = parentPost;
		getContentPane().setBackground(Color.WHITE);
		//setModal(true);
		setTitle("지원자 목록");
		getContentPane().setLayout(null);
		if(applicants==null) {
			applicants="";
		}
		System.out.println(applicants);
		app = applicants.split(",");
		JLabel lblNewLabel = new JLabel("대신맨 선택");
		lblNewLabel.setFont(new Font("나눔스퀘어", Font.BOLD, 16));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(12, 10, 171, 29);
		getContentPane().add(lblNewLabel);
		
		
		profile_panel = new JPanel();
		profile_panel.setBackground(Color.DARK_GRAY);
		profile_panel.setBorder(new LineBorder(Color.DARK_GRAY, 1, true));
		profile_panel.setBounds(195, 50, 401, 369);
		getContentPane().add(profile_panel);

		
		list = new JList(app);
		list.setFont(new Font("나눔스퀘어", Font.PLAIN, 15));
		list.setBounds(23, 50, 356, 273);
		list.addListSelectionListener(new ListSelectionListener() {			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				System.out.println("클릭");
				profile_panel.removeAll();
				profile_panel.add(new ProfilePanel((String)list.getSelectedValue()));
				profile_panel.setBorder(new LineBorder(Color.DARK_GRAY, 1, true));
				profile_panel.setSize(401, 369);
				repaint();				
			}
		});
		
		JScrollPane scrollPane = new JScrollPane(list);
		scrollPane.setBounds(12, 50, 171, 337);
		getContentPane().add(scrollPane);
		cancel_btn = new JLabel( new ImageIcon(getClass().getClassLoader().getResource(PATH + "cancel.png")));
		cancel_btn.setName(PATH + "cancel.png");
		cancel_btn.addMouseListener(new LabelEventListener(this,cancel_btn));
		cancel_btn.setBounds(109, 397, 75, 30);
		getContentPane().add(cancel_btn);
		
		sel_btn = new JLabel( new ImageIcon(getClass().getClassLoader().getResource(PATH + "sel.png")));
		sel_btn.setName(PATH + "sel.png");
		sel_btn.addMouseListener(new LabelEventListener(this, sel_btn));
		sel_btn.setBounds(12, 397, 75, 30);
		getContentPane().add(sel_btn);	
		
		label = new JLabel("배달원 정보");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("나눔스퀘어", Font.BOLD, 16));
		label.setBounds(195, 10, 401, 29);
		getContentPane().add(label);
		
		setSize(622, 475);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == cancel_btn) {
			dispose();
		}else if (e.getSource() == sel_btn) {
			Deliver = (String)list.getSelectedValue();
			parentPost.setDeliverer(Deliver);
			if(Deliver == null) {
				JOptionPane.showMessageDialog(null,"배달원을 선택해 주세요");
			}
			dispose();
		}
	}
}

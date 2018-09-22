package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import singleton.Singleton;
import java.awt.Font;

public class selectDeliver extends JDialog implements ActionListener{
	JList list;
	String app[];
	String Deliver;
	
	JButton cancel_btn;
	JButton sel_btn;
	
	public selectDeliver(String applicants) {
		setModal(true);
		setTitle("지원자 목록");
		getContentPane().setLayout(null);
		if(applicants==null) {
			applicants="";
		}
		app = applicants.split(",");
		JLabel lblNewLabel = new JLabel("대신맨 선택");
		lblNewLabel.setFont(new Font("08서울남산체 B", Font.PLAIN, 14));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(12, 10, 171, 29);
		getContentPane().add(lblNewLabel);
		
		list = new JList(app);
		list.setBounds(23, 50, 356, 273);
		list.addListSelectionListener(new ListSelectionListener() {			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				Singleton.getInstance().getMemCtrl().showDelivererProfile((String)list.getSelectedValue());
				
			}
		});
		
		JScrollPane scrollPane = new JScrollPane(list);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(12, 50, 171, 173);
		getContentPane().add(scrollPane);
				
		cancel_btn = new JButton("취소");
		cancel_btn.setBounds(109, 233, 74, 29);
		getContentPane().add(cancel_btn);
		cancel_btn.addActionListener(this);
		
		sel_btn = new JButton("선택");
		sel_btn.setBounds(12, 233, 74, 29);
		getContentPane().add(sel_btn);	
		sel_btn.addActionListener(this);
						
		setSize(213, 316);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public String selDeliver() {		
		return Deliver;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == cancel_btn) {
			dispose();
		}else if (e.getSource() == sel_btn) {
			Deliver = (String)list.getSelectedValue();
			if(Deliver == null) {
				JOptionPane.showMessageDialog(null,"배달원을 선택해 주세요");
			}
			dispose();
		}
		
		
	}
	
	
	
	
}

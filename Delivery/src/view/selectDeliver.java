package view;

import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.ScrollPaneConstants;

public class selectDeliver extends JDialog {
	
	JList<String> list;
	String app[];
	String Deliver;
	
	
	public selectDeliver(String applicants) {
		setModal(true);
		getContentPane().setLayout(null);
		
		app = applicants.split(",");
		
		JLabel lblNewLabel = new JLabel("대신맨 선택");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 22, 400, 16);
		getContentPane().add(lblNewLabel);
		
		list = new JList<String>(app);
		list.setBounds(23, 50, 356, 273);
		getContentPane().add(list);
		list.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		JScrollPane scrollPane = new JScrollPane(list);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(23, 50, 356, 273);
		getContentPane().add(scrollPane);
				
		JButton cancel_btn = new JButton("취소");
		cancel_btn.setBounds(158, 335, 117, 29);
		getContentPane().add(cancel_btn);
		cancel_btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				
			}
		});
		
		
		JButton sel_btn = new JButton("확인");
		sel_btn.setBounds(277, 335, 117, 29);
		getContentPane().add(sel_btn);	
		sel_btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Deliver = list.getSelectedValue();
				dispose();
				
			}
		});
						
		setBounds(0, 0, 200, 200);
		setVisible(true);
	}
	
	public String selDeliver() {		
		return Deliver;
	}
	
	
	
	
}

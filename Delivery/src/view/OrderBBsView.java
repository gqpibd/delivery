package view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class OrderBBsView extends JFrame {
	private JTable table;
	
	public OrderBBsView() {
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(6, 6, 468, 118);
		getContentPane().add(panel);
		panel.setLayout(new GridLayout(0, 3, 0, 0));
		
		JButton btnNewButton = new JButton("배달요청");
		panel.add(btnNewButton);
		
		JButton button = new JButton("사용요청 확인");
		panel.add(button);
		
		JButton button_1 = new JButton("마이 페이지");
		panel.add(button_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(6, 125, 468, 487);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(6, 6, 456, 434);
		panel_1.add(scrollPane);
		
		table = new JTable();
		scrollPane.setColumnHeaderView(table);
		
		JButton button_2 = new JButton("종료");
		button_2.setBounds(345, 444, 117, 37);
		panel_1.add(button_2);
		
		JButton button_3 = new JButton("로그아웃");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button_3.setBounds(217, 444, 128, 37);
		panel_1.add(button_3);
		
		
		
		
		setBounds(0, 0, 480, 640);
		setVisible(true);
		
	}
}

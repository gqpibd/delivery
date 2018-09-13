package view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;

public class OrderBBsView extends JFrame {
	
	private JTable table;
	String columnNames[] = {"번호", "상태", "제목", "지역", "작성자", "날짜"};
	Object rowData[][] = {{"1","대기중","도미노피자 배달해주세요","구의동","김영곤","2018-09-13"}};
	DefaultTableModel model;
	//what is the problem? all
	
	public OrderBBsView() {
		super("대신해드려요!!");
		getContentPane().setLayout(null);
		
		// 상단 패널 
		
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
		
		// 하단 패널 
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(6, 125, 468, 487);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);

		
		// 테이블의 폭을 설정하기 위한 Model good but
		model = new DefaultTableModel(rowData, columnNames);
		
		
		
		table = new JTable(model);
		
		table.getColumnModel().getColumn(0).setMaxWidth(30);	// 글번호 폭
		table.getColumnModel().getColumn(1).setMaxWidth(50);	// 상태 폭
		table.getColumnModel().getColumn(2).setMaxWidth(200);	// 제목 폭
		table.getColumnModel().getColumn(3).setMaxWidth(50);	// 지역 폭
		table.getColumnModel().getColumn(4).setMaxWidth(80);	// 작성자 폭
		table.getColumnModel().getColumn(5).setMaxWidth(100);	// 날짜 폭
		
		DefaultTableCellRenderer celAlignCenter = new DefaultTableCellRenderer();
		celAlignCenter.setHorizontalAlignment(JLabel.CENTER);	
		
		table.getColumn("번호").setCellRenderer(celAlignCenter);
		table.getColumn("상태").setCellRenderer(celAlignCenter);
		table.getColumn("지역").setCellRenderer(celAlignCenter);
		table.getColumn("작성자").setCellRenderer(celAlignCenter);
		table.getColumn("날짜").setCellRenderer(celAlignCenter);
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				int rowNum = table.getSelectedRow();	
				// 로우 번호 빼고 일단 다 지워놀게/  디테일뷰 만들어요 
			}
		});
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(6, 6, 456, 434);
		panel_1.add(scrollPane);
		
		// button
		
		JButton button_2 = new JButton("종료");
		button_2.setBounds(345, 444, 117, 37);
		panel_1.add(button_2);
		
		JButton button_3 = new JButton("로그아웃");
		button_3.setBounds(217, 444, 128, 37);
		panel_1.add(button_3);
		
		
		
		
		setBounds(0, 0, 480, 640);
		setVisible(true);
		
	}
}

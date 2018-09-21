package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
<<<<<<< HEAD
=======
import java.util.ArrayList;
import java.util.List;

>>>>>>> refs/remotes/origin/h2gon
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import dto.OrderBBsDto;
import singleton.Singleton;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;

public class MyOrdersView extends JPanel implements ActionListener{
	private JTable table;
	private String columnNames[] = { "번호", "날짜", "제목", "상태"};
	private Object rowData[][];
	private DefaultTableModel model;
	
	public MyOrdersView() {
		
		// 하단 패널
		
		setLayout(null);
		setSize(480, 487);
<<<<<<< HEAD
		JLabel lblNewLabel = new JLabel("내 주문 내역");
		lblNewLabel.setBounds(12, 10, 57, 15);
		add(lblNewLabel);
=======
		List<OrderBBsDto> list = Singleton.getInstance().getOrderCtrl().getOderList();
		setRowData(list);
		System.out.println(list);
		
		JLabel title_label = new JLabel( Singleton.getInstance().getMemCtrl().getCurrentUser().getId() + " 님의 주문내역");
		title_label.setHorizontalAlignment(SwingConstants.CENTER);
		title_label.setBounds(0, 25, 480, 58);
		add(title_label);
		
		
		
		model = new DefaultTableModel(rowData, columnNames) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
>>>>>>> refs/remotes/origin/h2gon

		
		table = new JTable(model);
		
		setTable();
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(19, 110, 443, 309);
		add(scrollPane);
		
		
		
		
		
		JButton back_btn = new JButton("New button");
		back_btn.setBounds(345, 431, 117, 29);
		add(back_btn);

		
		

	}

	@Override
	public void actionPerformed(ActionEvent e) {
	
	}
	
	public void setRowData(List<OrderBBsDto> list) {
		
		rowData = new Object[list.size()][4];
		int count=list.size();
		for (int i = 0; i < list.size(); i++) {
			rowData[i][0] = i +1;
			rowData[i][1] = list.get(i).getDate();
			rowData[i][2] = list.get(i).getTitle();
			rowData[i][3] = list.get(i).getStatus();
				
		}
	}
	
	public void setTable() {
		table.getColumnModel().getColumn(0).setMaxWidth(30); // 글번호 폭
		table.getColumnModel().getColumn(1).setMaxWidth(50); // 상태 폭
		table.getColumnModel().getColumn(2).setMaxWidth(190); // 제목 폭
		table.getColumnModel().getColumn(3).setMaxWidth(50); // 지역 폭

		DefaultTableCellRenderer celAlignCenter = new DefaultTableCellRenderer();
		celAlignCenter.setHorizontalAlignment(JLabel.CENTER);

		table.getColumn("번호").setCellRenderer(celAlignCenter);
		table.getColumn("상태").setCellRenderer(celAlignCenter);
		table.getColumn("날짜").setCellRenderer(celAlignCenter);
		table.getColumn("제목").setCellRenderer(celAlignCenter);
		
	}

}

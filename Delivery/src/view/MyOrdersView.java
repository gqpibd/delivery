package view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import dto.OrderDto;
import singleton.Singleton;

public class MyOrdersView extends JPanel implements ActionListener{
	private JTable table;
	private String columnNames[] = { "번호", "날짜", "제목", "상태"};
	private Object rowData[][];
	private DefaultTableModel model;
	private JLabel total_order;
	private JLabel request_label;
	private JLabel ongoing_label;
	private JLabel complete_label;
	private JButton check_btn;
	List<OrderDto> list;
	
	public MyOrdersView() {
		setLayout(null);
		setSize(MainView.BOTTOM_WIDTH, MainView.BOTTOM_HEIGHT);
		list = Singleton.getInstance().getOrderCtrl().getOderList();
		setRowData(list);
		System.out.println(list);
		
		
		JLabel title_label = new JLabel(Singleton.getInstance().getMemCtrl().getCurrentUser().getId() + " 님의 주문내역");
		title_label.setFont(new Font("나눔고딕 Light", Font.BOLD, 19));
		title_label.setHorizontalAlignment(SwingConstants.CENTER);
		title_label.setBounds(0, 6, 480, 60);
		add(title_label);
		
		model = new DefaultTableModel(rowData, columnNames) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		
		table = new JTable(model);
		
		setTable();
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(19, 129, 443, 299);
		add(scrollPane);
		
		total_order = new JLabel("총 주문 건수 :" + list.size() +"건");
		total_order.setBounds(19, 76, 134, 15);
		add(total_order);
		
		int rq =0; int ing =0; int comp = 0; int wait = 0;
		
		for (int i = 0; i < list.size(); i++) {
			String stat = list.get(i).getStatus();
			if(stat.equals("요청중")) {
				rq++;
			}else if(stat.equals("진행중")) {
				ing++;
			}else if(stat.equals("완료됨")) {
				comp++;
			}
				
		}
		
		request_label = new JLabel("요청중 :" + rq + "건");
		request_label.setBounds(61, 101, 78, 15);
		add(request_label);
		
		ongoing_label = new JLabel("진행중 :" + ing + "건");
		ongoing_label.setBounds(200, 101, 78, 15);
		add(ongoing_label);
		
		complete_label = new JLabel("완료됨 :" + comp +"건");
		complete_label.setBounds(378, 101, 78, 15);
		add(complete_label);
		
		check_btn = new JButton("확인");
		check_btn.setBounds(277, 98, 78, 23);
		add(check_btn);
		check_btn.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == check_btn) {
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i).getStatus().equals("진행중")) {
				Singleton.getInstance().getOrderCtrl().postView(list.get(i).getReqNum());
			}
			}
		}
	}
	
	public void setRowData(List<OrderDto> list) {
		rowData = new Object[list.size()][4];
		String date;
		for (int i = 0; i < list.size(); i++) {
			rowData[i][0] = i + 1;
			rowData[i][1] = list.get(i).getDate().split(" ")[0];
			rowData[i][2] = list.get(i).getTitle();
			rowData[i][3] = list.get(i).getStatus();
				
		}
	}
	
	public void setTable() {
		table.getColumnModel().getColumn(0).setPreferredWidth(30); // 글번호 폭
		table.getColumnModel().getColumn(1).setPreferredWidth(50); // 날짜 폭
		table.getColumnModel().getColumn(2).setPreferredWidth(190); // 제목 폭
		table.getColumnModel().getColumn(3).setPreferredWidth(50); // 상태 폭

		DefaultTableCellRenderer celAlignCenter = new DefaultTableCellRenderer();
		celAlignCenter.setHorizontalAlignment(JLabel.CENTER);

		table.getColumn("번호").setCellRenderer(celAlignCenter);
		table.getColumn("상태").setCellRenderer(celAlignCenter);
		table.getColumn("날짜").setCellRenderer(celAlignCenter);
		table.getColumn("제목").setCellRenderer(celAlignCenter);
		
	}

}

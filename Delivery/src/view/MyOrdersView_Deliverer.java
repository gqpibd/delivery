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

public class MyOrdersView_Deliverer extends JPanel implements ActionListener {

	private JTable table;
	private String columnNames[] = { "번호", "날짜", "제목", "상태" };
	private Object rowData[][];
	private DefaultTableModel model;
	private JLabel total_order;
	private JLabel request_label;
	private JLabel ongoing_label;
	private JLabel complete_label;
	private JLabel waiting_label;
	private JButton check_waiting;
	private JButton check_ongoing;

	public MyOrdersView_Deliverer() {
		setLayout(null);
		setSize(MainView.BOTTOM_WIDTH, MainView.BOTTOM_HEIGHT);
		List<OrderDto> list = Singleton.getInstance().getOrderCtrl().getOderList();
		setRowData(list);
		System.out.println(list);
		
		
		JLabel title_label = new JLabel(Singleton.getInstance().getMemCtrl().getCurrentUser().getId() + " 님의 배달 내역");
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
		
		total_order = new JLabel("총 배달 건수 : 건");
		total_order.setBounds(19, 76, 134, 15);
		add(total_order);
		
		request_label = new JLabel("지원중 : 건");
		request_label.setBounds(19, 101, 78, 15);
		add(request_label);
		
		ongoing_label = new JLabel("진행중 : 건");
		ongoing_label.setBounds(170, 101, 78, 15);
		add(ongoing_label);
		
		complete_label = new JLabel("완료됨 : 건");
		complete_label.setBounds(384, 101, 78, 15);
		add(complete_label);
		
		waiting_label = new JLabel("수락 대기중 : 건");
		waiting_label.setBounds(19, 442, 139, 15);
		add(waiting_label);
		
		check_waiting = new JButton("확인");
		check_waiting.setBounds(170, 438, 78, 23);
		add(check_waiting);
		
		check_ongoing = new JButton("확인");
		check_ongoing.setBounds(260, 97, 78, 23);
		add(check_ongoing);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
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
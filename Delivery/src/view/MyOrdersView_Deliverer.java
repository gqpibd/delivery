package view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import dto.DelivererDto;
import dto.OrderDto;
import singleton.Singleton;
import utils.images.LabelEventListener;

import java.awt.Color;

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
	private JLabel check_waiting;
	private List<OrderDto> list;
	private static final String PATH = "tabs/";

	public MyOrdersView_Deliverer() {
		setBackground(Color.DARK_GRAY);
		setLayout(null);
		DelivererDto user = (DelivererDto) Singleton.getInstance().getMemCtrl().getCurrentUser();
		setSize(MainView.BOTTOM_WIDTH, MainView.BOTTOM_HEIGHT);
		list = Singleton.getInstance().getOrderCtrl().getDeliverList(user.getId());

		int rq = 0;
		int ing = 0;
		int comp = 0;
		int wait = 0;

		for (int i = 0; i < list.size(); i++) {
			String stat = list.get(i).getStatus();
			if (stat.equals("요청중")) {
				rq++;
				String sel = list.get(i).getDelivererId();
				if (sel != null && sel.equals(user.getId())) {
					wait++;
					list.get(i).setStatus("수락대기중");
				}
			} else if (stat.equals("진행중")) {
				ing++;
			} else if (stat.equals("완료됨")) {
				comp++;
			}
		}

		setRowData(list);

		model = new DefaultTableModel(rowData, columnNames) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		table = new JTable(model);
		table.setRowMargin(2);
		table.setRowHeight(20);
		table.setFont(new Font("나눔스퀘어", Font.PLAIN, 14));
		setTable();
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					int rowNum = table.getSelectedRow();
					int index = list.size() - (int) table.getValueAt(rowNum, 0); // 내 주문 목록에서의 번호
					Singleton.getInstance().getOrderCtrl().postView(list.get(index).getReqNum());
				}
			}
		});

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setFont(new Font("나눔스퀘어", Font.PLAIN, 14));
		scrollPane.setBackground(Color.DARK_GRAY);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(18, 91, 443, 337);
		add(scrollPane);

		total_order = new JLabel("총 배달 건수 : " + (ing + comp) + " 건");
		total_order.setFont(new Font("나눔스퀘어", Font.BOLD, 14));
		total_order.setForeground(Color.WHITE);
		total_order.setBounds(19, 22, 134, 15);
		add(total_order);

		request_label = new JLabel("지원중 : " + rq + " 건");
		request_label.setFont(new Font("나눔스퀘어", Font.BOLD, 14));
		request_label.setForeground(Color.WHITE);
		request_label.setBounds(19, 63, 101, 15);
		add(request_label);

		ongoing_label = new JLabel("진행중 : " + ing + " 건");
		ongoing_label.setHorizontalAlignment(SwingConstants.CENTER);
		ongoing_label.setFont(new Font("나눔스퀘어", Font.BOLD, 14));
		ongoing_label.setForeground(Color.WHITE);
		ongoing_label.setBounds(177, 63, 111, 15);
		add(ongoing_label);

		complete_label = new JLabel("완료됨 : " + comp + " 건");
		complete_label.setHorizontalAlignment(SwingConstants.RIGHT);
		complete_label.setFont(new Font("나눔스퀘어", Font.BOLD, 14));
		complete_label.setForeground(Color.WHITE);
		complete_label.setBounds(347, 63, 111, 15);
		add(complete_label);

		waiting_label = new JLabel("수락 대기중 : " + wait + " 건");
		waiting_label.setFont(new Font("나눔스퀘어", Font.BOLD, 14));
		waiting_label.setForeground(Color.WHITE);
		waiting_label.setBounds(19, 442, 134, 15);
		add(waiting_label);

		check_waiting = new JLabel(new ImageIcon(getClass().getClassLoader().getResource(PATH + "confirm.png")));
		check_waiting.setName(PATH + "confirm.png");
		check_waiting.setBounds(157, 438, 78, 23);
		add(check_waiting);
		check_waiting.addMouseListener(new LabelEventListener(this, check_waiting, true));

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == check_waiting) {
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).getStatus().equals("수락대기중")) {
					Singleton.getInstance().getOrderCtrl().postView(list.get(i).getReqNum());
				}
			}
		}
	}

	public void setRowData(List<OrderDto> list) {
		rowData = new Object[list.size()][4];
		int count = list.size();
		for (int i = 0; i < list.size(); i++) {
			rowData[i][0] = count--;
			rowData[i][1] = list.get(i).getDate().split(" ")[0];
			rowData[i][2] = list.get(i).getTitle();
			rowData[i][3] = list.get(i).getStatus();

		}
	}

	public void setTable() {
		table.getColumnModel().getColumn(0).setPreferredWidth(20); // 글번호 폭
		table.getColumnModel().getColumn(1).setPreferredWidth(60); // 날짜 폭
		table.getColumnModel().getColumn(2).setPreferredWidth(180); // 제목 폭
		table.getColumnModel().getColumn(3).setPreferredWidth(50); // 상태 폭

		DefaultTableCellRenderer celAlignCenter = new DefaultTableCellRenderer();
		celAlignCenter.setHorizontalAlignment(JLabel.CENTER);

		table.getColumn("번호").setCellRenderer(celAlignCenter);
		table.getColumn("상태").setCellRenderer(celAlignCenter);
		table.getColumn("날짜").setCellRenderer(celAlignCenter);
		table.getColumn("제목").setCellRenderer(celAlignCenter);

	}

}
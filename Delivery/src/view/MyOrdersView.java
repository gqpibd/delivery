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

import dto.OrderDto;
import singleton.Singleton;
import utils.images.LabelEventListener;

import java.awt.Color;

public class MyOrdersView extends JPanel implements ActionListener {
	private JTable table;
	private String columnNames[] = { "번호", "날짜", "제목", "상태" };
	private Object rowData[][];
	private DefaultTableModel model;
	private JLabel total_order;
	private JLabel request_label;
	private JLabel ongoing_label;
	private JLabel complete_label;
	private JLabel check_btn;
	private List<OrderDto> list;
	private static final String PATH = "tabs/";

	public MyOrdersView() {
		setBackground(Color.DARK_GRAY);
		setLayout(null);
		Font fontStyle = new Font("다음_Regular", Font.PLAIN, 14);
		setFont(fontStyle);

		setSize(MainView.BOTTOM_WIDTH, MainView.BOTTOM_HEIGHT);
		list = Singleton.getInstance().getOrderCtrl().getOderList();
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
		scrollPane.setBounds(19, 99, 443, 350);
		add(scrollPane);

		total_order = new JLabel("총 주문 건수 : " + list.size() + " 건");
		total_order.setFont(new Font("나눔스퀘어", Font.BOLD, 14));
		total_order.setForeground(Color.WHITE);
		total_order.setBounds(19, 27, 134, 15);
		add(total_order);

		int rq = 0;
		int ing = 0;
		int comp = 0;

		for (int i = 0; i < list.size(); i++) {
			String stat = list.get(i).getStatus();
			if (stat.equals("요청중")) {
				rq++;
			} else if (stat.equals("진행중")) {
				ing++;
			} else if (stat.equals("완료됨")) {
				comp++;
			}

		}

		request_label = new JLabel("요청중 : " + rq + " 건");
		request_label.setFont(new Font("나눔스퀘어", Font.BOLD, 14));
		request_label.setForeground(Color.WHITE);
		request_label.setBounds(19, 64, 92, 15);
		add(request_label);

		ongoing_label = new JLabel("진행중 : " + ing + " 건");
		ongoing_label.setFont(new Font("나눔스퀘어", Font.BOLD, 14));
		ongoing_label.setForeground(Color.WHITE);
		ongoing_label.setBounds(160, 64, 99, 15);
		add(ongoing_label);

		complete_label = new JLabel("완료됨 : " + comp + " 건");
		complete_label.setHorizontalAlignment(SwingConstants.RIGHT);
		complete_label.setFont(new Font("나눔스퀘어", Font.BOLD, 14));
		complete_label.setForeground(Color.WHITE);
		complete_label.setBounds(361, 64, 101, 15);
		add(complete_label);

		check_btn = new JLabel(new ImageIcon(getClass().getClassLoader().getResource(PATH + "confirm.png")));
		check_btn.setName(PATH + "confirm.png");
		check_btn.addMouseListener(new LabelEventListener(this, check_btn, true));
		check_btn.setBounds(265, 60, 78, 23);
		add(check_btn);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == check_btn) {
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).getStatus().equals("진행중")) {
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
		table.getColumnModel().getColumn(0).setPreferredWidth(25); // 글번호 폭
		table.getColumnModel().getColumn(1).setPreferredWidth(60); // 날짜 폭
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

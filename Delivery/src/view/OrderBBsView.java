package view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import controller.OrderController;
import dto.OrderBBsDto;
import singleton.Singleton;

public class OrderBBsView extends JPanel implements ActionListener {

	private JTable table;
	String columnNames[] = { "번호", "상태", "제목", "지역", "작성자", "날짜" };
	Object rowData[][];
	DefaultTableModel model;
	JButton exit_btn;
	JButton logout_btn;
	JButton write_btn;

	public OrderBBsView() {
		// 하단 패널
		setRowData();

		setBounds(6, 125, 480, 487);
		setLayout(null);

		// 테이블의 폭을 설정하기 위한 Model good but
		model = new DefaultTableModel(rowData, columnNames) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		table = new JTable(model);

		table.getColumnModel().getColumn(0).setMaxWidth(30); // 글번호 폭
		table.getColumnModel().getColumn(1).setMaxWidth(50); // 상태 폭
		table.getColumnModel().getColumn(2).setMaxWidth(190); // 제목 폭
		table.getColumnModel().getColumn(3).setMaxWidth(50); // 지역 폭
		table.getColumnModel().getColumn(4).setMaxWidth(80); // 작성자 폭
		table.getColumnModel().getColumn(5).setMaxWidth(100); // 날짜 폭

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
				// 로우 번호 빼고 일단 다 지워놀게/ 디테일뷰 만들어요
			}
		});

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(6, 6, 468, 434);
		add(scrollPane);

		// button

		exit_btn = new JButton("종료");
		exit_btn.setBounds(345, 444, 117, 37);
		add(exit_btn);
		
		logout_btn = new JButton("로그아웃");
		logout_btn.setBounds(217, 444, 128, 37);
		add(logout_btn);
		
		write_btn = new JButton("글쓰기");
		write_btn.setBounds(7, 448, 117, 29);
		add(write_btn);
		
		write_btn.addActionListener(this);
		exit_btn.addActionListener(this);
		logout_btn.addActionListener(this);
		
	}

	public void setRowData() {
		Singleton s = Singleton.getInstance();

		List<OrderBBsDto> list = (List<OrderBBsDto>) s.getOderCtrl().getPostlist();
		rowData = new Object[list.size()][6];

		for (int i = 0; i < list.size(); i++) {
			rowData[i][0] = list.get(i).getReqNum();
			rowData[i][1] = list.get(i).getStatus();
			rowData[i][2] = list.get(i).getTitle();
			rowData[i][3] = list.get(i).getLocation();
			rowData[i][4] = list.get(i).getConsumerId();
			rowData[i][5] = list.get(i).getDate();
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == write_btn) {
			OrderController oc = Singleton.getInstance().getOderCtrl();
			oc.orderWriteView();
		}
		
	}
}

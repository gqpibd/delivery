package view;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import controller.MemberController;
import dto.DelivererDto;
import dto.MemberDto;
import dto.OrderDto;
import singleton.Singleton;

public class ProfileView extends JDialog {
	private JTable table;
	private String columnNames[] = { "번호", "상태", "제목", "지역", "작성자", "날짜" };
	private Object rowData[][];
	private DefaultTableModel model;
	public ProfileView(MemberDto dto) {
		setModal(true);
		MemberController mCtrl = Singleton.getInstance().getMemCtrl();
		JPanel contentPane = new JPanel();
		setContentPane(contentPane);
		//contentPane.setSize(300,300);
		contentPane.setLayout(null);
		setSize(487,500);
		
		DelivererDto deliverer = mCtrl.getDeliverInfo(dto);
		
		List<OrderDto> list = Singleton.getInstance().getOrderCtrl().getDeliverList(dto.getId());
		if(list == null) {
			list = new ArrayList<OrderDto>();
		}
		setRowData(list);
		
		model = new DefaultTableModel(rowData, columnNames) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		table = new JTable(model);

		setTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					super.mouseClicked(e);
					int rowNum = table.getSelectedRow();
					int postNum = (int) table.getValueAt(rowNum, 0);
					Singleton.getInstance().getOrderCtrl().postView(postNum);
				}
			}
		});
		
		JLabel imgLabel = new JLabel("이미지");
		imgLabel.setBackground(Color.WHITE);
		imgLabel.setHorizontalAlignment(SwingConstants.CENTER);
		imgLabel.setBounds(22, 21, 173, 168);
		contentPane.add(imgLabel);
		
		JLabel idLabel = new JLabel("아이디 : " + deliverer.getId());
		idLabel.setBounds(217, 21, 221, 29);
		contentPane.add(idLabel);
		
		JLabel locationLabel = new JLabel("배달지역 : " +deliverer.getLocations()[0]);
		locationLabel.setBounds(217, 57, 221, 29);
		contentPane.add(locationLabel);
		
		JLabel countLabel = new JLabel("배달건수 : " + deliverer.getDeliveryCounts());
		countLabel.setBounds(217, 103, 221, 29);
		contentPane.add(countLabel);
		
		JLabel scoreLabel = new JLabel("만족도 : " + deliverer.getScore());
		scoreLabel.setBounds(217, 139, 221, 29);
		contentPane.add(scoreLabel);
		
		JLabel deliverListLabel = new JLabel("배달목록");
		deliverListLabel.setBounds(22, 196, 99, 29);
		contentPane.add(deliverListLabel);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(22, 246, 435, 178);
		contentPane.add(scrollPane);
		
		JButton okBtn = new JButton("확인");
		okBtn.setBounds(304, 433, 153, 37);
		contentPane.add(okBtn);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public void setRowData(List<OrderDto> list) {

		rowData = new Object[list.size()][6];
		int count=list.size();
		for (int i = 0; i < list.size(); i++) {
			rowData[i][0] = count--;
			rowData[i][1] = list.get(i).getStatus();
			rowData[i][2] = list.get(i).getTitle();
			rowData[i][3] = list.get(i).getLocation();
			rowData[i][4] = list.get(i).getConsumerId();
			rowData[i][5] = list.get(i).getDate().split(" ")[0];
		}
	}

	public void setTable() {
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
	}

}

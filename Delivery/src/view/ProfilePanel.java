package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import controller.MemberController;
import dto.DelivererDto;
import dto.MemberDto;
import dto.OrderDto;
import singleton.Singleton;
import utils.images.ImageUtils;

public class ProfilePanel extends JPanel {
	private JTable table;
	private String columnNames[] = { "번호", "상태", "제목", "지역", "날짜" };
	private Object rowData[][];
	private DefaultTableModel model;
	private JLabel count_label;
	private JLabel imgLabel;

	public ProfilePanel(String id) {
		MemberController mCtrl = Singleton.getInstance().getMemCtrl();
		setBackground(Color.DARK_GRAY);
		setLayout(null);
		setSize(401, 369);
		setBorder(new LineBorder(Color.GRAY, 2, true));
		
		count_label = new JLabel();
		count_label.setFont(new Font("나눔스퀘어", Font.BOLD, 14));
		count_label.setBounds(12, 79, 183, 29);
		
		MemberDto dto = new MemberDto();
		dto.setId(id);
		DelivererDto deliverer = mCtrl.getDeliverInfo(dto);

		List<OrderDto> list = Singleton.getInstance().getOrderCtrl().getDeliverList(dto.getId());
		if (list == null) {
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
		table.setRowMargin(2);
		table.setRowHeight(20);
		table.setFont(new Font("나눔스퀘어", Font.PLAIN, 14));

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
		
		imgLabel = new JLabel();
		imgLabel.setBounds(18, 10, 150, 150);
		add(imgLabel);
		setImage(id);

		JLabel deliverListLabel = new JLabel("배달목록");
		deliverListLabel.setForeground(Color.WHITE);
		deliverListLabel.setFont(new Font("나눔스퀘어", Font.BOLD, 14));
		deliverListLabel.setBounds(12, 167, 99, 29);
		add(deliverListLabel);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setFont(new Font("나눔스퀘어", Font.PLAIN, 14));
		scrollPane.setBounds(12, 206, 377, 148);
		add(scrollPane);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(182, 10, 207, 154);
		add(panel);
		panel.setLayout(null);
		
		panel.add(count_label);

		JLabel id_label = new JLabel("아이디 : " + deliverer.getId());
		id_label.setFont(new Font("나눔스퀘어", Font.BOLD, 14));
		id_label.setBounds(12, 7, 183, 29);
		panel.add(id_label);

		JLabel lob_label = new JLabel("배달지역 : " + deliverer.getLocation());
		lob_label.setFont(new Font("나눔스퀘어", Font.BOLD, 14));
		lob_label.setBounds(12, 43, 183, 29);
		panel.add(lob_label);

		JLabel score_label = new JLabel("만족도 : " + deliverer.getScore() + "");
		score_label.setFont(new Font("나눔스퀘어", Font.BOLD, 14));
		score_label.setBounds(12, 115, 183, 29);
		panel.add(score_label);
		
		setVisible(true);
	}

	public void setRowData(List<OrderDto> list) {
		List<OrderDto> selectedList = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) { // 요청중인거는 빼고 보여주자
			if(list.get(i).getStatus().equals("요청중")) {
				continue;
			}
			selectedList.add(list.get(i));
		}
		rowData = new Object[selectedList.size()][5];
		
		for(int i=0;i<selectedList.size();i++) {
			rowData[i][0] = selectedList.get(i).getReqNum();
			rowData[i][1] = selectedList.get(i).getStatus();
			rowData[i][2] = selectedList.get(i).getTitle();
			rowData[i][3] = selectedList.get(i).getLocation();
			rowData[i][4] = selectedList.get(i).getDate().split(" ")[0];
		}
		count_label.setText("배달건수 : " + selectedList.size() + " 건");
	}

	public void setTable() {
		table.getColumnModel().getColumn(0).setMaxWidth(30); // 상태 폭
		table.getColumnModel().getColumn(1).setMaxWidth(50); // 상태 폭
		table.getColumnModel().getColumn(2).setMaxWidth(190); // 제목 폭
		table.getColumnModel().getColumn(3).setMaxWidth(50); // 지역 폭
		table.getColumnModel().getColumn(4).setMaxWidth(100); // 날짜 폭

		DefaultTableCellRenderer celAlignCenter = new DefaultTableCellRenderer();
		celAlignCenter.setHorizontalAlignment(JLabel.CENTER);

		table.getColumn("번호").setCellRenderer(celAlignCenter);
		table.getColumn("상태").setCellRenderer(celAlignCenter);
		table.getColumn("지역").setCellRenderer(celAlignCenter);
		table.getColumn("날짜").setCellRenderer(celAlignCenter);
	}
	
	private void setImage(String id) {
		BufferedImage img = Singleton.getInstance().getComm().getImage(id);		
		if(img != null) {
			ImageIcon icon = new ImageIcon(img);
			ImageUtils.setResizedImage(imgLabel, icon);
		}
	}
}

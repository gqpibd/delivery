package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import controller.OrderController;
import dto.MemberDto;
import dto.OrderDto;
import net.coderazzi.filters.gui.AutoChoices;
import net.coderazzi.filters.gui.TableFilterHeader;
import net.coderazzi.filters.gui.TableFilterHeader.Position;
import singleton.Singleton;

public class OrderBBsView extends JPanel implements ActionListener {

	private JTable table;
	private String columnNames[] = { "번호", "상태", "제목", "지역", "작성자", "날짜" };
	private Object rowData[][];
	private DefaultTableModel model;
	private JButton write_btn;
	private JButton search_btn;
	private JComboBox<String> choice_comboBox;
	private JTextField search_textF;

	public OrderBBsView() {
		Singleton s = Singleton.getInstance();
		List<OrderDto> list = s.getOrderCtrl().getPostlist();
		setRowData(list);

		setSize(MainView.BOTTOM_WIDTH, MainView.BOTTOM_HEIGHT);
		setLayout(null);

		// 테이블의 폭을 설정하기 위한 Model good but
		model = new DefaultTableModel(rowData, columnNames) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		table = new JTable(model);

		setTable();
		TableFilterHeader filterHeader = new TableFilterHeader(table, AutoChoices.ENABLED);
		filterHeader.setPosition(Position.TOP);
		
		for (int i = 0; i < columnNames.length; i++) {
			filterHeader.getFilterEditor(i).setEditable(false);
		}
		

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					int rowNum = table.getSelectedRow();
					int postNum = (int) table.getValueAt(rowNum, 0);
					Singleton.getInstance().getOrderCtrl().postView(postNum);
				}
			}
		});

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(7, 65, 468, 358);
		add(scrollPane);

		write_btn = new JButton("글쓰기");
		write_btn.setBounds(358, 430, 117, 33);
		add(write_btn);

		if (Singleton.getInstance().getMemCtrl().getCurrentUser().getAuth() != MemberDto.CONSUMER) {
			System.out.println(Singleton.getInstance().getMemCtrl().getCurrentUser().getAuth());
			write_btn.setVisible(false);
		}

		write_btn.addActionListener(this);

		choice_comboBox = new JComboBox<>();
		choice_comboBox.setBounds(7, 22, 117, 33);
		add(choice_comboBox);
		choice_comboBox.addItem("작성자");
		choice_comboBox.addItem("내용");
		choice_comboBox.addItem("제목");
		choice_comboBox.addActionListener(this);

		search_btn = new JButton("검색");
		search_btn.setBounds(358, 22, 117, 33);
		add(search_btn);
		search_btn.addActionListener(this);

		search_textF = new JTextField();
		search_textF.setBounds(137, 22, 209, 33);
		add(search_textF);
		search_textF.setColumns(10);

	}

	public void setRowData(List<OrderDto> list) {

		rowData = new Object[list.size()][6];
		//int count=list.size();
		System.out.println("list size : " + list.size());
		for (int i = 0; i < list.size(); i++) {
			rowData[i][0] = list.get(i).getReqNum();
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
		table.getColumnModel().getColumn(3).setMaxWidth(65); // 지역 폭
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

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == write_btn) {
			OrderController oc = Singleton.getInstance().getOrderCtrl();
			oc.orderWriteView();
		}		
		else if(e.getSource() == search_btn) {
			String inputF = search_textF.getText();
			List<OrderDto> list = Singleton.getInstance().getOrderCtrl()
					.selectList((String) choice_comboBox.getSelectedItem(), inputF);

			setRowData(list);
			System.out.println(list);
			model.setRowCount(rowData.length);
			model.setDataVector(rowData, columnNames);
			//table.setModel(model);
			setTable();
		}
				
	}
}

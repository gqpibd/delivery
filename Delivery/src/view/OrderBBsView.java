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
import dto.DelivererDto;
import dto.MemberDto;
import dto.OrderBBsDto;
import net.coderazzi.filters.gui.AutoChoices;
import net.coderazzi.filters.gui.IFilterEditor;
import net.coderazzi.filters.gui.TableFilterHeader;
import net.coderazzi.filters.gui.TableFilterHeader.Position;
import singleton.Singleton;

public class OrderBBsView extends JPanel implements ActionListener {

	private JTable table;
	private String columnNames[] = { "번호", "상태", "제목", "지역", "작성자", "날짜" };
	private Object rowData[][];
	private DefaultTableModel model;
	private JButton exit_btn;
	private JButton logout_btn;
	private JButton write_btn;
	private JButton search_btn;
	private JComboBox<String> choice_comboBox;
	private JTextField search_textF;

	public OrderBBsView() {
		// 하단 패널
		Singleton s = Singleton.getInstance();
		List<OrderBBsDto> list = s.getOrderCtrl().getPostlist();
		setRowData(list);

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

		setTable();
		TableFilterHeader filterHeader = new TableFilterHeader(table, AutoChoices.ENABLED);
		filterHeader.setPosition(Position.TOP);
<<<<<<< HEAD

		for (int i = 0; i < columnNames.length; i++) {
			filterHeader.getFilterEditor(i).setEditable(false);
		}
		if (s.getMemCtrl().getCurrentUser().getAuth() == MemberDto.DELIVERER) {
			DelivererDto dto = (DelivererDto) s.getMemCtrl().getCurrentUser();
			filterHeader.getFilterEditor(3).setContent(dto.getLocations()[0]);
		}
=======
		
		for (int i = 0; i < columnNames.length; i++) {
			filterHeader.getFilterEditor(i).setEditable(false);
		}
		
>>>>>>> refs/remotes/origin/h2gon
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

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(7, 17, 468, 382);
		add(scrollPane);

		// button

		exit_btn = new JButton("종료");
		exit_btn.setBounds(345, 444, 117, 37);
		add(exit_btn);
<<<<<<< HEAD

=======
		exit_btn.addActionListener(this);
		
>>>>>>> refs/remotes/origin/h2gon
		logout_btn = new JButton("로그아웃");
		logout_btn.setBounds(217, 444, 128, 37);
		add(logout_btn);
<<<<<<< HEAD

=======
		logout_btn.addActionListener(this);
		
>>>>>>> refs/remotes/origin/h2gon
		write_btn = new JButton("글쓰기");
		write_btn.setBounds(7, 444, 128, 33);
		add(write_btn);

		if (Singleton.getInstance().getMemCtrl().getCurrentUser().getAuth() != MemberDto.CONSUMER) {
			System.out.println(Singleton.getInstance().getMemCtrl().getCurrentUser().getAuth());
			write_btn.setVisible(false);
		}

		write_btn.addActionListener(this);
		exit_btn.addActionListener(this);
		logout_btn.addActionListener(this);

		choice_comboBox = new JComboBox<>();
		choice_comboBox.setBounds(123, 403, 117, 37);
		add(choice_comboBox);
		choice_comboBox.addItem("작성자");
		choice_comboBox.addItem("내용");
		choice_comboBox.addItem("제목");
		choice_comboBox.addActionListener(this);

		search_btn = new JButton("검색");
		search_btn.setBounds(357, 403, 117, 37);
		add(search_btn);
		search_btn.addActionListener(this);

		search_textF = new JTextField();
		search_textF.setBounds(241, 404, 117, 33);
		add(search_textF);
		search_textF.setColumns(10);

	}

	public void setRowData(List<OrderBBsDto> list) {

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

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == write_btn) {
			OrderController oc = Singleton.getInstance().getOrderCtrl();
			oc.orderWriteView();
<<<<<<< HEAD

		}
		if (e.getSource() == search_btn) {
=======
						
		}		
		else if(e.getSource() == search_btn) {
>>>>>>> refs/remotes/origin/h2gon
			String inputF = search_textF.getText();
			List<OrderBBsDto> list = Singleton.getInstance().getOrderCtrl()
					.selectList((String) choice_comboBox.getSelectedItem(), inputF);

			setRowData(list);
			model.setDataVector(rowData, columnNames);
			setTable();

		}
		else if(e.getSource() == logout_btn) {
			Singleton.getInstance().getMemCtrl().logout();
			Singleton.getInstance().hideMainView();
		}
		else if(e.getSource() == exit_btn) {
			System.exit(0);
		}
		
	}

}

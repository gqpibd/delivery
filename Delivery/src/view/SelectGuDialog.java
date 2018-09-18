package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import communicator.Communicator;
import singleton.Singleton;
import utils.images.LabelEventListener;

public class SelectGuDialog extends JDialog implements ActionListener {
	private JTextField input_field;
	private JList<String> list;
	private JLabel searchBtn;
	private JLabel confirmBtn;
	private JLabel cancelBtn;
	private JTextField selectedAddField;
	// private static final String PATH = "images/accountView/";

	public SelectGuDialog(JFrame parent) {
		super(parent, true);
		JPanel contentPane;
		setSize(321, 381);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		
		contentPane.setLayout(null);
		setContentPane(contentPane);
		Font fontStyle = new Font("다음_Regular", Font.PLAIN, 14);

		JLabel titleLabel = new JLabel("배달 지역 등록");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(fontStyle);
		titleLabel.setBounds(12, 10, 278, 15);
		contentPane.add(titleLabel);

		JLabel seoulLabel = new JLabel("서울특별시");
		seoulLabel.setFont(fontStyle);
		seoulLabel.setBounds(111, 51, 101, 15);
		contentPane.add(seoulLabel);

		input_field = new JTextField();
		input_field.setFont(fontStyle);
		input_field.setBounds(111, 76, 116, 21);
		contentPane.add(input_field);
		input_field.addActionListener(this);
		input_field.setColumns(10);

		JLabel sidoLabel = new JLabel("시/도");
		sidoLabel.setFont(fontStyle);
		sidoLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		sidoLabel.setBounds(42, 51, 57, 15);
		contentPane.add(sidoLabel);

		JLabel gu_search_Label = new JLabel("구 검색");
		gu_search_Label.setFont(fontStyle);
		gu_search_Label.setHorizontalAlignment(SwingConstants.RIGHT);
		gu_search_Label.setBounds(42, 79, 57, 15);
		contentPane.add(gu_search_Label);

		list = new JList<String>();
		JScrollPane scrollPane = new JScrollPane(list);
		scrollPane.setBackground(Color.WHITE);
		scrollPane.setBounds(14, 192, 276, 100);
		contentPane.add(scrollPane);
		list.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				selectedAddField.setText(list.getSelectedValue());
			}
		});

		JLabel lblNewLabel_1 = new JLabel("검색 결과");
		lblNewLabel_1.setFont(fontStyle);
		lblNewLabel_1.setBounds(12, 167, 87, 15);
		contentPane.add(lblNewLabel_1);

		//searchBtn = new JLabel(new ImageIcon(PATH + "adrSearchBtn.jpg"));
		searchBtn = new JLabel("검색");
		searchBtn.setHorizontalAlignment(SwingConstants.CENTER);
		searchBtn.setFont(fontStyle);
		//searchBtn.setBounds(239, 62, searchBtn.getIcon().getIconWidth(), searchBtn.getIcon().getIconHeight());
		searchBtn.setBounds(239, 75, 54, 25);
		searchBtn.addMouseListener(new LabelEventListener(this,searchBtn));
		contentPane.add(searchBtn);

		confirmBtn = new JLabel("적용");
		confirmBtn.setHorizontalAlignment(SwingConstants.CENTER);
		confirmBtn.setFont(fontStyle);
//		confirmBtn = new JLabel(new ImageIcon(PATH + "adrOkBtn.jpg"));
//		confirmBtn.setBounds(283, 332, confirmBtn.getIcon().getIconWidth(), confirmBtn.getIcon().getIconHeight());
		confirmBtn.setBounds(8, 302, 90, 25);
		confirmBtn.addMouseListener(new LabelEventListener(this,searchBtn));
		contentPane.add(confirmBtn);

		JLabel selected_Label = new JLabel("선택 지역");
		selected_Label.setHorizontalAlignment(SwingConstants.RIGHT);
		selected_Label.setFont(fontStyle);
		selected_Label.setBounds(29, 111, 70, 15);
		contentPane.add(selected_Label);

		selectedAddField = new JTextField();
		selectedAddField.setEditable(false);
		selectedAddField.setFont(fontStyle);
		selectedAddField.setBounds(111, 107, 116, 21);
		contentPane.add(selectedAddField);
		selectedAddField.setColumns(10);
		
		cancelBtn = new JLabel("취소");
		cancelBtn.setFont(fontStyle);
		cancelBtn.addMouseListener(new LabelEventListener(this,cancelBtn));
		cancelBtn.setHorizontalAlignment(SwingConstants.CENTER);
		cancelBtn.setBounds(204, 302, 90, 25);
		contentPane.add(cancelBtn);

		setLocationRelativeTo(null);

		setVisible(true);
	}

	public String getGuName() {
		return selectedAddField.getText();
	}
	// 콤보박스에서울에 있는 구전체가 나오게 하면 되죠? 그렇죠
	// 여기있는거 복사해서씁시

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == searchBtn || e.getSource() == input_field) {
			String input = input_field.getText();
			if(input.length() == 0) {
				JOptionPane.showMessageDialog(null, "한 글자 이상 입력해 주세요");
				return;
			}
			Communicator comm = Singleton.getInstance().getComm();
			comm.SendMessage(Communicator.SELECT_GU, input);
			ArrayList<String> results = (ArrayList<String>) comm.receiveObject();
			String resArr[] = new String[results.size()];
			for (int i = 0; i < resArr.length; i++) {
				resArr[i] = results.get(i).toString();
			}
			list.setListData(resArr);
		} else if (e.getSource() == confirmBtn) {
			if (selectedAddField.getText().length() == 0) {
				JOptionPane.showMessageDialog(null, "주소를 모두 입력해 주세요");
			} else {
				dispose();
			}
		} else if (e.getSource() == cancelBtn) {
			dispose();
		}
	}
}

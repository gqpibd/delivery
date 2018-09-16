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

public class SelectAddressDialog extends JDialog implements ActionListener {
	private JTextField loadNameField;
	private JList<String> list;
	private JLabel searchBtn;
	private JLabel confirmBtn;
	private JLabel cancelBtn;
	private JTextField selectedAddField;
	private JTextField detailAddField;
	// private static final String PATH = "images/accountView/";

	public SelectAddressDialog(JFrame parent) {
		super(parent, true);
		JPanel contentPane;
		setSize(392, 413);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		
		contentPane.setLayout(null);
		setContentPane(contentPane);
		Font fontStyle = new Font("다음_Regular", Font.PLAIN, 14);

		JLabel titleLabel = new JLabel("도로명 주소 검색");
		titleLabel.setFont(fontStyle);
		titleLabel.setBounds(12, 10, 167, 15);
		contentPane.add(titleLabel);

		JLabel seoulLabel = new JLabel("서울특별시");
		seoulLabel.setFont(fontStyle);
		seoulLabel.setBounds(111, 38, 101, 15);
		contentPane.add(seoulLabel);

		loadNameField = new JTextField();
		loadNameField.setFont(fontStyle);
		loadNameField.setBounds(111, 63, 116, 21);
		contentPane.add(loadNameField);
		loadNameField.addActionListener(this);
		loadNameField.setColumns(10);

		JLabel sidoLabel = new JLabel("시/도");
		sidoLabel.setFont(fontStyle);
		sidoLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		sidoLabel.setBounds(42, 38, 57, 15);
		contentPane.add(sidoLabel);

		JLabel loadLabel = new JLabel("도로명");
		loadLabel.setFont(fontStyle);
		loadLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		loadLabel.setBounds(42, 66, 57, 15);
		contentPane.add(loadLabel);

		list = new JList<>();
		list.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				selectedAddField.setText(list.getSelectedValue());
			}
		});

		JScrollPane scrollPane = new JScrollPane(list);
		scrollPane.setBackground(Color.WHITE);
		scrollPane.setBounds(12, 192, 349, 130);
		contentPane.add(scrollPane);

		JLabel lblNewLabel_1 = new JLabel("검색 결과");
		lblNewLabel_1.setFont(fontStyle);
		lblNewLabel_1.setBounds(12, 167, 87, 15);
		contentPane.add(lblNewLabel_1);

		//searchBtn = new JLabel(new ImageIcon(PATH + "adrSearchBtn.jpg"));
		searchBtn = new JLabel("검색");
		searchBtn.setFont(fontStyle);
		//searchBtn.setBounds(239, 62, searchBtn.getIcon().getIconWidth(), searchBtn.getIcon().getIconHeight());
		searchBtn.setBounds(239, 62, 100, 25);
		searchBtn.addMouseListener(new LabelEventListener(this,searchBtn));
		contentPane.add(searchBtn);

		confirmBtn = new JLabel("적용");
		confirmBtn.setHorizontalAlignment(SwingConstants.CENTER);
		confirmBtn.setFont(fontStyle);
//		confirmBtn = new JLabel(new ImageIcon(PATH + "adrOkBtn.jpg"));
//		confirmBtn.setBounds(283, 332, confirmBtn.getIcon().getIconWidth(), confirmBtn.getIcon().getIconHeight());
		confirmBtn.setBounds(79, 332, 100, 25);
		confirmBtn.addMouseListener(new LabelEventListener(this,searchBtn));
		contentPane.add(confirmBtn);

		selectedAddField = new JTextField();
		selectedAddField.setFont(fontStyle);
		selectedAddField.setEditable(false);
		selectedAddField.setBounds(111, 94, 215, 21);
		contentPane.add(selectedAddField);
		selectedAddField.setColumns(10);

		JLabel detailAddLabel = new JLabel("상세 주소");
		detailAddLabel.setFont(fontStyle);
		detailAddLabel.setBounds(29, 126, 70, 15);
		contentPane.add(detailAddLabel);

		detailAddField = new JTextField();
		detailAddField.setFont(fontStyle);
		detailAddField.setBounds(111, 123, 215, 21);
		contentPane.add(detailAddField);
		detailAddField.setColumns(10);
		
		cancelBtn = new JLabel("취소");
		cancelBtn.setFont(fontStyle);
		cancelBtn.setHorizontalAlignment(SwingConstants.CENTER);
		cancelBtn.setBounds(206, 332, 100, 25);
		contentPane.add(cancelBtn);

		setLocationRelativeTo(null);

		setVisible(true);
	}

	public String getAddress() {
		return selectedAddField.getText();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == searchBtn || e.getSource() == loadNameField) {
			String load = loadNameField.getText();
			Communicator comm = Singleton.getInstance().getComm();
			comm.SendMessage(Communicator.SELECT_ADDRESS, load);
			ArrayList<String> results = (ArrayList<String>) comm.receiveObject();
			String resArr[] = new String[results.size()];
			for (int i = 0; i < resArr.length; i++) {
				resArr[i] = results.get(i).toString();
			}
			list.setListData(resArr);
		} else if (e.getSource() == confirmBtn) {
			if (detailAddField.getText().length() == 0 || selectedAddField.getText().length() == 0) {
				JOptionPane.showMessageDialog(null, "주소를 모두 입력해 주세요");
			} else {
				dispose();
			}
		} else if (e.getSource() == cancelBtn) {
			dispose();
		}
	}

	public String getDetailAddress() {
		return detailAddField.getText();
	}
}

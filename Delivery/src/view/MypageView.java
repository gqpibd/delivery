package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class MypageView extends JPanel implements ActionListener{

	
	public MypageView() {
		// 하단 패널
		
		setBounds(6, 125, 480, 487);
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("내 정보");
		lblNewLabel.setBounds(12, 10, 57, 15);
		add(lblNewLabel);

		

	}

	@Override
	public void actionPerformed(ActionEvent e) {
	
	}
}
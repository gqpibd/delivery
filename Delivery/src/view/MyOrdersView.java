package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

<<<<<<< HEAD
import javax.swing.JFrame;
=======
>>>>>>> refs/remotes/origin/h2gon
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MyOrdersView extends JPanel implements ActionListener{

	
	public MyOrdersView() {
		// 하단 패널
		
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("내 주문 내역");
		lblNewLabel.setBounds(12, 10, 57, 15);
		add(lblNewLabel);

		

	}

	@Override
	public void actionPerformed(ActionEvent e) {
	
	}

}

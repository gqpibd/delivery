package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class MyOrdersView extends JPanel implements ActionListener{

	
	public MyOrdersView() {
		// 하단 패널
		
		setLayout(null);
		setBounds(6, 125, 480, 487);
		
		JLabel title_label = new JLabel("New label");
		title_label.setHorizontalAlignment(SwingConstants.CENTER);
		title_label.setBounds(0, 25, 480, 58);
		add(title_label);

		

	}

	@Override
	public void actionPerformed(ActionEvent e) {
	
	}

}

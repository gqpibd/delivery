package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
<<<<<<< HEAD

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
=======
>>>>>>> refs/remotes/origin/h2gon

<<<<<<< HEAD
public class MypageView extends JPanel implements ActionListener{
=======
import javax.swing.JLabel;
import javax.swing.JPanel;
>>>>>>> refs/remotes/origin/h2gon

<<<<<<< HEAD
	
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
=======
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
>>>>>>> refs/remotes/origin/h2gon

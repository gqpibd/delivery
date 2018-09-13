package utils.images;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.border.EtchedBorder;

public class LabelEventListener extends MouseAdapter{
	
	ActionListener listener = null;
	String imgPath;
	JLabel label;
	
	public LabelEventListener(ActionListener listener, String imgPath, JLabel label){
		this.listener = listener;
		this.imgPath = imgPath;
		this.label = label;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		super.mouseClicked(e);
		listener.actionPerformed(new ActionEvent(e.getSource(),e.getID(),e.paramString()));			
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		super.mouseReleased(e);
		label.setIcon(new ImageIcon(imgPath));
	}

	@Override
	public void mousePressed(MouseEvent e) {
		super.mousePressed(e);
		//((JLabel) e.getSource()).setBorder(null);
		
		int index = imgPath.lastIndexOf('.');
		String clickImg = imgPath.substring(0, index) + "_clicked" + imgPath.substring(index);
		File f = new File(clickImg);
		if(f.exists()) {
			label.setIcon(new ImageIcon(clickImg));
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		//((JLabel) e.getSource()).setBorder(new EtchedBorder());
		super.mouseEntered(e);
		
		int index = imgPath.lastIndexOf('.');
		String clickImg = imgPath.substring(0, index) + "_over" + imgPath.substring(index);
		File f = new File(clickImg);
		if(f.exists()) {
			label.setIcon(new ImageIcon(clickImg));
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		//((JLabel) e.getSource()).setBorder(null);
		super.mouseExited(e);
		label.setIcon(new ImageIcon(imgPath));
	}

}

package utils.images;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class LabelEventListener extends MouseAdapter {

	ActionListener listener = null;
	String imgPath;
	JLabel label;
	boolean isTab = false;

	public LabelEventListener(ActionListener listener, JLabel label, boolean isTab) {
		this(listener, label);
		this.isTab = isTab;
	}

	public LabelEventListener(ActionListener listener, JLabel label) {
		this.listener = listener;
		if (label.getName() != null) {
			this.imgPath = label.getName().split(" ")[0];
		}
		this.label = label;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		super.mouseClicked(e);
		listener.actionPerformed(new ActionEvent(e.getSource(), e.getID(), e.paramString()));
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		super.mouseReleased(e);
		if (!isTab) {
			URL url = getClass().getClassLoader().getResource(imgPath);
			label.setIcon(new ImageIcon(url));
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		super.mousePressed(e);
		// ((JLabel) e.getSource()).setBorder(null);
		if (!isTab) {
			int index = imgPath.lastIndexOf('.');
			String clickImg = imgPath.substring(0, index) + "_click" + imgPath.substring(index);
			URL url = getClass().getClassLoader().getResource(clickImg);
			System.out.println(url);

			if (url != null) {
				label.setIcon(new ImageIcon(url));
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		System.out.println(imgPath);
		int index = imgPath.lastIndexOf('.');
		String overImg = imgPath.substring(0, index) + "_over" + imgPath.substring(index);
		URL url = getClass().getClassLoader().getResource(overImg);
		if (url != null) {
			label.setIcon(new ImageIcon(url));
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if (isFocused() && isTab) {
			int index = imgPath.lastIndexOf('.');
			String overImg = imgPath.substring(0, index) + "_over" + imgPath.substring(index);
			URL url = getClass().getClassLoader().getResource(overImg);
			if (url != null) {
				label.setIcon(new ImageIcon(url));
			}
			return;
		}
		URL url = getClass().getClassLoader().getResource(imgPath);
		label.setIcon(new ImageIcon(url));
	}

	public boolean isFocused() {
		if(label.getName().split(" ").length > 1) {
			if (label.getName().split(" ").length > 1 && label.getName().split(" ")[1].equals("focus")) {
				return true;
			} 
		}
		return false;
	}
}

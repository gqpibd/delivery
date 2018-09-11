package client.utils.images;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.border.EtchedBorder;

public class LabelEventListener extends MouseAdapter{
	
	ActionListener listener = null;
	
	public LabelEventListener(ActionListener listener){
		this.listener = listener;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		super.mouseClicked(e);
		listener.actionPerformed(new ActionEvent(e.getSource(),e.getID(),e.paramString()));
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		super.mousePressed(e);
		((JLabel) e.getSource()).setBorder(null);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		((JLabel) e.getSource()).setBorder(new EtchedBorder());
		super.mouseEntered(e);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		((JLabel) e.getSource()).setBorder(null);
		super.mouseExited(e);
	}

}

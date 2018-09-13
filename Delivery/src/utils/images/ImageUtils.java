package utils.images;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

public class ImageUtils {
	public static String jFileChooserUtil() { 

		String folderPath = "";

		JFileChooser chooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory()); // ?��?��?���? ?��?��
		chooser.setCurrentDirectory(new File("d:/images")); // ?��?�� ?��?�� ?��?��?��리�?? �??��
		chooser.setAcceptAllFileFilterUsed(true); // Fileter 모든 ?��?�� ?��?��
		chooser.setDialogTitle("?��?�� ?���? �??��"); // 창의 ?���?
		chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES); // ?��?�� ?��?�� 모드

		FileNameExtensionFilter filter = new FileNameExtensionFilter("jpg", "jpg"); // filter ?��?��?�� 추�?
		chooser.setFileFilter(filter); // ?��?�� ?��?���? 추�?

		int returnVal = chooser.showOpenDialog(null); // ?��기용 �? ?��?��

		if (returnVal == JFileChooser.APPROVE_OPTION) { // ?��기�?? ?���?
			folderPath = chooser.getSelectedFile().toString();
		} else if (returnVal == JFileChooser.CANCEL_OPTION) { // 취소�? ?���?
			folderPath = "";
		}

		return folderPath;
	}


	public static void setResizedImage(JLabel imgLabel, ImageIcon icon) { // ?��미�?�? ?��?���?

		Image originImg = icon.getImage();

		Image changedImg = originImg.getScaledInstance(imgLabel.getWidth(), imgLabel.getHeight(), Image.SCALE_SMOOTH);

		ImageIcon resizedIcon = new ImageIcon(changedImg);

		imgLabel.setIcon(resizedIcon);

	}

}

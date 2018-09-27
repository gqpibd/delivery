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

		JFileChooser chooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		chooser.setCurrentDirectory(new File("d:/images")); 
		chooser.setAcceptAllFileFilterUsed(true);
		chooser.setDialogTitle("이미지 파일 선택");
		chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

		FileNameExtensionFilter filter = new FileNameExtensionFilter("이미지파일(.png)", "png");
		chooser.setFileFilter(filter); 

		int returnVal = chooser.showOpenDialog(null); 

		if (returnVal == JFileChooser.APPROVE_OPTION) { 
			folderPath = chooser.getSelectedFile().toString();
		} else if (returnVal == JFileChooser.CANCEL_OPTION) {
			folderPath = "";
		}

		return folderPath;
	}


	public static void setResizedImage(JLabel imgLabel, ImageIcon icon) {

		Image originImg = icon.getImage();

		Image changedImg = originImg.getScaledInstance(imgLabel.getWidth(), imgLabel.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon resizedIcon = new ImageIcon(changedImg);

		imgLabel.setIcon(resizedIcon);

	}

}

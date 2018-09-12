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
	public static String jFileChooserUtil() { // ?´ë¯¸ì? ?ŒŒ?¼ ?„ ?ƒì°?

		String folderPath = "";

		JFileChooser chooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory()); // ?””? ‰?† ë¦? ?„¤? •
		chooser.setCurrentDirectory(new File("d:/images")); // ?˜„?¬ ?‚¬?š© ?””? ‰?† ë¦¬ë?? ì§?? •
		chooser.setAcceptAllFileFilterUsed(true); // Fileter ëª¨ë“  ?ŒŒ?¼ ? ?š©
		chooser.setDialogTitle("?ŒŒ?¼ ?œ„ì¹? ê²??ƒ‰"); // ì°½ì˜ ? œëª?
		chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES); // ?ŒŒ?¼ ?„ ?ƒ ëª¨ë“œ

		FileNameExtensionFilter filter = new FileNameExtensionFilter("jpg", "jpg"); // filter ?™•?¥? ì¶”ê?
		chooser.setFileFilter(filter); // ?ŒŒ?¼ ?•„?„°ë¥? ì¶”ê?

		int returnVal = chooser.showOpenDialog(null); // ?—´ê¸°ìš© ì°? ?˜¤?”ˆ

		if (returnVal == JFileChooser.APPROVE_OPTION) { // ?—´ê¸°ë?? ?´ë¦?
			folderPath = chooser.getSelectedFile().toString();
		} else if (returnVal == JFileChooser.CANCEL_OPTION) { // ì·¨ì†Œë¥? ?´ë¦?
			folderPath = "";
		}

		return folderPath;
	}

	// public void setResizedImage(JLabel imgLabel, String path) { // ?´ë¯¸ì?ë¥? ?„£?–´ì¤?
	//
	// // getClass().getClassLoader().getResource(
	// try {
	// BufferedImage m_numberImage =
	// ImageIO.read(getClass().getClassLoader().getResource(path));
	//
	// ImageIcon icon = new ImageIcon(m_numberImage);
	//
	// // ImageIcon?—?„œ Imageë¥? ì¶”ì¶œ
	// Image originImg = icon.getImage();
	//
	// // ì¶”ì¶œ?œ Image?˜ ?¬ê¸°ë?? ì¡°ì ˆ?•˜?—¬ ?ƒˆë¡œìš´ Imageê°ì²´ ?ƒ?„±
	// Image changedImg = originImg.getScaledInstance(imgLabel.getWidth(),
	// imgLabel.getHeight(),
	// Image.SCALE_SMOOTH);
	//
	// // ?ƒˆë¡œìš´ Imageë¡? ImageIconê°ì²´ë¥? ?ƒ?„±
	// ImageIcon resizedIcon = new ImageIcon(changedImg);
	//
	// imgLabel.setIcon(resizedIcon);
	// } catch (IOException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	//
	// }
	public static void setResizedImage(JLabel imgLabel, ImageIcon icon) { // ?´ë¯¸ì?ë¥? ?„£?–´ì¤?

		// ImageIcon?—?„œ Imageë¥? ì¶”ì¶œ
		Image originImg = icon.getImage();

		// ì¶”ì¶œ?œ Image?˜ ?¬ê¸°ë?? ì¡°ì ˆ?•˜?—¬ ?ƒˆë¡œìš´ Imageê°ì²´ ?ƒ?„±
		Image changedImg = originImg.getScaledInstance(imgLabel.getWidth(), imgLabel.getHeight(), Image.SCALE_SMOOTH);

		// ?ƒˆë¡œìš´ Imageë¡? ImageIconê°ì²´ë¥? ?ƒ?„±
		ImageIcon resizedIcon = new ImageIcon(changedImg);

		imgLabel.setIcon(resizedIcon);

	}

}

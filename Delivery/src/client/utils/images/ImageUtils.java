package client.utils.images;

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
	public static String jFileChooserUtil() { // 이미지 파일 선택창

		String folderPath = "";

		JFileChooser chooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory()); // 디렉토리 설정
		chooser.setCurrentDirectory(new File("d:/images")); // 현재 사용 디렉토리를 지정
		chooser.setAcceptAllFileFilterUsed(true); // Fileter 모든 파일 적용
		chooser.setDialogTitle("파일 위치 검색"); // 창의 제목
		chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES); // 파일 선택 모드

		FileNameExtensionFilter filter = new FileNameExtensionFilter("jpg", "jpg"); // filter 확장자 추가
		chooser.setFileFilter(filter); // 파일 필터를 추가

		int returnVal = chooser.showOpenDialog(null); // 열기용 창 오픈

		if (returnVal == JFileChooser.APPROVE_OPTION) { // 열기를 클릭
			folderPath = chooser.getSelectedFile().toString();
		} else if (returnVal == JFileChooser.CANCEL_OPTION) { // 취소를 클릭
			folderPath = "";
		}

		return folderPath;
	}

	// public void setResizedImage(JLabel imgLabel, String path) { // 이미지를 넣어줌
	//
	// // getClass().getClassLoader().getResource(
	// try {
	// BufferedImage m_numberImage =
	// ImageIO.read(getClass().getClassLoader().getResource(path));
	//
	// ImageIcon icon = new ImageIcon(m_numberImage);
	//
	// // ImageIcon에서 Image를 추출
	// Image originImg = icon.getImage();
	//
	// // 추출된 Image의 크기를 조절하여 새로운 Image객체 생성
	// Image changedImg = originImg.getScaledInstance(imgLabel.getWidth(),
	// imgLabel.getHeight(),
	// Image.SCALE_SMOOTH);
	//
	// // 새로운 Image로 ImageIcon객체를 생성
	// ImageIcon resizedIcon = new ImageIcon(changedImg);
	//
	// imgLabel.setIcon(resizedIcon);
	// } catch (IOException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	//
	// }
	public static void setResizedImage(JLabel imgLabel, ImageIcon icon) { // 이미지를 넣어줌

		// ImageIcon에서 Image를 추출
		Image originImg = icon.getImage();

		// 추출된 Image의 크기를 조절하여 새로운 Image객체 생성
		Image changedImg = originImg.getScaledInstance(imgLabel.getWidth(), imgLabel.getHeight(), Image.SCALE_SMOOTH);

		// 새로운 Image로 ImageIcon객체를 생성
		ImageIcon resizedIcon = new ImageIcon(changedImg);

		imgLabel.setIcon(resizedIcon);

	}

}

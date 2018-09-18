package communicator;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class Communicator {
	public static final int INSERT = 0;
	public static final int DELETE = 1;
	public static final int UPDATE = 2;
	public static final int SELECT = 3;
	public static final int SELECT_POST = 4;
	public static final int SELECT_ADDRESS = 5;
	public static final int SELECT_IDCHEKCK = 6;
	public static final int SELECT_GU = 7;
	public static final int SELECT_POSTCONENT = 8;

	private Socket sock;

	public void makeConnection() {
		try {
			InetSocketAddress sockAddr = new InetSocketAddress("127.0.0.1", 6000); // 포트번호는 서버의 포트번호와 동일하게 해준다.
			sock = new Socket();
			sock.connect(sockAddr);
			System.out.println("연결성공");

		} catch (IOException e) {
			System.out.println("서버에 연결할 수 없습니다. 프로그램을 종료합니다.");
			JOptionPane.showMessageDialog(null, "서버에 연결할 수 없습니다. 프로그램을 종료합니다.");
			System.exit(0);
		}
	}

	public void SendMessage(int number, Object o) {
		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(sock.getOutputStream());

			oos.writeInt(number);
			oos.writeObject(o);

			oos.flush();

		} catch (IOException e) {
			System.out.println("서버와의 연결이 끊어졌습니다.  프로그램을 종료합니다.");
			JOptionPane.showMessageDialog(null, "서버와의 연결이 끊어졌습니다.  프로그램을 종료합니다.");
			System.exit(0);
		}
	}

	public void sendImage(String path) {
		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(sock.getOutputStream());

			BufferedImage im = ImageIO.read(new File(path));
			System.out.println(im.toString());
			ImageIO.write(im, "jpg", oos);
			oos.flush();
			oos.close();
			makeConnection();
			System.out.println("이미지 보냄");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Object receiveObject() {
		ObjectInputStream ois = null;
		Object obj = null;
		try {
			ois = new ObjectInputStream(sock.getInputStream());
			obj = ois.readObject();
		} catch (IOException e) {
			System.out.println("서버와의 연결이 끊어졌습니다.  프로그램을 종료합니다.");
			JOptionPane.showMessageDialog(null, "서버와의 연결이 끊어졌습니다.  프로그램을 종료합니다.");
			System.exit(0);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return obj;
	}
}
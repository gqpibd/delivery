package communicator;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketControl {

	public void serverOpen() {
		ServerSocket serSocket = null;
		try {
			serSocket = new ServerSocket(6000);

			while (true) {
				System.out.println("접속 대기중...");
				Socket socket = serSocket.accept();
				System.out.println("클라이언트 연결 ip:" + socket.getInetAddress());

				new ReadThread(socket).start();
				Thread.sleep(100);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			try {
				serSocket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}

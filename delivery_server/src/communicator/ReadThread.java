package communicator;

import java.net.Socket;

public class ReadThread extends Thread {
	Socket sock;

	public ReadThread(Socket sock) {
		this.sock = sock;
	}

	@Override
	public void run() {
		
	}
}

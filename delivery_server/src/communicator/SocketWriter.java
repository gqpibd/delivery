package communicator;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class SocketWriter<T> {
	public static <T> void Write(Socket sock, T o) {		
		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(sock.getOutputStream());
			oos.writeObject(o);
			oos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

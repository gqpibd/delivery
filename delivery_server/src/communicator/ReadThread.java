package communicator;

import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

import dao.MemberDao;
import dao.OrderDao;
import dao.ReviewDao;
import dao.SelectionsDao;
import dto.MemberDto;
import dto.OrderDto;
import dto.ReviewDto;

public class ReadThread extends Thread {
	Socket sock;
	MemberDao mDao = new MemberDao();
	OrderDao oDao = new OrderDao();
	ReviewDao rDao = new ReviewDao();
	SelectionsDao sDao = new SelectionsDao();

	public ReadThread(Socket sock) {
		this.sock = sock;
	}

	@Override
	public void run() {
		ObjectInputStream ois = null;

		try {
			while (true) {
				ois = new ObjectInputStream(sock.getInputStream()); // dto받기

				int number = ois.readInt(); // 어떤 명령인지 받고
				Object obj = ois.readObject(); // dto를 받는다

				// dto에 따라 어디서 수행할 지 결정된다.
				if (obj instanceof MemberDto) { // 로그인, 회원가입
					mDao.execute(number, (MemberDto) obj, sock);
				} else if (obj instanceof OrderDto) { 
					oDao.execute(number, (OrderDto) obj, sock);
				} else if ((obj instanceof ReviewDto)) { 
					rDao.execute(number, (ReviewDto) obj, sock);
				} else { // 정의된 dto가 아닌 경우 (String인 경우)
					sDao.execute(number, obj, sock);
					
				}
				sleep(100);
			}
		} catch (EOFException e) {
			System.out.println("다 읽음");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("소켓이 닫혔습니다");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

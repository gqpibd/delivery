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
   public static int INSERT = 0;
   public static int DELETE = 1;
   public static int UPDATE = 2;
   public static int SELECT = 3;
   private Socket sock;

   public void makeConnection() {
      try {
         InetSocketAddress sockAddr = new InetSocketAddress("127.0.0.1", 6000); // ?��?��번호?�� ?��버의 ?��?��번호?? ?��?��?���? ?���??��.
         sock = new Socket();
         sock.connect(sockAddr);
         System.out.println("?��결성�?");

      } catch (IOException e) {
         System.out.println("?��버에 ?��결할 ?�� ?��?��?��?��. ?��로그?��?�� 종료?��?��?��.");
         JOptionPane.showMessageDialog(null, "?��버에 ?��결할 ?�� ?��?��?��?��. ?��로그?��?�� 종료?��?��?��.");
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
         System.out.println("?��버�??�� ?��결이 ?��?��졌습?��?��.  ?��로그?��?�� 종료?��?��?��.");
         JOptionPane.showMessageDialog(null, "?��버�??�� ?��결이 ?��?��졌습?��?��.  ?��로그?��?�� 종료?��?��?��.");
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
         System.out.println("?��미�? 보냄");

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
         System.out.println("?��버�??�� ?��결이 ?��?��졌습?��?��.  ?��로그?��?�� 종료?��?��?��.");
         JOptionPane.showMessageDialog(null, "?��버�??�� ?��결이 ?��?��졌습?��?��.  ?��로그?��?�� 종료?��?��?��.");
         System.exit(0);
      } catch (ClassNotFoundException e) {
         e.printStackTrace();
      }

      return obj;
   }

}
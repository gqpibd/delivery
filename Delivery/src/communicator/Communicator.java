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
         InetSocketAddress sockAddr = new InetSocketAddress("127.0.0.1", 6000); // ?¬?Š¸ë²ˆí˜¸?Š” ?„œë²„ì˜ ?¬?Š¸ë²ˆí˜¸?? ?™?¼?•˜ê²? ?•´ì¤??‹¤.
         sock = new Socket();
         sock.connect(sockAddr);
         System.out.println("?—°ê²°ì„±ê³?");

      } catch (IOException e) {
         System.out.println("?„œë²„ì— ?—°ê²°í•  ?ˆ˜ ?—†?Šµ?‹ˆ?‹¤. ?”„ë¡œê·¸?¨?„ ì¢…ë£Œ?•©?‹ˆ?‹¤.");
         JOptionPane.showMessageDialog(null, "?„œë²„ì— ?—°ê²°í•  ?ˆ˜ ?—†?Šµ?‹ˆ?‹¤. ?”„ë¡œê·¸?¨?„ ì¢…ë£Œ?•©?‹ˆ?‹¤.");
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
         System.out.println("?„œë²„ì??˜ ?—°ê²°ì´ ?Š?–´ì¡ŒìŠµ?‹ˆ?‹¤.  ?”„ë¡œê·¸?¨?„ ì¢…ë£Œ?•©?‹ˆ?‹¤.");
         JOptionPane.showMessageDialog(null, "?„œë²„ì??˜ ?—°ê²°ì´ ?Š?–´ì¡ŒìŠµ?‹ˆ?‹¤.  ?”„ë¡œê·¸?¨?„ ì¢…ë£Œ?•©?‹ˆ?‹¤.");
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
         System.out.println("?´ë¯¸ì? ë³´ëƒ„");

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
         System.out.println("?„œë²„ì??˜ ?—°ê²°ì´ ?Š?–´ì¡ŒìŠµ?‹ˆ?‹¤.  ?”„ë¡œê·¸?¨?„ ì¢…ë£Œ?•©?‹ˆ?‹¤.");
         JOptionPane.showMessageDialog(null, "?„œë²„ì??˜ ?—°ê²°ì´ ?Š?–´ì¡ŒìŠµ?‹ˆ?‹¤.  ?”„ë¡œê·¸?¨?„ ì¢…ë£Œ?•©?‹ˆ?‹¤.");
         System.exit(0);
      } catch (ClassNotFoundException e) {
         e.printStackTrace();
      }

      return obj;
   }

}
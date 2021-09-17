package movieReservation;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Web2ClientTest1 {
   static Op_Authority authority;
   static Data data;
   static Operator operator;
   static Reservation reservation;
   static Login login;
   static Register register;
   static FindPw findpw;

   public static void main(String[] args) throws Exception {
  //    Register register = new Register();
   //   FindPw findpw = new FindPw();
      //data = new Data();
      login = new Login();
      login.setVisible(true);
     // register = new Register();
   //   findpw = new FindPw();
     // reservation = new Reservation();
      //operator = new Operator();
      //authority = new Op_Authority();

      ///////////////////////////////////////////////////////////////////////////////////////
      Socket socket = null;
      PrintWriter out = null;
      BufferedReader in = null;
      ObjectInputStream ois = null; // �����κ��� �о���ϰ�ü
      ObjectOutputStream oos = null;
     // ObjectInputStream oisMovie = null;
     // ObjectInputStream oisTheater = null;
      try {
         socket = new Socket("127.0.0.1", 7455); // �Ǵ� "localhost"
         out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "utf-8"), true);
         in = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8"));
         ois = new ObjectInputStream(socket.getInputStream());
         oos = new ObjectOutputStream(socket.getOutputStream());
      //   oisMovie = new ObjectInputStream(new FileInputStream("movies.dat"));
//         oisTheater = new ObjectInputStream(new FileInputStream("theaters.dat"));
      } catch (UnknownHostException e) {
         System.err.println("localhost�� ������ �� �����ϴ�.");
         // e.printStackTrace();
         System.exit(-1);
      } catch (IOException e) {
         // e.printStackTrace();
         System.err.println("����� ����11");
         System.exit(-1);
      }

      Scanner sc = new Scanner(System.in);

      String message; // �����κ����� ���ڿ�.
      // String movie;
      // ArrayList<Movie> movies = new ArrayList<Movie>();//����ڰ� ������ ��ȭ������Ƶδ°�.
      // ArrayList<Theater>theaters = new ArrayList<Theater>();//����ڰ�������

      // ObjectOutputStream oos1 = new ObjectOutputStream(new
      // FileOutputStream("movieList.dat"));

      while ((message = in.readLine()) != null) {

         System.out.println("���� : " + message);

         System.out.print("�Է� : ");
         message = sc.nextLine();
         if (message.equals("��"))
            break;
         

         out.println(message);//������ ������.

         switch (message) {
         case "��ȭ":
            MovieInfo userPickMovie = (MovieInfo) (ois.readObject());// �����κ�����ü
            // Movie userPickScreenMovie = (Movie)(oisMovie.readObject());//movieList.dat������.
            System.out.println("<�����κ��� ���� ��ȭ��ü1>");

            System.out.println("��ü1�� ����: " + userPickMovie);

            // System.out.print("��ȭ�� �����ϼ���: ");
            // movie = sc.nextLine();
            // String innerMessage = "";
            break;

        
      }
      System.out.println("���α׷� ����.");

      out.close();
      
      in.close();
      oos.close();
      ois.close();
      sc.close();
      socket.close();
   }

}
}
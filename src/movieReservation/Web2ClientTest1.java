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
      ObjectInputStream ois = null; // 서버로부터 읽어들일객체
      ObjectOutputStream oos = null;
     // ObjectInputStream oisMovie = null;
     // ObjectInputStream oisTheater = null;
      try {
         socket = new Socket("127.0.0.1", 7455); // 또는 "localhost"
         out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "utf-8"), true);
         in = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8"));
         ois = new ObjectInputStream(socket.getInputStream());
         oos = new ObjectOutputStream(socket.getOutputStream());
      //   oisMovie = new ObjectInputStream(new FileInputStream("movies.dat"));
//         oisTheater = new ObjectInputStream(new FileInputStream("theaters.dat"));
      } catch (UnknownHostException e) {
         System.err.println("localhost에 접근할 수 없습니다.");
         // e.printStackTrace();
         System.exit(-1);
      } catch (IOException e) {
         // e.printStackTrace();
         System.err.println("입출력 오류11");
         System.exit(-1);
      }

      Scanner sc = new Scanner(System.in);

      String message; // 서버로부터의 문자열.
      // String movie;
      // ArrayList<Movie> movies = new ArrayList<Movie>();//사용자가 선택한 영화들을담아두는곳.
      // ArrayList<Theater>theaters = new ArrayList<Theater>();//사용자가선택한

      // ObjectOutputStream oos1 = new ObjectOutputStream(new
      // FileOutputStream("movieList.dat"));

      while ((message = in.readLine()) != null) {

         System.out.println("서버 : " + message);

         System.out.print("입력 : ");
         message = sc.nextLine();
         if (message.equals("끝"))
            break;
         

         out.println(message);//서버로 날린다.

         switch (message) {
         case "영화":
            MovieInfo userPickMovie = (MovieInfo) (ois.readObject());// 서버로보낼객체
            // Movie userPickScreenMovie = (Movie)(oisMovie.readObject());//movieList.dat에있음.
            System.out.println("<서버로부터 받은 영화객체1>");

            System.out.println("객체1의 내용: " + userPickMovie);

            // System.out.print("영화를 선택하세요: ");
            // movie = sc.nextLine();
            // String innerMessage = "";
            break;

        
      }
      System.out.println("프로그램 종료.");

      out.close();
      
      in.close();
      oos.close();
      ois.close();
      sc.close();
      socket.close();
   }

}
}
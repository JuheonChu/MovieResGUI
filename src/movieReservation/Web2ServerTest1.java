package movieReservation;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;

class MovieInfo implements Serializable {
   
   
   ArrayList<MovieVO> movies = new ArrayList<MovieVO>(); // 전체영화목록.
   double priceSettled;
   double [] price;
   
   
   public ArrayList<MovieVO> getMovies() {
      return movies;
   }

   public void setMovies(ArrayList<MovieVO> movies) {
      this.movies = movies;
   }

   public MovieInfo() {
      
      
	   try {
		     movies.add(new MovieVO("신촌", "라라랜드", 21, new String[]{"16:45"},7000, null));
	         movies.add(new MovieVO("구로", "라라랜드", 21, new String[]{"19:10"}, 7000, null ));
	         movies.add(new MovieVO("홍대", "라라랜드", 21, new String[]{"21:35"}, 7000, null ) );
	         movies.add(new MovieVO("명동", "라라랜드", 23, new String[]{"09:30"}, 7000 , null));
	         
	         movies.add(new MovieVO("신촌", "조커", 21, new String[]{"19:10"}, 9500, null));
	         movies.add(new MovieVO("구로", "조커", 21, new String[]{"16:15"}, 9500, null));
	         movies.add(new MovieVO("홍대", "조커", 22, new String[]{"11:45"}, 9500, null));
	         movies.add(new MovieVO("명동", "조커", 23, new String[]{"14:35"}, 9500, null));
	         
	         movies.add(new MovieVO("신촌", "타이타닉", 22, new String[]{"21:50"}, 8500, null));
	         movies.add(new MovieVO("구로", "타이타닉", 23, new String[]{"09:25"}, 8500, null));
	         movies.add(new MovieVO("홍대", "타이타닉", 23, new String[]{"11:50"}, 8500, null));
	         movies.add(new MovieVO("명동", "타이타닉", 23, new String[]{"14:15"}, 8500, null));
	         
	         movies.add(new MovieVO("신촌", "아이언맨", 22, new String[]{"15:45"}, 11000, null));
	         movies.add(new MovieVO("구로", "아이언맨", 23, new String[]{"17:50"}, 11000, null));
	         movies.add(new MovieVO("홍대", "아이언맨", 23, new String[]{"19:55","12:10"}, 11000, null));
	         movies.add(new MovieVO("명동", "아이언맨", 23, new String[]{"09:45","14:35"}, 11000, null));
	         }catch(Exception e) {
	        	 e.printStackTrace();
	         }
   }

   public double[] getPrice() {
      return price;
   }

   public void setPrice(double[] price) {
      this.price = price;
   }

   public double getPriceSettled() {
      return priceSettled;
   }

   public void setPriceSettled(double priceSettled) {
      this.priceSettled = priceSettled;
   }

@Override
public String toString() {
	return "MovieInfo [movies=" + movies + ", priceSettled=" + priceSettled + ", price=" + Arrays.toString(price) + "]";
}

   
}

class Theater implements Serializable {
   String [] name;
   
   Theater(){
      name = new String[] {"구로", "화정", "대전", "용산", "부천"};
      
      /*try {
         ObjectOutputStream oos1  =  new ObjectOutputStream(new FileOutputStream("theaters.dat"));
         
         
         oos1.writeObject(name);
         
         oos1.close();
      }catch(Exception e) {
         
      }*/
   }
   
   /*public boolean checkReservable() {
      if(ReserveSeat.selectedSeatNum.size() == 20) {
         return false;
      }
      else {
         return true;
      }
   }*/
   
   public String[] getName() {
      return name;
   }

   @Override
   public String toString() {
      return "Theater [name=" + Arrays.toString(name) + "]";
   }
   
   
}


class Server extends Thread {

	int port = 7455;
	ServerSocket serverSocket = null;
	
	public void run() {
	try {
		serverSocket = new ServerSocket(port);
	} catch (IOException e1) {
   //System.out.println("다음의 포트 번호에 연결할 수 없습니다 : 9500");
   //e.printStackTrace();
		System.exit(-1);
	}

	Socket clientSocket = null;
	
	
while(true) {
	try {
		System.out.println("클라이언트 접속을 기다림.");//1번째실행문
		
		clientSocket = serverSocket.accept();
   
		//System.out.println("이쯤에서 에러가터질거야");
   
		PrintWriter out = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream(),"utf-8"), true);
		BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream(), "utf-8"));
		ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream());
		ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
		ObjectOutputStream oosMovie = new ObjectOutputStream(new FileOutputStream("movies.dat", true));
   
		//ObjectInputStream oisMembers = new ObjectInputStream(new FileInputStream("members.dat"));
		MovieInfo movie = new MovieInfo();
		ArrayList <MovieVO>list = movie.getMovies();
   
   
		String message;
   
		//out.println("접속되었습니다. (포트번호 : " + port + ")");
		
		out.println("{접속되었습니다. " + clientSocket.getInetAddress() + " : " + clientSocket.getPort() + "]");
		
		System.out.println("서버가 시작되었습니다.");//2번째실행문
   
		while ((message = in.readLine()) != null) {
				//message = in.readLine();
				System.out.println("클라이언트로부터 전송된 메시지 : " + message);
				if(message.equals("영화")) {
					oos.writeObject(new MovieInfo());//영화의 상영가능한날짜, 영화제목.
					out.println("영화정보완료 전송완료.");//클라이언트 영화상영시간대 0,1,2,3 선택한후에 실행됨
					oosMovie.writeObject(list);//서버가 파일보냄.
					//oosMovie.reset();
					//System.out.println(list + "보냄 완료");
					//oosMovie.writeObject(new Movie().getPrice());
					//oosMovie.flush();
				} 
				else {
					System.out.println("서버에서 !붙이기직전: " + message);
					out.println(message+"!");
				}
				if (message.equals("끝")) {
					break;
				}
         
			}//end of the while loop
			System.out.println("클라이언트가 나갔습니다. ");
   
			//oisMovie.close();
			//oisTheater.close();
			//oisMembers.close();
			oosMovie.close();
			out.close();
			in.close();
			oos.close();
			clientSocket.close();
			serverSocket.close();
  
		}catch(Exception e) {
			System.err.println("accept() 실패 ");
		}
		
		
		//e.printStackTrace();
		///System.exit(-1);
	

	} // end of the while loop

	}//end of the run()

	}



public class Web2ServerTest1 {
   
   
   
   public static void main(String[] args) throws IOException {
      
      //HashMap<String,Member>loginCheck = new HashMap<String,Member>();
	   new Server().start();
   }
}
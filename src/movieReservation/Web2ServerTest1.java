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
   
   
   ArrayList<MovieVO> movies = new ArrayList<MovieVO>(); // ��ü��ȭ���.
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
		     movies.add(new MovieVO("����", "��󷣵�", 21, new String[]{"16:45"},7000, null));
	         movies.add(new MovieVO("����", "��󷣵�", 21, new String[]{"19:10"}, 7000, null ));
	         movies.add(new MovieVO("ȫ��", "��󷣵�", 21, new String[]{"21:35"}, 7000, null ) );
	         movies.add(new MovieVO("��", "��󷣵�", 23, new String[]{"09:30"}, 7000 , null));
	         
	         movies.add(new MovieVO("����", "��Ŀ", 21, new String[]{"19:10"}, 9500, null));
	         movies.add(new MovieVO("����", "��Ŀ", 21, new String[]{"16:15"}, 9500, null));
	         movies.add(new MovieVO("ȫ��", "��Ŀ", 22, new String[]{"11:45"}, 9500, null));
	         movies.add(new MovieVO("��", "��Ŀ", 23, new String[]{"14:35"}, 9500, null));
	         
	         movies.add(new MovieVO("����", "Ÿ��Ÿ��", 22, new String[]{"21:50"}, 8500, null));
	         movies.add(new MovieVO("����", "Ÿ��Ÿ��", 23, new String[]{"09:25"}, 8500, null));
	         movies.add(new MovieVO("ȫ��", "Ÿ��Ÿ��", 23, new String[]{"11:50"}, 8500, null));
	         movies.add(new MovieVO("��", "Ÿ��Ÿ��", 23, new String[]{"14:15"}, 8500, null));
	         
	         movies.add(new MovieVO("����", "���̾��", 22, new String[]{"15:45"}, 11000, null));
	         movies.add(new MovieVO("����", "���̾��", 23, new String[]{"17:50"}, 11000, null));
	         movies.add(new MovieVO("ȫ��", "���̾��", 23, new String[]{"19:55","12:10"}, 11000, null));
	         movies.add(new MovieVO("��", "���̾��", 23, new String[]{"09:45","14:35"}, 11000, null));
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
      name = new String[] {"����", "ȭ��", "����", "���", "��õ"};
      
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
   //System.out.println("������ ��Ʈ ��ȣ�� ������ �� �����ϴ� : 9500");
   //e.printStackTrace();
		System.exit(-1);
	}

	Socket clientSocket = null;
	
	
while(true) {
	try {
		System.out.println("Ŭ���̾�Ʈ ������ ��ٸ�.");//1��°���๮
		
		clientSocket = serverSocket.accept();
   
		//System.out.println("���뿡�� �����������ž�");
   
		PrintWriter out = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream(),"utf-8"), true);
		BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream(), "utf-8"));
		ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream());
		ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
		ObjectOutputStream oosMovie = new ObjectOutputStream(new FileOutputStream("movies.dat", true));
   
		//ObjectInputStream oisMembers = new ObjectInputStream(new FileInputStream("members.dat"));
		MovieInfo movie = new MovieInfo();
		ArrayList <MovieVO>list = movie.getMovies();
   
   
		String message;
   
		//out.println("���ӵǾ����ϴ�. (��Ʈ��ȣ : " + port + ")");
		
		out.println("{���ӵǾ����ϴ�. " + clientSocket.getInetAddress() + " : " + clientSocket.getPort() + "]");
		
		System.out.println("������ ���۵Ǿ����ϴ�.");//2��°���๮
   
		while ((message = in.readLine()) != null) {
				//message = in.readLine();
				System.out.println("Ŭ���̾�Ʈ�κ��� ���۵� �޽��� : " + message);
				if(message.equals("��ȭ")) {
					oos.writeObject(new MovieInfo());//��ȭ�� �󿵰����ѳ�¥, ��ȭ����.
					out.println("��ȭ�����Ϸ� ���ۿϷ�.");//Ŭ���̾�Ʈ ��ȭ�󿵽ð��� 0,1,2,3 �������Ŀ� �����
					oosMovie.writeObject(list);//������ ���Ϻ���.
					//oosMovie.reset();
					//System.out.println(list + "���� �Ϸ�");
					//oosMovie.writeObject(new Movie().getPrice());
					//oosMovie.flush();
				} 
				else {
					System.out.println("�������� !���̱�����: " + message);
					out.println(message+"!");
				}
				if (message.equals("��")) {
					break;
				}
         
			}//end of the while loop
			System.out.println("Ŭ���̾�Ʈ�� �������ϴ�. ");
   
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
			System.err.println("accept() ���� ");
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
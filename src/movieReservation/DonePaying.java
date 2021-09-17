package movieReservation;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

class Canvas2 extends Canvas{
   private ImageIcon movieIcon = new ImageIcon("OIP.jpg");
   private Image img;
   public Canvas2() {
      setBackground(Color.WHITE);
      setSize(350,650);
      img = movieIcon.getImage();
      
   }
   
   @Override
   public void paint(Graphics g) {
      g.setColor(Color.blue);
      g.fillRect(0, 0,350, 50);
      g.setColor(Color.GRAY);
      g.fillRect(0, 50, 350, 30);
      g.drawImage(img, 120, 0, 100, 90, Color.DARK_GRAY, null);
   }
   
}
public class DonePaying extends JFrame{
   private MovieInfo movie;
   private Theater theater;
   ////////////////////////////////
   private JLabel reserved;
//////////////////////////////////////   
   private JPanel panel;
   private JLabel movieTitle;
   private JLabel availableTimeforMovie;
   private JLabel people;
   private JLabel seatNum; 
   private JLabel amountofMoney;
   private JLabel reservationDate;
////////////////////////////////////////////////   
   
   private JLabel movieName;//데이터를 사용자가선택한영화로부터 불러와야함.
   private JLabel movieAvailableTime;
   private JLabel ppl;//예매인원
   private JLabel seatNo; 
   private JLabel pay;
   private JLabel reservationDatefinal;
////////////////////////////////////////////////////////////
   private JButton goBacktoMain;
   private JButton terminate;
   ///////////////////////////////////
   private String id;
   private String pw;
   private String name;
   private String place;
   private String title;
   private int day;
   private String movieTime;
   private double price;
   private ArrayList<Integer>selectedSeatNum;
   
   
   public DonePaying(String id, String pw, String name, String place, String title, int day, String movieTime, double price, ArrayList<Integer> selectedSeatNum) {
	  this.id = id;
      this.pw = pw;
      this.name = name;
	  this.place = place;
      this.title = title;
      this.day = day;
      this.movieTime = movieTime;
      this.price = price;
      this.selectedSeatNum = selectedSeatNum;
      System.out.println(id+ pw+name+place+title+day+movieTime+ price+selectedSeatNum);
      
      setTitle("1조 CGV 영화 예매 프로그램");//1조 CGV 영화예매 프로그램
      setBounds(0, 0,350, 650);
      setLayout(null);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      panel = new JPanel();
      panel.setLayout(null);
      setContentPane(panel);
      panel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null,null,null));
      panel.setBounds(0, 80,350, 620);
      
      reserved = getJLabel("예매 완료",90);
      reserved.setBounds(125,90,100, 20);
      reserved.setFont(new Font("굴림체", Font.BOLD, 20));
      
      movieTitle = getJLabel("영화제목: ",150);
      availableTimeforMovie = getJLabel("상영일자: ", 210);
      people = getJLabel("예매인원: ", 270);
      seatNum = getJLabel("좌석번호: ", 330);
      amountofMoney = getJLabel("결제금액: ", 390);
      reservationDate = getJLabel("예약일자: ", 450);
//////////////////////////////////////////////////////////////////////////////////////////      
      movieName = getJLabel(150, "");
      movieName.setText(title);
      /*
       * 서버로부터 데이터를 읽어와야함. 읽어온값을 MovieName으로 설정
       */
      //movieName.setText(getMovie().getName());
//////////////////////////////////////////////////////////////////////////////////////////
      /*ObjectInputStream ois = null;
      ObjectOutputStream oos = null;
      
      try {
         ois = new ObjectInputStream(
               new FileInputStream("movies.dat")// 영화정보 불러들인다.
               );
         oos = new ObjectOutputStream(new FileOutputStream("seats.dat"));//서버에 좌석정보 저장.
         Movie userPickMovie = (Movie)(ois.readObject());
         oos.writeObject(ReserveSeat.selectedSeatNum);
         Iterator <String> it = userPickMovie.getHashMap().keySet().iterator();
         
         while(it.hasNext()) {
            String movieTitle = it.next();
            if(movieTitle.equals("라라랜드")) {
               movieName.setText(movieTitle);
               
            }
            else if(movieTitle.equals("조커")) {
               movieName.setText(movieTitle);
            }else if(movieTitle.equals("타이타닉")) {
               movieName.setText(movieTitle);
            }else if(movieTitle.equals("아이언맨")) {
               movieName.setText(movieTitle);
            }
         }
         
      }catch(Exception e) {
         e.printStackTrace();
      }*/
//////////////////////////////////////////////////////////////////////////////////      
      
      movieAvailableTime = getJLabel(210, "");
      movieAvailableTime.setText("7월 " + day + "일 " +  movieTime);
      /*
       * 서버로부터 데이터를 읽어와야함. 읽어온값을 movieAvailableTime으로설정
       */
      
      ppl = getJLabel(270, "");
      ppl.setText(String.valueOf(selectedSeatNum.size()));
      
      /*
       * 좌석정보서버로 보내야됨.
       */
      seatNo = getJLabel(330, "");
      seatNo.setText(String.valueOf(selectedSeatNum));
      
      pay = getJLabel(390, "");
      //pay.setBounds(150,390, 100,20);
      pay.setText(Payment.totalPayment.getText());
      
      
      reservationDatefinal =getJLabel(450, "");
      reservationDatefinal.setBounds(140,450, 350,20);
      
      long time = System.currentTimeMillis(); 

      SimpleDateFormat dayTime = new SimpleDateFormat(
            "yyyy-MM-dd hh:mm:ss");
      String str = dayTime.format(new Date(time));


      System.out.println("앱결제한 시간: " + str);
      reservationDatefinal.setText(String.valueOf(str));
      
      goBacktoMain = new JButton("메인으로 돌아가기");
      goBacktoMain.setBounds(10, 490, 320, 30);
      goBacktoMain.setBackground(new Color(38, 75,128));
      goBacktoMain.setForeground(new Color(114, 114, 114));
      goBacktoMain.setFont(new Font("굴림체", Font.BOLD, 15));
      goBacktoMain.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
        	 dispose();
        	 new Reservation(id,pw,name).setVisible(true);
            // new Mainpanelboard();
            
         }
      });
      panel.add(goBacktoMain);
      
      terminate = new JButton("프로그램 종료하기");
      terminate.setBounds(10,530, 320, 30);
      terminate.setBackground(Color.DARK_GRAY);
      terminate.setForeground(new Color(114, 114, 114));
      terminate.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            System.exit(0);
            
         }
      });
      panel.add(terminate);
      
      panel.add(new Canvas2());
   }
   
   private JPanel getJPanel() {
      return this.panel;
   }
   
   
   private JLabel getJLabel(String name, int y) {
      JLabel label = new JLabel(name);
      label.setFont(new Font("굴림체", Font.BOLD, 15));
      label.setOpaque(true);
      label.setBounds(10,y, 100,20);
      label.setForeground(Color.white);
      label.setBackground(Color.white);
      label.setEnabled(false);
      this.getJPanel().add(label);
      label.setText(name);
      
      return label;
   }
   
   public JLabel getJLabel(int y, String name) {
      JLabel label = new JLabel(name);
      label.setFont(new Font("굴림체", Font.BOLD, 15));
      label.setOpaque(true);
      label.setBounds(180,y, 150,20);
      label.setForeground(Color.white);
      label.setBackground(Color.white);
      label.setEnabled(false);
      label.setHorizontalAlignment(SwingConstants.RIGHT);
      this.getJPanel().add(label);
      label.setText(name);
      
      return label;
   }
   
   public void setMovie(MovieInfo movie) {
      this.movie = movie;
   }
   
   public MovieInfo getMovie() {
      movie = new MovieInfo();
      return movie;
   }
   

   public JLabel getSeatNo() {
      return seatNo;
   }
   
   
}
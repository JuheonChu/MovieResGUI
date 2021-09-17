package movieReservation;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.StreamCorruptedException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;


public class MovieSelect extends JFrame {
   public String getPlace() {
      return place;
   }

   public void setPlace(String place) {
      this.place = place;
   }

   public int getDay() {
      return day;
   }

   public void setDay(int day) {
      this.day = day;
   }

   private JPanel panel;// 파란색
   private MainCanvas1 canvas;
   private JButton before; // 이전으로
   private JPanel movieList; // movie객체의 정보를 담은 패널
//   private ArrayList<Movie2> list;   //movie객체들을 갖고 있는 리스트
   private JLabel movieTitle; // movie객체에서 getTitle을 해서 값 세팅
   private JButton movieTime; // movie객체에서 getTime을 해서 값 세팅
   
   private String id;
   private String pw;
   private String name;
   private String place;
   private int day;

   private ArrayList<MovieVO> list = new ArrayList<MovieVO>(); // 서버에서 전체 영화 정보를 불러옴
   private ArrayList<MovieVO> selectList = new ArrayList<MovieVO>(); // 우리가 선택한 장소의 영화 정보만 담을것임.

   public MovieSelect(String id, String pw, String name, String place, int day) {
     this.id = id;
     this.pw = pw;
     this.name = name;
      this.place = place;
      this.day = day;
      System.out.println("선택한 장소는 " + place + " 날짜는 " + day + "일입니다.");
//      reserve = new ReserveMovieVO(place, day);
      setTitle("영화 시간 선택");// 1조 CGV 영화예매 프로그램
      setBounds(0, 0, 350, 570);
      setLayout(null);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      // 임의의 데이터를 ArrayList에 넣음
//         list = new ArrayList<Movie2>();
//         String time[] = {"18:50", "20:00"};
//         Movie2 m1 = new Movie2("라라랜드", time);
//         list.add(m1);
//         String time2[] = {"09:30", "13:00", "21:15"};
//         Movie2 m2 = new Movie2("조커", time2);
//         list.add(m2);
//         String time3[] = {"07:30", "12:00", "22:15"};
//         Movie2 m3 = new Movie2("아이언맨", time2);
//         list.add(m3);
      panel = new JPanel();
      panel.setLayout(null);
      setContentPane(panel);
      panel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
      panel.setBounds(0, 80, 350, 620);

      ObjectInputStream oisMovie = null;

      try {
         // Movie movie = new Movie();
         System.out.println("try 구문들어옴.");
         oisMovie = new ObjectInputStream(new FileInputStream("movies.dat"));
         System.out.println("파일로부터데이터읽을준비..");// 여기까진들어옴..
         // System.out.println(ois.readObject());
         list = (ArrayList<MovieVO>) (oisMovie.readObject());// 이게문제네... 이 라인 밑으론실행안됨..(예외발생구역)
         // System.out.println("MovieVO 객체는 "+ list);
         // list = (ArrayList<MovieVO>)(ois.readObject());
         oisMovie.close();
      } catch (FileNotFoundException e1) {
         // TODO Auto-generated catch block
         e1.printStackTrace();
      } catch (StreamCorruptedException e1) {
         e1.printStackTrace();
      } catch (Exception e) {
         System.out.println("객체못읽음 ");
         e.getCause();
      }

      System.out.println("MovieVO객체를 담는 ArrayList " + list);

      for (int i = 0; i < list.size(); i++) {
         if (list.get(i).getPlace().equals(getPlace()) && list.get(i).getDay() == getDay()) {// 값을 비교해서 우리가 선택한 값이랑
                                                                        // 리스트의 무비 값이랑 같은지 비교
            selectList.add(list.get(i)); // 같으면 선택리스트에 추가
         }
      }
      System.out.println(selectList + "사용자가선택한 목록 리스트");

      /////// 여기까지는에러해결///////////////
      System.out.println(selectList.size() + "사용자가선택한목록 갯수");
      System.out.println(list.size() + "전체영화목록 갯수");

      // selectList가 비었으면 상영할 영화가 없다고 함
      if (selectList.isEmpty()) {
         JLabel nothing = new JLabel(
               "<html>"
               + "<div align=\"center\">해당 날짜에<br /> 상영하는 영화가 없습니다.</div>"
               + "</html>"
               );
         nothing.setFont(new Font("맑은 고딕", Font.BOLD, 18));
         nothing.setHorizontalAlignment(JLabel.CENTER);
         nothing.setVerticalAlignment(JLabel.CENTER);
         nothing.setBounds(0, 80, 350, 80);
         nothing.setOpaque(true);
         nothing.setForeground(Color.black);
         nothing.setBackground(Color.white);
         panel.add(nothing);
      }

      // 패널 생성
      for (int i = 0; i < selectList.size(); i++) {// list자체의 크기가0이라 답이없음..
         movieList = new JPanel();
         movieList.setLayout(new FlowLayout(FlowLayout.LEFT));
//            movieList.setLayout(null);
         movieList.setBackground(Color.white);
         movieList.setBounds(10, 80 * (i + 1), 350, 80);

         // 영화 제목 설정
         movieTitle = new JLabel(selectList.get(i).getTitle());
         movieTitle.setFont(new Font("맑은 고딕", Font.BOLD, 14));
         movieTitle.setForeground(Color.black);
//            movieTitle.setBounds(0, 90*(i+1), 350, 30);
         movieList.add(movieTitle);

         // 영화 시간 가져와서 세팅===> 라라랜드영화가 있으면 그날 그상영관에 몇시게 있는지 떠야된다.

         for (int j = 0; j < selectList.get(i).getTime().length; j++) {
            /*
             * if(j == 1) { continue; }
             */
            movieTime = new JButton();
            movieTime.setText(selectList.get(i).getTime()[j]);
            movieTime.setFont(new Font("맑은 고딕", Font.BOLD, 14));
            movieTime.setBackground(new Color(230, 236, 240));
            movieTime.setForeground(new Color(114, 114, 114));
            movieTime.setBorderPainted(false);
//                movieTime.setBounds(10 + (i*60), 10, 80, 60);

            movieTime.putClientProperty(movieTime.getText(), selectList.get(i));

            movieTime.addActionListener(new ActionListener() {

               @Override
               public void actionPerformed(ActionEvent e) {
                  dispose();
                  JButton btn = (JButton) e.getSource();
                  String time = btn.getText();
                  String title = ((MovieVO) (btn.getClientProperty(time))).getTitle();
                  double price = ((MovieVO) (btn.getClientProperty(time))).getPrice();
                  ReserveSeat seat = new ReserveSeat(id,pw,name,place, title, day, time,price);
                  seat.setVisible(true);

//                  System.out.println(title);
//                  seat.setMovieTitle(title);
//                  seat.setDay(day);
//                  seat.setPlace(place);
//                  seat.setVisible(true);
               }
            });
            movieList.add(movieTime);
         }
         panel.add(movieList);
      }

      before = new JButton("이전으로");
      before.setBounds(10, 490, 320, 30);
      before.setBackground(Color.DARK_GRAY);
      before.setForeground(new Color(114, 114, 114));
      before.addActionListener(new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent e) {
            dispose();
            // new MovieDaySelect().setVisible(true);
            new DaySelectTheater(id,pw,name, getPlace()).setVisible(true);
         }
      });
      panel.add(before);

      panel.add(new MainCanvas1());
   }

   public int getCounthowManyMoviesOnthatDay() {
      int cnt = 0;
      for (int i = 0; i < selectList.size() - 1; i++) {
         String str = selectList.get(i).getTitle();
         for (int j = i + 1; j < selectList.size(); j++) {
            String title2 = selectList.get(j).getTitle();
            if (!(str.equals(title2))) {
               cnt++;
            }
         }
      }
      return cnt;
   }

   /*
    * public int getAvailableTimeforMovieOnthatDay() { int cnt = 0; for(int i = 0;
    * i < getCounthowManyMoviesOnthatDay();i++) { String title =
    * selectList.get(i).getTitle(); String time = selectList.get(i).getTime();
    * for(int j = i+1; j < selectList.size();j++) { String title2 =
    * selectList.get(j).getTitle(); if(!(title.equals(title2)) && ) } } }
    */

}
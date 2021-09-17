package movieReservation;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;


public class TheatherSelect extends JFrame {
   private JPanel panel;// 파란색
   private MainCanvas1 canvas;
   private JButton before; // 이전으로
   private JPanel movieList; // movie객체의 정보를 담은 패널
//   private ArrayList<Movie2> list;   //movie객체들을 갖고 있는 리스트
   private JLabel moviePlace; // movie객체에서 getTitle을 해서 값 세팅
   private JButton movieTime; // movie객체에서 getTime을 해서 값 세팅
   
   
   private String id;
   private String pw;
   private String name;
   private String selectTitle;
   private int day;

   public String getSelectTitle() {
      return selectTitle;
   }

   public void setSelectTitle(String selectTitle) {
      this.selectTitle = selectTitle;
   }

   public int getDay() {
      return day;
   }

   public void setDay(int day) {
      this.day = day;
   }

   private ArrayList<MovieVO> list = new ArrayList<MovieVO>();
   private ArrayList<MovieVO> selectList = new ArrayList<MovieVO>();

   public TheatherSelect(String id, String pw, String name,String selectTitle, int day) {
	  this.id = id;
	  this.pw = pw;
	  this.name = name;
      this.selectTitle = selectTitle;
      this.day = day;
      System.out.println("=========");
      System.out.println("상영관 선택창입니다.");
      System.out.println("사용자 선택 영화 제목 : " + getSelectTitle());
      System.out.println("사용자 선택 영화 날짜 : " + getDay());
      System.out.println("=========");
      setTitle("영화 시간 선택");// 1조 CGV 영화예매 프로그램
      setBounds(0, 0, 350, 570);
      setLayout(null);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      // 임의의 데이터를 ArrayList에 넣음
      /*
       * list = new ArrayList<Movie2>(); String time[] = {"18:50", "20:00"}; Movie2 m1
       * = new Movie2("신촌점", time); list.add(m1); String time2[] = {"09:30", "13:00",
       * "21:15"}; Movie2 m2 = new Movie2("홍대", time2); list.add(m2); String time3[] =
       * {"07:30", "12:00", "22:15"}; Movie2 m3 = new Movie2("구로", time2);
       * list.add(m3);
       */

      panel = new JPanel();
      panel.setLayout(null);
      setContentPane(panel);
      panel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
      panel.setBounds(0, 80, 350, 620);

      ObjectInputStream ois = null;
      try {
         System.out.println("try 구문들어옴.");
         ois = new ObjectInputStream(new FileInputStream("movies.dat"));
         System.out.println("파일로부터데이터읽을준비..");
         list = (ArrayList<MovieVO>)(ois.readObject());//IOException
         ois.close();
      } catch (FileNotFoundException e1) {
         e1.printStackTrace();
      } catch (IOException e1) {
         e1.printStackTrace();
      } catch (Exception e) {
         System.out.println("객체못읽음 ");
         e.getCause();
      }

      System.out.println("MovieVO 객체를 담는  ArrayList " + list);

      // 전체 리스트에서 우리가 원하는 조건이 맞는 MovieVO만 필터링
      for (int i = 0; i < list.size(); i++) {
         // 값을 비교해서 우리가 선택한 값이랑 리스트의 무비 값이랑 같은지 비교
         if (list.get(i).getTitle().equals(getSelectTitle())/*조커*/ && list.get(i).getDay()/*23일*/ == getDay()) {
            selectList.add(list.get(i)); // 같으면 선택리스트에 추가
         }
      }

      System.out.println(selectList + "사용자가 선택한 목록 리스트");
      System.out.println(selectList.size() + "사용자가 선택한 목록 개수");
      System.out.println(list.size() + "전체 영화 목록 개수");

      // selectList가 비었으면 상영할 영화가 없다고 함
      if (selectList.isEmpty()) {
         JLabel nothing = new JLabel(
               "<html>"
               + "<div align=\"center\">해당 날짜에<br /> 상영하는 상영관이 없습니다.</div>"
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
      for (int i = 0; i < selectList.size(); i++) {
         movieList = new JPanel();
         movieList.setLayout(new FlowLayout(FlowLayout.LEFT));
         movieList.setBackground(Color.white);
         movieList.setBounds(10, 80 * (i + 1), 350, 80);

         // 영화 장소 설정
         moviePlace = new JLabel(selectList.get(i).getPlace());
         moviePlace.setFont(new Font("맑은 고딕", Font.BOLD, 14));
         moviePlace.setForeground(Color.black);
         movieList.add(moviePlace);

         // 영화 시간 가져와서 세팅
         for (int j = 0; j < (selectList.get(i).getTime()).length; j++) {
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
                  String place = ((MovieVO) (btn.getClientProperty(time))).getPlace();
                  double price =((MovieVO) (btn.getClientProperty(time))).getPrice();
                  ReserveSeat seat = new ReserveSeat(id, pw, name, place, selectTitle, day, time,price);
                  seat.setVisible(true);
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
            new DaySelectMovie(id,pw,name, selectTitle).setVisible(true);
         }
      });
      panel.add(before);

      panel.add(new MainCanvas1());
   }

}
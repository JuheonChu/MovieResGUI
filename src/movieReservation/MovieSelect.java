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

   private JPanel panel;// �Ķ���
   private MainCanvas1 canvas;
   private JButton before; // ��������
   private JPanel movieList; // movie��ü�� ������ ���� �г�
//   private ArrayList<Movie2> list;   //movie��ü���� ���� �ִ� ����Ʈ
   private JLabel movieTitle; // movie��ü���� getTitle�� �ؼ� �� ����
   private JButton movieTime; // movie��ü���� getTime�� �ؼ� �� ����
   
   private String id;
   private String pw;
   private String name;
   private String place;
   private int day;

   private ArrayList<MovieVO> list = new ArrayList<MovieVO>(); // �������� ��ü ��ȭ ������ �ҷ���
   private ArrayList<MovieVO> selectList = new ArrayList<MovieVO>(); // �츮�� ������ ����� ��ȭ ������ ��������.

   public MovieSelect(String id, String pw, String name, String place, int day) {
     this.id = id;
     this.pw = pw;
     this.name = name;
      this.place = place;
      this.day = day;
      System.out.println("������ ��Ҵ� " + place + " ��¥�� " + day + "���Դϴ�.");
//      reserve = new ReserveMovieVO(place, day);
      setTitle("��ȭ �ð� ����");// 1�� CGV ��ȭ���� ���α׷�
      setBounds(0, 0, 350, 570);
      setLayout(null);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      // ������ �����͸� ArrayList�� ����
//         list = new ArrayList<Movie2>();
//         String time[] = {"18:50", "20:00"};
//         Movie2 m1 = new Movie2("��󷣵�", time);
//         list.add(m1);
//         String time2[] = {"09:30", "13:00", "21:15"};
//         Movie2 m2 = new Movie2("��Ŀ", time2);
//         list.add(m2);
//         String time3[] = {"07:30", "12:00", "22:15"};
//         Movie2 m3 = new Movie2("���̾��", time2);
//         list.add(m3);
      panel = new JPanel();
      panel.setLayout(null);
      setContentPane(panel);
      panel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
      panel.setBounds(0, 80, 350, 620);

      ObjectInputStream oisMovie = null;

      try {
         // Movie movie = new Movie();
         System.out.println("try ��������.");
         oisMovie = new ObjectInputStream(new FileInputStream("movies.dat"));
         System.out.println("���Ϸκ��͵����������غ�..");// �����������..
         // System.out.println(ois.readObject());
         list = (ArrayList<MovieVO>) (oisMovie.readObject());// �̰Թ�����... �� ���� �����н���ȵ�..(���ܹ߻�����)
         // System.out.println("MovieVO ��ü�� "+ list);
         // list = (ArrayList<MovieVO>)(ois.readObject());
         oisMovie.close();
      } catch (FileNotFoundException e1) {
         // TODO Auto-generated catch block
         e1.printStackTrace();
      } catch (StreamCorruptedException e1) {
         e1.printStackTrace();
      } catch (Exception e) {
         System.out.println("��ü������ ");
         e.getCause();
      }

      System.out.println("MovieVO��ü�� ��� ArrayList " + list);

      for (int i = 0; i < list.size(); i++) {
         if (list.get(i).getPlace().equals(getPlace()) && list.get(i).getDay() == getDay()) {// ���� ���ؼ� �츮�� ������ ���̶�
                                                                        // ����Ʈ�� ���� ���̶� ������ ��
            selectList.add(list.get(i)); // ������ ���ø���Ʈ�� �߰�
         }
      }
      System.out.println(selectList + "����ڰ������� ��� ����Ʈ");

      /////// ��������¿����ذ�///////////////
      System.out.println(selectList.size() + "����ڰ������Ѹ�� ����");
      System.out.println(list.size() + "��ü��ȭ��� ����");

      // selectList�� ������� ���� ��ȭ�� ���ٰ� ��
      if (selectList.isEmpty()) {
         JLabel nothing = new JLabel(
               "<html>"
               + "<div align=\"center\">�ش� ��¥��<br /> ���ϴ� ��ȭ�� �����ϴ�.</div>"
               + "</html>"
               );
         nothing.setFont(new Font("���� ���", Font.BOLD, 18));
         nothing.setHorizontalAlignment(JLabel.CENTER);
         nothing.setVerticalAlignment(JLabel.CENTER);
         nothing.setBounds(0, 80, 350, 80);
         nothing.setOpaque(true);
         nothing.setForeground(Color.black);
         nothing.setBackground(Color.white);
         panel.add(nothing);
      }

      // �г� ����
      for (int i = 0; i < selectList.size(); i++) {// list��ü�� ũ�Ⱑ0�̶� ���̾���..
         movieList = new JPanel();
         movieList.setLayout(new FlowLayout(FlowLayout.LEFT));
//            movieList.setLayout(null);
         movieList.setBackground(Color.white);
         movieList.setBounds(10, 80 * (i + 1), 350, 80);

         // ��ȭ ���� ����
         movieTitle = new JLabel(selectList.get(i).getTitle());
         movieTitle.setFont(new Font("���� ���", Font.BOLD, 14));
         movieTitle.setForeground(Color.black);
//            movieTitle.setBounds(0, 90*(i+1), 350, 30);
         movieList.add(movieTitle);

         // ��ȭ �ð� �����ͼ� ����===> ��󷣵念ȭ�� ������ �׳� �׻󿵰��� ��ð� �ִ��� ���ߵȴ�.

         for (int j = 0; j < selectList.get(i).getTime().length; j++) {
            /*
             * if(j == 1) { continue; }
             */
            movieTime = new JButton();
            movieTime.setText(selectList.get(i).getTime()[j]);
            movieTime.setFont(new Font("���� ���", Font.BOLD, 14));
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

      before = new JButton("��������");
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
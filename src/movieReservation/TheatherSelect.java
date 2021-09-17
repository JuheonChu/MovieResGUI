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
   private JPanel panel;// �Ķ���
   private MainCanvas1 canvas;
   private JButton before; // ��������
   private JPanel movieList; // movie��ü�� ������ ���� �г�
//   private ArrayList<Movie2> list;   //movie��ü���� ���� �ִ� ����Ʈ
   private JLabel moviePlace; // movie��ü���� getTitle�� �ؼ� �� ����
   private JButton movieTime; // movie��ü���� getTime�� �ؼ� �� ����
   
   
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
      System.out.println("�󿵰� ����â�Դϴ�.");
      System.out.println("����� ���� ��ȭ ���� : " + getSelectTitle());
      System.out.println("����� ���� ��ȭ ��¥ : " + getDay());
      System.out.println("=========");
      setTitle("��ȭ �ð� ����");// 1�� CGV ��ȭ���� ���α׷�
      setBounds(0, 0, 350, 570);
      setLayout(null);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      // ������ �����͸� ArrayList�� ����
      /*
       * list = new ArrayList<Movie2>(); String time[] = {"18:50", "20:00"}; Movie2 m1
       * = new Movie2("������", time); list.add(m1); String time2[] = {"09:30", "13:00",
       * "21:15"}; Movie2 m2 = new Movie2("ȫ��", time2); list.add(m2); String time3[] =
       * {"07:30", "12:00", "22:15"}; Movie2 m3 = new Movie2("����", time2);
       * list.add(m3);
       */

      panel = new JPanel();
      panel.setLayout(null);
      setContentPane(panel);
      panel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
      panel.setBounds(0, 80, 350, 620);

      ObjectInputStream ois = null;
      try {
         System.out.println("try ��������.");
         ois = new ObjectInputStream(new FileInputStream("movies.dat"));
         System.out.println("���Ϸκ��͵����������غ�..");
         list = (ArrayList<MovieVO>)(ois.readObject());//IOException
         ois.close();
      } catch (FileNotFoundException e1) {
         e1.printStackTrace();
      } catch (IOException e1) {
         e1.printStackTrace();
      } catch (Exception e) {
         System.out.println("��ü������ ");
         e.getCause();
      }

      System.out.println("MovieVO ��ü�� ���  ArrayList " + list);

      // ��ü ����Ʈ���� �츮�� ���ϴ� ������ �´� MovieVO�� ���͸�
      for (int i = 0; i < list.size(); i++) {
         // ���� ���ؼ� �츮�� ������ ���̶� ����Ʈ�� ���� ���̶� ������ ��
         if (list.get(i).getTitle().equals(getSelectTitle())/*��Ŀ*/ && list.get(i).getDay()/*23��*/ == getDay()) {
            selectList.add(list.get(i)); // ������ ���ø���Ʈ�� �߰�
         }
      }

      System.out.println(selectList + "����ڰ� ������ ��� ����Ʈ");
      System.out.println(selectList.size() + "����ڰ� ������ ��� ����");
      System.out.println(list.size() + "��ü ��ȭ ��� ����");

      // selectList�� ������� ���� ��ȭ�� ���ٰ� ��
      if (selectList.isEmpty()) {
         JLabel nothing = new JLabel(
               "<html>"
               + "<div align=\"center\">�ش� ��¥��<br /> ���ϴ� �󿵰��� �����ϴ�.</div>"
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
      for (int i = 0; i < selectList.size(); i++) {
         movieList = new JPanel();
         movieList.setLayout(new FlowLayout(FlowLayout.LEFT));
         movieList.setBackground(Color.white);
         movieList.setBounds(10, 80 * (i + 1), 350, 80);

         // ��ȭ ��� ����
         moviePlace = new JLabel(selectList.get(i).getPlace());
         moviePlace.setFont(new Font("���� ���", Font.BOLD, 14));
         moviePlace.setForeground(Color.black);
         movieList.add(moviePlace);

         // ��ȭ �ð� �����ͼ� ����
         for (int j = 0; j < (selectList.get(i).getTime()).length; j++) {
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

      before = new JButton("��������");
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
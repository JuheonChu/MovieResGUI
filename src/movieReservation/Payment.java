package movieReservation;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashSet;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

class Canvas1 extends Canvas {
   public Canvas1() {
      setBackground(Color.WHITE);
      setSize(350, 570);
   }

   @Override
   public void paint(Graphics g) {
      g.setColor(Color.blue);
      g.fillRect(0, 0, 350, 50);
      g.setColor(Color.GRAY);
      g.fillRect(0, 50, 350, 30);
   }

}

public class Payment extends JFrame {

   private JPanel panel;
   private JLabel payment;// �����ݾ�
   private JLabel movieTask;// �����׸�(deprecated)
   private JLabel totalPay;// �ѱݾ�
   private JLabel cardNum;// ī���ȣ
   private JLabel cardPw;// ��й�ȣ
   private JLabel settlePayment;// �����ݾ�(�����е�κ��� ������ ��ȭ�ǰ����Ѱܹ޾ƾߵ�)
   static JLabel totalPayment; // ��������� �����ݾ�.
   private String[] discounts = { "�ش���� (0��)", "����� ���� (20%)", "�������� (30%)", "Ư������ (40%)" };
   private JComboBox<String> dcList = new JComboBox<String>(discounts);
   private JPasswordField cardNum1;
   private JPasswordField cardNum2;
   private JPasswordField cardNum3;
   private JPasswordField cardNum4;
   private JPasswordField[] arr = new JPasswordField[4]; // ī���ȣ ġ�� 4���� �ؽ�Ʈ
   private JPasswordField cardPassWord;// ī���й�ȣ TextField
   private JButton pay;
   private JButton goBack;
   private boolean progress1, progress2;
   private ObjectOutputStream oos;
   private ObjectInputStream ois;
   private ArrayList<MovieVO> movieList;
   // private double price;

   ///////////////////////////////////////////////
   String id;
   String pw;
   String name;
   String place;
   String movieTitle;
   int day;
   String time;
   double price;
   ArrayList<Integer> selectedSeatNum;
   ArrayList<UserBookingInfo>bookingInfo;
   // int howMany;

   public Payment(String id, String pw, String name, String place, String movieTitle, int day, String time, double price,
         ArrayList<Integer> selectedSeatNum) {
	  this.id= id;
	  this.pw = pw;
	  this.name = name;
      this.place = place;
      this.movieTitle = movieTitle;
      this.day = day;
      this.time = time;
      this.price = price;
      this.selectedSeatNum = selectedSeatNum;
      // this.howMany = howMany;
      System.out.println("���: " + place + "\t" + "��ȭ����: " + movieTitle + "\t" + "��¥: " + day + "\t" + "�ð�: " + time
            + "\t" + "����: " + price + "\t" + "����ڰ� ������ �¼�: " + selectedSeatNum);

      setTitle("1�� CGV ��ȭ ���� ���α׷�");// 1�� CGV ��ȭ���� ���α׷�
      setBounds(0, 0, 350, 570);
      setLayout(null);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      panel = new JPanel();
      panel.setLayout(null);
      setContentPane(panel);
      panel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
      panel.setBounds(0, 80, 350, 620);

      payment = getJLabel("�����ݾ�: ", 120);
      movieTask = getJLabel("�󿵿�ȭ: ", 180);
      totalPay = getJLabel("�����ݾ�: ", 240);
      cardNum = getJLabel("ī���ȣ: ", 300);
      cardPw = getJLabel("��й�ȣ: ", 360);
      /////////////////////////////////////////////////////////////////////
      String[] movieTime = new String[] { time };
      // new MovieVO()

//      ObjectOutputStream oos = null;
      ArrayList<MovieVO> aList = new ArrayList<MovieVO>();
//      aList.add(new MovieVO(place, movieTitle, day, movieTime, price, selectedSeatNum));
//      try {
//         oos = new ObjectOutputStream(new FileOutputStream("userMovies.dat"));
//
//         oos.writeObject(aList);
//
//         oos.close();
//      } catch (Exception e) {
//         e.printStackTrace();
//      } // occurs ClassCastException

      settlePayment = getJLabel(100, 120, /* 7000���ɼ����Ұ��� */price + "��" + "*" + selectedSeatNum.size() + "��");
      // �̺κ��� ���߿��ݵ�� �����ϰԵɰ̴ϴ�
      // price * num
      movieTask = getJLabel(100, 180, movieTitle);
      totalPayment = getJLabel(100, 240, String.valueOf(price * selectedSeatNum.size())); // üũ�ڽ����� �̺�Ʈ���� ����
      

      cardNum1 = getJPasswordField(155);
      cardNum2 = getJPasswordField(155 + 45);
      cardNum3 = getJPasswordField(155 + (45 * 2));
      cardNum4 = getJPasswordField(155 + (45 * 3));
      cardNum4.addActionListener(new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent e) {
            String cardNum = String.valueOf(cardNum1.getPassword()).concat(String.valueOf(cardNum2.getPassword()))
                  .concat(String.valueOf(cardNum3.getPassword())).concat(String.valueOf(cardNum4.getPassword()));

            int cnt = 0;
            for (int i = 0; i < cardNum.length(); i++) {
               char ch = cardNum.charAt(i);
               if (Character.isDigit(ch)) {
                  cnt++;
               }
            }
            if (cnt == 16 && cardNum.length() == 16) {
               progress1 = true;
               setProgress1(progress1);
            } else {
               progress1 = false;
            }
            System.out.println("ī���ȣ�� ?" + cardNum);
            System.out.println("cardNumprogress�� ����? " + progress1);

         }
      });

      arr = new JPasswordField[] { cardNum1, cardNum2, cardNum3, cardNum4 };

      for (int i = 0; i < arr.length; i++) {
         arr[i].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
         });

         arr[i].addFocusListener(new FocusListener() {

            @Override
            public void focusLost(java.awt.event.FocusEvent e) {
               JPasswordField pw = (JPasswordField) e.getSource();
               if (String.valueOf(pw.getPassword()).trim().length() == 0) {
                  pw.setText("1234");
               }

            }

            @Override
            public void focusGained(java.awt.event.FocusEvent e) {
               JPasswordField pw = (JPasswordField) e.getSource();
               if (String.valueOf(pw.getPassword()).trim().equals("1234")) {
                  pw.setText("");
               }

            }
         });

      } //// end of the ī��ѹ� ġ�¶�

      cardPassWord = getJPasswordField(245);
      cardPassWord.setBounds(240, 360, 90, 20);
      cardPassWord.grabFocus();
      cardPassWord.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            System.out.println(String.valueOf(cardPassWord.getPassword()));
            String str = String.valueOf(cardPassWord.getPassword());
            int cnt = 0;
            for (int i = 0; i < str.length(); i++) {
               char ch = str.charAt(i);
               if (Character.isDigit(ch)) {
                  cnt++;
               }

            }
            if (cnt == 4 && str.length() == 4) {
               progress2 = true;
               setProgress2(progress2);
               System.out.println("ī���й�ȣ progress�� ����? " + getProgress2());
            } else {
               progress2 = false;
               setProgress2(progress2);
            }

         }
      });
      
      
      
      //////////////////////////////��ڴ��� ����ڰ�������Ȯ��/////////////////////////
      
     ArrayList<UserBookingInfo> userBookingList= null;
     //ObjectOutputStream oos = null;
     ObjectInputStream oisBooking = null;

     // MemberObject member = new MemberObject();// txtName.getText(),txtId.getText(),String.valueOf(txtPw.getPassword())
     UserBookingInfo userBookInfo = new UserBookingInfo(id, name, place, movieTitle, day, time, price, selectedSeatNum);
     
      try {
         oisBooking = new ObjectInputStream(new FileInputStream("UserbookingInfo.dat"));
         userBookingList = (ArrayList<UserBookingInfo>)(oisBooking.readObject()); 
         oisBooking.close();

      } catch (FileNotFoundException e2) {
    	  System.out.println("�о���� �����̾�� list���λ�����.");
         userBookingList = new ArrayList<UserBookingInfo>();
      } catch (Exception e2) {
         e2.printStackTrace();
      }

      userBookingList.add(userBookInfo);      
      
      
      
      
      
      ObjectOutputStream oosBooking = null;
      
      try {
    	  
    	  oosBooking = new ObjectOutputStream(new FileOutputStream("UserbookingInfo.dat"));
    	  oosBooking.writeObject(userBookingList);
    	 // bookingInfo.add(new UserBookingInfo(id, name, place, movieTitle, day, time, price, selectedSeatNum));
    	 // oosBooking.writeObject(bookingInfo);
    	  //oosBooking.flush();
    	  oosBooking.close();
      }catch(Exception e) {
    	  System.out.println("�������۰��� �����߻�.");
      }
      

            
  ////////////////////////////////������ ����ڰ����������ο�///////////////////////////////////    
      
      
      
      

      pay = new JButton("�����ϱ�");
      pay.setBounds(10, 450, 320, 30);
      pay.setBackground(new Color(130, 236, 255));
      pay.setForeground(new Color(114, 114, 114));
      pay.setFont(new Font("����ü", Font.BOLD, 15));

      pay.setBorderPainted(false);
      pay.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {

            // dcList.resetKeyboardActions();

            // System.out.println("ī���ȣ������ progress��? " + getProgress2());

            JOptionPane.showMessageDialog(null, "������ �����Ͻðڽ��ϱ�?");
            if (getProgress1() == true && getProgress2() == true && totalPayment.getText() != null) {

               JOptionPane.showMessageDialog(null, "������ �Ϸ�Ǿ����ϴ�.");
               long timePay = System.currentTimeMillis();
               System.out.println(timePay);

               // done.setMovieName(new JLabel(Movie.name));
               // done.setMovieAvailableTime(new JLabel(String.valueOf(Movie.availableTime)));
               // done.setPpl(new
               // JLabel(String.valueOf(ReserveSeat.howMany.getSelectedItem())));
               // done.setSeatNo(new JLabel(String.valueOf((ReserveSeat.selectedSeatNum))));
               /// done.setPay(new JLabel(String.valueOf(Movie.price)));
               // done.setReservationDatefinal(new JLabel(String.valueOf(timePay)));
               movieList = new ArrayList<MovieVO>();

               try {
                  ois = new ObjectInputStream(new FileInputStream("movies.dat"));
                  movieList = (ArrayList<MovieVO>) (ois.readObject());
               } catch (ClassNotFoundException e1) {
                  e1.printStackTrace();
               } catch (FileNotFoundException e1) {
                  e1.printStackTrace();
               } catch (IOException e1) {
                  e1.printStackTrace();
               }
               for(int i=0; i<movieList.size();i++) {
                  MovieVO movie = movieList.get(i);
                  
                  if((movie.getPlace()).equals(place) && (movie.getTitle()).equals(movieTitle) && (movie.getDay())==day) {
                     for(int j=0; j<movie.getTime().length ; j++) {
                        if((movie.getTime()[j]).equals(time)) {
                           try {
                              movie.getSeatNum().addAll(selectedSeatNum);
                           }catch(NullPointerException e1) {
                              ArrayList<Integer> seat = movie.getSeatNum();
                              seat = new ArrayList<Integer>();
                              seat.addAll(selectedSeatNum);
                              movie.setSeatNum(seat);
//                              e1.printStackTrace();
                           }
                           movieList.set(i, movie);
                        }
                     }
                  }
               }
               System.out.println("Ȯ��"+ movieList);
               try {
                  oos = new ObjectOutputStream(new FileOutputStream("movies.dat"));
                  oos.writeObject(movieList);
               } catch (FileNotFoundException e1) {
                  e1.printStackTrace();
               } catch (IOException e1) {
                  e1.printStackTrace();
               }
               dispose();
               
            
               new DonePaying(id, pw, name, place, movieTitle, day, time, price, selectedSeatNum).setVisible(true);
            } else {
               JOptionPane.showMessageDialog(null, "���� ����.�ٽ� �õ����ּ���.");
            }

         }
      });
      panel.add(pay);

      goBack = new JButton("��������");
      goBack.setBounds(10, 490, 320, 30);
      goBack.setBackground(Color.DARK_GRAY);
      goBack.setForeground(new Color(114, 114, 114));
      goBack.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            dispose();
//            ReserveSeat.selectedSeatNum.clear();
            new ReserveSeat(id, pw, name, place, movieTitle, day, time, price).setVisible(true);

         }
      });
      panel.add(goBack);

      panel.add(new Canvas1());
   }

   public String getPlace() {
      return place;
   }

   public void setPlace(String place) {
      this.place = place;
   }

   public String getMovieTitle() {
      return movieTitle;
   }

   public void setMovieTitle(String movieTitle) {
      this.movieTitle = movieTitle;
   }

   public int getDay() {
      return day;
   }

   public void setDay(int day) {
      this.day = day;
   }

   public String getTime() {
      return time;
   }

   public void setTime(String time) {
      this.time = time;
   }

   public ArrayList<Integer> getSelectedSeatNum() {
      return selectedSeatNum;
   }

   public void setSelectedSeatNum(ArrayList<Integer> selectedSeatNum) {
      this.selectedSeatNum = selectedSeatNum;
   }

   private JPanel getPanel() {
      return panel;
   }

   private JLabel getJLabel(String name, int y) {
      JLabel label = new JLabel(name);
      label.setFont(new Font("����ü", Font.BOLD, 15));
      label.setOpaque(true);
      label.setBounds(10, y, 100, 20);
      label.setForeground(Color.white);
      label.setBackground(Color.white);
      label.setEnabled(false);
      this.getPanel().add(label);
      label.setText(name);
      // System.out.println("�������� Ȯ���Ͽ���");
      return label;
   }

   private JLabel getJLabel(int x, int y, String name) {
      JLabel label = new JLabel(name);
      label.setFont(new Font("����ü", Font.BOLD, 15));
      label.setOpaque(true);
      label.setBounds(x, y, 200, 20);
      label.setForeground(Color.white);
      label.setBackground(Color.white);
      label.setHorizontalAlignment(SwingConstants.RIGHT);
      label.setEnabled(false);
      this.getPanel().add(label);
      label.setText(name);
      return label;
   }

   private JPasswordField getJPasswordField(int x) {
      JPasswordField num = new JPasswordField();
      num.setBounds(x, 300, 40, 20);
      num.setColumns(4);
      this.getPanel().add(num);
      return num;
   }

   public double getPrice() {
      return price;
   }

   private void setProgress1(boolean brr) {
      this.progress1 = brr;
   }

   private boolean getProgress1() {
      return progress1;
   }

   private void setProgress2(boolean brr) {
      this.progress2 = brr;
   }

   private boolean getProgress2() {
      return progress2;
   }

}
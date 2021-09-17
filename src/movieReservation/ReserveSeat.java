package movieReservation;

import java.awt.Canvas;
import java.awt.Color;
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
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

class MyCanvas extends Canvas {
   // private JLabel screen;
   // private Graphics2D graphic;

   public MyCanvas() {
      setBackground(Color.WHITE);
      setSize(350, 570);

   }

   @Override
   public void paint(Graphics g) {
      g.setColor(Color.blue);
      g.fillRect(0, 0, 350, 50);
      g.setColor(Color.GRAY);
      g.fillRect(0, 50, 350, 30);

      g.setColor(Color.gray);
      g.fillRoundRect(10, 150, 320, 30, 10, 10);// SCreen 타원
      g.setColor(Color.black);
      g.setFont(new Font("Arial", Font.BOLD, 15));
      g.drawString("SCREEN", 140, 169);

   }

}

public class ReserveSeat extends JFrame {
   private JPanel panel;// 파란색
   private JLabel people;// 예매인원
   private JLabel screen;// Screen이라고 적을거임
   static String[] cnt = { "1", "2", "3", "4" };// 예매하는사람들명수
   JComboBox<String> howMany = new JComboBox<String>(cnt);; // 예매인원 체크박스
   private JButton[][] seats;
   private JButton book;
   private JButton before;
   private MyCanvas canvas;
   private int peopleCnt; // 좌석 예약할때 사용할변수.
   /////////////////////
   private String id;
   private String pw;
   private String name;
   private String place;
   private String movieTitle;
   private int day;
   private String time;
   private double price;
   ArrayList<Integer> selectedSeatNum = new ArrayList<Integer>();
   private ObjectInputStream ois;
   private ArrayList<MovieVO> list;
   /////////////////////
   // 예약된좌석 정보보관 (ArrayList)
   // static ObjectOutputStream oos;
   // static ObjectInputStream ois;

   public String getMovieTitle() {
      return movieTitle;
   }

   public void setMovieTitle(String movieTitle) {
      this.movieTitle = movieTitle;
   }

   public String getTime() {
      return time;
   }

   public void setTime(String time) {
      this.time = time;
   }

   public int getDay() {
      return day;
   }

   public String getPlace() {
      return place;
   }

   public ReserveSeat(String id, String pw, String name, String place, String movieTitle, int day, String time, double price) {
      list = new ArrayList<MovieVO>();
      try {
         ois = new ObjectInputStream(new FileInputStream("movies.dat"));
         list = (ArrayList<MovieVO>) ois.readObject();
      } catch (ClassNotFoundException e1) {
         e1.printStackTrace();
      } catch (FileNotFoundException e1) {
         e1.printStackTrace();
      } catch (IOException e1) {
         e1.printStackTrace();
      }
      this.place = place;
      this.movieTitle = movieTitle;
      this.day = day;
      this.time = time;
      this.price = price;
      //System.out.println(place + "," + movieTitle + "," + day + "," + time + ", " + price);

      setTitle("1조 CGV 영화 예매 프로그램");// 1조 CGV 영화예매 프로그램
      setBounds(0, 0, 350, 570);
      setLayout(null);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      panel = new JPanel();
      panel.setLayout(null);
      setContentPane(panel);
      panel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));

      panel.setBounds(0, 80, 350, 620);

      people = new JLabel("예매인원: ");// 예매인원
      people.setFont(new Font("Arial", Font.BOLD, 15));
      people.setOpaque(true); // people 뒤에 회색 text배경 사라지게함.
      people.setText("People:  ");// 한국말은 이상하게 깨짐.. 이해가안됨
      people.setBounds(10, 100, 65, 20);
      people.setEnabled(false);
      people.setForeground(Color.white);
      people.setBackground(Color.white);
      panel.add(people);

      howMany.setBounds(280, 100, 50, 20);
      // howMany.putClientProperty(key, value);
      howMany.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            // int index = howMany.getSelectedIndex();

            double price = getPrice();

            /*
             * switch(index) { case 1: //멤버십할인 price = price * 0.8;
             * System.out.println(price); break; case 2: price = (price * 0.7);
             * System.out.println(price); break; case 3: price = (price *0.6);
             * System.out.println(price); break; default: break;
             * 
             * }
             */
            System.out.println("영화표 가격은? " + price);
            System.out.println("체크박스에서 선택된 item: " + howMany.getSelectedItem());

            switch (howMany.getSelectedIndex()) {
            case 1: // 2
               price *= 2;
               break;
            case 2: // 3
               price *= 3;
               break;
            case 3: // 4
               price *= 4;
               break;
            default: // 1
               break;
            }
            System.out.println("총영화표 가격은? " + price);
            // setPrice(price);
            // System.out.println("setPrice() 이후의 총 가격은? " + getPrice());
            // totalPayment.setText(price + "원");

         }

      });
      panel.add(howMany);

      seats = new JButton[4][5];
      for (int row = 0; row < seats.length; row++) {

         for (int col = 0; col < seats[row].length; col++) {
            int num = ((row) * seats[row].length) + (col + 1);
            // 버튼의 num은 row:0 col 1,2,3,4,5 row:1(5) col 1,2,3,4,5
            // .....row:3(5) col 1,2,3,4,5 해서 1~20 까지 생성이됨.
            int moveX = 65;
            int moveY = 60;
            seats[row][col] = setBtnSeat("btnSeat" + num, num + "", 10 + (moveX * col), 200 + (moveY * row));// 버튼은
                                                                                       // num값과
                                                                                       // 관련없이
                                                                                       // 어차피
                                                                                       // 20개만생성함.
            /*
             * row 0, col0 --> JButton(10,200,53,48) row 0, col1 --> JButton(75, 200, 53,48)
             * ..... row 1, col0---> JButton(10, 260, 53,48) row 1, col1---> JButton(10,
             * 320, 53,48)
             */
            seats[row][col].addActionListener(new ActionListener() {
               @Override
               public void actionPerformed(ActionEvent e) {
                  // int selectedCnt = 0; //length of the ArrayList
                  if (e.getSource() instanceof JButton) {
                     JButton btn = (JButton) e.getSource();
                     peopleCnt = howMany.getSelectedIndex() + 1;// 예매하는사람수.
                     int btnNum = Integer.parseInt(btn.getText());// 버튼번호

                     
                     if (btn.getBackground().equals(Color.ORANGE)) {
                        btn.setBackground(new Color(230, 236, 240));

                        selectedSeatNum.remove(selectedSeatNum.indexOf(btnNum));
                        // selectedCnt--;
                     } else {

                        if (selectedSeatNum.size() < 4) {
                        	btn.setBackground(Color.ORANGE);
                        
                           // selectedCnt++;
                           selectedSeatNum.add(btnNum);
                        } else {
                           JOptionPane.showMessageDialog(null, "선택할수 있는 명 수를 초과했습니다");
                        }
                     }

                     System.out.println(selectedSeatNum);
                     setArrayList(selectedSeatNum);

                  }

               }// end of the actionPerformed

            });
         }
      }

      // System.out.println(selectedSeatNum.size());

      book = new JButton("예매하기");
      book.setBounds(10, 450, 320, 30);
      book.setBackground(new Color(130, 236, 255));
      book.setForeground(new Color(114, 114, 114));
      book.setFont(new Font("굴림체", Font.BOLD, 15));
      book.setBorderPainted(false);
      // book.putClientProperty(howMany.getSelectedItem(), value);
      book.addActionListener(new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent e) {
            System.out.println("소비자가 현재 좌석표번호 예매 현황" + getArrayList());
            peopleCnt = Integer.parseUnsignedInt((String) howMany.getSelectedItem());

            if (getArrayList().size() == peopleCnt) {
               dispose();
               new Payment(id,pw, name, place, movieTitle, day, time, getPrice(), selectedSeatNum).setVisible(true);
               peopleCnt = 0;
            } else {
               JOptionPane.showMessageDialog(null, "예매하려는 표의 숫자와 맞지않습니다.");
            }

         }
      });

      panel.add(book);

      before = new JButton("이전으로");
      before.setBounds(10, 490, 320, 30);
      before.setBackground(Color.DARK_GRAY);
      before.setForeground(new Color(114, 114, 114));
      before.addActionListener(new ActionListener() {
         /*
          * 영화별로선택했을경우
          */
         @Override
         public void actionPerformed(ActionEvent e) {
            if (Reservation.choose == 1) {// 영화별선택했을경우
               new TheatherSelect2(id,pw,name).setVisible(true);
               dispose();
            } else if (Reservation.choose == 2) {// 극장별로선택했을경우
               dispose();
               new MovieSelect(id,pw,name, getPlace(), getDay()).setVisible(true);
            }

         }
      });
      panel.add(before);

      panel.add(new MyCanvas());
   }

   private JPanel getJPanel() {
      return panel;
   }

   private JButton setBtnSeat(String name, String seat, int x, int y) {

      JButton btn = new JButton(seat);
      Font btnFont = new Font("맑은 고딕", Font.BOLD, 14);
      btn.setFont(btnFont);
      btn.setBackground(new Color(230, 236, 240));
      btn.setForeground(new Color(114, 114, 114));
      btn.setBorderPainted(false); // 버튼 테두리 모양이선명해지냐 흐릿하냐 차이
      btn.setBounds(x, y, 53, 48);// 생성될 JButton의 너비와길이는 53, 48로정해둠.
      // 버튼을 생성하는 x,y값에따라 num 간격만 바꿔준다
      
      for(int i=0; i<list.size(); i++) {
         MovieVO movie = list.get(i);
         if(movie.getSeatNum() != null && (movie.getPlace()).equals(place) && (movie.getTitle()).equals(movieTitle) && (movie.getDay())==day) {
            System.out.println((movie.getTime()).length);
            for(int j=0; j<(movie.getTime()).length ; j++) {
               if((movie.getTime()[j]).equals(time)) {
                  System.out.println(movie);
                  for(int k=0; k<movie.getSeatNum().size() ; k++) {
//                     System.out.println("좌석번호:"+seat);
//                     System.out.println("getk:"+movie.getSeatNum().get(k));
                     if(seat.equals(""+movie.getSeatNum().get(k))){
//                        System.out.println("같은것"+btn.getText());
                        btn.setBackground(Color.GRAY);
                        btn.setEnabled(false);
                     }
                  }
               }
            }
         }
      }
      
      this.getJPanel().add(btn);
      btn.setName(name);

      return btn;
   }

   private ArrayList<Integer> setArrayList(ArrayList<Integer> arr) {
      selectedSeatNum = arr;
      return selectedSeatNum;
   }

   private ArrayList<Integer> getArrayList() {
      return selectedSeatNum;
   }

   public void setPlace(String place) {
      this.place = place;

   }

   public void setDay(int day) {
      this.day = day;
   }

   public double getPrice() {
      return price;
   }

   public void setPrice(double price) {
      this.price = price;
   }
   
  /* private static void socialDistance(JButton [][] str, int row, int col) {
		if(validCheck(str,row,col) == false) {
					if(col == 0 && row >= 0 && row < str.length) {
						str[row][col+1].setEnabled(false);
						str[row][col+1].setBackground(Color.GRAY);
						str[row+1][col] = "X";
					}else if(col == 9 && row >= 0 && row < str.length) {
						str[row][col-1] = "X";
						str[row+1][col] = "X";
					}else if(row == 0 && col >= 1 && col < str.length-1) {
						str[row][col-1] = "X";
						str[row][col+1] = "X";
						str[row+1][col] = "X";
					}else if(row == str.length-1 && col >= 1 && col < str.length-1) {
						str[row][col-1] = "X";
						str[row][col+1] = "X";
						str[row-1][col] = "X";
					}else {
						str[row][col-1] = "X";
						str[row][col+1] = "X";
						str[row-1][col] = "X";
						str[row+1][col] = "X";
					}
		}
	}*/
}
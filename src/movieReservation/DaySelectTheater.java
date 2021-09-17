package movieReservation;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.BevelBorder;

public class DaySelectTheater extends JFrame{
   private MainCanvas1 canvas;
   private JPanel panel;
   private JLabel label; //2020년 7월
   private JLabel dayNames[]; //요일 이름
   private JButton days[]; //날짜
   private JButton before; //이전으로
   private JPanel calender;
   private JLabel blueLabel;
   private JLabel grayLabel;
   
   private String id;
   private String pw;
   private String name;
   private String place;
   private int day;
   

public DaySelectTheater(String id, String pw, String name, String place) {
	  this.id = id;
	  this.pw = pw;
	  this.name = name;
      this.place = place;
      System.out.println("상영관 위치는? " + place);// 잘받음.
      setTitle("날짜 선택");//1조 CGV 영화예매 프로그램
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       setBounds(0, 0, 350, 570);
       setLayout(null);
       
       panel = new JPanel();
       panel.setLayout(null);
       panel.setBackground(Color.white);
       setContentPane(panel);
//       panel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null,null,null));
//       panel.add(new MainCanvas());
       
       blueLabel = new JLabel();
       blueLabel.setOpaque(true); 
       blueLabel.setBackground(Color.blue);
       blueLabel.setBounds(0,0,350,50);
//       blueLabel.setPreferredSize(new Dimension(350,50));
       panel.add(blueLabel);
       
       grayLabel = new JLabel();
       grayLabel.setOpaque(true);
       grayLabel.setBackground(Color.gray);
       grayLabel.setBounds(0, 50, 350, 30);
//       grayLabel.setPreferredSize(new Dimension(350, 30));
       panel.add(grayLabel);
       
       label = new JLabel();
       label.setText("2020 July");
       label.setOpaque(true);
       label.setBackground(Color.white);
       label.setFont(new Font("Arial", Font.BOLD, 15));
       label.setEnabled(true);
       label.setHorizontalAlignment(JLabel.CENTER);
       label.setBounds(0,80,350,30);
       panel.add(label);
       
       calender = new JPanel(new GridLayout(6, 7, 2, 2));
       calender.setBackground(Color.white);
       calender.setBounds(10, 110, 320, 320);
       
       String str[] = {"일", "월", "화", "수", "목", "금", "토"};
       dayNames = new JLabel[7];
       for(int i = 0; i<dayNames.length; i++) {
          dayNames[i] = new JLabel(str[i]);
          dayNames[i].setHorizontalAlignment(JLabel.CENTER);
          if((dayNames[i].getText()).equals("일")) {
             dayNames[i].setForeground(Color.red);
          }else if((dayNames[i].getText()).equals("토")) {
             dayNames[i].setForeground(Color.blue);
          }
          calender.add(dayNames[i]);
       }
       
       days = new JButton[35];
       days[0] = new JButton("");
       days[1] = new JButton("");
       days[2] = new JButton("");//공백 버튼
       days[34] = new JButton("");
       for(int i = 3; i<days.length-1; i++) {
          JButton day = new JButton();
          day.setText(Integer.toString(i-2));
          days[i] = day;
       }
//        int i = 0;
       for(int i = 0; i<days.length-1; i++) {
          Font btnFont = new Font("맑은 고딕", Font.BOLD, 14);
          days[i].setFont(btnFont);
          days[i].setPreferredSize(new Dimension(30, 30));
          days[i].setBackground(new Color(230, 236, 240));
          days[i].setForeground(new Color(114, 114, 114));
          days[i].setMargin(new Insets(1,1,1,1));
          days[i].setEnabled(false);
          days[i].setBorderPainted(false); //버튼 테두리 모양이선명해지냐 흐릿하냐 차이
          
          if((days[i].getText()).equals("")) {   //공백 버튼은 이벤트 안 걺
             days[i].setEnabled(false);
             calender.add(days[i]);
             continue;
          }
          
          if(Integer.parseInt(days[i].getText()) >= 21 && Integer.parseInt(days[i].getText()) <= 23) {   //20,21,22일만 날짜 선택 가능하게 함
             days[i].setForeground(Color.black);
             days[i].setEnabled(true);
             days[i].addActionListener(new ActionListener() {
                
               //MovieSelect ms;
                
               @Override
               public void actionPerformed(ActionEvent e) {
                
                  JButton day = (JButton)e.getSource();
                  int day2 = Integer.parseInt(day.getText());
                 // JButton btn = (JButton)e.getSource();
                  
                // ms.setDay(Integer.parseInt(day.getText()));
                // ms.setPlace(place);
                 //new MovieSelect().setPlace(place);
                  new MovieSelect(id,pw,name, place, day2).setVisible(true);
                  dispose();
                  
               }
            });
          }
          if(Integer.parseInt(days[i].getText()) == 21) {   //21일(당일)만 색깔 주황색
             days[i].setForeground(new Color(255,102,0));
          }
          calender.add(days[i]);
       }
       
       panel.add(calender);
       
       before = new JButton("이전으로");
       before.setBounds(10,440,320,30);
       before.setPreferredSize(new Dimension(320, 30));
       before.setBackground(Color.DARK_GRAY);
       before.setForeground(new Color(114, 114, 114));
       before.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            setVisible(false);
            new TheatherSelect2(id,pw,name).setVisible(true);            
         }
      });
       panel.add(before);
       
   }
}
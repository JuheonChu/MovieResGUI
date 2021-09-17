package movieReservation;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;

public class MovieSelect2 extends JFrame {
   private JPanel panel1;
   private JPanel panel2;
//   private Movie2 movie;
   private String[] movies;
   ObjectInputStream ois;
   ObjectOutputStream oos;
   private JList list;
   private String selectTitle;
   ////////////////////////////
   private String id;
   private String pw;
   private String name;

   public String getSelectTitle() {
      return selectTitle;
   }

   public void setSelectTitle(String selectTitle) {
      this.selectTitle = selectTitle;
   }

   MovieSelect2(String id, String pw, String name) {
	   this.id = id;
	   this.pw = pw;
	   this.name = name;
	   
	   
	   
      movies = new String[] { "라라랜드", "아이언맨", "조커", "타이타닉" };

      setTitle("영화 선택");
      setSize(350, 570);
      getContentPane().setBackground(Color.BLUE);
      panel1 = new JPanel();
      panel1.setLayout(null);
      panel1.setBounds(0, 80, 350, 620);
      panel1.setBackground(Color.WHITE);
      panel2 = new JPanel();
      panel2.setLayout(null);
      panel2.setBackground(Color.gray);
      panel2.setBounds(0, 50, 350, 30);
      add(panel1);
      add(panel2);

      list = new JList(movies);
      setLayout(null);
      list.setBounds(10, 10, 300, 300);
      list.setFixedCellHeight(50);
      list.setFixedCellWidth(50);
      list.setBackground(Color.WHITE);
      list.setFont(new Font("맑은 고딕", Font.BOLD, 15));
      panel1.add(list);

      JButton button3 = new JButton("이전으로");
      button3.setBounds(10, 370, 320, 30);
      button3.setBorderPainted(false);
      button3.setBackground(Color.DARK_GRAY);
      button3.setForeground(new Color(114, 114, 114));
      panel1.add(button3);

      list.addMouseListener(new MouseListener() {
         @Override
         public void mouseReleased(MouseEvent e) {
         }

         @Override
         public void mousePressed(MouseEvent e) {
         }

         @Override
         public void mouseExited(MouseEvent e) {
         }

         @Override
         public void mouseEntered(MouseEvent e) {
         }

         @Override
         public void mouseClicked(MouseEvent e) {
            if (list.getSelectedIndex() == 0) {
               setSelectTitle("라라랜드");
               new DaySelectMovie(id, pw, name, "라라랜드").setVisible(true);
               dispose();
            } else if (list.getSelectedIndex() == 1) {
               setSelectTitle("아이언맨");
               new DaySelectMovie(id, pw, name,"아이언맨").setVisible(true);
               dispose();
            } else if (list.getSelectedIndex() == 2) {
               setSelectTitle("조커");
               new DaySelectMovie(id, pw, name,"조커").setVisible(true);
               dispose();
            } else if (list.getSelectedIndex() == 3) {
               setSelectTitle("타이타닉");
               new DaySelectMovie(id, pw, name,"타이타닉").setVisible(true);
               dispose();
            }
            setVisible(false);
         }
      });

      button3.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            JButton b = (JButton) (e.getSource());
            new Reservation(id,pw,name).setVisible(true);
            setVisible(false);
         }
      });
   }
}
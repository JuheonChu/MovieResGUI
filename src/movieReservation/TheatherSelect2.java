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

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class TheatherSelect2 extends JFrame {
   private JPanel panel1;
   private JPanel panel2;
   private String[] theathers;
   ObjectInputStream ois;
   ObjectOutputStream oos;
   private JList list;
   
   private String id;
   private String pw;
   private String name;
   private String place;

   public String getPlace() {
      return place;
   }

   public void setPlace(String place) {
      this.place = place;
   }

   TheatherSelect2(String id, String pw, String name) {
	   this.id = id;
	   this.pw = pw;
	   this.name = name;
      theathers = new String[] { "½ÅÃÌ", "È«´ë", "¸íµ¿", "±¸·Î" };
      setTitle("»ó¿µ°ü ¼±ÅÃ");
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

      list = new JList(theathers);

      setLayout(null);
      list.setBounds(10, 10, 300, 300);
      list.setBackground(Color.WHITE);
      list.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 15));
      list.setFixedCellHeight(50);
      list.setFixedCellWidth(50);
      panel1.add(list);

      JButton button3 = new JButton("ÀÌÀüÀ¸·Î");
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
               setPlace("½ÅÃÌ");
               // new
               // MovieSelect().setDay(Integer.parseInt(String.valueOf(list.getSelectedValue())));
               new DaySelectTheater(id,pw,name,"½ÅÃÌ").setVisible(true);
               dispose();
            } else if (list.getSelectedIndex() == 1) {
               setPlace("È«´ë");
               // new
               // MovieSelect().setDay(Integer.parseInt(String.valueOf(list.getSelectedValue())));
               new DaySelectTheater(id,pw,name,"È«´ë").setVisible(true);

//               new TheatherDaySelect().setVisible(true);
               dispose();
            } else if (list.getSelectedIndex() == 2) {
               setPlace("¸íµ¿");
               new DaySelectTheater(id,pw,name,"¸íµ¿").setVisible(true);
//               new TheatherDaySelect().setVisible(true);
               dispose();
            } else if (list.getSelectedIndex() == 3) {
               // new
               // MovieSelect().setDay(Integer.parseInt(String.valueOf(list.getSelectedValue())));
               setPlace("±¸·Î");
               new DaySelectTheater(id,pw,name,"±¸·Î").setVisible(true);
//               new TheatherDaySelect().setVisible(true);
               dispose();
            }
//            new TheatherDaySelect().setVisible(true);
            setVisible(false);
         }
      });

      button3.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            // JButton b =(JButton)(e.getSource());
            new Reservation(id,pw, name).setVisible(true);
            setVisible(false);
         }
      });

   }

}
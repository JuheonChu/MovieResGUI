package movieReservation;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

class MemberObject implements Serializable {
   String name;
   String id;
   String password;

   MemberObject() {

   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getId() {
      return id;
   }

   public void setId(String id) {
      this.id = id;
   }

   public String getPassword() {
      return password;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   @Override
   public String toString() {
      return "MemberObj [name=" + name + ", id=" + id + ", password=" + password + "]";
   }

   /**
   * 
   */
   private static final long serialVersionUID = 1L;

   @Override
   public int hashCode() {
      return 1;
   }

   @Override
   public boolean equals(Object obj) {
      MemberObject obj2 = (MemberObject) obj;

      if (id.equals(obj2.id))
         return true;
      else
         return false;
   }
}

class MainCanvas1 extends Canvas {
   public MainCanvas1() {
      setBackground(Color.WHITE);
      setSize(500, 570);
   }

   @Override
   public void paint(Graphics g) {
      g.setColor(Color.blue);
      g.fillRect(0, 0, 500, 50);
      g.setColor(Color.GRAY);
      g.fillRect(0, 50, 500, 30);

      g.setColor(Color.gray);
      g.setColor(Color.black);
      g.setFont(new Font("Arial", Font.BOLD, 15));

   }
}

class Login extends JFrame {
   static JFrame frame;
   MainCanvas1 canvas;
   JLabel l1, l2, l3, l4;
   JTextField txtId;
   JPasswordField txtPw;
   JButton btn1, btn2, btn3, btn4;
   Register register;

   Login() {
      frame = this;
      setTitle("1�� ��ȭ ���� ���α׷�");
      canvas = new MainCanvas1();
      register = new Register();
//      ImageIcon btnImg2 = new ImageIcon(".\\images\\Login.PNG");
      txtId = new JTextField();
      txtPw = new JPasswordField();
      l1 = new JLabel("���̵�� ��й�ȣ�� �Է����ּ��� ");
      l1.setOpaque(true);
      l1.setBackground(Color.WHITE);
      l2 = new JLabel("ID:");
      l2.setOpaque(true);
      l2.setBackground(Color.WHITE);
      l3 = new JLabel("PW:");
      l3.setOpaque(true);
      l3.setBackground(Color.WHITE);
      btn1 = new JButton("�α���");
      btn1.setOpaque(true);
      btn1.setBackground(Color.red);
      btn1.setForeground(Color.white);
      btn2 = new JButton("ȸ������");
      btn2.setBackground(Color.DARK_GRAY);
      btn2.setForeground(Color.white);
      btn3 = new JButton("��й�ȣ ã��");
      btn3.setBackground(Color.DARK_GRAY);
      btn3.setForeground(Color.white);
      btn4 = new JButton("��� �α���");
      btn4.setBackground(new Color(130, 236, 255));
      l1.setBounds(150, 80, 300, 100);
      l2.setBounds(95, 210, 30, 30);
      l3.setBounds(90, 260, 30, 30);
      txtId.setBounds(125, 200, 250, 50);
      txtPw.setBounds(125, 250, 250, 50);
      btn1.setBounds(125, 300, 250, 40);
      btn2.setBounds(125, 340, 125, 30);
      btn3.setBounds(250, 340, 125, 30);
      btn4.setBounds(340, 520, 150, 30);
      add(l1);
      add(l2);
      add(l3);
      add(txtId);
      add(txtPw);
      add(btn1);
      add(btn2);
      add(btn3);
      add(btn4);
      add(canvas);
      setLayout(null);
      setSize(500, 600);
      frame.setResizable(false);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setLocationRelativeTo(null);
      setVisible(true);
      btn2.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            dispose();
            register.setVisible(true);
         }
      });
      btn1.addActionListener(new ActionListener() { // �α����� ������ frame unvisible
         HashSet<MemberObject> member2;

         @Override
         public void actionPerformed(ActionEvent e) {

            try {
               ObjectInputStream ois = new ObjectInputStream(new FileInputStream("MembershipManagement.dat"));
               member2 = (HashSet<MemberObject>) (ois.readObject());
               System.out.println("member2 HashSet ��ü�� " + member2);

               ois.close();

            } catch (FileNotFoundException e2) {
               member2 = new HashSet<MemberObject>();
            } catch (Exception e3) {
               e3.printStackTrace();
            }

//            for(int i=0;i<member2.size();i++) {
//               
//               if(member2.get(i).id.equals(txtId.getText()) && member2.get(i).password.equals( String.valueOf(txtPw.getPassword()))){
//                    txtId.setText("");
//                    txtPw.setText("");
//                  JOptionPane.showMessageDialog(frame, "�α��� ����!");
//                  dispose();
//                   Web2ClientTest1.reservation.setVisible(true);
//                  break;
//               }
//               
//               if(i==member2.size()-1) {
//                   txtId.setText("");
//                   txtPw.setText("");
//                  JOptionPane.showMessageDialog(frame, "�α��� ����!");
//               }
//            }      
            Iterator<MemberObject> itr = member2.iterator();
            while (itr.hasNext()) {
               MemberObject i1 = (MemberObject) itr.next();
               if ((i1).getId().equals(txtId.getText())
                     && (i1).getPassword().equals(String.valueOf(txtPw.getPassword()))) {
                  txtId.setText("");
                  txtPw.setText("");
                  JOptionPane.showMessageDialog(frame, "�α��� ����!");
                  dispose();
                  new Reservation((i1).getId(), (i1).getPassword(), (i1).getName()).setVisible(true);
                  break;
               } else if (!(itr.hasNext())) {
                  txtId.setText("");
                  txtPw.setText("");
                  JOptionPane.showMessageDialog(frame, "�α��� ����!");
               }

            }

         }
      });
      btn3.addActionListener(new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent e) {
//            dispose();
            frame.setVisible(false);
            new FindPw().setVisible(true);
//             Web2ClientTest1.findpw.setVisible(true);
            // Group_1.findpw.setVisible(true);
         }
      });
      btn4.addActionListener(new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent e) {
            dispose();
            new Operator().setVisible(true);
         }
      });
   }

}

class Register extends JFrame {

   JFrame frame;
   JLabel l1, l2, l3;
   JTextField txtName, txtId;
   JPasswordField txtPw;
   JButton btn1, btn2;
   MainCanvas1 canvas;

   Register() {
      frame = this;
      setTitle("ȸ������");
      l1 = new JLabel("�̸��� �Է����ּ���");
      l1.setOpaque(true);
      l1.setBackground(Color.WHITE);
      l2 = new JLabel("���̵� �Է����ּ���");
      l2.setOpaque(true);
      l2.setBackground(Color.WHITE);
      l3 = new JLabel("��й�ȣ�� �Է����ּ���");
      l3.setOpaque(true);
      l3.setBackground(Color.WHITE);
      txtName = new JTextField("");
      txtId = new JTextField("");
      txtPw = new JPasswordField("");
      btn1 = new JButton("����");
      btn1.setBackground(Color.DARK_GRAY);
      btn1.setForeground(Color.white);
      btn2 = new JButton("��������");
      btn2.setBackground(Color.DARK_GRAY);
      btn2.setForeground(Color.white);
      canvas = new MainCanvas1();
      l1.setBounds(30, 160, 150, 50);
      l2.setBounds(30, 210, 150, 50);
      l3.setBounds(30, 260, 150, 50);
      txtName.setBounds(190, 170, 50, 30);
      txtId.setBounds(190, 220, 100, 30);
      txtPw.setBounds(200, 270, 100, 30);
      btn1.setBounds(30, 330, 100, 30);
      btn2.setBounds(130, 330, 100, 30);
      add(l1);
      add(l2);
      add(l3);
      add(txtName);
      add(txtId);
      add(txtPw);
      add(btn1);
      add(btn2);
      add(canvas);
      setLayout(null);
      setSize(500, 600);
      frame.setResizable(false);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setLocationRelativeTo(null);
      btn1.addActionListener(new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent e) {
            HashSet<MemberObject> member2 = null;
            ObjectOutputStream oos = null;
            ObjectInputStream ois = null;

            // System.out.println(Arrays.toString(txtPw.getPassword()));
            System.out.println(txtPw.getPassword());
            System.out.println(
                  txtName.getText() + " / " + txtId.getText() + " / " + String.valueOf(txtPw.getPassword()));
            MemberObject member = new MemberObject();// txtName.getText(),txtId.getText(),String.valueOf(txtPw.getPassword())
            member.setName(txtName.getText());
            member.setId(txtId.getText());
            member.setPassword(String.valueOf(txtPw.getPassword()));
            try {
               ois = new ObjectInputStream(new FileInputStream("MembershipManagement.dat"));
               member2 = (HashSet<MemberObject>) (ois.readObject()); // HashSet<ember>
               ois.close();

            } catch (FileNotFoundException e2) {
               member2 = new HashSet<MemberObject>();
            } catch (Exception e2) {
               e2.printStackTrace();
            }

            member2.add(member);

            try {
               oos = new ObjectOutputStream(new FileOutputStream("MembershipManagement.dat"));// file ����Ȼ��¿��� ���
                                                                           // �᳻����������.
               oos.writeObject(member2);// ȸ������ ��������.
               oos.close();
            } catch (FileNotFoundException e1) {
               e1.printStackTrace();
            } catch (IOException e1) {
               e1.printStackTrace();
            }
            // Group_1.register.setVisible(false);
            dispose();

            new Login().setVisible(true);

         }
      });
      btn2.addActionListener(new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent e) {
            frame.setVisible(false);
//          Web2ClientTest1.findpw.setVisible(false);
            Web2ClientTest1.login.setVisible(true);
         }

      });
   }
}

class FindPw extends JFrame {
   MainCanvas1 canvas;
   static JFrame frame;
   JLabel InputId;
   JTextField FindField;
   JButton Findbtn;
   JButton Back;

   FindPw() {
      canvas = new MainCanvas1();
      frame = this;
      setTitle("��й�ȣ ã��");
      InputId = new JLabel("��й�ȣ�� ã�� ���̵� �Է� : ");
      InputId.setOpaque(true);
      InputId.setBackground(Color.WHITE);
      FindField = new JTextField();
      Findbtn = new JButton("ã��");
      Findbtn.setBackground(Color.DARK_GRAY);
      Findbtn.setForeground(Color.white);
      Back = new JButton("�ڷΰ���");
      Back.setBackground(Color.DARK_GRAY);
      Back.setForeground(Color.white);
      InputId.setBounds(30, 100, 200, 50);
      FindField.setBounds(250, 110, 100, 30);
      Findbtn.setBounds(360, 110, 60, 30);
      Back.setBounds(200, 400, 100, 30);
      add(InputId);
      add(FindField);
      add(Findbtn);
      add(Back);
      add(canvas);
      setSize(500, 500);
      frame.setResizable(false);
      setLayout(null);
      setLocationRelativeTo(null);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      Findbtn.addActionListener(new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent e) {
            HashSet<MemberObject> member2 = null;
            try {
               ObjectInputStream ois = new ObjectInputStream(new FileInputStream("MembershipManagement.dat"));
               member2 = (HashSet<MemberObject>) (ois.readObject()); // HashSet<MemberObj>
               ois.close();

            } catch (FileNotFoundException e2) {
               member2 = new HashSet<MemberObject>();
            } catch (Exception e2) {
               e2.printStackTrace();
            }

//            for(MemberObj mm : member2) {
//               if(mm.getId().equals(FindField.getText())) {
//                  FindField.setText("");
//                  JOptionPane.showMessageDialog(frame, "��й�ȣ�� \"" + mm.getPassword()+" \"�Դϴ�.");
//                  break;
//               }
//               if(member2.get(member2.size()-1) == mm) {
//                  JOptionPane.showMessageDialog(frame, "��������");
//               }
//            }
//            
            Iterator itr = member2.iterator();
            while (itr.hasNext()) {
               MemberObject i1 = (MemberObject) itr.next();
               if ((i1).getId().equals(FindField.getText())) {
                  FindField.setText("");

                  JOptionPane.showMessageDialog(frame, "��й�ȣ�� \"" + (i1).getPassword() + " \"�Դϴ�.");

                  break;
               } else if (!(itr.hasNext())) {
                  FindField.setText("");
                  JOptionPane.showMessageDialog(frame, "��������");
               }
            }

         }
      });
      Back.addActionListener(new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent e) {
            frame.setVisible(false);
//          Web2ClientTest1.findpw.setVisible(false);
            Web2ClientTest1.login.setVisible(true);
         }
      });

   }
}

class Operator extends JFrame { // ��� password : centos, ��� id : Admin
   static JFrame frame;
   MainCanvas1 canvas;
   JLabel l1, l2, l3;
   JTextField txtId;
   JPasswordField txtPw;
   JButton btn1;
   //JButton userPayInfo;
   JButton Back;

   Operator() {

      frame = this;
      setTitle("��� �α���");
      canvas = new MainCanvas1();
      l1 = new JLabel("��ڷ� �α����մϴ� ���̵�� �н����带 �Է��ϼ��� ");
      l1.setOpaque(true);
      l1.setBackground(Color.WHITE);
      l2 = new JLabel("ID:");
      l2.setOpaque(true);
      l2.setBackground(Color.WHITE);
      l3 = new JLabel("PW:");
      l3.setOpaque(true);
      l3.setBackground(Color.WHITE);
      // ImageIcon btnImg2 = new ImageIcon(".\\images\\Login.PNG");
      txtId = new JTextField();
      txtPw = new JPasswordField();
      btn1 = new JButton("�α���"); // �α���
      btn1.setOpaque(true);
      btn1.setBackground(Color.red);
      btn1.setForeground(Color.white);
      Back = new JButton("�ڷΰ���");
      Back.setBackground(Color.DARK_GRAY);
      Back.setForeground(Color.white);

      l1.setBounds(100, 80, 350, 100);
      l2.setBounds(95, 210, 30, 30);
      l3.setBounds(90, 260, 30, 30);
      txtId.setBounds(125, 200, 250, 50);
      txtPw.setBounds(125, 250, 250, 50);
      btn1.setBounds(125, 300, 250, 40);
      Back.setBounds(200, 400, 100, 30);
      add(l1);
      add(l2);
      add(l3);
      add(txtId);
      add(txtPw);
      add(btn1);
      add(Back);
      add(canvas);
      setLayout(null);
      setSize(500, 600);
      frame.setResizable(false);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setLocationRelativeTo(null);
      btn1.addActionListener(new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent e) {
            if (txtId.getText().equals("admin") && String.valueOf(txtPw.getPassword()).equals("centos")) {
               JOptionPane.showMessageDialog(frame, "�α��� ����!");
               txtId.setText("");
               txtPw.setText("");

               // Web2ClientTest1.operator.setVisible(false);
               new Op_Authority().setVisible(true);
            } else {
               JOptionPane.showMessageDialog(frame, "�α��� ����!");
            }
         }
      });
      Back.addActionListener(new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent e) {
            frame.setVisible(false);
            new Login().setVisible(true);
         }
      });

   }
}

class Op_Authority extends JFrame { // ��� ����
   static JFrame frame;
   MainCanvas1 canvas;
   JButton SearchData;
   JButton userPayInfo;

   Op_Authority() {
      frame = this;
      canvas = new MainCanvas1();
      SearchData = new JButton("ȸ��������ȸ");
      SearchData.setBounds(0, 100, 500, 50);

      SearchData.addActionListener(new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent e) {
          //  HashSet<MemberObject> list = null;
            Data data = new Data();
            frame.setVisible(false);
            data.setVisible(true);
         }
      });
      
      userPayInfo = new JButton("�� �������� ��ȸ");
      userPayInfo.setBounds(0,160, 500, 50);
      userPayInfo.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			//ArrayList<UserBookingInfo>list = null;
            UserData udata = new UserData();
            frame.setVisible(false);
            udata.setVisible(true);
			
		}
	});

      add(SearchData);
      add(userPayInfo);
      add(canvas);
      setSize(600, 600);
      setLayout(null);
      setLocationRelativeTo(null);
   }

}

class UserData extends JFrame{
	JTable table;
	UserData frame;
	JScrollPane scrollPane;
	JButton b1;

	UserData() {
		
	      frame = this;

	      String columns[] = { "���̵�", "�̸�", "�󿵰�", "��ȭ����", "�󿵳�¥", "�󿵽ð�", "��ȭǥ����", "�¼���ȣ" };
	      DefaultTableModel model = new DefaultTableModel(columns, 0);
	      table = new JTable(model);
	      
	      ArrayList<UserBookingInfo>list = null;
	      try {
	         ObjectInputStream ois = new ObjectInputStream(new FileInputStream("UserbookingInfo.dat"));
	         list = (ArrayList<UserBookingInfo>) (ois.readObject());
	         //System.out.println(list);
	         ois.close();
	      } catch (FileNotFoundException e) {
	         list = new ArrayList<UserBookingInfo>();
	      } catch (Exception e) {
	         e.printStackTrace();
	      }

	     System.out.println(Arrays.toString(list.toArray()));
	     System.out.println("ArrayList�� size()�� ? " + list.size());
	     System.out.println(list.get(0));
	      
	      for (UserBookingInfo ubi : list) {
	    	
	    	  model.addRow(new Object[] { ubi.getId(),ubi.getName(),ubi.getPlace(), ubi.getTitle(),
	        		 "7�� " + ubi.getDay(), ubi.getTime(),ubi.getPrice(),ubi.getSeatNum()});
	    	  System.out.println(model.getRowCount() + "���� ���� ");
	      }
	      
	      
	      /*for(int i = 1; i < 100; i++) {
	    	  UserBookingInfo ubi = list.get(i);
	    	  model.addRow(new Object[] {ubi.getId(),ubi.getName(),ubi.getPlace(), ubi.getTitle(),
		        		 ubi.getDay(), ubi.getTime(),ubi.getPrice(),ubi.getSeatNum()});
	      }*/

	      table = new JTable(model);
	      scrollPane = new JScrollPane(table);

	      add(scrollPane);

	      table.addMouseListener(new MouseListener() {
	         @Override
	         public void mouseReleased(MouseEvent e) {
	         }

	         @Override
	         public void mousePressed(MouseEvent e) {
	         }

	         @Override
	         public void mouseExited(MouseEvent e) {
	            // TODO Auto-generated method stub

	         }

	         @Override
	         public void mouseEntered(MouseEvent e) {
	            // TODO Auto-generated method stub

	         }

	         @Override
	         public void mouseClicked(MouseEvent e) {
	            JTable table = (JTable) e.getSource();
	            int row = table.getSelectedRow();
	            int col = table.getSelectedColumn();
	            DefaultTableModel model = (DefaultTableModel) table.getModel();
	            JOptionPane.showMessageDialog(frame, (model.getValueAt(row, col)));
	         }
	      });

	      setLayout(new GridLayout(1, 1));
	      setSize(400, 400);
	      setLocationRelativeTo(null);//�߾ӹ�ġ.

	   }
}

class Data extends JFrame {
   JTable table;
   Data frame;
   JScrollPane scrollPane;
   JButton b1;

   Data() {
      frame = this;

      String columns[] = { "�̸�", "���̵�", "��й�ȣ" };
      DefaultTableModel model = new DefaultTableModel(columns, 0);
      table = new JTable(model);
      HashSet<MemberObject> list = null;
      try {
         ObjectInputStream ois = new ObjectInputStream(new FileInputStream("MembershipManagement.dat"));
         list = (HashSet<MemberObject>) (ois.readObject());
         ois.close();
      } catch (FileNotFoundException e) {
         list = new HashSet<MemberObject>();
      } catch (Exception e) {
         e.printStackTrace();
      }

      
      System.out.println("HashSet�� size()��? " + list.size() + "\t" + "HashSet�� elements����? "
      + list);
      for (MemberObject m : list) {
         model.addRow(new Object[] { m.name, m.id, m.password });
      }

      table = new JTable(model);
      scrollPane = new JScrollPane(table);

      add(scrollPane);

      table.addMouseListener(new MouseListener() {
         @Override
         public void mouseReleased(MouseEvent e) {
         }

         @Override
         public void mousePressed(MouseEvent e) {
         }

         @Override
         public void mouseExited(MouseEvent e) {
            // TODO Auto-generated method stub

         }

         @Override
         public void mouseEntered(MouseEvent e) {
            // TODO Auto-generated method stub

         }

         @Override
         public void mouseClicked(MouseEvent e) {
            JTable table = (JTable) e.getSource();
            int row = table.getSelectedRow();
            int col = table.getSelectedColumn();
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            JOptionPane.showMessageDialog(frame, (model.getValueAt(row, col)));
         }
      });

      setLayout(new GridLayout(1, 1));
      setSize(400, 400);
      setLocationRelativeTo(null);

   }
}

public class Group_1 {

}
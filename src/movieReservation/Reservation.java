package movieReservation;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Reservation extends JFrame {
	private JPanel panel1;
	private JPanel panel2;
	static int choose = 3;
	
	private String id;
	private String pw; 
	private String name;

	Reservation(String id, String pw, String name) {
		this.id = id;
		this.pw = pw;
		this.name = name;
		System.out.println("사용자 정보: "  + "\n" + "ID: " + id + " \n" + "PW: " + pw + " \n" + 
		"이름 : " + name);
		
		
		
		setTitle("예매선택");
		setSize(350, 570);
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

		getContentPane().setBackground(Color.BLUE); // 전체바탕
		JButton button1 = new JButton("영화별 예매");
		JButton button2 = new JButton("상영관별 예매");
		setLayout(null);
		button1.setBounds(15, 70, 150, 150);
		button2.setBounds(170, 70, 150, 150);
		button1.setBorderPainted(false);
		button2.setBorderPainted(false);
		button1.setFont(new Font("굴림체", Font.BOLD, 15));
		button2.setFont(new Font("굴림체", Font.BOLD, 15));
		button1.setBackground(new Color(130, 236, 255));
		button2.setBackground(new Color(130, 236, 255));

		JButton button3 = new JButton("로그아웃");
		button3.setBounds(10, 370, 320, 30);
		button3.setBorderPainted(false);
		button3.setBackground(Color.DARK_GRAY);
		button3.setForeground(new Color(114, 114, 114));

		button1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				choose = 1;
				new MovieSelect2(id, pw, name).setVisible(true);
				setVisible(false);
			}
		});
		button2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				choose = 2;
				new TheatherSelect2(id, pw, name).setVisible(true);
				setVisible(false);
			}
		});
		button3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("로그아웃, 첫화면");
				dispose();
				new Login().setVisible(true);
			}
		});
		panel1.add(button1);
		panel1.add(button2);
		panel1.add(button3);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}
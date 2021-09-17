package movieReservation;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class MainCanvas extends Canvas{
      public MainCanvas() {
            setBackground(Color.WHITE);
            setSize(350,570);
         }
        
         @Override
         public void paint(Graphics g) {
            g.setColor(Color.blue);
            g.fillRect(0, 0,350, 50);
            g.setColor(Color.GRAY);
            g.fillRect(0, 50, 350, 30);
            
            g.setColor(Color.gray);
            g.setColor(Color.black);
            g.setFont(new Font("Arial", Font.BOLD, 15));
         
         }
}
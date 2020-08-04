//package bytehacks;

import java.awt.*; 
import java.awt.event.*; 
import javax.swing.*; 
import java.io.*;
import java.util.*;
import javax.swing.Timer;

class Food
{
	Image pic;
	int amtleft;
	String name;
}

public class Produce extends JPanel implements MouseListener, MouseMotionListener //I implement mouse and mouse motion listener so I can sense when you click or hover over any buttons
{
	int[][]rect = {{370,420,90,20,376,435,370,410},
			{370,810,90,20,376,825,370,800},
			{1060,420,90,20,1066,435,1060,410},
			{1060,810,90,20,1066,825, 1060,800}};
	
	Rectangle[]buy;
	Rectangle buy1;
	Rectangle buy2;
	Rectangle buy3;
	Rectangle buy4;
	
 	Runner nf;
 	Image sign;
 	
 	int[]stockOld;
 	
 	String fullFile;
 	String newStock;
 	
 	PrintWriter printer;
 	PrintWriter printer2;
 	Scanner sc;
 	
 	Image rice;
 	Image rice2;
 	Image rice3;
 	Image rice4;
 	
	Image pom;
 	Image pom2;
 	Image pom3;
 	Image pom4;
 	
	Image olive;
 	Image olive2;
 	Image olive3;
 	Image olive4;
 	
 	Image back;
 	Rectangle backRect;
 	
 	Food [] items;
 	Rectangle search;
 	Rectangle cartrect;
 	
 	Rectangle silky;
 	
 	Rectangle choice1;
 	Rectangle choice2;
 	Rectangle choice3;
 	
 	Rectangle yesRect, noRect;
 	
 	boolean clickedSearch;
 	boolean clicked1;
 	boolean clicked2;
 	boolean clicked3;
 	
 	boolean hover1;
 	boolean hover2;
 	boolean hover3;
 	
 	boolean gg, restock;
 	 	
	Image cart;
	
	int hot1;
	int hot2;
	int hot3;
	
	Timer ggTimer;
 	
 	public Produce(Runner nf) 
 	{
		new Keys();

 		buy1=new Rectangle(370,420,90,20);
 		buy2=new Rectangle(370,810,90,20);
 		buy3=new Rectangle(1060,420,90,20);
 		buy4=new Rectangle(1060,810,90,20);
 		
 		yesRect = new Rectangle(560, 410, 92, 55);
		noRect = new Rectangle(749, 410, 92, 55);

 		buy=new Rectangle[4];
 		buy[0]=buy1;
 		buy[1]=buy2;
 		buy[2]=buy3;
 		buy[3]=buy4;
 		
 		backRect = new Rectangle(1385, 82, 45, 45);
 		
 		silky = new Rectangle(30, 10, 480, 55);
 		
 		items = new Food[12];
 		sign = new ImageIcon("sign.png").getImage(); 
 		stockOld = new int[12];
 		
 		rice = new ImageIcon("Rice1.jpg").getImage(); 
 		items[0]=new Food();
 		items[0].pic=rice;
 		items[0].amtleft=5;
 		rice2 = new ImageIcon("Rice2.jpg").getImage(); 
 		items[1]=new Food();
 		items[1].pic=rice2;
 		items[1].amtleft=5;
 		rice3 = new ImageIcon("Rice3.jpg").getImage(); 
 		items[2]=new Food();
 		items[2].pic=rice3;
 		items[2].amtleft=5;
 		rice4 = new ImageIcon("Rice4.jpg").getImage();
 		items[3]=new Food();
 		items[3].pic=rice4;
 		items[3].amtleft=5;
 		
 		pom = new ImageIcon("Pomegranate1.jpg").getImage(); 
 		items[4]=new Food();
 		items[4].pic=pom;
 		items[4].amtleft=5;
 		pom2 = new ImageIcon("Promagranate2.jpg").getImage(); 
 		items[5]=new Food();
 		items[5].pic=pom2;
 		items[5].amtleft=5;
 		pom3 = new ImageIcon("Pomegranate3.jpg").getImage(); 
 		items[6]=new Food();
 		items[6].pic=pom3;
 		items[6].amtleft=5;
 		pom4 = new ImageIcon("Pomegranate4.jpg").getImage(); 
 		items[7]=new Food();
 		items[7].pic=pom4;
 		items[7].amtleft=5;
 		
 		olive = new ImageIcon("Olive1.jpg").getImage();
 		items[8]=new Food();
 		items[8].pic=olive;
 		items[8].amtleft=5;
 		olive2 = new ImageIcon("Olive2.jpg").getImage(); 
 		items[9]=new Food();
 		items[9].pic=olive2;
 		items[9].amtleft=5;
 		olive3 = new ImageIcon("Olive3.jpg").getImage(); 
 		items[10]=new Food();
 		items[10].pic=olive3;
 		items[10].amtleft=5;
 		olive4 = new ImageIcon("Olive4.jpg").getImage(); 
 		items[11]=new Food();
 		items[11].pic=olive4;
 		items[11].amtleft=5;
 		
 		back = new ImageIcon("back.png").getImage(); 
 		
 		cart = new ImageIcon("ShoppingCart.png").getImage(); 
 	 	this.nf = nf;
 	 	addMouseListener(this);
 	 	addMouseMotionListener(this);
 	 	search = new Rectangle(795, 15, 380, 45);
 	 	cartrect = new Rectangle(1330, 7, 72, 56);
 	 	clickedSearch = false;
 	 	choice1 = new Rectangle(795,60,380,60);
 	 	choice2 = new Rectangle(795,120,380,60);
 	 	choice3 = new Rectangle(795,180,380,60);
 	 	clicked1 = false;
 	 	clicked2 = false;
 	 	clicked3 = false;
 	 	hover1 = false;
 	 	hover2 = false;
 	 	hover3 = false;
 	 	
 	 	hot1=(int)(Math.random()*4);
 	 	hot2=(int)(Math.random()*4)+4;
 	 	hot3=(int)(Math.random()*4)+8;
 	 	
 	 	GGStopwatch gs = new GGStopwatch();
		ggTimer = new Timer(200, gs);
		
		gg = restock = false;
		newStock = "0 0 5\r\n" + 
				"0 1 5\r\n" + 
				"0 2 5\r\n" + 
				"0 3 5\r\n" + 
				"0 4 5\r\n" + 
				"0 5 5\r\n" + 
				"0 6 5\r\n" + 
				"0 7 5\r\n" + 
				"0 8 5\r\n" + 
				"0 9 5\r\n" + 
				"0 10 5\r\n" + 
				"0 11 5\r\n" + 
				"\r\n" + 
				"1 0 5\r\n" + 
				"1 1 5\r\n" + 
				"1 2 5\r\n" + 
				"1 3 5\r\n" + 
				"1 4 5\r\n" + 
				"1 5 5\r\n" + 
				"1 6 5\r\n" + 
				"1 7 5\r\n" + 
				"1 8 5\r\n" + 
				"1 9 5\r\n" + 
				"1 10 5\r\n" + 
				"1 11 5\r\n" + 
				"\r\n" + 
				"2 0 5\r\n" + 
				"2 1 5\r\n" + 
				"2 2 5\r\n" + 
				"2 3 5\r\n" + 
				"2 4 5\r\n" + 
				"2 5 5\r\n" + 
				"2 6 5\r\n" + 
				"2 7 5\r\n" + 
				"2 8 5\r\n" + 
				"2 9 5\r\n" + 
				"2 10 5\r\n" + 
				"2 11 5\r\n" + 
				"\r\n" + 
				"3 0 5\r\n" + 
				"3 1 5\r\n" + 
				"3 2 5\r\n" + 
				"3 3 5\r\n" + 
				"3 4 5\r\n" + 
				"3 5 5\r\n" + 
				"3 6 5\r\n" + 
				"3 7 5\r\n" + 
				"3 8 5\r\n" + 
				"3 9 5\r\n" + 
				"3 10 5\r\n" + 
				"3 11 5";
 	 	
 	 	tryCatchIt();
 	 	initializeStock();

 	}
 
 	public void paintComponent(Graphics g) 
	{
 		super.paintComponent(g); 
 		setBackground(Color.WHITE);
 		g.setColor(new Color(107,142,35));
 		g.fillRect(0, 0, 1437, 75);
 		g.setColor(Color.WHITE);
 		g.setFont(new Font("Papyrus",Font.BOLD,40));
  		g.drawString("Silky Milky Trading Co.", 30, 50);
  		g.setColor(Color.black);
  		((Graphics2D)g).setStroke(new BasicStroke(3));
  		g.drawLine(70, 75, 70, 105);
  		g.drawLine(300, 75, 300, 105);
  		g.setColor(Color.black);
  		g.drawImage(sign, 50, 100, 275, 110, null);
  		g.drawString("Produce", 75, 143);
  		g.drawString("For Sale!", 75, 190);
  		g.setColor(Color.WHITE);
		g.fillRect(775, 15, 400, 45);
		g.fillOval(750, 15, 45, 45);
		g.fillOval(1150, 15, 45, 45);
		
		((Graphics2D) g).setStroke(new BasicStroke(2));
		g.setColor(Color.GRAY);
		g.drawOval(765, 24, 18, 18);
		g.drawLine(782, 40, 791, 50);
		
		g.drawImage(cart, 1325, 0, 75, 69, null);
		
		g.setColor(new Color(107,142,35,40));
		g.fillRect(30, 250, 560, 550);
		g.fillRect(760, 125, 560, 675);
		
		g.setColor(Color.BLACK);
		g.drawRect(30, 250, 560, 550);
		g.drawRect(760, 125, 560, 675);
		
		g.drawImage(items[hot2].pic, 65, 440, 250, 250, null);
		g.drawImage(items[hot1].pic, 790, 205, 250, 250, null);
		g.drawImage(items[hot3].pic, 1055, 505, 250, 250, null);
		
		g.setFont(new Font("papyrus",Font.BOLD,40));
		g.drawString("Best Sellers", 100, 340);
		
		g.setFont(new Font("monospaced", Font.PLAIN, 18));
		if(hot3==8)
		{
			g.drawString("Black olives", 805, 600);
			g.drawString("from the", 805, 630);
			g.drawString("Mediterranean", 805, 660);
		}
		else if(hot3==9)
		{
			g.drawString("Green olives", 800, 600);
			g.drawString("imported from Greece.",800, 630);
			g.drawString("Stuffed with pimentos", 800, 660);
		}
		else if(hot3==10)
		{
			g.drawString("One bottle of ", 805, 600);
			g.drawString("freshly made olive ", 805, 630);
			g.drawString("oil. Great for", 805, 660);
			g.drawString("cooking use", 805, 690);
		}
		else
		{
			g.drawString("Black and green ", 805, 600);
			g.drawString("olives mix. Tastes", 805, 630);
			g.drawString("great with bread", 805, 660);
		}
		
		if(hot1==0)
		{
			g.drawString("Extremely tasty", 1060, 280);
			g.drawString("raw white rice.", 1060, 310);
			g.drawString("Fresh and organic", 1060, 340);
		}
		else if(hot1==1)
		{
			g.drawString("Uncooked jasmine rice.", 1060, 310);
			g.drawString("Comes in a bushel", 1060, 340);
		}
		else if(hot1==2)
		{
			g.drawString("Healthy, fresh", 1060, 280);
			g.drawString("brown rice. Goes", 1060, 310);
			g.drawString("well with soup", 1060, 340);
		}
		else
		{
			g.drawString("Precooked purple", 1060, 280);
			g.drawString("rice. Very", 1060, 310);
			g.drawString("healthy and tasty", 1060, 340);
		}
		
		if(hot2==4)
		{
			g.drawString("Juicy pomegranate", 330, 505);
			g.drawString("from Persia. Very", 330, 535);
			g.drawString("beneficial for your", 330, 565);
			g.drawString("health", 330, 595);
		}
		else if(hot2==5)
		{
			g.drawString("Red and juicy", 330, 505);
			g.drawString("pomegranate. Pre-cut", 330, 535);
			g.drawString("for convenience", 330, 565);
		}
		else if(hot2==6)
		{
			g.drawString("Organic fresh", 330, 505);
			g.drawString("pomegranate from", 330, 535);
			g.drawString("Turkey", 330, 565);
		}
		else
		{
			g.drawString("Sweet and fresh", 330, 505);
			g.drawString("whole pomegranate.", 330, 535);
			g.drawString("Very healthy", 330, 565);
		}
		
		//tryCatchIt();
		//initializeStock();
		
		g.setFont(new Font("monospaced",Font.PLAIN,25));
		if(clicked1)
		{
			g.setColor(Color.BLACK);
			g.drawString("Rice",810,47);
			g.setColor(Color.WHITE);
			g.fillRect(0, 75, 1450, 900);
			
			g.setColor(Color.LIGHT_GRAY);
			g.drawLine(700, 75, 700, 850);
			g.drawLine(0,470,1440,470);
			g.setColor(Color.DARK_GRAY);
			
			g.drawImage(items[0].pic, 50, 130, 300, 300, null);
			g.setFont(new Font("papyrus", Font.BOLD, 40));
			g.drawString("8 ounces", 370, 170);
			g.drawString("salt", 370, 220);
			g.setFont(new Font("Monospaced", Font.PLAIN, 20));
			g.drawString("Extremely tasty", 370, 290);
			g.drawString("raw white rice.", 370, 330);
			g.drawString("Fresh and organic.", 370, 370);
			g.setFont(new Font("Monospaced", Font.PLAIN, 12));
			
			g.drawImage(items[1].pic, 50, 510, 300, 300, null);
			g.setFont(new Font("papyrus", Font.BOLD, 40));
			g.drawString("1 piece", 370, 560);
			g.drawString("wool fabric", 370, 610);
			g.setFont(new Font("Monospaced", Font.PLAIN, 20));
			g.drawString("Uncooked jasmine rice.", 370, 680);
			g.drawString("Comes in a bushel.", 370, 720);
			g.setFont(new Font("Monospaced", Font.PLAIN, 12));
				
			g.drawImage(items[2].pic, 730, 130, 310, 290, null);
			g.setFont(new Font("papyrus", Font.BOLD, 40));
			g.drawString("2 ounce", 1060, 170);
			g.drawString("cinnamon", 1060, 220);
			g.setFont(new Font("Monospaced", Font.PLAIN, 20));
			g.drawString("Healthy, fresh", 1060, 290);
			g.drawString("brown rice. Goes", 1060, 330);
			g.drawString("well with soup.", 1060, 370);
			g.setFont(new Font("Monospaced", Font.PLAIN, 12));
					
			g.drawImage(items[3].pic, 730, 510, 300, 300, null);
			g.setFont(new Font("papyrus", Font.BOLD, 40));
			g.drawString("1 porcelain", 1060, 560);
			g.drawString("figurine", 1060, 610);
			g.setFont(new Font("Monospaced", Font.PLAIN, 20));
			g.drawString("Precooked purple", 1060, 680);
			g.drawString("rice. Very", 1060, 720);
			g.drawString("healthy and tasty.", 1060, 760);
			g.setFont(new Font("Monospaced", Font.PLAIN, 12));
			
			for(int i = 0; i<4; i++)
			{
				if(items[i].amtleft != 0)
				{
					g.setColor(new Color(107,142,35));
					g.fillRect(rect[i][0], rect[i][1], rect[i][2], rect[i][3]);
					g.setColor(Color.WHITE);
					g.drawString("ADD TO CART", rect[i][4], rect[i][5]);
					g.setColor(Color.BLACK);
					g.drawString("items left: " + items[i].amtleft, rect[i][6], rect[i][7]);
				}
				else
					g.drawString("OUT OF STOCK", rect[i][6], rect[i][7]);
			}
			
			g.drawImage(back, 1385, 82, 45, 45, null);
		}
		if(clicked2)
		{
			g.setColor(Color.BLACK);
			g.drawString("Pomegranates",810,47);
			g.setColor(Color.WHITE);
			g.fillRect(0, 75, 1450, 900);
			
			g.setColor(Color.LIGHT_GRAY);
			g.drawLine(700, 75, 700, 850);
			g.drawLine(0,470,1440,470);
			g.setColor(Color.BLACK);
			g.drawImage(items[4].pic, 50, 130, 300, 285, null);
			g.setFont(new Font("papyrus", Font.BOLD, 40));
			g.drawString("1 bowl", 370, 170);
			g.drawString("brown rice", 370, 220);
			g.setFont(new Font("Monospaced", Font.PLAIN, 20));
			g.drawString("Juicy pomegranate", 370, 290);
			g.drawString("from Persia. Very", 370, 330);
			g.drawString("beneficial for your health.", 370, 370);
			g.setFont(new Font("Monospaced", Font.PLAIN, 12));
			
			g.drawImage(items[5].pic, 50, 510, 300, 300, null);
			g.setFont(new Font("papyrus", Font.BOLD, 40));
			g.drawString("8 ounces", 370, 560);
			g.drawString("pepper", 370, 610);
			g.setFont(new Font("Monospaced", Font.PLAIN, 20));
			g.drawString("Red and juicy", 370, 680);
			g.drawString("pomegranate. Pre-cut", 370, 720);
			g.drawString("for convenience.", 370, 760);
			g.setFont(new Font("Monospaced", Font.PLAIN, 12));
			
			g.drawImage(items[6].pic, 730, 130, 310, 290, null);
			g.setFont(new Font("papyrus", Font.BOLD, 40));
			g.drawString("1 bronze", 1060, 170);
			g.drawString("coin", 1060, 220);
			g.setFont(new Font("Monospaced", Font.PLAIN, 20));
			g.drawString("Organic fresh", 1060, 290);
			g.drawString("pomegranate from", 1060, 330);
			g.drawString("Turkey.", 1060, 370);
			g.setFont(new Font("Monospaced", Font.PLAIN, 12));
			
			g.drawImage(items[7].pic, 730, 510, 300, 300, null);
			g.setFont(new Font("papyrus", Font.BOLD, 40));
			g.drawString("2 sticks", 1060, 560);
			g.drawString("cinnamon", 1060, 610);
			g.setFont(new Font("Monospaced", Font.PLAIN, 20));
			g.drawString("Sweet and fresh", 1060, 680);
			g.drawString("whole pomegranate.", 1060, 720);
			g.drawString("Very healthy.", 1060, 760);
			g.setFont(new Font("Monospaced", Font.PLAIN, 12));
			
			for(int i = 0; i<4; i++)
			{
				if(items[i+4].amtleft != 0)
				{
					g.setColor(new Color(107,142,35));
					g.fillRect(rect[i][0], rect[i][1], rect[i][2], rect[i][3]);
					g.setColor(Color.WHITE);
					g.drawString("ADD TO CART", rect[i][4], rect[i][5]);
					g.setColor(Color.BLACK);
					g.drawString("items left: " + items[i+4].amtleft, rect[i][6], rect[i][7]);
				}
				else
					g.drawString("OUT OF STOCK", rect[i][6], rect[i][7]);
			}
			
			g.drawImage(back, 1385, 82, 45, 45, null);
		}
		if(clicked3)
		{
			g.setColor(Color.BLACK);
			g.drawString("Olives",810,47);
			g.setColor(Color.WHITE);
			g.fillRect(0, 75, 1450, 900);
			
			g.setColor(Color.LIGHT_GRAY);
			g.drawLine(700, 75, 700, 850);
			g.drawLine(0,470,1440,470);
			
			g.setColor(Color.BLACK);
			g.drawImage(items[8].pic, 50, 130, 300, 300, null);
			g.setFont(new Font("papyrus", Font.BOLD, 40));
			g.drawString("2", 370, 170);
			g.drawString("carrots", 370, 220);
			g.setFont(new Font("Monospaced", Font.PLAIN, 20));
			g.drawString("Black olives", 370, 290);
			g.drawString("from the", 370, 330);
			g.drawString("Mediterranean.", 370, 370);
			g.setFont(new Font("Monospaced", Font.PLAIN, 12));
			
			g.drawImage(items[9].pic, 50, 510, 300, 300, null);
			g.setFont(new Font("papyrus", Font.BOLD, 40));
			g.drawString("1/4 pound", 370, 560);
			g.drawString("salt", 370, 610);
			g.setFont(new Font("Monospaced", Font.PLAIN, 20));
			g.drawString("Green olives", 370, 680);
			g.drawString("imported from Greece.", 370, 720);
			g.drawString("Stuffed with pimentos.", 370, 760);
			g.setFont(new Font("Monospaced", Font.PLAIN, 12));
			
			g.drawImage(items[10].pic, 730, 130, 310, 290, null);
			g.setFont(new Font("papyrus", Font.BOLD, 40));
			g.drawString("1 ounce", 1060, 170);
			g.drawString("saffron", 1060, 220);
			g.setFont(new Font("Monospaced", Font.PLAIN, 20));
			g.drawString("One bottle of", 1060, 290);
			g.drawString("freshly made olive oil.", 1060, 330);
			g.drawString("Great for cooking use.", 1060, 370);
			g.setFont(new Font("Monospaced", Font.PLAIN, 12));
			
			g.drawImage(items[11].pic, 730, 510, 300, 300, null);
			g.setFont(new Font("papyrus", Font.BOLD, 40));
			g.drawString("1 piece", 1060, 560);
			g.drawString("silk fabric", 1060, 610);
			g.setFont(new Font("Monospaced", Font.PLAIN, 20));
			g.drawString("Black and green olives", 1060, 680);
			g.drawString("mix. Tastes great", 1060, 720);
			g.drawString("with bread.", 1060, 760);
			g.setFont(new Font("Monospaced", Font.PLAIN, 12));
		
			for(int i = 0; i<4; i++)
			{
				if(items[i+8].amtleft != 0)
				{
					g.setColor(new Color(107,142,35));
					g.fillRect(rect[i][0], rect[i][1], rect[i][2], rect[i][3]);
					g.setColor(Color.WHITE);
					g.drawString("ADD TO CART", rect[i][4], rect[i][5]);
					g.setColor(Color.BLACK);
					g.drawString("items left: " + items[i+8].amtleft, rect[i][6], rect[i][7]);
				}
				else
					g.drawString("OUT OF STOCK", rect[i][6], rect[i][7]);
			}
			
			g.drawImage(back, 1385, 82, 45, 45, null);
		}
		if(clickedSearch)
		{
			g.setColor(Color.WHITE);
			g.fillRect(795, 60, 380, 180);
			g.setColor(Color.BLACK);
			g.drawRect(795,60,380,180);
			g.setFont(new Font("monospaced",Font.PLAIN,25));
			g.drawString("Rice",807,102);
			g.drawString("Pomegranates",807,162);
			g.drawString("Olives",807,222);
			g.drawLine(795, 120, 1175, 120);
			g.drawLine(795, 180, 1175, 180);
			
			g.setColor(new Color(107,142,35,60));
			if(hover1)
				g.fillRect(795,60,380,60);
			if(hover2)
				g.fillRect(795,120,380,60);
			if(hover3)
				g.fillRect(795,180,380,60);
		}
		
		if(restock)
		{
			// Rectangle
			((Graphics2D) g).setStroke(new BasicStroke(5));
			g.setColor(new Color(127, 162, 55));
			g.fillRect(400, 300, 600, 200);
			g.setColor(Color.BLACK);
			g.drawRect(400, 300, 600, 200);
			
			// Buttons
			g.setColor(Color.WHITE);
			g.fillRect(560, 410, 92, 55);
			g.fillRect(749, 410, 92, 55);
			g.setColor(Color.BLACK);
			((Graphics2D) g).setStroke(new BasicStroke(3));
			g.drawRect(560, 410, 92, 55);
			g.drawRect(749, 410, 92, 55);
			
			// Text
			g.setColor(Color.BLACK);
			g.setFont(new Font("Papyrus", Font.BOLD, 40));
			g.drawString("Would you like to restock?", 460, 360);
			g.setFont(new Font("monospaced", Font.BOLD, 35));
			g.drawString("YES", 575, 450);
			g.drawString("NO", 775, 450);
		}
		
 		requestFocus();
  	} 
 
	public void mousePressed (MouseEvent e) 
	{
		int x=e.getX();
		int y=e.getY();
		
		if(restock && yesRect.contains(x,y))
		{
			restock = false;
			restock();
		}
		else if(restock && noRect.contains(x,y))
			restock = false;
		
		if(choice1.contains(x,y) && clickedSearch)
		{
			clicked1=true;
			clicked2=false;
			clicked3=false;
			clickedSearch=false;
		}
		if(choice2.contains(x,y) && clickedSearch)
		{
			clicked1=false;
			clicked2=true;
			clicked3=false;
			clickedSearch=false;
		}
		if(choice3.contains(x,y) && clickedSearch)
		{
			clicked1=false;
			clicked2=false;
			clicked3=true;
			clickedSearch=false;
		}
		
		if(backRect.contains(x,y))
		{
			clicked1=false;
			clicked2=false;
			clicked3=false;
		}
		
		if(silky.contains(x,y))
		{
			tryCatchIt2();
			updateStock();
			nf.switchCards("menu");
		}
		
		if(cartrect.contains(x, y))
		{
			tryCatchIt2();
			updateStock();
			nf.switchCards("cart");
		}
		
		if(clicked1)
			for(int i = 0; i < 4; i++)
			{
				if(buy[i].contains(x,y) && items[i].amtleft>0)
				{
					items[i].amtleft-=1;
					printer.println(3 + " " + i);
					printer.close();
					tryCatchIt();
				}
			}
			
		else if(clicked2)
			for(int i = 0; i < 4; i++)
			{
				if(buy[i].contains(x,y) && items[i+4].amtleft>0)
				{
					items[i+4].amtleft-=1;
					printer.println(3 +" "+ (i+4));
					printer.close();
					tryCatchIt();
				}
			}

		else if(clicked3)
			for(int i = 0; i < 4; i++)
			{
				if(buy[i].contains(x,y) && items[i+8].amtleft>0)
				{
					items[i+8].amtleft-=1;
					printer.println(3 +" "+ (i+8));
					printer.close();
					tryCatchIt();
				}
			}
		
		if(search.contains(x, y))
			clickedSearch=true;
		else
			clickedSearch=false;
			
		repaint();
	}
	
	public void tryCatchIt()
	{
		File outputFile = new File("shoppingCart.txt");
		File inputFile = new File("totalStock.txt");
		try
		{
			if (!inputFile.exists())
				inputFile.createNewFile();
			if(!outputFile.exists())
				outputFile.createNewFile();
			printer = new PrintWriter(new FileWriter(outputFile, true));
			sc = new Scanner(inputFile);
		}
		catch(IOException e)
		{
			System.err.print("File IO Error");
		}
	}
	
	public void tryCatchIt2()
	{
		File outputFile2 = new File("totalStock.txt");
		try
		{
			if(!outputFile2.exists())
				outputFile2.createNewFile();
			printer2 = new PrintWriter(new FileWriter(outputFile2, false));
		}
		catch(IOException e)
		{
			System.err.print("File IO Error");
		}
	}
	
	public void initializeStock()
	{
		int counter, pos, num;
		pos = num = 0;
		counter=0;
		StringBuffer buffer = new StringBuffer();
		
		while(sc.hasNext())
		{
			String line = sc.nextLine();
			buffer.append(line + System.lineSeparator());

			if(counter>=39 && counter<=50)
			{
				if(line.length() == 5)
					pos = 4;
				else
					pos = 5;
				line = line.substring(pos);
				
				num = Integer.parseInt(line);
				stockOld[counter-39] = num;
				items[counter-39].amtleft = num;
				//System.out.println(stockOld[counter-39]);
			}
			counter++;
		}
		//System.out.println();
		fullFile = buffer.toString();
		sc.close();
		tryCatchIt();
	}
	
	public void updateStock()
	{
		for(int i = 0; i < 12; i++)
		{
			fullFile = fullFile.replace("3 " + i + " "+ stockOld[i], "3 " + i + " "+ items[i].amtleft);
		}
		
		printer2.print(fullFile);
		printer2.close();
	}
	
	public void restock()
	{
		tryCatchIt2();
		printer2.append(newStock);
		printer2.close();
		tryCatchIt();
		initializeStock();
		requestFocus();
		repaint();
	}
	
 	public void mouseReleased (MouseEvent e) {}
 	public void mouseClicked (MouseEvent e) {}
 	public void mouseEntered (MouseEvent e) {}
 	public void mouseExited (MouseEvent e) {}
 	public void mouseDragged (MouseEvent e) {}
 	
 	public void mouseMoved (MouseEvent e) 
	{
 		int x=e.getX();
		int y=e.getY();
		
		if(choice1.contains(x,y))
			hover1=true;
		else
			hover1=false;
		if(choice2.contains(x,y))
			hover2=true;
		else
			hover2=false;
		if(choice3.contains(x,y))
			hover3=true;
		else
			hover3=false;
		
		repaint();
	}

 	class GGStopwatch implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			gg = false;
			ggTimer.stop();
			
			grabFocus();
			repaint();
		}		
	}
	
	class Keys implements KeyListener
	{
		public Keys()
		{
			addKeyListener(this);
		}
		public void keyPressed(KeyEvent e)
		{
			char key = e.getKeyChar();
			if(key == 'g')
			{
				if(!gg)
				{
					gg = true;
					ggTimer.restart();
				}
				else
				{
					ggTimer.stop();
					restock = true;
					gg = false;
				}	
			}
			
			requestFocus();
			repaint();
		}

		public void keyReleased(KeyEvent e)
		{}

		public void keyTyped(KeyEvent e)
		{}
		
	}
}

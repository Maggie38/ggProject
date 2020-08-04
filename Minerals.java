package bytehacks;

import javax.swing.*;
import javax.swing.Timer;

import bytehacks.Materials.GGStopwatch;
import bytehacks.Materials.Keys;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.Timer;

// Jade, silver, iron
public class Minerals extends JPanel
{
	Runner run;
	Color bannerColor;
	Font bannerFont, minFont, descripFont, descripFont2;
	PrintWriter printer, printer2;
	Scanner input;
	StringBuffer buffer;
	String fullFile, newStock;
	Image sign, cart, backArrow;
	Image jade1 = new ImageIcon("jade1.jpg").getImage();
	Image jade2 = new ImageIcon("jade2.jpeg").getImage();
	Image jade3 = new ImageIcon("jade3.jpg").getImage();
	Image jade4 = new ImageIcon("jade4.jpg").getImage();
	Image silver1 = new ImageIcon("silver1.jpeg").getImage();
	Image silver2 = new ImageIcon("silver2.jpg").getImage();
	Image silver3 = new ImageIcon("silver3.jpg").getImage();
	Image silver4 = new ImageIcon("silver4.jpg").getImage();
	Image iron1 = new ImageIcon("iron1.jpg").getImage();
	Image iron2 = new ImageIcon("iron2.jpg").getImage();
	Image iron3 = new ImageIcon("iron3.jpg").getImage();
	Image iron4 = new ImageIcon("iron4.jpg").getImage();
	int [][] rect = {{370,420,90,20,376,435,370,410},
							{370,810,90,20,376,825,370,800},
							{1060,420,90,20,1066,435,1060,410},
							{1060,810,90,20,1066,825, 1060,800}};
	Image[][] minItems = {{jade1, jade2, jade3, jade4},
							{silver1, silver2, silver3, silver4},
							{iron1, iron2, iron3, iron4}};
	String[] minNames = {"Jade", "Silver", "Iron"};
	int[][] oldMinStock = {{5, 5, 5, 5}, 
						{5, 5, 5, 5}, 
						{5, 5, 5, 5}};
	int[][] minStock = {{5, 5, 5, 5}, 
			{5, 5, 5, 5}, 
			{5, 5, 5, 5}};
	int[][] totalStock = new int[4][12];
	boolean searching, selected, hover1, hover2, hover3, gg, restock;
	int item, ranCol, ranCol2, ranCol3 = -1;
	Timer ggTimer;
 	
 	public Minerals(Runner run) 
 	{
 		this.run = run;
		tryCatchIt();
		initializeStock();
		new Buttons();
		new Keys();
		
		setBackground(Color.WHITE);
		
		// Initialize variables
		bannerColor = new Color(107,142,35);
		bannerFont = new Font("Papyrus", Font.BOLD, 40);
		minFont = new Font("monospaced", Font.PLAIN, 25);
		descripFont = new Font("monospaced", Font.PLAIN, 18);
		descripFont2 = new Font("monospaced", Font.PLAIN, 20);
		sign = new ImageIcon("sign.png").getImage();
		cart = new ImageIcon("shoppingCart.png").getImage();
		backArrow = new ImageIcon("back.png").getImage();	
		
		searching = hover1 = hover2 = hover3 = false;
		ranCol = (int)(Math.random() * 4);
		ranCol2 = (int)(Math.random() * 4);
		ranCol3 = (int)(Math.random() * 4);
		
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
		
		requestFocus();	
 	}
 
 	public void paintComponent(Graphics pixel) 
	{
 		super.paintComponent(pixel);
		
		// Title and banner
		pixel.setColor(bannerColor);
		pixel.fillRect(0, 0, 1437, 75);
		pixel.setFont(bannerFont);
		pixel.setColor(Color.WHITE);
		pixel.drawString("Silky Milky Trading Co.", 30, 50);		
		
		// Search bar
		pixel.setColor(Color.WHITE);
		pixel.fillRect(775, 15, 400, 45);
		pixel.fillOval(750, 15, 45, 45);
		pixel.fillOval(1150, 15, 45, 45);
		
		// Magnifying glass
		((Graphics2D) pixel).setStroke(new BasicStroke(2));
		pixel.setColor(Color.GRAY);
		pixel.drawOval(765, 24, 18, 18);
		pixel.drawLine(782, 40, 791, 50);
		
		// Shopping cart
		pixel.drawImage(cart, 1325, 0, 75, 69, null);
 		
		if(item == 1 || item == 2 || item == 3)
		{
			// Draw pictures
			pixel.setFont(minFont);
			pixel.drawImage(minItems[item-1][0], 50, 125, 300, 300, null);
			pixel.drawImage(minItems[item-1][1], 50, 510, 300, 300, null);
		    pixel.drawImage(minItems[item-1][2], 730, 125, 300, 300, null);
			pixel.drawImage(minItems[item-1][3], 730, 510, 300, 300, null);
						
			// Draw searchbar name
			pixel.setColor(Color.BLACK);
			pixel.drawString(minNames[item-1], 810, 47);
						
			// Draw sign
			pixel.setColor(Color.LIGHT_GRAY);
			pixel.drawLine(700, 75, 700, 850);
			pixel.drawLine(0,470,1440,470);

			// Draw backarrow
			pixel.drawImage(backArrow, 1385, 82, 45, 45, null);
			
			// Products
			if(item == 1)
			{			
				pixel.setColor(Color.BLACK);
				pixel.setFont(new Font("papyrus", Font.BOLD, 40));
				pixel.drawString("3 kilograms", 370, 170);
				pixel.drawString("ginger spices", 370, 220);
				pixel.drawString("1 cartload", 370, 560);
				pixel.drawString("wool", 370, 610);
				pixel.drawString("1 piece", 1060, 170);
				pixel.drawString("European artwork", 1060, 220);
				pixel.drawString("2 pieces", 1060, 560);
				pixel.drawString("gold", 1060, 610);
				
				pixel.setFont(new Font("Monospaced", Font.PLAIN, 20));
				pixel.drawString("Very intricate carved jade", 370, 290);
				pixel.drawString("dragon from China. Great", 370, 330);
				pixel.drawString("decoration for your homes!", 370, 370);
				pixel.drawString("Beatiful, clear white", 370, 680);
				pixel.drawString("jade from China.", 370, 720);
				pixel.drawString("Brings good fortune.", 370, 760);
				pixel.drawString("Genuine polished jade", 1060, 290);
				pixel.drawString("rocks. Perfect for", 1060, 330);
				pixel.drawString("showcasing your wealth.", 1060, 370);
				pixel.drawString("Clear, green and white", 1060, 680);
				pixel.drawString("jade ring. Perfect", 1060, 720);
				pixel.drawString("gift for a loved one!", 1060, 760);
	
				pixel.setFont(new Font("Monospaced", Font.PLAIN, 12));
				for(int i = 0; i < 4; i++)
				{
					if(minStock[item-1][i] != 0)
					{
						pixel.setColor(new Color(107,142,35));
						pixel.fillRect(rect[i][0], rect[i][1], rect[i][2], rect[i][3]);
						pixel.setColor(Color.WHITE);
						pixel.drawString("ADD TO CART", rect[i][4], rect[i][5]);
						pixel.setColor(Color.BLACK);
						pixel.drawString("items left: " + minStock[item-1][i], rect[i][6], rect[i][7]);
					}
					else
						pixel.drawString("OUT OF STOCK", rect[i][6], rect[i][7]);
				}
			}
			else if(item == 2)
			{
				pixel.setColor(Color.BLACK);
				pixel.setFont(new Font("papyrus", Font.BOLD, 40));
				pixel.drawString("5 rolls", 370, 170);
				pixel.drawString("multicolored silk", 370, 220);
				pixel.drawString("2 cartload", 370, 560);
				pixel.drawString("white rice", 370, 610);
				pixel.drawString("3 nuggets", 1060, 170);
				pixel.drawString("24 karat gold", 1060, 220);
				pixel.drawString("1/2 pound", 1060, 560);
				pixel.drawString("saffron", 1060, 610);
				
				pixel.setFont(new Font("Monospaced", Font.PLAIN, 20));
				pixel.drawString("Genuine silver", 370, 290);
				pixel.drawString("from central Asia.", 370, 330);
				pixel.drawString("Very beautiful and shiny.", 370, 370);
				pixel.drawString("Chunky silver", 370, 680);
				pixel.drawString("ore. Mined from", 370, 720);
				pixel.drawString("central Asia", 370, 760);
				pixel.drawString("Multicolored silver", 1060, 290);
				pixel.drawString("pieces. Raw and", 1060, 330);
				pixel.drawString("unpolished.", 1060, 370);
				pixel.drawString("Beautiful polished", 1060, 680);
				pixel.drawString("silver sculpture.", 1060, 720);
				pixel.drawString("Engraved by hand.", 1060, 760);
	
				pixel.setFont(new Font("Monospaced", Font.PLAIN, 12));
				for(int i = 0; i < 4; i++)
				{
					if(minStock[item-1][i] != 0)
					{
						pixel.setColor(new Color(107,142,35));
						pixel.fillRect(rect[i][0], rect[i][1], rect[i][2], rect[i][3]);
						pixel.setColor(Color.WHITE);
						pixel.drawString("ADD TO CART", rect[i][4], rect[i][5]);
						pixel.setColor(Color.BLACK);
						pixel.drawString("items left: " + minStock[item-1][i], rect[i][6], rect[i][7]);
					}
					else
						pixel.drawString("OUT OF STOCK", rect[i][6], rect[i][7]);
				}
			}
			else if(item == 3)
			{
				pixel.setColor(Color.BLACK);
				pixel.setFont(new Font("papyrus", Font.BOLD, 40));
				pixel.drawString("2 pounds", 370, 170);
				pixel.drawString("pomegranate", 370, 220);
				pixel.drawString("8 ounces", 370, 560);
				pixel.drawString("olive oil", 370, 610);
				pixel.drawString("2 pounds", 1060, 170);
				pixel.drawString("ivory", 1060, 220);
				pixel.drawString("12 ounces", 1060, 560);
				pixel.drawString("ground cumin", 1060, 610);
				
				pixel.setFont(new Font("Monospaced", Font.PLAIN, 20));
				pixel.drawString("Rough iron from mine", 370, 290);
				pixel.drawString("shafts deep within", 370, 330);
				pixel.drawString("southern China.", 370, 370);
				pixel.drawString("Shiny iron pieces.", 370, 680);
				pixel.drawString("Can be used to make", 370, 720);
				pixel.drawString("weapons or decorations", 370, 760);
				pixel.drawString("Jagged iron", 1060, 290);
				pixel.drawString("chunk. Great", 1060, 330);
				pixel.drawString("decoration.", 1060, 370);
				pixel.drawString("Shiny, polished,", 1060, 680);
				pixel.drawString("iron alloy", 1060, 720);
				pixel.drawString("ore.", 1060, 760);
				
				pixel.setFont(new Font("Monospaced", Font.PLAIN, 12));
				for(int i = 0; i < 4; i++)
				{
					if(minStock[item-1][i] != 0)
					{
						pixel.setColor(new Color(107,142,35));
						pixel.fillRect(rect[i][0], rect[i][1], rect[i][2], rect[i][3]);
						pixel.setColor(Color.WHITE);
						pixel.drawString("ADD TO CART", rect[i][4], rect[i][5]);
						pixel.setColor(Color.BLACK);
						pixel.drawString("items left: " + minStock[item-1][i], rect[i][6], rect[i][7]);
					}
					else
						pixel.drawString("OUT OF STOCK", rect[i][6], rect[i][7]);
				}
			}
		}
		else
		{
			// Fill item boxes
			pixel.setColor(new Color(107,142,35,40));
			pixel.fillRect(30, 250, 560, 550);
			pixel.fillRect(760, 125, 560, 675);

			// Item boxes
			pixel.setColor(Color.BLACK);
			pixel.drawRect(30, 250, 560, 550);
			pixel.drawRect(760, 125, 560, 675);
			
			// Best sellers
			pixel.setFont(new Font("papyrus",Font.BOLD,40));
			pixel.drawString("Best Sellers", 100, 340);
			
			// Sign
			pixel.setFont(bannerFont);
			pixel.setColor(Color.BLACK);
	  		((Graphics2D) pixel).setStroke(new BasicStroke(3));
	  		pixel.drawLine(70, 75, 70, 105);
	  		pixel.drawLine(300, 75, 300, 105);
	  		pixel.setColor(Color.BLACK);
	  		pixel.drawImage(sign, 50, 100, 275, 110, null);
	  		pixel.drawString("Minerals", 75, 143);
	  		pixel.drawString("For Sale!", 75, 190);
	  		
	  		// Hot picks pics
	  		pixel.setFont(descripFont);
			pixel.drawImage(minItems[0][ranCol], 75, 440, 250, 250, null);
			
			// Jade
			if(ranCol == 0)
			{
				pixel.drawString("Very intricate carved", 340, 535);
				pixel.drawString("jade dragon from", 340, 565);
				pixel.drawString("China.Great decoration", 340, 595);
				pixel.drawString("for your homes!", 340, 625);
			}
			else if(ranCol == 1)
			{
				pixel.drawString("Beatiful, clear white", 340, 505);
				pixel.drawString("jade from China.", 340, 535);
				pixel.drawString("Brings good fortune.", 340, 565);
			}
			else if(ranCol == 2)
			{
				pixel.drawString("Genuine polished jade", 340, 505);
				pixel.drawString("rocks. Perfect for", 340, 535);
				pixel.drawString("showcasing your wealth", 340, 565);
			}
			else if(ranCol == 3)
			{
				pixel.drawString("Clear, green and white", 340, 505);
				pixel.drawString("jade ring. Perfect", 340, 535);
				pixel.drawString("gift for a loved one!", 340, 565);
			}
			
			pixel.drawImage(minItems[1][ranCol2], 800, 205, 250, 250, null);
			if(ranCol2 == 0)
			{
				pixel.drawString("Genuine silver", 1065, 280);
				pixel.drawString("from central Asia.", 1065, 310);
				pixel.drawString("Very beautiful", 1065, 340);
				pixel.drawString("and shiny", 1065, 370);
			}
			else if(ranCol2 == 1)
			{
				pixel.drawString("Chunky silver", 1065, 280);
				pixel.drawString("ore. Mined from", 1065, 310);
				pixel.drawString("central Asia", 1065, 340);
			}
			else if(ranCol2 == 2)
			{
				pixel.drawString("Multicolored silver", 1065, 280);
				pixel.drawString("pieces. Raw and", 1065, 310);
				pixel.drawString("unpolished", 1065, 340);
			}
			else if(ranCol2 == 3)
			{
				pixel.drawString("Beautiful polished", 1065, 280);
				pixel.drawString("silver sculpture.", 1065, 310);
				pixel.drawString("Engraved by hand", 1065, 340);
			}
			
			// Iron
			pixel.drawImage(minItems[2][ranCol3], 1050, 505, 250, 250, null);
			if(ranCol3 == 0)
			{
				pixel.drawString("Rough iron from mine", 800, 600);
				pixel.drawString("shafts deep within", 800, 630);
				pixel.drawString("southern China", 800, 660);
			}
			else if(ranCol3 == 1)
			{
				pixel.drawString("Shiny iron pieces.", 800, 600);
				pixel.drawString("Can be used to make", 800, 630);
				pixel.drawString("weapons or decoration", 800, 660);
			}
			else if(ranCol3 == 2)
			{
				pixel.drawString("Jagged iron", 800, 600);
				pixel.drawString("chunk. Great", 800, 630);
				pixel.drawString("decoration", 800, 660);
			}
			else if(ranCol3 == 3)
			{
				pixel.drawString("Shiny, polished,", 800, 600);
				pixel.drawString("iron alloy", 800, 630);
				pixel.drawString("ore", 800, 660);
			}
		}
		
		if(searching)
		{
			pixel.setColor(Color.WHITE);
			pixel.fillRect(795, 60, 380, 180);
			pixel.setColor(Color.BLACK);
			pixel.drawRect(795, 60, 380, 180);
			pixel.drawLine(795, 120, 1175, 120);
			pixel.drawLine(795, 180, 1175, 180);
			
			pixel.setFont(minFont);
			pixel.drawString("Jade",807,102);
		    pixel.drawString("Silver",807,162);
			pixel.drawString("Iron",807,222);

			pixel.setColor(new Color(107,142,35,60));
			if(hover1)
				pixel.fillRect(795, 60, 380, 60);
			else if(hover2)
				pixel.fillRect(795, 120, 380, 60);
			else if(hover3)
				pixel.fillRect(795, 180, 380, 60);
		}		
		
		if(restock)
		{
			// Rectangle
			((Graphics2D) pixel).setStroke(new BasicStroke(5));
			pixel.setColor(new Color(127, 162, 55));
			pixel.fillRect(400, 300, 600, 200);
			pixel.setColor(Color.BLACK);
			pixel.drawRect(400, 300, 600, 200);
			
			// Buttons
			pixel.setColor(Color.WHITE);
			pixel.fillRect(560, 410, 92, 55);
			pixel.fillRect(749, 410, 92, 55);
			pixel.setColor(Color.BLACK);
			((Graphics2D) pixel).setStroke(new BasicStroke(3));
			pixel.drawRect(560, 410, 92, 55);
			pixel.drawRect(749, 410, 92, 55);
			
			// Text
			pixel.setColor(Color.BLACK);
			pixel.setFont(new Font("Papyrus", Font.BOLD, 40));
			pixel.drawString("Would you like to restock?", 460, 360);
			pixel.setFont(new Font("monospaced", Font.BOLD, 35));
			pixel.drawString("YES", 575, 450);
			pixel.drawString("NO", 775, 450);
		}
		
 		requestFocus();
  	} 
 
 	public void initializeStock()
	{
		int counter, row, col, pos, num;
		counter = col = row = pos = num = 0;
		buffer = new StringBuffer();
		
		while(input.hasNext())
		{
			String line = input.nextLine();
			buffer.append(line + System.lineSeparator());

			if(counter >= 13 && counter < 25)
			{
				if(line.length() == 5)
					pos = 4;
				else
					pos = 5;
				line = line.substring(pos);
				
				num = Integer.parseInt(line);
				minStock[row][col] = num;
				oldMinStock[row][col] = num;
				col+= 1;
				if(col == 4)
				{
					col = 0;
					row += 1;
				}
			}
			counter+=1;
		}
		fullFile = buffer.toString();
		input.close();
		tryCatchIt();
	}

 	public void updateStock()
	{
		int row, col;
		row = col = 0;
		
		for(int i = 0; i < 12; i++)
		{
			fullFile = fullFile.replace("1 " + i + " "+ oldMinStock[row][col], "1 " + i + " "+ minStock[row][col]);
			col+= 1;
			if(col == 4)
			{
				col = 0;
				row += 1;
			}
		}
		printer2.append(fullFile);
		printer2.close();
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
			input = new Scanner(inputFile);
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

 	class Buttons implements MouseListener, MouseMotionListener
	{
		Rectangle searchBar, cartRect, silkRect, cottonRect, woolRect, menuRect, backRect, yesRect, noRect;
		Rectangle add1 = new Rectangle(370,420,90,20);
		Rectangle add2 = new Rectangle(370,810,90,20);
		Rectangle add3 = new Rectangle(1060,420,90,20);
		Rectangle add4 = new Rectangle(1060,810,90,20);
		Rectangle[] add = {add1, add2, add3, add4};
		
		public Buttons()
		{
			addMouseListener(this);
			addMouseMotionListener(this);
			
			searchBar = new Rectangle(795, 15, 380, 45);
			cartRect = new Rectangle(1330, 7, 72, 56);
			silkRect  = new Rectangle(795, 60, 380, 60);
			cottonRect = new Rectangle(795, 120, 380, 60);
			woolRect = new Rectangle(795, 180, 380, 60);
			menuRect = new Rectangle(30, 10, 480, 55);
			backRect = new Rectangle(1385, 82, 45, 45);
			yesRect = new Rectangle(560, 410, 92, 55);
			noRect = new Rectangle(749, 410, 92, 55);
		}
		
		public void mouseClicked(MouseEvent event)
		{}

		public void mouseEntered(MouseEvent event)
		{}

		public void mouseExited(MouseEvent event)
		{}

		public void mousePressed(MouseEvent event)
		{
			int x = event.getX();
			int y = event.getY();
			
			if(restock && yesRect.contains(x,y))
			{
				restock = false;
				restock();
			}
			else if(restock && noRect.contains(x,y))
				restock = false;
			else if(menuRect.contains(x,y))
			{
				tryCatchIt2();
				updateStock();
				run.switchCards("menu");
			}
			else if(cartRect.contains(x,y))
			{
				tryCatchIt2();
				updateStock();
				run.switchCards("cart");
			}
			else if(backRect.contains(x,y))
			{
				item = -1;
				searching = false;
			}
			else if(searchBar.contains(x,y))
				searching = true;
			else if(silkRect.contains(x,y) && searching)
			{
				searching = false;
				item = 1;
			}
			else if(cottonRect.contains(x,y) && searching)
			{
				searching = false;
				item = 2;
			}
			else if(woolRect.contains(x,y) && searching)
			{
				searching = false;
				item = 3;
			}
			else
				searching = false;	
			
			for(int i = 0; i < 4; i++)
			{
				if(add[i].contains(x, y) && (minStock[item-1][i] != 0))
				{
					minStock[item-1][i] -= 1;
					printer.println(1 + " " + ((item-1)*4+i));
					printer.close();
					tryCatchIt();
				}
			}
		
			requestFocus();
			repaint();
		}

		public void mouseReleased(MouseEvent event)
		{}
		
		public void mouseDragged(MouseEvent event)
		{}
		
		public void mouseMoved(MouseEvent event)
		{
			int x = event.getX();
			int y = event.getY();
			
			if(silkRect.contains(x,y))
			{
				hover1 = true;
				hover2 = hover3 = false;
			}
			else if(cottonRect.contains(x,y))
			{
				hover2 = true;
				hover1 = hover3 = false;
			}
			else if(woolRect.contains(x,y))
			{
				hover3 = true;
				hover1 = hover2 = false;
			}
			else
				hover1 = hover2 = hover3 = false;
			
			requestFocus();
			repaint();
		}		
	}
}
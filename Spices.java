package bytehacks;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Scanner;
import javax.swing.Timer;

import bytehacks.Materials.GGStopwatch;
import bytehacks.Materials.Keys;

// Salt, saffron, cinnamon
public class Spices extends JPanel
{
	Runner run;
	Color bannerColor;
	Font bannerFont, spiceFont, descripFont, descripFont2;
	PrintWriter printer, printer2;
	Scanner input;
	StringBuffer buffer;
	String fullFile, newStock;
	Image sign, cart, backArrow;
	
	Image salt1 = new ImageIcon("salt1.jpg").getImage();
	Image salt2 = new ImageIcon("salt2.jpg").getImage();
	Image salt3 = new ImageIcon("salt3.jpg").getImage();
	Image salt4 = new ImageIcon("salt4.jpg").getImage();
	Image saff1 = new ImageIcon("saffron1.jpg").getImage();
	Image saff2 = new ImageIcon("saffron2.jpg").getImage();
	Image saff3 = new ImageIcon("saffron3.jpg").getImage();
	Image saff4 = new ImageIcon("saffron4.jpg").getImage();
	Image cinn1 = new ImageIcon("cinnamon1.jpg").getImage();
	Image cinn2 = new ImageIcon("cinnamon2.jpg").getImage();
	Image cinn3 = new ImageIcon("cinnamon3.jpg").getImage();
	Image cinn4 = new ImageIcon("cinnamon4.jpg").getImage();
	
	int [][] rect = {{370,420,90,20,376,435,370,410},
			{370,810,90,20,376,825,370,800},
			{1060,420,90,20,1066,435,1060,410},
			{1060,810,90,20,1066,825, 1060,800}};
	
	Image[][] spiceItems = {{salt1, salt2, salt3, salt4}, 
							{saff1, saff2, saff3, saff4}, 
							{cinn1, cinn2, cinn3, cinn4}};	
	String[] spiceNames = {"Salt", "Saffron", "Cinnamon"};
	int[][] spiceStock = {{5, 5, 5, 5}, 
							{5, 5, 5, 5}, 
							{5, 5, 5, 5}};
	int[][] oldSpiceStock = {{5, 5, 5, 5}, 
								{5, 5, 5, 5}, 
								{5, 5, 5, 5}};
	boolean searching, selected, hover1, hover2, hover3, gg, restock;;
	int item, ranCol, ranCol2, ranCol3 = -1;
	Timer ggTimer;
	
	public Spices(Runner run)
	{
		this.run = run;
		
		new Buttons();
		tryCatchIt();
		initializeStock();
		new Keys();
				
		setBackground(Color.WHITE);
		
		// Initialize variables
		bannerColor = new Color(107,142,35);
		bannerFont = new Font("Papyrus", Font.BOLD, 40);
		spiceFont = new Font("monospaced", Font.PLAIN, 25);
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
		
		((Graphics2D) pixel).setStroke(new BasicStroke(2));
		pixel.setColor(Color.GRAY);
		pixel.drawOval(765, 24, 18, 18);
		pixel.drawLine(782, 40, 791, 50);
		
		pixel.drawImage(cart, 1325, 0, 75, 69, null);
		
		if(item == 1 || item == 2 || item == 3)
		{
			pixel.setFont(spiceFont);
			pixel.drawImage(spiceItems[item-1][0], 50, 125, 300, 300, null);
			pixel.drawImage(spiceItems[item-1][1], 50, 510, 300, 300, null);
			pixel.drawImage(spiceItems[item-1][2], 730, 125, 300, 300, null);
			pixel.drawImage(spiceItems[item-1][3], 730, 510, 300, 300, null);
			
			pixel.setColor(Color.BLACK);
			pixel.drawString(spiceNames[item-1], 810, 47);
			
			pixel.setColor(Color.LIGHT_GRAY);
			pixel.drawLine(700, 75, 700, 850);
			pixel.drawLine(0,470,1440,470);

			pixel.drawImage(backArrow, 1385, 82, 45, 45, null);
			
			if(item == 1)
			{
				pixel.setColor(Color.BLACK);
				pixel.setFont(new Font("papyrus", Font.BOLD, 40));
				pixel.drawString("1 pound", 370, 170);
				pixel.drawString("tea leaves", 370, 220);
				pixel.drawString("5 cups", 370, 560);
				pixel.drawString("sugar", 370, 610);
				pixel.drawString("6 ounces", 1060, 170);
				pixel.drawString("ivory", 1060, 220);
				pixel.drawString("1 box", 1060, 560);
				pixel.drawString("cotton", 1060, 610);
				
				pixel.setFont(new Font("Monospaced", Font.PLAIN, 20));
				pixel.drawString("Pink himalayan salt rich", 370, 290);
				pixel.drawString("in minerals, and brought", 370, 330);
				pixel.drawString("from Pakistan.", 370, 370);
				pixel.drawString("Granulated Kosher salt", 370, 680);
				pixel.drawString("imported from North Africa.", 370, 720);
				pixel.drawString("Great for preserving.", 370, 760);
				pixel.drawString("Flakey Kosher salt", 1060, 290);
				pixel.drawString("imported from Asia.", 1060, 330);
				pixel.drawString("Season your food.", 1060, 370);
				pixel.drawString("Granulated Kosher salt", 1060, 680);
				pixel.drawString("from Asia that includes", 1060, 720);
				pixel.drawString("a wood mortar.", 1060, 760);
				
				pixel.setFont(new Font("Monospaced", Font.PLAIN, 12));
				for(int i = 0; i < 4; i++)
				{
					if(spiceStock[item-1][i] != 0)
					{
						pixel.setColor(new Color(107,142,35));
						pixel.fillRect(rect[i][0], rect[i][1], rect[i][2], rect[i][3]);
						pixel.setColor(Color.WHITE);
						pixel.drawString("ADD TO CART", rect[i][4], rect[i][5]);
						pixel.setColor(Color.BLACK);
						pixel.drawString("items left: " + spiceStock[item-1][i], rect[i][6], rect[i][7]);
					}
					else
						pixel.drawString("OUT OF STOCK", rect[i][6], rect[i][7]);
				}
			}
			else if(item == 2)
			{
				pixel.setColor(Color.BLACK);
				pixel.setFont(new Font("papyrus", Font.BOLD, 40));
				pixel.drawString("3 pounds", 370, 170);
				pixel.drawString("figs", 370, 220);
				pixel.drawString("5 pounds", 370, 560);
				pixel.drawString("porcelain", 370, 610);
				pixel.drawString("5 pounds", 1060, 170);
				pixel.drawString("silver", 1060, 220);
				pixel.drawString("10 pieces", 1060, 560);
				pixel.drawString("gold", 1060, 610);
				
				pixel.setFont(new Font("Monospaced", Font.PLAIN, 20));
				pixel.drawString("Freshly picked saffron", 370, 290);
				pixel.drawString("straight from the fields", 370, 330);
				pixel.drawString("of Asia.", 370, 370);
				pixel.drawString("Genuine saffron,", 370, 680);
				pixel.drawString("a cure-all spice", 370, 720);
				pixel.drawString("imported from Asia.", 370, 760);
				pixel.drawString("A teaspoon of", 1060, 290);
				pixel.drawString("great value saffron.", 1060, 330);
				pixel.drawString("imported from Asia.", 1060, 370);
				pixel.drawString("A bowl of fragrant,", 1060, 680);
				pixel.drawString("rare saffron", 1060, 720);
				pixel.drawString("imported from Asia.", 1060, 760);
				
				pixel.setFont(new Font("Monospaced", Font.PLAIN, 12));
				for(int i = 0; i < 4; i++)
				{
					if(spiceStock[item-1][i] != 0)
					{
						pixel.setColor(new Color(107,142,35));
						pixel.fillRect(rect[i][0], rect[i][1], rect[i][2], rect[i][3]);
						pixel.setColor(Color.WHITE);
						pixel.drawString("ADD TO CART", rect[i][4], rect[i][5]);
						pixel.setColor(Color.BLACK);
						pixel.drawString("items left: " + spiceStock[item-1][i], rect[i][6], rect[i][7]);
					}
					else
						pixel.drawString("OUT OF STOCK", rect[i][6], rect[i][7]);
				}
			}
			else if(item == 3)
			{
				pixel.setColor(Color.BLACK);
				pixel.setFont(new Font("papyrus", Font.BOLD, 40));
				pixel.drawString("20", 370, 170);
				pixel.drawString("carrots", 370, 220);
				pixel.drawString("6 ounces", 370, 560);
				pixel.drawString("bronze", 370, 610);
				pixel.drawString("2 pounds", 1060, 170);
				pixel.drawString("grapes", 1060, 220);
				pixel.drawString("1 bottle", 1060, 560);
				pixel.drawString("wine", 1060, 610);
				
				pixel.setFont(new Font("Monospaced", Font.PLAIN, 20));
				pixel.drawString("Fresh cinnamon sticks,", 370, 290);
				pixel.drawString("great for all sorts of", 370, 330);
				pixel.drawString("food and drinks.", 370, 370);
				pixel.drawString("Highly prized", 370, 680);
				pixel.drawString("cinnamon sticks and", 370, 720);
				pixel.drawString("ground cinnamon.", 370, 760);
				pixel.drawString("Fragrant cinnamon", 1060, 290);
				pixel.drawString("sticks freshly picked", 1060, 330);
				pixel.drawString("from India.", 1060, 370);				
				pixel.drawString("A tin of cinnamon", 1060, 680);
				pixel.drawString("sticks, a great", 1060, 720);
				pixel.drawString("plus to any recipe.", 1060, 760);
				
				pixel.setFont(new Font("Monospaced", Font.PLAIN, 12));
				for(int i = 0; i < 4; i++)
				{
					if(spiceStock[item-1][i] != 0)
					{
						pixel.setColor(new Color(107,142,35));
						pixel.fillRect(rect[i][0], rect[i][1], rect[i][2], rect[i][3]);
						pixel.setColor(Color.WHITE);
						pixel.drawString("ADD TO CART", rect[i][4], rect[i][5]);
						pixel.setColor(Color.BLACK);
						pixel.drawString("items left: " + spiceStock[item-1][i], rect[i][6], rect[i][7]);
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
	  		pixel.drawString("Spices", 75, 143);
	  		pixel.drawString("For Sale!", 75, 190);
	  			  		
	  		// Hot picks pics
	  		pixel.setFont(descripFont);
			pixel.drawImage(spiceItems[0][ranCol], 75, 440, 250, 250, null);
			if(ranCol == 0)
			{
				pixel.drawString("Pink himalayan salt", 340, 535);
				pixel.drawString("rich in minerals, and", 340, 565);
				pixel.drawString("brought from Pakistan", 340, 595);
			}
			else if(ranCol == 1)
			{
				pixel.drawString("Granulated Kosher salt", 340, 535);
				pixel.drawString("imported from North", 340, 565);
				pixel.drawString("Africa", 340, 595);
			}
			else if(ranCol == 2)
			{
				pixel.drawString("Flakey Kosher salt", 340, 535);
				pixel.drawString("imported from Asia", 340, 565);
			}
			else if(ranCol == 3)
			{
				pixel.drawString("Granulated Kosher", 340, 535);
				pixel.drawString("salt from Asia that", 340, 565);
				pixel.drawString("includes a wood mortar", 340, 595);
			}
			pixel.drawImage(spiceItems[1][ranCol2], 800, 205, 250, 250, null);
			if(ranCol2 == 0)
			{
				pixel.drawString("Freshly picked saffron", 1065, 280);
				pixel.drawString("straight from the", 1065, 310);
				pixel.drawString("fields of Asia", 1065, 340);

			}
			else if(ranCol2 == 1)
			{
				pixel.drawString("Genuine saffron,", 1065, 280);
				pixel.drawString("a cure-all spice", 1065, 310);
				pixel.drawString("imported from Asia", 1065, 340);
			}
			else if(ranCol2 == 2)
			{
				pixel.drawString("A teaspoon of", 1065, 280);
				pixel.drawString("great value saffron", 1065, 310);
				pixel.drawString("imported from Asia", 1065, 340);
			}
			else if(ranCol2 == 3)
			{
				pixel.drawString("A bowl of fragrant,", 1065, 280);
				pixel.drawString("rare saffron", 1065, 310);
				pixel.drawString("imported from Asia", 1065, 340);
			}
			pixel.drawImage(spiceItems[2][ranCol3], 1050, 505, 250, 250, null);
			if(ranCol3 == 0)
			{
				pixel.drawString("Fresh cinnamon sticks,", 800, 600);
				pixel.drawString("great for all sorts of", 800, 630);
				pixel.drawString("food and drinks", 800, 660);
			}
			else if(ranCol3 == 1)
			{
				pixel.drawString("Highly prized", 800, 600);
				pixel.drawString("cinnamon sticks and", 800, 630);
				pixel.drawString("ground cinnamon", 800, 660);
			}
			else if(ranCol3 == 2)
			{
				pixel.drawString("Fragrant cinnamon", 810, 600);
				pixel.drawString("sticks freshly picked", 810, 630);
				pixel.drawString("from India", 810, 660);
			}
			else if(ranCol3 == 3)
			{
				pixel.drawString("A tin of cinnamon", 810, 600);
				pixel.drawString("sticks, a great", 810, 630);
				pixel.drawString("plus to any recipe", 810, 660);
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
			
			pixel.setFont(spiceFont);
			pixel.drawString("Salt",807,102);
		    pixel.drawString("Saffron",807,162);
			pixel.drawString("Cinnamon",807,222);

			
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

			if(counter >= 26 && counter < 38)
			{
				if(line.length() == 5)
					pos = 4;
				else
					pos = 5;
				line = line.substring(pos);
				
				num = Integer.parseInt(line);
				spiceStock[row][col] = num;
				oldSpiceStock[row][col] = num;
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
			fullFile = fullFile.replace("2 " + i + " "+ oldSpiceStock[row][col], "2 " + i + " "+ spiceStock[row][col]);
			//fullFile = fullFile.replace("2 0 5", "2 0 3");

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
		Rectangle searchBar, cartRect, saltRect, saffronRect, cinnamonRect, menuRect, backRect, yesRect, noRect;
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
			saltRect = new Rectangle(795, 60, 380, 60);
			saffronRect = new Rectangle(795, 120, 380, 60);
			cinnamonRect = new Rectangle(795, 180, 380, 60);
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
			else if(saltRect.contains(x,y) && searching)
			{
				searching = false;
				item = 1;
			}
			else if(saffronRect.contains(x,y) && searching)
			{
				searching = false;
				item = 2;
			}
			else if(cinnamonRect.contains(x,y) && searching)
			{
				searching = false;
				item = 3;
			}
			else
				searching = false;	
			
			for(int i = 0; i < 4; i++)
			{
				if(add[i].contains(x, y) && (spiceStock[item-1][i] != 0))
				{
					spiceStock[item-1][i] -= 1;
					printer.println(2 + (" " + ((item-1) * 4 + i)));
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
			
			if(saltRect.contains(x,y))
			{
				hover1 = true;
				hover2 = hover3 = false;
			}
			else if(saffronRect.contains(x,y))
			{
				hover2 = true;
				hover1 = hover3 = false;
			}
			else if(cinnamonRect.contains(x,y))
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
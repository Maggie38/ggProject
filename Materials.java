package bytehacks;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.Timer;

// Silk, cotton, wool
public class Materials extends JPanel
{
	Runner run;
	Color bannerColor;
	Font bannerFont, matFont, descripFont, descripFont2;
	PrintWriter printer, printer2;
	Scanner input;
	StringBuffer buffer;
	String fullFile, newStock;
	Image sign, cart, backArrow;
	Image silk1 = new ImageIcon("silk1.jpg").getImage();
	Image silk2 = new ImageIcon("silk2.jpg").getImage();
	Image silk3 = new ImageIcon("silk3.jpg").getImage();
	Image silk4 = new ImageIcon("silk4.jpg").getImage();
	Image cotton1 = new ImageIcon("cotton1.jpg").getImage();
	Image cotton2 = new ImageIcon("cotton2.jpg").getImage();
	Image cotton3 = new ImageIcon("cotton3.jpg").getImage();
	Image cotton4 = new ImageIcon("cotton4.jpg").getImage();
	Image wool1 = new ImageIcon("wool1.jpg").getImage();
	Image wool2 = new ImageIcon("wool2.jpg").getImage();
	Image wool3 = new ImageIcon("wool3.jpg").getImage();
	Image wool4 = new ImageIcon("wool4.jpg").getImage();
	int [][] rect = {{370,420,90,20,376,435,370,410},
							{370,810,90,20,376,825,370,800},
							{1060,420,90,20,1066,435,1060,410},
							{1060,810,90,20,1066,825, 1060,800}};
	Image[][] matItems = {{silk1, silk2, silk3, silk4},
							{cotton1, cotton2, cotton3, cotton4},
							{wool1, wool2, wool3, wool4}};
	String[] matNames = {"Silk", "Cotton", "Wool"};
	int[][] oldMatStock = {{5, 5, 5, 5}, 
						{5, 5, 5, 5}, 
						{5, 5, 5, 5}};
	int[][] matStock = {{5, 5, 5, 5}, 
			{5, 5, 5, 5}, 
			{5, 5, 5, 5}};
	int[][] totalStock = new int[4][12];
	boolean searching, selected, hover1, hover2, hover3, gg, restock;
	int item, ranCol, ranCol2, ranCol3 = -1;
	Timer ggTimer;
	
	public Materials(Runner run)
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
		matFont = new Font("monospaced", Font.PLAIN, 25);
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
			pixel.setFont(matFont);
			pixel.drawImage(matItems[item-1][0], 50, 125, 300, 300, null);
			pixel.drawImage(matItems[item-1][1], 50, 510, 300, 300, null);
			pixel.drawImage(matItems[item-1][2], 730, 125, 300, 300, null);
			pixel.drawImage(matItems[item-1][3], 730, 510, 300, 300, null);
			
			// Draw searchbar name
			pixel.setColor(Color.BLACK);
			pixel.drawString(matNames[item-1], 810, 47);
			
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
				pixel.drawString("8 ounces", 370, 170);
				pixel.drawString("asbestos", 370, 220);
				pixel.drawString("1 young", 370, 560);
				pixel.drawString("hunting dog", 370, 610);
				pixel.drawString("Bronze", 1060, 170);
				pixel.drawString("mirror", 1060, 220);
				pixel.drawString("20 large", 1060, 560);
				pixel.drawString("watermelons", 1060, 610);
				
				pixel.setFont(new Font("Monospaced", Font.PLAIN, 20));
				pixel.drawString("Silk woven fabrics,", 370, 290);
				pixel.drawString("fabricated designs.", 370, 330);
				pixel.drawString("Show your wealth.", 370, 370);
				pixel.drawString("8 ounces of fresh", 370, 680);
				pixel.drawString("silk directly from", 370, 720);
				pixel.drawString("silk worms in Asia.", 370, 760);
				pixel.drawString("10 rolls of silk woven", 1060, 290);
				pixel.drawString("fabric. Great for soft", 1060, 330);
				pixel.drawString("and comfortable attire.", 1060, 370);
				pixel.drawString("Seven rolls of silk", 1060, 680);
				pixel.drawString("woven fabric. Multicolored", 1060, 720);
				pixel.drawString("and fashionable.", 1060, 760);
				
				pixel.setFont(new Font("Monospaced", Font.PLAIN, 12));
				for(int i = 0; i < 4; i++)
				{
					if(matStock[item-1][i] != 0)
					{
						pixel.setColor(new Color(107,142,35));
						pixel.fillRect(rect[i][0], rect[i][1], rect[i][2], rect[i][3]);
						pixel.setColor(Color.WHITE);
						pixel.drawString("ADD TO CART", rect[i][4], rect[i][5]);
						pixel.setColor(Color.BLACK);
						pixel.drawString("items left: " + matStock[item-1][i], rect[i][6], rect[i][7]);
					}
					else
						pixel.drawString("OUT OF STOCK", rect[i][6], rect[i][7]);
				}
			}
			else if(item == 2)
			{
				pixel.setColor(Color.BLACK);
				pixel.setFont(new Font("papyrus", Font.BOLD, 40));
				pixel.drawString("2 small", 370, 170);
				pixel.drawString("blankets", 370, 220);
				pixel.drawString("10", 370, 560);
				pixel.drawString("glass bowls", 370, 610);
				pixel.drawString("5 different", 1060, 170);
				pixel.drawString("fragrances", 1060, 220);
				pixel.drawString("8 ounces", 1060, 560);
				pixel.drawString("pepper", 1060, 610);
						
				pixel.setFont(new Font("Monospaced", Font.PLAIN, 20));
				pixel.drawString("Soft and fresh", 370, 290);
				pixel.drawString("cotton from the fields", 370, 330);
				pixel.drawString("of Asia.", 370, 370);
				pixel.drawString("One pound of", 370, 680);
				pixel.drawString("fresh cotton.", 370, 720);
				pixel.drawString("Durable and cheap.", 370, 760);
				pixel.drawString("A durable & fashionable", 1060, 290);
				pixel.drawString("cotton robe. Great for", 1060, 330);
				pixel.drawString("summer weather.", 1060, 370);
				pixel.drawString("One cotton plant.", 1060, 680);
				pixel.drawString("Grow your own", 1060, 720);
				pixel.drawString("fresh cotton.", 1060, 760);
				
				pixel.setFont(new Font("Monospaced", Font.PLAIN, 12));
				for(int i = 0; i < 4; i++)
				{
					if(matStock[item-1][i] != 0)
					{
						pixel.setColor(new Color(107,142,35));
						pixel.fillRect(rect[i][0], rect[i][1], rect[i][2], rect[i][3]);
						pixel.setColor(Color.WHITE);
						pixel.drawString("ADD TO CART", rect[i][4], rect[i][5]);
						pixel.setColor(Color.BLACK);
						pixel.drawString("items left: " + matStock[item-1][i], rect[i][6], rect[i][7]);
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
				pixel.drawString("apples", 370, 220);
				pixel.drawString("30 pieces", 370, 560);
				pixel.drawString("paper", 370, 610);
				pixel.drawString("1 young", 1060, 170);
				pixel.drawString("horse", 1060, 220);
				pixel.drawString("1 bronze", 1060, 560);
				pixel.drawString("sword", 1060, 610);
				
				pixel.setFont(new Font("Monospaced", Font.PLAIN, 20));
				pixel.drawString("1 pound of", 370, 290);
				pixel.drawString("wool. Warm and", 370, 330);
				pixel.drawString("comfortable.", 370, 370);
				pixel.drawString("5 pounds of", 370, 680);
				pixel.drawString("fresh cut", 370, 720);
				pixel.drawString("European wool.", 370, 760);
				pixel.drawString("2 pounds of", 1060, 290);
				pixel.drawString("braided white", 1060, 330);
				pixel.drawString("wool.", 1060, 370);
				pixel.drawString("1 basket of wool.", 1060, 680);
				pixel.drawString("Great for clothes", 1060, 720);
				pixel.drawString("and blankets.", 1060, 760);
				
				pixel.setFont(new Font("Monospaced", Font.PLAIN, 12));
				for(int i = 0; i < 4; i++)
				{
					if(matStock[item-1][i] != 0)
					{
						pixel.setColor(new Color(107,142,35));
						pixel.fillRect(rect[i][0], rect[i][1], rect[i][2], rect[i][3]);
						pixel.setColor(Color.WHITE);
						pixel.drawString("ADD TO CART", rect[i][4], rect[i][5]);
						pixel.setColor(Color.BLACK);
						pixel.drawString("items left: " + matStock[item-1][i], rect[i][6], rect[i][7]);
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
	  		pixel.drawString("Materials", 75, 143);
	  		pixel.drawString("For Sale!", 75, 190);
	  		
	  		// Hot picks pics
	  		pixel.setFont(descripFont);
			pixel.drawImage(matItems[0][ranCol], 75, 440, 250, 250, null);
			
			// Silk
			if(ranCol == 0)
			{
				pixel.drawString("Silk woven fabrics,", 340, 535);
				pixel.drawString("fabricated designs.", 340, 565);
				pixel.drawString("Show your wealth", 340, 595);
			}
			else if(ranCol == 1)
			{
				pixel.drawString("8 ounces of fresh", 340, 505);
				pixel.drawString("silk directly from", 340, 535);
				pixel.drawString("silk worms in Asia", 340, 565);
			}
			else if(ranCol == 2)
			{
				pixel.drawString("10 rolls of silk woven", 340, 505);
				pixel.drawString("fabric. Great for soft", 340, 535);
				pixel.drawString("and comfortable attire", 340, 565);
			}
			else if(ranCol == 3)
			{
				pixel.drawString("Seven rolls of silk", 340, 505);
				pixel.drawString("woven fabric.", 340, 535);
				pixel.drawString("Multicolored", 340, 565);
			}
			
			// Cotton
			pixel.drawImage(matItems[1][ranCol2], 800, 205, 250, 250, null);
			if(ranCol2 == 0)
			{
				pixel.drawString("Soft and fresh", 1065, 280);
				pixel.drawString("cotton from the fields", 1065, 310);
				pixel.drawString("of Asia", 1065, 340);

			}
			else if(ranCol2 == 1)
			{
				pixel.drawString("One pound of", 1065, 280);
				pixel.drawString("fresh cotton.", 1065, 310);
				pixel.drawString("Durable and cheap", 1065, 340);
			}
			else if(ranCol2 == 2)
			{
				pixel.drawString("A durable & fashionable", 1065, 280);
				pixel.drawString("cotton robe great for", 1065, 310);
				pixel.drawString("summer weather", 1065, 340);
			}
			else if(ranCol2 == 3)
			{
				pixel.drawString("One cotton plant.", 1065, 280);
				pixel.drawString("Grow your own", 1065, 310);
				pixel.drawString("fresh cotton", 1065, 340);
			}
			
			// Wool
			pixel.drawImage(matItems[2][ranCol3], 1050, 505, 250, 250, null);
			if(ranCol3 == 0)
			{
				pixel.drawString("1 pound of", 800, 600);
				pixel.drawString("wool. Warm and", 800, 630);
				pixel.drawString("comfortable", 800, 660);
			}
			else if(ranCol3 == 1)
			{
				pixel.drawString("5 pounds of", 800, 600);
				pixel.drawString("fresh cut", 800, 630);
				pixel.drawString("European wool", 800, 660);
			}
			else if(ranCol3 == 2)
			{
				pixel.drawString("2 pounds of", 800, 600);
				pixel.drawString("braided white", 800, 630);
				pixel.drawString("wool", 800, 660);
			}
			else if(ranCol3 == 3)
			{
				pixel.drawString("1 basket of wool.", 800, 600);
				pixel.drawString("Great for clothes", 800, 630);
				pixel.drawString("and blankets", 800, 660);
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
			
			pixel.setFont(matFont);
			pixel.drawString("Silk",807,102);
		    pixel.drawString("Cotton",807,162);
			pixel.drawString("Wool",807,222);

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

			if(counter < 12)
			{
				if(line.length() == 5)
					pos = 4;
				else
					pos = 5;
				line = line.substring(pos);
				
				num = Integer.parseInt(line);
				matStock[row][col] = num;
				oldMatStock[row][col] = num;
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
			fullFile = fullFile.replace("0 " + i + " "+ oldMatStock[row][col], "0 " + i + " "+ matStock[row][col]);
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
				if(add[i].contains(x, y) && (matStock[item-1][i] != 0))
				{
					matStock[item-1][i] -= 1;
					printer.println(0 + (" " + ((item-1) * 4 + i)));
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
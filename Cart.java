package bytehacks;

import java.awt.*; 
import java.awt.event.*; 
import javax.swing.*;

import java.io.*;
import java.util.Scanner; 

public class Cart extends JPanel{
	Runner run;
	Panel pan;
	Boolean proHov, spiHov, matsHov, minsHov;
	int items;
	int page;
	int pageNum;
	Scanner sc;
	String fullFile;
	int[]boughtItems;
	int[][]amtBought;
	int scrollY;
	int scrollSize;
	Rectangle buy;
	int sec;
	Timer time;
	BuyClass bc;
	boolean show;
	PrintWriter printer;
	PrintWriter printer2;
	boolean hasStuff;
	int pages2;	
	int pageNum2;
	Scanner sc2;
	
	public Cart(Runner run) {
		this.run = run;
	
		show=false;
		
		hasStuff=false;
				
		bc = new BuyClass();
		time = new Timer(200, bc);
		sec=-3;
		
		buy=new Rectangle(932, 653, 411, 80);
		
		setVisible(true);
		pan = new Panel();
		setBackground(Color.WHITE);
		proHov = false;
		spiHov = false;
		matsHov = false;
		minsHov = false;
		items = 0;
		
		amtBought=new int[4][12];
		tryCatchIt();
		getCart();
		
		String[]temp=fullFile.split(" ");
		
		for(int i = 0; i < temp.length-1; i+=2)
		{
			boolean good = true;
			for(int j = 0; j < i; j++)
			{
				int r = Integer.parseInt(temp[i]);
				int c = Integer.parseInt(temp[i+1]);
				if(amtBought[r][c]>0)
					good=false;
			}
			if(good)
			{
				items++;
			}
			amtBought[Integer.parseInt(temp[i])][Integer.parseInt(temp[i+1])]++;
		}
		page = (int)(items/3);
		if(items % 3 == 0 && items != 0)
			page -= 1;
		pageNum = 0;
		boughtItems=new int[items];
		int index=0;
		for(int row = 0; row < 4; row++)
			for(int col = 0; col<12; col++)
				if(amtBought[row][col]>0)
				{
					boughtItems[index]=(row*12)+(col);
					index++;
				}
		if(items>0)
			hasStuff=true;
		
		pages2 = (int)(items/7);
		if(items % 7 == 0 && items != 0)
			pages2--;
		pageNum2=0;
	}
	
	class BuyClass implements ActionListener 
	{
		public void actionPerformed(ActionEvent e) 
		{
			sec++;
			repaint();
			grabFocus();
			if(sec==15)
			{
				time.stop();
				run.switchCards("menu");
			}
		}
	} 
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
				
		if(items==0)
		{
			g.setFont(new Font("Monospaced", Font.PLAIN, 26));
			g.drawString("No items are currently in your cart", 100, 300);
		}
		
		g.setColor(new Color(107,142,35));
		g.fillRect(0, 0, 1437, 75);
		
		g.setColor(Color.WHITE);
		g.setFont(new Font("Papyrus", Font.BOLD, 40));
		g.drawString("Silky Milky Trading Co.", 30, 50);
		
		g.setColor(Color.GRAY);
		g.drawLine(0, 130, 1437, 130);
		
		g.setFont(new Font("Monospaced", Font.BOLD, 20));
		g.drawString("Materials", 30, 112);
		g.drawString("Minerals", 220, 112);
		g.drawString("Spices", 400, 112);
		g.drawString("Produce", 570, 112);
		
		g.setColor(new Color(107,142,35,60));
		if(matsHov)
			g.fillRect(0, 75, 175, 55);
		if(minsHov)
			g.fillRect(175, 75, 180, 55);
		if(spiHov)
			g.fillRect(355, 75, 170, 55);
		if(proHov)
			g.fillRect(525, 75, 175, 55);
		
		g.setColor(Color.BLACK);
		g.setFont(new Font("Monospaced", Font.BOLD, 35));
		g.drawString("Your Silky Milky Cart", 470, 175);
		
		Image silk1 = new ImageIcon("Silk1.jpg").getImage();
		Image silk2 = new ImageIcon("Silk2.jpg").getImage();
		Image silk3 = new ImageIcon("Silk3.jpg").getImage();
		Image silk4 = new ImageIcon("Silk4.jpg").getImage();
		Image cotton1 = new ImageIcon("Cotton1.jpg").getImage();
		Image cotton2 = new ImageIcon("Cotton2.jpg").getImage();
		Image cotton3 = new ImageIcon("Cotton3.jpg").getImage();
		Image cotton4 = new ImageIcon("Cotton4.jpg").getImage();
		Image wool1 = new ImageIcon("Wool1.jpg").getImage();
		Image wool2 = new ImageIcon("Wool2.jpg").getImage();
		Image wool3 = new ImageIcon("Wool3.jpg").getImage();
		Image wool4 = new ImageIcon("Wool4.jpg").getImage();
		
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
		
		Image salt1 = new ImageIcon("salt1.jpg").getImage();
		Image salt2 = new ImageIcon("salt2.jpg").getImage();
		Image salt3 = new ImageIcon("salt3.jpg").getImage();
		Image salt4 = new ImageIcon("salt4.jpg").getImage();
		Image saffron1 = new ImageIcon("saffron1.jpg").getImage();
		Image saffron2 = new ImageIcon("saffron2.jpg").getImage();
		Image saffron3 = new ImageIcon("saffron3.jpg").getImage();
		Image saffron4 = new ImageIcon("saffron4.jpg").getImage();
		Image cinnamon1 = new ImageIcon("cinnamon1.jpg").getImage();
		Image cinnamon2 = new ImageIcon("cinnamon2.jpg").getImage();
		Image cinnamon3 = new ImageIcon("cinnamon3.jpg").getImage();
		Image cinnamon4 = new ImageIcon("cinnamon4.jpg").getImage();
		
		Image rice1 = new ImageIcon("Rice1.jpg").getImage();
		Image rice2 = new ImageIcon("Rice2.jpg").getImage();
		Image rice3 = new ImageIcon("Rice3.jpg").getImage();
		Image rice4 = new ImageIcon("Rice4.jpg").getImage();
		Image pomegranate1 = new ImageIcon("Pomegranate1.jpg").getImage();
		Image pomegranate2 = new ImageIcon("Promagranate2.jpg").getImage();
		Image pomegranate3 = new ImageIcon("Pomegranate3.jpg").getImage();
		Image pomegranate4 = new ImageIcon("Pomegranate4.jpg").getImage();
		Image olive1 = new ImageIcon("Olive1.jpg").getImage();
		Image olive2 = new ImageIcon("Olive2.jpg").getImage();
		Image olive3 = new ImageIcon("Olive3.jpg").getImage();
		Image olive4 = new ImageIcon("Olive4.jpg").getImage();
		
		Image [] allImages = {silk1, silk2, silk3, silk4, cotton1, cotton2, cotton3, cotton4,
				wool1, wool2, wool3, wool4, jade1, jade2, jade3, jade4, silver1, silver2, 
				silver3, silver4, iron1, iron2, iron3, iron4, salt1, salt2, salt3, salt4,
				saffron1, saffron2, saffron3, saffron4, cinnamon1, cinnamon2, cinnamon3,
				cinnamon4, rice1, rice2, rice3, rice4, pomegranate1, pomegranate2, 
				pomegranate3, pomegranate4, olive1, olive2, olive3, olive4};
		
		String silk1S = "8 ounces asbestos";
		String silk2S = "1 young hunting dog";
		String silk3S = "Bronze mirror";
		String silk4S = "20 large watermelons";
		String cotton1S = "2 small blankets";
		String cotton2S = "10 glass bowls";
		String cotton3S = "5 different fragrances";
		String cotton4S = "8 ounces pepper";
		String wool1S = "20 apples";
		String wool2S = "30 pieces paper";
		String wool3S = "1 young horse";
		String wool4S = "1 bronze mirror";
		
		String jade1S = "3 kilograms ginger spices";
		String jade2S = "1 cartload wool";
		String jade3S = "1 piece European artwork";
		String jade4S = "2 pieces gold";
		String silver1S = "5 rolls multicolored silk";
		String silver2S = "2 cartload white rice";
		String silver3S = "3 nuggets 24 karat gold";
		String silver4S = "1/2 pound saffron";
		String iron1S = "2 pounds pomegranate";
		String iron2S = "8 ounces olive oil";
		String iron3S = "2 pounds ivory";
		String iron4S = "12 ounces ground cumin";
				
		String salt1S = "1 pound teas leaves";
		String salt2S = "5 cups sugar";
		String salt3S = "6 ounces ivory";
		String salt4S = "1 box cotton";
		String saffron1S = "3 pounds figs";
		String saffron2S = "5 pounds porcelain";
		String saffron3S = "5 pounds silver";
		String saffron4S = "10 pieces gold";
		String cinnamon1S = "20 carrots";
		String cinnamon2S = "6 ounces bronze";
		String cinnamon3S = "2 pounds grapes";
		String cinnamon4S = "1 bottle wine";
		
		String rice1S = "8 ounces salt";
		String rice2S = "1 piece wool fabric";
		String rice3S = "2 ounce cinnamon";
		String rice4S = "1 porcelain figurine";
		String pomegranate1S = "1 bowl brown rice";
		String pomegranate2S = "8 ounces pepper";
		String pomegranate3S = "1 bronze coin";
		String pomegranate4S = "2 sticks cinnamon";
		String olive1S = "2 carrots";
		String olive2S = "1/4 pound salt";
		String olive3S = "1 ounce saffron";
		String olive4S = "1 piece silk fabric";
		
		String [] allNames = {"Silk", "Silk", "Silk", "Silk", "Cotton", "Cotton", "Cotton", "Cotton",
				"Wool", "Wool","Wool", "Wool", "Jade", "Jade", "Jade", "Jade", "Silver", "Silver",
				"Silver", "Silver", "Iron", "Iron", "Iron", "Iron", "Salt", "Salt", "Salt", "Salt",
				"Saffron", "Saffron", "Saffron", "Saffron", "Cinnamon", "Cinnamon", "Cinnamon",
				"Cinnamon", "Rice", "Rice", "Rice", "Rice", "Pomegranate", "Pomegranate", 
				"Pomegranate", "Pomegranate", "Olive", "Olive", "Olive", "Olive"};
		
		String [] allPrices = {silk1S, silk2S, silk3S, silk4S, cotton1S, cotton2S, cotton3S, cotton4S,
				wool1S, wool2S, wool3S, wool4S, jade1S, jade2S, jade3S, jade4S, silver1S, silver2S, 
				silver3S, silver4S, iron1S, iron2S, iron3S, iron4S, salt1S, salt2S, salt3S, salt4S,
				saffron1S, saffron2S, saffron3S, saffron4S, cinnamon1S, cinnamon2S, cinnamon3S,
				cinnamon4S, rice1S, rice2S, rice3S, rice4S, pomegranate1S, pomegranate2S, 
				pomegranate3S, pomegranate4S, olive1S, olive2S, olive3S, olive4S};
		
		Image left = new ImageIcon("ArrowLeft.png").getImage();
		Image right = new ImageIcon("ArrowRight.png").getImage();
		
		if(page > 0)
		{
			g.drawImage(left, 60, 135, 40, 55, null);
			g.drawImage(right, 1310, 135, 40, 55, null);
		}
		
		if(pages2 > 0)
		{
			g.setColor(Color.BLACK);
			((Graphics2D)g).setStroke(new BasicStroke(3));
			g.drawLine(955, 245, 982, 265);
			g.drawLine(955, 245, 982, 225);
			
			g.drawLine(1275, 267, 1302, 247);
			g.drawLine(1275, 227, 1302, 247);
		}
		
		if(items>0)
		{
			g.setColor(new Color(204,0,0));
			g.fillRect(620,770,260,50);
			g.setColor(Color.WHITE);
			g.setFont(new Font("Papyrus", Font.BOLD, 35));
			g.drawString("CLEAR CART", 637, 805);
			g.setColor(Color.BLACK);
		}
		
		((Graphics2D)g).setStroke(new BasicStroke(1));
		for(int i = 0; i <= page; i++)
		{
			if(i==pageNum)
			{
				for(int j = 0; j < 3; j++)
				{
					if(items>i*3+j)
					{
						int index = boughtItems[i*3+j];
						int r = index/12;
						int c = index%12;
						int amount = amtBought[r][c];
						g.setFont(new Font("Monospaced", Font.PLAIN, 16));
						g.drawString("amount:"+amount, 210, 340+185*j);
						g.setFont(new Font("Papyrus", Font.BOLD, 30));
						g.drawString(allNames[index], 210, 260+185*j);
						g.setFont(new Font("Papyrus", Font.BOLD, 24));
						g.drawString(allPrices[index], 210, 300+185*j);
						g.drawImage(allImages[index], 50, 225+185*j, 130, 130, null);
					}
				}
			}
		}
		
		for(int i = 0; i <= pages2; i++)
		{
			if(i==pageNum2)
			{
				for(int j = 0; j < 7; j++)
				{
					if(items>i*7+j)
					{
						int index = boughtItems[i*7+j];
						int r = index/12;
						int c = index%12;
						int amount = amtBought[r][c];
						g.setFont(new Font("Papyrus", Font.BOLD, 24));
						g.drawString(" ~ "+allPrices[index], 910, 315+50*j);
						if(amount>1)
							g.drawString("x"+amount, 1310, 315+50*j);
					}
				}
			}
		}
	
		g.setColor(Color.GRAY);
		g.drawLine(30, 197, 875, 197);
		g.drawLine(30, 382, 875, 382);
		g.drawLine(30, 567, 875, 567);
		g.drawLine(30, 753, 875, 753);
		g.drawLine(900, 633, 1375, 633);
		g.drawRect(900, 197, 475, 556);
		
		g.setColor(Color.BLACK);
		g.setFont(new Font("Papyrus", Font.BOLD, 50));
		g.drawString("TOTAL:", 1040, 260);
		
		g.setFont(new Font("Papyrus", Font.BOLD, 30));
		
		if(hasStuff)
			g.setColor(new Color(107,142,35));
		
		else
			g.setColor(Color.LIGHT_GRAY);
		
		g.fillRect(932, 653, 411, 80);

		g.setFont(new Font("Papyrus", Font.BOLD, 65));
		g.setColor(Color.WHITE);
		g.drawString("Purchase", 993, 712);
		
		if(hasStuff)
			g.setColor(new Color(107,142,35));
		
		else
			g.setColor(Color.LIGHT_GRAY);		
		g.fillRect(1133, 653, 10, 12);
		
		if(show)
		{
			g.setColor(Color.WHITE);
			g.fillRect(0, 0, 1500, 900);
			int transparency=0;
			if(sec>=0)
				transparency = 255-sec*17;
			else 
				transparency = 255;
			g.setColor(new Color(0, 0, 0, transparency));
			g.drawString("You have successfully", 405, 410);
			g.drawString("completed your purchase.", 356, 500);
		}
	}
	public void tryCatchIt2()
	{
		File outputFile= new File("shoppingCart.txt");
		try
		{
			if(!outputFile.exists())
				outputFile.createNewFile();
			printer = new PrintWriter(new FileWriter(outputFile, false));
		}
		catch(IOException e)
		{
			System.err.print("File IO Error");
		}
	}
	
	public void tryCatchIt3()
	{
		File outputFile = new File("totalStock.txt");
		try
		{
			if (!outputFile.exists())
				outputFile.createNewFile();
			
			printer2 = new PrintWriter(new FileWriter(outputFile, false));
		}
		catch(IOException e)
		{
			System.err.print("File IO Error");
		}
	}
	
	public void tryCatchIt()
	{
		File inputFile = new File("shoppingCart.txt");
		try
		{
			if (!inputFile.exists())
				inputFile.createNewFile();
			
			sc = new Scanner(inputFile);
		}
		catch(IOException e)
		{
			System.err.print("File IO Error");
		}
	}
	
	public void tryCatchIt4()
	{
		File inputFile = new File("totalStock.txt");
		try
		{
			if (!inputFile.exists())
				inputFile.createNewFile();
			
			sc2 = new Scanner(inputFile);
		}
		catch(IOException e)
		{
			System.err.print("File IO Error");
		}
	}
	
	public void getCart()
	{
		StringBuffer buffer = new StringBuffer();
		
		while(sc.hasNext())
		{
			String line = sc.nextLine();
			buffer.append(line+" ");
		}
		//System.out.println();
		fullFile = buffer.toString();
		sc.close();
		tryCatchIt();
	}
	
	public void remakeStock()
	{
		tryCatchIt4();
		StringBuffer buff = new StringBuffer();
		String wholeThing="";
		int[][]temp = new int[4][12];
		int row=0;
		int col=0;
		
		while(sc2.hasNext())
		{
			String line = sc2.nextLine();
			buff.append(line+"\n");
			
			if(line.length()>=4)
			{
				line=line.substring(line.lastIndexOf(" ")+1);
				temp[row][col]=Integer.parseInt(line);
				col++;
				if(col==12)
				{
					col=0;
					row++;
				}
			}
		}
		wholeThing = buff.toString();
		tryCatchIt3();
		for(int r = 0; r < 4; r++)
		{
			for(int c = 0; c < 12; c++)
			{
				if(amtBought[r][c]>0)
				{
					String old = r+" "+c+" "+temp[r][c];
					String newS = r+" "+c+" "+(amtBought[r][c]+temp[r][c]);
					wholeThing = wholeThing.replace(old,newS);
				}
			}
		}
		printer2.print(wholeThing);
		
		printer2.close();
		sc2.close();
	}
	
	class Panel implements MouseListener, MouseMotionListener {
		Rectangle produce, materials, minerals, spices, silky, rightR, leftR, right2, left2, clear;
		
		public Panel() {
			addMouseListener(this);
			addMouseMotionListener(this);
			silky = new Rectangle(30, 10, 480, 55);
			materials = new Rectangle(0, 75, 175, 55);
			minerals = new Rectangle(175, 75, 180, 55);
			spices = new Rectangle(355, 75, 170, 55);
			produce = new Rectangle(525, 75, 175, 55);
			leftR = new Rectangle(50, 135, 60, 55);
			rightR = new Rectangle(1300, 135, 60, 55);
			right2 = new Rectangle(945, 215, 50, 60);
			left2 = new Rectangle(1263, 217, 50, 60);
			clear = new Rectangle(620,770,260,50);
		}
		
		public void mousePressed(MouseEvent e) {
			requestFocus();
			
			if(pages2 > 0)
			{
				if (left2.contains(e.getX(), e.getY()))
				{
					pageNum2--;
					if(pageNum2 < 0)
						pageNum2 = pages2;
				}
				
				if (right2.contains(e.getX(), e.getY()))
				{
					pageNum2++;
					if(pageNum2 > pages2)
						pageNum2 = 0;
				}
			}
			
			if(items>0)
			{
				if(clear.contains(e.getX(),e.getY()))
				{
					tryCatchIt2();
					printer.print("");
					remakeStock();
					items=0;
				}
			}
			
			if (silky.contains(e.getX(), e.getY()))
				run.switchCards("menu");
			
			if (materials.contains(e.getX(), e.getY()))
				run.switchCards("materials");

			if (minerals.contains(e.getX(), e.getY()))
				run.switchCards("minerals");

			if (spices.contains(e.getX(), e.getY()))
				run.switchCards("spices");

			if (produce.contains(e.getX(), e.getY()))
				run.switchCards("produce");
			
			if(page > 0)
			{
				if (leftR.contains(e.getX(), e.getY()))
				{
					pageNum--;
					if(pageNum < 0)
						pageNum = page;
				}
				
				if (rightR.contains(e.getX(), e.getY()))
				{
					pageNum++;
					if(pageNum > page)
						pageNum = 0;
				}
			}
			
			if(buy.contains(e.getX(), e.getY()) && hasStuff)
			{
				tryCatchIt2();
				printer.print("");
				new Cart(run);
				sec=-3;
				show = true;
				time.start();
			}
			
			repaint();
		}
		public void mouseClicked(MouseEvent e) {}
		public void mouseReleased(MouseEvent e) {} 
		public void mouseEntered(MouseEvent e) {} 
		public void mouseExited(MouseEvent e) {}

		public void mouseMoved(MouseEvent e) {
			requestFocus();
			
			if (materials.contains(e.getX(), e.getY()))
				matsHov = true;
			else 
				matsHov = false;
	
			if (minerals.contains(e.getX(), e.getY()))
				minsHov = true;
			else 
				minsHov = false;
			
			if (spices.contains(e.getX(), e.getY()))
				spiHov = true;
			else 
				spiHov = false;
	
			if (produce.contains(e.getX(), e.getY()))
				proHov = true;
			else 
				proHov = false;
			
			repaint();
		}
		public void mouseDragged(MouseEvent e) {}
	}
}
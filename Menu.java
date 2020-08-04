package bytehacks;

import java.awt.*; 
import java.awt.event.*; 
import javax.swing.*; 
import java.io.*; 

public class Menu extends JPanel{
	Runner run;
	Panel pan;
	Boolean proHov, spiHov, matsHov, minsHov;
	int count;	
	int [] rand;

	public Menu(Runner run) {
		this.run = run;
		setVisible(true);
		pan = new Panel();
		setBackground(Color.WHITE);
		proHov = false;
		spiHov = false;
		matsHov = false;
		minsHov = false;
		count = 0;
		rand = new int [4];
		rand[0] = (int)(Math.random()*3+0);
		rand[1] = (int)(Math.random()*3+0);
		rand[2] = (int)(Math.random()*3+0);
		rand[3] = (int)(Math.random()*3+0);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.setColor(new Color(107,142,35));
		g.fillRect(0, 0, 1437, 75);
		
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(0, 130, 1437, 1000);
		
		Image cart = new ImageIcon("ShoppingCart.png").getImage();
		g.drawImage(cart, 1325, 0, 75, 69, null);

		Image map = new ImageIcon("SilkMap.jpg").getImage();
		Image farmer = new ImageIcon("HappyFarmer2.jpg").getImage();
		Image produce = new ImageIcon("Fruit&Veggies.jpg").getImage();
		Image silk = new ImageIcon("Silk.jpg").getImage();
		Image spices = new ImageIcon("Spices.jpg").getImage();
		
		if(count == 0)
			g.drawImage(map, 0, 130, 1437, 400, null);

		if(count == 1)
			g.drawImage(farmer, 0, 130, 1437, 400, null);
		
		if(count == 2)
			g.drawImage(produce, 0, 130, 1437, 400, null);
		
		if(count == 3)
			g.drawImage(silk, 0, 130, 1437, 400, null);
		
		if(count == 4)
			g.drawImage(spices, 0, 130, 1437, 400, null);
		
		Image left = new ImageIcon("arrow_left.png").getImage();
		g.drawImage(left, 60, 250, 100, 125, null);
		
		Image right = new ImageIcon("arrow_right.png").getImage();
		g.drawImage(right, 1280, 250, 100, 125, null);
		
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
		
		g.setColor(new Color(107,142,35));
		g.fillRect(90, 471, 275, 300);
		g.fillRect(425, 471, 275, 300);
		g.fillRect(760, 471, 275, 300);
		g.fillRect(1095, 471, 275, 300);
	
		/*g.setColor(new Color(107,142,35));
		g.fillRect(60, 441, 295, 320);
		g.fillRect(395, 441, 295, 320);
		g.fillRect(730, 441, 295, 320);
		g.fillRect(1065, 441, 295, 320);*/
		
		g.setColor(Color.WHITE);
		g.fillRect(70, 450, 275, 300);
		g.fillRect(405, 450, 275, 300);
		g.fillRect(740, 450, 275, 300);
		g.fillRect(1075, 450, 275, 300);

		g.setFont(new Font("Monospaced", Font.BOLD, 18));
		g.setColor(Color.BLACK);
		g.drawString("Natural, tasty foods", 90, 480);
		g.drawString("Best-selling jewels", 425, 480);
		g.drawString("Spices to spice up", 770, 475);
		g.drawString("your life", 770, 495);
		g.drawString("Hot new cozy fabrics", 1095, 480);
		
		Image cinnamon = new ImageIcon("cinnamon1.jpg").getImage();
		Image saffron = new ImageIcon("saffron1.jpg").getImage();
		Image salt = new ImageIcon("salt1.jpg").getImage();
		Image cotton = new ImageIcon("Cotton3.jpg").getImage();
		Image silk2 = new ImageIcon("Silk3.jpg").getImage();
		Image wool = new ImageIcon("Wool1.jpg").getImage();
		Image jade = new ImageIcon("jade1.jpg").getImage();
		Image silver = new ImageIcon("silver1.jpeg").getImage();
		Image iron = new ImageIcon("Iron4.jpg").getImage();
		Image rice = new ImageIcon("Rice3.jpg").getImage();
		Image pomegranate = new ImageIcon("Pomegranate1.jpg").getImage();
		Image olive = new ImageIcon("Olive4.jpg").getImage();
		
		Image [] spiceA = {cinnamon, saffron, salt};
		Image [] materialA = {cotton, silk2, wool};
		Image [] mineralA = {jade, iron, silver};
		Image [] produceA = {rice, pomegranate, olive};
		
		requestFocus();
		
		g.drawImage(produceA[rand[0]], 92, 498, 230, 230, null);
		
		g.drawImage(mineralA[rand[1]], 427, 498, 230, 230, null);
		
		g.drawImage(spiceA[rand[2]], 762, 510, 230, 220, null);
		
		g.drawImage(materialA[rand[3]], 1097, 498, 230, 230, null);
	}
	
	class Panel implements MouseListener, MouseMotionListener {
		Rectangle produce, materials, minerals, spices, cartR, left, right;
		
		public Panel() {
			addMouseListener(this);
			addMouseMotionListener(this);
			materials = new Rectangle(0, 75, 175, 55);
			minerals = new Rectangle(175, 75, 180, 55);
			spices = new Rectangle(355, 75, 170, 55);
			produce = new Rectangle(525, 75, 175, 55);
			cartR = new Rectangle(1330, 7, 72, 56);
			left = new Rectangle(60, 250, 100, 125);
			right = new Rectangle(1280, 250, 100, 125);
		}
		
		public void mousePressed(MouseEvent e) {
			requestFocus();
			
			if (materials.contains(e.getX(), e.getY()))
				run.switchCards("materials");

			if (minerals.contains(e.getX(), e.getY()))
				run.switchCards("minerals");

			if (spices.contains(e.getX(), e.getY()))
				run.switchCards("spices");

			if (produce.contains(e.getX(), e.getY()))
				run.switchCards("produce");
			
			if (cartR.contains(e.getX(), e.getY()))
				run.switchCards("cart");
			
			requestFocus();
			
			if (left.contains(e.getX(), e.getY()))
			{
				count--;
				if(count < 0)
					count = 4;
			}
				
			if (right.contains(e.getX(), e.getY()))
			{
				count++;
				if(count > 4)
					count = 0;
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
		public void mouseDragged(MouseEvent e) {
		}
	}
}
//package bytehacks;

import java.awt.*;
import javax.swing.*;

public class Runner
{
	JFrame frame;
	CardLayout cl;
	Menu menu;
	Minerals minerals;
	Spices spices;
	Produce produce;
	Materials materials;
	Cart cart;
	
	public static void main (String[]arg)            
	{												
		Runner asdf = new Runner();
		asdf.method();
	}
	public void method()
	{
		frame = new JFrame("Silk Road");
		
		cl = new CardLayout();
		frame.setLayout(cl);
		
		frame.setSize(1437, 875);
		
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
    	frame.setBackground(Color.gray);
    	
    	menu=new Menu(this);
		
		frame.add(menu,"menu");
		frame.setVisible(true);
	}

	
	public void switchCards(String str)
	{
		if(str.equals("menu"))
		{
			menu=new Menu(this);
			frame.add(menu,"menu");
			cl.show(frame.getContentPane(), "menu");
		}
		if(str.equals("spices"))
		{
			spices=new Spices(this);
			frame.add(spices,"spices");
			cl.show(frame.getContentPane(), "spices");
		}
		if(str.equals("minerals"))
		{
			minerals=new Minerals(this);
			frame.add(minerals,"minerals");
			cl.show(frame.getContentPane(), "minerals");
		}
		if(str.equals("produce"))
		{
			produce=new Produce(this);
			frame.add(produce,"produce");
			cl.show(frame.getContentPane(), "produce");
		}
		if(str.equals("materials"))
		{
			materials=new Materials(this);
			frame.add(materials, "materials");
			cl.show(frame.getContentPane(), "materials");
		}
		if(str.equals("cart"))
		{
			cart=new Cart(this);
			frame.add(cart, "cart");
			cl.show(frame.getContentPane(), "cart");
		}
	}	
}

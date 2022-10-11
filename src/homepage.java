import javax.swing.*;
import java.awt.*; 
import java.awt.event.*;

public class homepage
{	
	public static void main(String args[])  
	    {  
		    JFrame f;
		    JLabel app_name;
		    JButton b1;
		    JButton b2;
		      
		    f=new JFrame("HOME PAGE");
		    app_name=new JLabel("MEGA BITE");
		    app_name.setFont(new Font("Verdana", Font.PLAIN, 18));
		    app_name.setBounds(180,50,150,100); 
		    
		    b1=new JButton("REGISTER");
		    b1.setBounds(120,150,100,40);
		    b1.addActionListener(new ActionListener() {
		    	public void actionPerformed(ActionEvent e) {
		    		new Cust_Frame();
		    		f.dispose();
		    	}
		    });
		    
		    b2=new JButton("LOGIN");
		    b2.setBounds(250,150,100,40);
		    b2.addActionListener(new ActionListener() {
		    	public void actionPerformed(ActionEvent e) {
		    		new login();
		    		f.dispose();
		    	}
		    });
		    	
		    f.add(app_name);
		    f.add(b1);
		    f.add(b2);
		    f.setSize(500,400);  
		    f.setLayout(null);  
		    f.setVisible(true);  
	    }  
}

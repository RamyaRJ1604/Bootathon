import javax.swing.*;
import java.awt.*; 
import java.awt.event.*;

public class homepage
{	
	public static void main(String args[])  
	    {  
		    JFrame frame;
		    JLabel app_name;
		    JButton register;
		    JButton login;
		      
		    frame=new JFrame("HOME PAGE");
		    app_name=new JLabel("MEGA BITE");
		    app_name.setFont(new Font("Verdana", Font.PLAIN, 18));
		    app_name.setBounds(180,50,150,100); 
		    
		    register=new JButton("REGISTER");
		    register.setBounds(120,150,100,40);
		    register.addActionListener(new ActionListener() {
		    	public void actionPerformed(ActionEvent e) {
		    		new Cust_Frame();
		    		frame.dispose();
		    	}
		    });
		    
		    login=new JButton("LOGIN");
		    login.setBounds(250,150,100,40);
		    login.addActionListener(new ActionListener() {
		    	public void actionPerformed(ActionEvent e) {
		    		new login();
		    		frame.dispose();
		    	}
		    });
		    	
		    frame.add(app_name);
		    frame.add(register);
		    frame.add(login);
		    frame.setSize(500,400);  
		    frame.setLayout(null);  
		    frame.setVisible(true);  
	    }  
}

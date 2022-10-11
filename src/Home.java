import javax.swing.*;
import java.awt.*; 
import java.awt.event.*;

public class Home{	
	public static void main(String args[]){  
		    JFrame frame;
		    JLabel appName;
		    JButton register;
		    JButton login;
		      
		    frame=new JFrame("HOME PAGE");
		    appName=new JLabel("MEGA BITE");
		    appName.setFont(new Font("Verdana", Font.PLAIN, 18));
		    appName.setBounds(180,50,150,100); 
		    
		    register=new JButton("REGISTER");
		    register.setBounds(120,150,100,40);
		    register.addActionListener(new ActionListener() {
		    	public void actionPerformed(ActionEvent e) {
		    		new Registration();
		    		frame.dispose();
		    	}
		    });
		    
		    login=new JButton("LOGIN");
		    login.setBounds(250,150,100,40);
		    login.addActionListener(new ActionListener() {
		    	public void actionPerformed(ActionEvent e) {
		    		new Login();
		    		frame.dispose();
		    	}
		    });
		    	
		    frame.add(appName);
		    frame.add(register);
		    frame.add(login);
		    frame.setSize(500,400);  
		    frame.setLayout(null);  
		    frame.setVisible(true);  
	    }  
}

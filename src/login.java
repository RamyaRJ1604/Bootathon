package bootathon;
import javax.swing.*;
public class login
{
	public static void main(String args[])  
    {  
		
	    JFrame f;
	    JLabel heading;
	    JLabel username;
	    JTextField t1;
	    JLabel password; 
	    JPasswordField pass;
	    JRadioButton r1;
	    JRadioButton r2;   
	    ButtonGroup rb;
	    JButton b;
	      
	    f=new JFrame("LOGIN PAGE");
	    heading=new JLabel("LOGIN PAGE");
	    heading.setBounds(200,50,100,30);
	    username=new JLabel("Username");  
	    username.setBounds(100,100, 100,30);
	    t1=new JTextField(" Enter Username ");  
	    t1.setBounds(175,100, 200,30); 
	    password=new JLabel("Password");  
	    password.setBounds(100,150, 100,30);
	    pass=new JPasswordField();  
	    pass.setBounds(175,150, 200,30);
	    r1=new JRadioButton("Customer"); 
	    r1.setBounds(120,200,100,30);    
	    r2=new JRadioButton("Restaurant Staff"); 	
	    r2.setBounds(230,200,200,30); 
	    rb=new ButtonGroup();    
	    b=new JButton("LOGIN");
	    b.setBounds(190,250,100,30);
	    
	    f.add(heading);
	    f.add(username);
	    f.add(t1);   
	    f.add(password); 
	    f.add(pass);
	    f.add(r1);
	    f.add(r2);
	    rb.add(r1);
	    rb.add(r2);
	    f.add(b);
	    f.setSize(500,500);  
	    f.setLayout(null);  
	    f.setVisible(true);  
    }  
}
  

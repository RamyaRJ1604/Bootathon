import javax.swing.*;
import java.util.regex.*;
import java.awt.event.*;
import java.sql.*;
public class login{
	login(){
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
	    t1=new JTextField();  
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
	    b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				if(!Pattern.matches("^[a-zA-Z_]{8,20}$", t1.getText())) {
					JOptionPane.showMessageDialog(f, "Invalid Username!");
					username.setText("");
					username.requestFocus();
				}
				else if(!Pattern.matches("^(?=.*[0-9])"+ "(?=.*[a-z])(?=.*[A-Z])"+ "(?=.*[@#$%^&+=])"+ "(?=\\S+$).{8,20}$", String.valueOf(pass.getPassword()))) {
					JOptionPane.showMessageDialog(f, "Invalid Password!");
					pass.setText("");
					pass.requestFocus();
				}
				else if(r1.isSelected()==false && r2.isSelected()==false) {
					JOptionPane.showMessageDialog(f, "Select an option!");
				}
				else {
					JOptionPane.showMessageDialog(f, "Login Successful!");
				}
			}
		}); 
	    
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
	public static void main(String args[]){}  
}
  

import javax.swing.*;
import java.util.regex.*;
import java.awt.event.*;
import java.sql.*;

public class login{
	login(){
		JFrame frame;
	    JLabel heading;
	    JLabel username;
	    JTextField usernameInput;
	    JLabel password; 
	    JPasswordField passwordInput;
	    JRadioButton customerRadio;
	    JRadioButton staffRadio;   
	    ButtonGroup buttonGroup;
	    JButton login;
	      
	    frame=new JFrame("LOGIN PAGE");
	    heading=new JLabel("LOGIN PAGE");
	    heading.setBounds(200,50,100,30);
	    username=new JLabel("Username");  
	    username.setBounds(100,100, 100,30);
	    usernameInput=new JTextField();  
	    usernameInput.setBounds(175,100, 200,30); 
	    password=new JLabel("Password");  
	    password.setBounds(100,150, 100,30);
	    passwordInput=new JPasswordField();  
	    passwordInput.setBounds(175,150, 200,30);
	    customerRadio=new JRadioButton("Customer"); 
	    customerRadio.setBounds(120,200,100,30);    
	    staffRadio=new JRadioButton("Restaurant Staff"); 	
	    staffRadio.setBounds(230,200,200,30); 
	    buttonGroup=new ButtonGroup();    
	    login=new JButton("LOGIN");
	    login.setBounds(190,250,100,30);
	    login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				if(!Pattern.matches("[a-zA-Z_]{8,20}", usernameInput.getText())) {
					JOptionPane.showMessageDialog(frame, "Invalid Username!", "Alert", JOptionPane.WARNING_MESSAGE);
					username.setText("");
					username.requestFocus();
				}
				else if(!Pattern.matches("^(?=.*[0-9])"+ "(?=.*[a-z])(?=.*[A-Z])"+ "(?=.*[@#$%^&+=])"+ "(?=\\S+$).{8,20}$", String.valueOf(passwordInput.getPassword()))) {
					JOptionPane.showMessageDialog(frame, "Invalid Password!", "Alert", JOptionPane.WARNING_MESSAGE);
					passwordInput.setText("");
					passwordInput.requestFocus();
				}
				else if(customerRadio.isSelected()==false && staffRadio.isSelected()==false) {
					JOptionPane.showMessageDialog(frame, "Select an option!", "Alert", JOptionPane.WARNING_MESSAGE);
				}
				else {
					String name = usernameInput.getText(), pwd = new String(passwordInput.getPassword());
					if(customerRadio.isSelected() && authorizeUser("customer", name, pwd)) {
						new CustomerPage();
						frame.dispose();
					} 
					else if(staffRadio.isSelected() && authorizeUser("staff", name, pwd)) {
						new StaffPage();
						frame.dispose();
					} else {
						JOptionPane.showMessageDialog(frame, "User is not registered!");
					}
				}
			}
		}); 
	    
	    frame.add(heading);
	    frame.add(username);
	    frame.add(usernameInput);   
	    frame.add(password); 
	    frame.add(passwordInput);
	    frame.add(customerRadio);
	    frame.add(staffRadio);
	    buttonGroup.add(customerRadio);
	    buttonGroup.add(staffRadio);
	    frame.add(login);
	    frame.setSize(500,500);  
	    frame.setLayout(null);  
	    frame.setVisible(true);  	
	}
	public static boolean authorizeUser(String table, String name, String password) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		    Connection conn = DriverManager.getConnection(
		        "jdbc:mysql://localhost:3306/Restaurant_Management","root", "passwood");
		    Statement statement = conn.createStatement();
		    ResultSet rs = statement.executeQuery("select count(*) from "+table+" where username = "+name+" & password = "+password);
		    rs.next();
		    if(rs.getInt(1) == 1) return true;
		    return false;
		} catch(Exception e) {
			return false;
		}
	}
	public static void main(String args[]){
		new login();
	}
	
}
  

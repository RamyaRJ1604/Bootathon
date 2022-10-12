import javax.swing.*;
import java.util.regex.*;
import java.awt.event.*;
import java.sql.*;

public class Login{
	Login(){
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
					JOptionPane.showMessageDialog(frame, "Username Format Invalid!", "Alert", JOptionPane.WARNING_MESSAGE);
					username.setText("");
					username.requestFocus();
				}
				else if(!Pattern.matches("^(?=.*[0-9])"+ "(?=.*[a-z])(?=.*[A-Z])"+ "(?=.*[@#$%^&+=])"+ "(?=\\S+$).{8,20}$", String.valueOf(passwordInput.getPassword()))) {
					JOptionPane.showMessageDialog(frame, "Password Format Invalid!", "Alert", JOptionPane.WARNING_MESSAGE);
					passwordInput.setText("");
					passwordInput.requestFocus();
				}
				else if(customerRadio.isSelected()==false && staffRadio.isSelected()==false) {
					JOptionPane.showMessageDialog(frame, "Select an option!", "Alert", JOptionPane.WARNING_MESSAGE);
				}
				else {
					String name = usernameInput.getText(), pwd = new String(passwordInput.getPassword());
					User currentUser;
					if(customerRadio.isSelected() && (currentUser = authorizeUser("customer", name, pwd))!=null) {
						new CustomerPage(currentUser);
						frame.dispose();
					} 
					else if(staffRadio.isSelected() && (currentUser = authorizeUser("staff", name, pwd))!=null) {
						new StaffPage(currentUser);
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
	public static User authorizeUser(String table, String name, String password) {
		PreparedStatement statement = null;
		PreparedStatement statement2 = null;
		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		    conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurant_management","root", "magesh123");
		    statement = conn.prepareStatement("select count(*), customer_id from "+table+" where customer_name = ? && password = ?");
			statement.setString(1, name);
			statement.setString(2, password);
		    ResultSet rs = statement.executeQuery();
		    rs.next();
		    if(rs.getInt(1) >= 1) {
		    	int customerID = rs.getInt(2);
		    	statement2 = conn.prepareStatement("select * from customer where customer_id = ?");
		    	statement2.setInt(1, customerID);
		    	ResultSet rs2 = statement2.executeQuery();
		    	rs2.next();
				String customerName = rs2.getString(2);
				String pwd = rs2.getString(3);
				String mobile = rs2.getString(4);
				String mail = rs2.getString(5);
				int addressID = rs2.getInt(6);

				statement2.close();

				statement2 = conn.prepareStatement("select * from address where address_id = ?");
		    	statement2.setInt(1, addressID); 
				rs2 = statement2.executeQuery();
				rs2.next();
				String address = String.valueOf(String.valueOf(rs2.getInt(2))  + "," + rs2.getString(3)  + "," + rs2.getString(4) + "," + String.valueOf(rs2.getInt(5)));
		    	return new User(customerID, customerName, pwd, mobile, mail, address, table);
		    }
		    return null;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			try { statement.close(); } catch (Exception e) { }
			try { conn.close(); } catch (Exception e) { }
		}
	}
	public static void main(String args[]){
		new Login();
	}
	
}
  

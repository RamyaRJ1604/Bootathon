import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.regex.Pattern;
import javax.swing.*;

public class Registration extends JFrame{
	JLabel heading;
	JLabel name;
	JLabel address;
	JLabel email;
	JLabel mobile;
	JLabel password;
	JTextField nameInput;
	JTextField emailInput;
	JTextField addressInput;
	JTextField mobileInput;
	JPasswordField passwordInput;
	JButton register;
	
	Registration(){		
		//Fonts
		Font  font1  = new Font(Font.DIALOG,  Font.BOLD, 25);
		Font  font3  = new Font(Font.DIALOG,  Font.BOLD, 15);
		
		//color
		Color purp = new Color(122,136,222);

		//Labels
		heading = new JLabel("Sign Up");
		name = new JLabel("Name: ");
		name.setBounds(220,150,100,50);
		email = new JLabel("Email: ");
		email.setBounds(220,250,100,50);
		address = new JLabel("Address: ");
		address.setBounds(220,350,100,50);
		mobile = new JLabel("Mobile: ");
		mobile.setBounds(220,450,100,50);
		password = new JLabel("Password");
		password.setBounds(220,550,100,50);
		heading.setBounds(350,20,100,100);
		
		//TextFields
		nameInput = new JTextField("Yourname");
		nameInput.setBounds(380,150,200,50);
		emailInput = new JTextField("abc@gmail.com");
		emailInput.setBounds(380,250,200,50);
		addressInput = new JTextField("12,street,city,411000");
		addressInput.setBounds(380,350,200,50);
		mobileInput = new JTextField("9234567890");
		mobileInput.setBounds(380,450,200,50);
		passwordInput = new JPasswordField("Krishna@24");
		passwordInput.setBounds(380,550,200,50);
		

		//radiobutton
		JRadioButton customerButton = new JRadioButton("Customer"); 
	    customerButton.setBounds(250,600,100,50);    
	    JRadioButton staffButton = new JRadioButton("Restaurant Staff"); 	
	    staffButton.setBounds(450,600,100,50); 
	    ButtonGroup buttonGroup = new ButtonGroup();    
		buttonGroup.add(customerButton);
		buttonGroup.add(staffButton);
		
		//Button
		register = new JButton("Register");
		register.setBounds(350,680,100,50);
		register.setBackground(Color.yellow);
		register.setForeground(purp);
		
		//setting fonts
		heading.setFont(font1);
		register.setFont(font3);
		name.setFont(font3);
		address.setFont(font3);
		mobile.setFont(font3);
		password.setFont(font3);
		
		//setting color
		this.getContentPane().setBackground(purp);
		name.setForeground(Color.YELLOW);
		password.setForeground(Color.YELLOW);
		mobile.setForeground(Color.YELLOW);
		address.setForeground(Color.YELLOW);
		email.setForeground(Color.YELLOW);
		heading.setForeground(Color.YELLOW);
		
		//padding
		nameInput.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		emailInput.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		addressInput.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		mobileInput.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		passwordInput.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		
		//adding the components to the frame
		add(heading);
		add(name);
		add(email);
		add(address);
		add(mobile);
		add(nameInput);
		add(emailInput);
		add(addressInput);
		add(mobileInput);
		add(passwordInput);
		add(password);
		add(customerButton);
		add(staffButton);
		add(register);
		
		//validation
		register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				if(!Pattern.matches("^[a-zA-Z_]{8,20}$", nameInput.getText())) {
					JOptionPane.showMessageDialog(null , "Invalid Username!", "Alert", JOptionPane.WARNING_MESSAGE);
					nameInput.setText("");
					nameInput.requestFocus();
				}
				else if(!Pattern.matches("[a-zA-Z\\.\\d]{3,}[@][a-z]{3,}[\\.][a-z]{2,3}", String.valueOf(emailInput.getText()))) {
					JOptionPane.showMessageDialog(null, "Invalid E-Mail ID!", "Alert", JOptionPane.WARNING_MESSAGE);
					emailInput.setText("");
					emailInput.requestFocus();
				}
				else if(addressInput.getText()==null) {
					JOptionPane.showMessageDialog(null, "Please enter valid addressInput.", "Alert", JOptionPane.WARNING_MESSAGE);
					addressInput.setText("");
					addressInput.requestFocus();
				}
				else if(!Pattern.matches("^[1-9][\\d]{9}$", String.valueOf(mobileInput.getText()))) {
					JOptionPane.showMessageDialog(null, "Invalid Mobile Number!", "Alert", JOptionPane.WARNING_MESSAGE);
					mobileInput.setText("");
					mobileInput.requestFocus();
				}
				else if(!Pattern.matches("^(?=.*[\\d])"+ "(?=.*[a-z])(?=.*[A-Z])"+ "(?=.*[@#$%^&+=])"+ "(?=\\S+$).{8,20}$", String.valueOf(passwordInput.getPassword()))) {
					JOptionPane.showMessageDialog(null, "Invalid Password!", "Alert", JOptionPane.WARNING_MESSAGE);
					passwordInput.setText("");
					passwordInput.requestFocus();
				}
				else {
					String receivedName = nameInput.getText();
					String receivedPassword = new String(passwordInput.getPassword());
					String receivedMobile = mobileInput.getText();
					String receivedMail = emailInput.getText();
					String receivedAddress = addressInput.getText();
					String receivedUserType = null;
					if(customerButton.isSelected()){
						receivedUserType = "customer";
					} else if(staffButton.isSelected()){
						receivedUserType = "staff";
					} else {
						receivedUserType = "unknown";
					}

					User newUser = new User(
						0,
						receivedName, 
						receivedPassword, 
						receivedMobile, 
						receivedMail,
						receivedAddress,
						receivedUserType
					);
					if(regiserUser(newUser))
						JOptionPane.showMessageDialog(null, "Registration Successful!");
					else
						JOptionPane.showMessageDialog(null, "Registration Failed!");
				}
			}
		});

		setLayout(null);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(800,800);
	}

	public static boolean regiserUser(User user) {
		Connection conn = null;
		PreparedStatement checkAddress = null;
		PreparedStatement setAddress = null;
		PreparedStatement userRegister = null;
		Statement statement = null;
		Statement statement1 = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		    conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurant_management","root", "magesh123");
			
			checkAddress = conn.prepareStatement("select count(*), address_id from address where house_no = ? && street = ? && city = ? && pincode = ?");
			checkAddress.setInt(1, user.address.houseNo);
			checkAddress.setString(2, user.address.street);
			checkAddress.setString(3, user.address.city);
			checkAddress.setInt(4, user.address.pincode);
			ResultSet rs = checkAddress.executeQuery();
			
			int addressRows;
			rs.next();
			if(rs.getInt(1) == 1) {
				addressRows = rs.getInt(2);
			} else {
				statement = conn.createStatement();
				ResultSet rs1 = statement.executeQuery("select count(*) from address");
				rs1.next();
				addressRows = (rs1.getInt(1))+1;
				setAddress = conn.prepareStatement("insert into address values(?,?,?,?,?)");
				setAddress.setInt(1, addressRows);
				setAddress.setInt(2, user.address.houseNo);
				setAddress.setString(3, user.address.street);
				setAddress.setString(4, user.address.city);
				setAddress.setInt(5, user.address.pincode);
				setAddress.execute();
				setAddress.close();
			}
	
			statement1 = conn.createStatement();
			rs = statement1.executeQuery("select count(*) from " + user.userType);
			rs.next();
			int userRows = rs.getInt(1);

		    userRegister = conn.prepareStatement("insert into "+ user.userType +" values(?,?,?,?,?,?)");
			userRegister.setInt(1, userRows+1);
			userRegister.setString(2, user.name);
			userRegister.setString(3, user.password);
			userRegister.setString(4, user.mobile);
			userRegister.setString(5, user.mail);
			userRegister.setInt(6, addressRows);
			System.out.println(userRegister);
			userRegister.execute();
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		} 
		finally {
			try { statement.close(); } catch (Exception e) { }
			try { statement1.close(); } catch (Exception e) { }
			try { conn.close(); } catch (Exception e) { }
    		try { userRegister.close(); } catch (Exception e) { }
    		try { setAddress.close(); } catch (Exception e) { }
    		try { checkAddress.close(); } catch (Exception e) { }
		
		}
		return true;
	}
	
	public static void main(String[] args) {
		new Registration();
	}
}
import java.awt.*;
import java.sql.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class AddFoodInMenu extends JFrame{
	AddFoodInMenu(){
		Font  f1  = new Font(Font.DIALOG,  Font.BOLD, 16);
		
		JLabel title = new JLabel("Menu");
		title.setBounds(180, 40, 80, 20);
		title.setForeground(Color.yellow);
		title.setFont(f1);
		add(title);
		
		// Color class for Purple
		Color purp = new Color(122,136,222);
		this.getContentPane().setBackground(purp);
		
		JLabel foodName = new JLabel("Food Name:");
		foodName.setBounds(100, 75, 100, 30);
		foodName.setForeground(Color.yellow);
		foodName.setFont(f1);
		add(foodName);
		
		JTextField nameInput = new JTextField();
		nameInput.setBounds(220, 75, 100, 30);
		add(nameInput);

		JLabel foodPrice = new JLabel("Price:");
		foodPrice.setBounds(100, 125, 100, 30);
		foodPrice.setForeground(Color.yellow);
		foodPrice.setFont(f1);
		add(foodPrice);
		
		JTextField priceInput = new JTextField();
		priceInput.setBounds(220, 125, 100, 30);
		add(priceInput);

		JButton addFood = new JButton("Add");
		addFood.setBounds(180, 190, 100, 40);
		addFood.setBackground(Color.yellow);
		add(addFood);
		addFood.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				Connection conn = null;
				Statement statement = null;
				PreparedStatement insertValue = null;
				PreparedStatement checkValue = null;
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurant_management","root","magesh123");
					
					String checkQuery = "select count(*) from menu where food_name = ?";
					checkValue = conn.prepareStatement(checkQuery); 
					checkValue.setString(1, nameInput.getText());
					ResultSet ifPresent = checkValue.executeQuery();
					ifPresent.next();
					if(ifPresent.getInt(1)==0){
						statement = conn.createStatement();
						String rowCount = "select count(*) from Menu";
						ResultSet totalcount = statement.executeQuery(rowCount);
						totalcount.next();
						int count = totalcount.getInt(1);

						String insertquery = "insert into Menu values (?,?,?)";
						insertValue = conn.prepareStatement(insertquery); 
						insertValue.setInt(1, count);
						insertValue.setString(2, nameInput.getText());
						insertValue.setInt(3, Integer.parseInt(priceInput.getText()));
						insertValue.executeUpdate();
						JOptionPane.showMessageDialog(null, "Added Successfully!");
					} else {
						String insertquery = "update menu set price = ? where food_name = ?";
						insertValue = conn.prepareStatement(insertquery); 
						insertValue.setInt(1, Integer.parseInt(priceInput.getText()));
						insertValue.setString(2, nameInput.getText());
						insertValue.executeUpdate();
						JOptionPane.showMessageDialog(null, "Food Already Present in Menu, Updated Price Successfully");
					}
				}
				catch(Exception e){
					System.err.println(e);
				} finally {
					try {statement.close();} catch (Exception e) { }
					try {insertValue.close();} catch (Exception e) { }
					try {conn.close();} catch (Exception e) { }
				}
			}
		}); 
		
		setLayout(null);
		setVisible(true);
		setSize(400, 300);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
	}
	public static void main(String[] args) {
		new AddFoodInMenu();
	}

}

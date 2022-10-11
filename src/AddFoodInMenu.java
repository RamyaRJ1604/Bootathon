import java.awt.*;
import java.sql.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class AddFoodInMenu extends JFrame{
	AddFoodInMenu(){
		
		
		
		// jdbc
		
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
				try {
					System.out.print("Dfd");
					int min = 10000;
					int max = 99999;
//					int randid = (int)Math.round(Math.random() * (max - min + 1) + min);
					String rowCount = "select count(*) from Menu";
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurant_management","root","pranav@05");
					Statement statement = connection.createStatement();
					String insertquery = "insert into Menu values (?,?,?)";
					
					PreparedStatement insertvalue = connection.prepareStatement(insertquery); 
					ResultSet totalcount = statement.executeQuery(rowCount);
					totalcount.next();
					int count = totalcount.getInt(1);
					insertvalue.setInt(1, count);
					insertvalue.setString(2, nameInput.getText());
					insertvalue.setInt(3, Integer.parseInt(priceInput.getText()));
					insertvalue.executeUpdate();
					connection.close();
				}
				catch(Exception e){
					System.out.println(e);
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

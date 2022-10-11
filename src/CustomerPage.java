import java.awt.*;
import javax.swing.*;

public class CustomerPage extends JFrame{
	CustomerPage(){
		Font  f1  = new Font(Font.DIALOG,  Font.BOLD, 16);
		
		JLabel title = new JLabel("Welcome Customer");
		title.setBounds(110, 40, 180, 20);
		title.setForeground(Color.yellow);
		title.setFont(f1);
		add(title);
		
		Color purp = new Color(122,136,222);
		this.getContentPane().setBackground(purp);

		JButton orderFood = new JButton("Order Food");
		orderFood.setBounds(60, 140, 120, 40);
		orderFood.setBackground(Color.yellow);
		add(orderFood);
		
		JButton orderHistory = new JButton("Order History");
		orderHistory.setBounds(200, 140, 120, 40);
		orderHistory.setBackground(Color.yellow);
		add(orderHistory);

		JButton displayDetails = new JButton("Display Details");
		displayDetails.setBounds(130, 200, 120, 40);
		displayDetails.setBackground(Color.yellow);
		add(displayDetails);
		
		
		setLayout(null);
		setVisible(true);
		setSize(400, 300);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	public static void main(String[] args) {
		new CustomerPage();
	}
}

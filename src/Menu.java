import javax.swing.*;

public class Menu extends JFrame{
	Menu(){
		JLabel title = new JLabel("Menu");
		title.setBounds(180, 30, 40, 20);
		add(title);
			
		JLabel foodName = new JLabel("Food Name:");
		foodName.setBounds(100, 75, 100, 30);
		add(foodName);
		JTextField nameInput = new JTextField();
		nameInput.setBounds(220, 75, 100, 30);
		add(nameInput);

		JLabel foodPrice = new JLabel("Price:");
		foodPrice.setBounds(100, 125, 100, 30);
		add(foodPrice);
		JTextField priceInput = new JTextField();
		priceInput.setBounds(220, 125, 100, 30);
		add(priceInput);

		JButton addFood = new JButton("Add");
		addFood.setBounds(180, 170, 75, 50);
		add(addFood);
		
		setLayout(null);
		setVisible(true);
		setSize(400, 300);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	public static void main(String[] args) {
		new Menu();
	}

}

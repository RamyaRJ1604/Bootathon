import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.*;
public class PlaceOrder {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		OrderFrame of = new OrderFrame();
		
	}

}

class OrderFrame extends JFrame{
	JLabel title;
	Images image = new Images();
	
	public OrderFrame() {
		//Fonts
		Font  f3  = new Font(Font.DIALOG,  Font.BOLD, 20);
		Font  f1  = new Font(Font.DIALOG,  Font.BOLD, 18);
			
		title = new JLabel("Place your order");
		title.setBounds(350,20,200,100);
//		image.setIcon(new ImageIcon("D://Bootathon//src/omlette.png"));
//		 Dimension size = image.getPreferredSize(); //Gets the size of the image
//	        image.setBounds(50, 30, size.width, size.height); //Sets the location of the image
////		image.setBounds(200,200,200,200);
		
		//color
		Color purp = new Color(122,136,222);
		
		//setting color
		this.getContentPane().setBackground(purp);
		title.setForeground(Color.yellow);
		//setting fonts
		title.setFont(f3);
		
		
		
		//jdbc connection
		try {
		
//			int randid = (int)Math.round(Math.random() * (max - min + 1) + min);
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurant_management","root","pranav@05");
			Statement statement = connection.createStatement();
			String query = "select * from Menu";
			ResultSet rs = statement.executeQuery(query);
			int starty = 150;
			
			while(rs.next()) {
				int foodid = rs.getInt(1);
				String foodname = rs.getString(2);
				int foodprice = rs.getInt(3);
				JCheckBox cb = new JCheckBox(String.valueOf(foodid));
				JLabel name = new JLabel(foodname);
				JLabel price = new JLabel("$"+String.valueOf(foodprice));
				JLabel empty = new JLabel("-----------------------------------------------------------------------------------------------------------------------------------------------");
				cb.setBounds(100,starty,40,40);
				name.setBounds(370,starty,100,50);
				price.setBounds(620,starty,100,50);
				empty.setBounds(100,starty+25,700,50);
				
				//fonts and color
				name.setFont(f1);
				price.setFont(f1);
				empty.setForeground(Color.cyan);
				price.setForeground(Color.yellow);
				name.setForeground(Color.yellow);
				add(cb);
				add(name);
				add(price);
				add(empty);
				starty+=100;
				
				System.out.print(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getInt(3));
			}
			
			connection.close();
		}
		catch(Exception e){
			System.out.println(e);
		}
		//adding elements
				add(image);
				add(title);
				add(image);
				setLayout(null);
				setVisible(true);
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				setSize(800,800);
		
	}
}
class Images extends Canvas{
	public void paint(Graphics g) {
		Toolkit t = Toolkit.getDefaultToolkit();
		Image i = t.getImage("./omlette.png");
		g.drawImage(i,200,200,this);
	}
}

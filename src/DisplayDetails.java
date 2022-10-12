import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.table.*;

public class DisplayDetails extends JFrame {
	DisplayDetails(User currentUser){

		JLabel pageTitle = new JLabel("Profile");
		pageTitle.setBounds(200, 40, 100, 30);
		add(pageTitle);

		// DefaultTableModel model = new DefaultTableModel();
		// JTable table = new JTable(model);
		// model.addColumn("Category");
      	// model.addColumn("Details");

        // model.addRow(new Object[]{"UserID", currentUser.id});
		// model.addRow(new Object[]{"Name", currentUser.name});
		// model.addRow(new Object[]{"Mobile Number", currentUser.mobile});
		// model.addRow(new Object[]{"Email Address", currentUser.mail});
		// String address = currentUser.address.houseNo + "," + currentUser.address.street + "," + currentUser.address.city + "," + currentUser.address.pincode;
		// model.addRow(new Object[]{"Residential Address", address});

		// table.setBounds(50, 100, 400, 100);
		// add(table);

		JLabel id = new JLabel("ID: ");
		id.setBounds(50,80,150,40);
		JLabel name = new JLabel("Name: ");
		name.setBounds(50,110,150,40);
		JLabel mobile = new JLabel("Mobile: ");
		mobile.setBounds(50,140,150,40);
		JLabel mail = new JLabel("Mail ID: ");
		mail.setBounds(50,170,150,40);
		JLabel addr = new JLabel("Address: ");
		addr.setBounds(50,200,150,40);
		

		JLabel setid = new JLabel(String.valueOf(currentUser.id));
		setid.setBounds(220,80,150,40);
		
		JLabel setname = new JLabel(currentUser.name);
		setname.setBounds(220,110,150,40);
		
		JLabel setmobile = new JLabel(currentUser.mobile);
		setmobile.setBounds(220,140,150,40);
		
		JLabel setmail = new JLabel(currentUser.mail);
		setmail.setBounds(220,170,150,40);
		
		String address = currentUser.address.houseNo + "," + currentUser.address.street + "," + currentUser.address.city + "," + currentUser.address.pincode;
		JLabel setaddress = new JLabel(address);
		setaddress.setBounds(220,200,150,40);


		add(id);
		add(name);
		add(mobile);
		add(mail);
		add(addr);
		
		//adding details label to frame
		add(setid);
		add(setname);
		add(setmobile);
		add(setmail);
		add(setaddress);


		JButton backButton = new JButton("Back");
		backButton.setBounds(200, 250, 100, 40);
		backButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(currentUser.userType == "customer"){
					new CustomerPage(currentUser);
					dispose();
				} else {
					new StaffPage(currentUser);
					dispose();
				}
			}
		});
		add(backButton);

		setLayout(null);
		setSize(500,400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
}

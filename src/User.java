public class User {
    int customerID;
    String name;
    String password;
    String mobile;
    String mail;
    Address address;
    String userType;
    
    class Address{
        int houseNo;
        String street;
        String city;
        int pincode;

        Address(int n, String a, String b, int p){
            houseNo = n;
            street = a;
            city = b;
            pincode = p;
        }
    }

    User(int id, String a, String b, String c, String d, String e, String f){
        customerID = id;
        name = a;
        password = b;
        mobile = c;
        mail = d;
        String[] addr = e.split(",");
        address = new Address(
            Integer.parseInt(addr[0].trim()), 
            addr[1].trim(), 
            addr[2].trim(), 
            Integer.parseInt(addr[3].trim())
        );
        userType = f;
    }
}

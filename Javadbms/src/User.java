import java.util.*;
import javax.mail.internet.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class User extends JFrame implements ActionListener{
	String fname;
	String lname;
	String username;
	String email_id;
	String password;
	double Current_bal;
	double you_owe;
	double owed; //only one parameterized constructor
	int counter; //no. of pending bills
	JLabel l1, l2, l3, l4, l5, l6, l7, l8, l9, l10, s1, s2, s3, label;
	JTextField tf1, tf2, tf3, tf4, tf5, tf6, tf7, tf8;
	JButton btn1, btn2;
	JPasswordField p1, p2;
	static ArrayList<String> Friends_list = new ArrayList<String>();
	static ArrayList<String> Groups_list = new ArrayList<String>();
	
	User()
	{
		fname=null;
		lname=null;
		username=null;
		email_id=null;
		password=null;
		Current_bal=0;
		you_owe=0;
		owed=0;
		counter=0;
	}

	User(String fn,String ln,String un,String ei,String pw,int bal,int yo,int ow)
	{
		fname=fn;
		lname=ln;
		username=un;
		email_id=ei;
		password=pw;
		Current_bal=bal;
		you_owe=yo;
		owed=ow;
	}
	
	public String getFname() {
		return fname;
	}
	public String getLname() {
		return lname;
	}
	public String getUsername() {
		return username;
	}
	public String getEmail_id() {
		return email_id;
	}
	public String getPassword() {
		return password;
	}
	public double getCurrent_bal() {
		return Current_bal;
	}
	public double getOwed() {
		return owed;
	}
	public double getYou_owe() {
		return you_owe;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setCurrent_bal(double current_bal) {
		Current_bal = current_bal;
	}
	public void setYou_owe(double you_owe) {
		this.you_owe = you_owe;
	}
	public void setOwed(double owed) {
		this.owed = owed;
	}
	
	public static boolean isValidEmailAddress(String email) {
		   boolean result = true;
		   try {
		      InternetAddress emailAddr = new InternetAddress(email);
		      emailAddr.validate();
		   } 
		   catch (AddressException ex) {
		      result = false;
		   }
		   return result;
	}
	
	/*boolean IsUserValid()
	{
		if(this.getUsername() exits in database)
			return true;
		else
			return false;
	}*/
	void AddUser()
	{
		JFrame f = new JFrame("Bill Sharing App Registration");
		JPanel p = new JPanel();
		JPanel pa = new JPanel();
		/*Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
		int height = screensize.height;
		int width = screensize.width;*/
		f.pack();
		f.setVisible(true);
	    //f.setSize(width, height);
		f.setSize(1000, 1000);
	    f.setLocationRelativeTo(null); //center the jframe on screen
	    p.setLayout(new BorderLayout());
	    pa.setLayout(new BorderLayout());
	    JLabel la = new JLabel(new ImageIcon("/home/shruti/Pictures/up2.jpg"));
	    //System.out.println("no");
	    l1 = new JLabel("Create Your Account");
	    l1.setHorizontalAlignment(SwingConstants.CENTER);
	    l1.setForeground(Color.DARK_GRAY);
	    l1.setFont(new Font("Serif", Font.BOLD, 28));
	 
	    l2 = new JLabel("First Name:");
	    l2.setForeground(Color.BLACK);
	    l2.setFont(new Font("Serif", Font.BOLD, 18));
	    l3 = new JLabel("Last Name:");
	    l3.setForeground(Color.BLACK);
	    l3.setFont(new Font("Serif", Font.BOLD, 18));
	    l4 = new JLabel("Username");
	    l4.setForeground(Color.BLACK);
	    l4.setFont(new Font("Serif", Font.BOLD, 18));
	    s1 = new JLabel("Your login user name must be between 3 and 15 characters." +
	    		" You can include lowercase letters (a-z),");
	    s1.setForeground(Color.BLACK);
	    s2 = new JLabel(" numbers, hyphens (“-“) and underscores (“_”), but it must" +
	    		" contain at least one letter.");
	    s2.setForeground(Color.BLACK);
	    s1.setFont(new Font("Dialog", Font.ITALIC, 11));
	    s2.setFont(new Font("Dialog", Font.ITALIC, 11));

		l5 = new JLabel("Email-ID:");
		l5.setForeground(Color.BLACK);
	    l5.setFont(new Font("Serif", Font.BOLD, 18));
	    l6 = new JLabel("Password:");
	    l6.setForeground(Color.BLACK);
	    l6.setFont(new Font("Serif", Font.BOLD, 18));
	    l7 = new JLabel("Confirm Password:");
	    l7.setForeground(Color.BLACK);
	    l7.setFont(new Font("Serif", Font.BOLD, 18));
	    l8 = new JLabel("Current Balance:");
	    l8.setForeground(Color.BLACK);
	    l8.setFont(new Font("Serif", Font.BOLD, 18));
	    l9 = new JLabel("You Owe:");
	    l9.setForeground(Color.BLACK);
	    l9.setFont(new Font("Serif", Font.BOLD, 18));
	    l10 = new JLabel("Owed:"); 
	    l10.setForeground(Color.BLACK);
	    l10.setFont(new Font("Serif", Font.BOLD, 18));
	    tf1 = new JTextField();
	    //tf1.setHorizontalAlignment(SwingConstants.CENTER);
	    tf2 = new JTextField();
	    tf3 = new JTextField();
	    tf4 = new JTextField();
	    p1 = new JPasswordField();
	    p2 = new JPasswordField();
	    tf5 = new JTextField();
	    tf6 = new JTextField();
	    tf7 = new JTextField();
	 
	    btn1 = new JButton("Submit");
	    btn2 = new JButton("Back");
	 
	    btn1.setBorder(new LineBorder(Color.BLACK,2));
	    btn1.setBackground(Color.white);
	    btn1.setForeground(Color.BLACK);
	    btn2.setBorder(new LineBorder(Color.BLACK,2));
	    btn2.setBackground(Color.white);
	    btn2.setForeground(Color.BLACK);
	    
	    btn1.addActionListener(this);
	    btn2.addActionListener(this);
	 
	    l1.setBounds(400, 30, 400, 30);
	    l2.setBounds(400, 90, 200, 30);
	    l3.setBounds(400, 140, 200, 30);
	    s1.setBounds(400, 180, 800, 30);
	    s2.setBounds(400, 200, 800, 30);
	    l4.setBounds(400, 250, 200, 30);
	    l5.setBounds(400, 300, 200, 30);
	    l6.setBounds(400, 350, 200, 30);
	    l7.setBounds(400, 400, 200, 30);
	    l8.setBounds(400, 450, 200, 30);
	    l9.setBounds(400, 500, 200, 30);
	    l10.setBounds(400, 550, 200, 30);
	    tf1.setBounds(600, 90, 200, 30);
	    tf2.setBounds(600, 140, 200, 30);
	    tf3.setBounds(600, 250, 200, 30);
	    tf4.setBounds(600, 300, 200, 30);
	    p1.setBounds(600, 350, 200, 30);
	    p2.setBounds(600, 400, 200, 30);
	    tf5.setBounds(600, 450, 200, 30);
	    tf6.setBounds(600, 500, 200, 30);
	    tf7.setBounds(600, 550, 200, 30);
	    btn1.setBounds(470, 650, 130, 40);
	    btn2.setBounds(620, 650, 130, 40);
	    btn1.setForeground(Color.BLACK);
	    btn1.setBackground(Color.white);
	    btn2.setForeground(Color.BLACK);
	    btn2.setBackground(Color.white);
	    
	    p.add(l1);
	    p.add(l2);
	    p.add(tf1);
	    p.add(l3);
	    p.add(tf2);
	    p.add(l4);
	    p.add(s1);
	    p.add(s2);
	    p.add(tf3);
	    p.add(l5);
	    p.add(tf4);
	    p.add(l6);
	    p.add(p1);
	    p.add(l7);
	    p.add(p2);
	    p.add(l8);
	    p.add(tf5);
	    p.add(l9);
	    p.add(tf6);
	    p.add(l10);
	    p.add(tf7);
	    p.add(btn1);
	    p.add(btn2);
	    p.add(la);
	    pa.add(p);
	    f.add(pa);
	}
	/*void UpdateUser(User us1)
	{
		update user us1 info in the database
	}
	*/
	void addFriend(User us1)
	{
		Friends_list.add(us1.username);
	}
	public void actionPerformed(ActionEvent e) 
    {
		Jdbc j = new Jdbc();
		System.out.println("yes");
       if (e.getSource() == btn1)
        {
           int x = 0;
           String st1 = tf1.getText();
           String st2 = tf2.getText();
           String st3 = tf3.getText();
           String st4 = tf4.getText();

           char[] s3 = p1.getPassword();
           char[] s4 = p2.getPassword(); 
           String st5 = new String(s3);
           String st6 = new String(s4);

           String st7 = tf5.getText();
           String st8 = tf6.getText();
           String st9 = tf7.getText();
           Double d1 = Double.parseDouble(st7);
           Double d2 = Double.parseDouble(st8);
           Double d3 = Double.parseDouble(st9);
           
           if (st5.equals(st6))
          {
               try
              {
            	   Class.forName("com.mysql.jdbc.Driver"); //or JDBC DRIVER NAME string
            	   j.con = DriverManager.getConnection(j.DB_URL,j.USER,j.PASS); // to open the connection
                   PreparedStatement ps = j.con.prepareStatement("insert into User values(?,?,?,?,?,?,?,?)");
                   ps.setString(1, st1);
                   ps.setString(2, st2);
                   ps.setString(3, st3);
                   ps.setString(4, st4);
                   ps.setString(5, st6);
                   ps.setDouble(6, d1);
                   ps.setDouble(7, d2);
                   ps.setDouble(8, d3); 
                   ps.executeUpdate();
                   x++;
                   if (x > 0) 
                   {
                	   JOptionPane.showMessageDialog(btn1, "Data Saved Successfully");
                   }
                 //STEP 6: Clean-up environment
                   //rs.close();
                   //ps.close();
                   j.con.close();
                }
              catch (Exception ex) 
              {
                   System.out.println(ex);
                   //ex.printStackTrace();
              }
          }     
         else
          {
               JOptionPane.showMessageDialog(btn1, "Password Does Not Match");
           } 
        }
          
         else
      {
           tf1.setText("");
           tf2.setText("");
           tf3.setText("");
           tf4.setText("");
           p1.setText("");
           p2.setText("");
           tf5.setText("");
           tf6.setText("");
           tf7.setText("");
       }
   } 
	public static void main(String args[])
	  {
	      // new ();
	   }

}
import java.util.*;
//import javax.mail.internet.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import java.sql.*;

public class UserProfile extends JFrame implements ActionListener 
{
	JLabel l1, l2, l3, l4, l5, l6, l7, l8, txt1, txt2, txt3, btn3, btn4, btn6;
	JButton btn1, btn2,btn5,btn7,btn8,btn9,b1,b2;
	JTextArea ar;
	String user;
	JFrame f ;
	UserProfile(String username)
	{
		user = username;
		f = new JFrame("User Profile");
		JPanel container = new JPanel();
		container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
	    JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		JPanel p3 = new JPanel();
		f.setVisible(true);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int height = screenSize.height;
		int width = screenSize.width;
		f.setSize(width/2, height/2);
	    //f.setSize(1000, 1000);
	    p1.setLayout(new BorderLayout());
	    p2.setLayout(new BorderLayout());
	    p3.setLayout(new BorderLayout());
	    f.setBackground(new Color(53, 56, 64));
	    TitledBorder inputTitle = BorderFactory.createTitledBorder("Bill Sharing");
	    inputTitle.setTitleJustification(TitledBorder.LEFT);
	    inputTitle.setBorder(new LineBorder(Color.BLACK,5));
	    container.setBorder(inputTitle);
	    //p2.setBorder(inputTitle);
	    JLabel la = new JLabel(new ImageIcon("/home/shruti/Pictures/Pics/up1.jpg"));
	    //f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	 
	    l1 = new JLabel("Hello "+username+"!");
	    l1.setForeground(Color.DARK_GRAY);
	    l1.setFont(new Font("Serif", Font.BOLD, 30));
	    l2 = new JLabel("SPLITSTER");
	    l2.setForeground(Color.BLACK);
	    l2.setFont(new Font("Serif", Font.BOLD, 30));
	    l2.setHorizontalAlignment(SwingConstants.CENTER);
	    l3 = new JLabel("Account Activity");
	    l3.setForeground(Color.BLACK);
	    l3.setHorizontalAlignment(SwingConstants.CENTER);
	    l3.setFont(new Font("Serif", Font.ITALIC, 30));
	    l4 = new JLabel("Total Balance");
	    l4.setForeground(Color.BLACK);
	    l4.setFont(new Font("Serif", Font.ITALIC, 25));
	    l5 = new JLabel("You Owe");
	    l5.setForeground(Color.BLACK);
	    l5.setFont(new Font("Arial", Font.ITALIC, 25));
	    l6 = new JLabel("Owed");
	    l6.setForeground(Color.BLACK);
	    l6.setFont(new Font("Arial", Font.ITALIC, 25));
	    l7 = new JLabel("");
	    btn1 = new JButton("Add Bill");
	    btn2 = new JButton("Settle Up");
	    btn3 = new JLabel("Recent Activity");
	    btn4 = new JLabel("All Expenses");
	    btn5 = new JButton("Groups");
	    btn6 = new JLabel("Friends");
	    btn8 = new JButton("Log Out");
	    btn7 = new JButton("Change Passoword");
	    btn1.setBorder(new LineBorder(Color.BLACK,2));
	    btn1.setBackground(Color.LIGHT_GRAY);
	    btn1.setForeground(Color.BLACK);
	    btn2.setBorder(new LineBorder(Color.BLACK,2));
	    btn2.setBackground(Color.LIGHT_GRAY);
	    btn2.setForeground(Color.BLACK);
	    btn3.setFont(new Font("Serif", Font.BOLD, 16));
	    btn3.setBorder(new LineBorder(Color.white,2));
	    btn3.setBackground(Color.white);
	    btn3.setForeground(Color.BLACK);
	    btn4.setFont(new Font("Serif", Font.BOLD, 16));
	    btn4.setBorder(new LineBorder(Color.white,2));
	    btn4.setBackground(Color.white);
	    btn4.setForeground(Color.BLACK);
	    btn5.setFont(new Font("Serif", Font.BOLD, 16));
	    btn5.setBorder(new LineBorder(Color.white,2));
	    btn5.setBackground(Color.white);
	    btn5.setForeground(Color.BLACK);
	    btn6.setFont(new Font("Serif", Font.BOLD, 16));
	    btn6.setBorder(new LineBorder(Color.white,2));
	    btn6.setBackground(Color.white);
	    btn6.setForeground(Color.BLACK);
	    btn7.setBorder(new LineBorder(Color.BLACK,2));
	    btn7.setBackground(Color.LIGHT_GRAY);
	    btn7.setForeground(Color.BLACK);
	    btn8.setBorder(new LineBorder(Color.BLACK,2));
	    btn8.setBackground(Color.LIGHT_GRAY);
	    btn8.setForeground(Color.BLACK);
	    ar = new JTextArea(20,30);
	 
	    btn1.addActionListener(this);
	    btn2.addActionListener(this);
	    btn5.addActionListener(this);
	    btn7.addActionListener(this);
	    btn8.addActionListener(this);
	   // btn9.addActionListener(this);
	 
	    Jdbc j = new Jdbc();
	    try
        {
      	   Class.forName("com.mysql.jdbc.Driver"); //or JDBC DRIVER NAME string
      	   j.con = DriverManager.getConnection(j.DB_URL,j.USER,j.PASS); // to open the connection
      	   j.stmt = j.con.createStatement();
      	   String sql1;
      	   sql1 = "select Current_Balance,You_owe,Owed from User where Username='"+username+"'";
      	   ResultSet rs = j.stmt.executeQuery(sql1);
      	   while(rs.next())
      	   {
      		   Double d1 = rs.getDouble("Current_Balance");
      		   Double d2 = rs.getDouble("You_owe");
      		   Double d3 = rs.getDouble("Owed");
      		   String s1 = String.valueOf(d1);
      		   String s2 = String.valueOf(d2);
      		   String s3 = String.valueOf(d3);
      		   txt1 = new JLabel(s1);
      		   txt1.setFont(new Font("Serif", Font.BOLD, 25));
      		   txt2 = new JLabel(s2);
      		   txt2.setFont(new Font("Serif", Font.BOLD, 25));
      		   txt3 = new JLabel(s3);
      		   txt3.setFont(new Font("Serif", Font.BOLD, 25));
      	   }
      	   j.stmt = j.con.createStatement();
    	   String sql2;
    	   sql2 = "select count(*) from Friend where Lender_Name='"+username+"' or Receiver_Name='"+username+"'";
    	   ResultSet rs1 = j.stmt.executeQuery(sql2);
    	   while(rs1.next())
    	   {
    		   int count = rs1.getInt("count(*)");
    		   
    		   String s = String.valueOf(count);
    		   l8 = new JLabel("("+s+")");
    	   }
    	   rs1.close();
      	   j.con.close();
        }
       catch(SQLException se1){                      //Handle errors for JDBC
            se1.printStackTrace();
        }catch(Exception ex){                            //Handle errors for Class.forName
           ex.printStackTrace();}
          finally{ //finally block used to close resources
           try{
              if(j.stmt!=null)
                 j.stmt.close();
           }catch(SQLException se2){
           }// nothing we can do
           try{
              if(j.con!=null)
                 j.con.close();
           }catch(SQLException se3){
              se3.printStackTrace();
           }//end finally 
          }
	    
	    l1.setBounds(100, 30, 200, 40);
	    l2.setBounds(400, 30, 200, 40);
	    l3.setBounds(500, 90, 250, 30);
	    l4.setBounds(350, 140, 200, 30);
	    l5.setBounds(550, 140, 200, 30);
	    l6.setBounds(750, 140, 200, 30);
	    txt1.setBounds(370, 200, 200, 30);
	    txt2.setBounds(570, 200, 200, 30);
	    txt3.setBounds(770, 200, 200, 30);
	    btn1.setBounds(350, 500, 150, 35);
	    btn2.setBounds(550, 500, 150, 35);
	    btn3.setBounds(100, 190, 150, 30);
	    btn4.setBounds(100, 230, 150, 30);
	    btn5.setBounds(100, 270, 150, 30);
	    btn6.setBounds(100, 310, 110, 30);
	    l8.setBounds(210, 310, 30, 30);
	    btn8.setBounds(900, 30, 130, 30);
	    btn7.setBounds(1100, 30, 150, 30);
	    //btn9.setBounds(1100, 70, 100, 60);
	    l7.setBounds(100, 350, 80, 30);
	 
	    p2.add(l1);
	    p3.add(btn7);
	    p3.add(btn8);
	    //p3.add(btn9);
	    p3.add(l3);
	    p3.add(l4);
	    p3.add(l5);
	    p3.add(l6);
	    p3.add(txt1);
	    p3.add(txt2);
	    p3.add(txt3);
	    p3.add(btn1);
	    p3.add(btn2);
	    p3.add(btn3);
	    p3.add(btn4);
	    p3.add(btn5);
	    p3.add(btn6); 
	    p3.add(l8);
	    p3.add(l7);
	    p3.add(la);
	    p1.setPreferredSize(new Dimension(1000, 750));
	    p2.setPreferredSize(new Dimension(1000, 150));
	    p3.setPreferredSize(new Dimension(300, 750));
	    p1.setBackground(Color.pink);
	    p2.setBackground(Color.LIGHT_GRAY);
	    container.add(l2);
	    container.add(p2);
	    container.add(p3);
	    container.add(p1);
	    f.add(container);
	    //f.add(p2);
}
	public void paint(Graphics g)
	{
		super.paint(g);
		Graphics2D g2 = (Graphics2D) g;
		Line2D lin = new Line2D.Float(270, 0, 270, 1000);
		g2.draw(lin);
	}
	
	void changepass()
	{
		JTextField xField, yField, zField;
		JTextField old_password, new_password, cfm;
		xField = new JTextField(5);
		yField = new JTextField(5);
		zField = new JTextField(5);
		old_password = new JTextField();
		new_password = new JPasswordField();
		cfm = new JPasswordField();
		Object[] message = {
		                    "Old Password:", old_password,
		                    "New Password:", new_password,
		                    "Confirm Password:", cfm
		      		};
		int option = JOptionPane.showConfirmDialog(null, message, "Change Password", JOptionPane.OK_CANCEL_OPTION);
		if (option == JOptionPane.OK_OPTION) 
		{
			Jdbc j = new Jdbc();
			String s=null;
			try
	    	{
	    		Class.forName("com.mysql.jdbc.Driver"); //or JDBC DRIVER NAME string
	    		j.con = DriverManager.getConnection(j.DB_URL,j.USER,j.PASS); // to open the connection
	    		String st3 = old_password.getText();
	    		j.stmt = j.con.createStatement();
	    		String sql2 = "select Password from User where Username='"+user+"'";
	    		ResultSet rs1 = j.stmt.executeQuery(sql2);
	    		while(rs1.next())
	       	   {
	    		   s = rs1.getString("Password");
	       	   }
	    		String st1 = new_password.getText();
	    		String st2 = cfm.getText();
	    		if(st1.equals(st2) && s.equals(st3))
	    		{
		    	
		    		j.stmt = j.con.createStatement();
		    		//System.out.println(st2);
		    	    String sql1 = "Update User set Password = '"+st2+"' where Username='"+user+"'";
		    		j.stmt.executeUpdate(sql1);
		    		JOptionPane.showMessageDialog(null, "Password changed successfully!");
		    		//new UserProfile(user);
	    		}
	    		else
			    {
			    	 JOptionPane.showMessageDialog(null, "Password Does Not Match or old password was incorrect.");
			    	 changepass();
			    }
		    	j.con.close();
		    }	
		    catch(SQLException se1){                      //Handle errors for JDBC
                 se1.printStackTrace();
            }catch(Exception ex){                            //Handle errors for Class.forName
                 ex.printStackTrace();}
            finally{ //finally block used to close resources
                 try{
                	 if(j.stmt!=null)
                         j.stmt.close();
                   }catch(SQLException se2){
                }// nothing we can do
                 try{
                      if(j.con!=null)
                         j.con.close();
                }catch(SQLException se3){
                      se3.printStackTrace();
                }//end finally 
            }
		    }
		}
	
	void group()
	{
		int d;
		Jdbc jd = new Jdbc();
	    try
    	{
    		Class.forName("com.mysql.jdbc.Driver"); //or JDBC DRIVER NAME string
    		jd.con = DriverManager.getConnection(jd.DB_URL,jd.USER,jd.PASS); // to open the connection
    		jd.stmt = jd.con.createStatement();
    		String sql3 = "select GName,count(G_ID) as No_of_Members from GroupT where MName='"+user+"' group by G_ID";
    		ResultSet rs1 = jd.stmt.executeQuery(sql3);
    		JFrame jf = new JFrame("Groups");
    		JPanel p = new JPanel();
    		d= 180;
			p.setLayout(new BorderLayout());
			jf.setVisible(true);
			jf.setSize(700, 700);
			//jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			JLabel la = new JLabel(new ImageIcon("/home/shruti/Pictures/Pics/mem1.jpg"));
			jf.setLocationRelativeTo(null);
			JLabel l1,l2,l3,l6,l7;
			l3=new JLabel("Your Groups");
			l3.setForeground(Color.DARK_GRAY);
			l3.setFont(new Font("Serif", Font.BOLD, 30));
			l3.setBounds(500, 80, 400, 30);
			l6=new JLabel("Group Name");
			l6.setForeground(Color.DARK_GRAY);
			l6.setFont(new Font("Serif", Font.BOLD, 20));
			l6.setBounds(480, 120, 180, 30);
			l7=new JLabel("No. of Members");
			l7.setForeground(Color.DARK_GRAY);
			l7.setFont(new Font("Serif", Font.BOLD, 20));
			l7.setBounds(660, 120, 200, 30);
			p.add(l3);
			p.add(l6);
			p.add(l7);
    		while(rs1.next())
    		{
    			String st1 = rs1.getString("GName");
    			String st2 = rs1.getString("No_of_Members");
    			l1=new JLabel(st1);
    			l2=new JLabel(st2);
    			l1.setForeground(Color.BLACK);
    		    l1.setFont(new Font("Serif", Font.ITALIC, 20));
    		    l2.setForeground(Color.BLACK);
    		    l2.setFont(new Font("Serif", Font.ITALIC, 20));
    			l1.setBounds(500, d, 200, 30);
    			l2.setBounds(700, d, 50, 30);
    			d=d+60;
    			p.add(l1);
    			p.add(l2);
    		}
    		JLabel l = new JLabel("");
    		b1 = new JButton("Create a Group");
    		b2 = new JButton("Update Group");
    		b1.addActionListener(this);
    		b2.addActionListener(this);
    		b1.setBounds(450, d+30, 130, 35);
    		b2.setBounds(630, d+30, 130, 35);
    		b1.setBorder(new LineBorder(Color.BLACK,2));
    	    b1.setBackground(Color.LIGHT_GRAY);
    	    b1.setForeground(Color.BLACK);
    	    b2.setBorder(new LineBorder(Color.BLACK,2));
    	    b2.setBackground(Color.LIGHT_GRAY);
    	    b2.setForeground(Color.BLACK);
			p.add(b1);
			p.add(b2);
    		p.add(l);
    		p.add(la);
	    	jd.con.close();
	    	jf.add(p);
	    }	
	    catch(SQLException se1){                      //Handle errors for JDBC
             se1.printStackTrace();
        }catch(Exception ex){                            //Handle errors for Class.forName
             ex.printStackTrace();}
        finally{ //finally block used to close resources
             try{
            	 if(jd.stmt!=null)
                     jd.stmt.close();
               }catch(SQLException se2){
            }// nothing we can do
             try{
                  if(jd.con!=null)
                     jd.con.close();
            }catch(SQLException se3){
                  se3.printStackTrace();
            }//end finally 
        }
	    
		
	}
	
	public void actionPerformed(ActionEvent e) 
    {
		if(e.getSource() == btn8)//log out
		{
			super.dispose();
			new MainScreen();
		}
		else if(e.getSource() == btn7)	//change password
		{
			changepass();
		}
		else if(e.getSource() == btn5)	//groups
		{
			group();  
		}
	    else if(e.getSource() == btn1)	//add bill
        {
        	Bill bf = new Bill();
        	bf.AddBill(user);           
    
          }  
	    else if(e.getSource() == btn2)	//settle up
	    {
	    	Bill bf = new Bill();
        	bf.settleup(user);  
	    } 
	    else if(e.getSource() == b1)
	    {
	    	Group g = new Group();
	    	g.CreateGroup(user);
	    }
	    else if(e.getSource() == b2)
	    {
	    	Group g = new Group();
	    	g.updategroup(user);
	    }
   } 
	

	public static void main(String args[])
	  {
	       new UserProfile("shruti");
	   }
   
}
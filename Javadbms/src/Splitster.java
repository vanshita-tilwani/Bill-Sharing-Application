import java.util.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;

class MainScreen extends JFrame implements ActionListener
{
	JButton btn1, btn2;
	JLabel l1, label;
	public MainScreen()
	{
     
		JFrame frame = new JFrame("SPLITSTER");
		JPanel panel = new JPanel();
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//make the frame half the height and width
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int height = screenSize.height;
		int width = screenSize.width;
		frame.setSize(width/2, height/2);
		//frame.setSize(1200, 1200);
	    panel.setLayout(new FlowLayout());
	    Image icon = Toolkit.getDefaultToolkit().getImage("/home/shruti/Pictures/Pics/screen.jpg");
	    //frame.setIconImage(icon);
		// center the jframe on screen
		frame.setLocationRelativeTo(null);
		JLabel j = new JLabel();
		frame.setVisible(true);
		ImageIcon background = new ImageIcon("/home/shruti/Pictures/Pics/screen1.jpg");
		label = new JLabel();
		//label.setFont(new Font("Serif",Font.BOLD, 60));
		//label.setForeground(Color.RED);
		label.setBounds(0, 0, width, height);
		label.setIcon(background);
		l1 = new JLabel("SPLITSTER");
		l1.setFont(new Font("Serif",Font.BOLD, 80));
		l1.setForeground(Color.DARK_GRAY);
		panel.setLayout(new BorderLayout());
		btn1 = new JButton("Sign Up");
		btn2 = new JButton("Sign In");
		btn1.setBounds(400, 600, 200, 50);
		btn2.setBounds(700, 600, 200, 50);
		l1.setBounds(450, 350, 200, 30);
		
		btn1.addActionListener(this);
	    btn2.addActionListener(this);
	    btn1.setBackground(Color.lightGray);
	    btn1.setForeground(Color.BLACK);
	    btn2.setBackground(Color.lightGray);
	    btn2.setForeground(Color.BLACK);
	    btn1.setBorder(new LineBorder(Color.BLACK,2));
	    btn2.setBorder(new LineBorder(Color.BLACK,2));

	    
		l1.setHorizontalAlignment(SwingConstants.CENTER);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(btn1);
		panel.add(btn2);
		panel.add(label,BorderLayout.CENTER);
		panel.add(l1,BorderLayout.NORTH);
		panel.setBackground(Color.lightGray);
		frame.add(panel);
		frame.setIconImage(icon);
	}
	
	public void actionPerformed(ActionEvent e) 
    {
		if (e.getSource() == btn1)
        {
			User u = new User();
			u.AddUser();
        }
		else
		{
			new SignIn();
		}
    }
}

class  SignIn
{
	JTextField xField, yField;
	JTextField username, password;
	SignIn()
	  {
			Jdbc j = new Jdbc();
	      xField = new JTextField(5);
	      yField = new JTextField(5);
	      username = new JTextField();
	      password = new JPasswordField();
	      Object[] message = {
	                    "Username:", username,
	                    "Password:", password
	      				};
	      
	      int option = JOptionPane.showConfirmDialog(null, message, "LOGIN", JOptionPane.OK_CANCEL_OPTION);
	      if (option == JOptionPane.OK_OPTION) 
	      {
	    	  int flag=0;
	    	  try
	    	  {
	    		  String st1 = username.getText();
	    		  String st2 = password.getText();
	    		  Class.forName("com.mysql.jdbc.Driver"); //or JDBC DRIVER NAME string
	    		  j.con = DriverManager.getConnection(j.DB_URL,j.USER,j.PASS); // to open the connection
	    		  j.stmt = j.con.createStatement();
	    		  String sql1 = "call U1()";	//procedure call - select Username,Password from User
	    		  ResultSet rs = j.stmt.executeQuery(sql1);
	    		  
	    		  while(rs.next())
	    		  {
	    			  String t1 = rs.getString("Username");
	    			  String t2 = rs.getString("Password");
	    			  boolean e1 = t1.equals(st1);
	    			  boolean e2 = t2.equals(st2);
	    			  if(e1)
	    			  { 
	    				  if(e2){
	    					  flag=1;
	    					  //System.out.println("Login successful");
	    					  break;
	    				  }
	    				  else{
	    					  flag=2;
	    					  break;
	    				  }
	    			  }
	    			  else{
	    				  flag=0;
	    			  }
	    		  }
	    		  if(flag==1)
	    		  {  
	    			  int response = JOptionPane.showConfirmDialog(null, "Do you wish to continue?", "Message",JOptionPane.OK_CANCEL_OPTION);
	    			  if(response == JOptionPane.OK_OPTION)
	    				  new UserProfile(st1);	
	    			  else
	    				  new SignIn();
	    		  }
	    		  else if(flag==2)
	    		  {
	    			  JOptionPane.showMessageDialog(null, "Password Does Not Match");
	    			  new SignIn();			  
	    		  }
	    		  else 
	    		  {
	    			  JOptionPane.showMessageDialog(null, "User does'nt exit.", "Message",JOptionPane.OK_CANCEL_OPTION);
	    				        //JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)
	    			  new SignIn();
	    			  //System.out.println("Login failed");
	    		  }	  
	    	  }
	    	  
	          catch(SQLException se1){                      //Handle errors for JDBC
	                    se1.printStackTrace();
	          }catch(Exception ex){                            //Handle errors for Class.forName
	                   ex.printStackTrace();}
	          finally{ //finally block used to close resources
	        	  try{
	                      if(j.stmt!=null)
	                         j.stmt.close();
	              }
	        	  catch(SQLException se2){
	                   }// nothing we can do
	              try{
	                  if(j.con!=null)
	                	  j.con.close();
	              }
	              catch(SQLException se3)
	              {
	                  se3.printStackTrace();
	              }//d finally   
	          }
	      }
	      else 
	      {
	    	  clear();
	    	  System.out.println("Login canceled");
	      }
	  }
	void clear()
	{
		username.setText("");
        password.setText("");
	}
}


public class Splitster {
	public static void main(String args[])
	{
		MainScreen ms = new MainScreen();
	}
}

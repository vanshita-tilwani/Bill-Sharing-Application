import java.util.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.swing.*;
import javax.swing.border.LineBorder;

public class Group extends JFrame implements ActionListener{
	
		Group g;
		static String user;
		String gname;
		String gid=null;
		String st;
		int grp_id;
		String event_name;
		double sharing_amt;
		String admin_id;
		int no_of_members;
		JButton b1,b2;
		JButton btn1, btn2;
		JComboBox comboBox;
		JTextField tf1, tf2, tf3, tf4;
		
		Group(){
			this.grp_id = 0;
			this.event_name = null;
			this.sharing_amt = 0.00;
			this.admin_id = null;
			this.no_of_members = 0;
		}
		Group(int grp_id,String event_name,double sharing_amt,String admin_id,int no_of_members){
			this.grp_id = grp_id;
			this.event_name = event_name;
			this.sharing_amt = sharing_amt;
			this.admin_id = admin_id;
			this.no_of_members = no_of_members;
		}
		public int getGrp_id(){
			return grp_id;
		}
		public int getNo_of_members(){
			return no_of_members;
		}
		public String getEvent_name(){
			return event_name;
		}
		public double getSharing_amt(){
			return sharing_amt;
		}
		public String getAdmin_id(){
			return admin_id;
		}
		public void setGrp_id(int grp_id){
			this.grp_id = grp_id;
		}
		public void setEvent_name(String event_name){
			this.event_name = event_name;
		}
		public void setSharing_amt(double sharing_amt){
			this.sharing_amt = sharing_amt;
		}
		public void setAdmin_id(String admin_id){
			this.admin_id = admin_id;
		}
		public void setNo_of_members(int no_of_members){
			this.no_of_members = no_of_members;
		}
		
		void CreateGroup(String user){
			this.user=user;
			JLabel l1, l2, l3, l4, l5, s1, s2,s3;
				JFrame f = new JFrame("Group Information form");
				f.setVisible(true);
			    f.setSize(700, 700);
			    f.setLayout(null);
			    //f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			 
			    l1 = new JLabel("Add Group");
			    l1.setForeground(Color.blue);
			    l1.setFont(new Font("Serif", Font.BOLD, 28));
			 
			    l2 = new JLabel("Group Name:");
			    s1 = new JLabel("Your Group name must be between 3 and 10 characters.");
			    l3 = new JLabel("Group ID:");
			    s2 = new JLabel("Your Group ID must be between 3 and 10 characters.");
			    s3 = new JLabel(" numbers, hyphens (“-“) and underscores (“_”), but it must" +
			    		" contain at least one letter.");
			    l4 = new JLabel("Event Name:");
			    Event e = new Event();
			    e.eventnames();
			    Object[] o = e.arrl.toArray();
		        comboBox = new JComboBox(o);
		        comboBox.setEditable(true);
		        /* comboBox.addActionListener(new ActionListener() {

		            public void actionPerformed(ActionEvent e) {
		                // Do something when you select a value
		            	comboBox.getSelectedItem();
		            }
		        });*/
		        comboBox.addActionListener(this);

			    s1.setFont(new Font("Dialog", Font.ITALIC, 11));
			    s2.setFont(new Font("Dialog", Font.ITALIC, 11));
			    s3.setFont(new Font("Dialog", Font.ITALIC, 11));

				l5 = new JLabel("Group Admin's Name:"); 
			    tf1 = new JTextField();
			    tf2 = new JTextField();
			    //tf3 = new JTextField();
			    tf4 = new JTextField();
			   
			    btn1 = new JButton("OK");
			    btn2 = new JButton("Back");
			 
			    btn1.addActionListener(this);
			    btn2.addActionListener(this);
			 
			    l1.setBounds(450, 30, 400, 30);
			    l2.setBounds(100, 90, 200, 30);
			    s1.setBounds(100, 140, 600, 30);
			    l3.setBounds(100, 200, 200, 30);
			    s2.setBounds(100, 240, 600, 30);
			    s3.setBounds(100, 260, 600, 30);
			    l4.setBounds(100, 310, 200, 30);
			    l5.setBounds(100, 360, 200, 30);
			    tf1.setBounds(400, 90, 200, 30);
			    tf2.setBounds(400, 200, 200, 30);
			    comboBox.setBounds(400, 310, 200, 30);
			    tf4.setBounds(400, 360, 200, 30);
			    btn1.setBounds(200, 410, 100, 30);
			    btn2.setBounds(400, 410, 100, 30);
			 
			    f.add(l1);
			    f.add(l2);
			    f.add(tf1);
			    f.add(l3);
			    f.add(tf2);
			    f.add(l4);
			    f.add(s1);
			    f.add(s2);
			    f.add(s3);
			    f.add(comboBox);
			    f.add(l5);
			    f.add(tf4);
			    f.add(btn1);
			    f.add(btn2);
		}
		void gmembers(Object selection)
		{
			int d;
			gname = (String)selection;
			Jdbc j = new Jdbc();
			try
			{
				Class.forName("com.mysql.jdbc.Driver"); //or JDBC DRIVER NAME string
				j.con = DriverManager.getConnection(j.DB_URL,j.USER,j.PASS); // to open the connection
				j.stmt = j.con.createStatement();
				String sql3 = "select G_ID,MName,Desig,DOJ from GroupT where GName='"+gname+"'";
				ResultSet rs1 = j.stmt.executeQuery(sql3);
				JFrame jf = new JFrame(gname);
				JPanel p = new JPanel();
				d= 180;
				p.setLayout(new BorderLayout());
				jf.setVisible(true);
				jf.setSize(700, 700);
				//jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				JLabel la = new JLabel(new ImageIcon("/home/shruti/Pictures/Pics/mem1.jpg"));
				jf.setLocationRelativeTo(null);
				JLabel l1,l2,l3=null,l4,l5,l6,l7,l8;
				l3=new JLabel(gname);
				l3.setForeground(Color.DARK_GRAY);
				l3.setFont(new Font("Serif", Font.BOLD, 30));
				l3.setBounds(500, 50, 150, 30);
				l6=new JLabel("Members Name");
				l6.setForeground(Color.DARK_GRAY);
				l6.setFont(new Font("Serif", Font.BOLD, 20));
				l6.setBounds(280, 120, 180, 30);
				l7=new JLabel("Designation");
				l7.setForeground(Color.DARK_GRAY);
				l7.setFont(new Font("Serif", Font.BOLD, 20));
				l7.setBounds(500, 120, 180, 30);
				l8=new JLabel("Date of Joining");
				l8.setForeground(Color.DARK_GRAY);
				l8.setFont(new Font("Serif", Font.BOLD, 20));
				l8.setBounds(720, 120, 180, 30);
				p.add(l3);
				p.add(l6);
				p.add(l7);
				p.add(l8);
				while(rs1.next())
				{
					String st1 = rs1.getString("MName");
					String st2 = rs1.getString("Desig");
					gid = rs1.getString("G_ID");
					Date dj = rs1.getDate("DOJ");
					String st3 = dj.toString();
					l1=new JLabel(st1);
					l2=new JLabel(st2);
					l4=new JLabel(st3);
					l1.setForeground(Color.BLACK);
					l1.setFont(new Font("Serif", Font.ITALIC, 20));
					l2.setForeground(Color.BLACK);
					l2.setFont(new Font("Serif", Font.ITALIC, 20));
					l4.setForeground(Color.BLACK);
					l4.setFont(new Font("Serif", Font.ITALIC, 20));
					l1.setBounds(300, d, 180, 30);
					l2.setBounds(520, d, 180, 30);
					l4.setBounds(740, d, 180, 30);
					d=d+60;
					p.add(l1);
					p.add(l2);
					p.add(l4);
				}
				l5=new JLabel(gid);
				l5.setForeground(Color.DARK_GRAY);
				l5.setFont(new Font("Serif", Font.BOLD, 30));
				l5.setBounds(650, 50, 150, 30);
				p.add(l5);
				JLabel l = new JLabel("");
				b1 = new JButton("Add a member");
				b2 = new JButton("Cancel");
				b1.addActionListener(this);
				b2.addActionListener(this);
				b1.setBounds(400, d+30, 180, 30);
				b2.setBounds(630, d+30, 130, 30);
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
				j.con.close();
				jf.add(p);
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
		
		void AddMember()
		 {
			JTextField xField, yField;
			JTextField uname, amt;
			xField = new JTextField(5);
			yField = new JTextField(5);
			uname= new JTextField();
			amt = new JTextField();
			Object[] message = {
			                    "Username", uname,
			                    "Invested Amount", amt,
			      		};
			Jdbc j1 = new Jdbc();
			Jdbc j2 = new Jdbc();
			Jdbc j3 = new Jdbc();
			int option = JOptionPane.showConfirmDialog(null, message, "Add Member", JOptionPane.OK_CANCEL_OPTION);
			if (option == JOptionPane.OK_OPTION) 
			{
				
				try
		    	{
		    		boolean flag1=false,flag2=false;
		    		Class.forName("com.mysql.jdbc.Driver"); //or JDBC DRIVER NAME string
		    		j1.con = DriverManager.getConnection(j1.DB_URL,j1.USER,j1.PASS); // to open the connection
		    		Class.forName("com.mysql.jdbc.Driver"); //or JDBC DRIVER NAME string
		    		j2.con = DriverManager.getConnection(j2.DB_URL,j2.USER,j2.PASS); // to open the
		    		String st3 = uname.getText();
		    		String st1 = amt.getText();
		    		String sql1;
		    		j1.stmt = j1.con.createStatement();
      				sql1 = "SELECT MName FROM GroupT where G_ID ='"+gid+"'";
      				ResultSet rs1 = j1.stmt.executeQuery(sql1);
      				while(rs1.next())
      				{		//flag1=false;flag2=false;
         					String	nameG = rs1.getString("MName");
         					if(nameG.equals(st3))
         					{
         						flag1=true;
         						break;
         					}
      				}
      				if(flag1)
      				{
      					System.out.println("1");
      					JOptionPane.showMessageDialog(null, "Cannot Add Memeber:Member already exists.");
				    	AddMember();
      				}
      				else
      				{
      					System.out.println("user");
      					j2.stmt = j2.con.createStatement();
      					String sql = "call U1()";
      					ResultSet rs = j2.stmt.executeQuery(sql);
      					while(rs.next())
      					{
      						flag1=false;flag2=false;
      						String name = rs.getString("Username");
      						if(name.equals(st3))
      						{
      							flag2=true;
      							break;
      						}
      					}
      					if(flag2){
      						System.out.println("2");
      						String sq;
      						Class.forName("com.mysql.jdbc.Driver"); //or JDBC DRIVER NAME string
      			    		j3.con = DriverManager.getConnection(j3.DB_URL,j3.USER,j3.PASS); // t
      			    		j3.stmt = j3.con.createStatement();
      			    		Double d1 = Double.parseDouble(st1);
      			    		//td.getDate();
      	      				sq = "insert into GroupT(G_ID,GName,DOJ,MName,Invested_amt) values('"+gid+"','"+gname+"','2016-05-11','"+st3+"',"+d1+")";
      	      				j3.stmt.executeUpdate(sq);
      						//System.out.println("member added.");
      	      				JOptionPane.showMessageDialog(null,"Member added Successfully.");
      					}
						else
  						{
							System.out.println("no");
  	      					JOptionPane.showMessageDialog(null, "Cannot Add Memeber:User doesn't exists.");
  					    	AddMember();
  						}
					}
      				j1.con.close();
      				j2.con.close();
      				j2.con.close();
		    	}		
			    catch(SQLException se1){                      //Handle errors for JDBC
	                 se1.printStackTrace();
	            }catch(Exception ex){                            //Handle errors for Class.forName
	                 ex.printStackTrace();}
	            finally{ //finally block used to close resources
	                 try{
	                	 if(j1.stmt!=null)
	                         j1.stmt.close();
	                   }catch(SQLException se2){
	                }// nothing we can do
	                 try{
	                      if(j1.con!=null)
	                         j1.con.close();
	                }catch(SQLException se3){
	                      se3.printStackTrace();
	                }//end finally 
	            }
			    }			
		}
		
		boolean isAdmin()
		 {
			boolean flag=false;
			String des=null;
			Jdbc j = new Jdbc();
   			try{
					Class.forName("com.mysql.jdbc.Driver");
      				j.con = DriverManager.getConnection(j.DB_URL,j.USER,j.PASS);
      				j.stmt = j.con.createStatement();
      				String sql;
      				sql = "select Desig from GroupT where GName='"+gname+"' and MName='"+user+"'";
      				ResultSet rs = j.stmt.executeQuery(sql);
      				while(rs.next())
      				{
         					des = rs.getString("Desig");
      				}
      				if(des.equals("Admin")){
						//System.out.println("yes");
						flag=true;
					}
      				else{
      					flag= false;
      				}
      				j.con.close();
   				}catch(SQLException se1){                      //Handle errors for JDBC
   					se1.printStackTrace();
   					}catch(Exception e){                            //Handle errors for Class.forName
   					e.printStackTrace();}
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
      				}//end 
			}
   			return flag;
		}
		
		public void updategroup(String user)
		{
			this.user = user;
			Jdbc j = new Jdbc();
			try {
				Class.forName("com.mysql.jdbc.Driver");
				j.con = DriverManager.getConnection(j.DB_URL,j.USER,j.PASS);	
				j.stmt = j.con.createStatement();
				String str="select GName from GroupT where MName='"+user+"' group by G_ID";
				ResultSet rs = j.stmt.executeQuery(str);
				ArrayList<Object> arrl = new ArrayList<Object>();
				while (rs.next()) 
				{
				    	arrl.add(rs.getString("GName"));
				}
				Object[] o = arrl.toArray();
				JDialog.setDefaultLookAndFeelDecorated(true);
				String initialSelection = (String)arrl.get(0);
				Object selection = JOptionPane.showInputDialog(null, "Choose your Group",
				        "Group Name", JOptionPane.QUESTION_MESSAGE, null, o, initialSelection);
				//System.out.println("You have been added to the event " +selection);
				gmembers(selection);
				j.con.close();
			}catch(SQLException se1){                      //Handle errors for JDBC
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
		
		public void actionPerformed(ActionEvent e) 
	    {
			if(e.getSource() == b1)//add a member
			{
				
				if(isAdmin())
				{
					AddMember();
				}
				else
				{
					JOptionPane.showMessageDialog(null, "OOPs! You don't seem to be the Admin of the Group.");
				}
			}
			else if(e.getSource() == b2)// cancel button of group screen
			{
				this.dispose();
				new UserProfile(user);
			}	
			else if(e.getSource() == comboBox)
			{
				st = (String)comboBox.getSelectedItem();
			}
			else if(e.getSource() == btn1)	//ok button of group form (create group)
		    {
		    	Jdbc j = new Jdbc();
		        int x = 0;
		        String st1 = tf1.getText();
		        String st2 = tf2.getText();
		        //String st3 = tf3.getText();
		        String st4 = st;
		        try
		        {
		        	Class.forName("com.mysql.jdbc.Driver"); //or JDBC DRIVER NAME string
		        	j.con = DriverManager.getConnection(j.DB_URL,j.USER,j.PASS); // to open the connection
		            PreparedStatement ps = j.con.prepareStatement("insert into GroupT(G_ID,GName,EName,DOJ,MName,Desig) values(?,?,?,?,?,?)");
		            ps.setString(1, st2);
		               //System.out.println(st1);
		            ps.setString(2, st1);
		            ps.setString(3, st4);
		            ps.setString(4, "2016-04-20");
		            ps.setString(5, user);
		            ps.setString(6, "Admin");
		            ps.executeUpdate();
		            j.stmt = j.con.createStatement();
		    		String str="insert into Event values('"+st4+"')";
		    		j.stmt.executeUpdate(str);
		               x++;
		               if (x > 0) 
		               {
		            	   JOptionPane.showMessageDialog(btn1, "Data Saved Successfully");
		               }
		               else
		            	   JOptionPane.showMessageDialog(btn1, "All entries are required!");
		               j.con.close();
		           }
		              catch (Exception ex) 
		              {
		                   System.out.println(ex);
		              }
		          }      
		         else if(e.getSource() == btn2)		//back button of gform
		         {
		           tf1.setText("");
		           tf2.setText("");
		           
		           tf4.setText("");
		           
		       }

	    }
		
		public static void main(String args[])
		{
			Group g = new Group();
			g.CreateGroup(user);
		}
		
}
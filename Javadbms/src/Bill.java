import java.util.*;
//import javax.mail.internet.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

class R extends JFrame implements ActionListener{

	private JButton buttonOK = new JButton("OK");
	private JRadioButton indi = new JRadioButton("Individual");
	private JRadioButton grp = new JRadioButton("In a group");
	String selectedgrp;
	JTextField textField;
	
	public R(final String user) {
		super("Add a bill");

		ButtonGroup group = new ButtonGroup();
		group.add(indi);
		group.add(grp);
		//group.s
		indi.setSelected(true);
		setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.anchor = GridBagConstraints.CENTER;
		constraints.insets = new Insets(10, 10, 10, 10);

		add(indi, constraints);
		constraints.gridx = 1;
		add(grp, constraints);
		constraints.gridx = 2;

		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.gridwidth = 3;

		constraints.gridy = 2;
		add(buttonOK, constraints);

		buttonOK.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent event) {
				String selectedOption = "";
				if (indi.isSelected()) {
					selectedOption = "Individual";
				} else if (grp.isSelected()) {
					selectedOption = "Group";
				} 
				JOptionPane.showMessageDialog(R.this,"You selected: " + selectedOption);
				if(selectedOption.equals("Individual"))
				{
					JTextField xField = new JTextField(5);
					JTextField yField = new JTextField(5);
					JTextField zField = new JTextField(5);
					JTextField ln = new JTextField();
					JTextField bn = new JTextField();
					JTextField amt = new JTextField();
					boolean flag1=false,flag2=false,flag=false;
					Object[] message = {
			    		"Lender's Name:", ln,
			    		"Borrower's Name:",bn,
			    		"Amount:", amt,
					};
					Jdbc j = new Jdbc();
					int option = JOptionPane.showConfirmDialog(null, message, "Bill", JOptionPane.OK_CANCEL_OPTION);
					if (option == JOptionPane.OK_OPTION) {
						try {
							Class.forName("com.mysql.jdbc.Driver");
							j.con = DriverManager.getConnection(j.DB_URL,j.USER,j.PASS);	
							j.stmt = j.con.createStatement();
							String str="call U1()";
							ResultSet rs = j.stmt.executeQuery(str);
							String lname=ln.getText();
							String bname=bn.getText();
							String amt1=amt.getText();
							Double d=Double.parseDouble(amt1);
							while (rs.next()) 
							{
								String name = rs.getString("Username");
								//System.out.println(name);
								if(lname.equals(name)){
			    					flag1=true;
								}
								if(bname.equals(name)){
									flag2=true;
								}
								if(flag1&&flag2){
									flag=true;
									break;
								}
							}
							//System.out.println(flag);
							if(flag)
							{
								
								j.stmt = j.con.createStatement();
								String sql1 = "select Lender_Name,Receiver_Name from Friend where Lender_Name='"+lname+"' and Receiver_Name='"+bname+"'";
								ResultSet rs1 = j.stmt.executeQuery(sql1);
								String l=null,r=null;						
								while(rs1.next())
								{
									l = rs1.getString("Lender_Name");
									r = rs1.getString("Receiver_Name");
								}
								
								if(lname.equals(user) || bname.equals(user))
								{	
									System.out.println("hi");
									//System.out.println(l);
									//System.out.println(r);
									j.stmt = j.con.createStatement();
									String sq = "select Current_Balance from User where Username='"+lname+"'";
									ResultSet rs2 = j.stmt.executeQuery(sq);
									Double d1=0.0;
									while(rs2.next()){
										d1 = rs2.getDouble("Current_Balance");
									}
									if(l!=null && r!=null)
									{
										System.out.println("hi");
										
										System.out.println(d1);
										if(d1>=d)
										{
											//System.out.println("true");
											j.stmt = j.con.createStatement();
											String sql= "Update Friend set Amount = Amount+"+d+" where Lender_Name='"+lname+"' and Receiver_Name='"+bname+"'";
											//String sql= "insert into Friend values('"+lname+"','"+bname+"',"+d+")";
											j.stmt.executeUpdate(sql);
											j.stmt = j.con.createStatement();
											String sql3 = "update User set You_owe = You_owe+"+d+",Current_Balance=Current_Balance-"+d+" where Username='"+lname+"'";
											j.stmt.executeUpdate(sql3);
											j.stmt = j.con.createStatement();
											String sql2 = "update User set Owed = Owed+"+d+",Current_Balance=Current_Balance+"+d+" where Username='"+bname+"'";
											j.stmt.executeUpdate(sql2);
										}
										else{
											flag=false;
											JOptionPane.showMessageDialog(null, "Lender does'nt have enough balance.");
										}
			    					
									}
									else{
										if(d1>=d){
											j.stmt = j.con.createStatement();
											String sql= "call f1('"+lname+"','"+bname+"',"+d+")";
											//String sql= "insert into Friend values('"+lname+"','"+bname+"',"+d+")";
											j.stmt.executeUpdate(sql);
											j.stmt = j.con.createStatement();
											String sql3 = "update User set You_owe = You_owe+"+d+",Current_Balance=Current_Balance-"+d+" where Username='"+lname+"'";
											j.stmt.executeUpdate(sql3);
											j.stmt = j.con.createStatement();
											String sql2 = "update User set Owed = Owed+"+d+",Current_Balance=Current_Balance+"+d+" where Username='"+bname+"'";
											j.stmt.executeUpdate(sql2);
										}
										else{
											flag=false;
											JOptionPane.showMessageDialog(null, "Lender does'nt have enough balance.");
										}
									}	
								}
								else{
									flag=false;
									JOptionPane.showMessageDialog(null, "Only user is allowed to lend or borrow.");
								}
							}
							else
								JOptionPane.showMessageDialog(null, "OOPs!looks like either lender or borrower is not using Splitster.");
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
			
			    	if ((flag) && ((Integer.parseInt(amt.getText()))>0))  {
			    		JOptionPane.showMessageDialog(null, "Bill Added successfully");
			    	
			    	} else {
			    		JOptionPane.showMessageDialog(null, "Bill Addition failed.");
			    	}
			    	} else {
			    		JOptionPane.showMessageDialog(null, "Adding bill is cancelled");
			    	}
				}
				else
				{
					JFrame f = new JFrame("Add a bill to the group");
					JPanel labelPanel = new JPanel(new GridLayout(2, 1)); // 2 rows 1 column
			        f.add(labelPanel, BorderLayout.WEST);

			        // Panel for the fields
			        JPanel fieldPanel = new JPanel(new GridLayout(2, 1)); // 2 rows 1 column
			        f.add(fieldPanel, BorderLayout.CENTER);

			        // Combobox
			        JLabel labelCombo = new JLabel("Group Name");
			        // Options in the combobox
			        Jdbc j = new Jdbc();
			        ArrayList<Object> arrl = new ArrayList<Object>();
			        try {
					    Class.forName("com.mysql.jdbc.Driver");
						j.con = DriverManager.getConnection(j.DB_URL,j.USER,j.PASS);	
					    j.stmt = j.con.createStatement();
					    String str="select GName from GroupT where MName='"+user+"' group by GName";
					    ResultSet rs = j.stmt.executeQuery(str);
					    int i=0;
					    String en;
					    while (rs.next()) 
					    {
					    	String name = rs.getString("GName");
					    	//System.out.println(name);
					    	arrl.add(name);
					    }
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
			        //String[] options = { "Cash", "Cheque", "Online Transaction" };
			        Object[] o = arrl.toArray();
			        final JComboBox comboBox = new JComboBox(o);
			        comboBox.addActionListener(new ActionListener() {

			            public void actionPerformed(ActionEvent e) {
			                // Do something when you select a value
			            	selectedgrp=(String)comboBox.getSelectedItem();
			            	System.out.println(selectedgrp);
			            }
			        });

			        // Textfield
			     
			        JLabel labelTextField1 = new JLabel("Amount");
			        textField = new JTextField();
			        // Add labels
			        labelPanel.add(labelCombo);
			        //labelPanel.add(labelTextField1);
			        labelPanel.add(labelTextField1);
			        // Add fields
			        fieldPanel.add(comboBox);
			        //fieldPanel.add(textField1);
			        fieldPanel.add(textField);
			   
			        // Button submit
			        JButton ok = new JButton("OK");
			        
				    //f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				    //f.getContentPane().add(null, BorderLayout.NORTH);

				    // Panel with the button
				    JPanel p = new JPanel();
				    p.add(ok);
				    f.getContentPane().add(p, BorderLayout.SOUTH);

				        // Show the frame
				    f.pack();
				    f.setVisible(true);
				    f.setLocation(520, 300);
			        ok.addActionListener(new ActionListener() {
			            public void actionPerformed(ActionEvent e) {
			            	 
			            	setVisible(false);
			            	Jdbc j = new Jdbc();
			            	JOptionPane.showMessageDialog(null, textField.getText() + " has been added to the group " + selectedgrp);	
			            	try {
			            		
			            		Double d = Double.parseDouble(textField.getText());
					    		Class.forName("com.mysql.jdbc.Driver");
					    		j.con = DriverManager.getConnection(j.DB_URL,j.USER,j.PASS);
					    		j.stmt = j.con.createStatement();
					    		String str1="select Shared_amt from GroupT where GName ='"+selectedgrp+"' and MName='"+user+"'";
					    		ResultSet rs = j.stmt.executeQuery(str1);
					    		Double old=0.0;
					    		while(rs.next())
					    		{
					    			old=rs.getDouble("Shared_amt");
					    		}
					    		j.stmt = j.con.createStatement();
					    		String str="update GroupT set Invested_amt=Invested_amt+"+d+" where MName='"+user+"' and GName='"+selectedgrp+"'";
					    		j.stmt.executeUpdate(str);
					    		j.stmt = j.con.createStatement();
					    		String st4="select count(GName) as NUM,sum(Invested_amt) as SUM from GroupT where GName ='"+selectedgrp+"' group by G_ID";
					    		ResultSet rs2 = j.stmt.executeQuery(st4);
					    		
					    		String count=null;
					    		Double sum=0.0;
					    		while(rs2.next())
					    		{
					    			count=rs.getString("NUM");
					    			sum=rs.getDouble("SUM");
					    		}
					    		int i= Integer.parseInt(count);
					    		Double d1= sum/i;
					    		Double d2=d1-old;
					    		j.stmt = j.con.createStatement();
					    		String str2="update GroupT set Shared_amt="+d1+" where GName='"+selectedgrp+"'";
					    		j.stmt.executeUpdate(str2);	    	
					    		j.stmt = j.con.createStatement();
					    		String str3="update User set Current_Balance=Current_Balance-"+d+",You_owe=You_owe+"+d+" where Username='"+user+"'";
					    		j.stmt.executeUpdate(str3);	
					    		j.stmt = j.con.createStatement();
					    		String str4="update User set Owed=Owed+"+d2+" where User.Username=GroupT.MName and GName='"+selectedgrp+"'";
					    		j.stmt.executeUpdate(str4);	 
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
			        });		       
				}
			}
		});

		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub	
	}
}

class S extends JFrame implements ActionListener{

	private JButton buttonOK = new JButton("OK");
	private JRadioButton indi = new JRadioButton("Individual");
	private JRadioButton grp = new JRadioButton("In a group");

	public S(final String user) {
		super("Settle Up");

		ButtonGroup group = new ButtonGroup();
		group.add(indi);
		group.add(grp);

		indi.setSelected(true);
		setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.anchor = GridBagConstraints.CENTER;
		constraints.insets = new Insets(10, 10, 10, 10);

		add(indi, constraints);
		constraints.gridx = 1;
		add(grp, constraints);
		constraints.gridx = 2;

		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.gridwidth = 3;

		constraints.gridy = 2;
		add(buttonOK, constraints);

		buttonOK.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent event) {
				String selectedOption = "";
				if (indi.isSelected()) {
					selectedOption = "Individual";
				} else if (grp.isSelected()) {
					selectedOption = "Group";
				} 
				JOptionPane.showMessageDialog(S.this,"You selected: " + selectedOption);
				JTextField xField = new JTextField(5);
			    JTextField zField = new JTextField(5);
			    JTextField fn = new JTextField();
			    JTextField amt = new JTextField();
			    boolean flag1=false,flag2=false,flag=false;
			    Object[] message = {
			    		"Friend's Name:", fn,
			    		"Amount:", amt,
			    };
			    Jdbc j = new Jdbc();
			    int option = JOptionPane.showConfirmDialog(null, message, "Settle Up", JOptionPane.OK_CANCEL_OPTION);
				if (option == JOptionPane.OK_OPTION) {
			    	try {
			    		Class.forName("com.mysql.jdbc.Driver");
			    		j.con = DriverManager.getConnection(j.DB_URL,j.USER,j.PASS);	
			    		j.stmt = j.con.createStatement();
			    		String str="select Lender_Name,Amount from Friend where Receiver_Name='"+user+"'";
			    		ResultSet rs = j.stmt.executeQuery(str);
			    		String lname=fn.getText();			    		
			    		String amt1=amt.getText();
			    		Double d=Double.parseDouble(amt1);
			    		if(d>0){
			    			Double a=0.0,a1=0.0;
			    			while (rs.next()) 
			    			{
			    				String name = rs.getString("Lender_Name");
			    				a = rs.getDouble("Amount");
			    				if(lname.equals(name)){
			    					flag1=true;
			    					break;
			    				}
			    			}
			    			//System.out.println(flag);
			    			if(flag1)
			    			{
			    				if(a<d)
			    				{
			    					JOptionPane.showMessageDialog(null, "You are not allowed to settle up as amount entered is greater than the settle up amount.");
			    				}	
			    				else
			    				{
			    					j.stmt = j.con.createStatement();
			    					String sql= "select Current_Balance from User where Username='"+user+"'";
			    					ResultSet rs1 = j.stmt.executeQuery(sql);
			    					while (rs1.next()) 
			    					{
			    						a1 = rs1.getDouble("Current_Balance");
			    						if(a1>=d){
					    					flag2=true;
					    					break;
			    						}
			    					}
			    					if(flag2)
			    					{
			    						flag = true;	    					
			    						j.stmt = j.con.createStatement();
			    						String sql2 = "update User set You_owe = You_owe-"+d+",Current_Balance=Current_Balance+"+d+" where Username='"+lname+"'";
			    						j.stmt.executeUpdate(sql2);
			    						j.stmt = j.con.createStatement();
			    						String sql1 = "update User set Owed = Owed-"+d+",Current_Balance=Current_Balance-"+d+" where Username='"+user+"'";
			    						j.stmt.executeUpdate(sql1);
			    						j.stmt = j.con.createStatement();
			    						String sql3 = "update Friend set Amount = Amount-"+d+" where Lender_Name='"+lname+"' and Receiver_Name='"+user+"'";
			    						j.stmt.executeUpdate(sql3);
			    					}
			    					else
			    						JOptionPane.showMessageDialog(null, "Current balance is not enough to settle up the amount.");
			    				}
			    			}
			    			else
			    				JOptionPane.showMessageDialog(null, "User never borrow any amount from this friend.");
			    			}
			    			else
			    				JOptionPane.showMessageDialog(null, "Entered amount is negative.");
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
			
			    	if (flag)  {
			    		JOptionPane.showMessageDialog(null, "Settled up successfully");
			    	
			    	} else {
			    		JOptionPane.showMessageDialog(null, "Settle up process terminated with an error!");
			    	}
			    	} 
					else {//cancel button
			    		JOptionPane.showMessageDialog(null, "Settle Up process is cancelled");
			    	}
				}
			});

		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub	
	}
}
	
class BillForm extends JFrame implements ActionListener
{
	JLabel l1, l2, l3, l4;
	JTextField tf1, tf2, tf3;
	JButton btn1, btn2;
	BillForm()
	{
		JFrame f = new JFrame();
		JPanel p = new JPanel();
		JPanel p1 = new JPanel();
		f.pack();
		f.setVisible(true);
		f.setSize(400, 400);
		p1.setLayout(new BorderLayout());
		p.setLayout(new BorderLayout());
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setLocationRelativeTo(null);
		f.setTitle("Addition of Bills");
		JLabel la = new JLabel(new ImageIcon("/home/shruti/Pictures/bf6.jpg"));
		
		//p.setPreferredSize(new Dimension(1000, 750));
		//p1.setPreferredSize(new Dimension(400, 400));
		
	 	l1 = new JLabel("Add Your Bill");
		l1.setForeground(Color.DARK_GRAY);
		l1.setFont(new Font("Serif", Font.BOLD, 32));
	 	l2 = new JLabel("Lender's Name");
	 	l2.setForeground(Color.BLACK);
	    l2.setFont(new Font("Serif", Font.BOLD, 18));
	    l3 = new JLabel("No of members");
	    l3.setForeground(Color.BLACK);
	    l3.setFont(new Font("Serif", Font.BOLD, 18));
	   	l4 = new JLabel("Amount paid");
	   	l4.setForeground(Color.BLACK);
	    l4.setFont(new Font("Serif", Font.BOLD, 18));
	    tf1 = new JTextField();
	    tf2 = new JTextField();
	    tf3 = new JTextField();
	   	btn1 = new JButton("Submit");
	    btn2 = new JButton("Clear");
	    btn1.setBorder(new LineBorder(Color.BLACK,2));
	    btn1.setBackground(Color.white);
	    btn1.setForeground(Color.BLACK);
	    btn2.setBorder(new LineBorder(Color.BLACK,2));
	    btn2.setBackground(Color.white);
	    btn2.setForeground(Color.BLACK);
	    
	    l2.setBounds(400, 180, 200, 30);
	    l3.setBounds(400, 240, 200, 30);
	    l4.setBounds(400, 300, 200, 30);
	    l1.setBounds(550, 80, 250, 30);
	    tf1.setBounds(700, 180, 200, 30);
	    tf2.setBounds(700, 240, 200, 30);
	    tf3.setBounds(700, 300, 200, 30);
	    btn1.setBounds(500, 370, 130, 30);
	    btn2.setBounds(700, 370, 130, 30);
	    
	 	btn1.addActionListener(this);
	    btn2.addActionListener(this);
	    
	    p.add(l1);
	    p.add(l2);
	    p.add(tf1);
	    p.add(l3);
	    p.add(tf2);
	    p.add(l4);
	    p.add(tf3);
	    p.add(btn1);
	    p.add(btn2);
	    p.add(la);
	    //p1.add(p);
	    f.add(p);
	}
	public void actionPerformed(ActionEvent e) 
    {
		JLabel l1,l2;
		JTextField tf;
		JButton bt1, bt2;
		bt1 = new JButton("Submit");
		bt2 = new JButton("Cancel");	
		String str1=tf1.getText();
		String str2=tf2.getText();
		String str3=tf3.getText();
		boolean st1= str1.equals("");
		boolean st2= str2.equals("");
		boolean st3= str3.equals("");
		if( !st1 && !st2 && !st3)
		{
			if (e.getSource() == btn1)
			{
				super.dispose();
				int i=1,d= 180;
				String st;
				String nou=tf2.getText();
				int no=Integer.parseInt(nou);
				JFrame jf= new JFrame();
				JPanel p = new JPanel();
				p.setLayout(new BorderLayout());
				jf.setVisible(true);
				jf.setSize(700, 700);
				jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				JLabel la = new JLabel(new ImageIcon("/home/shruti/Pictures/mem1.jpg"));
				jf.setLocationRelativeTo(null);
				jf.setTitle("Addition of Members");
				l2=new JLabel("Add Your Members");
				l2.setForeground(Color.DARK_GRAY);
				l2.setFont(new Font("Serif", Font.BOLD, 30));
				l2.setBounds(470, 80, 400, 30);
				p.add(l2);
				while(i<=no)
				{
					st= "User "+ i;
					l1=new JLabel(st);
					l1.setForeground(Color.BLACK);
				    l1.setFont(new Font("Serif", Font.BOLD, 18));
					tf = new JTextField();
					l1.setBounds(400, d, 200, 30);
				    tf.setBounds(700, d, 200, 30);
					d=d+60;
					p.add(l1);
					p.add(tf);
					i++;
				}
				//bt1 = new JButton("Submit");
	    		//bt2 = new JButton("Cancel");	
				bt1.addActionListener(this);
	    		bt2.addActionListener(this);
	    		bt1.setBounds(500, d+30, 130, 35);
	    		bt2.setBounds(700, d+30, 130, 35);
	    		bt1.setBorder(new LineBorder(Color.BLACK,2));
	    	    bt1.setBackground(Color.LIGHT_GRAY);
	    	    bt1.setForeground(Color.BLACK);
	    	    bt2.setBorder(new LineBorder(Color.BLACK,2));
	    	    bt2.setBackground(Color.LIGHT_GRAY);
	    	    bt2.setForeground(Color.BLACK);
				p.add(bt1);
				p.add(bt2);
				p.add(la);
				jf.add(p);
		 	} 	
		 	else if(e.getSource() == btn2)
     		{	
          		tf1.setText("");
           		tf2.setText("");
           		tf3.setText("");
     		}	
			else if(e.getSource() == bt1)
			{
				JOptionPane.showMessageDialog(bt1, "Data Saved Successfully");
			}
			else 
			{
				JOptionPane.showMessageDialog(btn1, "Process Canceled");
			}
			
		}	
		else
		{
			JOptionPane.showMessageDialog(null,"Please enter the credentials");
		}
	}
}

class Bill extends JFrame implements ActionListener
{
	int splitting_amt;
	int no_of_users;
	String main;
	String arr[];
	String user;
	String date; //only 1 parametrized constructor since user need to submit all info therefore no empty constructor
	
	public int getSplitting_amt() {
		return splitting_amt;
	}
	public int getNo_of_users() {
		return no_of_users;
	}
	public String[] getArr() {
		return arr;
	}
	public String getDate() {
		return date;
	}
	public void setSplitting_amt(int splitting_amt) {
		this.splitting_amt = splitting_amt;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public void setNo_of_users(int no_of_users) {
		this.no_of_users = no_of_users;
	}
	public void setArr(String[] arr) {
		this.arr = arr;
	}
	
	void AddBill(String user)
	{
		final String u = user;
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new R(u).setVisible(true);
			}
		});
	}
	void amtSplit()
	{
		int n=this.splitting_amt/no_of_users;
		System.out.println("The amount that each member will pay to "+ this.main+" is "+ n);
	}
	boolean IsAmtValid()
	{
		if(splitting_amt>0 && splitting_amt< 100000)
			return true;
		else 
			return false;
	}
	/* in order to add the more amount between the  members .. created a method called update bill 
	 which will add extra amt to already present amt
	 */
	void updateBill()
	{
		System.out.println("Enter the amount");
		Scanner in = new Scanner(System.in);
		int amt= in.nextInt();
		amt= amt+this.splitting_amt;
		setSplitting_amt(amt);
		System.out.println("The bill is successfully updated");
		in.close();
	}
	
	void settleup(String user)
	{
		final String u = user;
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new S(u).setVisible(true);
			}
		});
	}
	
	public void main(String[] args) {
		
	}
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}


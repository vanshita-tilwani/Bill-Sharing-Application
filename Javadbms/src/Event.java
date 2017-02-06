import java.util.*;
import java.sql.*;


class Event_
{
	String name;
	public Event_()
	{
		name=null;
	}
	public Event_(String name) {
		
		this.name=name;
		
	}	
	public String getName() {
		return name;
	}	
	public void setName(String name) {
		this.name = name;
	}	
	public String toString()
	{
		return  "Name:" + name ;   
	}	
}

public class Event 
{
	ArrayList<Object> arrl;
	Event()
	{
		ArrayList<Object> arrl = null;
	}
	void eventnames()
	{
		Jdbc j = new Jdbc();
		arrl = new ArrayList<Object>();
        try {
		    Class.forName("com.mysql.jdbc.Driver");
			j.con = DriverManager.getConnection(j.DB_URL,j.USER,j.PASS);	
		    j.stmt = j.con.createStatement();
		    String str="select EName from Event";
		    ResultSet rs = j.stmt.executeQuery(str);
		    while (rs.next()) 
		    {
		    	String name = rs.getString("EName");
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
	}
	static void display(Event_ ev) throws Exception
	{
		
			}
	public static void main(String args[]) throws Exception 
	{
		   {
			  // Scanner sc=new Scanner(System.in);
			   
		  // try{
		      //STEP 2: Register JDBC driver
		      Class.forName("com.mysql.jdbc.Driver");	// or JDBC_DRIVER string

		      //STEP 3: Open a connection
		      System.out.println("Connecting to database...");
		      System.out.println("Creating statement...");
		      //stmt = conn.createStatement();
		      String sql;
		      sql = "select * from record.try";
		      String sql1;
		      Event_ ev1=new Event_("Birthday party");
			  Event_ ev2=new Event_("New year");
			  Event_ ev3=new Event_("Picnic");
			  Event_ ev4=new Event_("outing");
			  display(ev1);
			  display(ev2);
			  display(ev3);
			  display(ev4);
			 
		   
		   }	
		
	}
}



import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import java.sql.Connection;

public class Main {
	public static void main(String args[]) throws SQLException {
		System.out.println("This is a test for JDBC connection to a SSH secured DB server.");
		
		System.out.println("Remote username:");
        Scanner systemIn = new Scanner(System.in);
        String user = systemIn.next();
        System.out.println("Remote username:");
        String password = systemIn.next();
        System.out.println("Database username:");
        String dbuserName = systemIn.next();
        System.out.println("Database password:");
        String dbpassword = systemIn.next();
        System.out.println("Database schema");
        String schema = systemIn.next();
		
		
		int lport=5656;
	    String rhost="dbstud2.sis.uta.fi";
	    String host="shell.sis.uta.fi";
	    int rport=5432;
	    //String user=""; 																	// T�h�n tunnus mill� kirjaudut shell.sis.uta (peruspalvelutunnus)
	    //String password="";																// Yliopiston k�ytt�j�tilin salasana
	    //String dbuserName = "";															// dbstud2.sis.uta.fi k�ytt�j�, pit�is olla kans peruspalvelutunnus
        //String dbpassword = "";															// dbstud2.sis.uta.fi k�ytt�j�n salasana
        String url = "jdbc:postgresql://localhost:"+lport+"/" + dbuserName + "?currentSchema=" + schema;	// T�h�n vaihdat ton /ji4313?currentSchema="jdbc" sun tietokannaksi ja schemaksi
        //String driverName="org.postgresql.Driver";										// Jos noi referenced libraries kusee ku gitin takia niin lis��t
        Connection conn = null;																// vaan projektiin noi jsch.jar ja postgresql.jar sillee ku pistin kuvan
        Session session= null;
        systemIn.close();
	    try{
	    	//Set StrictHostKeyChecking property to no to avoid UnknownHostKey issue
	    	java.util.Properties config = new java.util.Properties(); 
	    	config.put("StrictHostKeyChecking", "no");
	    	JSch jsch = new JSch();
	    	session=jsch.getSession(user, host, 22);
	    	session.setPassword(password);
	    	session.setConfig(config);
	    	session.connect();
	    	System.out.println("Connected");
	    	int assinged_port=session.setPortForwardingL(lport, rhost, rport);
	        System.out.println("localhost:"+assinged_port+" -> "+rhost+":"+rport);
	    	System.out.println("Port Forwarded");
	    	
	    	//mysql database connectivity
            //Class.forName(driverName).newInstance();
            conn = DriverManager.getConnection (url, dbuserName, dbpassword);
            System.out.println ("Database connection established");
            System.out.println("DONE");
            Statement selectStmt = conn.createStatement();
            ResultSet rs = selectStmt.executeQuery("SELECT * FROM tilit");				// T�h�n SQL kysely mit� haluaa testata
            while (rs.next()) {
            	System.out.print(rs.getString(1) + "\t");								// N�it� lis�� jos kysely palauttaa enemm�n sarakkeita ku 2
            	System.out.println(rs.getString(2));
            }
	    }catch(Exception e){
	    	e.printStackTrace();
	    }finally{
	    	if(conn != null && !conn.isClosed()){
	    		System.out.println("Closing Database Connection");
	    		conn.close();
	    	}
	    	if(session !=null && session.isConnected()){
	    		System.out.println("Closing SSH Connection");
	    		session.disconnect();
	    	}
	    }
	}
}

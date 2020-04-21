package group32.tikoht.ssh;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

public class DatabaseConnection {

    public static Session getSSHConnection(String user, String password) {
        int lport=5656;
	    String rhost="dbstud2.sis.uta.fi";
	    String host="shell.sis.uta.fi";
	    int rport=5432;															   												
        Session session= null;

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
	    	return session;
	    }catch(Exception e){
	    	e.printStackTrace();
	    }
        return null;
    }
}
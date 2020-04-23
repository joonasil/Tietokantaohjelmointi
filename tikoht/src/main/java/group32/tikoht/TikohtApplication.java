package group32.tikoht;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import group32.tikoht.ssh.DatabaseConnection;
import javax.annotation.PreDestroy;
import com.jcraft.jsch.Session;

@SpringBootApplication
public class TikohtApplication {

	private static Session session = null;

	public static void main(String[] args) {
		if (args[0] != null && args[1] != null) {
			session = DatabaseConnection.getSSHConnection(args[0], args[1]);
		}
		else {
			System.out.println("CommandLine arguments are empty!");
		}

		if(session != null) {
			SpringApplication.run(TikohtApplication.class, args);	
		}
	}

	@PreDestroy
	public void onExit() {
		System.out.println("Spring Boot server closed, closing SSH connection!");
		session.disconnect();
	}

}

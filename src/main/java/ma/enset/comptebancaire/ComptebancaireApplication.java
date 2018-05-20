package ma.enset.comptebancaire;

import ma.enset.comptebancaire.dao.userRepository;
import ma.enset.comptebancaire.entities.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCrypt;

@SpringBootApplication
public class ComptebancaireApplication implements CommandLineRunner {
	@Autowired
	userRepository userr;

	public static void main(String[] args) {
		SpringApplication.run(ComptebancaireApplication.class, args);
	}
	public void insertUserExamples() throws Exception
	{
		String key = BCrypt.gensalt();
		user user1 = new user("admin",BCrypt.hashpw("admin", key),true);
		user user2 = new user("user",BCrypt.hashpw("user", key),true);
		user user3 = new user("hassan",BCrypt.hashpw("hassan", key),true);
		userr.save(user1);
		userr.save(user2);
		userr.save(user3);
	}

	@Override
	public void run(String... args) throws Exception {
		//insertUserExamples();
	}
}

package hr.fer.rznu.lab1.tweeter;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import hr.fer.rznu.lab1.tweeter.tweet.Tweet;
import hr.fer.rznu.lab1.tweeter.tweet.TweetRepository;
import hr.fer.rznu.lab1.tweeter.user.User;
import hr.fer.rznu.lab1.tweeter.user.UserRepository;

@Component
public class DataLoader implements ApplicationRunner {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private TweetRepository tweetRepository;
	
	public DataLoader(UserRepository userRepositor, TweetRepository tweetRepository) {
		this.userRepository = userRepositor;
		this.tweetRepository = tweetRepository;
	}

	@Override
	public void run(ApplicationArguments args) throws Exception { 
		userRepository.save(new User(1, "Ana", "Horvat", LocalDate.now()));
		userRepository.save(new User(2, "Pero", "Perić", LocalDate.now()));
//		userRepository.save(new User(3, "Ivo", "Ivić", LocalDate.now()));
//		userRepository.save(new User(4, "Marija", "Marić", LocalDate.now()));
//		userRepository.save(new User(5, "Luka", "Horvat", LocalDate.now()));
//		userRepository.save(new User(6, "Ana", "Tomić", LocalDate.now()));
//		userRepository.save(new User(7, "Ivana", "Novak", LocalDate.now()));
		
		tweetRepository.save(new Tweet(1, "Anin prvi tweet", new User(1)));
		tweetRepository.save(new Tweet(2, "Anin drugi tweet", new User(1)));
		tweetRepository.save(new Tweet(3, "Anin treći tweet", new User(1)));
		tweetRepository.save(new Tweet(4, "Perin prvi tweet", new User(2)));
		tweetRepository.save(new Tweet(5, "Perin drugi tweet", new User(2)));
		tweetRepository.save(new Tweet(6, "Perin treći tweet", new User(2)));
		
	}

}

package hr.fer.rznu.lab1.tweeter.tweet;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class TweetService {

	@Autowired
	private TweetRepository tweetRepository;

	public List<Tweet> getAllTweets() {
		List<Tweet> tweets = new LinkedList<>();
		tweetRepository.findAll().forEach(tweets::add);
		return tweets;
	}

	public List<Tweet> getAllTweetsOfUser(Integer userId) {
		if (userId == null)
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		return tweetRepository.findByUserId(userId);
	}

	public Tweet getTweet(Integer id) {
		if (id == null)
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

		Optional<Tweet> tweet = tweetRepository.findById(id);
		if (tweet.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		
		return tweet.get();
	}

	public void addTweet(Tweet tweet) {
		if (tweet == null || tweetRepository.existsById(tweet.getId())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		try {
			tweetRepository.save(tweet);
		} catch (Exception ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
	}

	public void updateTweet(Integer id, Tweet tweet) {
		if (id == null || id != tweet.getId() || tweet.getUser() == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		} else if(!tweetRepository.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		try {
			tweetRepository.save(tweet);
		} catch (Exception ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
	}

	public void deleteTweet(Integer id) {
		if (id == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		} else if(!tweetRepository.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}

		tweetRepository.deleteById(id);

	}

}

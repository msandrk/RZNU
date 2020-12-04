package hr.fer.rznu.lab1.tweeter.tweet;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		if(userId == null) return null;
		return tweetRepository.findByUserId(userId);
	}
	
	public Tweet getTweet(Integer id) {
		if(id == null) return null;
		Optional<Tweet> tweet = tweetRepository.findById(id);
		return tweet.isPresent() ? tweet.get() : null;
	}
	
	public void addTweet(Tweet tweet) {
		if(tweet == null || 
			tweetRepository.existsById(tweet.getId())) {
			return;
		}
		tweetRepository.save(tweet);
	}
	
	public void updateTweet(Integer id, Tweet tweet) {
		if(id == null || id != tweet.getId()
				|| !tweetRepository.existsById(id)) return;
		tweetRepository.save(tweet);
	}

	public void deleteTweet(Integer id) {
		if(id == null || !tweetRepository.existsById(id)) return;
		
		tweetRepository.deleteById(id);
	}


}

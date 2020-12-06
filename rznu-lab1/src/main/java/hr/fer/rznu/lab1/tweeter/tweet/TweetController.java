package hr.fer.rznu.lab1.tweeter.tweet;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TweetController {
	
	@Autowired
	private TweetService tweetService;
	
	@GetMapping("/tweets")
	public List<Tweet> getAllTweets(){
		return tweetService.getAllTweets();
	}
	
	@GetMapping("/users/{userId}/tweets")
	public List<Tweet> getAllTweetsOfUser(@PathVariable Integer userId){
		return tweetService.getAllTweetsOfUser(userId);
	}
	
	@GetMapping("/tweets/{tweetId}")
	public Tweet getTweet(@PathVariable Integer tweetId) {
		return tweetService.getTweet(tweetId);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/tweets")
	public void addTweet(@RequestBody Tweet tweet) {
		tweetService.addTweet(tweet);
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/tweets/{tweetId}")
	public void updateTweet(@RequestBody Tweet tweet, 
							@PathVariable Integer tweetId) {

		tweetService.updateTweet(tweetId, tweet);
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/tweets/{tweetId}")
	public void deleteTweet(@PathVariable Integer tweetId) {
		tweetService.deleteTweet(tweetId);
	}
}

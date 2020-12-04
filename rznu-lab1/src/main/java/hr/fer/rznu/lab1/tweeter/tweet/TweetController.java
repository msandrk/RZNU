package hr.fer.rznu.lab1.tweeter.tweet;

import java.util.List;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import hr.fer.rznu.lab1.tweeter.user.User;

@RestController
public class TweetController {
	
	@Autowired
	private TweetService tweetService;
	
	@RequestMapping("/tweets")
	public List<Tweet> getAllTweets(){
		return tweetService.getAllTweets();
	}
	
	@RequestMapping("/users/{userId}/tweets")
	public List<Tweet> getAllTweetsOfUser(@PathVariable Integer userId){
		return tweetService.getAllTweetsOfUser(userId);
	}
	
	@RequestMapping("/users/{userId}/tweets/{tweetId}")
	public Tweet getTweet(@PathVariable Integer tweetId) {
		return tweetService.getTweet(tweetId);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/users/{userId}/tweets")
	public void addTweet(@RequestBody Tweet tweet, @PathVariable Integer userId) {
		tweet.setUser(new User(userId));
		tweetService.addTweet(tweet);
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/users/{userId}/tweets/{tweetId}")
	public void updateTweet(@RequestBody Tweet tweet, @PathVariable Integer userId, 
							@PathVariable Integer tweetId) {

		tweet.setUser(new User(userId));
		tweetService.updateTweet(tweetId, tweet);
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/users/{userId}/tweets/{tweetId}")
	@OnDelete(action=OnDeleteAction.CASCADE)
	public void deleteTweet(@PathVariable Integer tweetId) {
		tweetService.deleteTweet(tweetId);
	}
}

package hr.fer.rznu.lab1.tweeter.tweet;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import hr.fer.rznu.lab1.tweeter.user.User;

@Entity
public class Tweet {
	
	@Id
	private Integer id;
	private String tweet;
	
	@ManyToOne
	private User user;
	
	public Tweet() {
		
	}

	public Tweet(Integer id, String tweet, User user) {
		super();
		this.id = id;
		this.tweet = tweet;
		this.user = user;
	}

	public String getTweet() {
		return tweet;
	}

	public void setTweet(String tweet) {
		this.tweet = tweet;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Integer getId() {
		return id;
	}		
}

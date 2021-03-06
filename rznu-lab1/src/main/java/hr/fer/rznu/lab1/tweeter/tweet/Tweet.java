package hr.fer.rznu.lab1.tweeter.tweet;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import hr.fer.rznu.lab1.tweeter.user.User;

@Entity
public class Tweet {
	
	@Id
	private Integer id;
	private String tweet;
	
	@ManyToOne
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnoreProperties({"firstName", "lastName", "dateOfBirth"})
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

	@Override
	public String toString() {
		return String.format("Tweet [id=%s, tweet=%s, user=%s]", id, tweet, user);
	}
	
	public String asJSONString() {
		return "{\"id\":" + id + ",\"tweet\":\"" + tweet + "\",\"user\":{\"id\":" + user.getId() + "}}";
	}
	
}

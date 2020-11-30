package hr.fer.rznu.lab1.tweeter.tweet;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface TweetRepository extends CrudRepository<Tweet, Integer> {
	
	List<Tweet> findByUserId(Integer userId);
}

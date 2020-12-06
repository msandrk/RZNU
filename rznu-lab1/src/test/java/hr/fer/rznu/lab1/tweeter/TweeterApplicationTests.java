package hr.fer.rznu.lab1.tweeter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;


import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import hr.fer.rznu.lab1.tweeter.tweet.Tweet;
import hr.fer.rznu.lab1.tweeter.tweet.TweetService;
import hr.fer.rznu.lab1.tweeter.user.User;
import hr.fer.rznu.lab1.tweeter.user.UserService;
import net.minidev.json.JSONObject;

@WebMvcTest
class TweeterApplicationTests {
	
	private User user1 = new User(1, "Ana", "Horvat", LocalDate.now());
	private User user2 = new User(2, "Pero", "Novak", LocalDate.now());
	private Tweet tweet1 = new Tweet(1, "Anin prvi tweet", user1);
	private Tweet tweet2 = new Tweet(2, "Anin drugi tweet", user1);
	private Tweet tweet3 = new Tweet(3, "Perin prvi tweet", user2);
	private Tweet tweet4 = new Tweet(4, "Perin drugi tweet", user2);

	@Autowired
	private MockMvc mvc;
		
	@MockBean
	private TweetService tweetService;
	
	@MockBean
	private UserService userService;
	
	@Test
	void contextLoads() {
	}
	
	
	///////////////////////////////////
	///////// User API tests /////////
    /////////////////////////////////
	
	@WithMockUser(value="admin")
	@Test
	public void addUserTest() throws Exception {
		mvc.perform(post("/users")
				.contentType(MediaType.APPLICATION_JSON)
				.content(user1.asJSONString()))
				.andExpect(status().isOk());
	}
	
	@WithMockUser(value="admin")
	@Test
	public void getUsersTest() throws Exception {
		List<User> users = new ArrayList<>();
		users.add(user1);
		users.add(user2);
		when(userService.getAllUsers()).thenReturn(users);
		mvc.perform(get("/users").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
		.andExpect(content().string(containsString("\"id\":1")))
		.andExpect(content().string(containsString("\"id\":2")));
	}
	
	@WithMockUser(value = "admin")
	@Test
	public void getUserWithIDTest() throws Exception {
		when(userService.getUser(1)).thenReturn(user1);
		mvc.perform(get("/users/1").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
		.andExpect(content().json(user1.asJSONString()));
	}
	
	@WithMockUser(value = "admin")
	@Test
	public void updateUser() throws Exception {
		User updatedUser1 = new User(user1.getId(), user1.getFirstName(), user2.getLastName(), user1.getDateOfBirth());
		mvc.perform(put("/users/1").contentType(MediaType.APPLICATION_JSON)
				.content(updatedUser1.asJSONString()))
				.andExpect(status().isOk());
	}
	
	public void deleteUser() throws Exception {
		mvc.perform(delete("/users/1")).andExpect(status().isOk());
	}
	
	///////////////////////////////////
	//////// Tweets API tests ////////
	/////////////////////////////////
	
	@WithMockUser(value = "admin")
	@Test
	public void getAllTweetsTest() throws Exception{
		List<Tweet> tweets = new ArrayList<Tweet>();
		tweets.add(tweet1);
		tweets.add(tweet2);
		tweets.add(tweet3);
		tweets.add(tweet4);
		when(tweetService.getAllTweets()).thenReturn(tweets);
		mvc.perform(get("/tweets").accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(content().string(containsString("Anin prvi tweet")))
		.andExpect(content().string(containsString("Anin drugi tweet")))
		.andExpect(content().string(containsString("Perin prvi tweet")))
		.andExpect(content().string(containsString("Perin drugi tweet")));
	}
	
	@WithMockUser(value = "admin")
	@Test
	public void getAllTweetsOfUserTest() throws Exception {
		List<Tweet> list = new ArrayList<Tweet>();
		list.add(tweet1);
		list.add(tweet2);
		when(tweetService.getAllTweetsOfUser(1)).thenReturn(list);
		mvc.perform(get("/users/1/tweets")).andExpect(status().isOk())
			.andExpect(content().string(containsString("Anin prvi tweet")))
			.andExpect(content().string(containsString("Anin drugi tweet")));
	}
	
	@WithMockUser(value = "admin")
	@Test
	public void getTweetTest() throws Exception {
		when(tweetService.getTweet(1)).thenReturn(tweet1);
		mvc.perform(get("/tweets/1")).andExpect(status().isOk())
			.andExpect(content().json(tweet1.asJSONString()));
	}
	
	@WithMockUser(value = "admin")
	@Test
	public void addTweetTest() throws Exception{
		mvc.perform(post("/tweets").contentType(MediaType.APPLICATION_JSON)
				.content(tweet2.asJSONString()))
				.andExpect(status().isOk());
	}
	
	@WithMockUser(value = "admin")
	@Test
	public void updateTweetTest() throws Exception {
		Tweet updatedTweet1 = new Tweet(tweet1.getId(), "Updated", tweet1.getUser());
		mvc.perform(put("/tweets/1").contentType(MediaType.APPLICATION_JSON)
				.content(updatedTweet1.asJSONString()))
				.andExpect(status().isOk());
	}
	
	@WithMockUser(value = "admin")
	@Test
	public void deleteTweet() throws Exception {
		mvc.perform(delete("/tweets/1")).andExpect(status().isOk());
	}

}

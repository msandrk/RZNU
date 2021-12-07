# Tweeter REST API

This is a RESTful API based on the [Twitter](https://twitter.com/) social media site implemented using [Spring Boot](https://spring.io/projects/spring-boot).
This API provides endpoints for accessing and managing [users](#users-api) and [tweets](#tweets-api).

This API uses [Basic Authentication](https://en.wikipedia.org/wiki/Basic_access_authentication). So when using it, `Authentication` header needs to be provided. User with following credentials will be authenticated successfully:
* username: `admin`
* password: `password`

## Users API
Every user in Tweeter app has following attributes:
* `id` *(Integer)* - unique id of a user
* `firstName` *(String)* - first (given) name of a user
* `lastName` *(String)* - last name (surname) of a user
* `dateOfBirth` *([LocalDate](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/time/LocalDate.html))* - date of birth of a user

There are following endpoints for accessing and managing users:
**`GET /users`**
* Returns a list of all of the registered users in a JSON format
* If there are no registered users returns an empty list

**`POST /users`**
* Creates a new user in the Tweeter application
* Body of the request must contain user's attributes in JSON format

**`GET /users/{id}`**
* Fetches the user with and id of `{id}`
* If there is no user with given id, code 404 Not Found is returned

**`PUT /users/{id}`**
* Endpoint for updating the info of a user with an id of `{id}`
* When trying to update attributes for a user with an id that is non-existing, code 404 Not Found is returned

**`DELETE /users/{id}`**
* Deletes the user with the given id of `{id}` from the database
* If the user had any tweets those are also deleted
* If there is no user with the given id of `{id}`, code 404 Not Found is returned

## Tweets API
Every tweet in the Tweeter application has following attributes:
* `id` *(Integer)* - a unique id of a tweet
* `tweet` *(String)* - content of this tweet
* `user` *([User](#users-api))* - users that is the owner of this tweet

There are following endpoints for accessing and managing tweets:
**`GET /tweets`**
* Returns a list of all the tweets in the Tweeter application in JSON format.
* If there are no tweets present, an empty list is returned.

**`POST /tweets`**
* Creates a new tweet.
* Body of the request must contain tweet's attributes in JSON format.
* Only an id on a user is needed when creating a new tweet

**`GET /tweets/{tweet_id}`**
* Fetches the tweet with an id of `tweet_id`.
* If a tweet with the given id does not exist, code 404 Not Found is returned.

**`PUT /tweets/{tweet_id}`**
* Endpoint for updating info of a tweet with and id of `{tweet_id}`.
* If tweet with given `{tweet_id}` is not found, code 404 Not Found is returned.

**`DELETE /tweets/{tweet_id}`**
* Deletes a tweet with and id of `{tweet_id}`.
* If tweet with the given `{tweet_id}` does not exist, 404 Not Found is returned.

**`GET /users/{user_id}/tweets`**
* Fetches a list of all of the tweets from the user with and id of `{user_id}`
* If the user with the given id has no tweets an empty list is returned.

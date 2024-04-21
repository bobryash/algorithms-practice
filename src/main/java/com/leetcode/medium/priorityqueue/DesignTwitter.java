package com.leetcode.medium.priorityqueue;

import java.util.*;

/**
 * #355. Design Twitter
 *
 * Design a simplified version of Twitter where users can post tweets,
 * follow/unfollow another user, and is able to see the 10 most recent tweets in the user's news feed.
 *
 * Implement the Twitter class:
 *
 * Twitter()
 * Initializes your twitter object.
 *
 * void postTweet(int userId, int tweetId)
 * Composes a new tweet with ID tweetId by the user userId.
 * Each call to this function will be made with a unique tweetId.
 *
 * List<Integer> getNewsFeed(int userId)
 * Retrieves the 10 most recent tweet IDs in the user's news feed.
 * Each item in the news feed must be posted by users who the user followed or by the user themself.
 * Tweets must be ordered from most recent to least recent.
 *
 * void follow(int followerId, int followeeId)
 * The user with ID followerId started following the user with ID followeeId.
 *
 * void unfollow(int followerId, int followeeId)
 * The user with ID followerId started unfollowing the user with ID followeeId.
 *
 * Example 1:
 *
 * Input
 * ["Twitter", "postTweet", "getNewsFeed", "follow", "postTweet", "getNewsFeed", "unfollow", "getNewsFeed"]
 * [[], [1, 5], [1], [1, 2], [2, 6], [1], [1, 2], [1]]
 * Output
 * [null, null, [5], null, null, [6, 5], null, [5]]
 *
 * Explanation
 * Twitter twitter = new Twitter();
 * twitter.postTweet(1, 5); // User 1 posts a new tweet (id = 5).
 * twitter.getNewsFeed(1);  // User 1's news feed should return a list with 1 tweet id -> [5]. return [5]
 * twitter.follow(1, 2);    // User 1 follows user 2.
 * twitter.postTweet(2, 6); // User 2 posts a new tweet (id = 6).
 * twitter.getNewsFeed(1);  // User 1's news feed should return a list with 2 tweet ids -> [6, 5]. Tweet id 6 should precede tweet id 5 because it is posted after tweet id 5.
 * twitter.unfollow(1, 2);  // User 1 unfollows user 2.
 * twitter.getNewsFeed(1);  // User 1's news feed should return a list with 1 tweet id -> [5], since user 1 is no longer following user 2.
 *
 */
public class DesignTwitter {

    public static void main(String[] args) {
        DesignTwitter twitter = new DesignTwitter();
        twitter.postTweet(1, 5);
        System.out.println(twitter.getNewsFeed(1)); // [5]
        twitter.follow(1, 2);
        twitter.postTweet(2, 6);
        System.out.println(twitter.getNewsFeed(1)); // [6, 5]
        twitter.unfollow(1, 2);
        System.out.println(twitter.getNewsFeed(1)); // [5]
    }

    private final Map<Integer, List<Tweet>> tweets;
    private final Map<Integer, Set<Integer>> follows;
    private int timestamp;
    private static final int NEWSFEED_LIMIT = 10;

    public DesignTwitter() {
        tweets = new HashMap<>();
        follows = new HashMap<>();
        timestamp = 0;
    }

    public void postTweet(int userId, int tweetId) {
        // might be her first tweet, so create a new list if needed
        List<Tweet> list = tweets.computeIfAbsent(userId, k -> new ArrayList<>());
        list.add(new Tweet(tweetId, timestamp));
        // each tweet will have a different timestamp, so we can know which is most recent
        timestamp++;
    }

    public void follow(int followerId, int followeeId) {
        // might be her first follow, so create a new set if needed
        Set<Integer> followeeSet = follows.computeIfAbsent(followerId, k -> new HashSet<>());
        followeeSet.add(followeeId);
    }

    public void unfollow(int followerId, int followeeId) {
        // no need to do check "&& follows.get(followerId).contains(followeeId)
        // because set.remove(v) will just return false, if it didn't contain that value
        if (follows.containsKey(followerId)) {
            follows.get(followerId).remove(followeeId);
        }
    }

    public List<Integer> getNewsFeed(int userId) {
        // will contain tweets from most recent ones to the oldest
        PriorityQueue<Tweet> pq = new PriorityQueue<>((a, b) -> b.timestamp - a.timestamp);
        // by problem description, newsfeed also contains user's tweets, so add them to pq
        if (tweets.containsKey(userId)) {
            tweets.get(userId).forEach(pq::offer);
        }

        // now add to pq followees tweets
        if (follows.containsKey(userId)) {
            Set<Integer> followees = follows.get(userId);
            for (int followeeId: followees) {
                List<Tweet> followeeTweets = tweets.get(followeeId);
                if (followeeTweets != null && !followeeTweets.isEmpty()) {
                    for (Tweet tweet: followeeTweets) {
                        pq.offer(tweet);
                    }
                }
            }
        }

        // now find and return 10 most recent tweet ids from pq
        List<Integer> result = new ArrayList<>();
        int count = 0;
        while (!pq.isEmpty() && count < NEWSFEED_LIMIT) {
            result.add(pq.poll().tweetId);
            count++;
        }

        return result;
    }

    class Tweet {
        int tweetId;
        int timestamp;

        Tweet(int tweetId, int timestamp) {
            this.tweetId = tweetId;
            this.timestamp = timestamp;
        }
    }
}

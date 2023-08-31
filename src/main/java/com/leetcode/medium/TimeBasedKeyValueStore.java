package com.leetcode.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * #981. Time Based Key-Value Store
 * <p>
 * Design a time-based key-value data structure that can store multiple values for the same key at different time stamps
 * and retrieve the key's value at a certain timestamp.
 * <p>
 * Implement the TimeMap class:
 * <p>
 * TimeMap() Initializes the object of the data structure.
 * void set(String key, String value, int timestamp) Stores the key key with the value value at the given time timestamp.
 * String get(String key, int timestamp) Returns a value such that set was called previously, with timestamp_prev <= timestamp.
 * If there are multiple such values, it returns the value associated with the largest timestamp_prev.
 * If there are no values, it returns "".
 * <p>
 * <p>
 * Example 1:
 * Input
 * ["TimeMap", "set", "get", "get", "set", "get", "get"]
 * [[], ["foo", "bar", 1], ["foo", 1], ["foo", 3], ["foo", "bar2", 4], ["foo", 4], ["foo", 5]]
 * Output
 * [null, null, "bar", "bar", null, "bar2", "bar2"]
 * <p>
 * Explanation
 * TimeMap timeMap = new TimeMap();
 * timeMap.set("foo", "bar", 1);  // store the key "foo" and value "bar" along with timestamp = 1.
 * timeMap.get("foo", 1);         // return "bar"
 * timeMap.get("foo", 3);         // return "bar", since there is no value corresponding to foo at timestamp 3 and timestamp 2, then the only value is at timestamp 1 is "bar".
 * timeMap.set("foo", "bar2", 4); // store the key "foo" and value "bar2" along with timestamp = 4.
 * timeMap.get("foo", 4);         // return "bar2"
 * timeMap.get("foo", 5);         // return "bar2"
 */
public class TimeBasedKeyValueStore {


    public static void main(String[] args) {
        TimeMap timeMap = new TimeMap();
        timeMap.set("foo", "bar", 1);  // store the key "foo" and value "bar" along with timestamp = 1.
        System.out.println(timeMap.get("foo", 1));         // return "bar"
        System.out.println(timeMap.get("foo", 3));         // return "bar", since there is no value corresponding to foo at timestamp 3 and timestamp 2, then the only value is at timestamp 1 is "bar".
        timeMap.set("foo", "bar2", 4); // store the key "foo" and value "bar2" along with timestamp = 4.
        System.out.println(timeMap.get("foo", 4));         // return "bar2"
        System.out.println(timeMap.get("foo", 5));         // return "bar2"
    }

    // (don't mind amount of code - no tricks, only simple logic)
    // the idea - all new entries have ascending timestamps.
    // so each list, associated with key, is ordered by timestamp by default
    // (because all new elements appended to the end of the list).
    // thus we can find timestamp which is equaled or closest to required (the goal of the task for get() method) with binary logN speed.
    static class TimeMap {

        Map<String, List<Pair>> map;

        public TimeMap() {
            map = new HashMap<>();
        }

        public void set(String key, String value, int timestamp) {
            if (!map.containsKey(key)) {
                List<Pair> list = new ArrayList<>();
                list.add(new Pair(value, timestamp));
                map.put(key, list);
            } else {
                map.get(key).add(new Pair(value, timestamp));
            }
        }

        public String get(String key, int timestamp) {
            String result = "";

            if (map.containsKey(key)) {
                List<Pair> list = map.get(key);

                int left = 0, right = list.size() - 1;
                while (left <= right) {
                    int mid = left + (right - left) / 2;
                    if (list.get(mid).timestamp <= timestamp) {
                        result = list.get(mid).value;
                        left = mid + 1;
                    } else {
                        right = mid - 1;
                    }
                }
            }

            return result;
        }

        static class Pair {
            Pair(String value, Integer timestamp) {
                this.value = value;
                this.timestamp = timestamp;
            }

            String value;
            Integer timestamp;
        }
    }
}

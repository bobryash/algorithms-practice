package com.leetcode.medium.priorityqueue;

import java.util.*;

/**
 * #621. Task Scheduler
 *
 * You are given an array of CPU tasks, each represented by letters A to Z,
 * and a cooling time, n. Each cycle or interval allows the completion of one task.
 * Tasks can be completed in any order, but there's a constraint: identical
 * tasks must be separated by at least n intervals due to cooling time.
 *
 * Return the minimum number of intervals required to complete all tasks.
 *
 *
 * Example 1:
 *
 * Input: tasks = ["A","A","A","B","B","B"], n = 2
 * Output: 8
 * Explanation: A possible sequence is: A -> B -> idle -> A -> B -> idle -> A -> B.
 * After completing task A, you must wait two cycles before doing A again.
 * The same applies to task B. In the 3rd interval, neither A nor B can be done, so you idle.
 * By the 4th cycle, you can do A again as 2 intervals have passed.
 *
 * Example 2:
 * Input: tasks = ["A","C","A","B","D","B"], n = 1
 * Output: 6
 * Explanation: A possible sequence is: A -> B -> C -> D -> A -> B.
 * With a cooling interval of 1, you can repeat a task after just one other task.
 *
 * Example 3:
 * Input: tasks = ["A","A","A", "B","B","B"], n = 3
 * Output: 10
 * Explanation: A possible sequence is: A -> B -> idle -> idle -> A -> B -> idle -> idle -> A -> B.
 * There are only two types of tasks, A and B, which need to be separated by 3 intervals.
 * This leads to idling twice between repetitions of these tasks.
 */
class TaskScheduler {
    public static void main(String[] args) {
        System.out.println(leastInterval(new char[]{'A', 'A', 'A', 'B', 'B', 'B'}, 2)); // 8
        System.out.println(leastInterval(new char[]{'A', 'C', 'A', 'B', 'D', 'B'}, 1)); // 6
        System.out.println(leastInterval(new char[]{'A', 'A', 'A', 'B', 'B', 'B'}, 3)); // 10

        System.out.println(leastInterval(new char[]{'A', 'A', 'A', 'B', 'B', 'C', 'C'}, 1)); // 7
    }

    // the idea - use priority queue and queue to manage tasks:
    // pq will define order - from most frequent to less (most optimal approach, see example below),
    // q will queue new tasks: after task is processed, count down frequency and put it in a q along with time,
    // when task will be available for processing again (= current time + cooldown)
    // when task is available again, put it back in pq again
    public static int leastInterval(char[] tasks, int n) {
        // find frequency for each task (A-3, B-2, C-2)
        Map<Character, Integer> freqMap = new HashMap<>();
        for (char c: tasks) {
            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
        }

        // put frequencies in pq with reverse order, from big to small: 3, 2, 2
        // you should start with most frequent ones, because you will have smaller ones to do when you idle
        // (if you exhaust small ones, you will have nothing to do in between doing frequent ones in the end):
        // CBCBAA_A_A - 9 times
        // ABCABCA - 7 times
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        // in pq we put counts, not chars. chars themselves are not needed anymore from here on now
        pq.addAll(freqMap.values());

        Queue<Pair> q = new LinkedList<>();
        int time = 0;
        while (!pq.isEmpty() || !q.isEmpty()) {
            time++;
            if (!pq.isEmpty()) {
                int newTaskFreq = pq.poll() - 1; // count down a task, we processed it
                if (newTaskFreq > 0) { // no need to put it back, if we processed every task
                    q.offer(new Pair(newTaskFreq, time + n)); // it will be available after cooldown
                }
            }

            if (!q.isEmpty()) {
                // cooldown is passed, task is ready.
                // since it's a queue, first added will be first, hence it will be the oldest one
                // and the one which is most probably ready to go
                if (q.peek().timeWhenAvailable == time) {
                    Pair readyToGoTask = q.poll();
                    pq.offer(readyToGoTask.taskCount); // put its frequency back in pq
                }
            }
        }

        return time;
    }

    // stores info about task's current frequency and time until it ready to be processed
    private static class Pair {
        int taskCount;
        int timeWhenAvailable;

        Pair(int taskCount, int timeWhenAvailable) {
            this.taskCount = taskCount;
            this.timeWhenAvailable = timeWhenAvailable;
        }
    }
}

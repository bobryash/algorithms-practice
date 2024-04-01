package com.leetcode.medium;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Stack;

/**
 * #853. Car Fleet
 * <p>
 * There are n cars going to the same destination along a one-lane road. The destination is target miles away.
 * <p>
 * You are given two integer array position and speed, both of length n, where position[i] is the position of the ith car and speed[i] is the speed of the ith car (in miles per hour).
 * <p>
 * A car can never pass another car ahead of it, but it can catch up to it and drive bumper to bumper at the same speed. The faster car will slow down to match the slower car's speed. The distance between these two cars is ignored (i.e., they are assumed to have the same position).
 * <p>
 * A car fleet is some non-empty set of cars driving at the same position and same speed. Note that a single car is also a car fleet.
 * <p>
 * If a car catches up to a car fleet right at the destination point, it will still be considered as one car fleet.
 * <p>
 * Return the number of car fleets that will arrive at the destination.
 * <p>
 * Example 1:
 * Input: target = 12, position = [10,8,0,5,3], speed = [2,4,1,1,3]
 * Output: 3
 * Explanation:
 * The cars starting at 10 (speed 2) and 8 (speed 4) become a fleet, meeting each other at 12.
 * The car starting at 0 does not catch up to any other car, so it is a fleet by itself.
 * The cars starting at 5 (speed 1) and 3 (speed 3) become a fleet, meeting each other at 6. The fleet moves at speed 1 until it reaches target.
 * Note that no other cars meet these fleets before the destination, so the answer is 3.
 * <p>
 * Example 2:
 * Input: target = 10, position = [3], speed = [3]
 * Output: 1
 * Explanation: There is only one car, hence there is only one fleet.
 * <p>
 * Example 3:
 * Input: target = 100, position = [0,2,4], speed = [4,2,1]
 * Output: 1
 * Explanation:
 * The cars starting at 0 (speed 4) and 2 (speed 2) become a fleet, meeting each other at 4. The fleet moves at speed 2.
 * Then, the fleet (speed 2) and the car starting at 4 (speed 1) become one fleet, meeting each other at 6. The fleet moves at speed 1 until it reaches target.
 */
public class CarFleet {
    public static void main(String[] args) {
        System.out.println(carFleet(12, new int[]{10, 8, 0, 5, 3}, new int[]{2, 4, 1, 1, 3})); // 3
        System.out.println(carFleet(10, new int[]{3}, new int[]{3})); // 1
        System.out.println(carFleet(100, new int[]{0, 2, 4}, new int[]{4, 2, 1})); // 1
    }

    // intuition:
    // position(speed): 0(1) 3(3) 5(1) 8(4) 10(2) ---> target (12)
    // time until car reaches the end (without any blocks): s/v = (target - position[i]) / speed[i]
    // if time of car 1 is less than a near car 2, then 1 will catch up with 2,
    // and by description they could be considered as one - 2 alone(slowest)
    //
    // the idea - to additionally keep cars in stack, and if we find there is a
    // faster one than the current, then pop the faster one, and leave only the slow one
    // (because only the speed of the slowest one will matter - one line road!)
    public static int carFleet(int target, int[] position, int[] speed) {
        Car[] cars = new Car[position.length];
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(position[i], (double) (target - position[i]) / speed[i]);
        }

        // from lesser to bigger (target position = 12!): (0, 12.0), (3, 3.0), (5, 7.0), (8, 1.0), (10, 1.0)
        Arrays.sort(cars, Comparator.comparingInt(v -> v.position));
        Stack<Car> stack = new Stack<>();

        // if current car's time (till the finish) is bigger than time of current on top of stack (let's call it previous),
        // it means that stack's car is going to catch up with current one (because stack's car it faster).
        // but from the description - it can't go around a slower car,
        // and it will follow it as one fleet, with slower car's speed.
        // so we consider it as one fleet, and we can pop up the fast car from the stack.
        for (Car car : cars) {
            // stack's car is faster and will catch up with current car, and they'll become a slow fleet
            while (!stack.isEmpty() && car.timeToFinish >= stack.peek().timeToFinish) {
                stack.pop();
            }
            stack.push(car);
        }

        return stack.size();
    }

    // test case example flow:
    // [(0, 12.0), (3, 3.0), (5, 7.0), (8, 1.0), (10, 1.0)] - sorted input (position, timeToFinish)
    // stack change:
    //[(0, 12.0)]
    //[(0, 12.0), (3, 3.0)]
    //[(0, 12.0), (5, 7.0)]
    //[(0, 12.0), (5, 7.0), (8, 1.0)]
    //[(0, 12.0), (5, 7.0), (10, 1.0)] - result
    static class Car {
        int position;
        double timeToFinish; // until it reaches the target (by itself)

        Car(int p, double t) {
            this.position = p;
            this.timeToFinish = t;
        }

        @Override
        public String toString() {
            return "(" + position + ", " + timeToFinish + ")";
        }
    }
}


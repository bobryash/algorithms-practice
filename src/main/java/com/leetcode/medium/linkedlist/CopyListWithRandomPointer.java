package com.leetcode.medium.linkedlist;

import com.util.ListNode;

import java.util.HashMap;
import java.util.Map;

/**
 * #138. Copy List with Random Pointer
 *
 * A linked list of length n is given such that each node contains an additional random pointer,
 * which could point to any node in the list, or null.
 *
 * Construct a deep copy of the list. The deep copy should consist of exactly n brand new nodes,
 * where each new node has its value set to the value of its corresponding original node.
 * Both the next and random pointer of the new nodes should point to new nodes in the copied
 * list such that the pointers in the original list and copied list represent the same list state.
 * None of the pointers in the new list should point to nodes in the original list.
 *
 * For example, if there are two nodes X and Y in the original list, where X.random --> Y,
 * then for the corresponding two nodes x and y in the copied list, x.random --> y.
 *
 * Return the head of the copied linked list.
 *
 * The linked list is represented in the input/output as a list of n nodes.
 * Each node is represented as a pair of [val, random_index] where:
 *
 * val: an integer representing Node.val
 * random_index: the index of the node (range from 0 to n-1) that the random pointer points to,
 * or null if it does not point to any node.
 * Your code will only be given the head of the original linked list.
 *
 * Example 1:
 * Input: head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
 * Output: [[7,null],[13,0],[11,4],[10,2],[1,0]]
 *
 * Example 2:
 * Input: head = [[1,1],[2,1]]
 * Output: [[1,1],[2,1]]
 *
 * Example 3:
 * Input: head = [[3,null],[3,0],[3,null]]
 * Output: [[3,null],[3,0],[3,null]]
 */
public class CopyListWithRandomPointer {

    public static void main(String[] args) {
        // System.out.println(copyRandomList(new ListNode()));
    }

    // problem description is huge, but long story short:
    // create a deep copy of OG list, where every node is new and all
    // its pointers also point to new nodes.
    public static ListNode copyRandomList(ListNode head) {
        Map<ListNode, ListNode> oldToCopy = new HashMap<>();
        ListNode cur = head;

        // create new nodes as per problem requirements
        // and link them with old ones by map, along the way
        while (cur != null) {
            oldToCopy.put(cur, new ListNode(cur.val));
            cur = cur.next;
        }

        cur = head;
        // can't do everything in one cycle, because refs should point to new links only.
        // in first cycle above we just created them.
        while(cur != null) {
            oldToCopy.get(cur).next = oldToCopy.get(cur.next);
            oldToCopy.get(cur).random = oldToCopy.get(cur.random);
            cur = cur.next;
        }

        return oldToCopy.get(head);
    }
}

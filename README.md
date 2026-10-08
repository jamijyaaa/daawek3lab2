# Week 3 — Data Structures and Algorithms

## Introduction

For this week's assignment, I solved two linked list problems from LeetCode:

1. Merge Two Sorted Lists
2. Linked List Cycle

For both problems, I first tried to use a simple approach that I could understand and explain myself. After that, I analyzed the time and space complexity and thought about how the solutions could be improved.

---

# 1. Merge Two Sorted Lists

## Problem

The problem gives us two linked lists. Both lists are already sorted in increasing order.

We need to combine them into one sorted linked list.

For example:

```text
List 1: 1 -> 2 -> 4
List 2: 1 -> 3 -> 4

Result: 1 -> 1 -> 2 -> 3 -> 4 -> 4
```

The important part is that the input lists are already sorted.

## Approach

For my solution, I decided to use a simple brute-force approach.

First, I go through the first linked list and put all its values into an `ArrayList`. Then I do the same with the second linked list.

After that, I sort the `ArrayList` using Java's `Collections.sort()`.

Finally, I create a new linked list using the values from the sorted array.

The steps are:

1. Traverse the first list.
2. Add all values to an `ArrayList`.
3. Traverse the second list.
4. Add its values to the same `ArrayList`.
5. Sort the array.
6. Create a new linked list from the sorted values.
7. Return the new list.

## Tracing

Let's use this example:

```text
list1 = 1 -> 2 -> 4
list2 = 1 -> 3 -> 4
```

At the beginning:

```text
values = []
```

I start with the first list.

After reading `1`:

```text
values = [1]
```

After reading `2`:

```text
values = [1, 2]
```

After reading `4`:

```text
values = [1, 2, 4]
```

Then I move to the second list.

After reading `1`:

```text
values = [1, 2, 4, 1]
```

After reading `3`:

```text
values = [1, 2, 4, 1, 3]
```

After reading `4`:

```text
values = [1, 2, 4, 1, 3, 4]
```

Now I sort the array:

```text
[1, 1, 2, 3, 4, 4]
```

Then I create a new linked list:

```text
1 -> 1 -> 2 -> 3 -> 4 -> 4
```

So the final result is correct.

## Time Complexity

**Time Complexity: O((n + m) log(n + m))**

Here:

* `n` is the number of nodes in the first list.
* `m` is the number of nodes in the second list.

First, I visit every node in both lists:

```text
O(n + m)
```

Then I sort all `n + m` values.

Sorting takes:

```text
O((n + m) log(n + m))
```

Finally, I create the new linked list, which takes:

```text
O(n + m)
```

The sorting operation takes the most time, so the final complexity is:

```text
O((n + m) log(n + m))
```

## Space Complexity

**Space Complexity: O(n + m)**

I store all values from both lists inside an `ArrayList`.

There are `n + m` values in total, so the array requires:

```text
O(n + m)
```

additional space.

I also create a new linked list containing all the values.

## Reflection / Improvement

I think this solution is easy to understand, but it is not the most efficient solution.

The main problem is that I sort all the values even though both input lists are already sorted.

A better solution would use two pointers.

One pointer would point to the current node of the first list and another pointer would point to the current node of the second list.

I could compare their values and put the smaller node into the result. Then I would move that pointer forward.

For example:

```text
list1: 1 -> 2 -> 4
        ^
list2: 1 -> 3 -> 4
        ^
```

I compare `1` and `1`, choose one of them, and move its pointer.

Then I continue comparing the current values until one of the lists is finished.

The improved solution would have:

```text
Time Complexity: O(n + m)
Space Complexity: O(1)
```

So it is better than my current solution because it does not need to sort all values and it can reuse the existing linked list nodes.

---

# 2. Linked List Cycle

## Problem

In this problem, we are given the head of a linked list.

Normally, a linked list eventually ends with `null`.

However, a linked list can also contain a cycle. This happens when a node points back to a previous node.

For example:

```text
1 -> 2 -> 3 -> 4
          ^    |
          |____|
```

Here, node `4` points back to node `3`, so the list will never reach `null`.

The goal is to determine whether the linked list contains a cycle.

The answer should be:

```text
true
```

if there is a cycle, and:

```text
false
```

if there is no cycle.

## Approach

For my solution, I decided to use a `HashSet`.

The idea is to remember every node that I have already visited.

When I visit a node, I first check if it is already inside the `HashSet`.

If it is already there, it means that I have reached the same node again, so there must be a cycle.

If it is not there, I add it to the set and continue to the next node.

The steps are:

1. Start from the head.
2. Check if the current node is already in the set.
3. If it is already there, return `true`.
4. If it is not there, add it to the set.
5. Move to the next node.
6. If the current node becomes `null`, return `false`.

## Tracing

Let's consider this linked list:

```text
1 -> 2 -> 3 -> 4
          ^    |
          |____|
```

At the beginning:

```text
visited = {}
current = 1
```

I visit node `1`.

It is not in the set, so I add it:

```text
visited = {1}
current = 2
```

Then I visit node `2`:

```text
visited = {1, 2}
current = 3
```

Then node `3`:

```text
visited = {1, 2, 3}
current = 4
```

Then node `4`:

```text
visited = {1, 2, 3, 4}
current = 3
```

Now I reach node `3` again.

I check the set:

```text
3 is already in visited
```

Therefore, I know that there is a cycle.

The method returns:

```text
true
```

### Example without a cycle

Suppose we have:

```text
1 -> 2 -> 3 -> null
```

The algorithm visits:

```text
1
2
3
```

and then:

```text
current = null
```

Since we reached `null`, there is no cycle, so the method returns:

```text
false
```

## Time Complexity

**Time Complexity: O(n)**

Here, `n` is the number of nodes that we visit.

In the worst case, we visit every node once.

Checking whether a node exists in a Java `HashSet` takes `O(1)` time on average.

Therefore, the total time complexity is:

```text
O(n)
```

## Space Complexity

**Space Complexity: O(n)**

I store the visited nodes inside the `HashSet`.

In the worst case, the set can contain all `n` nodes.

Therefore, the additional space is:

```text
O(n)
```

## Reflection / Improvement

There is a better solution in terms of memory usage.

The standard approach is called **Floyd's Cycle Detection Algorithm**, or the **slow and fast pointer method**.

The idea is to use two pointers:

* `slow` moves one node at a time.
* `fast` moves two nodes at a time.

If there is a cycle, eventually the two pointers will meet.

If there is no cycle, the fast pointer will reach `null`.

The improved solution would have:

```text
Time Complexity: O(n)
Space Complexity: O(1)
```

The time complexity is the same as my solution, but the space complexity is better.

My solution uses:

```text
O(n)
```

extra memory for the `HashSet`.

The slow and fast pointer solution only needs two variables, so it uses:

```text
O(1)
```

extra memory.

---

# Conclusion

For both problems, I started with solutions that were relatively simple to understand.

For `Merge Two Sorted Lists`, I used an `ArrayList` and sorting. This made the solution easier for me to implement, but the complexity is:

```text
Time: O((n + m) log(n + m))
Space: O(n + m)
```

For `Linked List Cycle`, I used a `HashSet` to remember visited nodes:

```text
Time: O(n)
Space: O(n)
```

After analyzing the solutions, I found that both can be improved.

For `Merge Two Sorted Lists`, the sorted property of the input lists can be used directly to get:

```text
O(n + m)
```

time.

For `Linked List Cycle`, Floyd's algorithm can reduce the extra space from:

```text
O(n)
```

to:

```text
O(1)
```

I think the main thing I learned from these problems is that a solution does not have to be optimal from the beginning. It is also important to understand why my first solution works, where it spends time and memory, and what can be improved.

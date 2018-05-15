#ifndef LEETCODEEASYSTACK
#define LEETCODEEASYSTACK

#include <vector>
#include <stack>
#include <queue>

using namespace std;

//155. Min Stack
class MinStack {
public:
    stack<long> s;
    long m;

    /** initialize your data structure here. */
    MinStack() {

    }

    void push(int x) {
        if (s.empty()) {
            s.push(0);
            m = x;
        } else {
            s.push((long)x - m);
            if (x < m)
                m = x;
        }
    }

    void pop() {
        long t = s.top();
        s.pop();
        if (t < 0)
            m -= t;
    }

    int top() {
        if (s.top() <= 0)
            return m;
        return s.top() + m;
    }

    int getMin() {
        return m;
    }
};

//225. Implement Stack using Queues
class MyStack {
    queue<int> q;

public:
    /** Initialize your data structure here. */
    MyStack() {

    }

    /** Push element x onto stack. */
    void push(int x) {
        q.push(x);
        for (int i=1; i<q.size(); i++) {
            q.push(q.front());
            q.pop();
        }
    }

    /** Removes the element on top of the stack and returns that element. */
    int pop() {
        int x = top();
        q.pop();
        return x;
    }

    /** Get the top element. */
    int top() {
        q.front();
    }

    /** Returns whether the stack is empty. */
    bool empty() {
        return q.empty();
    }
};

//232. Implement Queue using Stacks
class MyQueue {
    stack<int> s;
    stack<int> tmp;

public:
    /** Initialize your data structure here. */
    MyQueue() {

    }

    /** Push element x to the back of queue. */
    void push(int x) {
        s.push(x);
    }

    /** Removes the element from in front of queue and returns that element. */
    int pop() {
        while (!s.empty()) {
            int x = s.top();
            s.pop();
            tmp.push(x);
        }
        int res = tmp.top();
        tmp.pop();
        while (!tmp.empty()) {
            int x = tmp.top();
            tmp.pop();
            s.push(x);
        }
        return res;
    }

    /** Get the front element. */
    int peek() {
        while (!s.empty()) {
            int x = s.top();
            s.pop();
            tmp.push(x);
        }
        int res = tmp.top();
        while (!tmp.empty()) {
            int x = tmp.top();
            tmp.pop();
            s.push(x);
        }
        return res;
    }

    /** Returns whether the queue is empty. */
    bool empty() {
        return s.empty();
    }
};

//496. Next Greater Element I
vector<int> nextGreaterElement(vector<int>& findNums, vector<int>& nums)
{
    vector<int> res;

    for (int i = 0; i < findNums.size(); ++i) {
        int tmp = -1;
        int idx = -1;
        for (int j = 0; j < nums.size(); ++j) {
            if (findNums[i] == nums[j])
                idx = j;
            else if (idx != -1 && findNums[i] < nums[j]) {
                tmp = nums[j];
                break;
            }
        }
        res.push_back(tmp);
    }

    return res;
}

//682. Baseball Game
int getInteger(string s)
{
    int a;
    stringstream ss;
    ss << s;
    ss >> a;
    return a;
}

int calPoints(vector<string>& ops)
{
    stack<int> s;
    int a, b;
    int res = 0;
    for (auto op : ops) {
        if (s.empty())
            s.push(getInteger(op));
        else {
            if (op == "+") {
                a = s.top();
                s.pop();
                b = s.top();
                s.push(a);
                s.push(a + b);
            } else if (op == "C") {
                s.pop();
            } else if (op == "D") {
                a = s.top();
                s.push(a * 2);
            } else {
                s.push(getInteger(op));
            }
        }
    }

    while (!s.empty()) {
        res += s.top();
        s.pop();
    }

    return res;
}

#endif // LEETCODEEASYSTACK


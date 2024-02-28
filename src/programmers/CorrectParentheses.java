package programmers;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class CorrectParentheses {
    public static void main(String[] args) {

    }
    public static boolean solution(String s) {
        char[] cArray = s.toCharArray();
        Stack<Integer> stack = new Stack<>();

        for (char c : cArray){
            if(c == '('){
                stack.push(1);
                continue;
            }
            if(c == ')'){
                if(stack.empty()){
                    return false;
                }
                stack.pop();
            }
        }

        if(!stack.empty()){
            return false;
        }

        return true;
    }
}
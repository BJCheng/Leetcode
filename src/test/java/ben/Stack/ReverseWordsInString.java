package ben.Stack;

import org.junit.Assert;
import org.junit.Test;

import java.util.Stack;

public class ReverseWordsInString {
    public String reverseWords(String s) {
        s = s.trim();
        String[] ary = s.split(" ");
        Stack<String> stack = new Stack<>();
        for(int i=0; i<ary.length; i++) {
            if(" ".equals(ary[i])) {
                continue;
            }
            stack.push(ary[i]);
        }
        StringBuilder result = new StringBuilder();
        while(!stack.isEmpty()) {
            result.append(stack.pop());
            if(!stack.isEmpty()) {
                result.append(" ");
            }
        }
        return result.toString();
    }

    @Test
    public void test() {
        Assert.assertEquals("", new ReverseWordsInString().reverseWords("a good   example"));
    }
}

package ben.Stack;

import org.junit.Assert;
import org.junit.Test;

import java.util.Stack;

public class BasicCalculatorIII {
    public int calculate(String s) {
        Stack<String> operatorStack = new Stack<>();
        Stack<Integer> numberStack = new Stack<>();
        int left = 0;
        int right = 0;
        while(right < s.length()) {
            while(right < s.length() && 0 <= s.charAt(right)-'0' && s.charAt(right)-'0' < 10) {
                right++;
            }
            if(right > left) {
                numberStack.push(Integer.valueOf(s.substring(left, right)));
                processTimesOrDivides(operatorStack, numberStack);
            }
            // has to br break after process times or divides
            if(right == s.length()) {
                break;
            }
            String operator = s.substring(right, right+1);
            switch(operator) {
                case "(":
                    operatorStack.add("(");
                    break;
                case ")":
                    processPlusOrMinus(operatorStack, numberStack);
                    operatorStack.pop(); // pop "("
                    processTimesOrDivides(operatorStack, numberStack);
                    break;
                case "+":
                case "-":
                    processPlusOrMinus(operatorStack, numberStack);
                    operatorStack.push(operator);
                    break;
                // * or /
                default:
                    operatorStack.push(operator);
                    break;
            }
            right += 1;
            left = right;
        }
        while(!operatorStack.isEmpty()) {
            processPlusOrMinus(operatorStack, numberStack);
        }
        return numberStack.pop();
    }

    void processPlusOrMinus(Stack<String> operatorStack, Stack<Integer> numberStack) {
        if(numberStack.size() >= 2) {
            if ("+".equals(operatorStack.peek())) {
                numberStack.push(numberStack.pop() + numberStack.pop());
                operatorStack.pop();
            } else if ("-".equals(operatorStack.peek())) {
                int secondNumber = numberStack.pop();
                int firstNumber = numberStack.pop();
                numberStack.push(firstNumber - secondNumber);
                operatorStack.pop();
            }
        }
    }

    void processTimesOrDivides(Stack<String> operatorStack, Stack<Integer> numberStack) {
        if(numberStack.size() >= 2) {
            if("*".equals(operatorStack.peek())) {
                numberStack.push(numberStack.pop() * numberStack.pop());
                operatorStack.pop();
            } else if("/".equals(operatorStack.peek())) {
                int secondNumber = numberStack.pop();
                int firstNumber = numberStack.pop();
                numberStack.push(firstNumber / secondNumber);
                operatorStack.pop();
            }
        }
    }

    @Test
    public void test() {
        Assert.assertEquals(2, new BasicCalculatorIII().calculate("1+1"));
        Assert.assertEquals(4, new BasicCalculatorIII().calculate("6-4/2"));
        Assert.assertEquals(6, new BasicCalculatorIII().calculate("2*1+(5+5*2)/3+(1+2*6/2-8)"));
        Assert.assertEquals(28, new BasicCalculatorIII().calculate("2*(5+5*12)/13+(116/12+8)+1+22*3*(31+2+10/32)/(13*222*3-22)"));
        Assert.assertEquals(-24, new BasicCalculatorIII().calculate("1*2-3/4+5*6-7*8+9/10"));
        Assert.assertEquals(9, new BasicCalculatorIII().calculate("6/2*3"));
    }
}

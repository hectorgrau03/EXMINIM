package edu.upc.dsa;

import java.util.Stack;

public class ReversePolishNotationImpl implements ReversePolishNotation {

    @Override
    public double NotacionPolaca(String expression) {
        if (expression == null || expression.trim().isEmpty()) {
            return 0.0;
        }

        Stack<Double> stack = new Stack<>();
        
        // Separamos la expresión por espacios
        String[] tokens = expression.trim().split("\\s+");

        for (String token : tokens) {
            switch (token) {
                case "+":
                    stack.push(stack.pop() + stack.pop());
                    break;
                case "-":
                    double subtrahend = stack.pop();
                    double minuend = stack.pop();
                    stack.push(minuend - subtrahend);
                    break;
                case "*":
                    stack.push(stack.pop() * stack.pop());
                    break;
                case "/":
                    double divisor = stack.pop();
                    double dividend = stack.pop();
                    stack.push(dividend / divisor);
                    break;
                default:
                    stack.push(Double.parseDouble(token));
                    break;
            }
        }
        return stack.pop();
    }
}
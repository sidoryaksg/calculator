package com.example.calculator.service;

import org.springframework.stereotype.Service;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

@Service
public record CalculatorService (Calculator calculator) {

    public double calculate(String expression) {
        return evaluate(convertToRpn(expression), calculator);
    }

    private static List<Character> convertToRpn(String expression) {
        Deque<Character> stack = new ArrayDeque<>();

        List<Character> items = new ArrayList<>();

        for (int i = 0; i < expression.length(); ++i) {
            char c = expression.charAt(i);

            if (isLetterOrDigit(c)) {
                items.add(c);
            } else if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    items.add(stack.pop());
                }

                stack.pop();
            } else {
                while (!stack.isEmpty() && (getPrecedence(c) <= getPrecedence(stack.peek())) && hasLeftAssociativity(c)) {
                    items.add(stack.pop());
                }

                stack.push(c);
            }
        }

        while (!stack.isEmpty()) {
            if (stack.peek() == '(') {
                throw new IllegalArgumentException("Expression is invalid: " + expression);
            }

            items.add(stack.pop());
        }

        return items;
    }

    private static boolean isLetterOrDigit(char c) {
        return Character.isLetterOrDigit(c);
    }

    private static int getPrecedence(char c) {
        switch (c) {
            case '+', '-':
                return 1;
            case '*', '/':
                return 2;
            default:
                return -1;
        }
    }

    private static boolean hasLeftAssociativity(char c) {
        return (c == '+') || (c == '-') || (c == '/') || (c == '*');
    }

    private static double evaluate(List<Character> items, Calculator calculator) {
        if (items.isEmpty()) {
            return 0;
        }

        Deque<Double> stack = new ArrayDeque<>();

        for (Character item : items) {
            if ("+-*/".indexOf(item) != -1) {
                double y = stack.pop();
                double x = stack.pop();

                switch (item) {
                    case '+' -> stack.push(calculator.add(x, y));
                    case '-' -> stack.push(calculator.subtract(x, y));
                    case '*' -> stack.push(calculator.multiply(x, y));
                    case '/' -> stack.push(calculator.divide(x, y));
                }
            } else {
                stack.push(Double.parseDouble(String.valueOf(item)));
            }
        }

        return stack.pop();
    }
}

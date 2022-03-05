package com.company;

import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

public class Main {

    public static LinkedList<Wagon> stringTOList(String input) {
        StringBuilder converter = new StringBuilder(input);

        LinkedList<Wagon> train = new LinkedList<>();

        StringBuilder meaning = new StringBuilder();
        boolean mark = false;

        for (int meaningPos = 0; meaningPos < converter.length(); meaningPos++) {
            String symbol = converter.substring(meaningPos, meaningPos + 1);

            if (symbol.equals("+") || symbol.equals("-") || symbol.equals("*")
                    || symbol.equals("/") || symbol.equals(")") || symbol.equals("(")) {
                if (mark) {
                    train.add(new Wagon(meaning.toString(), 0));
                    meaning.delete(0, meaning.length());
                    mark = false;
                }
                int priority = 0;
                String singWagon = converter.substring(meaningPos, meaningPos + 1);
                if ((singWagon.equals("*")) || (singWagon.equals("/"))) {
                    priority = 3;
                } else if ((singWagon.equals("+")) || (singWagon.equals("-"))) {
                    priority = 2;
                } else if (singWagon.equals("(")) {
                    priority = 1;
                } else if (singWagon.equals(")")) {
                    priority = 4;
                } else System.out.println(singWagon + " неверный оператор ошибка");
                train.add(new Wagon(singWagon, priority));

            } else {
                mark = true;
                meaning = meaning.append(converter.charAt(meaningPos));
            }
        }

        if (mark) {
            train.add(new Wagon(meaning.toString(), 0));
            meaning.delete(0, meaning.length());
            mark = false;
        }
        return train;
    }

    public static LinkedList toPolishNotation(LinkedList train) {
        Wagon wagon;
        Stack<Wagon> stack = new Stack<>();
        LinkedList<Wagon> polishNotation = new LinkedList<>();
        do {

            if (!train.isEmpty()) {
                wagon = (Wagon) train.poll();
                if ((wagon.getPriority() > 0 && stack.empty()) || (wagon.getPriority() == 1)) {
                    stack.push(wagon);
                } else if (wagon.getPriority() > 0) {
                    if (wagon.getPriority() > stack.peek().getPriority() && wagon.getPriority() != 4) {
                        stack.push(wagon);
                    } else if ((wagon.getPriority() < stack.peek().getPriority() ||
                            wagon.getPriority() == stack.peek().getPriority()) && wagon.getPriority() != 4) {
                        while (!stack.empty()) {
                            if (wagon.getPriority() > stack.peek().getPriority()) {
                                stack.push(wagon);
                                break;
                            }
                            polishNotation.add(stack.pop());
                        }
                    } else {
                        while (stack.peek().getPriority() != 1) {
                            polishNotation.add(stack.pop());
                        }
                        stack.pop();
                    }
                } else polishNotation.add(wagon);
            } else while (!stack.empty()) {
                polishNotation.add(stack.pop());
            }


        } while (!(stack.empty() && train.isEmpty()));
        return polishNotation;
    }

    public static String toStringPolishNotation(LinkedList<Wagon> polishNotation) {
        StringBuilder result = new StringBuilder();
        if (!polishNotation.isEmpty()) {
            for (Wagon wagon : polishNotation
            ) {
                result.append(wagon.getMeaning());
                result.append(" ");
            }
            result.delete(result.length() - 1, result.length());

            return result.toString();
        } else return "is null";
    }

    public static double calculationPolishNotation(String polishNotation) {
        Stack<String> toStack = new Stack<>();
        String[] array = polishNotation.split(" ");
        double result = 0;
        for (int num = 0; num < array.length; num++) {

            if (array[num].equals("+") || array[num].equals("-") | array[num].equals("*") || array[num].equals("/")) {

                double meaning1 = Double.parseDouble(toStack.pop());
                double meaning2 = Double.parseDouble(toStack.pop());

                if (array[num].equals("+")) {
                    result = meaning2 + meaning1;
                    toStack.push(Double.toString(result));
                } else if (array[num].equals("-")) {
                    result = meaning2 - meaning1;
                    toStack.push(Double.toString(result));
                } else if (array[num].equals("*")) {
                    result = meaning2 * meaning1;
                    toStack.push(Double.toString(result));
                } else if (array[num].equals("+")) {
                    result = meaning2 / meaning1;
                    toStack.push(Double.toString(result));
                }

            } else toStack.push(array[num]);

        }

        return result;

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите математическую формулу (* только целые числа) например 2+7*(8+2*(10-5)+3*(8+2)+1)");
        String input = scanner.nextLine();

        LinkedList<Wagon> train = stringTOList(input);
        LinkedList<Wagon> polishNotation = toPolishNotation(train);

        System.out.println(toStringPolishNotation(polishNotation));
        System.out.println(calculationPolishNotation(toStringPolishNotation(polishNotation)));

    }
}


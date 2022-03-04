package com.company;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите математическую формулу (например 2+7*(8+2*(10-5)+3*(8+2)+1)):");
        String input = scanner.nextLine();
        StringBuilder converter = new StringBuilder(input);

        Stack<Wagon> stack = new Stack<>();
        Queue<Wagon> polishNotation = new LinkedList<>();
        Queue<Wagon> train = new LinkedList<>();

        StringBuilder meaning = new StringBuilder();
        boolean mark = false;

        for (int meaningPos = 0; meaningPos < converter.length(); meaningPos++) {
            String symbol = converter.substring(meaningPos,meaningPos+1);

          if (symbol.equals("+") || symbol.equals("-") || symbol.equals("*")
                  ||  symbol.equals("/") || symbol.equals(")") || symbol.equals("(")) {
              if (mark) {
                  train.add(new Wagon(meaning.toString(),0));
                  meaning.delete(0,meaning.length());
                  mark = false;
              }
              int priority = 0;
              String singWagon = converter.substring(meaningPos,meaningPos+1);
              if ((singWagon.equals("*")) || (singWagon.equals("/"))) {
                  priority = 3;
              } else if ((singWagon.equals("+")) || (singWagon.equals("-"))){
                  priority = 2;
              } else if (singWagon.equals("(")){
                  priority = 1;
              } else if (singWagon.equals(")")){
                  priority = 4;
              } else System.out.println(singWagon+ " неверный оператор ошибка");
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

        System.out.println(stack.empty());
        Wagon wagon;
        System.out.println(train);

int i = 1;

        do {
            i++;
//            System.out.println(i);
            if (!train.isEmpty()) {
                wagon = train.poll();
                if ((wagon.getPriority() > 0 && stack.empty()) || (wagon.getPriority() == 1)) {
                    stack.push(wagon);
                } else if (wagon.getPriority() > 0) {
                    if (wagon.getPriority() > stack.peek().getPriority() && wagon.getPriority() != 4) {
                        stack.push(wagon);
                    } else if ((wagon.getPriority() < stack.peek().getPriority() ||
                            wagon.getPriority() == stack.peek().getPriority()) && wagon.getPriority() != 4) {
                        while (!stack.empty()) {
                            if (wagon.getPriority() > stack.peek().getPriority()){//TODO
                                stack.push(wagon);
                                break;
                            }
                            polishNotation.add(stack.pop());
//                            System.out.println(polishNotation.toString()+ "aaa");
                        }
                    } else {
                       while (stack.peek().getPriority() != 1){
                           polishNotation.add(stack.pop());
                       }
                       stack.pop();
                    }
                } else polishNotation.add(wagon);
            } else while (!stack.empty()){
                polishNotation.add(stack.pop());
            }

//            System.out.println(stack.empty() + " " +  train.isEmpty());
//            System.out.println((!(!stack.empty() && train.isEmpty())));
        } while (!(stack.empty() && train.isEmpty()));

        System.out.println(polishNotation.toString());
        System.out.println(stack.toString());

    }
}















////        char[] arrayValues = input.toCharArray();
////        Queue<String> sign = new ArrayDeque<>();
////        Stack<Integer> numbers = new Stack<Integer>();
//
//        int sequence = 0;
//
//        Stack<Integer> openRoundBracket = new Stack<>();
//        Stack<String> actions = new Stack<>();
//
//        while (converter.indexOf(")")!=-1 && converter.indexOf("(")!=-1) {
//
//            sequence++;
//
//            int startRoundBracket = converter.indexOf("(");
//            System.out.println(startRoundBracket);
//            System.out.println(converter.charAt(startRoundBracket));
//
//


//2+7*(8+2*(10-5)+3*(8+2)+1)

//            int startRoundBracket = converter.indexOf(")");
//
//            int endRoundBracket = converter.indexOf("(");
//            System.out.println(converter.length());
//            System.out.println(startRoundBracket);
//            System.out.println(endRoundBracket);
//
//            StringBuilder action = new StringBuilder(converter.substring(startRoundBracket, endRoundBracket));
//            action.deleteCharAt(0);
//            action.deleteCharAt(action.length()-1);
//
//            System.out.println(action);
//            converter.replace(startRoundBracket , endRoundBracket ,"?" + sequence);
//            System.out.println(converter);






//        input = input.substring(startRoundBracket,endRoundBracket);
//        System.out.println(input);



//        for (int i = 0; i < arrayValues.length; i++) {
////            char value = arrayValues[i];
//            int indexOf (char ch)
//
//
////            if ( ) {
////                numbers.add(int (value));
////            } else {
////                sign.add(value);
////            }
//        }
//    }
//}

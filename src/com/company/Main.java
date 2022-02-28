package com.company;

import java.util.Scanner;
import java.util.Stack;

public class Main {

    public static boolean isNum(String numStr) {
        try {
            Integer.parseInt(numStr);
            return true;
        } catch (Exception R) {
            return false;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите математическую формулу:");
        String input = scanner.nextLine();
        StringBuilder converter = new StringBuilder(input);

        Stack<String> sign = new Stack<>();
        Stack<Integer> numbers = new Stack<Integer>();
        Stack<Wagon> train = new Stack<>();

        StringBuilder meaning = new StringBuilder();
        boolean mark = false;

        for (int meaningPos = 0; meaningPos < converter.length(); meaningPos++) {

          if (isNum(converter.substring(meaningPos,meaningPos+1))) {
              mark = true;
              meaning = meaning.append(converter.charAt(meaningPos));
            } else {
              if (mark) {

                  numbers.push(Integer.parseInt(meaning.toString()));
                  train.push(new Wagon(meaning.toString(), true));
                  meaning.delete(0,meaning.length());
                  mark = false;
              }
              sign.push(converter.substring(meaningPos,meaningPos+1));
              train.push(new Wagon(converter.substring(meaningPos,meaningPos+1), false));

          }


        }
        System.out.println(numbers.toString());
        System.out.println(sign.toString());
        System.out.println(train);
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

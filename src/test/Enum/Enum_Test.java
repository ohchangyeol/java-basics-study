package test.Enum;

import test.domain.Human;

public class Enum_Test {
    public static void main(String[] args) {
        System.out.println("Hello world!\n" +
                "--------------------");

        for (Human string : Human.values()) {
            System.out.println("string = " + string);
        }
        System.out.println("-----------------------");

        for (Human key : Human.values()) {
           System.out.println("getValue = " + key.getValue());
       }

    }
}
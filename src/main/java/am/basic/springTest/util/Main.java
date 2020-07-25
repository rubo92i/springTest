package am.basic.springTest.util;

public class Main {

    public static void main(String[] args) {
        int x = 10;
        int y = 10;
        x = x + y++;
        System.out.println(y);
        System.out.println(x);

    }

    //y++
    public static void test1() {
        int x = 10;
        int y = 10;
        //x = x  + y++;
        x = x + y;
        y = y + 1;


        System.out.println(y);
        System.out.println(x);
    }

    //++y
    public static void test2() {
        int x = 10;
        int y = 10;
        //x = x  + ++y;
        y = y + 1;
        x = x + y;

        System.out.println(y);
        System.out.println(x);
    }
}

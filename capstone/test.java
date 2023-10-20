package capstone;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class test {

    public void test1(int b) {
        int a = 4;
        test2(a);
        System.out.println(a);
    }

    public void test2(int a) {
        a = 5;
    }

    public static void main(String[] args) {
        test t = new test();
        int b = 2;
        t.test1(b);
        System.out.println(b);
    }
}

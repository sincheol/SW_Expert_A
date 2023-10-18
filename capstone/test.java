package capstone;

public class test {
    int t = 1;

    public void test1() {
        if (t <= 3) {
            t++;
            test2(t);
            System.out.println(t);
        }
    }

    public void test2(int t) {
        if (t <= 3) {
            test1();
            System.out.println(t);
        }
    }

    // node root, priorityqueue<node>
    public static void main(String[] args) {
        test a = new test();
        a.test1();
    }
}

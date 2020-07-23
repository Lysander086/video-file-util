package webDemo;

public class StringUtil {
    public static void main(String[] args) {
        String test = ("chaojimali");
        test = test.replace("chaoji", "");
        System.out.println("test:" + test);


        String test2 = ("chaojimali");
        test2 = test2.substring(6);
        System.out.println("test2:" + test2);
    }
}
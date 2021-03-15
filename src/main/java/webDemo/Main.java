package webDemo;

public class Main {
    public static void main(String[] args) {
        String string= "[-test";
        string=string.replaceAll("\\[-","");
        System.out.println(string);
    }
}
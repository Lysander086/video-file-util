package webDemo;

public class Test3 {

    /**
     * @param args
     */
    public static void main(String[] args) {
        //要切割的字符串
        String s = "113.jpg,123.jpg,113.jpg,121.jpg,122.jpg,131.jpg";
        String res = "";
        System.out.println("编译前：" + s);
        //调用方法
        res = s.replaceAll(",113.jpg|113.jpg,", "");//.replaceAll( ",122.jpg|122.jpg,","");
        System.out.println("编译后：" + res);
    }

}
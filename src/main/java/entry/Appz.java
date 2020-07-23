package entry;

import util.VideoUtil;

public class Appz {
    public static final boolean DEBUGGING = false;
//        public static String coursePath = "D:\\test"; //测试路径
    public static String coursePath = "E:\\hdCvideos\\再学JavaScript ES(6-10)全版本语法大全\\第2章 ES6基础知识"; //测试路径

    public static void main(String[] args) {
        VideoUtil.invokeCalculate(coursePath);
    }
}

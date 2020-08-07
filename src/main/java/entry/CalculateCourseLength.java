package entry;

import util.VideoUtil;

public class CalculateCourseLength {
    public static final boolean DEBUGGING = false;
//        public static String coursePath = "D:\\test"; //测试路径
//    public static String coursePath = "E:\\hdCvideos\\再学JavaScript ES(6-10)全版本语法大全\\第2章 ES6基础知识";
    public static String coursePath = "E:\\hdCvideos\\frontEnd\\285、Vue 实战商业级读书Web APP 全面提升技能";

    public static void main(String[] args) {
        VideoUtil.invokeCalculate(coursePath);
    }
}

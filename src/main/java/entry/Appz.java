package entry;

import util.VideoUtil;

public class Appz {
    public static final boolean DEBUGGING = false;

    public static void main(String[] args) {
//         String coursePath = "E:\\hdCvideos\\401、Vue Element+Node.js开发企业通用管理后台系统"; //要遍历课程的路径
//        String coursePath = "E:\\hdCvideos\\196、Vue核心技术Vue+Vue-Router+Vuex+SSR实战精讲"; //要遍历课程的路径
//        String coursePath = "E:\\hdCvideos\\再学JavaScript ES(6-10)全版本语法大全\\第2章 ES6基础知识"; //要遍历课程的路径
        String coursePath = "D:\\test"; //测试路径
        VideoUtil.invokeCalculate(coursePath);
    }
}

import util.VideoUtil;

public class Appz {
    public static void main(String[] args) {
        String coursePath = "E:\\hdCvideos\\Vue Element+Node.js开发企业通用管理后台系统"; //要遍历课程的路径
        // String coursePath = "D:\\test"; //要遍历课程的路径

        VideoUtil.invokeCalculate(coursePath);
    }
}

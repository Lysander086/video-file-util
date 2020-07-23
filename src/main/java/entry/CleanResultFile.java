package entry;

import util.FileUtil;

import java.io.File;

public class CleanResultFile {
    public static final String[] CONTAINED_KEY = new String[]{"当前目录视频", "remaining hour"};
    public static final String fileEXTENSION = "txt";
    public static String path = "";
//    public static String path = "D:\\test";

    public static void main(String[] args) {
        System.out.println("cleaning start");
        if (!"".equals(path)) cleanFiles(path);
        else {
            cleanFiles(Appz.coursePath);
        }
        System.out.println("cleaning end");
    }

    static void cleanFiles(String path) {
        File file = new File(path);  //获取其file对象
        File[] files = file.listFiles();

        assert files != null;
        for (File thing : files) {
            if (thing.isFile()
                    && fileEXTENSION.equals(FileUtil.getExtensionName(thing.getName()))
                    && FileUtil.ifContainKeyWords(thing.getName(), CONTAINED_KEY)) {
                String fileIndicator = thing.getAbsolutePath() + File.separator + thing.getName();
                if (thing.delete())
                    System.out.println(fileIndicator + "已被删除");
                else System.out.println(fileIndicator + "文件删除失败");

            } else if (thing.isDirectory()) {
                cleanFiles(thing.getAbsolutePath());
            }
        }
    }
}

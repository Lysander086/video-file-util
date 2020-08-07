package entry;

import lombok.SneakyThrows;
import util.FileUtil;

import java.io.File;

public class BatchRenameFile {
    public static final String containedKey = "【海量资源尽在250java.cn】";
    //    public static final String containedKey = "250java.cn";
    //    public static String path = "";
    public static String path = "E:\\hdCvideos\\frontEnd\\再学JavaScript ES(6-11)全版本语法大全";

    public static void main(String[] args) {
        System.out.println("BatchRenameFile start");
        batchRename(path);
        System.out.println("BatchRenameFile end");
        FileUtil.createFile(path + File.separator + "batch rename from " + containedKey + ".txt");
    }

    @SneakyThrows
    static void batchRename(String path) {
        File file = new File(path);  //获取其file对象
        File[] files = file.listFiles();

        assert files != null;
        String newName = "";
        File thingWithNewName;
        for (File thing : files) {
            if (thing.isDirectory()) batchRename(thing.getAbsolutePath());
            String oldName = thing.getName();
            if (oldName.contains(containedKey)) {
//                thing.renameTo()
                newName = oldName.replaceAll(containedKey, "");
                String fileNewName =
                        thing.getAbsolutePath().substring(0, thing.getAbsolutePath().lastIndexOf(File.separator))
                                + File.separator
                                + newName;
                thingWithNewName = new File(fileNewName);
                if (thing.renameTo(thingWithNewName)) {
                    System.out.println("successfully get thingWithNewName: " + thingWithNewName.getName());
                } else {
                    throw new Exception("error: file may be in use");
                }
            }
        }
    }

}

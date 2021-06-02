package entry;

import lombok.SneakyThrows;
import util.FileUtil;

import java.io.File;

public class BatchRenameFile {
    //    public static final String containedKey = "( )"; // name to remove
    public static final String[] containVal = new String[]{"[QQ微信352852792]"};
    public static final boolean regexSupport = true;
    public static final String replacedVal = ""; // name to replace
    public static String path = "C:\\Users\\rdc\\Desktop\\1fm\\java architecture course\\java架构"; // your video path here
    public static final boolean toGenerateFile = false; // whether create a text file in specified path

    public static void main(String[] args) {
        System.out.println("BatchRenameFile start");
        batchRename(path);
        System.out.println("BatchRenameFile end");
        for (String containedKey : containVal) {
            if (toGenerateFile)
                FileUtil.createFile(path + File.separator + "batch rename from " + containedKey + ".txt");
        }
    }

    @SneakyThrows
    static void batchRename(String path) {
        File file = new File(path);  //获取其file对象
        File[] files = file.listFiles();

        assert files != null;
        String newName = "";
        File thingWithNewName;
        for (File thing : files) {
            if (thing.isDirectory() && !thing.getName().contains("git")) batchRename(thing.getAbsolutePath());
            String oldName = thing.getName();
            for (String containedVal : containVal) {
                if (regexSupport || oldName.contains(containedVal)) {
                    newName = oldName.replaceAll(containedVal, replacedVal);
                    String fileNewName =
                            thing.getAbsolutePath().substring(0, thing.getAbsolutePath().lastIndexOf(File.separator)) + File.separator + newName;
                    thingWithNewName = new File(fileNewName);
                    if (thing.renameTo(thingWithNewName)) {
                        System.out.println("successfully get thingWithNewName: " + thingWithNewName.getName());
                    } else {
                        System.out.println(String.format("!!! Error: file %s may be in use, skipped renaming.", thing.getName()));
                    }
                }
            }

        }
    }

}

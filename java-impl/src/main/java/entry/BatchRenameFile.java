package entry;

import lombok.SneakyThrows;
import util.FileUtil;

import java.io.File;

public class BatchRenameFile {
    //    public static final String containedKey = "( )"; // name to remove
    public static final String[] containedKeyArr = new String[]{" "};
    public static String path = " "; // your video path here
    public static final String replacedKey = ""; // name to replace
    public static final boolean toGenerateFile = false; // whether create a text file in specified path

    public static void main(String[] args) {
        System.out.println("BatchRenameFile start");
        batchRename(path);
        System.out.println("BatchRenameFile end");
        for (String containedKey : containedKeyArr) {
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
            for (String containedKey : containedKeyArr) {
                if (oldName.contains(containedKey)) {
                    newName = oldName.replaceAll(containedKey, replacedKey);
                    String fileNewName =
                            thing.getAbsolutePath().substring(0, thing.getAbsolutePath().lastIndexOf(File.separator)) + File.separator + newName;
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

}

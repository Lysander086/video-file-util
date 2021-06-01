package entry;

import lombok.SneakyThrows;
import util.FileUtil;

import java.io.File;

public class BatchRenameFile {
    //    public static final String containedKey = "( )"; // name to remove
    public static final String[] containVal = new String[]{"[(|)]"};
    public static String path = "C:\\Users\\rdc\\Desktop\\1fm\\spring cloud  微服务"; // your video path here
    public static final String replacedVal = ""; // name to replace
    public static final boolean regexSupport = false;
    public static final boolean toGenerateFile = false; // whether create a text file in specified path

    public static void main(String[] args) {
        System.out.println("BatchRenameFile start");
        batchRename(path);
        System.out.println("BatchRenameFile end");
        for (String containedKey : containVal) {
            if (toGenerateFile)
                FileUtil.createFile(path + File.separator + "batch rename from " + containedKey + ".txt");
        }
        String str = "abc(a)cba";
        String str2 = str.replaceAll("[(|)]", "b");
        System.out.println(str2);
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
                        throw new Exception("error: file may be in use");
                    }
                }
            }

        }
    }

}

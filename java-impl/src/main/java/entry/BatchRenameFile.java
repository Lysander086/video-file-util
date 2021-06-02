package entry;

import lombok.SneakyThrows;
import util.FileUtil;

import java.io.File;

/* "([|])" */
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
        String newName;
        File newNamedFile;
        for (File f : files) {
            if (f.isDirectory() && !f.getName().contains("git")) batchRename(f.getAbsolutePath());
            String oldName = f.getName();
            for (String containedVal : containVal) {
                if (regexSupport || oldName.contains(containedVal)) {
                    newName = oldName.replaceAll(containedVal, replacedVal);
                    String fileNewName =
                            f.getAbsolutePath().substring(0, f.getAbsolutePath().lastIndexOf(File.separator)) + File.separator + newName;
                    newNamedFile = new File(fileNewName);
                    if (f.renameTo(newNamedFile)) {
                        if (regexSupport) if (!oldName.equals(newName))
                            System.out.println("successfully get newNamedFile: " + fileNewName);
                        else
                            System.out.println("successfully get newNamedFile: " + fileNewName);
                    } else {
                        System.out.println(String.format("!!! Error: file %s may be in use, skipped renaming.", f.getName()));
                    }
                }
            }

        }
    }

}

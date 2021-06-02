package entry;

import lombok.SneakyThrows;
import util.FileUtil;

import java.io.File;

/* "([|])" */
public class BatchRenameFile {

    public static final String[] containValues = new String[]{" "};

    public static final boolean regexSupport = true;
    public static final String replacedVal = ""; // name to replace
    public static String path = "C:\\Users\\inz\\Desktop\\1fm\\java architecture course\\bakRename - Copy"; // your video path here
    public static final boolean toGenerateFile = false; // whether create a text file in specified path

    public static void main(String[] args) {
        System.out.println("BatchRenameFile start");
        batchRename(path);
        System.out.println("BatchRenameFile end");
        for (String containedKey : containValues) {
            if (toGenerateFile)
                FileUtil.createFile(path + File.separator + "batch rename from " + containedKey + ".txt");
        }
    }

    @SneakyThrows
    static void batchRename(String path) {
        File file = new File(path);  //获取其file对象
        File[] files = file.listFiles();

        assert files != null;

        File newNamedFile;
        for (File f : files) {
            if (f.isDirectory() && !f.getName().contains("git")) batchRename(f.getAbsolutePath());

            String newName = f.getName();
            for (String containedVal : containValues) {
                newName = newName.replaceAll(containedVal, replacedVal);
            }
            if (regexSupport || !newName.equals(f.getName())) {
                String fileNewName =
                        f.getAbsolutePath().substring(0, f.getAbsolutePath().lastIndexOf(File.separator)) + File.separator + newName;
                newNamedFile = new File(fileNewName);
                if (f.renameTo(newNamedFile)) {
                    System.out.println("rename success: " + fileNewName);
                } else {
                    System.out.println(String.format("!!! Error: file %s may be in use, skipped renaming.", f.getName()));
                }
            }


        }
    }

}

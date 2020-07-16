
package util;


public class SuffixValidTest {

    /*
     * @param thing can be a file or others */
    public static boolean isValid(String thing) {
        String[] allowTypes = Constants.all();
        if (null == thing || "".equals(thing)) {
            return false;
        }
        String extensionSuffix = FileUtil.getExtensionName(thing);
        for (String type : allowTypes) {
            if (type.equals(extensionSuffix)) {
                return true;
            }
        }
        return false;
    }


}
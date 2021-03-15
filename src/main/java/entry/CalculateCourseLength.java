package entry;

import util.VideoUtil;

public class CalculateCourseLength {
    public static final boolean DEBUGGING = false;
    public static String coursePath = " ";
//    public static String coursePath = " ";

    public static void main(String[] args) {
        try {
            VideoUtil.invokeCalculate(coursePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

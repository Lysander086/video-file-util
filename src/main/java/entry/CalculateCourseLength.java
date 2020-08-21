package entry;

import util.VideoUtil;

public class CalculateCourseLength {
    public static final boolean DEBUGGING = false;
    public static String coursePath = "YourPathHere";

    public static void main(String[] args) {
        VideoUtil.invokeCalculate(coursePath);
    }
}

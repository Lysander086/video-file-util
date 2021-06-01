package entry;

import util.VideoUtil;

public class CalculateCourseLength {
    public static final boolean DEBUGGING = false;
    public static String coursePath = "/Users/Lysander/Desktop/1fm/courses/Machine Learning boot camp/1. Welcome to the course!";
//    public static String coursePath = " ";
    public static void main(String[] args) {
        try {
            VideoUtil.invokeCalculate(coursePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

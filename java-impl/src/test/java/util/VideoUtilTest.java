package util;

import org.junit.Test;

import static org.junit.Assert.*;

public class VideoUtilTest {

    @Test
    public void test_getFormattedDuration() {
        String res = VideoUtil.getFormattedDuration(3600 * 1000);
        System.out.println(res);
    }
}
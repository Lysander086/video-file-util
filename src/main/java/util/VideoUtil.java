package util;

import entry.CalculateCourseLength;
import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.EncoderException;
import it.sauronsoftware.jave.MultimediaInfo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

public class VideoUtil {

    public static String getFormattedDuration(long milliseconds) {
        StringBuilder sb = new StringBuilder();
        long mss = milliseconds / 1000;
        long days = mss / (60 * 60 * 24);
        long hours = (mss % (60 * 60 * 24)) / (60 * 60);
        long minutes = (mss % (60 * 60)) / 60;
        long seconds = mss % 60;
        DecimalFormat format = new DecimalFormat("00");
        if (days > 0 || hours > 0) {
            sb.append(format.format(hours)).append(":").append(format.format(minutes)).append(":").append(format.format(seconds));
        } else {
            sb.append(format.format(minutes)).append(":").append(format.format(seconds));
        }
        return sb.toString();
    }

    public static void invokeCalculate(String coursePath) {

        System.out.println("Calculating");
        try {
            long totalTime = VideoUtil.writeResult(coursePath);
//            writer.write("total hours: " + VideoUtil.getFormattedDuration(totalTime));
            System.out.println("remaining hours:" + VideoUtil.getFormattedDuration(totalTime));
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("end");
    }

    public static long writeResult(String path) throws IOException {
        File file = new File(path);  //获取其file对象
        File[] files = file.listFiles();
        long totalTime = 0;
        assert files != null;
        for (File thing : files) {
            String fileName = thing.getName();
            if (!thing.isDirectory() && fileName.contains("finished")
                    || fileName.contains("fff")
                    || fileName.contains("gitignore")
            )
                return 0;
        }

        // if a dir named archivez or finished, videos under this dir would not be counted
        for (File thing : files) {
            if (thing.isDirectory() && !(thing.getName().equals("archivez") || thing.getName().equals("finished"))) {
                totalTime += writeResult(thing.getAbsolutePath());
            }
        }
        // 获取当前目录下的视频信息
        List<MultimediaInfo> multimediaInfoList = getMediaInfo(files);

        BufferedWriter writer = new BufferedWriter(new FileWriter(path + File.separator + "当前目录视频时长(包括子目录).txt"));

        // 如果当前目录下没有视频, 那直接返回子目录的全部视频时长, 结束递归
        if (multimediaInfoList == null) {
            writer.write("total hours: " + VideoUtil.getFormattedDuration(totalTime));
            writer.close();
            return totalTime;
        }
        writer.write("current dir: " + file.getName() + "\n");

        AtomicLong allDuration = new AtomicLong(0L);
        AtomicInteger i = new AtomicInteger();
        multimediaInfoList.forEach(everyInfo -> {
            try {
                allDuration.addAndGet(everyInfo.getDuration());
                writer.write(VideoUtil.getFormattedDuration(everyInfo.getDuration()) + " videoName: " + files[i.getAndIncrement()].getName()
                        + " "
                        + "\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        totalTime += allDuration.longValue();
        writer.write("total hours: " + VideoUtil.getFormattedDuration(totalTime));
        if (CalculateCourseLength.DEBUGGING) System.out.println(VideoUtil.getFormattedDuration(totalTime));
        writer.close();
        return totalTime;
    }

    // 对于路径下的所有视频文件获取视频信息
    public static List<MultimediaInfo> getMediaInfo(File[] files) {

        File[] videoFiles = Arrays.stream(files).filter(everyFile -> everyFile.isFile() && SuffixValidTest.isValid(everyFile.getName())).toArray(File[]::new);
        if (videoFiles.length <= 0) {
            return null;
        }
        Encoder encoder = new Encoder();
        return Arrays.stream(videoFiles).map(every -> {
            try {
                return encoder.getInfo(every);
            } catch (EncoderException e) {
                e.printStackTrace();
            }
            return null;
        }).collect(Collectors.toList());
    }
}

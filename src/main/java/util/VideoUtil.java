package util;

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
        try (BufferedWriter writer =
                     new BufferedWriter(new FileWriter(coursePath + File.separator + "当前目录视频时长.txt"));) {
            long totalTime = VideoUtil.writeResult(coursePath);
            writer.write("total hours: " + VideoUtil.getFormattedDuration(totalTime));
            System.out.println("剩余时长:" + VideoUtil.getFormattedDuration(totalTime));
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
            if (thing.getName().contains("finished"))
                return 0;
        }
        for (File thing : files) {
            if (thing.isDirectory() && !thing.getName().equals("archivez")) {
                totalTime += writeResult(thing.getAbsolutePath());
            }
        }
        // 获取当前目录下的视频信息
        List<MultimediaInfo> multimediaInfoList = getMediaInfo(files);
        // 如果当前目录下没有视频, 那直接返回子目录的全部视频时长, 结束递归
        if (multimediaInfoList == null) return totalTime;


        BufferedWriter writer = new BufferedWriter(new FileWriter(path + File.separator + "当前目录视频时长.txt"));
        writer.write("current dir: " + file.getName() + "\n");

        AtomicLong allDuration = new AtomicLong(0L);
        AtomicInteger i = new AtomicInteger();
        multimediaInfoList.forEach(everyInfo -> {
            try {
                allDuration.addAndGet(everyInfo.getDuration());
                writer.write(files[i.getAndIncrement()].getName()
                        + " " + VideoUtil.getFormattedDuration(everyInfo.getDuration())
                        + "\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        totalTime += allDuration.longValue();
        writer.write("total hours: " + VideoUtil.getFormattedDuration(totalTime));
        writer.close();
        return allDuration.longValue();
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

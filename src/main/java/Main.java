import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static jdk.nashorn.internal.objects.ArrayBufferView.length;

/**
 * Created by bohu on 4/6/17.
 */
public class Main {
    public static void main(String args[]) {
        TranscriberDemo td = new TranscriberDemo();
        td.preprocessing();
        String trainingDir = "../dataSet/";
        File root = new File(trainingDir);
        FilenameFilter wavFilter = new FilenameFilter() {
            public boolean accept(File dir, String name) {
                name = name.toLowerCase();
                return name.endsWith(".wav");
            }
        };
        File[] wavFiles = root.listFiles(wavFilter);
        System.out.println(wavFiles.length);
        double totalSize = 0;
        List<Long> times = new LinkedList<>();
        for (File audio : wavFiles) {
            long t = td.speechRecognizer(audio);
            totalSize += audio.length();
            times.add(t);
        }
        double sum = 0;
        for (long t : times) {
            sum += t;
        }

        System.out.println(totalSize/wavFiles.length/1024);
        System.out.println(sum/times.size());
    }
}

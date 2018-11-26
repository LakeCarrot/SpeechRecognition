import java.io.*;
import java.nio.file.FileSystemNotFoundException;
import java.util.List;

import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.SpeechResult;
import edu.cmu.sphinx.api.StreamSpeechRecognizer;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;
/**
 * Created by bohu on 3/28/17.
 */
public class TranscriberDemo {
    static Configuration configuration;
    static StreamSpeechRecognizer recognizer;
    public void preprocessing() {
        configuration = new Configuration();

        configuration
                .setAcousticModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us");
        configuration
                .setDictionaryPath("resource:/edu/cmu/sphinx/models/en-us/cmudict-en-us.dict");
        /*
        configuration
                .setLanguageModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us.lm.bin");
                */
        configuration.setUseGrammar(true);
        configuration.
                setGrammarName("menu");
        configuration.
                setGrammarPath("model");

        try {
            recognizer = new StreamSpeechRecognizer(configuration);
        } catch (IOException e) {}

    }
    public long speechRecognizer(File audio) {
        InputStream stream = null;
        try {
            stream = new FileInputStream(audio);
        } catch (FileNotFoundException e) {
        }
        long begin = System.currentTimeMillis();
        recognizer.startRecognition(stream);
        long end = System.currentTimeMillis();
        SpeechResult result;
        while ((result = recognizer.getResult()) != null) {
            System.out.format("Hypothesis: %s\n", result.getHypothesis());
        }
        recognizer.stopRecognition();
        return (end - begin);
    }
}

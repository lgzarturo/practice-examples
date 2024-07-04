import org.mozilla.universalchardet.UniversalDetector;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.String.format;
import static java.nio.file.Files.walk;

public class EncodingValidator {
    private final static Logger logger = Logger.getLogger(EncodingValidator.class.getName());

    public static void isFilesInDirectoryEncodingUTF8(Path directoryPath) {
        try (var paths = walk(directoryPath)) {
            paths.filter(Files::isRegularFile)
                .filter(file -> file.toString().endsWith(".java"))
                .forEach(file -> {
                    boolean isUTF8 = isFileEncodingUTF8(file);
                    logger.info(format("-- Is file %s encoding UTF-8? %s%n", file, isUTF8));
                });
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error while processing files in directory", e);
        }
    }


    public static boolean isFileEncodingUTF8(Path filePath) {
        try (FileInputStream fileInputStream = new FileInputStream(filePath.toFile())) {
            byte[] buffer = new byte[4096];
            UniversalDetector detector = new UniversalDetector(null);
            int nread;
            while ((nread = fileInputStream.read(buffer)) > 0 && !detector.isDone()) {
                detector.handleData(buffer, 0, nread);
            }
            detector.dataEnd();

            String encoding = detector.getDetectedCharset();
            logger.info(format("Detected encoding: %s", encoding));
            detector.reset();
            return "UTF-8".equalsIgnoreCase(encoding);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error while processing file", e);
            return false;
        }
    }
}

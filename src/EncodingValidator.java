import org.mozilla.universalchardet.UniversalDetector;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
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
                    if (!isUTF8) {
                        correctEncoding(file);
                    }
                    getCreatedAndUpdatedFile(file);
                    getPermissions(file);
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

    public static void correctEncoding(Path filePath) {
        try {
            String content = Files.readString(filePath);
            Files.writeString(filePath, content, StandardCharsets.UTF_8);
            logger.info(format("Corrected encoding for file: %s", filePath));
            var isUTF8 = isFileEncodingUTF8(filePath);
            if (!isUTF8) {
                logger.warning(format("Could not correct encoding for file: %s", filePath));
            } else {
                logger.info(format("File %s encoding is now UTF-8", filePath));
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error while correcting file encoding", e);
        }
    }

    public static void getCreatedAndUpdatedFile(Path filePath) {
        try {
            var createdTime = Files.getAttribute(filePath, "creationTime");
            var updatedTime = Files.getAttribute(filePath, "lastModifiedTime");
            var created = getHumanReadableDate(createdTime.toString());
            var updated = getHumanReadableDate(updatedTime.toString());
            logger.info(format("-- File %s was created at %s and last updated at %s", filePath, created, updated));
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error while getting file creation and update time", e);
        }
    }


    public static String getHumanReadableDate(String date) {
        String dateHumanReadable = date.replace("T", " ").replace("Z", "");
        String time = dateHumanReadable.split(" ")[1];
        String timeHumanReadable = time.split(":")[0] + ":" + time.split(":")[1];
        return String.format("%s %s", dateHumanReadable.split(" ")[0], timeHumanReadable);
    }

    public static void getPermissions(Path filePath) {
        try {
            var permissions = Files.getPosixFilePermissions(filePath);
            logger.info(format("-- File %s has permissions: %s", filePath, permissions));
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error while getting file permissions", e);
        }
    }
}

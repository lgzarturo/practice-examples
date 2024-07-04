import com.ibm.icu.text.CharsetDetector;
import com.ibm.icu.text.CharsetMatch;
import org.mozilla.universalchardet.UniversalDetector;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
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
                    Charset charset = detectCharset(file);
                    logger.info(format("### Is file %s encoding UTF-8? %s", file, charset));
                    boolean isUTF8 = charset.equals(StandardCharsets.UTF_8);
                    boolean fileIsCorrected = isUTF8 || correctEncoding(file) || correctEncodingWithReadLines(file);
                    if (fileIsCorrected) {
                        getCreatedAndUpdatedFile(file);
                        getPermissions(file);
                    } else {
                        logger.warning(format("File %s could not be corrected", file));
                    }
                });
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error while processing files in directory", e);
        }
    }


    public static boolean isFileEncodingUTF8WithJUniversalCharDet(Path filePath) {
        try (FileInputStream fileInputStream = new FileInputStream(filePath.toFile())) {
            byte[] buffer = new byte[4096];
            UniversalDetector detector = new UniversalDetector(null);
            int nread;
            while ((nread = fileInputStream.read(buffer)) > 0 && !detector.isDone()) {
                detector.handleData(buffer, 0, nread);
            }
            detector.dataEnd();

            String encoding = detector.getDetectedCharset();
            detector.reset();
            return "UTF-8".equalsIgnoreCase(encoding);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error while processing file", e);
            return false;
        }
    }

    public static boolean isFileEncodingUTFWithCharsetDetector(Path filePath) {
        try (FileInputStream fileInputStream = new FileInputStream(filePath.toFile())) {
            byte[] buffer = new byte[4096];
            fileInputStream.read(buffer);
            CharsetDetector detector = new CharsetDetector();
            detector.setText(buffer);
            CharsetMatch match = detector.detect();
            if (match != null) {
                return match.getName().equalsIgnoreCase("UTF-8");
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error while processing file", e);
            return false;
        }
        return false;
    }

    public static Charset detectCharset(Path filePath) {
        if (isFileEncodingUTF8WithJUniversalCharDet(filePath) || isFileEncodingUTFWithCharsetDetector(filePath)) {
            return StandardCharsets.UTF_8;
        }
        return StandardCharsets.ISO_8859_1;
    }

    public static boolean correctEncoding(Path filePath) {
        try {
            String content = Files.readString(filePath);
            Files.writeString(filePath, content, StandardCharsets.UTF_8);
            logger.info(format("Corrected encoding for file: %s", filePath));
            if (detectCharset(filePath).equals(StandardCharsets.UTF_8)) {
                return true;
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error while correcting file encoding", e);
        }
        return false;
    }

    public static boolean correctEncodingWithReadLines(Path filePath) {
        try {
            var lines = Files.readAllLines(filePath, StandardCharsets.UTF_8);
            Files.write(filePath, lines, StandardCharsets.UTF_8);
            logger.info(format("Corrected encoding for file: %s", filePath));
            if (detectCharset(filePath).equals(StandardCharsets.UTF_8)) {
                return true;
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error while correcting file encoding", e);
        }
        return false;
    }

    public static void getCreatedAndUpdatedFile(Path filePath) {
        try {
            var createdTime = Files.getAttribute(filePath, "creationTime");
            var updatedTime = Files.getAttribute(filePath, "lastModifiedTime");
            var created = getHumanReadableDate(createdTime.toString());
            var updated = getHumanReadableDate(updatedTime.toString());
            logger.info(format("File %s was created at %s and last updated at %s", filePath, created, updated));
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
            logger.info(format("File %s has permissions: %s", filePath, permissions));
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error while getting file permissions", e);
        }
    }
}

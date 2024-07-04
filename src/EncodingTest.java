import java.nio.file.Path;
import java.util.logging.Logger;

import static java.lang.String.format;

public class EncodingTest {
    private static final Logger logger = Logger.getLogger(EncodingTest.class.getName());

    public static void main(String[] args) {
        String directoryPath = "/Users/lgzarturo/temp/metal";
        logger.info(format("Checking encoding of files in directory: %s%n", directoryPath));
        EncodingValidator.isFilesInDirectoryEncodingUTF8(Path.of(directoryPath));
    }
}

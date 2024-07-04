import java.nio.file.Path;

public class EncodingTest {
    public static void main(String[] args) {
        String directoryPath = "/Users/lgzarturo/IdeaProjects/metal/core-api/src/";
        EncodingValidator.isFilesInDirectoryEncodingUTF8(Path.of(directoryPath));
    }
}

import com.opencsv.CSVWriter;
import pojo.Person;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Example02CSVGenerator {
    private final static Logger logger = Logger.getLogger(Example02CSVGenerator.class.getName());

    public static void main(String[] args) {
        List<Person> people = List.of(
            new Person("Alice", "alice@gmail.com", "1234567890"),
            new Person("Bob", "bob@gmail.com", "0987654321"),
            new Person("Charlie", "charlie@gmail.com", "6789012345")
        );

        try {
            String fileName = "people.csv";
            byte[] content = generateCSVContent(people);
            writeToFile(fileName, content);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error while generating CSV file", e);
        }
    }

    private static byte[] generateCSVContent(List<Person> people) throws Exception {
        try (
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(baos, StandardCharsets.UTF_8);
            CSVWriter writer = new CSVWriter(osw)
        ) {
            String[] header = {"Name", "Email", "Phone"};
            writer.writeNext(header);
            people.forEach(person -> writer.writeNext(new String[]{person.name(), person.email(), person.phone()}));
            writer.flush();
            return baos.toByteArray();
        }
    }

    private static void writeToFile(String fileName, byte[] content) throws Exception {
        try (FileOutputStream fos = new FileOutputStream(fileName)) {
            fos.write(content);
        }
    }
}

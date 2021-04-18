package classTests;

import models.FileReader;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.logging.Logger;

public class FileReaderTest {

    private static final Logger log = Logger.getLogger(FileReader.class.getName());

    @BeforeAll
    static void setup() {
        log.info("Starting test for class FileReader");
    }

    @Test
    @DisplayName("Test.: File load")
    void fileLoad(){
        log.info("Starting test File Load");
        FileReader fileReader = new FileReader("tes2");
        log.info("Getting content");
        log.info(fileReader.getFileContent());
    }

}

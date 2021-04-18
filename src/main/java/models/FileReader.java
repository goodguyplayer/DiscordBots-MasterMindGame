package models;

import java.io.File;
import java.util.Scanner;

/**
 * Class that loads a file and return the data.
 *
 * @author Nathan (goodguyplayer)
 * @Version 0.1
 * @since 2021-04-18
 */

/*
Changelog.:

Version 0.1.:
    - attempt updated

Version 0.:
    - Added vars
    - Added method loadFile
    - Added method getFile
    - Added method getFileContent

 */
public class FileReader {
    String filename;
    File file;

    /**
     * Constructor. Initializes with the filename and starts a new file to read
     * @param filename name of file
     * @author Nathan (goodguyplayer)
     */
    public FileReader(String filename) {
        this.filename = filename;
        loadFile();
    }

    /**
     * private method made to load file.
     * In case there's an error, print error.
     * @author Nathan (goodguyplayer)
     */
    private void loadFile() {
        try {
            this.file = new File(this.filename);
        } catch (Exception e){
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * gets the file object
     * @return file object
     * @author Nathan (goodguyplayer)
     */
    public File getFile() {
        return file;
    }

    /**
     * gets the content of the file.
     * @return content of file
     * @author Nathan (goodguyplayer)
     */
    public String getFileContent() {
        try {
            Scanner myReader = new Scanner(this.file);
            String data = new String();
            while (myReader.hasNextLine()) {
                data += myReader.nextLine();
            }
            myReader.close();
            return data;
        } catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            return "";
        }
    }
}

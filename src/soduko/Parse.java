
package soduko;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Parse {
    private String[] lines = new String[9]; //String array that holds the contents of the soduko.in file
    /**
     * Reads and stores (in the lines array) the contents of soduko.in 
     * @throws IOException 
     */
    public void parseFile() throws IOException{
    File input = new File("soduko.in");
    input.createNewFile();
    String line;
    BufferedReader br = new BufferedReader(new FileReader(input));
    int index = 0;
    while((line = br.readLine()) != null){
     lines[index] = line;
     index++;
    }
    }
    
    /**
     * Getter for the String array lines
     * @return- the lines array 
     */
    public String[] getFileContents(){
        return lines;
    }
}

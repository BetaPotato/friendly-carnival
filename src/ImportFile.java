
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


/**
 *
 * @author lee_866317
 * Fuck Off NSA
 */
public class ImportFile {
    public VulnerabilityMap vulMap = new VulnerabilityMap();
    public void importVul(String fileName) { //To reset, change type of test from Vulnerability to ScanCommand
        Scanner data = null;
        
        try {
           data = new Scanner(new File(fileName)); 
        }
        catch (FileNotFoundException ex){
            return;
        }
        
        // Read through each line and capture the data
        while (data.hasNextLine()){
            String line = data.nextLine();
            Scanner i = new Scanner(line);
            i.useDelimiter(",");
            
            //Read in each value on the line and create a team
            
            int id = i.nextInt();
            boolean whichOS = i.next().equalsIgnoreCase("linux");
            String certifiedComputers = i.next();
            String findCommand = i.next();
            String compareOutput = i.next();
            String createCommand = i.next();
            int worthPoints = i.nextInt();
            String name = i.next();
            boolean isPenalty = i.next().equalsIgnoreCase("true");
            
            Vulnerability test = new Vulnerability(whichOS, certifiedComputers, findCommand, compareOutput, createCommand, worthPoints, name, isPenalty);
            vulMap.getScans().put(id, test);
            
            
        }
    }
}

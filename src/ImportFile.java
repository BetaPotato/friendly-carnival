
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author lee_866317
 */
public class ImportFile {
    public VulMap vulMap = new VulMap();
    public void importVul(String fileName) {
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
            String name = i.next();
            String commandFind = i.next();
            
            ScanCommand scanCommands = new ScanCommand(id, name, commandFind);
            
            vulMap.getScans().put(id, scanCommands);
        }
    }
}

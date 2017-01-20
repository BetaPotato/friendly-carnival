
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.InputMismatchException;
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
    public VulnerabilityMap vulMap = new VulnerabilityMap();
    public int importVul(String fileName) {
        Scanner data = null;
        
        try {
           data = new Scanner(new File(fileName)); 
        }
        catch (FileNotFoundException ex){
            return 1;
        }
        
        // Read through each line and capture the data
        try {
            while (data.hasNextLine()){
                String line = data.nextLine();
                Scanner i = new Scanner(line);
                i.useDelimiter(",");
            
                //Read in each value on the line and create a team
            
                String name = i.next();
                boolean whichOS = i.nextBoolean();
                Scanner certComp = new Scanner(i.next()).useDelimiter(";");
                String certifiedComputers = certComp.next();
                String findCommand = i.next();
                String compareOutput = i.next();
                String createCommand = i.next();
                int worthPoints = i.nextInt();
                boolean isPenalty = i.nextBoolean();
            
                Vulnerability test = new Vulnerability(whichOS, certifiedComputers, findCommand, compareOutput, createCommand, worthPoints, name, isPenalty);
                vulMap.getScans().put(name, test);
            }
        }
        catch (InputMismatchException ex) {
            return 2;
        }
        return 0;
    }
}


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author lee_866317
 */

public class Windows_DB {
    //recieve list of vulnerabilites from Scoring Bot
    List <String> VulList = new ArrayList();
    
    importVul("Vulnerabilites.csv");
    
    private void importVul(String fileName) {
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
            String name = i.next();
            VulList.add(name);
        }
    }
    System.out.println(VulList);
}



import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
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
    
    
    public Windows_DB(String fileName, ArrayList arrayName){
        importVulnerabilities imp = new importVulnerabilities();
        imp.importVul(fileName, arrayName);
        
    }
}
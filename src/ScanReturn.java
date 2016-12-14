/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author lee_866317
 */
public class ScanReturn {
    public ImportFile scan = new ImportFile();
    ScanReturn()
    {
        
    }
    
    public String returnScans(String file)
    {
        scan.importVul(file);
        Integer key = scan.vulMap.scans.firstKey();
        ScanCommand test = scan.vulMap.getScans().get(key);
        String name = test.getName();
        return name;
        
    }

}


import java.util.ArrayList;
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
public class VulMap {
    public TreeMap<Integer, ScanCommand> scans;
    
    public VulMap(){
        scans = new TreeMap();
    }
    
    public TreeMap<Integer, ScanCommand> getScans() {
        return scans;
    }
    
}

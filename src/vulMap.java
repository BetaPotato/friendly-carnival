
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
public class vulMap {
    public TreeMap<Integer, scanCommands> scans;
    
    public vulMap(){
        scans = new TreeMap();
    }
    
    public TreeMap<Integer, scanCommands> getScans() {
        return scans;
    }
    
}

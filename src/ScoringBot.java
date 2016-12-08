
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *This is the ScoringBot Object, and will do things like decide what vuln to add if there aren't enough, live scoring of computer, and make the good sound when accomplished. 
 * @author Ryan
 */
public class ScoringBot implements Runnable
{
    private final boolean whichOS;                //This has 2 forms, false for Windows and true for Linux
    public static final boolean LINUX = true;     //an answer for whichOS
    public static final boolean WINDOWS = false;  //an answer for whichOS
    private boolean continueRunning = true;
    private final int totalPoints;
    private int currentPoints;

    private final List<Vulnerability> vulns;
    private List<Vulnerability> solvedVulns = new ArrayList<Vulnerability>();
    
    private final ComputerConnection connect;
    private final File savefile;
    private final String pathGoodSound = "goodSound";
    private final String pathBadSound = "badSound";
    
    private final ArrayList<RemoteArgs> executeSoundLinux;
    private final ArrayList<RemoteArgs> executeSoundWindows;

    public ScoringBot(ArrayList<Vulnerability> vulns, boolean whichOS)
    {
        this.vulns = vulns;
        this.whichOS = whichOS;
        connect = new ComputerConnection(whichOS);
        savefile = new File("savefile.txt");
        currentPoints = 0;
        
        int totals = 0;
        for (int i = 0; i < vulns.size(); i++)
        {
            totals += vulns.get(i).pointsWorth();
        }
        totalPoints = totals;
        
        //These need to be changed so they actually are able to be executed
        executeSoundLinux = new ArrayList<RemoteArgs>();
        executeSoundWindows = new ArrayList<RemoteArgs>();
    }
    
    protected synchronized void changeRunning(boolean changeto)
    {
        continueRunning = changeto;
    }
    
    protected synchronized boolean canRun()
    {
        return continueRunning;
    }
    
    //@Override
    public void run()
    {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        while (canRun())
        {
            try {
                FileInputStream fis = new FileInputStream(savefile);
                try (ObjectInputStream ois = new ObjectInputStream(fis)) {
                    solvedVulns = (ArrayList<Vulnerability>) ois.readObject();
                }
            } catch (Exception ex) {}
            
            
            
            //clears out the information in the save file and add in all of the vulnerabilitites
            try
            {
                FileOutputStream fos = new FileOutputStream(savefile);
                try (ObjectOutputStream oos = new ObjectOutputStream(fos))
                {
                    oos.writeObject(solvedVulns);
                }
            } catch (Exception ex) {}
        }
    }
    //Ryan please add in something to pass vulnerabilities in an Array List into "Windows_DB.java"
    //Windows_DB will take the vulnerability and return the solution to display into the GUI
    //Thanks, Jack
    
}

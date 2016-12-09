
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
        //CHANGE
        executeSoundLinux = new ArrayList<RemoteArgs>();
        executeSoundLinux.add(new RemoteArgs("", false));
        executeSoundWindows = new ArrayList<RemoteArgs>();
        executeSoundLinux.add(new RemoteArgs("start", false));
    }
    
    protected synchronized void changeRunning(boolean changeto)
    {
        continueRunning = changeto;
    }
    
    protected synchronized boolean canRun()
    {
        return continueRunning;
    }
    
    private void executeSound (String soundPath)
    {
        if (whichOS)    //if Linux, true. If windows, false
        {
            executeSoundLinux.add(new RemoteArgs(soundPath, false));
            connect.sendMessage(executeSoundLinux);
            executeSoundLinux.remove(executeSoundLinux.size()-1);
        }
        else
        {
            executeSoundWindows.add(new RemoteArgs(soundPath, false));
            connect.sendMessage(executeSoundWindows);
            executeSoundWindows.remove(executeSoundWindows.size()-1);
        }
    }
    
    //@Override
    public void run()
    {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
       //This goes and tries to read anything that is stored from a previous round in the file
        while (canRun())
        {
            try {
                FileInputStream fis = new FileInputStream(savefile);
                try (ObjectInputStream ois = new ObjectInputStream(fis)) {
                    solvedVulns = (ArrayList<Vulnerability>) ois.readObject();
                }
            } catch (Exception ex) {}
            
            for (int i = 0; i < vulns.size(); i++)  //for every problem, look and try and solve it
            {
                try
                {
                    String alloutput = connect.sendMessage(vulns.get(i).toFindVuln());
                    String[] info = alloutput.split(":");
                    
                    for (int a = 0; a < info.length; a++)
                    {
                        if (info[a].equals(vulns.get(i).toCompare()))
                        {
                            //have it do good things
                        }
                    }
                    
                }
                catch (Exception e){e.printStackTrace();}
            }
            
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
}

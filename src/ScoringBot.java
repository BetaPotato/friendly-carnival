
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
 *This is the ScoringBot Object, and will do live scoring of computer and make the good sound when accomplished and bad when backtracking. 
 * @author Ryan
 */
public class ScoringBot implements Runnable
{
    private final boolean whichOS;                //This has 2 forms, false for Windows and true for Linux
    public static final boolean LINUX = true;     //an answer for whichOS
    public static final boolean WINDOWS = false;  //an answer for whichOS
    private boolean continueRunning = true;
    //private final int totalPoints;
    //private int currentPoints;

    private final List<Vulnerability> vulns;
    private List<Vulnerability> solvedVulns = new ArrayList<>();
    
    private final Window GUI;
    private final ComputerConnection connect;
    private final File savefile;
    private final String pathGoodSound = "goodSound";
    private final String pathBadSound = "badSound";
    
    private final ArrayList<RemoteArgs> executeSoundLinux;
    private final ArrayList<RemoteArgs> executeSoundWindows;

    /**
     * 
     * @param vulns This is an ArrayList of Vulnerabilities that the ScoringBot will be looking for on the computer to see if they have been solved or not.
     * @param whichOS This is the boolean of which Operating System the program is currently on. Can only be either {@link #WINDOWS} or {@link #LINUX}.
     * @param GUI This is a reference to the GUI that will get called back to notify it when a {@link Vulnerability} has been either solved or unsolved.
     */
    public ScoringBot(ArrayList<Vulnerability> vulns, boolean whichOS, Window GUI)
    {
        this.vulns = vulns;
        this.whichOS = whichOS;
        connect = new ComputerConnection(whichOS);
        savefile = new File("savefile.txt");
        /*currentPoints = 0;
        
        int totals = 0;
        for (int i = 0; i < vulns.size(); i++)
        {
            totals += vulns.get(i).pointsWorth();
        }
        totalPoints = totals;
        */
        
        //These need to be changed so they actually are able to be executed
        //CHANGE
        executeSoundLinux = new ArrayList<>();
        executeSoundLinux.add(new RemoteArgs("", false));
        executeSoundWindows = new ArrayList<>();
        executeSoundLinux.add(new RemoteArgs("start", false));
        
        this.GUI = GUI;
    }
    
    /**
     * A synchronized method that is used to set if the ScoringBot is allowed to continue to run.
     * @param changeto the boolean to set either the ScoringBot to be allowed to run or not.
     */
    protected synchronized void changeRunning(boolean changeto)
    {
        continueRunning = changeto;
    }
    
    /**
     * Synchronized method used to determine if the ScoringBot is allowed to continue to run.
     * @return {@link #continueRunning}.
     */
    protected synchronized boolean canRun()
    {
        return continueRunning;
    }
    
    /**
     * Executes the sound that is given on the computer.
     * @param soundPath The path to the sound file (preferably .mp3 file).
     */
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
    @Override
    public void run()
    {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
       //This goes and tries to read anything that is stored from a previous round in the file
        try
        {
            while (canRun())
            {
                try {
                    FileInputStream fis = new FileInputStream(savefile);
                    try (ObjectInputStream ois = new ObjectInputStream(fis)) {
                        solvedVulns = (ArrayList<Vulnerability>) ois.readObject();
                    }
                } catch (Exception ex) {}

                boolean worked=false;   //stored if either the output, error, or status effect was met for that iteration
                for (int i = 0; i < vulns.size(); i++)  //for every problem, look and try and solve it
                {
                    worked=false;
                    try
                    {
                        String alloutput = connect.sendMessage(vulns.get(i).toFindVuln());
                        String[] info = alloutput.split(":");

                        Test1:
                        for (String info1 : info) {
                            if (info1.equals(vulns.get(i).toCompare())) {
                                //have it do good things
                                if (!solvedVulns.contains(vulns.get(i)))     //If the solvedVulns doesn't already have 
                                {
                                    //if (!vulns.get(i).isPenalty())
                                        //executeSound(pathGoodSound);
                                    //else
                                        //executeSound(pathBadSound);
                                    solvedVulns.add(vulns.get(i));
                                    GUI.vulnSolved(vulns.get(i));
                                }
                                worked=true;
                                break Test1;
                            }
                        }
                        if (!worked && solvedVulns.contains(vulns.get(i)))  //If the command doesn't have the correct output but was solved before, ding them
                        {
                            //if (!vulns.get(i).isPenalty())
                               // executeSound(pathBadSound);
                           // else
                               // executeSound(pathGoodSound);
                            solvedVulns.remove(vulns.get(i));
                            GUI.vulnUnSolved(vulns.get(i));
                        }

                    }
                    catch (Exception e){}
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
        finally
        {
            return;
        }
    }
}

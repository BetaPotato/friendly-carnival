
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * This is an interface that has all of the needed variables and methods for the ScoringBot code to work properly.
 * @author Ryan
 */
public interface Window
{

    static final ExecutorService exec = Executors.newSingleThreadExecutor();
    
    /**
     * 
     * @param vulns This is an ArrayList of Vulnerabilities that the ScoringBot will be looking for on the computer to see if they have been solved or not.
     * @param whichOS a boolean of which Operating System the Vulnerability is allowed to <b>run on</b>, NOT that the computer is running on. Should only be {@link #WINDOWS} or {@link #LINUX}.
     * @return A {@code Future<?>} that is given back from the {@code ExecutorService} which is the only real way to access the {@link ScoringBot} Thread.
     */
    default Future<?> startScoringBot(ArrayList<Vulnerability> vulns, boolean whichOS, Window GUI)
    {
        return exec.submit(new ScoringBot(vulns, whichOS, GUI));
    }
    
    /**
     * This is the method that must be called in order to stop the ScoringBot properly. 
     * @param fut This is the {@code Future<?>} that must be given back from {@link StartScoringBot(ArrayList<Vulnerability> vulns, boolean whichOS)}.
     */
    default void stopScoringBot(Future<?> fut)
    {
        fut.cancel(true);
    }
    
    /**
     * This method needs to be implemented to allow the {@link ScoringBot} to call the {@code Window} and update the GUI with the proper score.
     * @param vuln A {@link Vulnerability} that has been solved and needs to be updated in the GUI.
     */
    void vulnSolved(Vulnerability vuln);
    /**
     * This method needs to be implemented to allow the {@link ScoringBot} to call the {@code Window} and update the GUI with the proper score.
     * @param vuln A {@link Vulnerability} that has been unsolved and needs to be updated in the GUI.
     */
    void vulnUnSolved(Vulnerability vuln);
}

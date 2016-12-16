
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
    
    default Future<?> startScoringBot(ArrayList<Vulnerability> vulns, boolean whichOS)
    {
        return exec.submit(new ScoringBot(vulns, whichOS, this));
    }
    
    default void stopScoringBot(Future<?> fut)
    {
        fut.cancel(true);
    }
    
    void vulnSolved(Vulnerability vuln);
    void vulnUnSolved(Vulnerability vuln);
}

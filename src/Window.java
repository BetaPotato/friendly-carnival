
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
 *
 * @author Ryan
 */
public interface Window
{

    static final ExecutorService exec = Executors.newSingleThreadExecutor();
    
    default Future<?> startScoringBot(ArrayList<Vulnerability> vulns, boolean whichOS)
    {
        return exec.submit(new ScoringBot(vulns, whichOS));
    }
    
    default void stopScoringBot(Future<?> fut)
    {
        fut.cancel(true);
    }
}

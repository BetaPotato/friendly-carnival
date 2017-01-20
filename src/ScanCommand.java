
import java.util.ArrayList;


/**
 *
 * @author lee_866317
 * This program is a class that stores the TreeMap ID, Vulnerability Name, and CMD Text
 * 
 * Is this class a copy of Vulnerability?
 */
public class ScanCommand {
    private boolean whichOS;
    private String certifiedComputers;
    private String findCommand;
    private String compareOutput;
    private String createCommand;
    private int worthPoints;
    private String name;
    private boolean isPenalty;

    
    public ScanCommand(boolean whichOS, String certifiedComputers, String findCommand, String compareOutput, String createCommand, int worthPoints, String name, boolean isPenalty)
    {
        this.whichOS = whichOS;
        this.certifiedComputers = certifiedComputers;
        this.findCommand = findCommand;
        this.compareOutput = compareOutput;
        this.createCommand = createCommand;
        this.worthPoints = worthPoints;
        this.name = name;
        this.isPenalty = isPenalty;
    }
    
    public ScanCommand()
    {
        whichOS = false;
        certifiedComputers = null;
        findCommand = null;
        compareOutput = "";
        createCommand = null;
        worthPoints = 0;
        name = "";
        isPenalty = false;
    }
    public String getName() {
        return name;
    }   

    public String getFindCommand(){
        return findCommand;
    }
    
    public boolean getOS(){
        return whichOS;
    }
    public String getCreateCommand() {
        return createCommand;
    }   

    public String getCertifiedComputers(){
        return certifiedComputers;
    }
    
    public String getCompareOutput(){
        return compareOutput;
    }
    
    public int getWorthPoints(){
        return worthPoints;
    }
    
    public boolean getIsPenalty(){
        return isPenalty;
    }
}

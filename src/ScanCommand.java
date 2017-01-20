


/**
 *
 * @author lee_866317
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
        return "Vulnerability Name: "+name;
    }   

    public String getFindCommand(){
        return "The command to find this vulnerability is: "+findCommand;
    }
    
    public String getOS(){
        if(whichOS == true)
        {
            return "This vulnerablity exists in Windows";
        }
        else
        {
            return "This vulnerability exists in Linux";
        }
    }
    public String getCreateCommand() {
        return "The command to create this vunlerability is: "+createCommand;
    }   

    public String getCertifiedComputers(){
        return "The following commands work in "+certifiedComputers;
    }
    
    public String getCompareOutput(){
        return compareOutput;
    }
    
    public String getWorthPoints(){
        return "This vulnerability is worth "+worthPoints+" points.";
    }
    
    public boolean getIsPenalty(){
        return isPenalty;
    }
}

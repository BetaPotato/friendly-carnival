/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *This is the object class, which holds stuff like the information about a vuln like what command to execute to find it, what that 
 * output compares to, which computers the vuln works on.
 * @author Ryan
 */
public class Vulnerabilities
{
    private final String[] certifiedComputers;    //This holds all of the computers the vuln. can work on
    private final String findCommand;             //This is the command that can see if the vuln is solved or not
    private final String createCommand;           //This is the command to create this vuln if necessary
    private final boolean whichOS;                //This has 2 forms, false for Windows and true for Linux
    public static final boolean LINUX = true;     //an answer for whichOS
    public static final boolean WINDOWS = false;  //an answer for whichOS
    public static final String UBUNTU12 =  "UBUNTU12";  //type of certified Computer
    public static final String UBUNTU14 = "UBUNTU14";
    public static final String WINDOWS7 = "WINDOWS7";
    
    public Vulnerabilities(boolean whichOS, String[] certifiedComputers, String findCommand, String createCommand)
    {
        this.certifiedComputers = certifiedComputers;
        this.findCommand = findCommand;
        this.whichOS = whichOS;
        this.createCommand = createCommand;
    }
    
    protected boolean isComputerCertified(String computer)
    {
        for (int i = 0; i < certifiedComputers.length; i++)
        {
            if (certifiedComputers[i].equals(computer))
                return true;
        }
        return false;
    }
}

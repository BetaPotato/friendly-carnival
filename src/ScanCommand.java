
/**
 *
 * @author lee_866317
 * This program is a class that stores the TreeMap ID, Vulnerability Name, and CMD Text
 */
public class ScanCommand {
    private int id;
    private String name;
    private String findCommand;
    private boolean os;

    
    public ScanCommand(String name, String command, boolean os)
    {
        this.name = name;
        this.findCommand = name;
        this.os = os;
    }
    
    public ScanCommand()
    {
        name = "";
        findCommand = "";
        os = true;
    }
    
    public String getName() {
        return name;
    }

    public String getCommand(){
        return name;
    }
    
    public boolean getOS(){
        return os;
    }
}

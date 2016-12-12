
/**
 *
 * @author lee_866317
 * This program is a class that stores the TreeMap ID, Vulnerability Name, and CMD Text
 */
public class ScanCommand {
    private int id;
    private String name;
    private String findCommand;

    
    public ScanCommand(int id, String name, String command)
    {
        this.id = id;
        this.name = name;
        this.findCommand = name;
    }
    
    public ScanCommand()
    {
        id = 0;
        name = "";
        findCommand = "";
    }
    
    public String getName() {
        return name;
    }

    public String getCommand(){
        return name;
    }
    
    public int getID(){
        return id;
    }
}
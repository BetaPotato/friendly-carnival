
/**
 *
 * @author lee_866317
 * This program is a class that stores the TreeMap ID, Vulnerability Name, and CMD Text
 */
public class ScanCommands {
    private int id;
    private String name;
    private String findCommand;

    
    public ScanCommands(int id, String name, String command)
    {
        this.id = id;
        this.name = name;
        this.findCommand = name;
    }
    
    public ScanCommands()
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

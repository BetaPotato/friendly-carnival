/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author lee_866317
 */
public class scanCommands {
    private int id;
    private String name;
    private String findCommand;

    
    public scanCommands(int id, String name, String command)
    {
        this.id = id;
        this.name = name;
        this.findCommand = name;
    }
    
    public scanCommands()
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

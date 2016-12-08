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
    private String name;
    private String findCommand;
    
    public scanCommands(String name, String command)
    {
        this.name = name;
        this.findCommand = name;
    }
    
    public scanCommands()
    {
        name = "";
        findCommand = "";
    }
    
    public String getName() {
        return name;
    }

    public String getCommand(){
        return name;
    }
}

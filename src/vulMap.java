
import java.util.ArrayList;
import java.util.TreeMap;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author lee_866317
 */
public class vulMap {
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 
/**
 *
 * @author lee_866317
 */
    public TreeMap<Integer, team> teams;
    public ArrayList<league> leagues;
    
    public model(){
        teams = new TreeMap();
        leagues = new ArrayList();
    }
    
    public TreeMap<Integer, team> getTeams() {
        return teams;
    }

    public void setTeams(TreeMap<Integer, team> teams) {
        this.teams = teams;
    }

    public ArrayList<league> getLeagues() {
        return leagues;
    }

    public void setLeagues(ArrayList<league> leagues) {
        this.leagues = leagues;
    }
}

}

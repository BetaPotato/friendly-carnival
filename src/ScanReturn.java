/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author lee_866317

 */
public class ScanReturn {
    public ImportFile offm8 = new ImportFile();
    public VulnerabilityMap vulMap = new VulnerabilityMap();
    public ScanReturn()
    {
    }
    
    public String returnScans(String file, int number)
    {
        offm8.importVul(file);
        ScanCommand test = offm8.vulMap.getScans().get(number);
        String os = test.getOS();
        String name = test.getName();
        String findCommand = test.getFindCommand();
        String createCommand = test.getCreateCommand();
        String certifiedComputers = test.getCertifiedComputers();
        String worthPoints = test.getWorthPoints();
        
        return name+"\n"+os+"\n"+certifiedComputers+"\n"+findCommand+"\n"+createCommand+"\n"+worthPoints;
        
    }

}

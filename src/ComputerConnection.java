
import java.util.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

    /*
     * To change this license header, choose License Headers in Project Properties.
     * To change this template file, choose Tools | Templates
     * and open the template in the editor.
     */

    /**
     *This class is what needs to be called in order to look at the computer and see anything there. Both Training and Scoringbot sides 
     * should call this Object.
     * @author Ryan
     */
    public class ComputerConnection
    {
        
        /**
         * this is the String version of the {@link #arguments}. Converted in the {@link #convertArgumentToString()} method.
         */
        protected ArrayList<String> command = new ArrayList<>();	//needs to be set in child class, The command that would be run, used for Key
        
        /**
         * This is the boolean of which Operating System the program is currently on. Can only be either {@link #WINDOWS} or {@link #LINUX}.
         */
        private boolean whichOS;                //This has 2 forms, false for Windows and true for Linux
        /**
         * This is the variable that needs to be passed to ComputerConnection to determine which OS the program is on. This is the variable for all Linux Operating Systems.
         */
        public static final boolean LINUX = true;     //an answer for whichOS
        /**
         * This is the variable that needs to be passed to ComputerConnection to determine which OS the program is on. This is the variable for all Windows Operating Systems.
         */
        public static final boolean WINDOWS = false;  //an answer for whichOS
        
        /**
         * The String of the hostname of the computer you want to connect to.
         */
        //protected String host;
        /**
         * The information of how to login to the remote computer.
         */
        //protected String login;
        /**
         * An {@code ArrayList}<{@link RemoteArgs}> which contains all of the sections of the command you want to execute on the remote computer.
         */
        protected ArrayList<RemoteArgs> arguments;
        /**
         * Stores the error of the command
         */
        private String standardError = "";	//used to hold the standard Error
        /**
         * Stores the output of the given command
         */
        private String standardOut = "";		//used to hold the output
        /**
         * Stores the status integer that the command exited with
         */
        private int status = 1;				//used to hold the status

        /**
         * The ProcessBuilder that will be used to create the process to execute the command on the computer.
         */
        private ProcessBuilder pb;
        //private String username = "";
        //private String domainName = "";
        //private String password = "";
        //private String hostname = "";
        /**
         * The amount of time that the program will allow a command to execute before it decides to kill the process.
         */
        private final long executeTime;

         //@param hostname - The String of the hostname of the computer you want to connect to.
         //@param loginInformation - The information that is used in order to log into the remote computer (like password, keyPath, username).
         
       /**
        * This creates the ComputerConnection class, which will execute a given command on the computer and return the output, error and status.
        * @param whichOS The boolean for which Operating System of the current computer.
        */
        public ComputerConnection(boolean whichOS)//, String hostname, String loginInformation)
        {
            //host = hostname;
            //login = loginInformation;
            //arguments = argument;
            this.whichOS = whichOS;
            executeTime = 60000;

            //System.out.println("" + Thread.currentThread() +": I'm the Constructor for the new Thread. Here is everything: rem: +");
        }

        /**
         * Takes the {@link arguments} and converts it into a String.
         * @throws InterruptedException when the command is not able to execute within the @{link executeTime}.
         */
        protected void convertArgumentToString() throws InterruptedException
        //converts the ArrayList<RemoteArgs> into a String based on if they need to have embedded quotes around them or not.
        {
                if(!Thread.currentThread().isInterrupted())	
                {
                    String[] commandseg;
                        for (int i = 0; i < arguments.size(); i++)
                        {
                                //if(i != 0)	//so that the command that is sent to SSH or Windows is properly spaced out, there isn't an extra space at beginning or ending and all the separate arguments have spaces
                                  //      commandseg += " ";
                                if(!Thread.currentThread().isInterrupted())	//if the thread is interrupted, tell who needs to know that died and then kill self
                                {
                                        if(!arguments.get(i).isInQuotes())
                                                commandseg = arguments.get(i).getArgument().split(" "); //commandseg += arguments.get(i).getArgument();
                                        else
                                        {
                                                commandseg = new String[1];
                                                commandseg[0] = ("\"" + arguments.get(i).getArgument() + "\"");
                                        }
                                        Thread.yield();
                                        
                                        command.clear();
                                        Thread.yield();
                                        command.addAll(Arrays.asList(commandseg));
                                }
                                else
                                        throw new InterruptedException();
                        }
                        System.out.println(command);
                }
                else
                        throw new InterruptedException();
        }
 
        //The ArrayList of RemoteArgs that holds the arguments that will be executed on the computer.
        //Returns the output of {@link sendMessage()}
        /**
         * The method to set the Operating System of the current computer
         * @param whichOS the boolean of which Operating System the program is currently on. Can only be either {@link WINDOWS} or {@link LINUX}.
         */
        protected void setOS(boolean whichOS)
        {
            this.whichOS = whichOS;
        }
        
        /**
         * This method executes the given command on the computer, and captures the standard output, error and status and returns it.
         * @param argument The command that will be executed on the computer.
         * @return Returns the output of the command executed on the computer, in the format of {@link #standardOut}:{@link #standardError}:{@link #status}
         */
        protected String sendMessage(ArrayList<RemoteArgs> argument)
        {
            //host = hostname;
            //login = loginInformation;
            arguments = argument;
            //this.whichOS = whichOS;
            return sendMessage();
        }
        
        /**
         * This method executes the given command on the computer, and captures the standard output, error and status and returns it.
         * @param argument The command that will be executed on the computer split up into sections of surrounded in quotes and not in quotes
         * @param whichOS his is the boolean of which Operating System the program is currently on. Can only be either {@link #WINDOWS} or {@link #LINUX}.
         * @return Returns the output of the command executed on the computer, in the format of {@link #standardOut}:{@link #standardError}:{@link #status}
         */
        protected String sendMessage(ArrayList<RemoteArgs> argument, boolean whichOS)
        {
            //host = hostname;
            //login = loginInformation;
            arguments = argument;
            this.whichOS = whichOS;
            return sendMessage();
        }
        
        /**
         * This method executes the given command on the computer, and captures the standard output, error and status and returns it.
         * @return Returns the standard output, error, and status in that order, separated by :
         */
        private String sendMessage()		//goes out to SSH or Windows
        {
                //arguments.clear();
                Process p = null;
                try
                {
                        System.out.println("Breaking up the information given and sorting it");
                        convertArgumentToString();
                        System.out.println(command);
                        List<String> allTogether = new ArrayList<>();	//sets up the List that is given to the ProcessBuilder to use to execute winexe
                        /*allTogether.add("/mbs/sbin/winexe");
                        allTogether.add("--debug-stderr");	//no say in matter, must stay. This makes sure that the Errors and the Outputs are separated
                        allTogether.add("--uninstall");
                        allTogether.add("-U");
                        allTogether.add(domainName + "/" + username + "%" + password);
                        System.out.println(allTogether.get(4));
                        allTogether.add("//" + hostname);
                        System.out.println(allTogether.get(5));
                        allTogether.add(command);
                        System.out.println(allTogether.get(6));*/
                        if (whichOS)    //is true when Linux
                        {
                            //add the necessary extra stuff for Linux
                            //allTogether.add("/bin/bash");
                            //allTogether.add("-s");
                            for (int i = 0; i < command.size(); i++)
                            {
                                allTogether.add(command.get(i));
                            }
                        }
                        else
                        {
                            //add the necessary extra stuff to execute Windows
                            allTogether.add("C:\\Windows\\System32\\cmd.exe");
                            allTogether.add("/c"); // has /c start
                            //allTogether.add("start");
                            //allTogether.add("echo");
                            //allTogether.add(command); apparently this causes the entire command to get shoved together in quotes
                            for (int i = 0; i < command.size(); i++)
                            {
                                allTogether.add(command.get(i));
                            }
                            //allTogether.add(">>");
                            //allTogether.add("stuffier.txt");
                            //allTogether.add("pause");
                        }
                        System.out.println(allTogether);

                        pb = new ProcessBuilder(allTogether);	//creates the processBuilder
                        pb.redirectErrorStream(false);	//makes sure that the Error and Output streams are not combined

                        p = pb.start();	//starts the execution, and saves the currently executing thing as a Process
                        
                        if(!p.waitFor(executeTime, TimeUnit.MILLISECONDS))	//waits for the process for a given amount of milliseconds to finish. If not finished within that amount of time, assumed that it died in a horrible way
                        {
                                p.destroyForcibly();	//if waiting for task to finish took too long, then forcibly end the subprocess before saying that bad things happened
                                throw new InterruptedException("The Command could not be executed within a minute");
                        }

                        BufferedReader output = new BufferedReader(new InputStreamReader(p.getInputStream()));	//gets a reader that can read the Out and error from the process
                        BufferedReader error = new BufferedReader(new InputStreamReader(p.getErrorStream()));
                        Thread.yield();
                        standardError = "";
                        standardOut = "";
                        String line;
                        while((line = error.readLine()) != null)	//Keeps looking through the stuff in the pipe until there is nothing left to pull out
                        {
                                standardError += line;
                        }
                        error.close();

                        Thread.yield();

                        while((line = output.readLine()) != null)
                        {
                                standardOut += line;
                        }
                        output.close();

                        try
                        {
                            status = p.exitValue();	//tries to set the status. If can't get the real status, makes best guess
                        }
                        catch(NullPointerException e)
                        {
                                e.printStackTrace();
                                if(!standardOut.equals("") && standardError.equals(""))
                                                status = 0;
                                        else
                                                status = 1;
                        }
                        System.out.println("Disk info: " + standardOut + " And the Error was: " + standardError + " And the Status is: " + status);
                        return(standardOut+":"+standardError+":"+status);	//adds all the information into the Hashmap
                }
                catch(InterruptedException | IOException e)
                {
                        try
                        {
                                if(p.isAlive())
                                        p.destroyForcibly();
                        }
                        catch(Exception a){}
                        e.printStackTrace();
                        throw new NullPointerException("Unable to properly execute command");
                }
        }

            /**
             * Removes any \r that may be in the output/error/status that comes from Windows machines.
             * @param info The status from {@link #setValue(String, String)
             * @return A line feed String.
             */
            protected String check(String info) //[look for the \r and \n]
            // Will return the string of information given in line feed form (gets rid of \r than come from windows, not get rid of \n or \t
            {
                return info.replace("\r", "");
            }
    }

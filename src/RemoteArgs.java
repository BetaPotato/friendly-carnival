/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * This class is used in order to store a section of a command that needs to be executed on the remote machine, and if that section does or doesn't need to be in quotes when it gets there.
 * @author Ryan Galligher
 *
 */
public class RemoteArgs
{
	/**
	 * The section of the command as a String.
	 */
	private final String argument;
	/**
	 * If the {@link #argument} needs to be in Quotations or not
	 */
	private final boolean inQuotes;
	
	/**
	 * Creates a RemoteArgs Object with the necessary information.
	 * @param argument - The section of the command as a String.
	 * @param isInQuotes - If the {@link #argument} needs to be in Quotations or not.
	 */
	public RemoteArgs(String argument, boolean isInQuotes)
	{
		this.argument = argument;
		inQuotes = isInQuotes;
	}

	/**
	 * @return If the {@link #argument} needs to be in Quotations or not.
	 */
	public boolean isInQuotes()
	{
		return inQuotes;
	}

	/**
	 * @return The section of the command as a String.
	 */
	public String getArgument()
	{
		return argument;
	}
	
	

}

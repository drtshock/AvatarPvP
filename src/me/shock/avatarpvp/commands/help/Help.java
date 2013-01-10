package me.shock.avatarpvp.commands.help;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class Help 
{

	String dash = ChatColor.DARK_RED + " - " + ChatColor.WHITE;
	String apvp = ChatColor.BLUE + "[" + ChatColor.WHITE + "AvatarPvP" + ChatColor.BLUE + "]" + ChatColor.WHITE + ": ";
	
	public void printHelp(CommandSender sender)
	{
		sender.sendMessage(apvp + "Available commands:");
		sender.sendMessage(ChatColor.GRAY + "/apvp config" + dash + "view config settings.");
		sender.sendMessage(ChatColor.GRAY + "/apvp config reload" + dash + "reload config.");
		sender.sendMessage(ChatColor.GRAY + "/apvp <nation>" + dash + "view a nation's abilities.");
		sender.sendMessage(ChatColor.GRAY + "/apvp list <nation>" + dash + "view a nation's online players.");
		sender.sendMessage(ChatColor.GRAY + "/apvp help" + dash + "view this command list.");
		sender.sendMessage(ChatColor.GRAY + "/bind clear" + dash + "clear abilities from item in hand.");
		
		// Not in Config.class
		sender.sendMessage(ChatColor.GRAY + "/<nation>" + dash + "view a nation's abilities.");
		
		return;
	}
	
}

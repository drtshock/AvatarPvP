package me.shock.avatarpvp.commands.help;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class ListAbilities 
{
	
	String apvp = ChatColor.BLUE + "[" + ChatColor.WHITE + "AvatarPvP" + ChatColor.BLUE + "]" + ChatColor.WHITE + ": ";
	String dash = ChatColor.DARK_RED + " - " + ChatColor.WHITE;
	String fire = ChatColor.DARK_RED + "[" + ChatColor.GRAY + "FireNation" + ChatColor.DARK_RED + "]" + ChatColor.WHITE + ":";
	String earth = ChatColor.GREEN + "[" + ChatColor.GRAY + "EarthNation" + ChatColor.GREEN + "]" + ChatColor.WHITE + ":";
	String air = ChatColor.AQUA + "[" + ChatColor.GRAY + "AirNation" + ChatColor.AQUA + "]" + ChatColor.WHITE + ":";
	String anti = ChatColor.DARK_RED + "[" + ChatColor.GRAY + "FireNation" + ChatColor.DARK_RED + "]" + ChatColor.WHITE + ":";
	String water = ChatColor.BLUE + "[" + ChatColor.GRAY + "WaterNation" + ChatColor.BLUE + "]" + ChatColor.WHITE + ":";

	public void printFireAbilities(CommandSender sender)
	{
		sender.sendMessage(fire + "fireball - shoot 3 fireballs every 10 seconds.");
		sender.sendMessage(fire + "lightning - strike lightning where you are looking during storms.");
		sender.sendMessage(fire + "firebow - bow that always shoots fire arrows.");
		return;
	}
	
	public void printWaterAbilities(CommandSender sender)
	{
		sender.sendMessage(water + "ice - Bow shoots ice arrow.");
		
		return;
	}
	
	public void printEarthAbilities(CommandSender sender)
	{
		sender.sendMessage(earth + "fortify - 5 second sphere of protection.");
		sender.sendMessage(earth + "golem - summon a rock golem to protect you.");
		
		return;
	}
	
	public void printAirAbilities(CommandSender sender)
	{
		sender.sendMessage(air + "fly - have fly ability for 5 seconds.");
		
		return;
	}
	
	public void printAntiAbilities(CommandSender sender)
	{
		sender.sendMessage(anti + "chi - block your target from using bending powers for 15 seconds.");
		sender.sendMessage(anti + "stun - slow your target for 10 seconds.");
		
		return;
	}
}

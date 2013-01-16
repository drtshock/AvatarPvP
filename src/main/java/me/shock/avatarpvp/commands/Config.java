package me.shock.avatarpvp.commands;

import me.shock.avatarpvp.Main;
import me.shock.avatarpvp.commands.help.*;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Config implements CommandExecutor
{

	Help helpClass = new Help();
	ListAbilities abilities = new ListAbilities();
	
    public Main plugin;
	
	public Config(Main instance)
	{
		this.plugin = instance;
	}
	
	String apvp = ChatColor.BLUE + "[" + ChatColor.WHITE + "AvatarPvP" + ChatColor.BLUE + "]" + ChatColor.WHITE + ": ";
	String dash = ChatColor.DARK_RED + " - " + ChatColor.WHITE;
	String fire = ChatColor.DARK_RED + "[" + ChatColor.GRAY + "FireNation" + ChatColor.DARK_RED + "]" + ChatColor.WHITE + ":";
	String earth = ChatColor.GREEN + "[" + ChatColor.GRAY + "EarthNation" + ChatColor.GREEN + "]" + ChatColor.WHITE + ":";
	String air = ChatColor.AQUA + "[" + ChatColor.GRAY + "AirNation" + ChatColor.AQUA + "]" + ChatColor.WHITE + ":";
	String anti = ChatColor.DARK_RED + "[" + ChatColor.GRAY + "FireNation" + ChatColor.DARK_RED + "]" + ChatColor.WHITE + ":";
	String water = ChatColor.BLUE + "[" + ChatColor.GRAY + "WaterNation" + ChatColor.BLUE + "]" + ChatColor.WHITE + ":";



	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if(cmd.getName().equalsIgnoreCase("apvp"))
		{
			if(args.length == 0)
			{
				helpClass.printHelp(sender);
				
				return true;
			}
			
			
		/**
		 * All the commands with 1 args.
		 * nation
		 * help	
		 * config
		 * <nation>
		 */
			
			if(args.length == 1)
			{
				
				if(args[0].equalsIgnoreCase("help"))
				{
					helpClass.printHelp(sender);
					return true;
				}
				if(args[0].equalsIgnoreCase("fire") || args[0].equalsIgnoreCase("firenation"))
				{
					abilities.printFireAbilities(sender);
					return true;
				}
				if(args[0].equalsIgnoreCase("config"))
				{
					printConfig(sender);
				}
				if(args[0].equalsIgnoreCase("fire") || args[0].equalsIgnoreCase("firenation"))
				{
					abilities.printFireAbilities(sender);
				}
				if(args[0].equalsIgnoreCase("earth") || args[0].equalsIgnoreCase("earthnation"))
				{
					abilities.printEarthAbilities(sender);
				}
				if(args[0].equalsIgnoreCase("air") || args[0].equalsIgnoreCase("airnation"))
				{
					abilities.printAirAbilities(sender);
				}
				if(args[0].equalsIgnoreCase("water") || args[0].equalsIgnoreCase("waternation"))
				{
					abilities.printWaterAbilities(sender);
				}
				if(args[0].equalsIgnoreCase("anti") || args[0].equalsIgnoreCase("antination"))
				{
					abilities.printAntiAbilities(sender);
				}
				
				else
				{
					helpClass.printHelp(sender);
				}
			}
			
			/**
			 * All commands with 2 args
			 * config reload
			 * list <nation>
			 */
			if(args.length == 2)
			{
				if(args[0].equalsIgnoreCase("config") && args[1].equalsIgnoreCase("reload"))
				{
					this.plugin.reloadConfig();
					sender.sendMessage(apvp + "Config reloaded.");
				}
				if(args[0].equalsIgnoreCase("list"))
				{
					if(args[1].equalsIgnoreCase("fire") || args[1].equalsIgnoreCase("firenation"))
					{
						Player[] target;
						StringBuilder online = new StringBuilder(1000);
					    int len$ = (target = Bukkit.getServer().getOnlinePlayers()).length;
					    for (int i$ = 0; i$ < len$; i$++) 
					    {
					    	Player p = target[i$];
					        if (p.hasPermission("avatarpvp.fire")) 
					        {
					        	online.insert(online.length(), p.getName());
					        	online.append(", ");
					        }

					    }
					    sender.sendMessage(fire + online);
					}
					if(args[1].equalsIgnoreCase("earth") || args[1].equalsIgnoreCase("earthnation"))
					{
						Player[] target;
						StringBuilder online = new StringBuilder(1000);
					    int len$ = (target = Bukkit.getServer().getOnlinePlayers()).length;
					    for (int i$ = 0; i$ < len$; i$++) 
					    {
					    	Player p = target[i$];
					        if (p.hasPermission("avatarpvp.earth")) 
					        {
					        	online.insert(online.length(), p.getName());
					        	online.append(", ");
					        }

					    }
					    sender.sendMessage(earth + online);
					}
					if(args[1].equalsIgnoreCase("air") || args[1].equalsIgnoreCase("airnation"))
					{
						Player[] target;
						StringBuilder online = new StringBuilder(1000);
					    int len$ = (target = Bukkit.getServer().getOnlinePlayers()).length;
					    for (int i$ = 0; i$ < len$; i$++) 
					    {
					    	Player p = target[i$];
					        if (p.hasPermission("avatarpvp.air")) 
					        {
					        	online.insert(online.length(), p.getName());
					        	online.append(", ");
					        }

					    }
					    sender.sendMessage(air + online);
					}
					if(args[1].equalsIgnoreCase("water") || args[1].equalsIgnoreCase("waternation"))
					{
						Player[] target;
						StringBuilder online = new StringBuilder(1000);
					    int len$ = (target = Bukkit.getServer().getOnlinePlayers()).length;
					    for (int i$ = 0; i$ < len$; i$++) 
					    {
					    	Player p = target[i$];
					        if (p.hasPermission("avatarpvp.water")) 
					        {
					        	online.insert(online.length(), p.getName());
					        	online.append(", ");
					        }

					    }
					    sender.sendMessage(water + online);
					}
					if(args[1].equalsIgnoreCase("anti") || args[1].equalsIgnoreCase("antination"))
					{
						Player[] target;
						StringBuilder online = new StringBuilder(1000);
					    int len$ = (target = Bukkit.getServer().getOnlinePlayers()).length;
					    for (int i$ = 0; i$ < len$; i$++) 
					    {
					    	Player p = target[i$];
					        if (p.hasPermission("avatarpvp.anti")) 
					        {
					        	online.insert(online.length(), p.getName());
					        	online.append(", ");
					        }

					    }
					    sender.sendMessage(anti + online);
					}
					else
					{
						helpClass.printHelp(sender);
					}
				}
				else
				{
					helpClass.printHelp(sender);
				}
			}
			
			/**
			 * Commands with 3 args
			 * None D:
			 */
			if(args.length == 3)
			{
				helpClass.printHelp(sender);
			}
			
			
			/**
			 * Any other amount of args
			 */
			else
			{
				helpClass.printHelp(sender);
			}
			
		}
		
		return true;
	}
	
	
	
	public void printConfig(CommandSender sender)
	{
		double version = plugin.getConfig().getDouble("version");
		long fireballCool = plugin.getConfig().getLong("FireNation.fireball.cooldown");
		long lightningCool = plugin.getConfig().getLong("FireNation.lightning.cooldown");
		int lightningRadius = plugin.getConfig().getInt("FireNation.lightning.radius");
		int lightningDamage = plugin.getConfig().getInt("FireNation.lightning.damage");
		long flyCool = plugin.getConfig().getInt("AirNation.fly.cooldown");
		long fortifycool = plugin.getConfig().getLong("EarthNation.fortify.cooldown");
		long golemcool = plugin.getConfig().getLong("EarthNation.golem.cooldown");
		
		sender.sendMessage(apvp + "Config settings:");
		sender.sendMessage(apvp + "Version: " + version);
		sender.sendMessage(fire + ChatColor.GRAY + "Fire Ball Cooldown: " + fireballCool);
		sender.sendMessage(fire + ChatColor.GRAY + "Lightning Cooldown: " + lightningCool);
		sender.sendMessage(fire + ChatColor.GRAY + "Lightning Radius: " + lightningRadius);
		sender.sendMessage(fire + ChatColor.GRAY + "Lightning Damage: " + lightningDamage);
		sender.sendMessage(air + ChatColor.GRAY + "Fly Cooldown; " + flyCool);
		sender.sendMessage(earth + ChatColor.GRAY + "Fortify Cooldown: " + fortifycool);
		sender.sendMessage(earth + ChatColor.GRAY + "Golem Cooldown: " + golemcool);
		
		
		return;
	}
	
}

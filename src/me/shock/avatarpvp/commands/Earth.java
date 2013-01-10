package me.shock.avatarpvp.commands;

import java.util.ArrayList;

import me.shock.avatarpvp.Main;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Earth implements CommandExecutor
{

	
    public Main plugin;
	
	public Earth(Main instance)
	{
		this.plugin = instance;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		
		String apvp = ChatColor.BLUE + "[" + ChatColor.WHITE + "AvatarPvP" + ChatColor.BLUE + "]" + ChatColor.WHITE + ": ";
		String noperm = apvp + "You don't have permission to use this.";
		
		/**
		 * Quick check if sender is player.
		 */
		if (!(sender instanceof Player))
		{
			sender.sendMessage("[AvatarPvP] Only players can use commands.");
			return true;
		}
		
		Player player = (Player) sender;
		ItemStack itemstack = player.getItemInHand();
		int amount = itemstack.getAmount();
		
		/**
		 * Earth command.
		 * Allows players to bind abilities to items.
		 * Change the lore on bind.
		 * Check if player has permission before they bind.
		 */
		
		if(cmd.getName().equalsIgnoreCase("earth"))
		{
			if(sender.hasPermission("avatarpvp.earth"))
			{
				// Check if they told us which ability they want.
				if(args.length != 1)
				{
					sender.sendMessage(apvp + "Bind different abilities to the item in your hand. Try:");
					sender.sendMessage(apvp + "fortify - 5 second sphere of protection.");
					sender.sendMessage(apvp + "golem - summon a rock golem to protect you.");
					return true;
				}
				
				// Fortify ability.
				else if(args[0].equalsIgnoreCase("fortify"))
				{
					if(sender.hasPermission("avatarpvp.earth.fortify"))
					{
						
						if(amount != 1)
						{
							sender.sendMessage(apvp + "You can only have 1 item at a time.");
							return true;
						}
						else
						{
							ItemMeta meta = itemstack.getItemMeta();
							ArrayList<String> lore = new ArrayList<String>();
							if(!(lore.isEmpty()))
							{
								sender.sendMessage(apvp + "You can't bind more than one ability to an item.");
								return true;
							}
							else
							{
								itemstack.setItemMeta(meta);
								lore.add(ChatColor.GREEN + "Fortify");
								meta.setLore(lore);
								itemstack.setItemMeta(meta);
								sender.sendMessage(apvp + "Successfully binded " + ChatColor.GREEN + "fortify " + ChatColor.WHITE + "to the item in your hand.");
								return true;
							}
						}
					}
					else
					{
						sender.sendMessage(noperm);
						return true;
					}
				}
				
				// Rock Golem ability.
				else if(args[0].equalsIgnoreCase("golem"))
				{
					if(sender.hasPermission("avatarpvp.earth.golem"))
					{
						
						if(amount != 1)
						{
							sender.sendMessage(apvp + "You can only have 1 item at a time.");
							return true;
						}
						else
						{
							ItemMeta meta = itemstack.getItemMeta();
							ArrayList<String> lore = new ArrayList<String>();
							if(!(lore.isEmpty()))
							{
								sender.sendMessage(apvp + "You can't bind more than one ability to an item.");
								return true;
							}
							else
							{
								itemstack.setItemMeta(meta);
								lore.add(ChatColor.GREEN + "Golem");
								meta.setLore(lore);
								itemstack.setItemMeta(meta);
								sender.sendMessage(apvp + "Successfully binded " + ChatColor.GREEN + "golem" + ChatColor.WHITE + " to the item in your hand.");
								return true;
							}
						}
					}
					else
					{
						sender.sendMessage(noperm);
						return true;
					}
				}
			}
			
			else
			{
				sender.sendMessage(apvp + "You are not an earth bender.");
			}
		}
		
		return false;
	}
}

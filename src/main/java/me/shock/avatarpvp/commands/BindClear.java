package me.shock.avatarpvp.commands;

import java.util.ArrayList;
import java.util.List;

import me.shock.avatarpvp.Main;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class BindClear implements CommandExecutor
{

	public Main plugin;
	
	public BindClear(Main instance)
	{
		this.plugin = instance;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		String apvp = ChatColor.BLUE + "[" + ChatColor.WHITE + "AvatarPvP" + ChatColor.BLUE + "]" + ChatColor.WHITE + ": ";
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
		 * Clear the binds from the item
		 * Alias apvp, bind.
		 */
		
		if(cmd.getName().equalsIgnoreCase("bind"))
		{
			if(args.length == 1)
			{
				if(args[0].equalsIgnoreCase("clear"))
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
							List<String> oldLore = meta.getLore();
							oldLore.clear();
							meta.setLore(null);
							itemstack.setItemMeta(meta);
							sender.sendMessage(apvp + "Cleared bind from the item in your hand.");
							return true;
						}
					}
					
				}
				
				else
				{
					player.sendMessage(apvp + "/bind clear - clear binds from the item in hand.");
				}
			}
			
			else if(args.length != 1)
			{
				player.sendMessage(apvp + "/bind clear - clear binds from the item in hand.");
			}
		}
		
		
		return false;
	}
}

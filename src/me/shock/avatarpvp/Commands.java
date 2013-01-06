package me.shock.avatarpvp;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Commands implements CommandExecutor
{
	
	public Main plugin;
	
	public Commands(Main instance)
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
			return false;
		}
		
		/**
		 * Bind command.
		 * Allows players to bind abilities to items.
		 * Change the lore on bind.
		 * Check if player has permission before they bind.
		 */
		
		if(cmd.getName().equalsIgnoreCase("bind"))
		{
			if(args[0].isEmpty())
			{
				sender.sendMessage(apvp + "Use \"/bind\" to bind an ability to the item in hand.");
				return false;
			}
			/**
			 * Some variables we need to get for different subcommands.
			 */
			Player player = (Player) sender;
			ItemStack itemstack = player.getItemInHand();
			int amount = itemstack.getAmount();
			
			/**
			 * Commands for earth benders.
			 */
			if(args[0].equalsIgnoreCase("earth"))
			{
				if(sender.hasPermission("avatarpvp.earth"))
				{
					if(args[1].isEmpty())
					{
						sender.sendMessage(apvp + "Bind different abilities to the item in your hand. Try:");
						sender.sendMessage(apvp + "fortify - 5 second sphere of protection.");
						sender.sendMessage(apvp + "golem - summon a rock golem to protect you.");
						return false;
					}
					
					if(args[1].equalsIgnoreCase("fortify"))
					{
						if(sender.hasPermission("avatarpvp.earth.fortify"))
						{
							
							if(amount != 1)
							{
								sender.sendMessage(apvp + "You can only have 1 item at a time.");
								return false;
							}
							else
							{
								ItemMeta meta = itemstack.getItemMeta();
								ArrayList<String> lore = new ArrayList<String>();
								if(!(lore.isEmpty()))
								{
									sender.sendMessage(apvp + "You can't bind more than one ability to an item.");
									return false;
								}
								else
								{
									lore.add(ChatColor.GREEN + "Fortify");
									itemstack.setItemMeta(meta);
									sender.sendMessage(apvp + "Successfully binded fortify to the item in your hand.");
									return false;
								}
							}
						}
						else
						{
							sender.sendMessage(noperm);
							return false;
						}
					}
				}
				
				else
				{
					sender.sendMessage(apvp + "You are not an earth bender.");
				}
			}
			
			/**
			 * Clear binds from the item in hand.
			 */
			if(args[0].equalsIgnoreCase("clear"))
			{
				ItemMeta meta = itemstack.getItemMeta();
				List<String> oldLore = meta.getLore();
				oldLore.clear();
				sender.sendMessage(apvp + "Cleared bind from the item in your hand.");
				return false;
			}
		}
		
		
		
		return false;
	}

}

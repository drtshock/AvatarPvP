package me.shock.avatarpvp.commands;

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
		
		/**
		 * Clear the binds from the item
		 * Alias apvp, bind.
		 */
		
		if(cmd.getName().equalsIgnoreCase("apvp") || cmd.getName().equalsIgnoreCase("bind"))
		{
			if(args[0].equalsIgnoreCase("clear"))
			{
				ItemMeta meta = itemstack.getItemMeta();
				List<String> oldLore = meta.getLore();
				oldLore.clear();
				meta.setLore(null);
				itemstack.setItemMeta(meta);
				sender.sendMessage(apvp + "Cleared bind from the item in your hand.");
				return true;
			}
			else
			{
				player.sendMessage(apvp + "/bind clear - clear binds from the item in hand.");
			}
		}
		
		
		return false;
	}
}

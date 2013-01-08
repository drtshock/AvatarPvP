package me.shock.avatarpvp;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
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
			return true;
		}
		
		/**
		 * Bind command.
		 * Allows players to bind abilities to items.
		 * Change the lore on bind.
		 * Check if player has permission before they bind.
		 */
		
		if(cmd.getName().equalsIgnoreCase("bind"))
		{
			if(args.length > 2)
			{
				sender.sendMessage(apvp + "/bind - bind an ability to the item in hand.");
				return true;
			}
			
			/**
			 * Some variables we need to get for different subcommands.
			 */
			
			Player player = (Player) sender;
			ItemStack itemstack = player.getItemInHand();
			int amount = itemstack.getAmount();
			
			/**
			 * Commands for earth benders.
			 * Fortify - 5 seconds sphere protection.
			 * Golem - summon a rock golem to protect you.
			 */
			
			if(args[0].equalsIgnoreCase("earth"))
			{
				if(sender.hasPermission("avatarpvp.earth"))
				{
					// Check if they told us which ability they want.
					if(args.length != 2)
					{
						sender.sendMessage(apvp + "Bind different abilities to the item in your hand. Try:");
						sender.sendMessage(apvp + "fortify - 5 second sphere of protection.");
						sender.sendMessage(apvp + "golem - summon a rock golem to protect you.");
						return true;
					}
					
					// Fortify ability.
					else if(args[1].equalsIgnoreCase("fortify"))
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
					else if(args[1].equalsIgnoreCase("golem"))
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
			
			/**
			 * Water bender commands.
			 * Heal - Healing 1 potion for 1 30 seconds.
			 * Make configurable.
			 */
			
			if(args[0].equalsIgnoreCase("water"))
			{
				if(sender.hasPermission("avatarpvp.water"))
				{
					// Check if they forgot to tell us which ability they want.
					if(args.length != 2)
					{
						sender.sendMessage(apvp + "Bind different abilities to the item in your hand. Try:");
						sender.sendMessage(apvp + "ice - Bow shoots ice arrow.");
						return true;
					}
					
					// If they told us then lets give them their ability.
					else
					{
						// IceBow ability.
						if(args[1].equalsIgnoreCase("ice"))
						{
							if(sender.hasPermission("avatarpvp.water.icebow"))
							{
								if(itemstack.getType() == Material.BOW)
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
										lore.add(ChatColor.BLUE + "Ice Bow");
										itemstack.setItemMeta(meta);
										sender.sendMessage(apvp + "Successfully binded " + ChatColor.BLUE + "Ice Bow" + ChatColor.WHITE + "to the item in your hand.");
										return true;
									}
								}
								else
								{
									sender.sendMessage(apvp + "You can only bind that to a bow.");
									return true;
								}
							}
							// No perms :(
							else
							{
								sender.sendMessage(noperm);
								return true;
							}
						}
					}
				}
				
				// No perms :(
				else
				{
					sender.sendMessage(noperm);
					return true;
				}
			}
			
			/**
			 * Fire Bender commands.
			 * Fireball - shoot 3 fireballs every 3 seconds.
			 * Lightning - strike lightning where looking during storms.
			 * Fire Bow - always shoot fire arrows.
			 */
			
			if(args[0].equalsIgnoreCase("fire"))
			{
				if(sender.hasPermission("avatarpvp.fire"))
				{
					// Check if they forgot to tell us which ability they want.
					if(args[1].isEmpty())
					{
						sender.sendMessage(apvp + "Bind different abilities to the item in your hand. Try:");
						sender.sendMessage(apvp + "fireball - shoot 3 fireballs every 10 seconds.");
						sender.sendMessage(apvp + "lightning - strike lightning where you are looking during storms.");
						sender.sendMessage(apvp + "firebow - bow that always shoots fire arrows.");
						return true;
					}
					
					// If they told us then lets give them their ability.
					else
					{
						// Fireball ability.
						if(args[1].equalsIgnoreCase("fireball"))
						{
							if(sender.hasPermission("avatarpvp.fire.fireball"))
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
										lore.add(ChatColor.DARK_RED + "Fireball");
										itemstack.setItemMeta(meta);
										sender.sendMessage(apvp + "Successfully binded " + ChatColor.DARK_RED + "Fireball" + ChatColor.WHITE + "to the item in your hand.");
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
						
						// Lightning ability.
						if(args[1].equalsIgnoreCase("lightning"))
						{
							if(sender.hasPermission("avatarpvp.fire.lightning"))
							{
								if(amount != 1)
								{
									sender.sendMessage(apvp + "You can only have 1 item at a time");
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
										lore.add(ChatColor.DARK_RED + "Lightning");
										itemstack.setItemMeta(meta);
										sender.sendMessage(apvp + "Successfully binded " + ChatColor.DARK_RED + "Lightning" + ChatColor.WHITE + "to the item in your hand.");
										return true;
									}
								}
							}
							// No perms :(
							else
							{
								sender.sendMessage(noperm);
								return true;
							}
						}
						
						// Lightning ability.
						if(args[1].equalsIgnoreCase("firebow"))
						{
							if(sender.hasPermission("avatarpvp.fire.firebow"))
							{
								if(itemstack.getType() == Material.BOW)
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
										lore.add(ChatColor.DARK_RED + "FireBow");
										itemstack.setItemMeta(meta);
										sender.sendMessage(apvp + "Successfully binded " + ChatColor.DARK_RED + "FireBow" + ChatColor.WHITE + "to the item in your hand.");
										return true;
									}
								}
								else
								{
									sender.sendMessage(apvp + "You can only bind that to a bow.");
									return true;
								}
							}
							// No perms :(
							else
							{
								sender.sendMessage(noperm);
								return true;
							}
						}
					}
				}
				
				// No perms :(
				else
				{
					sender.sendMessage(noperm);
					return true;
				}
			}
			
			/**
			 * Air Bender command here.
			 * Fly - have fly ability for 5 seconds.
			 */
			
			if(args[0].equalsIgnoreCase("air"))
			{
				if(sender.hasPermission("avatarpvp.air"))
				{
					// Check if they forgot to tell us which ability they want.
					if(args.length != 2)
					{
						sender.sendMessage(apvp + "Bind different abilities to the item in your hand. Try:");
						sender.sendMessage(apvp + "fly - have fly ability for 5 seconds.");
						return true;
					}
					
					// If they told us then lets give them their ability.
					else
					{
						// Fly ability.
						if(args[1].equalsIgnoreCase("fly"))
						{
							if(sender.hasPermission("avatarpvp.air.fly"))
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
										lore.add(ChatColor.GREEN + "Fly");
										itemstack.setItemMeta(meta);
										sender.sendMessage(apvp + "Successfully binded " + ChatColor.AQUA + "Fly" + ChatColor.WHITE + "to the item in your hand.");
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
				}
				
				// No perms :(
				else
				{
					sender.sendMessage(noperm);
					return true;
				}

			}
			
			
			/**
			 * Anti Bender commands.
			 * Chi - block your target from using bending powers for 15 seconds.
			 * Stun - stun opponent for 3 seconds.
			 */
			
			if(args[0].equalsIgnoreCase("air"))
			{
				if(sender.hasPermission("avatarpvp.anti"))
				{
					// Check if they forgot to tell us which ability they want.
					if(args.length != 2)
					{
						sender.sendMessage(apvp + "Bind different abilities to the item in your hand. Try:");
						sender.sendMessage(apvp + "chi - block your target from using bending powers for 15 seconds.");
						sender.sendMessage(apvp + "stun - slow your target for 10 seconds.");
						return true;
					}
					
					// If they told us then lets give them their ability.
					else
					{
						// Chi Blocker ability.
						if(args[1].equalsIgnoreCase("chi"))
						{
							if(sender.hasPermission("avatarpvp.anti.chi"))
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
										lore.add(ChatColor.GREEN + "Chi Blocker");
										itemstack.setItemMeta(meta);
										sender.sendMessage(apvp + "Successfully binded " + ChatColor.RED + "Chi Blocker" + ChatColor.WHITE + "to the item in your hand.");
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
						
						// Stun ability.
						if(args[1].equalsIgnoreCase("chi"))
						{
							if(sender.hasPermission("avatarpvp.anti.stun"))
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
										lore.add(ChatColor.GREEN + "Electric Glove");
										itemstack.setItemMeta(meta);
										sender.sendMessage(apvp + "Successfully binded " + ChatColor.RED + "Electric Glove" + ChatColor.WHITE + "to the item in your hand.");
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
				}
				
				// No perms :(
				else
				{
					sender.sendMessage(noperm);
					return true;
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
				meta.setLore(null);
				itemstack.setItemMeta(meta);
				sender.sendMessage(apvp + "Cleared bind from the item in your hand.");
				return true;
			}
		}
		
		/**
		 * End of commands. Finally :)
		 */
		return false;
	}
}
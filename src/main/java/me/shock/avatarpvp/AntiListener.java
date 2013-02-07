package me.shock.avatarpvp;

import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

public class AntiListener implements Listener 
{

	public Main plugin;
	
	public AntiListener(Main instance)
	{
		this.plugin = instance;
	}
	
	HashMap<String, Long> chi = new HashMap<String, Long>();
	HashMap<String, Long> stun = new HashMap<String, Long>();
	
	long chiCool = (long) 30;
	long stunCool = (long) 30;
	
	String apvp = ChatColor.BLUE + "[" + ChatColor.WHITE + "AvatarPvP" + ChatColor.BLUE + "]" + ChatColor.WHITE + ": ";
	
	/**
	 * Listen to anti abilities.
	 * chi - block targets abilities.
	 * stun - freeze player for a few seconds.
	 */
	
	@EventHandler
	public void onHit(EntityDamageByEntityEvent event)
	{
		Entity entity = event.getEntity();
		Entity hitter = event.getDamager();
		if((entity.getType() == EntityType.PLAYER) && (hitter.getType() == EntityType.PLAYER))
		{
			Player damaged = (Player) entity;
			Player damager = (Player) entity;
			if(damager.getItemInHand().getAmount() > 0)
			{
				ItemStack is = damager.getItemInHand();
				ItemMeta meta = is.getItemMeta();
				List<String> lore = meta.getLore();
				if(is.hasItemMeta())
				{
					if(is.getItemMeta().hasEnchants())
						return;
					if(lore.contains(ChatColor.RED + "Chi Blocker") 
							&& damager.hasPermission("avatarpvp.anti.chi") 
							&& !damaged.hasPermission("avatarpvp.anti.chi"))
					{
						if(chi.containsKey(damager.getName()))
						{
							long diff = (System.currentTimeMillis() - chi.get(damager.getName())) / 1000;
							
							// Used it too recently.
							if(chiCool > diff)
							{
								damager.sendMessage(apvp + "You must wait " + ChatColor.RED + (chiCool - diff) + ChatColor.WHITE + " seconds before using this again.");
							}
							
							// Can use it again.
							else
							{
								chiBlock(damaged.getName());
								plugin.blocked.add(damaged.getName());
								chi.remove(damager.getName());
								chi.put(damager.getName(), System.currentTimeMillis());
								damager.sendMessage(apvp + "You just Chi Blocked " + damaged.getName());
							}
						}
						else
						{
							chiBlock(damaged.getName());
							chi.put(damager.getName(), System.currentTimeMillis());
							damager.sendMessage(apvp + "You just Chi Blocked " + damaged.getName());
						}
					}
				}
			}
		}
	}
	
	public void chiBlock(final String name)
	{
		 Bukkit.getServer().getScheduler().runTaskAsynchronously((Plugin) this, new Runnable()
         {
             @Override
             public void run()
             {
                 plugin.blocked.remove(plugin.blocked.contains(name));
             }
         });	
	}
	
	
	@EventHandler
	public void antiInteract(PlayerInteractEvent event)
	{
		/**
		 * Stuff we need for everything.
		 * Set up cooldowns in seconds.
		 */
		
		Player player = event.getPlayer();
		Action action = event.getAction();
		if(action == Action.LEFT_CLICK_BLOCK)
		{
			if(player.getItemInHand().getAmount() > 0)
			{
				ItemStack itemStack = player.getItemInHand();
				ItemMeta meta = itemStack.getItemMeta();
				List<String> lore = meta.getLore();
				if(itemStack.hasItemMeta())
				{
					if(itemStack.getItemMeta().hasEnchants())
						return;
					
					if(lore.contains(ChatColor.RED + "Chi Blocker"))
					{
						if(player.hasPermission("avatarpvp.anti.chi"))
						{
							// Check if the player has used the ability already.
							if(chi.containsKey(player.getName()))
							{
								long diff = (System.currentTimeMillis() - chi.get(player.getName())) / 1000;
								
								// Used it too recently.
								if(chiCool > diff)
								{
									player.sendMessage(apvp + "You must wait " + ChatColor.RED + (chiCool - diff) + ChatColor.WHITE + " seconds before using this again.");
								}
								
								// Can use it again.
								else
								{
									Block clickedBlock = event.getClickedBlock();
									Location loc = clickedBlock.getLocation();
									loc.getWorld().spawnEntity(loc, EntityType.IRON_GOLEM);
									chi.remove(player.getName());
									chi.put(player.getName(), System.currentTimeMillis());
									player.sendMessage(apvp + "Spawned an iron golem to protect you.");
								}
							}
							
							// Player hasn't already used it.
							else
							{
								Block clickedBlock = event.getClickedBlock();
								Location loc = clickedBlock.getLocation();
								loc.getWorld().spawnEntity(loc, EntityType.IRON_GOLEM);
								chi.put(player.getName(), System.currentTimeMillis());
								player.sendMessage(apvp + "Spawned an iron golem to protect you.");
							}
						}
						else
						{
							player.sendMessage(apvp + "You don't have permission to use this ability.");
						}
					}
				}
				else
					return;

			}
		}
	}
}

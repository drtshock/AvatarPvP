package me.shock.avatarpvp;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class EarthListener implements Listener
{

	public Main plugin;
	
	public EarthListener(Main instance)
	{
		this.plugin = instance;
	}
	
	String apvp = ChatColor.BLUE + "[" + ChatColor.WHITE + "AvatarPvP" + ChatColor.BLUE + "]" + ChatColor.WHITE + ": ";
	String wait = ChatColor.RED + "You must wait to use this ability again.";
	
	/**
	 * Listen to earth abilities.
	 * Fortify - 5 seconds sphere protection.
	 * Golem - summon a rock golem to protect you.
	 */
	
	@EventHandler
	public void earthInteract(PlayerInteractEvent event)
	{
		Player player = event.getPlayer();
		Action action = event.getAction();
		if(action == Action.LEFT_CLICK_BLOCK)
		{
			ItemStack itemStack = player.getItemInHand();
			ItemMeta meta = itemStack.getItemMeta();
			List<String> lore = meta.getLore();
			
			if(lore.isEmpty())
			{
				return;
			}
			
			/**
			 * Check for lore of earth abilities added via commands.
			 */
			else
			{
				if(lore.contains(ChatColor.GREEN + "Fortify"))
				{
					// TODO: stuff for fortify here.
				}
				
				if(lore.contains(ChatColor.GREEN + "Golem"))
				{
					if(player.hasPermission("avatarpvp.earth.golem"))
					{
						if(cooldownthinghere)
						{
							Block clickedBlock = event.getClickedBlock();
							Location loc = clickedBlock.getLocation();
							loc.getWorld().spawnEntity(loc, EntityType.IRON_GOLEM);
						}
						else
						{
							player.sendMessage(wait);
							return;
						}
					}
					else
					{
						player.sendMessage(apvp + "You don't have permission to use this ability.");
					}
				}
			}
			
		}
		// Everything must be above this.
	}
	
	/**
	 * Get the iron golem spawned then 
	 * make it so it doesn't attack anyone else.
	 * TODO: figure this out :(
	 */
	
	@EventHandler
	public void onGolemSpawn(CreatureSpawnEvent event)
	{
		EntityType type = event.getEntityType();
		SpawnReason reason = event.getSpawnReason();
		if(type == EntityType.IRON_GOLEM && reason == SpawnReason.CUSTOM)
		{
			// TODO: figure this out :(
		}
	}
}

package me.shock.avatarpvp;

import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class WaterListener implements Listener 
{
	public Main plugin;
	
	public WaterListener(Main instance)
	{
		this.plugin = instance;
	}
	
	/**
	 * Deal more damage during full moon.
	 * Let's just say that they deal more damage at night.
	 * Just multiply given damage by 10%
	 * 
	 * TODO: Heal in water.
	 * TODO: No speed reduction in water.
	 * TODO: Arrows have a chance to freeze players.
	 */
	
	@EventHandler
	public void onWaterDamage(EntityDamageByEntityEvent event)
	{
		/**
		 * Variables from the config.
		 */
		int duration = plugin.getConfig().getInt("WaterNation.IceArrow.Duration");
		int slowLevel = plugin.getConfig().getInt("WaterNation.IceArrow.SlownessLevel");
		
		
		Entity attacker = event.getDamager();
		if(attacker instanceof Player)
		{
			Player player = (Player) attacker;
			
			/**
			 * Stuff for dealing more damage at night time.
			 */
			int damage = event.getDamage();
			if(player.hasPermission("avatarpvp.water.moon"))
			{
				if(checkBlocked(player))
					return;
				
				long time = player.getWorld().getTime();
				if(time > 18000)
				{
					int newdamage = (int) (damage * 1.1);
					event.setDamage(newdamage);
				}
			}
			
			/**
			 * Stuff for freeze arrows.
			 */
			
			Entity damagee = event.getEntity();
			DamageCause cause = event.getCause();
			if(cause == DamageCause.PROJECTILE && player.hasPermission("avatarpvp.water.icearrow") && damagee instanceof Player)
			{
				if(checkBlocked(player))
					return;
				
				Player damagedPlayer = (Player) damagee;
				Random generator = new Random();
				int random = generator.nextInt(4);
				if(random == 3)
				{
					damagedPlayer.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, duration, slowLevel));
				}
			}
			
		}
	}
	public boolean checkBlocked(Player player)
	{
		if(plugin.blocked.contains(player.getName()))
		{
			player.sendMessage(ChatColor.RED + "You are blocked from using any abilities!");
			return true;
		}
		return false;
	}
}

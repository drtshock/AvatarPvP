package me.shock.avatarpvp;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class WaterTask implements Runnable
{
	
	private Main instance;
	private int tickDelay;


	public WaterTask(Main instance, int tickDelay) 
	{
		this.instance = instance;
		this.tickDelay = tickDelay;
	}
	


	public void RunCheck() 
	{
		Player[] players = Bukkit.getOnlinePlayers();
		for(int i = 0; i < players.length; i++)
		{
			checkInWater(players[i], false);
		}
		
	}

	private void checkInWater(Player player, boolean b) 
	{
		Block feet = player.getLocation().getBlock();
		Block head = player.getEyeLocation().getBlock();
		if((checkBlock(feet) || checkBlock(head)) && player.hasPermission("avatarpvp.water"))
		{
			if(player.getHealth() < 20 && player.getHealth() > 0)
			player.setHealth(player.getHealth() + 1);
		}
		
	}
	
	private boolean checkBlock(Block b) 
	{
		if (b.getType() == Material.STATIONARY_WATER || b.getType() == Material.WATER)
			return true;
		return false;
	}
	
	public void run() 
	{
		RunCheck();
		Bukkit.getScheduler().scheduleSyncDelayedTask(instance, new WaterTask(instance, tickDelay), tickDelay);
	}

}

package me.shock.avatarpvp;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import me.shock.avatarpvp.commands.BindClear;
import me.shock.avatarpvp.commands.Earth;
import me.shock.avatarpvp.commands.Air;
import me.shock.avatarpvp.commands.Anti;
import me.shock.avatarpvp.commands.Water;
import me.shock.avatarpvp.commands.Fire;
import me.shock.avatarpvp.commands.Config;
import me.shock.avatarpvp.WaterTask;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin
{

	protected FileConfiguration config;
	File file;
	
	public static Main plugin;
	public ArrayList<String> blocked;

	public void onEnable()
	{
		loadListeners();
		loadCommands();
		loadConfig();
		
		Bukkit.getScheduler().scheduleSyncDelayedTask(this, new WaterTask(this, 10), 0);	}
	
	public void onDisable()
	{
		
	}
	
	
	private void loadListeners()
	{
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new EarthListener(this), (this));
		pm.registerEvents(new WaterListener(this), (this));
		pm.registerEvents(new FireListener(this), (this));
		pm.registerEvents(new AirListener(this), (this));
		pm.registerEvents(new AntiListener(this), (this));
	}
	
	
	private void loadCommands()
	{
		getCommand("bind").setExecutor(new BindClear(this));
		getCommand("apvp").setExecutor(new Config(this));
		getCommand("earth").setExecutor(new Earth(this));
		getCommand("air").setExecutor(new Air(this));
		getCommand("anti").setExecutor(new Anti(this));
		getCommand("water").setExecutor(new Water(this));
		getCommand("fire").setExecutor(new Fire(this));
	}
	
	/**
	 * Check if there is already a /plugins/AvatarPvP/config.yml
	 * If there isn't then copy a new one from the .jar
	 * If there is then let them know in console :)
	 */
	
	private void loadConfig()
	{
		Logger log = Bukkit.getServer().getLogger();
		/**
		 * Check to see if there's a config.
		 * If not then create a new one.
		 */
		File config = new File(getDataFolder() + File.separator+ "config.yml");
		if(!config.exists())
		{
			try{
				getDataFolder().mkdir();
				config.createNewFile();
			} catch (IOException e) {
				log.log(Level.SEVERE, "[AvatarPvP] Couldn't create config");
			}
			/**
			 * Write the config file here.
			 * New, genius way to write it :)
			 */
			try {
				FileOutputStream fos = new FileOutputStream(new File(getDataFolder() + File.separator + "config.yml"));
				InputStream is = getResource("config.yml");
				byte[] linebuffer = new byte[4096];
				int lineLength = 0;
				while((lineLength = is.read(linebuffer)) > 0)
				{
					fos.write(linebuffer, 0, lineLength);
				}
				fos.close();
				
				log.log(Level.INFO, "[AvatarPvP] Wrote new config");
				
			} catch (IOException e) {
				log.log(Level.SEVERE, "[AvatarPvP] Couldn't write config: " + e);
			}	
		}
		else
		{
			log.log(Level.INFO, "[AvatarPvP] Config found.");
		}
	}
	
}

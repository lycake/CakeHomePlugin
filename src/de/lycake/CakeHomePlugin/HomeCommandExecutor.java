package de.lycake.CakeHomePlugin;

import java.util.HashMap;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HomeCommandExecutor implements CommandExecutor{
	CakeHomePlugin plugin_;
	public HashMap<Player, Location> homes_;
	
	public HomeCommandExecutor(CakeHomePlugin plugin){
		plugin_ = plugin;
		homes_ = plugin.homes_;
	}

	/**
	 * Will execute when a player types a command
	 */
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
		Player player = null;
		Location loc;
		
		
		// Home
		if (cmd.getName().equalsIgnoreCase("home") && sender instanceof Player){	
			player = (Player) sender;
			if (args.length == 0){
				if(homes_ == null)
					System.out.println("jap hier ist homes null.. fuck!!!!!!!!!!!!");
				if (!homes_.containsKey(2)){
					player.sendMessage("You have never set your home");
					return false;
				} else {
					loc = homes_.get(player);
					loc.setY(Math.ceil(loc.getY()));
					player.teleport(loc);
					return true;
				}
			} else if (args.length > 0 && args[0].equalsIgnoreCase("set")){
				loc = player.getLocation();
				loc.setY(Math.ceil(loc.getY()));
				homes_.put(player, loc);
				HomeUtils.saveHomes(homes_);
				return true;
			}
		}
		return false;
	}
	
	

}

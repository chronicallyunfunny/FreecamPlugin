package me.chronicallyunfunny.noclip;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandNoclip implements CommandExecutor {
    NoClip plugin;

    public CommandNoclip(NoClip plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            if (!(plugin.getConfig().getBoolean("enable"))) {
                sender.sendMessage("[NoClip] This command does not exist.");
            }
            else {
                System.out.println("[NoClip] The console cannot issue this command.");
            }
            return true;
        }
        Player player = (Player) sender;
        if (!(plugin.getConfig().getBoolean("enable"))) {
            player.sendMessage(ChatColor.RED + "This command does not exist.");
            return true;
        }
        if (!(player.hasPermission("noclip.nc"))) {
            player.sendMessage(ChatColor.RED + "You do not have permission to issue this command.");
            return true;
        }
        if (args.length > 0)
            return false;
        if (!(LocationCacheConfig.getConfig().isConfigurationSection(player.getName()))) {
            LocationCacheConfig.getConfig().createSection(player.getName());
            Location playerLoc = player.getLocation();
            double locX = playerLoc.getX();
            double locY = playerLoc.getY();
            double locZ = playerLoc.getZ();
            float locPitch = playerLoc.getPitch();
            float locYaw = playerLoc.getYaw();
            GameMode gamemode = player.getGameMode();
            String strGamemode = gamemode.toString();
            LocationCacheConfig.getConfig().set(player.getName() + ".x", locX);
            LocationCacheConfig.getConfig().set(player.getName() + ".y", locY);
            LocationCacheConfig.getConfig().set(player.getName() + ".z", locZ);
            LocationCacheConfig.getConfig().set(player.getName() + ".pitch", locPitch);
            LocationCacheConfig.getConfig().set(player.getName() + ".yaw", locYaw);
            LocationCacheConfig.getConfig().set(player.getName() + ".gamemode", strGamemode);
            LocationCacheConfig.saveConfig();
            player.setGameMode(GameMode.SPECTATOR);
        }
        else {
            double retLocX = LocationCacheConfig.getConfig().getDouble(player.getName() + ".x");
            double retLocY = LocationCacheConfig.getConfig().getDouble(player.getName() + ".y");
            double retLocZ = LocationCacheConfig.getConfig().getDouble(player.getName() + ".z");
            float retLocPitch = (float) LocationCacheConfig.getConfig().getDouble(player.getName() + ".pitch");
            float retLocYaw = (float) LocationCacheConfig.getConfig().getDouble(player.getName() + ".yaw");
            String retStrGamemode = LocationCacheConfig.getConfig().getString(player.getName() + ".gamemode");
            Location retPlayerLoc = new Location(player.getWorld(), retLocX, retLocY, retLocZ, retLocYaw, retLocPitch);
            player.teleport(retPlayerLoc);
            player.setGameMode(GameMode.valueOf(retStrGamemode));
            LocationCacheConfig.getConfig().set(player.getName(), null);
            LocationCacheConfig.saveConfig();
        }
        return true;
    }
}

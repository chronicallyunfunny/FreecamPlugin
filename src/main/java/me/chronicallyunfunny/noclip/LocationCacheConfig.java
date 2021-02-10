package me.chronicallyunfunny.noclip;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class LocationCacheConfig {
    private static File file;
    private static FileConfiguration customFile;

    public static void setup() {
        file = new File(Bukkit.getServer().getPluginManager().getPlugin("NoClip").getDataFolder(), "locationcache.yml");
        if (!(file.exists())) {
            try {
                file.createNewFile();
            } catch (IOException ex) {
                return;
            }
        }
        customFile = YamlConfiguration.loadConfiguration(file);
    }

    public static FileConfiguration getConfig() {
        return customFile;
    }

    public static void saveConfig() {
        try {
            customFile.save(file);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}

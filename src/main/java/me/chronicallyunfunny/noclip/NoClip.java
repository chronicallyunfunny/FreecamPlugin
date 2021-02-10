package me.chronicallyunfunny.noclip;

import org.bukkit.plugin.java.JavaPlugin;

public final class NoClip extends JavaPlugin {
    @Override
    public void onEnable() {
        getCommand("noclip").setExecutor(new CommandNoclip(this));
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        LocationCacheConfig.setup();
        LocationCacheConfig.getConfig().options().copyDefaults(true);
        LocationCacheConfig.saveConfig();
        System.out.println("[NoClip] Started!");
    }

    @Override
    public void onDisable() {
        System.out.println("[NoClip] Stopped!");
    }
}

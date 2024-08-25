package us.sethcpalm.hardestCore;

import org.bukkit.plugin.java.JavaPlugin;

public final class HardestCore extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(new DeathEvent(this), this);
        System.out.println("HardestCore has started. You'd better not die...");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        System.out.println("HardestCore has stopped. You're safe... For now...");
    }
}

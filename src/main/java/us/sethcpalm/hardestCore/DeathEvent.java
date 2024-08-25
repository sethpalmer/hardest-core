package us.sethcpalm.hardestCore;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Objects;

public class DeathEvent implements Listener {

    private final HardestCore plugin;

    public DeathEvent(HardestCore plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        System.out.println("A player has died... Updating world name...");

        Path path = Paths.get(plugin.getDataFolder().getParentFile().getAbsolutePath()).getParent().resolve("server.properties");
        try {
            List<String> lines = Files.readAllLines(path);
            for (String line : lines) {
                String[] split = line.split("=");
                if (split.length > 0 && Objects.equals(split[0], "level-name")) {
                    lines.remove(line);
                    lines.add("level-name=world" + System.currentTimeMillis());
                    break;
                }
            }
            Files.write(path, lines, StandardOpenOption.TRUNCATE_EXISTING);
            Bukkit.shutdown();
        } catch (Exception error) {
            error.printStackTrace();
        }
    }
}

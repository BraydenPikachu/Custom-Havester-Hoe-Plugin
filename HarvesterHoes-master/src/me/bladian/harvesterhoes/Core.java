package src.me.bladian.harvesterhoes;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.Configuration;
import org.bukkit.plugin.java.JavaPlugin;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
/**
 * Created by Bladian. Before using the code, kindly ask permission to him via the following methods.
 * <p>
 * Twitter: BladianMC
 * Discord: Bladian#6411
 * <p>
 * Thank you for reading!
 */


public class Core extends JavaPlugin
{

    private Reference reference;
    private static Core INSTANCE;


    public static HashMap<UUID, Integer> balances = new HashMap<>();

    public Reference getReference()
    {
        return reference;
    }

    @Override
    public void onDisable()
    {
      saveBal();
    }

    @Override
    public void onEnable()
    {
        reference = new Reference();
        loadBal();
        Configuration config = getConfig();
        reference.setMaterial(Material.valueOf(config.getString("item.material")));
        reference.setName(config.getString("item.name"));
        reference.setLore(config.getStringList("item.lore"));

        reference.setPermission(config.getString("permission"));

        getCommand("HarvesterHoe").setExecutor(new ComHarvesterHoe(this));
        getServer().getPluginManager().registerEvents(new Events(this), this);
    }
    
    
    
    public void saveBal() {
        File file = new File("data.yml");
        YamlConfiguration bal = YamlConfiguration.loadConfiguration(file);
        for (UUID uuid : this.balances.keySet()) {
            bal.set(uuid.toString(), this.balances.get(uuid));
        }
        try {
            bal.save(file);
        } catch (IOException ignored) {
        }
    }
 /**
     * Load all balances from the config the the map
     */
    private void loadBal() {
        File file = new File("data.yml");
        YamlConfiguration bal = YamlConfiguration.loadConfiguration(file);
        for (String uuid : bal.getKeys(false)) {
            this.balances.put(UUID.fromString(uuid), bal.getInt(uuid));
        }
}
}

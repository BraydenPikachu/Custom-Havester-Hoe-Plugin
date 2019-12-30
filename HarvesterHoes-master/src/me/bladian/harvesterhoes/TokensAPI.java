package src.me.bladian.harvesterhoes;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import java.util.List;
import java.util.UUID;
/**
 * Write a description of class Tokens here.
 *
 * @author (BraydenPikachu)
 * @version (a version number or a date)
 */
public class TokensAPI
{
      private UUID uuid;
      


    
      public TokensAPI(UUID uuid) {
        this.uuid = uuid;
    }


    public void setCoins(int amount) {
        Core.balances.put(uuid, amount);
    }

    public void addTokens(int amount) {

        if(!Core.balances.containsKey(uuid)){
            Core.balances.put(uuid, amount);
        } else {
            Core.balances.put(uuid, Core.balances.get(uuid) + amount);
        }
}
public int getCoins() {
        if(!Core.balances.containsKey(uuid)){
            setCoins(0);
            return 0;
        } else {
            return Core.balances.get(uuid);
        }
}
}
package src.me.bladian.harvesterhoes;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Bladian. Before using the code, kindly ask permission to him via the following methods.
 * <p>
 * Twitter: BladianMC
 * Discord: Bladian#6411
 * <p>
 * Thank you for reading!
 */


public class ComHarvesterHoe implements CommandExecutor
{

    private Reference reference;

    public ComHarvesterHoe(Core core)
    {
        this.reference = core.getReference();
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings)
    {
        if(command.getName().equalsIgnoreCase("HarvesterHoe"))
        {
            if(commandSender.hasPermission(reference.getPermission()))
            {
                if(strings.length == 0)
                {
                    commandSender.sendMessage("§c/harvesterhoe <player>");
                    return true;
                }
                Player t = Bukkit.getPlayer(strings[0]);
                if(t == null)
                {
                    commandSender.sendMessage("§cPlayer isn't online");
                    return true;
                }
                t.getInventory().addItem(reference.getItemStack());
                commandSender.sendMessage("§aGave " + t.getName() + " a harvester hoe");
            }
        }
        return false;
    }
     
    public boolean onUpgrade(CommandSender commandSender, Command command, String s, String[] strings)
    {
        if(command.getName().equalsIgnoreCase("Upgrade"))
        {
            commandSender.sendMessage("Coming Soon");
        }
        return false;
}
}
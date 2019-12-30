package src.me.bladian.harvesterhoes;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by Bladian. Before using the code, kindly ask permission to him via the following methods.
 * <p>
 * Twitter: BladianMC
 * Discord: Bladian#6411
 * <p>
 * Thank you for reading!
 */


public class Events implements Listener
{

    private Reference reference;
    
    public Events(Core core)
    {
        this.reference = core.getReference();
    }

    @EventHandler
    public void onInteract(BlockBreakEvent e)
    {
        Block block = e.getBlock();
        if (block.getType() == Material.SUGAR_CANE_BLOCK)
        {
            Player p = e.getPlayer();
            if (p.getItemInHand() != null && p.getItemInHand().getType() != Material.AIR)
            {
                ItemStack itemStack = p.getItemInHand();
                if (itemStack.getType() == reference.getMaterial())
                {
                    if (itemStack.getItemMeta().getDisplayName() != null)
                    {
                        if (itemStack.getItemMeta().getDisplayName().equals(reference.getName()))
                        {
                            e.setCancelled(true);
                            List<Block> blocks = getBlocksOfSC(block);
                            for (ListIterator iterator = blocks.listIterator(blocks.size()); iterator.hasPrevious();) {
                                final Block listElement = (Block) iterator.previous();
                                listElement.setType(Material.AIR);
                            }
                            p.getInventory().addItem(new ItemStack(Material.SUGAR_CANE, blocks.size()));
                            
                        }
                    }
                }
            }
        }
    }


    private List<Block> getBlocksOfSC(Block block)
    {
        List<Block> blocks = new ArrayList<>();
        blocks.add(block);
        Location checkLoc = block.getLocation().clone().add(0, 1, 0);
        while (checkLoc.getBlock().getType() == Material.SUGAR_CANE_BLOCK)
        {
            blocks.add(checkLoc.getBlock());
            checkLoc.add(0, 1, 0);
        }
        return blocks;
    }
}

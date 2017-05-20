package net.jgsb.dessertcraft.client.gui.handler;

import net.jgsb.dessertcraft.client.gui.GuiFreezer;
import net.jgsb.dessertcraft.tileentity.TileEntityFreezer;
import net.jgsb.dessertcraft.tileentity.container.ContainerFreezer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

/**
 * Created by Jacob on 3/8/2017.
 */
public class DessertGuiHandler implements IGuiHandler {

    public static final int FREEZER_TILE_ENTITY_GUI = 0;

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if(ID == FREEZER_TILE_ENTITY_GUI) {
            return new ContainerFreezer(player.inventory, (TileEntityFreezer) world.getTileEntity(new BlockPos(x, y, z)));
        }

        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if(ID == FREEZER_TILE_ENTITY_GUI) {
            return new GuiFreezer(player.inventory, (TileEntityFreezer) world.getTileEntity(new BlockPos(x, y, z)));
        }

        return null;
    }


}

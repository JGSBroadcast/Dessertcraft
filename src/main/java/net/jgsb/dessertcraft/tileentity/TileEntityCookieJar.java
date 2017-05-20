package net.jgsb.dessertcraft.tileentity;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

/**
 * Created by Jacob on 3/7/2017.
 */
public class TileEntityCookieJar extends TileEntity {

    private int cookieCount = 0;

    public boolean addCookie() {
        if(!world.isRemote) {
            if(cookieCount < 64) {
                cookieCount++;
                markDirty();
                IBlockState state = world.getBlockState(pos);
                world.notifyBlockUpdate(pos, state, state, 3);
                return true;
            }
        }

        return false;
    }

    public void removeCookie() {
        if(!world.isRemote) {
            if(cookieCount > 0) {
                world.spawnEntity(new EntityItem(world, pos.getX() + 0.5, pos.getY() + 1, pos.getZ() + 0.5, new ItemStack(Items.COOKIE)));
                cookieCount--;
                markDirty();
                IBlockState state = world.getBlockState(pos);
                world.notifyBlockUpdate(pos, state, state, 3);
            }
        }
    }

    public int getCookieCount() {
        return cookieCount;
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        compound.setInteger("CookieCount", this.cookieCount);
        return compound;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        this.cookieCount = compound.getInteger("CookieCount");
    }

    @Override
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
        NBTTagCompound tag = pkt.getNbtCompound();
        readUpdateTag(tag);
    }

    @Override
    public SPacketUpdateTileEntity getUpdatePacket() {
        NBTTagCompound tag = new NBTTagCompound();
        this.writeUpdateTag(tag);
        return new SPacketUpdateTileEntity(pos, getBlockMetadata(), tag);
    }

    @Override
    public NBTTagCompound getUpdateTag() {
        NBTTagCompound tag = super.getUpdateTag();
        writeUpdateTag(tag);
        return tag;
    }

    public void writeUpdateTag(NBTTagCompound tag) {
        tag.setInteger("CookieCount", this.cookieCount);
    }

    public void readUpdateTag(NBTTagCompound tag) {
        this.cookieCount = tag.getInteger("CookieCount");
    }
}

package net.jgsb.dessertcraft.tileentity.slot;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

/**
 * Created by Jacob on 5/6/2017.
 */
public class SlotFreezerOutput extends Slot {
    private final EntityPlayer thePlayer;
    private int numFreezerOutput;

    public SlotFreezerOutput(EntityPlayer player, IInventory inv, int index, int xPos, int yPos) {
        super(inv, index, xPos, yPos);
        thePlayer = player;
    }

    @Override
    public boolean isItemValid(ItemStack stack) {
        return false;
    }

    @Override
    public ItemStack decrStackSize(int amount) {
        if(getHasStack()) {
            numFreezerOutput += Math.min(amount, getStack().getCount());
        }
        return super.decrStackSize(amount);
    }

    @Override
    public ItemStack onTake(EntityPlayer thePlayer, ItemStack stack) {
        onCrafting(stack);
        return super.onTake(thePlayer, stack);
    }

    // @Override
    public void onPickupFromSlot(EntityPlayer playerIn, ItemStack stack) {
        onCrafting(stack);
        // super.onPickupFromSlot(playerIn, stack);
    }

    @Override
    protected void onCrafting(ItemStack stack, int amount) {
        numFreezerOutput += amount;
        super.onCrafting(stack);
    }

    @Override
    protected void onCrafting(ItemStack stack) {
        numFreezerOutput = 0;
    }
}

package net.jgsb.dessertcraft.tileentity.container;

import net.jgsb.dessertcraft.init.FreezerRecipes;
import net.jgsb.dessertcraft.tileentity.TileEntityFreezer;
import net.jgsb.dessertcraft.tileentity.slot.SlotFreezerOutput;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.*;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by Jacob on 3/8/2017.
 */
public class ContainerFreezer extends Container {

    private TileEntityFreezer te;
    private int ticksPassed;
    private int ticksPerItem;
    private int timeCanGrind;

    public ContainerFreezer(InventoryPlayer playerInv, TileEntityFreezer te) {
        this.te = te;

        /*
        * SLOTS:
        *
        * Tile Entity 0-28 ....... 0 - 28
        * Player Inventory 9-35 .. 9 - 35
        * Player Inventory 0-8 ... 0 - 8
        */

        // Tile Entity, Slots 0-26, Slot IDs 0-26
        for(int y = 0; y < 3; ++y) {
            for(int x = 0; x < 9; ++x) {
                this.addSlotToContainer(new Slot(te, x + y * 9, 8 + x * 18, 18 + y * 18));
            }
        }

        // Freezer Slots 27-28
        this.addSlotToContainer(new Slot(te, 27, 53, 97));
        this.addSlotToContainer(new SlotFreezerOutput(playerInv.player, te, 28, 113, 97));

        // Player Inventory, Slots 9-35, Slot IDs 9-35
        for(int y = 0; y < 3; ++y) {
            for(int x = 0; x < 9; ++x) {
                this.addSlotToContainer(new Slot(playerInv, 9 + x + y * 9, 8 + x * 18, 140 + y * 18));
            }
        }

        // Player Inventory, Slots 0 - 8, Slot IDs 0 - 8
        for(int x = 0; x < 9; ++x) {
            this.addSlotToContainer(new Slot(playerInv, x, 8 + x * 18, 198));
        }

    }

    @Override
    public void addListener(IContainerListener listener) {
        super.addListener(listener);
        listener.sendAllWindowProperties(this, te);
    }

    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();

        for(int i = 0; i < listeners.size(); ++i) {
            IContainerListener icrafting = listeners.get(i);

            if(ticksPassed != te.getField(2)) {
                icrafting.sendProgressBarUpdate(this, 2, te.getField(2));
            }

            if(timeCanGrind != te.getField(0)) {
                icrafting.sendProgressBarUpdate(this, 0, te.getField(0));
            }

            if(ticksPerItem != te.getField(3)) {
                icrafting.sendProgressBarUpdate(this, 3, te.getField(3));
            }

            ticksPassed = te.getField(2);
            timeCanGrind = te.getField(0);
            ticksPerItem = te.getField(3);
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int id, int data) {
        te.setField(id, data);
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return this.te.isUsableByPlayer(playerIn);
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int slotIndex) {
        ItemStack itemStack1 = ItemStack.EMPTY;
        Slot slot = inventorySlots.get(slotIndex);

        if(slot.getHasStack() && slot.getStack().getItem() != Items.AIR) {
            ItemStack itemStack2 = slot.getStack();
            itemStack1 = itemStack2.copy();

            if(slotIndex == 28) {
                if(!mergeItemStack(itemStack2, te.getSizeInventory(), te.getSizeInventory() + 36, true)) {
                    return ItemStack.EMPTY;
                }

                slot.onSlotChange(itemStack2, itemStack1);
            } else if (slotIndex != 27) {
                if(slotIndex >= te.getSizeInventory() && slotIndex < te.getSizeInventory() + 36) { // from player inv to freezer
                    // check if there is a freezer recipe for the stack
                    if(FreezerRecipes.instance().getFreezingResult(itemStack2).getItem() != Items.AIR && (te.getStackInSlot(27).getItem() == Items.AIR || (te.getStackInSlot(27).getItem() == itemStack2.getItem()) && te.getStackInSlot(27).getCount() != te.getStackInSlot(27).getMaxStackSize())) {
                        // put in input if recipe
                        if(!mergeItemStack(itemStack2, 27, 28, false)) {
                            return ItemStack.EMPTY;
                        }
                        // put in freezer if not
                    } else if(!mergeItemStack(itemStack2, 0, te.getSizeInventory() - 2, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if(slotIndex >= 0 && slotIndex < te.getSizeInventory()) { // from freezer to player inv
                    if(!mergeItemStack(itemStack2, te.getSizeInventory(),te.getSizeInventory() + 36, false)) {
                        return ItemStack.EMPTY;
                    }
                }
                // from input to inventory
            } else if (!mergeItemStack(itemStack2, te.getSizeInventory(), te.getSizeInventory() + 36, false)) {
                return ItemStack.EMPTY;
            }

            if(itemStack2.getCount() == 0) {
                slot.putStack(ItemStack.EMPTY);
            } else {
                slot.onSlotChanged();
            }

            if(itemStack2.getCount() == itemStack1.getCount()) {
                return null;
            }

            slot.onTake(playerIn, itemStack2);
        }

        return itemStack1;
    }
}

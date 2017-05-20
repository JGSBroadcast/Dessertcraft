package net.jgsb.dessertcraft.tileentity;

import net.jgsb.dessertcraft.init.FreezerRecipes;
import net.jgsb.dessertcraft.tileentity.container.ContainerFreezer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntityLockable;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

/**
 * Created by Jacob on 3/8/2017.
 */
public class TileEntityFreezer extends TileEntityLockable implements ISidedInventory, ITickable {

    private static final int[] slotsTop = new int[]{27};
    private static final int[] slotsBottom = new int[]{28};
    private static final int[] slotsSides = new int[29];
    private NonNullList<ItemStack> inventory = NonNullList.withSize(getSizeInventory(), ItemStack.EMPTY);
    private String customName;
    private int timeCanFreeze;
    private int currentItemFreezTime;
    private int ticksFreezingPassed;
    private int ticksPerItem;

    public TileEntityFreezer() {
        for(int i = 0; i < this.getSizeInventory(); i++) {
            slotsSides[i] = i;
        }
    }

    @Override
    public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newSate) {
        return false;
    }

    public String getCustomName() {
        return this.customName;
    }

    public void setCustomName(String customName) {
        this.customName = customName;
    }

    @Override
    public String getName() {
        return this.hasCustomName() ? this.customName : "container.freezer";
    }

    @Override
    public boolean hasCustomName() {
        return this.customName != null && !this.customName.equals("");
    }

    @Override
    public ITextComponent getDisplayName() {
        return this.hasCustomName() ? new TextComponentString(this.getName()) : new TextComponentTranslation(this.getName());
    }

    @Override
    public int getSizeInventory() {
        return 29;
    }


    @Override
    public boolean isEmpty() {
        for(ItemStack itemstack : this.inventory) {
            if(!itemstack.isEmpty()) {
                return false;
            }
        }

        return true;
    }

    @Override
    public ItemStack getStackInSlot(int index) {
        return this.inventory.get(index);
    }

    @Override
    public ItemStack decrStackSize(int index, int count) {
        if(this.getStackInSlot(index) != null) {
            ItemStack itemStack;

            if(inventory.get(index).getCount() <= count) {
                itemStack =inventory.get(index);
                inventory.set(index, ItemStack.EMPTY);
                return itemStack;
            } else {
                itemStack = inventory.get(index).splitStack(count);

                if(inventory.get(index).getCount() <= 0) {
                    inventory.set(index, ItemStack.EMPTY);
                }

                return itemStack;
            }
        } else {
            return null;
        }
    }

    @Override
    public ItemStack removeStackFromSlot(int index) {
        ItemStack stack = inventory.get(index);
        inventory.set(index, ItemStack.EMPTY);
        return stack;
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {
        boolean isSameStackInSlot = stack != ItemStack.EMPTY && stack.isItemEqual(inventory.get(index))
                && ItemStack.areItemStackTagsEqual(stack, inventory.get(index));

        inventory.set(index, stack);

        if(stack != ItemStack.EMPTY && stack.getCount() > this.getInventoryStackLimit()) {
            stack.setCount(this.getInventoryStackLimit());
        }

        if(index == 27 && !isSameStackInSlot) {
            ticksPerItem = timetoFreezeOneItem(stack);
            ticksFreezingPassed = 0;
            markDirty();
        }
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public boolean isUsableByPlayer(EntityPlayer player) {
        return this.world.getTileEntity(this.getPos()) == this && player.getDistanceSq(this.pos.add(0.5, 0.5, 0.5)) <= 64;
    }

    @Override
    public void openInventory(EntityPlayer player) {
    }

    @Override
    public void closeInventory(EntityPlayer player) {
    }

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack) {
        return index != 28;
    }

    @Override
    public int getField(int id) {
        switch(id) {
            case 0:
                return timeCanFreeze;
            case 1:
                return currentItemFreezTime;
            case 2:
                return ticksFreezingPassed;
            case 3:
                return ticksPerItem;
            default:
                return 0;
        }
    }

    @Override
    public void setField(int id, int value) {
        switch(id) {
            case 0:
                timeCanFreeze = value;
                break;
            case 1:
                currentItemFreezTime = value;
                break;
            case 2:
                ticksFreezingPassed = value;
                break;
            case 3:
                ticksPerItem = value;
                break;
            default:
                break;
        }
    }

    @Override
    public int getFieldCount() {
        return 4;
    }

    @Override
    public void clear() {
        for(int i = 0; i < this.getSizeInventory(); i++) {
            inventory.set(i, ItemStack.EMPTY);
        }
    }

    public boolean freezingSomething() {
        return true;
    }

    @Override
    public void update() {
        if(!canFreeze()) {
            return;
        }

        boolean isFreezing = freezingSomething();
        boolean changedFreezingState = false;

        if(freezingSomething()) {
            --timeCanFreeze;
        }

        if(!world.isRemote) {
            if(inventory.get(27).getItem() != Items.AIR) {
                if(!freezingSomething() && canFreeze()) {
                    timeCanFreeze = 150;

                    if(freezingSomething()) {
                        changedFreezingState = true;
                    }
                }

                if(freezingSomething() && canFreeze()) {
                    ++ticksFreezingPassed;

                    if(ticksFreezingPassed == ticksPerItem) {
                        ticksFreezingPassed = 0;
                        ticksPerItem = timetoFreezeOneItem(inventory.get(27));
                        freezeItem();
                        changedFreezingState = true;
                    }
                } else {
                    ticksFreezingPassed = 0;
                }
            }

            if(isFreezing != freezingSomething()) {
                changedFreezingState = true;
            }
        }

        if(changedFreezingState) {
            markDirty();
        }
    }

    public int timetoFreezeOneItem(ItemStack stack) {
        return 200;
    }

    private boolean canFreeze() {
        if(inventory.get(27).getItem() == Items.AIR) {
            return false;
        } else {
            ItemStack stackToOutput = FreezerRecipes.instance().getFreezingResult(inventory.get(27));
            if(stackToOutput.getItem() == Items.AIR) {
                return false;
            }

            if(inventory.get(28).getItem() == Items.AIR) {
                return true;
            }

            if(!inventory.get(28).isItemEqual(stackToOutput)) {
                return false;
            }

            int result = inventory.get(28).getCount() + stackToOutput.getCount();

            return result <= getInventoryStackLimit() && result <= inventory.get(28).getMaxStackSize();
        }
    }

    public void freezeItem() {
        if(canFreeze()) {
            ItemStack stack = FreezerRecipes.instance().getFreezingResult(inventory.get(27));

            if(inventory.get(28).getItem() == Items.AIR) {
                inventory.set(28, stack.copy());
            } else if(inventory.get(28).getItem() == stack.getItem()) {
                inventory.get(28).setCount(inventory.get(28).getCount() + stack.getCount());
            }

            if(inventory.get(27).getItem() == Items.WATER_BUCKET) {
                inventory.set(27, new ItemStack(Items.BUCKET));
            } else {
                inventory.get(27).setCount(inventory.get(27).getCount() - 1);

                if (inventory.get(27).getCount() <= 0) {
                    inventory.set(27, ItemStack.EMPTY);
                }
            }
        }
    }

    @Override
    public int[] getSlotsForFace(EnumFacing side) {
        return side == EnumFacing.DOWN ? slotsBottom : (side == EnumFacing.UP ? slotsTop : slotsSides);
    }

    @Override
    public boolean canInsertItem(int index, ItemStack itemStackIn, EnumFacing direction) {
        return isItemValidForSlot(index, itemStackIn);
    }

    @Override
    public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction) {
        return true;
    }

    @Override
    public String getGuiID() {
        return "dessertcraft:freezer";
    }

    @Override
    public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) {
        return new ContainerFreezer(playerInventory, this);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        nbt.setShort("FreezeTime", (short) timeCanFreeze);
        nbt.setShort("TimeElapsed", (short) ticksFreezingPassed);
        nbt.setShort("FreezeTimeTotal", (short) ticksPerItem);

        NBTTagList list = new NBTTagList();
        for(int i = 0; i < this.getSizeInventory(); i++) {
            if (this.getStackInSlot(i) != null) {
                NBTTagCompound stackTag = new NBTTagCompound();
                stackTag.setByte("Slot", (byte) i);
                this.getStackInSlot(i).writeToNBT(stackTag);
                list.appendTag(stackTag);
            }
        }

        nbt.setTag("Items", list);

        if(this.hasCustomName()) {
            nbt.setString("CustomName", this.getCustomName());
        }

        return nbt;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);

        this.inventory = NonNullList.<ItemStack>withSize(this.getSizeInventory(), ItemStack.EMPTY);
        ItemStackHelper.loadAllItems(nbt, this.inventory);

        timeCanFreeze = nbt.getShort("FreezeTime");
        ticksFreezingPassed = nbt.getShort("TimeElapsed");
        ticksPerItem = nbt.getShort("FreezeItemTotal");

        if(nbt.hasKey("CustomName", 8)) {
            this.setCustomName(nbt.getString("CustomName"));
        }
    }
}

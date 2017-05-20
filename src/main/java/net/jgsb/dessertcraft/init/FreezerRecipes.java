package net.jgsb.dessertcraft.init;

import com.google.common.collect.Maps;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.Iterator;
import java.util.Map;

/**
 * Created by Jacob on 5/6/2017.
 */
public class FreezerRecipes {
    private static final FreezerRecipes freezingBase = new FreezerRecipes();
    private final Map freezingList = Maps.newHashMap();

    public static FreezerRecipes instance() {
        return freezingBase;
    }

    private FreezerRecipes() {
        addFreezingRecipe(new ItemStack(Items.WATER_BUCKET), new ItemStack(Item.getItemFromBlock(Blocks.ICE)));
        addFreezingRecipe(new ItemStack(Item.getItemFromBlock(Blocks.ICE)), new ItemStack(Item.getItemFromBlock(Blocks.PACKED_ICE)));
    }

    public void addFreezingRecipe(ItemStack input, ItemStack output) {
        freezingList.put(input, output);
    }

    public ItemStack getFreezingResult(ItemStack stack) {
        Iterator iterator = freezingList.entrySet().iterator();
        Map.Entry entry;

        do {
            if(!iterator.hasNext()) {
                return ItemStack.EMPTY;
            }

            entry = (Map.Entry) iterator.next();
        } while (!areItemStacksEqual(stack, (ItemStack) entry.getKey()));

        return (ItemStack) entry.getValue();
    }

    private boolean areItemStacksEqual(ItemStack stack1, ItemStack stack2) {
        return stack2.getItem() == stack1.getItem();
    }

    public Map getFreezingList() {
        return freezingList;
    }
}

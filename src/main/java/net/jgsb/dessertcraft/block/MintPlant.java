package net.jgsb.dessertcraft.block;

import net.jgsb.dessertcraft.init.DessertItems;
import net.minecraft.block.BlockCrops;
import net.minecraft.item.Item;

import java.util.Random;

/**
 * Created by Jacob on 1/23/2017.
 */
public class MintPlant extends BlockCrops {

    @Override
    protected Item getSeed() {
        return DessertItems.mint_seeds;
    }

    @Override
    protected Item getCrop() {
        return DessertItems.mint_leaf;
    }

    @Override
    public int quantityDropped(Random p_149745_1_) {
        return p_149745_1_.nextInt(4);
    }

}

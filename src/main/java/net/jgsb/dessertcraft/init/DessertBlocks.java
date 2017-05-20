package net.jgsb.dessertcraft.init;

import net.jgsb.dessertcraft.Reference;
import net.jgsb.dessertcraft.block.BlockCookieJar;
import net.jgsb.dessertcraft.block.BlockFreezer;
import net.jgsb.dessertcraft.block.MintPlant;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by Jacob on 3/7/2017.
 */
public class DessertBlocks {

    public static Block mint_plant;
    public static Block cookie_jar;
    public static Block freezer;

    public static void init() {
        mint_plant = new MintPlant().setUnlocalizedName("mint");
        cookie_jar = new BlockCookieJar().setUnlocalizedName("cookie_jar");
        freezer = new BlockFreezer().setUnlocalizedName("freezer");
    }

    public static void register() {
        registerBlock(mint_plant);
        registerBlock(cookie_jar);
        registerBlock(freezer);
    }

    public static void registerBlock(Block block) {
        ItemBlock item = new ItemBlock(block);
        GameRegistry.register(item, new ResourceLocation(Reference.MOD_ID + ":" + block.getUnlocalizedName().substring(5)));
        GameRegistry.register(block, new ResourceLocation(Reference.MOD_ID + ":" + block.getUnlocalizedName().substring(5)));
    }

    public static void registerRenders() {
        registerRender(mint_plant);
        registerRender(cookie_jar);
        registerRender(freezer);
    }

    public static void registerRender(Block block) {
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(block), 0, new ModelResourceLocation(Reference.MOD_ID + ":" + block.getUnlocalizedName().substring(5), "inventory"));
    }
}

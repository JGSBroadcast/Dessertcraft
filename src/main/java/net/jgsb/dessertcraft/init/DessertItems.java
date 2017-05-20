package net.jgsb.dessertcraft.init;

import net.jgsb.dessertcraft.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemSeeds;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class DessertItems {

	public static Item cupcake;
	public static Item cone;
	public static Item vanilla_icecream;
	public static Item chocolate_icecream;
	public static Item chocolate_chip;
	public static Item mintchip_icecream;
	public static Item uncooked_brownie;
	public static Item brownie;
	public static Item chocolatechip_icecream;
	public static Item plain_popsicle;
	public static Item cherry_popsicle;
	public static Item orange_popsicle;
	public static Item grape_popsicle;
	public static Item bomb_pop;
	public static Item mint_leaf;
	public static Item mint_seeds;

	public static void init() {
		cupcake = new ItemFood(5, 0.2F, false).setUnlocalizedName("cupcake");
		cone = new ItemFood(5, 0.3F, false).setUnlocalizedName("cone");
		vanilla_icecream = new ItemFood(7, 0.4F, false).setUnlocalizedName("vanilla_icecream");
		chocolate_icecream = new ItemFood(8, 0.5F, false).setUnlocalizedName("chocolate_icecream");
		chocolate_chip = new ItemFood(1, 0.1F, false).setUnlocalizedName("chocolate_chip");
		mintchip_icecream = new ItemFood(9, 0.6F, false).setUnlocalizedName("mintchip_icecream");
		uncooked_brownie = new ItemFood(3, 0.3F, false).setUnlocalizedName("uncooked_brownie");
		brownie = new ItemFood(6, 0.6F, false).setUnlocalizedName("brownie");
		chocolatechip_icecream = new ItemFood(8, 0.4F, false).setUnlocalizedName("chocolatechip_icecream");
		plain_popsicle = new ItemFood(4, 0.1F, false).setUnlocalizedName("plain_popsicle");
		cherry_popsicle = new ItemFood(5, 0.1F, false).setUnlocalizedName("cherry_popsicle");
		orange_popsicle = new ItemFood(5, 0.1F, false).setUnlocalizedName("orange_popsicle");
		grape_popsicle = new ItemFood(5, 0.1F, false).setUnlocalizedName("grape_popsicle");
		bomb_pop = new ItemFood(7, 0.2F, false).setUnlocalizedName("bomb_pop");
		mint_leaf = new Item().setUnlocalizedName("mint_leaf").setCreativeTab(CreativeTabs.FOOD);
		mint_seeds = new ItemSeeds(DessertBlocks.mint_plant, Blocks.FARMLAND).setUnlocalizedName("mint_seeds");
		MinecraftForge.addGrassSeed(new ItemStack(mint_seeds), 10);
	}

	public static void register() {
		GameRegistry.register(cupcake, new ResourceLocation(Reference.MOD_ID + ":" + cupcake.getUnlocalizedName().substring(5)));
		GameRegistry.register(cone, new ResourceLocation(Reference.MOD_ID + ":" + cone.getUnlocalizedName().substring(5)));
		GameRegistry.register(vanilla_icecream, new ResourceLocation(Reference.MOD_ID + ":" + vanilla_icecream.getUnlocalizedName().substring(5)));
		GameRegistry.register(chocolate_icecream, new ResourceLocation(Reference.MOD_ID + ":" + chocolate_icecream.getUnlocalizedName().substring(5)));
		GameRegistry.register(chocolate_chip, new ResourceLocation(Reference.MOD_ID + ":" + chocolate_chip.getUnlocalizedName().substring(5)));
		GameRegistry.register(mintchip_icecream, new ResourceLocation(Reference.MOD_ID + ":" + mintchip_icecream.getUnlocalizedName().substring(5)));
		GameRegistry.register(uncooked_brownie, new ResourceLocation(Reference.MOD_ID + ":" + uncooked_brownie.getUnlocalizedName().substring(5)));
		GameRegistry.register(brownie, new ResourceLocation(Reference.MOD_ID + ":" + brownie.getUnlocalizedName().substring(5)));
		GameRegistry.register(chocolatechip_icecream, new ResourceLocation(Reference.MOD_ID + ":" + chocolatechip_icecream.getUnlocalizedName().substring(5)));
		GameRegistry.register(plain_popsicle, new ResourceLocation(Reference.MOD_ID + ":" + plain_popsicle.getUnlocalizedName().substring(5)));
		GameRegistry.register(cherry_popsicle, new ResourceLocation(Reference.MOD_ID + ":" + cherry_popsicle.getUnlocalizedName().substring(5)));
		GameRegistry.register(orange_popsicle, new ResourceLocation(Reference.MOD_ID + ":" + orange_popsicle.getUnlocalizedName().substring(5)));
		GameRegistry.register(grape_popsicle, new ResourceLocation(Reference.MOD_ID + ":" + grape_popsicle.getUnlocalizedName().substring(5)));
		GameRegistry.register(bomb_pop, new ResourceLocation(Reference.MOD_ID + ":" + bomb_pop.getUnlocalizedName().substring(5)));
		GameRegistry.register(mint_leaf, new ResourceLocation(Reference.MOD_ID + ":" + mint_leaf.getUnlocalizedName().substring(5)));
		GameRegistry.register(mint_seeds, new ResourceLocation(Reference.MOD_ID + ":" + mint_seeds.getUnlocalizedName().substring(5)));
	}

	public static void registerRenders() {
		registerRender(cupcake);
		registerRender(cone);
		registerRender(vanilla_icecream);
		registerRender(chocolate_icecream);
		registerRender(chocolate_chip);
		registerRender(mintchip_icecream);
		registerRender(uncooked_brownie);
		registerRender(brownie);
		registerRender(chocolatechip_icecream);
		registerRender(plain_popsicle);
		registerRender(cherry_popsicle);
		registerRender(orange_popsicle);
		registerRender(grape_popsicle);
		registerRender(bomb_pop);
		registerRender(mint_leaf);
		registerRender(mint_seeds);
	}

	public static void registerRender(Item item){
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + item.getUnlocalizedName().substring(5), "inventory"));
	}
}

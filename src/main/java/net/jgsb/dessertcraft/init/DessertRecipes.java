package net.jgsb.dessertcraft.init;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class DessertRecipes {

	public static void register() {
		GameRegistry.addRecipe(new ItemStack(DessertItems.cupcake, 1), "M", "E", "W", 'M', Items.MILK_BUCKET, 'E', Items.EGG, 'W', Items.WHEAT);

		GameRegistry.addRecipe(new ItemStack(DessertItems.cone, 1), "W W", " W ", 'W', Items.WHEAT);

		GameRegistry.addRecipe(new ItemStack(DessertItems.vanilla_icecream, 1), "M", "C", 'M', Items.MILK_BUCKET, 'C', DessertItems.cone);

		GameRegistry.addShapelessRecipe(new ItemStack(DessertItems.chocolate_icecream, 1), DessertItems.vanilla_icecream, new ItemStack(Items.DYE, 1, 3));

		GameRegistry.addShapelessRecipe(new ItemStack(DessertItems.chocolate_chip, 1), new ItemStack(Items.DYE, 1, 3));

		GameRegistry.addShapelessRecipe(new ItemStack(DessertItems.mintchip_icecream, 1), DessertItems.vanilla_icecream, DessertItems.chocolate_chip, DessertItems.mint_leaf);

		GameRegistry.addRecipe(new ItemStack(DessertItems.uncooked_brownie, 1), "CCC", "CCC", 'C', new ItemStack(Items.DYE, 1, 3));

		GameRegistry.addShapelessRecipe(new ItemStack(DessertItems.chocolatechip_icecream, 1), DessertItems.vanilla_icecream, DessertItems.chocolate_chip);

		GameRegistry.addShapelessRecipe(new ItemStack(DessertItems.plain_popsicle, 4), Blocks.ICE, Items.STICK);

		GameRegistry.addShapelessRecipe(new ItemStack(DessertItems.cherry_popsicle, 1), DessertItems.plain_popsicle, new ItemStack(Items.DYE, 1, 1));

		GameRegistry.addShapelessRecipe(new ItemStack(DessertItems.orange_popsicle, 1), DessertItems.plain_popsicle, new ItemStack(Items.DYE, 1, 14));

		GameRegistry.addShapelessRecipe(new ItemStack(DessertItems.grape_popsicle, 1), DessertItems.plain_popsicle, new ItemStack(Items.DYE, 1, 5));

		GameRegistry.addShapelessRecipe(new ItemStack(DessertItems.bomb_pop, 1), DessertItems.plain_popsicle, new ItemStack(Items.DYE, 1, 1),
				new ItemStack(Items.DYE, 1, 4), new ItemStack(Items.DYE, 1, 15));

		GameRegistry.addRecipe(new ItemStack(Item.getItemFromBlock(DessertBlocks.cookie_jar), 1), "GSG", "G G", "GGG", 'G', Item.getItemFromBlock(Blocks.GLASS), 'S',
				Item.getItemFromBlock(Blocks.STONE_SLAB));

		GameRegistry.addRecipe(new ItemStack(Item.getItemFromBlock(DessertBlocks.freezer), 1), "ISI", "ICI", "IWI", 'I', Items.IRON_INGOT, 'S',
				Items.SNOWBALL, 'W', Items.WATER_BUCKET, 'C', Item.getItemFromBlock(Blocks.CHEST));

		GameRegistry.addSmelting(DessertItems.uncooked_brownie, new ItemStack (DessertItems.brownie), 0.35F);
	}
}

package net.jgsb.dessertcraft.init;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class DessertRecipes {

	public static void register() {
		GameRegistry.addRecipe(new ItemStack(Desserts.cupcake, 1), "M", "E", "W", 'M', Items.MILK_BUCKET, 'E', Items.EGG, 'W', Items.WHEAT);

		GameRegistry.addRecipe(new ItemStack(Desserts.cone, 1), "W W", " W ", 'W', Items.WHEAT);

		GameRegistry.addRecipe(new ItemStack(Desserts.vanilla_icecream, 1), "M", "C", 'M', Items.MILK_BUCKET, 'C', Desserts.cone);

		GameRegistry.addShapelessRecipe(new ItemStack(Desserts.chocolate_icecream, 1), Desserts.vanilla_icecream, new ItemStack(Items.DYE, 1, 3));

		GameRegistry.addShapelessRecipe(new ItemStack(Desserts.chocolate_chip, 1), new ItemStack(Items.DYE, 1, 3));

		GameRegistry.addShapelessRecipe(new ItemStack(Desserts.mintchip_icecream, 1), Desserts.vanilla_icecream, Desserts.chocolate_chip, Desserts.mint_leaf);

		GameRegistry.addRecipe(new ItemStack(Desserts.uncooked_brownie, 1), "CCC", "CCC", 'C', new ItemStack(Items.DYE, 1, 3));

		GameRegistry.addShapelessRecipe(new ItemStack(Desserts.chocolatechip_icecream, 1), Desserts.vanilla_icecream, Desserts.chocolate_chip);

		GameRegistry.addShapelessRecipe(new ItemStack(Desserts.plain_popsicle, 4), Blocks.ICE, Items.STICK);

		GameRegistry.addShapelessRecipe(new ItemStack(Desserts.cherry_popsicle, 1), Desserts.plain_popsicle, new ItemStack(Items.DYE, 1, 1));

		GameRegistry.addShapelessRecipe(new ItemStack(Desserts.orange_popsicle, 1), Desserts.plain_popsicle, new ItemStack(Items.DYE, 1, 14));

		GameRegistry.addShapelessRecipe(new ItemStack(Desserts.grape_popsicle, 1), Desserts.plain_popsicle, new ItemStack(Items.DYE, 1, 5));

		GameRegistry.addShapelessRecipe(new ItemStack(Desserts.bomb_pop, 1), Desserts.plain_popsicle, new ItemStack(Items.DYE, 1, 1), new ItemStack(Items.DYE, 1, 4),
				new ItemStack(Items.DYE, 1, 15));

		GameRegistry.addSmelting(Desserts.uncooked_brownie, new ItemStack (Desserts.brownie), 0.35F);
	}
}

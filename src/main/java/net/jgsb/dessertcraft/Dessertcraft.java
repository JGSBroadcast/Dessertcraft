package net.jgsb.dessertcraft;


import net.jgsb.dessertcraft.init.DessertBlocks;
import net.jgsb.dessertcraft.init.DessertItems;
import net.jgsb.dessertcraft.init.DessertRecipes;
import net.jgsb.dessertcraft.proxy.CommonProxy;
import net.jgsb.dessertcraft.tileentity.TileEntityCookieJar;
import net.jgsb.dessertcraft.tileentity.TileEntityFreezer;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;


@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION, updateJSON = Reference.UPDATE_JSON)

public class Dessertcraft {

	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
	public static CommonProxy proxy;

	@Mod.Instance
	public static Dessertcraft instance;

	@EventHandler
	public void preInit(FMLPreInitializationEvent e) {
		instance = this;
		DessertBlocks.init();
		DessertBlocks.register();
		DessertItems.init();
		DessertItems.register();
	}

	@EventHandler
	public void init(FMLInitializationEvent e) {
		proxy.registerRenders();
		proxy.init(e);
		DessertRecipes.register();
		GameRegistry.registerTileEntity(TileEntityCookieJar.class, Reference.MOD_ID + "TileEntityCookieJar");
		GameRegistry.registerTileEntity(TileEntityFreezer.class, Reference.MOD_ID + "TileEntityFreezer");
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent e) {

	}

}

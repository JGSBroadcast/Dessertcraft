package net.jgsb.dessertcraft.proxy;

import net.jgsb.dessertcraft.Dessertcraft;
import net.jgsb.dessertcraft.client.gui.handler.DessertGuiHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

public class CommonProxy {

	public void registerRenders() {

	}

	public void init(FMLInitializationEvent e) {
		NetworkRegistry.INSTANCE.registerGuiHandler(Dessertcraft.instance, new DessertGuiHandler());
	}
}

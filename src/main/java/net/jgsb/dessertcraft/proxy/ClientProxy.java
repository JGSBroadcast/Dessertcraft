package net.jgsb.dessertcraft.proxy;

import net.jgsb.dessertcraft.init.DessertBlocks;
import net.jgsb.dessertcraft.init.DessertItems;
import net.jgsb.dessertcraft.tileentity.TileEntityCookieJar;
import net.jgsb.dessertcraft.tileentity.render.RendererCookieJar;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class ClientProxy extends CommonProxy {

	@Override
	public void registerRenders() {
		DessertBlocks.registerRenders();
		DessertItems.registerRenders();

		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCookieJar.class, new RendererCookieJar());
	}

}

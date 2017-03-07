package net.jgsb.dessertcraft.proxy;

import net.jgsb.dessertcraft.init.Desserts;

public class ClientProxy extends CommonProxy {

	@Override
	public void registerRenders(){
		Desserts.registerRenders();
	}

}

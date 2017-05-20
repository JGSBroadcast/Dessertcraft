package net.jgsb.dessertcraft.tileentity.render;

import net.jgsb.dessertcraft.tileentity.TileEntityCookieJar;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

/**
 * Created by Jacob on 3/8/2017.
 */
public class RendererCookieJar extends TileEntitySpecialRenderer<TileEntityCookieJar> {

    private static final EntityItem ITEM = new EntityItem(Minecraft.getMinecraft().world, 0, 0, 0, new ItemStack(Items.COOKIE));

    @Override
    public void renderTileEntityAt(TileEntityCookieJar te, double x, double y, double z, float partialTicks, int destroyStage) {
        ITEM.hoverStart = 0F;

        GlStateManager.pushMatrix();
        {
            GlStateManager.translate(x, y, z);
            GlStateManager.rotate(90F, 1, 0, 0);
            GlStateManager.translate(0.5, 0.02, -0.15);

            for(int i = 0; i < Math.ceil(te.getCookieCount() / 8); i++) {
                Minecraft.getMinecraft().getRenderManager().doRenderEntity(ITEM, 0, 0, 0, 0F, 0F, false);
                GlStateManager.translate(0, 0, -0.0625);
            }
        }

        GlStateManager.popMatrix();
    }
}

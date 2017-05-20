package net.jgsb.dessertcraft.client.gui;

import net.jgsb.dessertcraft.Reference;
import net.jgsb.dessertcraft.tileentity.TileEntityFreezer;
import net.jgsb.dessertcraft.tileentity.container.ContainerFreezer;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;

/**
 * Created by Jacob on 3/8/2017.
 */
public class GuiFreezer extends GuiContainer {

    private TileEntityFreezer te;

    public GuiFreezer(InventoryPlayer playerInv, TileEntityFreezer te) {
        super(new ContainerFreezer(playerInv, te));

        this.te = te;

        this.xSize = 176;
        this.ySize = 222;
    }

    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(new ResourceLocation(Reference.MOD_ID, "textures/gui/container/freezer.png"));
        this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);

        int progressLevel = getProgressLevel(24);
        drawTexturedModalRect(this.guiLeft + 76, this.guiTop + 96, 176, 0, progressLevel + 1, 16);
    }

    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        this.fontRendererObj.drawString(this.te.getDisplayName().getUnformattedText(), 8, 6, 4210752);
    }

    private int getProgressLevel(int progressBarWidth) {
        int ticksPassed = te.getField(2);
        int ticksPerItem = te.getField(3);
        return ticksPerItem != 0 && ticksPassed != 0 ? ticksPassed * progressBarWidth / ticksPerItem : 0;
    }
}

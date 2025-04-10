package com.noahxk.stevenswarppads.screen.custom;

import com.mojang.blaze3d.systems.RenderSystem;
import com.noahxk.stevenswarppads.StevensWarpPads;
import com.noahxk.stevenswarppads.block.entity.WarpPadCoreBlockEntity;
import com.noahxk.stevenswarppads.screen.button.WarpOptionButton;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;

public class WarpPadCoreScreen extends AbstractContainerScreen<WarpPadCoreMenu> {
    private static final ResourceLocation GUI_TEXTURE = ResourceLocation.fromNamespaceAndPath(StevensWarpPads.MODID, "textures/gui/warppadcore/warp_pad_core_menu.png");
    private WarpPadCoreBlockEntity blockEntity;
    private Player player;

    public WarpPadCoreScreen(WarpPadCoreMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
        this.blockEntity = menu.blockEntity;
        this.player = playerInventory.player;
    }

    @Override
    protected void init() {
        addRenderableWidget(new WarpOptionButton(this.width / 2 - 50, this.height / 2 - 50, 50, 20, Component.literal(blockEntity.getWarpPadName()), new BlockPos(0,0,0), blockEntity));
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float v, int i, int i1) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, GUI_TEXTURE);

        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        guiGraphics.blit(GUI_TEXTURE, x, y, 0, 0, imageWidth, imageHeight);
    }

    @Override
    protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {}
}

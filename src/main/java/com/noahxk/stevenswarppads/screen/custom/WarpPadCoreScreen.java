package com.noahxk.stevenswarppads.screen.custom;

import com.mojang.blaze3d.systems.RenderSystem;
import com.noahxk.stevenswarppads.StevensWarpPads;
import com.noahxk.stevenswarppads.block.entity.WarpPadCoreBlockEntity;
import com.noahxk.stevenswarppads.network.payloads.ServerboundWarpPadNameChangePacket;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.network.PacketDistributor;
import org.lwjgl.glfw.GLFW;

public class WarpPadCoreScreen extends AbstractContainerScreen<WarpPadCoreMenu> {
    private static final ResourceLocation GUI_TEXTURE = ResourceLocation.fromNamespaceAndPath(StevensWarpPads.MODID, "textures/gui/warppadcore/warp_pad_core_menu.png");
    private WarpPadCoreBlockEntity blockEntity;
    private Player player;
    private EditBox textField;

    public WarpPadCoreScreen(WarpPadCoreMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
        this.blockEntity = menu.blockEntity;
        this.player = playerInventory.player;
    }

    @Override
    protected void init() {
        textField = new EditBox(Minecraft.getInstance().font, this.width / 2 - 85, this.height / 2 - 80, 169, 13, textField, Component.literal("Warp Pad Name"));
        textField.setMaxLength(100);
        textField.setValue(this.blockEntity.getWarpPadName());
        textField.setEditable(true);
        addRenderableWidget(textField);

        //addRenderableWidget(new WarpOptionButton(this.width / 2 - 50, this.height / 2 - 50, 50, 20, Component.literal(blockEntity.getWarpPadName()), new BlockPos(0,0,0), blockEntity));
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

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if(textField.keyPressed(keyCode, scanCode, modifiers) || textField.isFocused()) {
            if(keyCode == GLFW.GLFW_KEY_ENTER || keyCode == GLFW.GLFW_KEY_ESCAPE) {
                textField.setFocused(false);
                System.out.println("Text field value: " + textField.getValue());
                PacketDistributor.sendToServer(new ServerboundWarpPadNameChangePacket(textField.getValue(), blockEntity.getBlockPos().asLong()));
            }

            return true;
        }

        return super.keyPressed(keyCode, scanCode, modifiers);
    }
}

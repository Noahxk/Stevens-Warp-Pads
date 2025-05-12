package com.noahxk.stevenswarppads.screen.custom;

import com.mojang.blaze3d.systems.RenderSystem;
import com.noahxk.stevenswarppads.StevensWarpPads;
import com.noahxk.stevenswarppads.block.entity.WarpPadCoreBlockEntity;
import com.noahxk.stevenswarppads.data.WarpPadData;
import com.noahxk.stevenswarppads.data.WarpPadListSavedData;
import com.noahxk.stevenswarppads.network.payloads.ServerboundWarpPadNameChangePacket;
import com.noahxk.stevenswarppads.screen.button.WarpMenuPageSelectionButton;
import com.noahxk.stevenswarppads.screen.button.WarpOptionButton;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.neoforged.neoforge.network.PacketDistributor;
import org.lwjgl.glfw.GLFW;

import java.util.ArrayList;
import java.util.List;

public class WarpPadCoreScreen extends AbstractContainerScreen<WarpPadCoreMenu>{
    private static final ResourceLocation GUI_TEXTURE = ResourceLocation.fromNamespaceAndPath(StevensWarpPads.MODID, "textures/gui/warppadcore/warp_pad_core_menu.png");
    private WarpPadCoreBlockEntity blockEntity;
    private EditBox textField;
    private List<GuiEventListener> activeButtons = new ArrayList<>();
    private int currentPage = 0;
    private int requiredPages;
    private List<WarpPadData> padList = new ArrayList<>();

    public WarpPadCoreScreen(WarpPadCoreMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
        this.blockEntity = menu.blockEntity;
    }

    @Override
    protected void init() {
        addNameEditBox();
        addPageSelectionButtons();

        for(WarpPadData pad : WarpPadListSavedData.getData().DATA) {
            if(blockEntity.getWarpPadId().equals(pad.getId())) continue;
            padList.add(new WarpPadData(pad.getPos(), pad.getName(), pad.getDimension(), pad.getId()));
        }

        requiredPages = (padList.size() - 1) / 4;

        loadPage(currentPage);
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
    protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
        guiGraphics.drawString(Minecraft.getInstance().font, "Display Name:", this.width / 2 - 85, this.height / 2 - 80, -1);
        guiGraphics.drawString(Minecraft.getInstance().font, "Destinations:", this.width / 2 - 85, this.height / 2 - 45, -1);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if(textField.keyPressed(keyCode, scanCode, modifiers) || textField.isFocused()) {
            if(keyCode == GLFW.GLFW_KEY_ENTER || keyCode == GLFW.GLFW_KEY_ESCAPE) {
                textField.setFocused(false);
                PacketDistributor.sendToServer(new ServerboundWarpPadNameChangePacket(textField.getValue(), blockEntity.getBlockPos().asLong()));
            }

            return true;
        }

        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    private void addNameEditBox() {
        textField = new EditBox(Minecraft.getInstance().font, this.width / 2 - 85, this.height / 2 - 70, 170, 13, textField, Component.literal("Warp Pad Name"));
        textField.setMaxLength(25);
        textField.setValue(this.blockEntity.getWarpPadName());
        textField.setEditable(true);
        addRenderableWidget(textField);
    }

    private void addPageSelectionButtons() {
        addRenderableWidget(new WarpMenuPageSelectionButton(this.width / 2 - 70, this.height / 2 + 15, Component.literal("<"), this, false));
        addRenderableWidget(new WarpMenuPageSelectionButton(this.width / 2 + 50, this.height / 2 + 15, Component.literal(">"), this, true));
    }

    private void unloadPage() {
        for(GuiEventListener btn : activeButtons) {
            removeWidget(btn);
        }
        activeButtons.clear();
    }

    private void loadPage(int page) {
        int padCount = padList.size();
        int pageButtonIndex = page * 4;

        int pageButtonCount = padCount - pageButtonIndex;
        if(pageButtonCount > 4) pageButtonCount = 4;

        int yLevel = -30;
        for(int j = 0; j < pageButtonCount; j++) {
            WarpPadData pad = padList.get(pageButtonIndex);

            GuiEventListener btn = addRenderableWidget(new WarpOptionButton(this.width / 2 - 40, this.height / 2 + yLevel, Component.literal(pad.getName()), blockEntity.getBlockPos(), pad.getPos()));
            activeButtons.add(btn);
            pageButtonIndex++;
            yLevel += 30;
        }
    }

    public void changePage(int changeValue) {
        this.currentPage += changeValue;
        this.unloadPage();
        this.loadPage(this.currentPage);
    }

    public int getCurrentPage() {
        return this.currentPage;
    }

    public int getRequiredPages() {
        return this.requiredPages;
    }
}

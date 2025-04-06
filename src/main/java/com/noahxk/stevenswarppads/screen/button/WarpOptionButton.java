package com.noahxk.stevenswarppads.screen.button;

import com.noahxk.stevenswarppads.block.entity.WarpPadCoreBlockEntity;
import net.minecraft.client.gui.components.AbstractButton;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;

public class WarpOptionButton extends AbstractButton {
    private BlockPos targetPos;
    private WarpPadCoreBlockEntity blockEntity;
    private Component message;

    public WarpOptionButton(int x, int y, int width, int height, Component message, BlockPos targetPos, WarpPadCoreBlockEntity blockEntity) {
        super(x, y, width, height, message);
        this.targetPos = targetPos;
        this.message = message;
        this.blockEntity = blockEntity;
    }

    @Override
    public void onPress() {
        System.out.println("Target Pad ID: " + this.message.getString() + ", Target Pad Pos: " + this.targetPos.toShortString());
    }

    @Override
    protected void updateWidgetNarration(NarrationElementOutput narrationElementOutput) {

    }
}

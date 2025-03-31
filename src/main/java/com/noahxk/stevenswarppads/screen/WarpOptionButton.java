package com.noahxk.stevenswarppads.screen;

import net.minecraft.client.gui.components.AbstractButton;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;

public class WarpOptionButton extends AbstractButton {
    private BlockPos targetPos;
    private Component name;

    public WarpOptionButton(int x, int y, int width, int height, Component message, BlockPos targetPos) {
        super(x, y, width, height, message);
        this.targetPos = targetPos;
        this.name = message;
    }

    @Override
    public void onPress() {
        System.out.println("Button Name: " + this.name.getString() + " Target Position: " + this.targetPos.toShortString());
    }

    @Override
    protected void updateWidgetNarration(NarrationElementOutput narrationElementOutput) {

    }
}

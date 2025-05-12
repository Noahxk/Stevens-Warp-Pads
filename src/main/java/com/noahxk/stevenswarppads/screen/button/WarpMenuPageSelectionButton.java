package com.noahxk.stevenswarppads.screen.button;

import com.noahxk.stevenswarppads.screen.custom.WarpPadCoreScreen;
import net.minecraft.client.gui.components.AbstractButton;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.Component;

public class WarpMenuPageSelectionButton extends AbstractButton {
    private boolean increases;
    private WarpPadCoreScreen screen;

    public WarpMenuPageSelectionButton(int x, int y, Component message, WarpPadCoreScreen screen, boolean increases) {
        super(x, y, 20, 20, message);

        this.increases = increases;
        this.screen = screen;
    }

    @Override
    public void onPress() {
        if(increases) {
            if(screen.getCurrentPage() < screen.getRequiredPages()) screen.changePage(1);
        } else {
            if(screen.getCurrentPage() > 0) screen.changePage(-1);
        }
    }

    @Override
    protected void updateWidgetNarration(NarrationElementOutput narrationElementOutput) {

    }
}

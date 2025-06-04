package com.noahxk.stevenswarppads.screen.button;

import com.noahxk.stevenswarppads.data.WarpPadData;
import com.noahxk.stevenswarppads.network.payloads.ServerboundWarpLocationSelectedPacket;
import net.minecraft.client.gui.components.AbstractButton;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.Component;
import net.neoforged.neoforge.network.PacketDistributor;

public class WarpOptionButton extends AbstractButton {
    private final WarpPadData destinationPad;
    private final WarpPadData currentPad;

    public WarpOptionButton(int x, int y, WarpPadData destinationPad, WarpPadData currentPad) {
        super(x, y, 80, 18, Component.literal(destinationPad.getName()));
        this.destinationPad = destinationPad;
        this.currentPad = currentPad;
    }

    @Override
    public void onPress() {
        PacketDistributor.sendToServer(new ServerboundWarpLocationSelectedPacket(
            currentPad.getId().toString(),
            destinationPad.getId().toString()
        ));
    }

    @Override
    protected void updateWidgetNarration(NarrationElementOutput narrationElementOutput) {}
}

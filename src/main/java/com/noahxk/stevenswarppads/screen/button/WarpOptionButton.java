package com.noahxk.stevenswarppads.screen.button;

import com.noahxk.stevenswarppads.data.WarpPadData;
import com.noahxk.stevenswarppads.network.payloads.ServerboundWarpLocationSelectedPacket;
import net.minecraft.client.gui.components.AbstractButton;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.neoforged.neoforge.network.PacketDistributor;

public class WarpOptionButton extends AbstractButton {
    private final CompoundTag destinationPad;
    private final CompoundTag currentPad;

    public WarpOptionButton(int x, int y, WarpPadData destinationPad, WarpPadData currentPad) {
        super(x, y, 80, 18, Component.literal(destinationPad.getName()));
        this.destinationPad = destinationPad.serialize();
        this.currentPad = currentPad.serialize();
    }

    @Override
    public void onPress() {
        PacketDistributor.sendToServer(new ServerboundWarpLocationSelectedPacket(
            currentPad.getLong("pos"),
            currentPad.getString("dimension"),
            currentPad.getUUID("warpPadId").toString(),
            destinationPad.getLong("pos"),
            destinationPad.getString("dimension"),
            destinationPad.getUUID("warpPadId").toString()
        ));
    }

    @Override
    protected void updateWidgetNarration(NarrationElementOutput narrationElementOutput) {}
}

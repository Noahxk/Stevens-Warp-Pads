package com.noahxk.stevenswarppads.screen.button;

import com.noahxk.stevenswarppads.network.payloads.ServerboundWarpLocationSelectedPacket;
import net.minecraft.client.gui.components.AbstractButton;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.neoforged.neoforge.network.PacketDistributor;

public class WarpOptionButton extends AbstractButton {
    private BlockPos toPos;
    private BlockPos fromPos;

    public WarpOptionButton(int x, int y, Component message, BlockPos fromPos, BlockPos toPos) {
        super(x, y, 80, 18, message);
        this.fromPos = fromPos;
        this.toPos = toPos;
    }

    @Override
    public void onPress() {
        PacketDistributor.sendToServer(new ServerboundWarpLocationSelectedPacket(fromPos.asLong(), toPos.asLong()));
    }

    @Override
    protected void updateWidgetNarration(NarrationElementOutput narrationElementOutput) {

    }
}

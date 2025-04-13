package com.noahxk.stevenswarppads.screen.button;

import com.noahxk.stevenswarppads.block.entity.WarpPadCoreBlockEntity;
import com.noahxk.stevenswarppads.network.payloads.ServerboundWarpLocationSelectedPacket;
import net.minecraft.client.gui.components.AbstractButton;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.neoforged.neoforge.network.PacketDistributor;

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
        PacketDistributor.sendToServer(new ServerboundWarpLocationSelectedPacket(blockEntity.getBlockPos().asLong(), targetPos.asLong()));
    }

    @Override
    protected void updateWidgetNarration(NarrationElementOutput narrationElementOutput) {

    }
}

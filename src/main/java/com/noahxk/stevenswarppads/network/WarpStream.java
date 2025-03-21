package com.noahxk.stevenswarppads.network;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;

public class WarpStream {
    private BlockPos targetPos;
    private Level targetDim;
    private ServerPlayer requestingPlayer;
    private String targetWarpPadID;

    public WarpStream(BlockPos targetPos, Level targetDim, ServerPlayer requestingPlayer, String targetWarpPadID) {
        this.targetPos = targetPos;
        this.targetDim = targetDim;
        this.requestingPlayer = requestingPlayer;
        this.targetWarpPadID = targetWarpPadID;
    }

    public BlockPos getTargetPos() {
        return this.targetPos;
    }

    public Level getTargetDim() {
        return this.targetDim;
    }

    public ServerPlayer getRequestingPlayer() {
        return this.requestingPlayer;
    }

    public String getTargetWarpPadID() {
        return this.targetWarpPadID;
    }
}

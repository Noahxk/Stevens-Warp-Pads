package com.noahxk.stevenswarppads.data;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.saveddata.SavedData;
import net.neoforged.neoforge.server.ServerLifecycleHooks;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.UUID;

public class WarpPadListSavedData extends SavedData {

    public final List<WarpPadData> DATA = new ArrayList<>();

    public static WarpPadListSavedData create() {
        return new WarpPadListSavedData();
    }

    public WarpPadListSavedData load(CompoundTag compoundTag, HolderLookup.Provider lookupProvider) {
        CompoundTag saveData = compoundTag.getCompound("saveData");
        for(int i = 0; saveData.contains("data" + i); i++) {
            DATA.add(WarpPadData.deserialize(saveData.getCompound("data" + i)));
        }

        return this;
    }

    @Override
    @Nonnull
    public CompoundTag save(CompoundTag compoundTag, HolderLookup.Provider provider) {
        CompoundTag saveData = new CompoundTag();
        for(ListIterator<WarpPadData> iterator = DATA.listIterator(); iterator.hasNext();) {
            saveData.put("data" + iterator.nextIndex(), iterator.next().serialize());
        }
        compoundTag.put("saveData", saveData);

        return compoundTag;
    }

    public static void addWarpPad(WarpPadData object) {
        WarpPadListSavedData data = ServerLifecycleHooks.getCurrentServer().overworld().getDataStorage().computeIfAbsent(new Factory<WarpPadListSavedData>(WarpPadListSavedData::create, new WarpPadListSavedData()::load), "warppadlist");
        data.DATA.add(object);
        data.setDirty();
        System.out.println(data.DATA);
    }

    public static void addWarpPad(BlockPos pos, String name, ServerLevel level, UUID id) {
        WarpPadListSavedData.addWarpPad(new WarpPadData(pos, name, level, id));
    }

    public static void removeWarpPad(WarpPadData object) {
        WarpPadListSavedData data = ServerLifecycleHooks.getCurrentServer().overworld().getDataStorage().computeIfAbsent(new Factory<WarpPadListSavedData>(WarpPadListSavedData::create, new WarpPadListSavedData()::load), "warppadlist");
        data.DATA.remove(object);
        data.setDirty();
        System.out.println(data.DATA);
    }

    public static void removeWarpPad(UUID id) {
        WarpPadListSavedData.removeWarpPad(new WarpPadData(new BlockPos(0,0,0), "", ServerLifecycleHooks.getCurrentServer().overworld(), id));
    }

    public static WarpPadListSavedData getData() {
        return ServerLifecycleHooks.getCurrentServer().overworld().getDataStorage().get(new Factory<WarpPadListSavedData>(WarpPadListSavedData::create, new WarpPadListSavedData()::load), "warppadlist");
    }
}

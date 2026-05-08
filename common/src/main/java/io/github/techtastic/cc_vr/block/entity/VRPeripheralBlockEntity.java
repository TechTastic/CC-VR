package io.github.techtastic.cc_vr.block.entity;

import dan200.computercraft.api.peripheral.AttachedComputerSet;
import io.github.techtastic.cc_vr.registry.CCVRBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class VRPeripheralBlockEntity extends BlockEntity {
    public static final String TAG_BOUND_PLAYER;

    public final AttachedComputerSet computers = new AttachedComputerSet();
    private UUID boundPlayer = null;

    public VRPeripheralBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(CCVRBlockEntities.VR_PERIPHERAL.get(), blockPos, blockState);
    }

    @Nullable
    public Player getBoundPlayer() {
        if (this.level == null || this.boundPlayer == null)
            return null;
        return this.level.getPlayerByUUID(this.boundPlayer);
    }

    public void setBoundPlayer(UUID boundPlayer) {
        this.boundPlayer = boundPlayer;
        this.setChanged();
    }

    @Override
    protected void saveAdditional(CompoundTag compoundTag, HolderLookup.Provider provider) {
        if (this.boundPlayer != null)
            compoundTag.putUUID(TAG_BOUND_PLAYER, this.boundPlayer);
        super.saveAdditional(compoundTag, provider);
    }

    @Override
    protected void loadAdditional(CompoundTag compoundTag, HolderLookup.Provider provider) {
        super.loadAdditional(compoundTag, provider);
        if (compoundTag.hasUUID(TAG_BOUND_PLAYER))
            this.boundPlayer = compoundTag.getUUID(TAG_BOUND_PLAYER);
    }

    static {
        TAG_BOUND_PLAYER = "cc_vr$bound_player";
    }
}

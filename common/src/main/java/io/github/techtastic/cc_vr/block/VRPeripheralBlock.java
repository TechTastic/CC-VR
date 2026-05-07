package io.github.techtastic.cc_vr.block;

import io.github.techtastic.cc_vr.block.entity.VRPeripheralBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

public class VRPeripheralBlock extends Block implements EntityBlock {
    public VRPeripheralBlock(Properties properties) {
        super(properties);
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new VRPeripheralBlockEntity(blockPos, blockState);
    }

    @Override
    protected @NonNull RenderShape getRenderShape(BlockState blockState) {
        return RenderShape.MODEL;
    }

    @Override
    protected @NonNull InteractionResult useWithoutItem(BlockState blockState, Level level, BlockPos blockPos, Player player, BlockHitResult blockHitResult) {
        BlockEntity be = level.getBlockEntity(blockPos);
        if (!level.isClientSide() && be instanceof VRPeripheralBlockEntity vr) {
            if (vr.getBoundPlayer() == player)
                vr.setBoundPlayer(null);
            else
                vr.setBoundPlayer(player.getUUID());
            return InteractionResult.SUCCESS;
        }
        return super.useWithoutItem(blockState, level, blockPos, player, blockHitResult);
    }
}

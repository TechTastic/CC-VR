package io.github.techtastic.cc_vr.block;

import io.github.techtastic.cc_vr.block.entity.VRPeripheralBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

public class VRPeripheralBlock extends Block implements EntityBlock {
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final BooleanProperty EQUIPPED = BooleanProperty.create("equipped");
    public static final VoxelShape SHAPE;

    public VRPeripheralBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(EQUIPPED, false).setValue(FACING, Direction.NORTH));
    }

    @Override
    protected @NonNull VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
        return SHAPE;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder.add(EQUIPPED, FACING));
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext blockPlaceContext) {
        BlockState blockState = super.getStateForPlacement(blockPlaceContext);
        if (blockState == null)
            blockState = this.defaultBlockState();
        return blockState.setValue(FACING, blockPlaceContext.getHorizontalDirection().getOpposite());
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
            if (vr.getBoundPlayer() == player) {
                vr.setBoundPlayer(null);
                level.setBlockAndUpdate(blockPos, blockState.setValue(EQUIPPED, false));
                vr.computers.queueEvent("vr_unbind");
            } else {
                vr.setBoundPlayer(player.getUUID());
                level.setBlockAndUpdate(blockPos, blockState.setValue(EQUIPPED, true));
                vr.computers.queueEvent("vr_bind", player.getUUID().toString());
            }
            return InteractionResult.SUCCESS;
        }
        return super.useWithoutItem(blockState, level, blockPos, player, blockHitResult);
    }

    static {
        SHAPE = Shapes.or(Shapes.box(0, 0, 0, 1, 0.1, 1), Shapes.box(0.2, 0.1, 0.2, 0.8, 0.8, 0.8));
    }
}

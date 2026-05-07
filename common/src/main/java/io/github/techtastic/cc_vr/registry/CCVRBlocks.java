package io.github.techtastic.cc_vr.registry;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.DeferredSupplier;
import io.github.techtastic.cc_vr.block.VRPeripheralBlock;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;

import static io.github.techtastic.cc_vr.CCVR.MOD_ID;

public class CCVRBlocks {
    private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(MOD_ID, Registries.BLOCK);

    public static final DeferredSupplier<VRPeripheralBlock> VR_PERIPHERAL = BLOCKS.register("vr_peripheral", () ->
            new VRPeripheralBlock(BlockBehaviour.Properties.of()));

    public static void register() {
        BLOCKS.register();
    }
}

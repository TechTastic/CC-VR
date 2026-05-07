package io.github.techtastic.cc_vr.registry;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.DeferredSupplier;
import io.github.techtastic.cc_vr.block.entity.VRPeripheralBlockEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;

import static io.github.techtastic.cc_vr.CCVR.MOD_ID;

public class CCVRBlockEntities {
    private static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(MOD_ID, Registries.BLOCK_ENTITY_TYPE);

    public static final DeferredSupplier<BlockEntityType<VRPeripheralBlockEntity>> VR_PERIPHERAL = BLOCK_ENTITIES.register("vr_peripheral", () ->
            BlockEntityType.Builder.of(VRPeripheralBlockEntity::new, CCVRBlocks.VR_PERIPHERAL.get()).build(null));

    public static void register() {
        BLOCK_ENTITIES.register();
    }
}

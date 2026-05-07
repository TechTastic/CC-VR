package io.github.techtastic.cc_vr.registry;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.DeferredSupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;

import static io.github.techtastic.cc_vr.CCVR.MOD_ID;

public class CCVRItems {
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(MOD_ID, Registries.ITEM);

    public static final DeferredSupplier<BlockItem> VR_PERIPHERAL = ITEMS.register("vr_peripheral", () ->
            new BlockItem(CCVRBlocks.VR_PERIPHERAL.get(), new Item.Properties()));

    public static void register() {
        ITEMS.register();
    }
}

package io.github.techtastic.cc_vr.registry;

import dev.architectury.registry.CreativeTabRegistry;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.DeferredSupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

import static io.github.techtastic.cc_vr.CCVR.MOD_ID;

public class CCVRCreativeTabs {
    private static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(MOD_ID, Registries.CREATIVE_MODE_TAB);

    public static final DeferredSupplier<CreativeModeTab> MAIN = TABS.register(MOD_ID, () -> CreativeTabRegistry.create(builder -> builder
            .title(Component.translatable("itemGroup.cc_vr"))
            .icon(() -> new ItemStack(CCVRBlocks.VR_PERIPHERAL.get().asItem()))
            .displayItems((itemDisplayParameters, output) -> {
                output.accept(CCVRBlocks.VR_PERIPHERAL.get());
            })
            .build()
    ));

    public static void register() {
        TABS.register();
    }
}

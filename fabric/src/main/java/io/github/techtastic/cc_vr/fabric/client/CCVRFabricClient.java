package io.github.techtastic.cc_vr.fabric.client;

import dev.architectury.registry.client.rendering.RenderTypeRegistry;
import io.github.techtastic.cc_vr.registry.CCVRBlocks;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.renderer.RenderType;

public final class CCVRFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        // This entrypoint is suitable for setting up client-specific logic, such as rendering.

        RenderTypeRegistry.register(RenderType.cutout(), CCVRBlocks.VR_PERIPHERAL.get());
    }
}

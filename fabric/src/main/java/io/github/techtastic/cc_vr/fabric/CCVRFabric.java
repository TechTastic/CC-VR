package io.github.techtastic.cc_vr.fabric;

import dan200.computercraft.api.peripheral.PeripheralLookup;
import io.github.techtastic.cc_vr.peripheral.VRPeripheral;
import io.github.techtastic.cc_vr.registry.CCVRBlockEntities;
import net.fabricmc.api.ModInitializer;

import io.github.techtastic.cc_vr.CCVR;

public final class CCVRFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.

        // Run our common setup.
        CCVR.init();

        PeripheralLookup.get().registerForBlockEntity((be, direction) ->
                new VRPeripheral(be), CCVRBlockEntities.VR_PERIPHERAL.get());
    }
}

package io.github.techtastic.cc_vr.neoforge;

import dan200.computercraft.api.peripheral.PeripheralCapability;
import io.github.techtastic.cc_vr.block.entity.VRPeripheralBlockEntity;
import io.github.techtastic.cc_vr.peripheral.VRPeripheral;
import io.github.techtastic.cc_vr.registry.CCVRBlockEntities;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;

import io.github.techtastic.cc_vr.CCVR;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;

@Mod(CCVR.MOD_ID)
public final class CCVRNeoForge {
    public CCVRNeoForge(IEventBus modEventBus, ModContainer modContainer) {
        modEventBus.addListener(this::registerCapabilities);

        // Run our common setup.
        CCVR.init();
    }

    private void registerCapabilities(RegisterCapabilitiesEvent event) {
        event.registerBlockEntity(
                PeripheralCapability.get(),
                CCVRBlockEntities.VR_PERIPHERAL.get(),
                (be, direction) -> new VRPeripheral(be)
        );
    }
}

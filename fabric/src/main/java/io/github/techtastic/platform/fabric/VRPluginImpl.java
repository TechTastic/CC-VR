package io.github.techtastic.platform.fabric;

import io.github.techtastic.cc_vr.CCVR;
import net.blf02.vrapi.api.IVRAPI;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.entrypoint.EntrypointContainer;

import java.util.List;

public class VRPluginImpl {
    private static IVRAPI API;

    public static IVRAPI getVRAPI() {
        return API;
    }

    public static void initVR() {
        List<EntrypointContainer<IVRAPI>> apis = FabricLoader.getInstance().getEntrypointContainers("vrapi", IVRAPI.class);
        if (!apis.isEmpty()) {
            API = apis.getFirst().getEntrypoint();
            CCVR.HAS_VR_PLUGIN = true;
        }
    }
}

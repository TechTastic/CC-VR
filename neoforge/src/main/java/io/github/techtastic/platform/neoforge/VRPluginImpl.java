package io.github.techtastic.platform.neoforge;

import io.github.techtastic.cc_vr.CCVR;
import net.blf02.neoforge.VRAPIPlugin;
import net.blf02.neoforge.VRAPIPluginProvider;
import net.blf02.vrapi.api.IVRAPI;

@VRAPIPlugin
public class VRPluginImpl implements VRAPIPluginProvider {
    private static IVRAPI API;

    public static IVRAPI getVRAPI() {
        return API;
    }

    @Override
    public void getVRAPI(IVRAPI ivrapi) {
        API = ivrapi;
        CCVR.HAS_VR_PLUGIN = true;
    }
}

package io.github.techtastic.cc_vr;

import io.github.techtastic.cc_vr.registry.CCVRBlockEntities;
import io.github.techtastic.cc_vr.registry.CCVRBlocks;
import io.github.techtastic.cc_vr.registry.CCVRItems;

public final class CCVR {
    public static final String MOD_ID = "cc_vr";
    public static boolean HAS_VR_PLUGIN = false;

    public static void init() {
        CCVRBlocks.register();
        CCVRBlockEntities.register();
        CCVRItems.register();
    }
}

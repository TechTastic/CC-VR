package io.github.techtastic.platform;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.blf02.vrapi.api.IVRAPI;

public class VRPlugin {
    @ExpectPlatform
    public static IVRAPI getVRAPI() {
        throw new AssertionError();
    }
}

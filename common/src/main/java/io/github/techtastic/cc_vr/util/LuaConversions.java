package io.github.techtastic.cc_vr.util;

import net.minecraft.world.phys.Vec3;
import org.joml.Quaternionfc;
import org.vivecraft.api.data.VRBodyPartData;

import java.util.HashMap;
import java.util.Map;

public class LuaConversions {
    public static Map<String, Object> toLua(VRBodyPartData data) {
        HashMap<String, Object> map = new HashMap<>();

        map.put("pitch", data.getPitch());
        map.put("roll", data.getRoll());
        map.put("yaw", data.getYaw());
        map.put("pos", toLua(data.getPos()));
        map.put("dir", toLua(data.getDir()));
        map.put("rotation", toLua(data.getRotation()));

        return map;
    }

    public static Map<String, Double> toLua(Vec3 vec) {
        return Map.of(
                "x", vec.x,
                "y", vec.y,
                "z", vec.z
        );
    }

    public static Map<String, Double> toLua(Quaternionfc quat) {
        return Map.of(
                "x", (double) quat.x(),
                "y", (double) quat.y(),
                "z", (double) quat.z(),
                "w", (double) quat.w()
        );
    }
}

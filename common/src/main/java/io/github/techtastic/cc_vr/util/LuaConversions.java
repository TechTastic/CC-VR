package io.github.techtastic.cc_vr.util;

import net.blf02.vrapi.api.data.IVRData;
import net.minecraft.world.phys.Vec3;
import org.joml.Matrix4fc;
import org.joml.Vector4f;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LuaConversions {
    public static Map<String, Object> toLua(IVRData data) {
        HashMap<String, Object> map = new HashMap<>();

        map.put("pitch", (double) data.getPitch());
        map.put("roll", (double) data.getRoll());
        map.put("yaw", (double) data.getYaw());
        map.put("lookAngle", toLua(data.getLookAngle()));
        map.put("rotationMatrix", toLua(data.getRotationMatrix()));
        map.put("position", toLua(data.position()));

        return map;
    }

    public static Map<String, Double> toLua(Vec3 vec) {
        return Map.of(
                "x", vec.x,
                "y", vec.y,
                "z", vec.z
        );
    }

    public static List<List<Double>> toLua(Matrix4fc matrix) {
        List<List<Double>> list = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            Vector4f row = matrix.getRow(i, new Vector4f());
            list.add(i, List.of((double) row.x, (double) row.y, (double) row.z, (double) row.w));
        }

        return list;
    }
}

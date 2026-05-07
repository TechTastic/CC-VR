package io.github.techtastic.cc_vr.peripheral;

import dan200.computercraft.api.lua.IArguments;
import dan200.computercraft.api.lua.LuaException;
import dan200.computercraft.api.lua.LuaFunction;
import dan200.computercraft.api.peripheral.IPeripheral;
import io.github.techtastic.cc_vr.CCVR;
import io.github.techtastic.cc_vr.block.entity.VRPeripheralBlockEntity;
import io.github.techtastic.cc_vr.util.LuaConversions;
import io.github.techtastic.platform.VRPlugin;
import net.blf02.vrapi.api.data.IVRPlayer;
import net.blf02.vrapi.data.VRPlayer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import java.util.Map;

public class VRPeripheral implements IPeripheral {
    private final VRPeripheralBlockEntity be;

    public VRPeripheral(VRPeripheralBlockEntity be) {
        this.be = be;
    }

    private IVRPlayer getVRPlayer() throws LuaException {
        if (!(this.be.getBoundPlayer() instanceof Player player))
            throw new LuaException("No Bound Player!");
        if (!CCVR.HAS_VR_PLUGIN)
            throw new LuaException("No VR API instance!");
        if (!VRPlugin.getVRAPI().apiActive(player))
            throw new LuaException("Bound Player is not in VR!");
        if (!(VRPlugin.getVRAPI().getVRPlayer(this.be.getBoundPlayer()) instanceof IVRPlayer vr))
            throw new LuaException("Bound Player is not in VR!");
        return vr;
    }

    @Override
    public @NonNull String getType() {
        return "vr";
    }

    @Override
    public boolean equals(@Nullable IPeripheral other) {
        return other instanceof VRPeripheral vr && vr.be.equals(this.be);
    }

    @LuaFunction
    public final boolean hasBoundPlayer() {
        return this.be.getBoundPlayer() != null;
    }

    @LuaFunction
    public final boolean isInVR() throws LuaException {
        if (!this.hasBoundPlayer())
            throw new LuaException("No Bound Player!");
        return VRPlugin.getVRAPI().playerInVR(this.be.getBoundPlayer());
    }

    @LuaFunction
    public final boolean isLeftHanded() throws LuaException {
        if (!this.hasBoundPlayer())
            throw new LuaException("No Bound Player!");
        return VRPlugin.getVRAPI().isLeftHanded(this.be.getBoundPlayer());
    }

    @LuaFunction
    public final boolean isSeated() throws LuaException {
        if (!this.hasBoundPlayer())
            throw new LuaException("No Bound Player!");
        return VRPlugin.getVRAPI().isSeated(this.be.getBoundPlayer());
    }

    @LuaFunction
    public final void triggerHapticPulse(IArguments args) throws LuaException {
        if (!this.hasBoundPlayer())
            throw new LuaException("No Bound Player!");
        VRPlugin.getVRAPI().triggerHapticPulse(
                args.getInt(0),
                (float) args.getDouble(1),
                (float) args.optDouble(2, 160),
                (float) args.optDouble(3, 1),
                (float) args.optDouble(4, 0),
                (ServerPlayer) this.be.getBoundPlayer()
        );
    }

    @LuaFunction
    public final Map<String, Object> getHMD() throws LuaException {
        return LuaConversions.toLua(this.getVRPlayer().getHMD());
    }

    @LuaFunction
    public final Map<String, Object> getController0() throws LuaException {
        return LuaConversions.toLua(this.getVRPlayer().getController0());
    }

    @LuaFunction
    public final Map<String, Object> getController1() throws LuaException {
        return LuaConversions.toLua(this.getVRPlayer().getController1());
    }

    @LuaFunction
    public final Map<String, Object> getController(int controller) throws LuaException {
        return LuaConversions.toLua(this.getVRPlayer().getController(controller));
    }

    @LuaFunction
    public final Map<String, Object> getLeftEye() throws LuaException {
        return LuaConversions.toLua(this.getVRPlayer().getLeftEye());
    }

    @LuaFunction
    public final Map<String, Object> getRightEye() throws LuaException {
        return LuaConversions.toLua(this.getVRPlayer().getRightEye());
    }

    @LuaFunction
    public final Map<String, Object> getEye(int eye) throws LuaException {
        return LuaConversions.toLua(this.getVRPlayer().getEye(eye));
    }
}

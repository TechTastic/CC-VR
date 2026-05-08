package io.github.techtastic.cc_vr.peripheral;

import dan200.computercraft.api.lua.IArguments;
import dan200.computercraft.api.lua.LuaException;
import dan200.computercraft.api.lua.LuaFunction;
import dan200.computercraft.api.peripheral.IPeripheral;
import io.github.techtastic.cc_vr.CCVR;
import io.github.techtastic.cc_vr.block.entity.VRPeripheralBlockEntity;
import io.github.techtastic.cc_vr.util.LuaConversions;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import org.vivecraft.api.VRAPI;
import org.vivecraft.api.data.VRBodyPart;
import org.vivecraft.api.data.VRPose;
import org.vivecraft.api.server.VRServerAPI;
import org.vivecraft.server.ServerVRPlayers;
import org.vivecraft.server.ServerVivePlayer;
import org.vivecraft.server.api_impl.VRServerAPIImpl;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;

public class VRPeripheral implements IPeripheral {
    private final VRPeripheralBlockEntity be;

    public VRPeripheral(VRPeripheralBlockEntity be) {
        this.be = be;
    }

    private ServerVivePlayer getVRPlayer() throws LuaException {
        if (!(this.be.getBoundPlayer() instanceof Player player))
            throw new LuaException("No Bound Player!");
        if (!ServerVRPlayers.isVRPlayer((ServerPlayer) player))
            throw new LuaException("Bound Player is not in VR!");
        if (!(ServerVRPlayers.getVivePlayer((ServerPlayer) this.be.getBoundPlayer()) instanceof ServerVivePlayer vr))
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
        return ServerVRPlayers.isVRPlayer((ServerPlayer) this.be.getBoundPlayer());
    }

    @LuaFunction
    public final boolean isLeftHanded() throws LuaException {
        return this.getVRPlayer().isLeftHanded();
    }

    @LuaFunction
    public final boolean isSeated() throws LuaException {
        return this.getVRPlayer().isSeated();
    }

    @LuaFunction
    public final boolean isCrawling() throws LuaException {
        return this.getVRPlayer().crawling;
    }

    @LuaFunction
    public final double getHeightScale() throws LuaException {
        return this.getVRPlayer().heightScale;
    }

    @LuaFunction
    public final double getWorldScale() throws LuaException {
        return this.getVRPlayer().worldScale;
    }

    @LuaFunction
    public final String getFBTMode() throws LuaException {
        return this.getVRPlayer().asVRPose().getFBTMode().name();
    }

    @LuaFunction
    public final void triggerHapticPulse(IArguments args) throws LuaException {
        if (!this.hasBoundPlayer())
            throw new LuaException("No Bound Player!");
        VRServerAPI.instance().sendHapticPulse(
                (ServerPlayer) this.be.getBoundPlayer(),
                parseBodyPart(args.getString(0)),
                (float) args.getDouble(1),
                (float) args.optDouble(2, 160),
                (float) args.optDouble(3, 1),
                (float) args.optDouble(4, 0)
        );
    }

    @LuaFunction
    public final Map<String, Object> getHead() throws LuaException {
        VRPose pose = this.getVRPlayer().asVRPose();
        if (pose == null) return null;
        return LuaConversions.toLua(pose.getHead());
    }

    @LuaFunction
    public final Map<String, Object> getMainHand() throws LuaException {
        VRPose pose = this.getVRPlayer().asVRPose();
        if (pose == null) return null;
        return LuaConversions.toLua(pose.getMainHand());
    }

    @LuaFunction
    public final Map<String, Object> getOffhand() throws LuaException {
        VRPose pose = this.getVRPlayer().asVRPose();
        if (pose == null) return null;
        return LuaConversions.toLua(pose.getOffHand());
    }

    @LuaFunction
    public final Map<String, Object> getBodyPartData(String bodyPart) throws LuaException {
        VRBodyPart part = parseBodyPart(bodyPart);
        VRPose pose = this.getVRPlayer().asVRPose();
        if (pose == null) return null;
        try {
            return LuaConversions.toLua(Objects.requireNonNull(pose.getBodyPartData(part)));
        } catch (NullPointerException | MatchException ignored) {
            throw new LuaException("Invalid body part due to FBT mode!");
        }
    }

    private VRBodyPart parseBodyPart(String part) throws LuaException {
        try {
            return VRBodyPart.valueOf(part);
        } catch (IllegalArgumentException ignored) {
            throw new LuaException("Invalid body part! Valid body parts include: [" + String.join(", ", (String[]) Arrays.stream(VRBodyPart.values()).filter(this.getVRPlayer().asVRPose().getFBTMode()::bodyPartAvailable).map(Enum::name).toArray()) + "]!");
        }
    }
}

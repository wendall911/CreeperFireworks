package creeperfireworks.network;

import creeperfireworks.platform.Services;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

import org.joml.Vector3f;

import creeperfireworks.CreeperFireworks;

public record LaunchFireworksPacket(Vector3f location) implements CustomPacketPayload {

    public static final Type<LaunchFireworksPacket> TYPE = new Type<>(new ResourceLocation(CreeperFireworks.MODID, "launch_fireworks"));
    public static final StreamCodec<FriendlyByteBuf, LaunchFireworksPacket> STREAM_CODEC =
        StreamCodec.composite(
                ByteBufCodecs.VECTOR3F,
                LaunchFireworksPacket::location,
                LaunchFireworksPacket::new
        );

    public static void handle(Vector3f location) {

    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

}

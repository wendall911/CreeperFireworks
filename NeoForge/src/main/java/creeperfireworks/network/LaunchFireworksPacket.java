package creeperfireworks.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec3;

import creeperfireworks.CreeperFireworks;

public record LaunchFireworksPacket(Vec3 location) implements CustomPacketPayload {

    public static final ResourceLocation ID = new ResourceLocation(CreeperFireworks.MODID, "launch_fireworks");

    public LaunchFireworksPacket(FriendlyByteBuf buf) {
        this(new Vec3(buf.readVector3f()));
    }

    public void write(FriendlyByteBuf buf) {
        buf.writeVector3f(this.location.toVector3f());
    }

    @Override
    public ResourceLocation id() {
        return ID;
    }

}

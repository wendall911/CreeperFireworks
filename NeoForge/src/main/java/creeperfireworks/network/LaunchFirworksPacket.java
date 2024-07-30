package creeperfireworks.network;

import java.util.function.Supplier;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkEvent;

import creeperfireworks.util.FireworksHelper;

public class LaunchFirworksPacket {

    private final Vec3 location;

    public LaunchFirworksPacket(Vec3 location) {
        this.location = location;
    }

    public static void encode(LaunchFirworksPacket msg, FriendlyByteBuf buf) {
        buf.writeVector3f(msg.location.toVector3f());
    }

    public static LaunchFirworksPacket decode(FriendlyByteBuf buf) {
        return new LaunchFirworksPacket(new Vec3(buf.readVector3f()));
    }

    public static void handle(LaunchFirworksPacket msg, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ClientLevel level = Minecraft.getInstance().level;

            if (level != null) {
                FireworksHelper.launchFireworks(level, msg.location);
            }
        });
        ctx.get().setPacketHandled(true);
    }

}

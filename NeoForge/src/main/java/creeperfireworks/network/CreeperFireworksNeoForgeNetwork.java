package creeperfireworks.network;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

import creeperfireworks.CreeperFireworks;

public class CreeperFireworksNeoForgeNetwork {

    private static final String PROTOCOL_VERSION = "1";

    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
        new ResourceLocation(CreeperFireworks.MODID, "main"),
        () -> PROTOCOL_VERSION,
        PROTOCOL_VERSION::equals,
        PROTOCOL_VERSION::equals
    );

    public static void init() {
        INSTANCE.registerMessage(0, LaunchFirworksPacket.class, LaunchFirworksPacket::encode,
                LaunchFirworksPacket::decode, LaunchFirworksPacket::handle);
    }

}

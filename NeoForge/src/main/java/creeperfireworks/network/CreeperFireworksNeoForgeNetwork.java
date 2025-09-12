package creeperfireworks.network;

import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

import creeperfireworks.CreeperFireworks;

import static technology.roughness.whitenoise.util.ResourceLocationHelper.loc;

public class CreeperFireworksNeoForgeNetwork {

    private static final String PROTOCOL_VERSION = "1";

    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
        loc(CreeperFireworks.MODID, "main"),
        () -> PROTOCOL_VERSION,
        PROTOCOL_VERSION::equals,
        PROTOCOL_VERSION::equals
    );

    public static void init() {
        INSTANCE.registerMessage(0, LaunchFirworksPacket.class, LaunchFirworksPacket::encode,
                LaunchFirworksPacket::decode, LaunchFirworksPacket::handle);
    }

}

package creeperfireworks;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import creeperfireworks.network.CreeperFireworksNeoForgeNetwork;

@Mod(CreeperFireworks.MODID)
public class CreeperFireworksNeoForge {

    public CreeperFireworksNeoForge() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        eventBus.addListener(this::setup);
        CreeperFireworks.init();
    }

    private void setup(final FMLCommonSetupEvent evt) {
        CreeperFireworksNeoForgeNetwork.init();
    }

}


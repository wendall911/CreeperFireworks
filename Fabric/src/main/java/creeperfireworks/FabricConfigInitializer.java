package creeperfireworks;

import technology.roughness.whitenoise.config.WhiteNoiseConfigInitializer;

public class FabricConfigInitializer implements WhiteNoiseConfigInitializer {

    @Override
    public void onInitializeConfig() {
        CreeperFireworks.initConfig();
    }

}

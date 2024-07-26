package creeperfireworks.config;

import com.illusivesoulworks.spectrelib.config.SpectreConfigSpec;

import org.apache.commons.lang3.tuple.Pair;

public class ConfigHandler {

    public static final SpectreConfigSpec CLIENT_SPEC;
    public static final SpectreConfigSpec COMMON_SPEC;

    private static final Client CLIENT;
    private static final Common COMMON;

    static {
        final Pair<Client, SpectreConfigSpec> specPairClient = new SpectreConfigSpec.Builder().configure(Client::new);
        final Pair<Common, SpectreConfigSpec> specPairCommon = new SpectreConfigSpec.Builder().configure(Common::new);

        CLIENT_SPEC = specPairClient.getRight();
        CLIENT = specPairClient.getLeft();
        COMMON_SPEC = specPairCommon.getRight();
        COMMON = specPairCommon.getLeft();
    }

    public static void init() {}

    public static class Client {

        private final SpectreConfigSpec.BooleanValue showFireworks;

        public Client(SpectreConfigSpec.Builder builder) {
            builder.push("visuals");

            showFireworks = builder.comment("Show fireworks explosion after Creeper explosion.")
                .define("showFireworks", true);
        }

        public static boolean showFireworks() {
            return CLIENT.showFireworks.get();
        }
    }

    public static class Common {

        private final SpectreConfigSpec.BooleanValue disablePlayerDamage;
        private final SpectreConfigSpec.BooleanValue disableBlockDamage;
        private final SpectreConfigSpec.BooleanValue disableItemDamage;

        public Common(SpectreConfigSpec.Builder builder) {
            builder.push("general");

            disablePlayerDamage = builder.comment("Disable player damage on Creeper explosion.")
                .define("disablePlayerDamage", false);

            disableBlockDamage = builder.comment("Disable block damage on Creeper explosion.")
                .define("disableBlockDamage", true);

            disableItemDamage = builder.comment("Disable item damage on Creeper explosion.")
                .define("disableItemDamage", true);
        }

        public static boolean disablePlayerDamage() {
            return COMMON.disablePlayerDamage.get();
        }

        public static boolean disableBlockDamage() {
            return COMMON.disableBlockDamage.get();
        }

        public static boolean disableItemDamage() {
            return COMMON.disableItemDamage.get();
        }

    }

}

package creeperfireworks.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;

import com.illusivesoulworks.spectrelib.config.SpectreConfigSpec;

import net.minecraft.world.item.FireworkRocketItem;

import org.apache.commons.lang3.tuple.Pair;

import creeperfireworks.util.ColorHelper;

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

    public static void init() {
        Client.decodedColors.clear();

        CLIENT.fireworksColors.get().forEach((colorString) -> {
            Client.decodedColors.add(ColorHelper.decode(colorString).getRGB());
        });
    }

    public static class Client {

        private static final String[] colorStrings = new String[]{"#3B511A", "#41CD34"};
        private static final List<String> colorsList = List.of("colors");
        private static final List<Integer> decodedColors = new ArrayList<>();
        private static final Predicate<Object> hexValidator = s -> s instanceof String
            && ((String) s).matches("#[a-zA-Z\\d]{6}");
        private static final List<String> shapes = Stream.of(FireworkRocketItem.Shape.values()).map(Enum::name).toList();

        private final SpectreConfigSpec.IntValue fireworksChance;
        private final SpectreConfigSpec.ConfigValue<List<? extends String>> fireworksColors;
        private final SpectreConfigSpec.BooleanValue fireworksFlicker;
        private final SpectreConfigSpec.ConfigValue<String> fireworksShape;
        private final SpectreConfigSpec.IntValue fireworksHeight;

        public Client(SpectreConfigSpec.Builder builder) {
            builder.push("visuals");

            fireworksChance = builder
                .comment("Chance of fireworks after creeper explosion.")
                .defineInRange("fireworksChance", 100, 0, 100);
            fireworksColors = builder
                .comment("Colors to use in fireworks. Requires hex color. Default: "
                    + "[\"" + String.join("\", \"", colorStrings) + "\"]")
                .defineListAllowEmpty(colorsList, getColors(), hexValidator);
            fireworksFlicker = builder
                .comment("Fireworks flicker.")
                .define("fireworksFlicker", true);
            fireworksShape = builder
                .comment("Fireworks shape. One of: " + shapes)
                .defineInList("fireworksShape", "CREEPER", shapes);
            fireworksHeight = builder
                .comment("Height above creeper that fireworks explode. Default 5")
                .defineInRange("fireworksHeight", 5, 0, 32);
        }

        public static int fireworksChance() {
            return CLIENT.fireworksChance.get();
        }

        public static List<Integer> getColorsList() {
            return Client.decodedColors;
        }

        public static boolean fireworksFlicker() {
            return CLIENT.fireworksFlicker.get();
        }

        public static int getFireworksShape() {
            return FireworkRocketItem.Shape.valueOf(CLIENT.fireworksShape.get()).getId();
        }

        public static float getFireworksHeight() {
            return (float) CLIENT.fireworksHeight.get();
        }

        private static Supplier<List<? extends String>> getColors() {
            return () -> Arrays.asList(Client.colorStrings);
        }

    }

    public static class Common {

        private final SpectreConfigSpec.BooleanValue disableBlockDamage;
        private final SpectreConfigSpec.BooleanValue disableItemDamage;

        public Common(SpectreConfigSpec.Builder builder) {
            builder.push("general");

            disableBlockDamage = builder.comment("Disable block damage on Creeper explosion.")
                .define("disableBlockDamage", true);

            disableItemDamage = builder.comment("Disable dropped item damage on Creeper explosion.")
                .define("disableItemDamage", true);
        }

        public static boolean disableBlockDamage() {
            return COMMON.disableBlockDamage.get();
        }

        public static boolean disableItemDamage() {
            return COMMON.disableItemDamage.get();
        }

    }

}

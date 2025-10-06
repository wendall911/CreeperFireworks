package creeperfireworks.data;

import java.util.concurrent.CompletableFuture;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;

import net.minecraft.core.HolderLookup;

import creeperfireworks.common.Translations;
import creeperfireworks.CreeperFireworks;

public class CreeperFireworksLanguageProvider extends FabricLanguageProvider {

    protected CreeperFireworksLanguageProvider(FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryFuture) {
        super(dataOutput, "en_us", registryFuture);
    }

    @Override
    public void generateTranslations(HolderLookup.Provider provider, TranslationBuilder builder) {
        addTranslationTitle(builder, "Creeper Fireworks");
        addTranslation(builder, "visuals");
        addTranslation(builder, "fireworkschance");
        addTranslation(builder, "fireworksflicker");
        addTranslation(builder, "fireworkstrail");
        addTranslation(builder, "fireworksshape");
        addTranslation(builder, "fireworksheight");
        addTranslation(builder, "colors");
        addTranslation(builder, "general");
        addTranslation(builder, "disableblockdamage");
        addTranslation(builder, "disableitemdamage");
    }

    private void addTranslationTitle(TranslationBuilder builder, String title) {
        builder.add(CreeperFireworks.MODID + ".configuration.title", title);
    }

    private void addTranslation(TranslationBuilder builder, String id) {
        addTranslationName(builder, id);
        addTranslationDescription(builder, id);
    }

    private void addTranslationName(TranslationBuilder builder, String id) {
        builder.add(CreeperFireworks.MODID + ".configuration." + id + ".name", Translations.get(id + ".title"));
    }

    private void addTranslationDescription(TranslationBuilder builder, String id) {
        builder.add(CreeperFireworks.MODID + ".configuration." + id + ".description", Translations.get(id));
    }

    private void addTranslationDescription(TranslationBuilder builder, String id, String key) {
        builder.add(CreeperFireworks.MODID + ".configuration." + id + ".description", Translations.get(key));
    }

}

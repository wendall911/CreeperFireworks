package creeperfireworks.mixin;

import java.util.List;
import java.util.function.Predicate;

import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.ExplosionDamageCalculator;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import creeperfireworks.config.ConfigHandler;
import creeperfireworks.platform.Services;

@Mixin(Level.class)
public abstract class MixinLevel {

    /*
     * Limit explosion damage (configurable) to players, items and blocks. Any combination is OK.
     */
    @Inject(
        method = "explode(Lnet/minecraft/world/entity/Entity;Lnet/minecraft/world/damagesource/DamageSource;Lnet/minecraft/world/level/ExplosionDamageCalculator;DDDFZLnet/minecraft/world/level/Level$ExplosionInteraction;ZLnet/minecraft/core/particles/ParticleOptions;Lnet/minecraft/core/particles/ParticleOptions;Lnet/minecraft/core/Holder;)Lnet/minecraft/world/level/Explosion;",
        at = @At("HEAD"),
        cancellable = true
    )
    private void cf$explode(Entity entity, DamageSource damageSource, ExplosionDamageCalculator damageCalculator, double x, double y, double z, float radius, boolean fire, Level.ExplosionInteraction explosionInteraction, boolean animate, ParticleOptions smallParticles, ParticleOptions largeParticles, Holder<SoundEvent> soundEvent, CallbackInfoReturnable<Explosion> cir) {
        if (entity instanceof Creeper creeper) {
            creeperFireworks$boom(creeper);

            if (creeper.getCommandSenderWorld().getGameRules().getBoolean(GameRules.RULE_MOBGRIEFING)) {
                Level level = (Level) (Object) this;

                if (ConfigHandler.Common.disableBlockDamage()) {
                    Explosion explosion = new Explosion(level, entity, damageSource, damageCalculator, x, y, z, radius, fire, Explosion.BlockInteraction.KEEP, smallParticles, largeParticles, soundEvent);

                    explosion.explode();
                    explosion.finalizeExplosion(animate);

                    cir.setReturnValue(explosion);
                }
            }
        }
    }

    @Inject(
        method = "getEntities(Lnet/minecraft/world/entity/Entity;Lnet/minecraft/world/phys/AABB;Ljava/util/function/Predicate;)Ljava/util/List;",
        at = @At("RETURN"),
        cancellable = true
    )
    private void cf$getEntities(Entity enitity, AABB $$1, Predicate<? super Entity> $$2, CallbackInfoReturnable<List<Entity>> cir) {
        if (enitity instanceof Creeper) {
            cir.setReturnValue(creeperFireworks$getEntities(cir.getReturnValue()));
        }
    }

    /*
     * Filters item damage for dropped items and removes from list if damage is disabled.
     * This will prevent destruction of these items.
     */
    @Unique
    private <T extends Entity> List<T> creeperFireworks$getEntities(List<T> entities) {
        if (ConfigHandler.Common.disableItemDamage()) {
            entities.removeIf(e -> e instanceof ItemEntity);
        }

        return entities;
    }

    @Unique
    private void creeperFireworks$boom(Creeper creeper) {
        // Go boom!
        Services.PLATFORM.sendLaunchFireworksPacket(creeper);
    }

}

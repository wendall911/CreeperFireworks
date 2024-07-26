package creeperfireworks.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.List;
import java.util.Set;

@Mixin(Explosion.class)
public class MixinExplosion {

    @Shadow @Final private Level level;

    @Inject(method = "explode", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/phys/Vec3;<init>(DDD)V", ordinal = 1),
        locals = LocalCapture.CAPTURE_FAILHARD)
    private void creeperfireworks$explode(CallbackInfo ci, Set<BlockPos> blockPosSet, float a, int b, int c, int d, int e, int f, int g, List<Entity> entities) {
        Explosion explosion = (Explosion) (Object) this;

        if (explosion.getDirectSourceEntity() instanceof Creeper creeper && creeper.getCommandSenderWorld().getGameRules().getBoolean(GameRules.RULE_MOBGRIEFING)) {

        }
    }


}

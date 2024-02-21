package io.github.natanfudge.fudgefix.mixin;

import io.github.natanfudge.fudgefix.config.FFMidnightConfig;
import net.minecraft.block.spawner.MobSpawnerLogic;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(MobSpawnerLogic.class)
public class MixinMobSpawnerLogic {
    @Shadow private int maxNearbyEntities;

    /**
     * Minecraft left an open door for abuse by allowing as many entities as you want with one spawner.
     * We make it possible to limit the amount of mobs a spawner can have, to limit lag.
     * @see FFMidnightConfig#maxMobsPerSpawner
     */
    @Redirect(method = "serverTick", at = @At(value = "FIELD", target = "Lnet/minecraft/block/spawner/MobSpawnerLogic;maxNearbyEntities:I"))
    public int limitMaxNearbyEntities(MobSpawnerLogic instance) {
        return Math.min(FFMidnightConfig.maxMobsPerSpawner, this.maxNearbyEntities);
    }
}

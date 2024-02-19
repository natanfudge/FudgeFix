package io.github.natanfudge.fudgefix.mixin;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import io.github.natanfudge.fudgefix.FudgeFix;
import net.minecraft.command.argument.ParticleEffectArgumentType;
import net.minecraft.entity.AreaEffectCloudEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.registry.Registries;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AreaEffectCloudEntity.class)
public abstract class MixinAreaEffectCloudEntity {

    @Shadow public abstract void setParticleType(ParticleEffect particle);

    /**
     * Minecraft irresponsibly allows any particle to be multiplied and applied a million times over with AreaEffectCloud...
     * Here we don't allow certain particles which would be too laggy with this, see GriefChecker.laggyParticles
     */
    @Inject(method = "readCustomDataFromNbt", at = @At("TAIL"))
    public void readCustomDataFromNbtReplaceLaggyParticles(NbtCompound nbt, CallbackInfo ci) {
        if (nbt.contains("Particle", 8)) {
            var particle = nbt.getString("Particle");
            try {
                if (FudgeFix.particleIsLaggy(particle)) {
                    FudgeFix.LOGGER.warn("Replaced AOE effect particle that is too laggy: '{}' with smoke", particle);
                    this.setParticleType(ParticleEffectArgumentType.readParameters(new StringReader("smoke"), Registries.PARTICLE_TYPE.getReadOnlyWrapper()));
                }
            } catch (CommandSyntaxException var5) {
                FudgeFix.LOGGER.warn("Couldn't load replacement smoke particle");
            }
        }
    }
}

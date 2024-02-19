package io.github.natanfudge.fudgefix.mixin;

import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public class MixinLivingEntity {
    /**
     *   Don't heal negative values! Forge adds this check by itself.
     */
    @Inject(method = "heal(F)V", at = @At("HEAD"), cancellable = true)
    private void healButNotNegativeValues(float amount, CallbackInfo ci) {
        if (amount <= 0.0F) ci.cancel();
    }
}

package io.github.natanfudge.fudgefix.mixin;

import io.github.natanfudge.fudgefix.FudgeFix;
import net.minecraft.entity.projectile.ExplosiveProjectileEntity;
import net.minecraft.nbt.NbtCompound;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ExplosiveProjectileEntity.class)
public class MixinExplosiveProjectileEntity {
    @Shadow
    public double powerX;
    @Shadow
    public double powerY;
    @Shadow
    public double powerZ;

    /**
     * Some exploits use excessively high power to crash the server. We limit the power level when loading NBTs.
     */
    @Inject(method = "readCustomDataFromNbt(Lnet/minecraft/nbt/NbtCompound;)V", at = @At("TAIL"))
    private void onReadCustomDataFromNbtLimitPower(NbtCompound nbt, CallbackInfo ci) {
        // We can't let the power level be over 9000!
        this.powerX = FudgeFix.clampIn9000(powerX);
        this.powerY =  FudgeFix.clampIn9000(powerY);
        this.powerZ = FudgeFix.clampIn9000(powerZ);
    }

}

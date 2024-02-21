package io.github.natanfudge.fudgefix.mixin;

import io.github.natanfudge.fudgefix.FudgeFix;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(Entity.class)
public abstract class MixinEntity {
    @Shadow
    @Nullable
    public abstract Text getCustomName();

    @Shadow
    public abstract void setCustomName(Text name);

    /**
     * Limit the amount of characters in a custom name to a reasonable number (CHARACTER_LIMIT)
     */
    @Inject(method = "readNbt", at = @At("RETURN"))
    void afterReadingNbtTruncateString(NbtCompound nbt, CallbackInfo ci) {
        var customName = this.getCustomName();
        if (customName != null) {
            var string = customName.getString();
            if (customName.getString().length() > FudgeFix.CHARACTER_LIMIT) {
                this.setCustomName(Text.literal(string.substring(0, FudgeFix.CHARACTER_LIMIT) + "..."));
            }
        }

    }
}

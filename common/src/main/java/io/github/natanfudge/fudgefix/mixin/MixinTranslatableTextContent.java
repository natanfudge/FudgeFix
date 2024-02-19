package io.github.natanfudge.fudgefix.mixin;

import com.google.common.collect.ImmutableList;
import io.github.natanfudge.fudgefix.FudgeFix;
import net.minecraft.text.StringVisitable;
import net.minecraft.text.TranslatableTextContent;
import net.minecraft.util.Language;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(TranslatableTextContent.class)
public class MixinTranslatableTextContent {
    @Shadow
    private Language languageCache;
    @Shadow
    private List<StringVisitable> translations;

    @Shadow @Final private String key;

    /**
     * When expanding a translation string, check if it can become overly big and crash the game.
     * If so, don't expand it normally and just give it some placeholder text instead.
     */
    @Inject(method = "updateTranslations()V", at = @At("HEAD"), cancellable = true)
    public void stopSusFormatStrings(CallbackInfo ci) {
        Language language = Language.getInstance();
        if (language != this.languageCache) {
            if (FudgeFix.isSus((TranslatableTextContent) (Object) this)) {
                this.languageCache = language;
                this.translations = ImmutableList.of(StringVisitable.plain(this.key));
                ci.cancel();
            }
        }
    }


}

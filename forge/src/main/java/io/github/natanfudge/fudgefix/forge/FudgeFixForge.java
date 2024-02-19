package io.github.natanfudge.fudgefix.forge;

import io.github.natanfudge.fudgefix.FudgeFix;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(FudgeFix.MOD_ID)
public class FudgeFixForge {
    public FudgeFixForge() {
        FudgeFix.init();
    }
}
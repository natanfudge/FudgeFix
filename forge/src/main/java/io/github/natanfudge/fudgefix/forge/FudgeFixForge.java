package io.github.natanfudge.fudgefix.forge;

import io.github.natanfudge.fudgefix.FudgeFix;
import io.github.natanfudge.fudgefix.config.MidnightConfig;
import net.minecraftforge.client.ConfigScreenHandler;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(FudgeFix.MOD_ID)
public class FudgeFixForge {
    public FudgeFixForge() {
        ModLoadingContext.get().registerExtensionPoint(ConfigScreenHandler.ConfigScreenFactory.class, () ->
                new ConfigScreenHandler.ConfigScreenFactory(
                        (mc, screen) -> MidnightConfig.getScreen(screen, FudgeFix.MOD_ID)
                )
        );
        FudgeFix.init();
    }
}
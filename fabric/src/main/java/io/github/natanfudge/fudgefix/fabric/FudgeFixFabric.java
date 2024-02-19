package io.github.natanfudge.fudgefix.fabric;

import io.github.natanfudge.fudgefix.FudgeFix;
import net.fabricmc.api.ModInitializer;

public class FudgeFixFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        FudgeFix.init();
    }
}
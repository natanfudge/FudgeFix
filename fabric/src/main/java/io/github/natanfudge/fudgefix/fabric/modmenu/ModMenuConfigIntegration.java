package io.github.natanfudge.fudgefix.fabric.modmenu;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import io.github.natanfudge.fudgefix.FudgeFix;
import io.github.natanfudge.fudgefix.config.MidnightConfig;

public class ModMenuConfigIntegration implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
         return parent -> MidnightConfig.getScreen(parent, FudgeFix.MOD_ID);
    }
}

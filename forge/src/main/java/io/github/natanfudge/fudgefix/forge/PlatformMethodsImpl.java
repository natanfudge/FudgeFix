package io.github.natanfudge.fudgefix.forge;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.minecraftforge.fml.loading.FMLPaths;

import java.nio.file.Path;

public class PlatformMethodsImpl {
    public static Path getConfigDirectory() {
        return FMLPaths.CONFIGDIR.get();
    }

    public static boolean isPhysicalClient() {
        return FMLEnvironment.dist == Dist.CLIENT;
    }
}

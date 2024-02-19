package io.github.natanfudge.fudgefix;

import dev.architectury.injectables.annotations.ExpectPlatform;

import java.io.File;
import java.nio.file.Path;

public class PlatformMethods {
    @ExpectPlatform
    public static Path getConfigDirectory() {
        // Just throw an error, the content should get replaced at runtime.
        // Something is terribly wrong if this is not replaced.
        throw new AssertionError();
    }

    @ExpectPlatform
    public static boolean isPhysicalClient() {
        // Just throw an error, the content should get replaced at runtime.
        // Something is terribly wrong if this is not replaced.
        throw new AssertionError();
    }
}

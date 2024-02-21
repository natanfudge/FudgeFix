package io.github.natanfudge.fudgefix.config;


public class FFMidnightConfig extends MidnightConfig {
    @Comment
    public static Comment maxMobsPerSpawnerComment;
    @Entry
    public static int maxMobsPerSpawner = 1000;
    @Comment
    public static Comment disableCommandBlockCloningComment;

    @Entry public static boolean disableCommandBlockCloning = false;

}
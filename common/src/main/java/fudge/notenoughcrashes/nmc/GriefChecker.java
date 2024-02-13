package fudge.notenoughcrashes.nmc;

import net.minecraft.text.MutableText;
import net.minecraft.text.TranslatableTextContent;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


//TODO 0. Add config screen (modmenu integration, copy midnightlib)
// 1. Add config option for limiting mob per spawner
// 2. Add config option for disallowing nbt hotbar
// 3. Add config option for not allowing spawner in NBT hotbar (when minecraft loads in, hook in, and get rid of spawners, but not persistently, to not ruin their hotbar)
// 4. Check the rest of the crashes in the extended hotbar
// 5. Test all crashes and config in fabric
// 6. Test all crashes and config in forge
// 7. Create a new neoforge + fabric + quilt + forge project from the arch example
// 8. Copy all code there and see if things still work (run quilt and neoforge at least)
// 10. Write README.md
// 11. Create Curseforge project + modrinth project (with description from README)
// 12. Copy setup code from NEC and publish!

public class GriefChecker {
    public static final String MOD_NAME = "FudgeFix";
    public static final String MOD_ID = "fudge_fix";
    public static Logger LOGGER = LogManager.getLogger(MOD_NAME);

    public static boolean isSus(TranslatableTextContent content) {
        var folded = fold(content);
        // Sus nesting - sus
        if (folded == null) return true;
        // Not nested at all - not sus
        if (folded.size() == 1) return false;

        var totalExpansions = 1;
        for (TranslatableTextContent item : folded) {
            // We may get false positives in strings with excessive %%%% signs consecutively, but that is rare and doing
            // this is faster than actually parsing the string.
            totalExpansions *= countPercentSigns(item.getKey());
            // If you want more than 1000 format string expansions, you can fuck off.
            if (totalExpansions > 1000) return true;
        }
        return false;
    }

    private static int countPercentSigns(String string) {
        var count = 0;
        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i) == '%') count++;
        }
        return count;
    }

    /**
     * Returns null if this TranslatableTextContent because it's nested too much
     */
    @Nullable
    private static List<TranslatableTextContent> fold(TranslatableTextContent content) {
        List<TranslatableTextContent> list = new ArrayList<>();
        TranslatableTextContent current = content;

        var iteration = 1;

        while (true) {
            // We fail this TranslatableTextContent if it's nested 4 levels deep or more,
            // but IMO there's no reason why it should be nested more than 1 layer deep.
            if (iteration >= 4) return null;

            list.add(current);
            var args = current.getArgs();
            if (args.length == 0) {
                break;
            }
            var arg = args[0];
            if (arg instanceof MutableText text) {
                var nextContent = text.getContent();
                if (nextContent instanceof TranslatableTextContent translatableTextContent) {
                    current = translatableTextContent;
                } else {
                    break;
                }
            } else {
                break;
            }

            iteration++;
        }
        return list;
    }

    public static double clampIn9000(double value) {
        return Math.max(-9000, Math.min(value, 9000));
    }

    private static final Set<String> laggyParticles = Set.of("elder_guardian");

    public static boolean particleIsLaggy(String particleName) {
        return laggyParticles.contains(StringUtils.removeStart(particleName, "minecraft:"));
    }
}

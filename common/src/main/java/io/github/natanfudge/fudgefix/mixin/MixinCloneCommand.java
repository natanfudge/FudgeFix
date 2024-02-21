package io.github.natanfudge.fudgefix.mixin;

import io.github.natanfudge.fudgefix.config.FFMidnightConfig;
import net.minecraft.block.BlockState;
import net.minecraft.block.CommandBlock;
import net.minecraft.server.command.CloneCommand;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(CloneCommand.class)
public class MixinCloneCommand {
    /**
     * Prevents the /clone command from cloning command blocks, which is a method of crashing the server.
     */
    @Redirect(method = "execute", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/world/ServerWorld;setBlockState(Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;I)Z"))
    private static boolean preventCloningCommandsBlocks(ServerWorld instance, BlockPos blockPos, BlockState blockState, int i) {
        if (FFMidnightConfig.disableCommandBlockCloning && blockState.getBlock() instanceof CommandBlock) return false;
        else return instance.setBlockState(blockPos, blockState, i);
    }
}

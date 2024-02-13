package fudge.notenoughcrashes.mixins.nmc;

import fudge.notenoughcrashes.nmc.GriefChecker;
import net.minecraft.block.entity.BeehiveBlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BeehiveBlockEntity.class)
public class MixinBeehiveBlockEntity {

    @Shadow
    public void markDirty() {
        throw new IllegalStateException("Should be implemented by Minecraft itself");
    }

    /**
     * When Minecraft tries to release bees in a bee hive, it will try to load the bee from NBT, and then -
     * if it fails to read it from nbt - it ignores it. The problem is that the bee stays in the hive, so it tries
     * to release the problematic bee over and over - creating lag.
     * The solution from mojang is to just destroy the bee when reading nbt fails, but doing that from our side is
     * really problematic, as it may break easily when they change the bee code.
     * Instead, we simply iterate over the nbt when it's loaded to get rid of any problematic NBTs.
     */
    @Inject(method = "readNbt(Lnet/minecraft/nbt/NbtCompound;)V", at = @At(value = "HEAD"))
    private void onReadNbtPruneInvalidBees(NbtCompound nbt, CallbackInfo ci) {
        NbtList bees = nbt.getList("Bees", 10);
        var removed = bees.removeIf(bee -> {
            if (bee instanceof NbtCompound beeCompound) {
                var entityData = beeCompound.get("EntityData");
                if (entityData instanceof NbtCompound entityCompound) {
                    // If there's no entity id there as there should be, get rid of it.
                    // Note: we can't check for a certain id, because more bees may be added in the future.
                    return Registries.ENTITY_TYPE.getOrEmpty(new Identifier(entityCompound.getString("id"))).isEmpty();
                } else {
                    // Invalid type of EntityData
                    return true;
                }
            } else {
                // Invalid type of Bees
                return true;
            }
        });
        // Make sure the fix is saved so it won't be done every time the bee hive is loaded
        if (removed) {
            GriefChecker.LOGGER.warn("Invalid bee NBTs were detected and removed");
            this.markDirty();
        }
    }
}

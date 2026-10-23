
package dev.hopperspeed.mixin;

import dev.hopperspeed.HopperSpeedSettings;
import net.minecraft.world.level.block.entity.HopperBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = HopperBlockEntity.class, remap = false)
public abstract class HopperBlockEntityMixin {

    @Shadow(remap = false)
    private int cooldownTime;

    @Inject(
        method = "setCooldown",
        at = @At("HEAD"),
        cancellable = true,
        remap = false
    )
    private void hopperspeed$setCooldown(
            int originalTicks,
            CallbackInfo ci) {

        int wantedTicks = HopperSpeedSettings.getInterval();

        if (originalTicks >= 7
                && originalTicks <= 8
                && wantedTicks < originalTicks) {

            this.cooldownTime = wantedTicks;
            ci.cancel();
        }
    }
}

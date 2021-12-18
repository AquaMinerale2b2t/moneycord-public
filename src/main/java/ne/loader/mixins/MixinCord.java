package ne.loader.mixins;

import me.earth.earthhack.impl.Earthhack;
import ne.loader.Cord;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = Earthhack.class, remap = false)
public class MixinCord
{
    @Inject(method = "init", at = @At("HEAD"))
    private static void initHook(CallbackInfo info) {
        System.out.println("[M0NEYC0RD] Initialize");
        Cord.initialize();
    }
}

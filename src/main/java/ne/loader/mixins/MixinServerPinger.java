package ne.loader.mixins;

import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.network.ServerPinger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = {ServerPinger.class})
public class MixinServerPinger {

    @Inject(method = "ping", at = @At(value = "HEAD"), cancellable = true)
    public void pingHook(ServerData server, CallbackInfo ci) {
        System.out.println("Preventing script kiddie attack at IP: " + server.serverIP);
        ci.cancel();
    }

    @Inject(method = "tryCompatibilityPing", at = @At(value = "HEAD"), cancellable = true)
    public void tryCompatibilityPingHook(ServerData server, CallbackInfo ci) {
        System.out.println("Preventing script kiddie attack at IP: " + server.serverIP);
        ci.cancel();
    }
}
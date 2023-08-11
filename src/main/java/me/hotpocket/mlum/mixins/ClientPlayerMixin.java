package me.hotpocket.mlum.mixins;

import me.hotpocket.mlum.Mlum;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
@Environment(EnvType.CLIENT)
public abstract class ClientPlayerMixin {

    @Shadow public abstract Text getName();

    @Inject(at = @At("HEAD"), method = "isGlowing", cancellable = true)
    public void isGlowing(CallbackInfoReturnable<Boolean> cir) {
        Mlum options = Mlum.instance;
        cir.setReturnType(switch (getName().getString()) {
            case "file" -> options.getLocateFile().getValue() || options.getLocateRazor().getValue();
            case "soap" -> options.getLocateSoap().getValue();
            case "wire" -> options.getLocateGlass().getValue() || options.getLocateNails().getValue();
            case "metal" -> options.getLocateMetal().getValue();
            case "tape" -> options.getLocateTape().getValue() || options.getLocateWire().getValue();
            case "baseballbat" -> options.getLocateBat().getValue();
            case "sock" -> options.getLocateSock().getValue();
            case "timber" -> options.getLocateTimber().getValue() || options.getLocateNewspaper().getValue();
            case "crowbar" -> (options.getLocateCrowbar().getValue();
            case "trowel" -> options.getLocateTrowel().getValue();
            case "cloak" -> options.getLocateCloak().getValue();
            case "bellyrub" -> options.getLocateShank().getValue();
        });
    }

}

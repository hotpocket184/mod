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
        switch (getName().getString()) {
            case ("file"):
                if (options.getLocateFile().getValue() || options.getLocateRazor().getValue())
                    cir.setReturnValue(true);
                else
                    cir.setReturnValue(false);
                break;
            case ("soap"):
                if (options.getLocateSoap().getValue())
                    cir.setReturnValue(true);
                else
                    cir.setReturnValue(false);
                break;
            case ("wire"):
                if (options.getLocateGlass().getValue() || options.getLocateNails().getValue())
                    cir.setReturnValue(true);
                else
                    cir.setReturnValue(false);
                break;
            case ("metal"):
                if (options.getLocateMetal().getValue())
                    cir.setReturnValue(true);
                else
                    cir.setReturnValue(false);
                break;
            case ("tape"):
                if (options.getLocateTape().getValue() || options.getLocateWire().getValue())
                    cir.setReturnValue(true);
                else
                    cir.setReturnValue(false);
                break;
            case ("baseballbat"):
                if (options.getLocateBat().getValue())
                    cir.setReturnValue(true);
                else
                    cir.setReturnValue(false);
                break;
            case ("sock"):
                if (options.getLocateSock().getValue())
                    cir.setReturnValue(true);
                else
                    cir.setReturnValue(false);
                break;
            case ("timber"):
                if (options.getLocateTimber().getValue() || options.getLocateNewspaper().getValue())
                    cir.setReturnValue(true);
                else
                    cir.setReturnValue(false);
                break;
            case ("crowbar"):
                if (options.getLocateCrowbar().getValue())
                    cir.setReturnValue(true);
                else
                    cir.setReturnValue(false);
                break;
            case ("trowel"):
                if (options.getLocateTrowel().getValue())
                    cir.setReturnValue(true);
                else
                    cir.setReturnValue(false);
                break;
            case ("cloak"):
                if (options.getLocateCloak().getValue())
                    cir.setReturnValue(true);
                else
                    cir.setReturnValue(false);
                break;
            case ("bellyrub"):
                if (options.getLocateShank().getValue())
                    cir.setReturnValue(true);
                else
                    cir.setReturnValue(false);
                break;
        }
    }
}

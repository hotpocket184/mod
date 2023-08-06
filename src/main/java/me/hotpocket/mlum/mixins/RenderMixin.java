package me.hotpocket.mlum.mixins;

import me.hotpocket.mlum.Mlum;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(Screen.class)
@Environment(EnvType.CLIENT)
public abstract class RenderMixin {

    @Inject(method = "getTooltipFromItem", at = @At(value = "HEAD"))
    protected void renderTooltip(ItemStack stack, CallbackInfoReturnable<List<Text>> cir) {
        Mlum.instance.isContraband = stack.getNbt() != null && stack.getNbt().get("display") != null && stack.getNbt().get("display").toString().contains("CONTRABAND");
    }
}

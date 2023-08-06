package me.hotpocket.mlum.mixins;

import me.hotpocket.mlum.Mlum;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.impl.client.itemgroup.CreativeGuiExtensions;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.CreativeInventoryScreen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(CreativeInventoryScreen.class)
@Environment(EnvType.CLIENT)
public abstract class CreativeRenderMixin {

    @Inject(method = "renderTooltip", at = @At(value = "HEAD"))
    protected void renderTooltip(MatrixStack matrices, ItemStack stack, int x, int y, CallbackInfo ci) {
        Mlum.instance.isContraband = stack.getNbt() != null && stack.getNbt().get("display") != null && stack.getNbt().get("display").toString().contains("CONTRABAND");
    }
}

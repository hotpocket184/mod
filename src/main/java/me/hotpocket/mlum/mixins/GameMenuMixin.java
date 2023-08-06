package me.hotpocket.mlum.mixins;

import me.hotpocket.mlum.ItemFinderScreen;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.GameMenuScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Objects;
import java.util.function.Supplier;

@Mixin(GameMenuScreen.class)
@Environment(EnvType.CLIENT)
public class GameMenuMixin {

    @Inject(method = "createButton", at = @At(value = "RETURN"), cancellable = true)
    protected void gameMenu(Text text, Supplier<Screen> screenSupplier, CallbackInfoReturnable<ButtonWidget> cir) {
        if (Objects.equals(text, Text.translatable("gui.advancements"))) {
            cir.setReturnValue(ButtonWidget.builder(Text.of("Item Finder"), (button) -> {
                MinecraftClient.getInstance().setScreen(new ItemFinderScreen(MinecraftClient.getInstance().currentScreen, MinecraftClient.getInstance().options));
            }).width(98).build());
        }
    }
}

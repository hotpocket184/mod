package me.hotpocket.mlum.mixins;

import me.hotpocket.mlum.Mlum;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.tooltip.TooltipBackgroundRenderer;
import net.minecraft.client.render.BufferBuilder;
import net.minecraft.util.math.ColorHelper;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TooltipBackgroundRenderer.class)
@Environment(EnvType.CLIENT)
public abstract class TooltipMixin {

    @Inject(method = "render", at = @At(
            value = "TAIL"))
    private static void render(TooltipBackgroundRenderer.RectangleRenderer renderer, Matrix4f matrix, BufferBuilder buffer, int x, int y, int width, int height, int z, CallbackInfo ci) {
        if (Mlum.instance.isContraband) {
            int newColor = ColorHelper.Argb.getArgb(245, 150, 0, 0);

            int i = x - 3;
            int j = y - 3;
            int k = width + 3 + 3;
            int l = height + 3 + 3;
            renderHorizontalLine(renderer, matrix, buffer, i, j - 1, k, z, newColor);
            renderHorizontalLine(renderer, matrix, buffer, i, j + l, k, z, newColor);
            renderRectangle(renderer, matrix, buffer, i, j, k, l, z, newColor);
            renderVerticalLine(renderer, matrix, buffer, i - 1, j, l, z, newColor);
            renderVerticalLine(renderer, matrix, buffer, i + k, j, l, z, newColor);
            renderBorder(renderer, matrix, buffer, i, j + 1, k, l, z, 1358888960, 1350500352);
            Mlum.instance.isContraband = false;
        }
    }

    private static void renderBorder(TooltipBackgroundRenderer.RectangleRenderer renderer, Matrix4f matrix, BufferBuilder buffer, int x, int y, int width, int height, int z, int startYColor, int endYColor) {
        renderVerticalLine(renderer, matrix, buffer, x, y, height - 2, z, startYColor, endYColor);
        renderVerticalLine(renderer, matrix, buffer, x + width - 1, y, height - 2, z, startYColor, endYColor);
        renderHorizontalLine(renderer, matrix, buffer, x, y - 1, width, z, startYColor);
        renderHorizontalLine(renderer, matrix, buffer, x, y - 1 + height - 1, width, z, endYColor);
    }

    private static void renderVerticalLine(TooltipBackgroundRenderer.RectangleRenderer renderer, Matrix4f matrix, BufferBuilder buffer, int x, int y, int height, int z, int color) {
        renderer.blit(matrix, buffer, x, y, x + 1, y + height, z, color, color);
    }

    private static void renderVerticalLine(TooltipBackgroundRenderer.RectangleRenderer renderer, Matrix4f matrix, BufferBuilder buffer, int x, int y, int height, int z, int startColor, int endColor) {
        renderer.blit(matrix, buffer, x, y, x + 1, y + height, z, startColor, endColor);
    }

    private static void renderHorizontalLine(TooltipBackgroundRenderer.RectangleRenderer renderer, Matrix4f matrix, BufferBuilder buffer, int x, int y, int width, int z, int color) {
        renderer.blit(matrix, buffer, x, y, x + width, y + 1, z, color, color);
    }

    private static void renderRectangle(TooltipBackgroundRenderer.RectangleRenderer renderer, Matrix4f matrix, BufferBuilder buffer, int x, int y, int width, int height, int z, int color) {
        renderer.blit(matrix, buffer, x, y, x + width, y + height, z, color, color);
    }
}

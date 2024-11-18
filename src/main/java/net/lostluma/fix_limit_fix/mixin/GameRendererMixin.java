package net.lostluma.fix_limit_fix.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.client.render.GameRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(GameRenderer.class)
public class GameRendererMixin {
	/**
	 * Change the FPS limit for the "Power Saver" mode to 60 instead of 40.
	 */
	@ModifyExpressionValue(
		method = "render",
		at = @At(value = "CONSTANT", args = "intValue=40")
	)
	private int powerSaverValue(int original) {
		return 60;
	}

	/**
	 * Fixes the condition that pauses the render thread to work with "Balanced" FPS limit in game.
	 * By default, it only applies when using the "Power Saver" mode, which the user may not want to use.
	 */
	@ModifyExpressionValue(
		method = "render",
		at = @At(value = "FIELD", target = "Lnet/minecraft/client/options/GameOptions;fpsLimit:I", ordinal = 3)
	)
	private int shouldLimitFps0(int original) {
		return original == 1 ? 2 : original;
	}

	/**
	 * Fixes the condition that pauses the render thread to work with "Balanced" FPS limit in menu.
	 * By default, it only applies when using the "Power Saver" mode, which the user may not want to use.
	 */
	@ModifyExpressionValue(
		method = "render",
		at = @At(value = "FIELD", target = "Lnet/minecraft/client/options/GameOptions;fpsLimit:I", ordinal = 4)
	)
	private int shouldLimitFps1(int original) {
		return original == 1 ? 2 : original;
	}
}

package daniking.lancheatingoff.mixin;


import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.Drawable;
import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.Selectable;
import net.minecraft.client.gui.screen.OpenToLanScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.CyclingButtonWidget;
import net.minecraft.text.Text;
import net.minecraft.world.GameMode;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(OpenToLanScreen.class)
@Environment(EnvType.CLIENT)
public class TurnOffLanCheatingMixin  extends Screen {

    @Shadow
    private GameMode gameMode;
    @Final
    @Shadow
    private static Text GAME_MODE_TEXT;

    protected TurnOffLanCheatingMixin(Text title) {
        super(title);
    }

    @Redirect(at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/OpenToLanScreen;addDrawableChild(Lnet/minecraft/client/gui/Element;)Lnet/minecraft/client/gui/Element;", ordinal = 1), method = "init")
    private <T extends Element & Drawable & Selectable> T removeCheatingButton(OpenToLanScreen openToLanScreen, T drawableElement) {
        return null;
    }

    @Redirect(at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/OpenToLanScreen;addDrawableChild(Lnet/minecraft/client/gui/Element;)Lnet/minecraft/client/gui/Element;", ordinal = 0), method = "init")
    private <T extends Element & Drawable & Selectable> T centerButton(OpenToLanScreen openToLanScreen, T drawableElement) {
        return (T) this.addDrawableChild(CyclingButtonWidget.builder(GameMode::getSimpleTranslatableName).values(GameMode.SURVIVAL, GameMode.SPECTATOR, GameMode.CREATIVE, GameMode.ADVENTURE).initially(this.gameMode).build(this.width / 2 - 75, 120, 150, 20, GAME_MODE_TEXT, (button, gameMode) -> {
            this.gameMode = gameMode;
        }));
    }

}

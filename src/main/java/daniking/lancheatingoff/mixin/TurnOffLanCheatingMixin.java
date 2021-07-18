package daniking.lancheatingoff.mixin;


import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.Drawable;
import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.Selectable;
import net.minecraft.client.gui.screen.OpenToLanScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(OpenToLanScreen.class)
@Environment(EnvType.CLIENT)
public class TurnOffLanCheatingMixin  {

    @Redirect(at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/OpenToLanScreen;addDrawableChild(Lnet/minecraft/client/gui/Element;)Lnet/minecraft/client/gui/Element;", ordinal = 1), method = "init")
    protected <T extends Element & Drawable & Selectable> T removeCheatingButton(OpenToLanScreen openToLanScreen, T drawableElement) {
        return null;
    }

}

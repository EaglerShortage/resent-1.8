package dev.resent.module.impl.hud;

import dev.resent.annotation.RenderModule;
import dev.resent.module.base.Mod.Category;
import dev.resent.module.base.RenderMod;
import dev.resent.ui.Theme;

@RenderModule(name = "Server info", category = Category.HUD, x = 4, y = 110)
public class ServerInfo extends RenderMod {

    public int getWidth() {
        return mc.fontRendererObj.getStringWidth(getText()) + 4;
    }

    public int getHeight() {
        return mc.fontRendererObj.FONT_HEIGHT + 4;
    }

    public void draw() {
        drawString(getText(), this.x + 2, this.y + 2, Theme.getFontColor(Theme.getFontId()), Theme.getTextShadow());
    }

    public String getText() {
        if (mc.getCurrentServerData() != null) {
            return "[Playing on: " + mc.getCurrentServerData().serverIP + "]";
        }
        return "[Playing on: Not connected]";
    }
}

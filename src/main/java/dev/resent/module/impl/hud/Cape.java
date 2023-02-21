package dev.resent.module.impl.hud;

import dev.resent.annotation.Module;
import dev.resent.cape.CapeUi;
import dev.resent.module.base.Mod;
import dev.resent.module.base.Mod.Category;

@Module(name = "Cape", category = Category.MISC)
public class Cape extends Mod{
    
    public void onEnable(){
        mc.displayGuiScreen(new CapeUi());
    }
}

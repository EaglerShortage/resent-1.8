package dev.resent.module.impl.misc;

import dev.resent.annotation.Module;
import dev.resent.module.base.Category;
import dev.resent.module.base.Mod;
import dev.resent.module.setting.BooleanSetting;

@Module(name = "Scoreboard", category = Category.MISC, hasSetting = true)
public class Scoreboard extends Mod {

    public Scoreboard() {
        addSetting(numbers);
    }

    public BooleanSetting numbers = new BooleanSetting("Numbers", "", false);
}

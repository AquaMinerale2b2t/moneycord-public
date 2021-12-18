package pig.money.cord.modules;

import me.earth.earthhack.impl.util.text.ChatUtil;
import me.earth.earthhack.impl.util.text.TextColor;

public class Notify {

    public static void enable(String name){
        ChatUtil.sendMessage(TextColor.BOLD + name + " " + TextColor.GREEN + "enabled.");
    }

    public static void disable(String name){
        ChatUtil.sendMessage(TextColor.BOLD + name + " " + TextColor.RED + "disabled.");
    }

}

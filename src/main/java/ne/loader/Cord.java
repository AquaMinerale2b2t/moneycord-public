package ne.loader;

import me.earth.earthhack.api.plugin.Plugin;
import me.earth.earthhack.api.register.exception.AlreadyRegisteredException;
import me.earth.earthhack.impl.managers.Managers;
import org.lwjgl.opengl.Display;
import pig.money.cord.modules.antiLog4j.AntiLog4j;
import pig.money.cord.modules.autototem.AutoTotem;
import pig.money.cord.modules.chorushelper.ChorusHelper;
import pig.money.cord.modules.donkeyhelper.DonkeyHelper;
import pig.money.cord.modules.fastfall.FastFall;
import pig.money.cord.modules.feetplace.FeetPlace;
import pig.money.cord.modules.instantwalk.InstantWalk;
import pig.money.cord.modules.phase.Phase;
import pig.money.cord.modules.vclip.Vclip;


@SuppressWarnings("unused")
public class Cord implements Plugin {

    public static void initialize(){

        Display.setTitle("");

    }

    @Override
    public void load() {
        try {

            Managers.MODULES.register(new FastFall());
            Managers.MODULES.register(new AutoTotem());
            Managers.MODULES.register(new Vclip());
            Managers.MODULES.register(new FeetPlace());
            Managers.MODULES.register(new Phase());
            Managers.MODULES.register(new ChorusHelper());
            Managers.MODULES.register(new DonkeyHelper());
            Managers.MODULES.register(new InstantWalk());
            Managers.MODULES.register(new AntiLog4j());

        } catch (AlreadyRegisteredException e) { e.printStackTrace(); }
    }
}

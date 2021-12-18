package pig.money.cord.modules.phase;

import me.earth.earthhack.api.module.data.DefaultData;

final class PhaseData extends DefaultData<Phase>
{
    public PhaseData(Phase module) {
        super(module);
    }

    @Override
    public int getColor() {
        return 0xffffffff;
    }

    @Override
    public String getDescription() {
        return ": ^) ";
    }

}
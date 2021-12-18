package pig.money.cord.modules.instantwalk;

import me.earth.earthhack.api.module.data.DefaultData;

final class InstantWalkData extends DefaultData<InstantWalk>
{
    public InstantWalkData(InstantWalk module) {
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
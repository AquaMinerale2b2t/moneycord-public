package pig.money.cord.modules.fastfall;

import me.earth.earthhack.api.module.data.DefaultData;

final class FastFallData extends DefaultData<FastFall>
{
    public FastFallData(FastFall module) {
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
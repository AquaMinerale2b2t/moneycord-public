package pig.money.cord.modules.antiLog4j;

import me.earth.earthhack.api.module.data.DefaultData;

final class AntiLog4jData extends DefaultData<AntiLog4j>
{
    public AntiLog4jData(AntiLog4j module) {
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
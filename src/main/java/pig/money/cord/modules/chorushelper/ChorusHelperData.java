package pig.money.cord.modules.chorushelper;

import me.earth.earthhack.api.module.data.DefaultData;

final class ChorusHelperData extends DefaultData<ChorusHelper>
{
    public ChorusHelperData(ChorusHelper module) {
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
package pig.money.cord.modules.donkeyhelper;

import me.earth.earthhack.api.module.data.DefaultData;

final class DonkeyHelperData extends DefaultData<DonkeyHelper>
{
    public DonkeyHelperData(DonkeyHelper module) {
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
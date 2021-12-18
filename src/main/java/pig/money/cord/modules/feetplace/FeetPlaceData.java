package pig.money.cord.modules.feetplace;

import me.earth.earthhack.api.module.data.DefaultData;

final class FeetPlaceData extends DefaultData<FeetPlace>
{
    public FeetPlaceData(FeetPlace module) {
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
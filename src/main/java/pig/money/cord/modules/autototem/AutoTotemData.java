package pig.money.cord.modules.autototem;

import me.earth.earthhack.api.module.data.DefaultData;

final class AutoTotemData extends DefaultData<AutoTotem>
{
    public AutoTotemData(AutoTotem module) {
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
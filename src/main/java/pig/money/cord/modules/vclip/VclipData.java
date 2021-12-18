package pig.money.cord.modules.vclip;

import me.earth.earthhack.api.module.data.DefaultData;

final class VclipData extends DefaultData<Vclip>
{
    public VclipData(Vclip module) {
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
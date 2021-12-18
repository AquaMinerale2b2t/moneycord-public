package pig.money.cord.modules.example;

import me.earth.earthhack.api.module.data.DefaultData;

final class ExampleData extends DefaultData<Example>
{
    public ExampleData(Example module) {
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
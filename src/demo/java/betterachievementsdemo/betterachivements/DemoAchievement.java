package betterachievementsdemo.betterachivements;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;

import betterachievements.api.components.achievement.ICustomBackgroundColour;

public abstract class DemoAchievement extends Achievement implements ICustomBackgroundColour {

    public DemoAchievement(String id, String name, int column, int row, Item item, Achievement parent) {
        super(id, name, column, row, item, parent);
    }

    public DemoAchievement(String id, String name, int column, int row, Block block, Achievement parent) {
        super(id, name, column, row, block, parent);
    }

    public DemoAchievement(String id, String name, int column, int row, ItemStack itemStack, Achievement parent) {
        super(id, name, column, row, itemStack, parent);
    }
}

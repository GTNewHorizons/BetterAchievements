package betterachievements.handler.message;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.stats.Achievement;

import betterachievements.registry.AchievementRegistry;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;

public class AchievementUnlockMessage implements IMessage, IMessageHandler<AchievementUnlockMessage, IMessage> {

    private String achievementId;

    public AchievementUnlockMessage() {}

    public AchievementUnlockMessage(Achievement achievement) {
        this.achievementId = achievement.statId;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        int length = buf.readInt();
        this.achievementId = new String(buf.readBytes(length).array());
    }

    @Override
    public void toBytes(ByteBuf buf) {
        byte[] bytes = this.achievementId.getBytes();
        buf.writeInt(bytes.length);
        buf.writeBytes(bytes);
    }

    @Override
    public IMessage onMessage(AchievementUnlockMessage message, MessageContext ctx) {
        EntityPlayer player = ctx.getServerHandler().playerEntity;
        if (player.capabilities.isCreativeMode) {
            Achievement achievement = AchievementRegistry.instance().getAchievement(message.achievementId);
            unlockAchievement(achievement, player);
        }
        return null;
    }

    private void unlockAchievement(Achievement achievement, EntityPlayer player) {
        if (achievement.parentAchievement != null) unlockAchievement(achievement.parentAchievement, player);
        player.triggerAchievement(achievement);
    }
}

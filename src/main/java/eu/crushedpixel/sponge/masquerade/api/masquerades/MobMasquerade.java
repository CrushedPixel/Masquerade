package eu.crushedpixel.sponge.masquerade.api.masquerades;

import eu.crushedpixel.sponge.masquerade.api.manipulators.EntityDataManipulator;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.network.Packet;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.network.play.server.SPacketSpawnMob;
import org.spongepowered.api.entity.living.player.Player;

import java.util.ArrayList;
import java.util.List;

public abstract class MobMasquerade<E extends EntityLivingBase, D extends EntityDataManipulator<?>> extends Masquerade<E, D> {

    // the entity type
    protected final int entityType;

    public MobMasquerade(Player player, Class<E> entityClass) {
        super(player, entityClass);

        Integer entityType = EntityList.CLASS_TO_ID.get(entityClass);
        if (entityType == null) throw new IllegalArgumentException("Provided entity class could not be mapped to entity ID");

        this.entityType = entityType;
    }

    @Override
    public List<Packet> createSpawnPackets(double posX, double posY, double posZ,
                                           byte yaw, byte pitch, byte headYaw,
                                           short velX, short velY, short velZ) {
        List<Packet> packetList = new ArrayList<>();

        SPacketSpawnMob packetSpawnMob = new SPacketSpawnMob();
        packetSpawnMob.entityId = this.entityID;
        packetSpawnMob.uniqueId = this.entityUUID;
        packetSpawnMob.type = this.entityType;
        packetSpawnMob.x = posX;
        packetSpawnMob.y = posY;
        packetSpawnMob.z = posZ;
        packetSpawnMob.yaw = yaw;
        packetSpawnMob.pitch = pitch;
        packetSpawnMob.headPitch = headYaw; // the packet's field is incorrectly translated
        packetSpawnMob.velocityX = velX;
        packetSpawnMob.velocityY = velY;
        packetSpawnMob.velocityZ = velZ;
        packetSpawnMob.dataManager = new EntityDataManager(null);

        packetList.add(packetSpawnMob);
        return packetList;
    }

}

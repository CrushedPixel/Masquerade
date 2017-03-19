package eu.crushedpixel.sponge.masquerade.api.manipulators;

import eu.crushedpixel.sponge.masquerade.api.data.FlagEntityMetadata;
import eu.crushedpixel.sponge.masquerade.api.masquerades.Masquerade;
import eu.crushedpixel.sponge.masquerade.api.data.BasicEntityMetadata;
import eu.crushedpixel.sponge.masquerade.api.data.EntityMetadata;
import net.minecraft.entity.Entity;
import net.minecraft.network.datasync.EntityDataManager.DataEntry;

import java.util.ArrayList;
import java.util.List;

public class EntityDataManipulator<E extends Entity> implements DataManipulator {

    private static final int FLAG_ON_FIRE = 0;
    private static final int FLAG_SNEAKING = 1;
    private static final int FLAG_SPRINTING = 3;
    private static final int FLAG_INVISIBLE = 5;
    private static final int FLAG_GLOWING = 6;

    protected final DataEntry<Byte> flags = new DataEntry<>(Entity.FLAGS, (byte) 0);

    public final EntityMetadata<Byte, Boolean> onFire = new FlagEntityMetadata(this, flags, FLAG_ON_FIRE);
    public final EntityMetadata<Byte, Boolean> sneaking = new FlagEntityMetadata(this, flags, FLAG_SNEAKING);
    public final EntityMetadata<Byte, Boolean> sprinting = new FlagEntityMetadata(this, flags, FLAG_SPRINTING);
    public final EntityMetadata<Byte, Boolean> invisible = new FlagEntityMetadata(this, flags, FLAG_INVISIBLE);
    public final EntityMetadata<Byte, Boolean> glowing = new FlagEntityMetadata(this, flags, FLAG_GLOWING);
    public final EntityMetadata<Integer, Integer> air = new BasicEntityMetadata<>(this, Entity.AIR, 300);
    public final EntityMetadata<String, String> customName = new BasicEntityMetadata<>(this, Entity.CUSTOM_NAME, "");
    public final EntityMetadata<Boolean, Boolean> customNameVisible = new BasicEntityMetadata<>(this, Entity.CUSTOM_NAME_VISIBLE, false);
    public final EntityMetadata<Boolean, Boolean> silent = new BasicEntityMetadata<>(this, Entity.SILENT, false);
    public final EntityMetadata<Boolean, Boolean> noGravity = new BasicEntityMetadata<>(this, Entity.NO_GRAVITY, false);

    private final Masquerade<E, ? extends EntityDataManipulator<E>> masquerade;

    public EntityDataManipulator(Masquerade<E, ? extends EntityDataManipulator<E>> masquerade) {
        this.masquerade = masquerade;
    }

    @Override
    public List<EntityMetadata> getAllEntries() {
        List<EntityMetadata> entries = new ArrayList<>();

        entries.add(onFire);
        entries.add(sneaking);
        entries.add(sprinting);
        entries.add(invisible);
        entries.add(glowing);
        entries.add(air);
        entries.add(customName);
        entries.add(customNameVisible);
        entries.add(silent);
        entries.add(noGravity);

        return entries;
    }

    @Override
    public Masquerade<?, ?> getMasquerade() {
        return masquerade;
    }

}
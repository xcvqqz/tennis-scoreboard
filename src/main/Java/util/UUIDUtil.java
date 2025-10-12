package util;


import dto.MatchDTO;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class UUIDUtil {

    public static final Set<UUID> allUUIDs = new HashSet<>();

    public UUIDUtil() {}

    public UUID getNewUUID() {
        UUID uuid = UUID.randomUUID();
        allUUIDs.add(uuid);
        return uuid;
    }

    public UUID getUUID(){
        return allUUIDs.iterator().next();
    }

}

package util;


import dto.MatchDTO;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class UUIDUtil {

    public UUIDUtil() {}

    public UUID getNewUUID() {
        UUID uuid = UUID.randomUUID();
        return uuid;
    }

    public UUID parseUUID(String uuidString) {
        UUID uuid = UUID.fromString(uuidString);
        return uuid;
    }


}

package io.github.xcvqqz.tennis_scoreboard.util;

import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
public class UUIDUtil {

    public UUID getNewUUID() {
        UUID uuid = UUID.randomUUID();
        return uuid;
    }

    public UUID parseUUID(String uuidString) {
        UUID uuid = UUID.fromString(uuidString);
        return uuid;
    }
}
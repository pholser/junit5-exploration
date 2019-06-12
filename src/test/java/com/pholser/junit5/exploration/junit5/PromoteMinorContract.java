package com.pholser.junit5.exploration.junit5;

import com.pholser.junit5.exploration.VersionIndicator;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public interface PromoteMinorContract {
    VersionIndicator indicator();

    @Test default void promoteMinor() {
        VersionIndicator version = indicator();

        VersionIndicator promoted = version.promoteMinor();

        assertAll(
            () -> assertEquals(version.major(), promoted.major()),
            () -> assertEquals(version.minor() + 1, promoted.minor()),
            () -> assertEquals(Optional.empty(), promoted.patch()),
            () -> assertEquals(Optional.empty(), promoted.previewLevel()),
            () -> assertEquals(Optional.empty(), promoted.previewNumber())
        );
    }
}

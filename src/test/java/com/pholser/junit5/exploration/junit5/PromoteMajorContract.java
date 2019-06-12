package com.pholser.junit5.exploration.junit5;

import com.pholser.junit5.exploration.VersionIndicator;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public interface PromoteMajorContract {
    VersionIndicator indicator();

    @Test default void promoteMajor() {
        VersionIndicator version = indicator();

        VersionIndicator promoted = version.promoteMajor();

        assertAll(
            () -> assertEquals(version.major() + 1, promoted.major()),
            () -> assertEquals(0, promoted.minor()),
            () -> assertEquals(Optional.empty(), promoted.patch()),
            () -> assertEquals(Optional.empty(), promoted.previewLevel()),
            () -> assertEquals(Optional.empty(), promoted.previewNumber())
        );

    }
}

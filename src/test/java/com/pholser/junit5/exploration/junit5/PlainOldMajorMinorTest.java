package com.pholser.junit5.exploration.junit5;

import com.pholser.junit5.exploration.VersionIndicator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class PlainOldMajorMinorTest {
    private VersionIndicator version;

    @BeforeEach void init() {
        version = new VersionIndicator("40.23");
    }

    @Test void major() {
        assertEquals(40, version.major());
    }

    @Test void minor() {
        assertEquals(23, version.minor());
    }

    @Test void patch() {
        assertEquals(Optional.empty(), version.patch());
    }

    @Test void previewLevel() {
        assertEquals(Optional.empty(), version.previewLevel());
    }

    @Test void previewNumber() {
        assertEquals(Optional.empty(), version.previewNumber());
    }

    @Test void promotePreviewNumber() {
        IllegalStateException thrown =
            assertThrows(
                IllegalStateException.class,
                () -> version.promotePreviewNumber());

        assertEquals(
            "Version indicator 40.23 is not a preview version",
            thrown.getMessage());
    }

    @Test void promotePreview() {
        IllegalStateException thrown =
            assertThrows(
                IllegalStateException.class,
                () -> version.promotePreview());

        assertEquals(
            "Version indicator 40.23 is not a preview version",
            thrown.getMessage());
    }

    @Test void promotePatch() {
        IllegalStateException thrown =
            assertThrows(
                IllegalStateException.class,
                () -> version.promotePatch());

        assertEquals(
            "Version indicator 40.23 is not a patch version",
            thrown.getMessage());
    }

    @Test void promoteMinor() {
        VersionIndicator promoted = version.promoteMinor();

        assertAll(
            () -> assertEquals(version.major(), promoted.major()),
            () -> assertEquals(version.minor() + 1, promoted.minor()),
            () -> assertEquals(Optional.empty(), promoted.patch()),
            () -> assertEquals(Optional.empty(), promoted.previewLevel()),
            () -> assertEquals(Optional.empty(), promoted.previewNumber())
        );
    }

    @Test void promoteMajor() {
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

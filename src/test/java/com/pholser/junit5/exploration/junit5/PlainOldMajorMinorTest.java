package com.pholser.junit5.exploration.junit5;

import com.pholser.junit5.exploration.VersionIndicator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DisplayNameGeneration(DisplayNameGenerator.class)
class PlainOldMajorMinorTest
    implements PromoteMajorContract, PromoteMinorContract {

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

    @Override public VersionIndicator indicator() {
        return version;
    }
}

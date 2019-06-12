package com.pholser.junit5.exploration.junit5;

import com.pholser.junit5.exploration.VersionIndicator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MajorMinorPatchBetaTest
    implements PromoteMajorContract, PromoteMinorContract {

    private VersionIndicator version;

    @BeforeEach void init() {
        version = new VersionIndicator("1.2.3-beta-4");
    }

    @Test void major() {
        assertEquals(1, version.major());
    }

    @Test void minor() {
        assertEquals(2, version.minor());
    }

    @Test void patch() {
        assertEquals(Optional.of(3), version.patch());
    }

    @Test void previewLevel() {
        assertEquals(Optional.of("beta"), version.previewLevel());
    }

    @Test void previewNumber() {
        assertEquals(Optional.of(4), version.previewNumber());
    }

    @Test void promotePreviewNumber() {
        VersionIndicator promoted = version.promotePreviewNumber();

        assertAll(
            () -> assertEquals(version.major(), promoted.major()),
            () -> assertEquals(version.minor(), promoted.minor()),
            () -> assertEquals(version.patch(), promoted.patch()),
            () -> assertEquals(version.previewLevel(), promoted.previewLevel()),
            () -> assertEquals(
                version.previewNumber().map(n -> n + 1),
                promoted.previewNumber())
        );
    }

    @Test void promotePreview() {
        VersionIndicator promoted = version.promotePreview();

        assertAll(
            () -> assertEquals(version.major(), promoted.major()),
            () -> assertEquals(version.minor(), promoted.minor()),
            () -> assertEquals(
                version.patch().map(n -> n + 1),
                promoted.patch()),
            () -> assertEquals(Optional.empty(), promoted.previewLevel()),
            () -> assertEquals(Optional.empty(), promoted.previewNumber())
        );
    }

    @Test void promotePatch() {
        VersionIndicator promoted = version.promotePatch();

        assertAll(
            () -> assertEquals(version.major(), promoted.major()),
            () -> assertEquals(version.minor(), promoted.minor()),
            () -> assertEquals(
                version.patch().map(n -> n + 1),
                promoted.patch()),
            () -> assertEquals(Optional.empty(), promoted.previewLevel()),
            () -> assertEquals(Optional.empty(), promoted.previewNumber())
        );
    }

    @Override public VersionIndicator indicator() {
        return version;
    }
}

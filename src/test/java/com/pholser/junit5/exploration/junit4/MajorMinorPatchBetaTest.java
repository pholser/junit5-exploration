package com.pholser.junit5.exploration.junit4;

import com.pholser.junit5.exploration.VersionIndicator;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class MajorMinorPatchBetaTest {
    private VersionIndicator version;

    @Before public void init() {
        version = new VersionIndicator("1.2.3-beta-4");
    }

    @Test public void major() {
        assertEquals(1, version.major());
    }

    @Test public void minor() {
        assertEquals(2, version.minor());
    }

    @Test public void patch() {
        assertEquals(Optional.of(3), version.patch());
    }

    @Test public void previewLevel() {
        assertEquals(Optional.of("beta"), version.previewLevel());
    }

    @Test public void previewNumber() {
        assertEquals(Optional.of(4), version.previewNumber());
    }

    @Test public void promotePreviewNumber() {
        VersionIndicator promoted = version.promotePreviewNumber();

        assertEquals(version.major(), promoted.major());
        assertEquals(version.minor(), promoted.minor());
        assertEquals(version.patch(), promoted.patch());
        assertEquals(version.previewLevel(), promoted.previewLevel());
        assertEquals(
            version.previewNumber().map(n -> n + 1),
            promoted.previewNumber());
    }

    @Test public void promotePreview() {
        VersionIndicator promoted = version.promotePreview();

        assertEquals(version.major(), promoted.major());
        assertEquals(version.minor(), promoted.minor());
        assertEquals(
            version.patch().map(n -> n + 1),
            promoted.patch());
        assertEquals(Optional.empty(), promoted.previewLevel());
        assertEquals(Optional.empty(), promoted.previewNumber());
    }

    @Test public void promotePatch() {
        VersionIndicator promoted = version.promotePatch();

        assertEquals(version.major(), promoted.major());
        assertEquals(version.minor(), promoted.minor());
        assertEquals(
            version.patch().map(n -> n + 1),
            promoted.patch());
        assertEquals(Optional.empty(), promoted.previewLevel());
        assertEquals(Optional.empty(), promoted.previewNumber());
    }

    @Test public void promoteMinor() {
        VersionIndicator promoted = version.promoteMinor();

        assertEquals(version.major(), promoted.major());
        assertEquals(version.minor() + 1, promoted.minor());
        assertEquals(Optional.empty(), promoted.patch());
        assertEquals(Optional.empty(), promoted.previewLevel());
        assertEquals(Optional.empty(), promoted.previewNumber());
    }

    @Test public void promoteMajor() {
        VersionIndicator promoted = version.promoteMajor();

        assertEquals(version.major() + 1, promoted.major());
        assertEquals(0, promoted.minor());
        assertEquals(Optional.empty(), promoted.patch());
        assertEquals(Optional.empty(), promoted.previewLevel());
        assertEquals(Optional.empty(), promoted.previewNumber());
    }
}

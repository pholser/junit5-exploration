package com.pholser.junit5.exploration.junit3;

import junit.framework.TestCase;

import java.util.Optional;

public class MajorMinorPatchBetaTest extends TestCase {
    private VersionIndicator version;

    @Override public void setUp() {
        version = new VersionIndicator("1.2.3-beta-4");
    }

    public void testMajor() {
        assertEquals(1, version.major());
    }

    public void testMinor() {
        assertEquals(2, version.minor());
    }

    public void testPatch() {
        assertEquals(Optional.of(3), version.patch());
    }

    public void testPreviewLevel() {
        assertEquals(Optional.of("beta"), version.previewLevel());
    }

    public void testPreviewNumber() {
        assertEquals(Optional.of(4), version.previewNumber());
    }

    public void testPromotePreviewNumber() {
        VersionIndicator promoted = version.promotePreviewNumber();

        assertEquals(version.major(), promoted.major());
        assertEquals(version.minor(), promoted.minor());
        assertEquals(version.patch(), promoted.patch());
        assertEquals(version.previewLevel(), promoted.previewLevel());
        assertEquals(
            version.previewNumber().map(n -> n + 1),
            promoted.previewNumber());
    }

    public void testPromotePreview() {
        VersionIndicator promoted = version.promotePreview();

        assertEquals(version.major(), promoted.major());
        assertEquals(version.minor(), promoted.minor());
        assertEquals(
            version.patch().map(n -> n + 1),
            promoted.patch());
        assertEquals(Optional.empty(), promoted.previewLevel());
        assertEquals(Optional.empty(), promoted.previewNumber());
    }

    public void testPromotePatch() {
        VersionIndicator promoted = version.promotePatch();

        assertEquals(version.major(), promoted.major());
        assertEquals(version.minor(), promoted.minor());
        assertEquals(
            version.patch().map(n -> n + 1),
            promoted.patch());
        assertEquals(Optional.empty(), promoted.previewLevel());
        assertEquals(Optional.empty(), promoted.previewNumber());
    }

    public void testPromoteMinor() {
        VersionIndicator promoted = version.promoteMinor();

        assertEquals(version.major(), promoted.major());
        assertEquals(version.minor() + 1, promoted.minor());
        assertEquals(Optional.empty(), promoted.patch());
        assertEquals(Optional.empty(), promoted.previewLevel());
        assertEquals(Optional.empty(), promoted.previewNumber());
    }

    public void testPromoteMajor() {
        VersionIndicator promoted = version.promoteMajor();

        assertEquals(version.major() + 1, promoted.major());
        assertEquals(0, promoted.minor());
        assertEquals(Optional.empty(), promoted.patch());
        assertEquals(Optional.empty(), promoted.previewLevel());
        assertEquals(Optional.empty(), promoted.previewNumber());
    }
}

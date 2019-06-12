package com.pholser.junit5.exploration.junit3;

import junit.framework.TestCase;

import java.util.Optional;

public class MajorMinorAlphaTest extends TestCase {
    private VersionIndicator version;

    @Override public void setUp() {
        version = new VersionIndicator("3.55-alpha-2");
    }

    public void testMajor() {
        assertEquals(3, version.major());
    }

    public void testMinor() {
        assertEquals(55, version.minor());
    }

    public void testPatch() {
        assertEquals(Optional.empty(), version.patch());
    }

    public void testPreviewLevel() {
        assertEquals(Optional.of("alpha"), version.previewLevel());
    }

    public void testPreviewNumber() {
        assertEquals(Optional.of(2), version.previewNumber());
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
        assertEquals(Optional.empty(), promoted.patch());
        assertEquals(Optional.of("beta"), promoted.previewLevel());
        assertEquals(Optional.of(1), promoted.previewNumber());
    }

    public void testPromotePatch() {
        try {
            version.promotePatch();
            fail();
        } catch (IllegalStateException expected) {
            assertEquals(
                "Version indicator 3.55-alpha-2 is not a patch version",
                expected.getMessage()
            );
        }
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

package com.pholser.junit5.exploration.junit3;

import com.pholser.junit5.exploration.VersionIndicator;
import junit.framework.TestCase;

import java.util.Optional;

public class PlainOldMajorMinorTest extends TestCase {
    private VersionIndicator version;

    @Override public void setUp() {
        version = new VersionIndicator("40.23");
    }

    public void testMajor() {
        assertEquals(40, version.major());
    }

    public void testMinor() {
        assertEquals(23, version.minor());
    }

    public void testPatch() {
        assertEquals(Optional.empty(), version.patch());
    }

    public void testPreviewLevel() {
        assertEquals(Optional.empty(), version.previewLevel());
    }

    public void testPreviewNumber() {
        assertEquals(Optional.empty(), version.previewNumber());
    }

    public void testPromotePreviewNumber() {
        try {
            version.promotePreviewNumber();
            fail();
        } catch (IllegalStateException expected) {
            assertEquals(
                "Version indicator 40.23 is not a preview version",
                expected.getMessage()
            );
        }
    }

    public void testPromotePreview() {
        try {
            version.promotePreview();
            fail();
        } catch (IllegalStateException expected) {
            assertEquals(
                "Version indicator 40.23 is not a preview version",
                expected.getMessage()
            );
        }
    }

    public void testPromotePatch() {
        try {
            version.promotePatch();
            fail();
        } catch (IllegalStateException expected) {
            assertEquals(
                "Version indicator 40.23 is not a patch version",
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

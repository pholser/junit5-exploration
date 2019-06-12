package com.pholser.junit5.exploration.junit3;

import junit.framework.TestCase;

public class IllFormattedVersionsTest extends TestCase {
    public void testUtterGarbage() {
        try {
            new VersionIndicator("ASJDKASJD(!@*UEP(!@*UEOIASC:KN");
            fail();
        } catch (IllegalArgumentException expected) {
        }
    }

    public void testNonNumericMajor() {
        try {
            new VersionIndicator("A.1");
            fail();
        } catch (IllegalArgumentException expected) {
        }
    }

    public void testNonNumericMinor() {
        try {
            new VersionIndicator("1.X");
            fail();
        } catch (IllegalArgumentException expected) {
        }
    }

    public void testNonNumericPatch() {
        try {
            new VersionIndicator("1.2.Y");
            fail();
        } catch (IllegalArgumentException expected) {
        }
    }

    public void testUnrecognizedPreviewLevel() {
        try {
            new VersionIndicator("1.2.3-epsilon-4");
            fail();
        } catch (IllegalArgumentException expected) {
        }
    }

    public void testNonNumericPreviewNumber() {
        try {
            new VersionIndicator("1.2.3-beta-*");
            fail();
        } catch (IllegalArgumentException expected) {
        }
    }

    public void testPreviewIndicatorWithMissingPreviewNumber() {
        try {
            new VersionIndicator("1.2.3-beta");
            fail();
        } catch (IllegalArgumentException expected) {
        }
    }
}

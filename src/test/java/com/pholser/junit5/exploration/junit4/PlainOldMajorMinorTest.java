package com.pholser.junit5.exploration.junit4;

import com.pholser.junit5.exploration.VersionIndicator;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.rules.ExpectedException.none;

public class PlainOldMajorMinorTest {
    @Rule public final ExpectedException thrown = none();

    private VersionIndicator version;

    @Before public void init() {
        version = new VersionIndicator("40.23");
    }

    @Test public void major() {
        assertEquals(40, version.major());
    }

    @Test public void minor() {
        assertEquals(23, version.minor());
    }

    @Test public void patch() {
        assertEquals(Optional.empty(), version.patch());
    }

    @Test public void previewLevel() {
        assertEquals(Optional.empty(), version.previewLevel());
    }

    @Test public void previewNumber() {
        assertEquals(Optional.empty(), version.previewNumber());
    }

    @Test public void promotePreviewNumber() {
        thrown.expect(IllegalStateException.class);
        thrown.expectMessage(
            "Version indicator 40.23 is not a preview version");

        version.promotePreviewNumber();
    }

    @Test public void promotePreview() {
        thrown.expect(IllegalStateException.class);
        thrown.expectMessage(
            "Version indicator 40.23 is not a preview version");

        version.promotePreview();
    }

    @Test public void promotePatch() {
        thrown.expect(IllegalStateException.class);
        thrown.expectMessage(
            "Version indicator 40.23 is not a patch version");

        version.promotePatch();
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

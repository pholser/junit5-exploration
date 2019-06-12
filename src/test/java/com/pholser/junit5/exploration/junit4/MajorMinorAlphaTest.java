package com.pholser.junit5.exploration.junit4;

import com.pholser.junit5.exploration.VersionIndicator;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.rules.ExpectedException.none;

public class MajorMinorAlphaTest {
    @Rule public final ExpectedException thrown = none();

    private VersionIndicator version;

    @Before public void init() {
        version = new VersionIndicator("3.55-alpha-2");
    }

    @Test public void major() {
        assertEquals(3, version.major());
    }

    @Test public void minor() {
        assertEquals(55, version.minor());
    }

    @Test public void patch() {
        assertEquals(Optional.empty(), version.patch());
    }

    @Test public void previewLevel() {
        assertEquals(Optional.of("alpha"), version.previewLevel());
    }

    @Test public void previewNumber() {
        assertEquals(Optional.of(2), version.previewNumber());
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
        assertEquals(Optional.empty(), promoted.patch());
        assertEquals(Optional.of("beta"), promoted.previewLevel());
        assertEquals(Optional.of(1), promoted.previewNumber());
    }

    @Test public void promotePatch() {
        thrown.expect(IllegalStateException.class);
        thrown.expectMessage(
            "Version indicator 3.55-alpha-2 is not a patch version");

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

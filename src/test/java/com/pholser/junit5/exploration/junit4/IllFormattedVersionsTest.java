package com.pholser.junit5.exploration.junit4;

import com.pholser.junit5.exploration.VersionIndicator;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.rules.ExpectedException.none;

public class IllFormattedVersionsTest {
    @Rule public final ExpectedException thrown = none();

    @Test public void utterGarbage() {
        thrown.expect(IllegalArgumentException.class);

        new VersionIndicator("ASJDKASJD(!@*UEP(!@*UEOIASC:KN");
    }

    @Test public void nonNumericMajor() {
        thrown.expect(IllegalArgumentException.class);

        new VersionIndicator("A.1");
    }

    @Test public void nonNumericMinor() {
        thrown.expect(IllegalArgumentException.class);

        new VersionIndicator("1.X");
    }

    @Test public void nonNumericPatch() {
        thrown.expect(IllegalArgumentException.class);

        new VersionIndicator("1.2.Y");
    }

    @Test public void unrecognizedPreviewLevel() {
        thrown.expect(IllegalArgumentException.class);

        new VersionIndicator("1.2.3-epsilon-4");
    }

    @Test public void nonNumericPreviewNumber() {
        thrown.expect(IllegalArgumentException.class);

        new VersionIndicator("1.2.3-beta-*");
    }

    @Test public void previewIndicatorWithMissingPreviewNumber() {
        thrown.expect(IllegalArgumentException.class);

        new VersionIndicator("1.2.3-beta");
    }
}

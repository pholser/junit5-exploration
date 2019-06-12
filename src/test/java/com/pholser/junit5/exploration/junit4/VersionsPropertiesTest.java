package com.pholser.junit5.exploration.junit4;

import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import com.pholser.junit5.exploration.VersionIndicator;
import org.junit.runner.RunWith;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(JUnitQuickcheck.class)
public class VersionsPropertiesTest {
    @Property public void promoteMinor(VersionIndicator v) {
        VersionIndicator promoted = v.promoteMinor();

        assertEquals(v.major(), promoted.major());
        assertEquals(v.minor() + 1, promoted.minor());
        assertEquals(Optional.empty(), promoted.patch());
        assertEquals(Optional.empty(), promoted.previewLevel());
        assertEquals(Optional.empty(), promoted.previewNumber());
    }

    @Property public void promoteMajor(VersionIndicator v) {
        VersionIndicator promoted = v.promoteMajor();

        assertEquals(v.major() + 1, promoted.major());
        assertEquals(0, promoted.minor());
        assertEquals(Optional.empty(), promoted.patch());
        assertEquals(Optional.empty(), promoted.previewLevel());
        assertEquals(Optional.empty(), promoted.previewNumber());
    }
}

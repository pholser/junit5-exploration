package com.pholser.junit5.exploration.junit5;

import com.pholser.junit5.exploration.VersionIndicator;
import net.jqwik.api.*;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

class VersionsPropertiesTest {
    @Property void promoteMinor(@ForAll("legit") VersionIndicator v) {
        VersionIndicator promoted = v.promoteMinor();

        assertEquals(v.major(), promoted.major());
        assertEquals(v.minor() + 1, promoted.minor());
        assertEquals(Optional.empty(), promoted.patch());
        assertEquals(Optional.empty(), promoted.previewLevel());
        assertEquals(Optional.empty(), promoted.previewNumber());
    }

    @Property void promoteMajor(@ForAll("legit") VersionIndicator v) {
        VersionIndicator promoted = v.promoteMajor();

        assertEquals(v.major() + 1, promoted.major());
        assertEquals(0, promoted.minor());
        assertEquals(Optional.empty(), promoted.patch());
        assertEquals(Optional.empty(), promoted.previewLevel());
        assertEquals(Optional.empty(), promoted.previewNumber());
    }

    @Provide
    Arbitrary<VersionIndicator> legit() {
        Arbitrary<Integer> majors = Arbitraries.integers().between(0, 50);
        Arbitrary<Integer> minors = Arbitraries.integers().between(0, 50);
        Arbitrary<Integer> patches = Arbitraries.integers().between(0, 50);
        Arbitrary<String> previews = Arbitraries.of("alpha", "beta");
        Arbitrary<Integer> previewNumbers = Arbitraries.integers().between(0, 50);

        return Combinators.combine(
            majors, minors, patches, previews, previewNumbers)
                .as(VersionIndicator::new);
    }
}

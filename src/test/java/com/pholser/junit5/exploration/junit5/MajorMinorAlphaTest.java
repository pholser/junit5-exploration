package com.pholser.junit5.exploration.junit5;

import com.pholser.junit5.exploration.VersionIndicator;
import org.junit.Ignore;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.EnabledOnOs;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.condition.OS.MAC;

@DisplayNameGeneration(JavaCaseToSpacedWords.class)
class MajorMinorAlphaTest
    implements PromoteMajorContract, PromoteMinorContract {

    private VersionIndicator version;

    @BeforeEach void init() {
        version = new VersionIndicator("3.55-alpha-2");
    }

    @Test void major() {
        assertEquals(3, version.major());
    }

    @Test void minor() {
        assertEquals(55, version.minor());
    }

    @Test void patch() {
        assertEquals(Optional.empty(), version.patch());
    }

    @Test void previewLevel() {
        assertEquals(Optional.of("alpha"), version.previewLevel());
    }

    @Test void previewNumber() {
        assertEquals(Optional.of(2), version.previewNumber());
    }

    @Test void promotePreviewNumber() {
        VersionIndicator promoted = version.promotePreviewNumber();

        assertAll(
            () -> assertEquals(version.major(), promoted.major()),
            () -> assertEquals(version.minor(), promoted.minor()),
            () -> assertEquals(version.patch(), promoted.patch()),
            () -> assertEquals(version.previewLevel(), promoted.previewLevel()),
            () -> assertEquals(
                version.previewNumber().map(n -> n + 1),
                promoted.previewNumber())
        );
    }

    @Test void promotePreview() {
        VersionIndicator promoted = version.promotePreview();

        assertAll(
            () -> assertEquals(version.major(), promoted.major()),
            () -> assertEquals(version.minor(), promoted.minor()),
            () -> assertEquals(Optional.empty(), promoted.patch()),
            () -> assertEquals(Optional.of("beta"), promoted.previewLevel()),
            () -> assertEquals(Optional.of(1), promoted.previewNumber())
        );
    }

    @Test void promotePatch() {
        IllegalStateException thrown =
            assertThrows(
                IllegalStateException.class,
                () -> version.promotePatch());

        assertEquals(
            "Version indicator 3.55-alpha-2 is not a patch version",
            thrown.getMessage());
    }

    @Disabled("Not sure")
    @Test
    void someTest() {
        assertDoesNotThrow(() -> 2 / 0);
    }

    @Test void assumption() {
        assumeTrue(version.major() > 3);

        fail("assumption was not violated?!");
    }

    @EnabledOnOs(MAC)
    @Test void onlyOnMac() {
        assertTrue(true);
    }

    @Override public VersionIndicator indicator() {
        return version;
    }
}

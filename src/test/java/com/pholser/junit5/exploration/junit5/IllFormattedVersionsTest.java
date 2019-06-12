package com.pholser.junit5.exploration.junit5;

import com.pholser.junit5.exploration.VersionIndicator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertThrows;

class IllFormattedVersionsTest {
    static Stream<String> whackedOutVersions() {
        return Stream.of(
            "ASJDKASJD(!@*UEP(!@*UEOIASC:KN",
            "A.1",
            "1.X",
            "1.2.Y",
            "1.2.3-epsilon-4",
            "1.2.3-beta-*",
            "1.2.3-beta"
        );
    }

    @ParameterizedTest
    @MethodSource("whackedOutVersions")
    void crazyVersionIndicators(String raw) {
        assertThrows(
            IllegalArgumentException.class,
            () -> new VersionIndicator(raw));
    }
}

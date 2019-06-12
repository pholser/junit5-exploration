package com.pholser.junit5.exploration.junit5;

import com.pholser.junit5.exploration.VersionIndicator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Tests for ill-formatted version indicators")
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

    // A kind of Test Template
    @ParameterizedTest
    @MethodSource("whackedOutVersions")
    void crazyVersionIndicators(String raw) {
        assertThrows(
            IllegalArgumentException.class,
            () -> new VersionIndicator(raw));
    }
}

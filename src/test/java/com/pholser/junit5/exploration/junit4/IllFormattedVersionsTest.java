package com.pholser.junit5.exploration.junit4;

import com.pholser.junit5.exploration.VersionIndicator;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;

import static org.junit.rules.ExpectedException.none;

@RunWith(Parameterized.class)
public class IllFormattedVersionsTest {
    @Parameters
    public static Iterable<Object[]> data() {
        return Arrays.asList(
            new Object[][] {
                { "ASJDKASJD(!@*UEP(!@*UEOIASC:KN", "utter garbage" },
                { "A.1", "non-numeric major" },
                { "1.X", "non-numeric minor" },
                { "1.2.Y", "non-numeric patch" },
                { "1.2.3-epsilon-4", "unrecognized preview level" },
                { "1.2.3-beta-*", "non-numeric preview number" },
                { "1.2.3-beta", "preview indicator with missing preview number" }
            }
        );
    }

    @Rule public final ExpectedException thrown = none();

    private String input;
    private String reason;

    public IllFormattedVersionsTest(String input, String reason) {
        this.input = input;
        this.reason = reason;
    }

    @Test public void tryAFailingCase() {
        thrown.expect(IllegalArgumentException.class);

        new VersionIndicator(input);
    }
}

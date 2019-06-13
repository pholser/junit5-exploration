package com.pholser.junit5.exploration;

import java.util.Optional;
import java.util.regex.Pattern;

public class VersionIndicator {
    private static final Pattern PATTERN =
        Pattern.compile(
            "(\\d+)\\.(\\d+)(?:(?:\\.(\\d+))?-(?:(alpha|beta)-(\\d+)))?");

    private final int major;
    private final int minor;
    private final Integer patch;
    private final String previewLevel;
    private final Integer previewNumber;

    public VersionIndicator(String raw) {
        var matcher = PATTERN.matcher(raw);
        if (!matcher.matches()) {
            throw new IllegalArgumentException(
                "Version indicator [" + raw + "] ill-formatted");
        }

        this.major = Integer.parseInt(matcher.group(1));
        this.minor = Integer.parseInt(matcher.group(2));
        this.patch =
            matcher.group(3) == null
                ? null
                : Integer.parseInt(matcher.group(3));
        this.previewLevel = matcher.group(4);
        this.previewNumber =
            matcher.group(5) == null
                ? null
                : Integer.parseInt(matcher.group(5));
    }

    public VersionIndicator(
        int major,
        int minor,
        Integer patch,
        String previewLevel,
        Integer previewNumber) {

        this.major = major;
        this.minor = minor;
        this.patch = patch;
        this.previewLevel = previewLevel;
        this.previewNumber = previewNumber;
    }

    public int major() {
        return major;
    }

    public int minor() {
        return minor;
    }

    public Optional<Integer> patch() {
        return Optional.ofNullable(patch);
    }

    public Optional<String> previewLevel() {
        return Optional.ofNullable(previewLevel);
    }

    public Optional<Integer> previewNumber() {
        return Optional.ofNullable(previewNumber);
    }

    public VersionIndicator promotePreviewNumber() {
        if (previewNumber == null) {
            throw new IllegalStateException(
                "Version indicator " + this + " is not a preview version");
        }

        return new VersionIndicator(
            major,
            minor,
            patch,
            previewLevel,
            previewNumber + 1);
    }

    public VersionIndicator promotePreview() {
        if (previewLevel == null) {
            throw new IllegalStateException(
                "Version indicator " + this + " is not a preview version");
        }

        if ("alpha".equals(previewLevel)) {
            return new VersionIndicator(
                major,
                minor,
                patch,
                "beta",
                1);
        }

        if (patch == null) {
            return new VersionIndicator(
                major,
                minor + 1,
                patch,
                null,
                null);
        }

        return new VersionIndicator(
            major,
            minor,
            patch + 1,
            null,
            null);
    }

    public VersionIndicator promotePatch() {
        if (patch == null) {
            throw new IllegalStateException(
                "Version indicator " + this + " is not a patch version");
        }

        return new VersionIndicator(
            major,
            minor,
            patch + 1,
            null,
            null);
    }

    public VersionIndicator promoteMinor() {
        return new VersionIndicator(
            major,
            minor + 1,
            null,
            null,
            null);
    }

    public VersionIndicator promoteMajor() {
        return new VersionIndicator(
            major + 1,
            0,
            null,
            null,
            null);
    }

    @Override public String toString() {
        var buffer = new StringBuilder();

        buffer.append(major).append(".").append(minor);

        if (patch != null) {
            buffer.append(".").append(patch);
        }
        if (previewNumber != null) {
            buffer.append("-").append(previewLevel)
                .append("-").append(previewNumber);
        }

        return buffer.toString();
    }
}

package com.pholser.junit5.exploration.junit4;

import com.pholser.junit.quickcheck.generator.GenerationStatus;
import com.pholser.junit.quickcheck.generator.Generator;
import com.pholser.junit.quickcheck.random.SourceOfRandomness;
import com.pholser.junit5.exploration.VersionIndicator;

import static java.util.Arrays.asList;

public class Versions extends Generator<VersionIndicator> {
    public Versions() {
        super(VersionIndicator.class);
    }

    @Override public VersionIndicator generate(
        SourceOfRandomness random,
        GenerationStatus status) {

        boolean addPatch = random.nextBoolean();
        boolean addPreview = random.nextBoolean();

        return new VersionIndicator(
            random.nextInt(0, 50),
            random.nextInt(0, 50),
            addPatch ? random.nextInt(0, 50) : null,
            addPreview ? random.choose(asList("alpha", "beta")) : null,
            addPreview ? random.nextInt(0, 50) : null
        );
    }
}

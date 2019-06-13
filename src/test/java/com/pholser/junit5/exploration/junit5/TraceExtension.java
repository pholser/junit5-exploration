package com.pholser.junit5.exploration.junit5;

import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class TraceExtension
    implements BeforeEachCallback, AfterEachCallback {

    @Override public void beforeEach(ExtensionContext context) {
        System.out.println(context.getDisplayName() + " cranking up");
    }

    @Override public void afterEach(ExtensionContext context) {
        System.out.println(context.getDisplayName() + " winding down");
    }
}

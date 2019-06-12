package com.pholser.junit5.exploration.junit5;

import org.junit.jupiter.api.DisplayNameGenerator;

import java.lang.reflect.Method;
import java.util.regex.Pattern;

public class JavaCaseToSpacedWords extends DisplayNameGenerator.Standard {
    private static final Pattern PATTERN = Pattern.compile("(\\B[A-Z])");

    @Override public String generateDisplayNameForClass(Class<?> testClass) {
        return PATTERN.matcher(super.generateDisplayNameForClass(testClass))
            .replaceAll(" $1")
            .toLowerCase();
    }

    @Override public String generateDisplayNameForNestedClass(
        Class<?> nestedClass) {

        return PATTERN.matcher(
            super.generateDisplayNameForNestedClass(nestedClass))
            .replaceAll(" $1")
            .toLowerCase();
    }

    @Override public String generateDisplayNameForMethod(
        Class<?> testClass,
        Method testMethod) {

        return PATTERN.matcher(
            super.generateDisplayNameForMethod(testClass, testMethod))
            .replaceAll(" $1")
            .replaceAll("\\(\\)","")
            .toLowerCase();
    }
}

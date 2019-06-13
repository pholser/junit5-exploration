package com.pholser.junit5.exploration.junit5;

import com.pholser.junit5.exploration.ThingDoer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.logging.Logger;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;

@ExtendWith({
    MockitoExtension.class,
    TraceExtension.class
})
class MockingTest {
    @Mock private Logger log;
    private ThingDoer doer;

    @BeforeEach void init() {
        doer = new ThingDoer(log);
    }

    @Test void legitThingDone() {
        assertEquals(0, doer.doSomething(4));

        verify(log).entering(
            ThingDoer.class.getCanonicalName(),
            "doSomething");
        verify(log).exiting(
            ThingDoer.class.getCanonicalName(),
            "doSomething");
    }

    @Test void uncoolThingDone() {
        assertThrows(
            ArithmeticException.class,
            () -> doer.doSomething(0));

        verify(log).entering(
            ThingDoer.class.getCanonicalName(),
            "doSomething");
        verify(log).severe("Dude, did you divide by zero?");
        verify(log).exiting(
            ThingDoer.class.getCanonicalName(),
            "doSomething");
    }
}

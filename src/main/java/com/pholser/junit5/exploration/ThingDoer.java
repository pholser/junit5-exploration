package com.pholser.junit5.exploration;

import java.util.logging.Logger;

public class ThingDoer {
    private final Logger log;

    public ThingDoer(Logger log) {
        this.log = log;
    }

    public int doSomething(int x) {
        log.entering(getClass().getCanonicalName(), "doSomething");

        try {
            return 2 / x;
        } catch (ArithmeticException e) {
            log.severe("Dude, did you divide by zero?");
            throw e;
        } finally {
            log.exiting(getClass().getCanonicalName(), "doSomething");
        }
    }
}

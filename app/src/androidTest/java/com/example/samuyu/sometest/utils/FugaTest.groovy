package com.example.samuyu.sometest.utils

import pl.polidea.robospock.RoboSpecification
import spock.lang.Specification

/**
 * Created by toyamaosamuyu on 2014/11/27.
 */
//class FugaTest extends Specification {
class FugaTest extends RoboSpecification {
def "Add"() {

        setup:
        def fuga = new Fuga()

        expect:
        fuga.add(a, b) == result


        where:
        a   | b   || result
        0   | 2   || 2
    }
}

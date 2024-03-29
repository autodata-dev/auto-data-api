package com.autodata.api.integers

import com.autodata.api.integers.IntegerColumn
import com.autodata.api.integers.IntegerRange
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.datatest.withData
import io.kotest.matchers.ints.shouldBeGreaterThanOrEqual
import io.kotest.matchers.ints.shouldBeInRange
import io.kotest.matchers.ints.shouldBeLessThanOrEqual

class IntegerColumnTests :
    DescribeSpec({
        describe("When generating a value") {
            describe("Meets the minimum") {
                withData(
                    IntegerRange(Int.MIN_VALUE),
                    IntegerRange(-1000),
                    IntegerRange(-1),
                    IntegerRange(0),
                    IntegerRange(1),
                    IntegerRange(1000),
                    IntegerRange(Int.MAX_VALUE)
                ) { range ->
                    val value = IntegerColumn(range).generate()

                    value shouldBeGreaterThanOrEqual range.min
                }
            }

            describe("Meets the maximum") {
                withData(
                    IntegerRange(max = Int.MIN_VALUE),
                    IntegerRange(max = -1000),
                    IntegerRange(max = -1),
                    IntegerRange(max = 0),
                    IntegerRange(max = -1),
                    IntegerRange(max = 1000),
                    IntegerRange(max = Int.MAX_VALUE)
                ) { range ->
                    val value = IntegerColumn(range).generate()

                    value shouldBeLessThanOrEqual range.max
                }
            }

            describe("Meets the range") {
                withData(
                    IntegerRange(0, 1),
                    IntegerRange(-1, 0),
                    IntegerRange(0, 0),
                    IntegerRange(-1000, 1000),
                    IntegerRange(Int.MIN_VALUE, Int.MAX_VALUE),
                    IntegerRange(Int.MIN_VALUE, Int.MIN_VALUE),
                    IntegerRange(Int.MAX_VALUE, Int.MAX_VALUE)
                ) { range ->
                    val value = IntegerColumn(range).generate()

                    value shouldBeInRange range.min..range.max
                }
            }
        }
    })
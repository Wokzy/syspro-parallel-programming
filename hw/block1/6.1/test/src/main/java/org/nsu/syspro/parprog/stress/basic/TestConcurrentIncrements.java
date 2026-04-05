package org.nsu.syspro.parprog.stress.basic;

import org.openjdk.jcstress.annotations.*;
import org.openjdk.jcstress.infra.results.*;

import static org.openjdk.jcstress.annotations.Expect.ACCEPTABLE;
import static org.openjdk.jcstress.annotations.Expect.ACCEPTABLE_INTERESTING;

/*
Build and run `TestConcurrentIncrements` tests on your local computer. 
- Do you observe `(1, 1)` in your environment?
    - Yes, I do.

- Is it OK if `(1, 1)` result would not empirically appear on some device?
    - Yes. It depends on how `add` instruction is implemented on partictular architecture.
    RISC-V for instance provide atomic add, and I believe in case it is used by compiler there will be no (1, 1).

Create subclass inside `TestConcurrentIncrements`....
- Do you observe `1` in your environment? Should you?
    - Yes, I do. This is happening because of data-race.

- Do you observe `5` in your environment? Should you?
    - Yes, I do. All increments luckily happened sequentially.

- Do you observe `0` in your environment? Should you?
    - No, I do not.
    In example you provided https://github.com/openjdk/jcstress/blob/master/jcstress-samples/src/main/java/org/openjdk/jcstress/samples/api/API_02_Arbiters.java
    The following statement is written:
        "Arbiters run after both actors, and therefore can observe the final result."
    According to this statement all "increments" have happend before results are read, hence we must see at least "1" in the results.
*/

@JCStressTest
@Outcome(id = "1", expect = ACCEPTABLE_INTERESTING)
@Outcome(id = "2", expect = ACCEPTABLE_INTERESTING)
@Outcome(id = "3", expect = ACCEPTABLE_INTERESTING)
@Outcome(id = "4", expect = ACCEPTABLE_INTERESTING)
@Outcome(id = "5", expect = ACCEPTABLE_INTERESTING)
@State
public class TestConcurrentIncrements {
    int v;

    @Actor
    public void actor1() {
        v++;
    }

    @Actor
    public void actor2() {
        v++;
    }

    @Actor
    public void actor3() {
        v++;
    }

    @Actor
    public void actor4() {
        v++;
    }

    @Actor
    public void actor5() {
        v++;
    }

    @Arbiter
    public void arbiter(I_Result r) {
        r.r1 = v;
    }
}

// @JCStressTest
// // @Outcome(id = "1, 1", expect = ACCEPTABLE_INTERESTING, desc = "Data race")
// @Outcome(id = "1, 2", expect = ACCEPTABLE, desc = "actor1 -> actor2.")
// @Outcome(id = "2, 1", expect = ACCEPTABLE, desc = "actor2 -> actor1.")
// @State
// public class TestConcurrentIncrements {
//     int v;
//     @Actor public void actor1(II_Result r) {
//         r.r1 = ++v;
//     }
//     @Actor public void actor2(II_Result r) {
//         r.r2 = ++v;
//     }
// }

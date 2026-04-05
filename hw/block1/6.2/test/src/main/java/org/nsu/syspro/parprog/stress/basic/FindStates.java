package org.nsu.syspro.parprog.stress.basic;

import org.openjdk.jcstress.annotations.*;
import org.openjdk.jcstress.infra.results.*;

/*
Все 4 исхода мною пронаблюдались, каких-либо неожиданных результатов не было. Прикрепляю ниже доказательство 1.3, в котором рассмотрено эти 4 единственно возможных исхода в терминах interleaving модели

### Рассмотрим 2 случая
#### `c_y != 2`
В этом случае какой бы порядок исполнения ни был, в результате окажется, что `x=1 && z=1`, поскольку B - единственный поток, который эти переменные изменяет пользуясь значениями в них же самих, т.е он не зависит от потока A и потока C в рассматриваемом случае. Остаётся 2 варианта для y = 0,1,2.

- `(x=1, y=0, z=1)` - `A.*->B.*->C.*`
- `(x=1, y=1, z=1)` - `C.*->B.1->B.2->A.*->B.3->B.4`
- `(x=1, y=2, z=1)` - `C.*->B.*->A.*`

#### `c_y == 2`
- `(x=0, y=2, z=1)` - (например) `B.*->A.*->C.*`. Это будет единственным сценарием с `c_y == 2`, потому что для его выполнения требуется, чтобы в какой-то момент времени в y было значение 2. Единственный тред, который изменяет y - это A. Чтобы в y попало значение 2 нужно, чтобы x, z в какой-то момент времени были 1-ми. Их изменяют треды B и C, но исполнение C требует выполнения условия `c_y == 2` для изменения x, а мы его и хотим добиться, поэтому остаётся тред B. Для того, чтобы x стало 1 надо исполнить B.2, для z = 1 надо исполнить B.4 Соотв A.3 нужно дождаться исполнения всех команд в B => С.1 нужно дождаться A и B, дальше исполнение детерменированно.
*/

@JCStressTest
@Outcome(id = "1, 0, 1", expect = Expect.ACCEPTABLE_INTERESTING)
@Outcome(id = "1, 1, 1", expect = Expect.ACCEPTABLE_INTERESTING)
@Outcome(id = "1, 2, 1", expect = Expect.ACCEPTABLE_INTERESTING)
@Outcome(id = "0, 2, 1", expect = Expect.ACCEPTABLE_INTERESTING)
@State
public class FindStates {
    int x, y, z;

    @Actor
    public void a() {
        int a_x = x;   // A.1
        int a_z = z;   // A.2
        y = a_x + a_z; // A.3
    }

    @Actor
    public void b() {
        int b_x = x; // B.1
        x = b_x + 1; // B.2
        int b_z = z; // B.3
        z = b_z + 1; // B.4
    }

    @Actor
    public void c() {
        int c_y = y;    // C.1
        if (c_y == 2) { // C.2
            int c_x = x;  // C.3
            x = c_x - 1;  // C.4
        }
    }

    @Arbiter
    public void main(III_Result r) {
        r.r1 = x;
        r.r2 = y;
        r.r3 = z;
    }
}

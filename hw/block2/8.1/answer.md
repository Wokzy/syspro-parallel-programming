
### Is it wait-free?
- Из лекций: Wait-free method: every call finishes in a finite number of steps.
- `AFx`, `BFx` - события, означающие, что поток 0 или 1 (A или B соотв) исполнил инструкцию `Fx`
- Будем считать, что для любого `x` и `y` события `AFx` и `BFy` непересекающиеся

- Пример возможной счётной последовательности событий:
  `AF1 -> AF2 -> AF3 -> BF1 -> BF2 -> BF3 -> (AF4 -> BF4 -> AF5 -> BF5 -> AF8 -> AF9 -> BF8 -> BF9) ...` (повторяется счётное число раз, но не конечное). Метод не wait-free.

### Is it lock-free?
- Из лекций: Lock-free method: infinitely often some method call finishes in a finite number of steps. Если понимать это определение как: бесконечно часто какой-нибудь из вызовов завершает свою работу за конечное число шагов, при этом если бесконечно часто не значит всегда, то доказательство видится очевидным - достаточно привести пример последовательности событий (шагов), где хотя бы 1 поток завершает работу. Если вызовов бесконечно много, то таких "удачных" вызовов тоже будет бесконечно много.
- Пример: `AF1 -> BF1 -> AF2 -> AF3 -> AF4 -> AF5 -> AF6 -> AF7 -> BF...`

### Is it obstruction-free?
- Из лекций: Obstruction-free method: if, from any point after which it executes in isolation, it finishes in a finite number of steps. Требуется доказать, что если в любой момент исполнения функции 1 поток будет "заморожен" или был запущен всего 1 поток, то метод завершит свою работу за конечное число шагов.
- Док-во:
  1. Случай с единственным запущенным потоков тривиален.
  2. Доказывать будем методом чайника, поскольку потоки инвариантны относительно своего номера, то обозначим эти номера `i` и `j` и не теряя общности будем рассматривать случаи, когда поток `j` "замораживается":
    1. Пускай поток j был заморожен перед исполнением `iF7`, те `freeze(j) -> iF7`, тогда поток i завершит свою работу за 1 шаг вне зависимости от значения флагов.
    2. `freeze(j) -> iF8 -> (3)`
    3. `freeze(j) -> iF9 -> iF4 -> iF5 -> iF6 -> iF7`.
    4. `freeze(j) -> iF1 -> (5)`
    5. `freeze(j) -> iF2 -> iF3 -> iF4 -> iF5 -> [iF8 -> (2)], [iF6 -> iF8 -> (3)], [iF6 -> iF7]`
    6. `freeze(j) -> iF6 -> [5]`
    7. `freeze(j) -> iF5 -> [5]`
    8. `freeze(j) -> iF4 -> [7]`
    9. `freeze(j) -> iF3 -> [7]`

### Does it guarantee deadlock-freedom?
- Из лекций: Deadlock-free: some thread trying to acquire the lock eventually succeeds. "Acquire the lock" здесь понимается, как событие `iF7` или `jF7`
- В первом пункте приводился пример дедлока, foo не является dealock-free


### Does it guarantee starvation-freedom?
- Starvation-free: every thread trying to acquire the lock eventually succeeds
- Следствие из предыдущего пункта: foo не является starfation-free

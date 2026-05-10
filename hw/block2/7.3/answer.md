
## Prove that Bakery Algorithm is deadlock-free.
- Всегда найдётся поток, который имеет лексикографически наименьшую пару `(label[A], A)` среди остальных, а потому он никого не ждёт и забирает lock.

## Prove that Bakery Algorithm is first-come-first-served.
- `first-come` здесь понимается сравнительно в отношении двух потоков `A` и `B`, где `writeA(label[A]) -> readB(label[A])`. В таком сценарии `(label[A], A) << (label[B], B)`, а потому поток `A` будет `first-served`

## Prove that Bakery Algorithm satisfies mutual exclusion.
- От обратного: пусть `A` и `B` оба попали в критическую секцию, пусть для определённости `(label[A], A) << (label[B], B))` тогда поток `B` для попадания в критическую секцию должен был увидеть, что `flag[A] == false`. Для этого:
`labeling[B] -> readB(flag[A]) -> flag[a] = true -> labeling[A]` <=> `(label[A], A) >> (label[B], B))` - противоречие.

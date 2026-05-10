
## 7.1.1
Введём следующую интерпретацию:
- disjoint = непересекающиеся
- overlapping = пересекающиеся

Prove that for two arbitrary intervals `X` and `Y` at least one of the following cases holds:
1. `X` and `Y` are disjoint and `X -> Y`; or
2. `X` and `Y` are disjoint and `Y -> X`; or
3. `X` and `Y` are overlapping intervals

- Если `X` и `Y` непересекающиеся, то по определению либо `X -> Y` либо `Y -> X`, в противном случае эти 2 интервала пересекающиеся. А значит, если 1 и 2 не выполнены, то выполнено 3.
- Пускай невыполнены условия 2 и 3, тогда `X` и `Y` пересекающиеся, значит либо `X -> Y` либо `Y -> X`, но по определению в силу невыполнения условия 2: `X -> Y`. Условие 1 выполнено
- Пускай теперь невыполнены условия 1 и 3, тогда `X` и `Y` пересекающиеся, значит либо `X -> Y` либо `Y -> X`, но по определению в силу невыполнения условия 1: `Y -> X`. Условие 2 выполнено

Что и требовалось доказать.

## Task 7.1.2

Prove that `->` (precedence) binary relation on intervals is a partial order that:
- Irreflexive. Never true that `X -> X`.
- Antisymmetric. If `X -> Y` then not true `Y -> X`.
- Transitive. If `X -> Y` and `Y -> Z` then `X -> Z`.

Please also prove that precedence is indeed a partial order: provide an example with `X != Y` where `X -> Y` false and `Y -> X` is also false.

Для формальности определим интервал `X(a, b)` как конечный набор из `n >= 2` событий:
`(a=x1 -> x2 -> ... -> xn = b)`, при этом для любого `i` **не** существует события `s` такого, что `xi -> s -> xi+1`.

- `X(a, b)`. От обратного: `x1 -> xn` по определению интервала, но по предположению `xn -> x1` => противоречие с условием строгого порядка над событиями

- `X(a, b), Y(c, d)`.` x1 -> ... -> xn -> y1 -> ... -> yn`. По транзитивности `x1 -> yn`, но тогда по свойству антисимметричности событий `!yn -> x1`, а тогда `!Y -> X`.

- `X(a, b), Y(c, d), Z(e, f)`. `x1 -> ... -> xn -> y1 -> ... -> yn -> z1 -> ... -> zn` по условию. По транзитивности `xn -> z1`. А тогда по определению `X -> Z` чтд.

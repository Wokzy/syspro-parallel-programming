
## 1

#### wait-free
- A method is wait-free if it guarantees that every call finishes its execution in a finite number of steps.
- It is bounded wait-free if there is a bound on the number of steps a method call can take.
- A wait-free method whose performance does not depend on the number of active threads is called population-oblivious.

#### lock-free
- A method is lock-free if it guarantees that infinitely often some method call finishes in a finite number of steps. Lock-free algorithms admit the **possibility** that some threads could starve.

#### obstruction-free
- A method is obstruction-free if, from any point after which it executes in isolation, it finishes in a finite number of steps.
- The obstruction-free condition ensures that not all threads can be blocked by a sudden delay of one or more other threads.


## 2
- A register that implements Register<Integer> for a range of M integer values is called an M-valued register
- An **atomic** register is a linearizable implementation of the sequential register
- MRSW register is safe if:
  1. read() does not overlap with write and return latest written value
  2. read() overlap with write() and return any value from allowed range

- MSRW register is regular if:
  1. read() does not overlap with write and return latest written value
  2. read() overlap with write() and return any value from {values written by write() overlapped with read()} U {latest register value before read()}

- atomic -> regular -> safe

## 3 

- unary notation: value is represented as array of bits, where first bit index set to true == value

- Lemma 4.2.3. The read() call in the construction in Fig. 4.8 always returns a value corresponding to a bit in 0..M − 1 set by some write() call.
- Proof: The following property is invariant: if a reading thread is reading r_bit[j], then some bit at index j or higher, written by a write() call, is set to true.

- Lemma 4.2.4. The construction in Fig. 4.8 is a regular M-valued MRSW register.
Proof: For any read, let x be the value written by the most recent non-overlapping
write(). At the time the write() completed, a_bit[x] was set to true, and
a_bit[i] is false for i < x. By Lemma 4.2.3, if the reader returns a value that is
not x, then it observed some a_bit[j], j > x to be true, and that bit must have
been set by a concurrent write (because it writes a_bit[j] before setting a_bit[x] to zero)

- Constructed MRSW M-valued register is corollary from Lemma 4.2.4
- Regular MRSW M-valued register is wait-free because it is bounded by M


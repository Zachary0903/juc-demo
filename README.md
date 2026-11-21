# Java Concurrency (JUC) Demo

This repository is a hands-on Java concurrency reference project. It contains standalone demonstrations of Java concurrency utilities and memory model concepts commonly used in multithreaded application development.

## Topics

- Thread visibility and atomicity with `volatile` and atomic classes
- CAS operations and the ABA problem
- `synchronized`, reentrant locks, deadlocks, and read/write locks
- `CountDownLatch`, `CyclicBarrier`, and `Semaphore`
- Blocking and synchronous queues
- `Callable`, `FutureTask`, and `CompletableFuture`
- Thread pools and rejection policies
- Fork/Join parallelism
- Java functional interfaces
- Singleton initialization patterns
- Concurrent collection behavior

## Project Layout

The source code is organized under `juc-demo/src/juc`:

- `count/` — synchronization primitives
- `bq/` — blocking queue implementations
- `lock/` and `rw/` — locking and synchronization
- `cas/` — compare-and-swap demonstrations
- `volatiletest/` — Java Memory Model and atomicity examples
- `pool/` — `ThreadPoolExecutor` configuration
- `future/` and `callable/` — asynchronous execution
- `forkjoin/` — parallel divide-and-conquer computation
- `function/` — `Supplier`, `Consumer`, `Function`, and `Predicate`
- `singleton/` — singleton implementation patterns
- `unsafe/` — concurrent collection examples
- `stream/` — stream demonstrations

## Requirements

- JDK 8 or later
- An IDE such as IntelliJ IDEA

## Running the Examples

Open the repository in IntelliJ IDEA or import `juc-demo/juc-demo.iml` as a Java module. Run any class containing a `main` method directly. Each package provides a small, independent concurrency scenario.

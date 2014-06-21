spring-transactional-example
============================

Shows the difference between Spring's Transaction Boundaries

Simple Transactions

| Propagation   |  A  |  B  |
| ------------- | --- | --- |
| REQUIRED      |  5  |  0  |
| NESTED        |  5  |  0  |
| REQUIRES_NEW  |  5  |  0  |
| SUPPORTS      |  5  |  4  |
| NOT_SUPPORTED |  5  |  4  |
| NEVER         |  5  |  4  |
| MANDATORY     |  0  |  0  |


A
=
Insert 5 records using specific propagation. All 5 records are good.

B
=
Inserts 5 records using a specific propagation. 4 records are good. 1 record is bad.

With One transaction level, we see 3 basic groups.

G1 = Group that executes in a transaction (All or Nothing)
G2 = Group that does not execute in a transaction (Good ones pass, bad ones fail, no rollback)
G3 = Group that requires a parent transaction and will not execute without it.
G1 = [REQUIRED, NESTED, REQUIRES_NEW]
G2 = [SUPPORTS, NOT_SUPPORTED, NEVER]
G3 = [MANDATORY]

Next, we show the difference between items in G1

| PROPAGATION   |  C  |  D  |
| ------------- | --- | --- |
| REQUIRED      |  X  |  X  |
| NESTED        |  X  |  X  |
| REQUIRES_NEW  |  X  |  X  |
  

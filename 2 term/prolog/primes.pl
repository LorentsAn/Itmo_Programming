divided(Input, Del):- X is (Input mod Del),
    X = 0.

sorted([A]) :- !.
sorted([A, A1 | T]) :- A =< A1, sorted([A1 | T]).

prime_mem(N, I, I) :-
    I =< N,
    divided(N, I),
    !.

prime_mem(N, Input, Del) :-
    Del =< N,
    not(divided(Input, Del)),
    D1 is Del + 1,
    prime_mem(N, Input, D1).

composite(N) :- N > 1, not(prime(N)).


prime(N):- N > 1, prime_mem(N, N, 2).

prime_divisors(1, []) :- !.

prime_divisors(N, [N]) :-
	prime(N), !.

prime_del(N, X, R) :-
	divided(N, X),
	prime(X),
	R is X.

prime_del(N, X, R) :-
	X1 is X + 1,
	prime_del(N, X1, R).


prime_divisors(N, [H | T]) :-
	N > 1,
	prime_del(N, 2, H),
	N1 is div(N, H),
	prime_divisors(N1, T),
	sorted([H | T]), !.


square_divisors(N, D) :-
    N1 is N * N,
    prime_divisors(N1, D).

map_build(ListMap, TreeMap) :-
  length(ListMap, Len),
  D is log(Len) / log(2),
  map_build(ListMap, D, Len, _, TreeNode, _),
  TreeMap = TreeNode.

map_build(ListMap, Depth, Size, ListMap, nullNode, 0) :- Size = 0, !.
map_build([H | Next], Depth, Len, Next, node(H1, null, null), NewLen) :- Depth = 0, H1 = H, NewLen = Len - 1, !.

make(K, V, K, V).

map_build(ListMap, Depth, Len, Next1, node(Key, Value, Left, Right), NewSize) :-
    D1 is Depth - 1,
    Len1 is Len - 1,
    map_build(ListMap, D1, Len1, [(K, V) | Next], Left, LeftLength),
    make(K, V, Key, Value),
    map_build(Next, D1, LeftLength, Next1, Right, NewLen),
    !.
map_get(tree(Node, Len), Key, Value) :- map_get(Node, Key, Value).

map_get(node(Key, Value, _, _), Key, Value) :- !.

map_get(node(Key, Value, LeftChild, RightChild), K, V) :-
  Key @> K, map_get(LeftChild, K, V);
  Key @< K, map_get(RightChild, K, V).

map_containsKey(node(Key, Value, _, _), Key) :- !.

map_containsKey(node(Key, Value, LeftChild, RightChild), K) :-
    Key @> K, map_get(LeftChild, K, V);
    Key @< K, map_get(RightChild, K, V).

map_containsValue(node(_, Value, _, _), Value).
map_containsValue(node(_, _, LeftChild, _), Value) :-
    map_containsValue(LeftChild, Value).
map_containsValue(node(_, _, _, RightChild), Value) :-
    map_containsValue(RightChild, Value).
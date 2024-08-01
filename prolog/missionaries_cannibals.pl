% State representation: state(MLeft, CLeft, BoatSide, MRight, CRight)
% MLeft: Number of missionaries on the left side
% CLeft: Number of cannibals on the left side
% BoatSide: Indicates the side of the boat, either 'left' or 'right'
% MRight: Number of missionaries on the right side
% CRight: Number of cannibals on the right side

% Initial state
initial(state(3, 3, left, 0, 0)).

% Final state
final(state(0, 0, _, 3, 3)).

% Valid state
valid(state(MLeft, CLeft, _, MRight, CRight)) :-
    % The number of missionaries on either side should not be negative
    MLeft >= 0, MRight >= 0,
    % The number of cannibals on either side should not be negative
    CLeft >= 0, CRight >= 0,
    % No more cannibals than missionaries on either side
    (MLeft >= CLeft ; MLeft = 0),
    (MRight >= CRight ; MRight = 0).

% Move boat from left to right
move(state(MLeft, CLeft, left, MRight, CRight), state(MLeftNew, CLeftNew, right, MRightNew, CRightNew)) :-
    % The boat can only carry up to two people
    between(0, 2, X),
    % X is the number of people (missionaries and/or cannibals) to move from left to right
    between(0, MLeft, XM),
    between(0, CLeft, XC),
    XM + XC =:= X,
    MLeftNew is MLeft - XM,
    CLeftNew is CLeft - XC,
    MRightNew is MRight + XM,
    CRightNew is CRight + XC,
    valid(state(MLeftNew, CLeftNew, right, MRightNew, CRightNew)).

% Move boat from right to left
move(state(MLeft, CLeft, right, MRight, CRight), state(MLeftNew, CLeftNew, left, MRightNew, CRightNew)) :-
    % The boat can only carry up to two people
    between(0, 2, X),
    % X is the number of people (missionaries and/or cannibals) to move from right to left
    between(0, MRight, XM),
    between(0, CRight, XC),
    XM + XC =:= X,
    MLeftNew is MLeft + XM,
    CLeftNew is CLeft + XC,
    MRightNew is MRight - XM,
    CRightNew is CRight - XC,
    valid(state(MLeftNew, CLeftNew, left, MRightNew, CRightNew)).

% Print path
printPath([]).
printPath([State|Path]) :-
    printPath(Path),
    write(State), nl.

% Path finding
path(Start, End, Path) :-
    % Search for a path using depth-first search
    dfs(Start, End, [Start], Path).

dfs(Node, Node, Visited, [Node|Visited]).
dfs(CurrentNode, Node, Visited, Path) :-
    move(CurrentNode, NextNode),
    not(member(NextNode, Visited)),
    dfs(NextNode, Node, [NextNode|Visited], Path).

% Solve the problem
solve :-
    initial(InitialState),
    final(FinalState),
    path(InitialState, FinalState, Path),
    printPath(Path).

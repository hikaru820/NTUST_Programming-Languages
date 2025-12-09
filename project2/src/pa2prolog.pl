% Programming Assignment 2 - Prolog
% Family Relationship Predicates

% mother(X, Y): X is the mother of Y
mother(X, Y) :-
    parent(X, Y),
    female(X).

% father(X, Y): X is the father of Y
father(X, Y) :-
    parent(X, Y),
    male(X).

% child(X, Y): X is a child of Y
child(X, Y) :-
    parent(Y, X).

% sibling(X, Y): X and Y are siblings (share at least one parent)
sibling(X, Y) :-
    parent(Z, X),
    parent(Z, Y),
    X \= Y.

% sister(X, Y): X is the sister of Y
sister(X, Y) :-
    sibling(X, Y),
    female(X).

% brother(X, Y): X is the brother of Y
brother(X, Y) :-
    sibling(X, Y),
    male(X).

% daughter(X, Y): X is the daughter of Y
daughter(X, Y) :-
    child(X, Y),
    female(X).

% son(X, Y): X is the son of Y
son(X, Y) :-
    child(X, Y),
    male(X).

% uncle(X, Y): X is the uncle of Y (brother of Y's parent)
uncle(X, Y) :-
    parent(Z, Y),
    brother(X, Z).

% aunt(X, Y): X is the aunt of Y (sister of Y's parent)
aunt(X, Y) :-
    parent(Z, Y),
    sister(X, Z).

% cousin(X, Y): X and Y are cousins (their parents are siblings)
cousin(X, Y) :-
    parent(P1, X),
    parent(P2, Y),
    sibling(P1, P2).

% Do not modify the following facts.  Complete the predicates 
% above so that they work with the following facts.

male(mark).
male(mel).
male(richard).
male(tom).
male(adam).

female(amy).
female(jane).
female(joan).
female(betty).
female(rosa).
female(fran).


parent(mel, joan).
parent(jane, betty).
parent(jane, tom).
parent(richard, adam).
parent(richard, rosa).
parent(joan, fran).
parent(mark, jane).
parent(mark, richard).
parent(amy, jane).
parent(amy, richard).
parent(amy, joan).

% ============================================================
% TEST QUERIES
% ============================================================

% Query 1: Show that adam is betty's cousin
% ?- cousin(adam, betty).
% Expected: true

% Query 2: Show that rosa is betty's cousin
% ?- cousin(rosa, betty).
% Expected: true

% Query 3: Show that fran is betty's cousin
% ?- cousin(fran, betty).
% Expected: true

% Query 4: Show that betty and tom are siblings
% ?- sibling(betty, tom).
% Expected: true

% Query 5: Show that betty is tom's sister
% ?- sister(betty, tom).
% Expected: true

% Query 6: Show that tom is betty's brother
% ?- brother(tom, betty).
% Expected: true

% ============================================================
% ADDITIONAL TEST QUERIES FOR ALL PREDICATES
% ============================================================

% Testing mother/2
% ?- mother(jane, betty).
% Expected: true
% ?- mother(amy, jane).
% Expected: true

% Testing father/2
% ?- father(mark, jane).
% Expected: true
% ?- father(mel, joan).
% Expected: true

% Testing child/2
% ?- child(betty, jane).
% Expected: true
% ?- child(tom, jane).
% Expected: true

% Testing daughter/2
% ?- daughter(betty, jane).
% Expected: true
% ?- daughter(rosa, richard).
% Expected: true

% Testing son/2
% ?- son(tom, jane).
% Expected: true
% ?- son(adam, richard).
% Expected: true

% Testing uncle/2
% ?- uncle(richard, betty).
% Expected: true (richard is jane's brother, jane is betty's mother)
% ?- uncle(richard, tom).
% Expected: true

% Testing aunt/2
% ?- aunt(jane, adam).
% Expected: true (jane is richard's sister, richard is adam's father)
% ?- aunt(joan, betty).
% Expected: true (joan is jane's sister, jane is betty's mother)

% Finding all of betty's cousins
% ?- cousin(X, betty).
% Expected: X = adam ; X = rosa ; X = fran

% Finding all cousins (both directions)
% ?- cousin(X, Y).
% This will return all cousin pairs in the family

% ============================================================
% SAMPLE OUTPUT FROM QUERIES
% ============================================================
/*
?- cousin(adam, betty).
true.

?- cousin(rosa, betty).
true.

?- cousin(fran, betty).
true.

?- sister(betty, tom).
true.

?- brother(tom, betty).
true.

?- cousin(X, betty).
X = adam ;
X = rosa ;
X = fran.
*/
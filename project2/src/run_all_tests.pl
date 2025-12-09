% Load the main prolog file
:- consult('pa2prolog.pl').

% Display header
:- writeln('=====================================').
:- writeln('Prolog Test Results').
:- writeln('=====================================').
:- writeln('').

% Required Queries
:- writeln('Testing Required Queries:').
:- writeln('').

:- writeln('1. Is adam betty\'s cousin?').
:- (cousin(adam, betty) -> writeln('   Result: true') ; writeln('   Result: false')).
:- writeln('').

:- writeln('2. Is rosa betty\'s cousin?').
:- (cousin(rosa, betty) -> writeln('   Result: true') ; writeln('   Result: false')).
:- writeln('').

:- writeln('3. Is fran betty\'s cousin?').
:- (cousin(fran, betty) -> writeln('   Result: true') ; writeln('   Result: false')).
:- writeln('').

:- writeln('4. Is betty tom\'s sister?').
:- (sister(betty, tom) -> writeln('   Result: true') ; writeln('   Result: false')).
:- writeln('').

:- writeln('5. Is tom betty\'s brother?').
:- (brother(tom, betty) -> writeln('   Result: true') ; writeln('   Result: false')).
:- writeln('').

:- writeln('=====================================').
:- writeln('Testing All Predicates:').
:- writeln('=====================================').
:- writeln('').

:- writeln('Mother predicate:').
:- (mother(jane, betty) -> writeln('  mother(jane, betty): true') ; writeln('  mother(jane, betty): false')).
:- (mother(amy, jane) -> writeln('  mother(amy, jane): true') ; writeln('  mother(amy, jane): false')).
:- writeln('').

:- writeln('Father predicate:').
:- (father(mark, jane) -> writeln('  father(mark, jane): true') ; writeln('  father(mark, jane): false')).
:- (father(richard, adam) -> writeln('  father(richard, adam): true') ; writeln('  father(richard, adam): false')).
:- writeln('').

:- writeln('Child predicate:').
:- (child(betty, jane) -> writeln('  child(betty, jane): true') ; writeln('  child(betty, jane): false')).
:- (child(tom, jane) -> writeln('  child(tom, jane): true') ; writeln('  child(tom, jane): false')).
:- writeln('').

:- writeln('Sibling predicate:').
:- (sibling(betty, tom) -> writeln('  sibling(betty, tom): true') ; writeln('  sibling(betty, tom): false')).
:- (sibling(jane, richard) -> writeln('  sibling(jane, richard): true') ; writeln('  sibling(jane, richard): false')).
:- writeln('').

:- writeln('Sister predicate:').
:- (sister(betty, tom) -> writeln('  sister(betty, tom): true') ; writeln('  sister(betty, tom): false')).
:- (sister(jane, richard) -> writeln('  sister(jane, richard): true') ; writeln('  sister(jane, richard): false')).
:- writeln('').

:- writeln('Brother predicate:').
:- (brother(tom, betty) -> writeln('  brother(tom, betty): true') ; writeln('  brother(tom, betty): false')).
:- (brother(richard, jane) -> writeln('  brother(richard, jane): true') ; writeln('  brother(richard, jane): false')).
:- writeln('').

:- writeln('Daughter predicate:').
:- (daughter(betty, jane) -> writeln('  daughter(betty, jane): true') ; writeln('  daughter(betty, jane): false')).
:- (daughter(rosa, richard) -> writeln('  daughter(rosa, richard): true') ; writeln('  daughter(rosa, richard): false')).
:- writeln('').

:- writeln('Son predicate:').
:- (son(tom, jane) -> writeln('  son(tom, jane): true') ; writeln('  son(tom, jane): false')).
:- (son(adam, richard) -> writeln('  son(adam, richard): true') ; writeln('  son(adam, richard): false')).
:- writeln('').

:- writeln('Uncle predicate:').
:- (uncle(richard, betty) -> writeln('  uncle(richard, betty): true') ; writeln('  uncle(richard, betty): false')).
:- (uncle(richard, tom) -> writeln('  uncle(richard, tom): true') ; writeln('  uncle(richard, tom): false')).
:- writeln('').

:- writeln('Aunt predicate:').
:- (aunt(jane, adam) -> writeln('  aunt(jane, adam): true') ; writeln('  aunt(jane, adam): false')).
:- (aunt(joan, betty) -> writeln('  aunt(joan, betty): true') ; writeln('  aunt(joan, betty): false')).
:- writeln('').

:- writeln('Cousin predicate - All of Betty\'s cousins:').
:- findall(X, cousin(X, betty), Cousins), write('  Cousins of betty: '), writeln(Cousins).
:- writeln('').

:- writeln('=====================================').
:- writeln('Tests completed successfully!').
:- writeln('=====================================').

% Exit prolog
:- halt.
;;; Programming Assignment 2 - Scheme Part 2
;;; Function: dbl_atm
;;; Input: An atom and a list
;;; Output: A list with all occurrences of the given atom doubled
#lang racket

(define (dbl_atm atom lst)
  (cond
    ;; Base case: empty list
    ((null? lst) '())
    
    ;; If first element is a list, recursively process it
    ((list? (car lst))
     (cons (dbl_atm atom (car lst))
           (dbl_atm atom (cdr lst))))
    
    ;; If first element matches the atom, double it
    ((eq? (car lst) atom)
     (cons atom (cons atom (dbl_atm atom (cdr lst)))))
    
    ;; Otherwise, keep the element as is
    (else
     (cons (car lst) (dbl_atm atom (cdr lst))))))

;;; Test Cases
(display "Test Case 1 (Given Example):\n")
(display "atom = a\n")
(display "list = (a (b c a (a d)))\n")
(display "Expected: (a a (b c a a (a a d)))\n")
(display "Result:   ")
(display (dbl_atm 'a '(a (b c a (a d)))))
(newline)
(newline)

(display "Test Case 2:\n")
(display "atom = x\n")
(display "list = (x y x z)\n")
(display "Expected: (x x y x x z)\n")
(display "Result:   ")
(display (dbl_atm 'x '(x y x z)))
(newline)
(newline)

(display "Test Case 3:\n")
(display "atom = 1\n")
(display "list = (1 2 (3 1) 4 1)\n")
(display "Expected: (1 1 2 (3 1 1) 4 1 1)\n")
(display "Result:   ")
(display (dbl_atm 1 '(1 2 (3 1) 4 1)))
(newline)
(newline)

(display "Test Case 4 (No matches):\n")
(display "atom = z\n")
(display "list = (a b c (d e))\n")
(display "Expected: (a b c (d e))\n")
(display "Result:   ")
(display (dbl_atm 'z '(a b c (d e))))
(newline)
(newline)

(display "Test Case 5 (Empty list):\n")
(display "atom = a\n")
(display "list = ()\n")
(display "Expected: ()\n")
(display "Result:   ")
(display (dbl_atm 'a '()))
(newline)
(newline)

(display "Test Case 6 (Deeply nested):\n")
(display "atom = a\n")
(display "list = (a (a (a)))\n")
(display "Expected: (a a (a a (a a)))\n")
(display "Result:   ")
(display (dbl_atm 'a '(a (a (a)))))
(newline)
;;; Programming Assignment 2 - Scheme Part 1
;;; Function: inv_app
;;; Input: Two lists (list1 and list2)
;;; Output: Inverse of list2 concatenated with inverse of list1
#lang racket

(define (inv_app list1 list2)
  (append (reverse list2) (reverse list1)))

;;; Test Cases
(display "Test Case 1:\n")
(display "list1 = (1 2 3)\n")
(display "list2 = (a b c)\n")
(display "Expected: (c b a 3 2 1)\n")
(display "Result:   ")
(display (inv_app '(1 2 3) '(a b c)))
(newline)
(newline)

(display "Test Case 2:\n")
(display "list1 = (x y)\n")
(display "list2 = (1 2 3 4)\n")
(display "Expected: (4 3 2 1 y x)\n")
(display "Result:   ")
(display (inv_app '(x y) '(1 2 3 4)))
(newline)
(newline)

(display "Test Case 3:\n")
(display "list1 = (hello world)\n")
(display "list2 = (foo bar baz)\n")
(display "Expected: (baz bar foo world hello)\n")
(display "Result:   ")
(display (inv_app '(hello world) '(foo bar baz)))
(newline)
(newline)

(display "Test Case 4 (Empty lists):\n")
(display "list1 = ()\n")
(display "list2 = ()\n")
(display "Expected: ()\n")
(display "Result:   ")
(display (inv_app '() '()))
(newline)
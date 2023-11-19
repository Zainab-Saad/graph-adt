# Graph - Abstract Data Type Design

The type we will implement is Graph<L>: an abstract data type for mutable weighted directed graphs with labeled vertices.

## Task1:
Devise, document, and implement tests for Graph<String> .
For now, we’ll only test (and then implement) graphs with String vertex labels. Later, we’ll expand to other kinds of labels.
In order to accommodate running our tests on multiple implementations of the Graph 
interface, here is the setup:
•	The testing strategy and tests for the static Graph.empty() method are in GraphStaticTest.java . Since the method is static, there will be only one implementation, and we only need to run these tests once. We’ve provided these tests. You are free to change or add to them, but you can leave them as-is for this problem.
•	Write your testing strategy and your tests for all the instance methods in GraphInstanceTest.java . In these tests, you must use the emptyInstance() method to get fresh empty graphs, not Graph.empty() ! See the provided testInitialVerticesEmpty() for an example.



## Task2:

Implement weighted directed graphs with String labels — twice . Check further details on the link. And provide the following for all the classes in this task:
•	Document the abstraction function and representation invariant .
•	Along with the rep invariant, document how the type prevents rep exposure .
•	Implement checkRep to check the rep invariant.
•	Implement toString with a useful human-readable representation of the abstract value.



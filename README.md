Math
====
Packages that support more complex mathematical processes than the standard java.lang.Math package.
Functions can be stored and operations can be performed on those functions. Matrix math is also supported, as is usage of complex numbers.

Intended operations include:  
    *Summation
    *Multiplication by sequence
    *Integration
    *Differentiation

###Data
The Data class is a superclass for all non-Java number types, such as complex numbers. It contains the operations central to all data types, such as addition.

ComplexNumber stores a real and an imaginary component as double values. Addition and multiplication are supported.

###Functions
All functions are subclasses of the class Function, which has abstract methods for evaluation and functional differentiation. These methods must be implemented by all subclasses. Function also has a scalar field which gives the scalar multiple applied to the function (1 by default).

Polynomials are stored as linkedlists of coefficients, starting with the constant term and going up until the leading coefficient. Evaluation is performed by traversing this list and raising the argument to the appropriate power, multiplying by coefficient, and adding the result to the total.

Sinusoidal functions are subclasses of the Sinusoid class, which is itself a subclass of Function. All sinusoids have a phase shift (0 by default) and an angular frequency constant (1 by default). For example, a Sine function with a scalar of 3, an angular frequency of 5, and a phase shift of -2 would be 3Sin(5x-2).

###Matrix Math
Matrices are currently stored as two-dimensional double arrays. Addition and multiplication is supported. Invertible matrices can be inverted, and thus a functionality for the solution of determined systems is supplied. Non-determined systems (with more than one or no solutions) will cause exceptions to be thrown.

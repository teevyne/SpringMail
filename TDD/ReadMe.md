CHAPTER ONE\
BUILDING SYSTEMS THE RIGHT WAY

Returning HTTP response in django


The key to TDD is to first write a test that is expected to fail, because what it is supposed to test is not in existence yet. We are writing what we expect the system to do when we perform an action
A lot of times, TDD is called Test-Driven Design as it would help you achieve a fine design for your system. The design part of TDD is more important than the testing part. When
we do test last, we don’t reap the design benefit from the practice.. TDD helps us achieve two aims: 1. Detect regression errors 2. Keep sytem design simple

The more the test, the more the resilience and safety of the system. If the system is written following TDD, then any time you discover a bug, the team will add an additional test case that reproduces the error. Now, you will be able to make changes to the production code to pass the failing test case. TDD actually does more of design than testing The two phases of TDD are design and coding

TDD is often
looked as a continuous cycle of RED -> GREEN -> REFACTOR. It is the refactoring stage
where design emerges. Refactoring is defined as improving the design of existing code
without changing its external behavior. TDD as a design tool requires a change in mind-set. It will not happen in a day.
It takes constant practice to master and reap the benefits.

Benefits of Unit Testing
1. Specifications - Unit testing ensures that the actual behavior - expeted input and output - of the system are met. If this is not the case, then some more thinking of what the system is meant to do is engaged in
2. Earlt error detection - Testing helps to uncover errors as the system in being built. This almost always goes in sync with writing the production code itself, in terms of specification
3. Maintenace - UT helps in understanding the dynamics and functionalities of a code without being overwhelmed by the actual code
4. Improves design - Testing the code makes you uncover new approaches for which the code can be implemented and as such, the design of the system - implementation-wise - is improved
5. Documentation - Since unit test help with determining system specifications, they also describe how the piece of code work and this in itself is documentation for the code.

JUnt 5 - code name for JUnit Lambda, was built from ground up to avoid the mistakes and limitations of JUnit 4. it is backward compatible from JUnit 3.8 above and can only be used with Java 8. JUnit 5 has three subprojects: JUnit Platform (for the JVM), JUnit Jupiter and JUnit Vintage

A test case is broken down into the following three parts:
• We set up data that our test case needs.					Arrange
• We call the unit being tested.						Act
• We perform assertions to verify if expected behavior is correct.		Assert
This is also called AAA (Arrange, Act, and Assert).

CHapter Two

You do not have to obey all of the rules of engagement, JUnit 5 that held sway in JUnit 4.
Basically, your constructor does not have to have a public visibility, already it has a test method as shown up here\
and it not only has a constructor, but one with parameters - though this is only made possible with the help of ParameterResolver \
class.

We also looked at the assertions made available by JUnit 4 and JUnit 5.. assertThat is not presrnt in JUnit 5
assertAll comes to thr rescue in situations like this 
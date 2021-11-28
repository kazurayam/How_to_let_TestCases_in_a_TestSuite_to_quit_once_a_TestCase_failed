# How to let TestCases in a TestSuite to quit once a TestCase failed

## Problem to solve

A topic in the Katalon User Forum wrote:

- [Stop all test suite if one test case failed](https://forum.katalon.com/t/stop-all-test-suite-if-one-test-case-failed/49629)

>Hi,
>there is a way to stop a test suite if one of its cases fails?
>I need some method or form to stop the suite, does anyone have a solution?

Fair enough requirement, I think. 
Let me assume I have a Test Suite `TS1` is comprised with 3 Test Cases: `TC1`, `TC2`, `TC3`. 
 The `TC3` runs very long (e.g, 20 minutes). The `TC2` normally passes but occasionally fails.
When I run the `TS1` and unfortunately the `TC2` failed, I want the `TS1` stops as soon as the `TC2` failed.
I do not want to wait for the `TC3` to finish after 20 minutes.

However Katalon Studio does not support the feature to stop a Test Suite when a comprising Test Case failed.

What else can I do practically to let a Test Suite to finish as soon as a Test Case failed?

## Solution

I will not ask Katalon Studio to control if it should invoke each Test Cases (TC1, TC2, TC3) or not.
I will let it invoke all Test Cases in a Test Suite as defined.

Rather, I would write *each Test Cases to check if any of preceding Test Cases in the Test Suite has failed*. 
If there are any failed Test Cases, then a Test Case should quit immediately. A Test Case should check it before executing the body of test processes to prevent consuming time. Test Cases can be self-deterministic if it is informed if any of its preceding Test Cases in a Test Suite has failed or not.

I will introduce a few Groovy classes that implements *Self-deterministic Test Cases in Test Suite*. A jar file that includes the module will be provided.


## Dependencies

At the [Releases](https://github.com/kazurayam/TestSuiteAdvisor/releases) page, you can download the jar of `TestSuiteAdvizor-x.x.x.jar`. You want to copy that jar into your project's `Drivers` folder.

TestSuiteAdvisor internally depends on the [ExecutionProfilesLoader](https://github.com/kazurayam/ExecutionProfilesLoader/releases) project's jar. You want to copy the jar into your project's `Drivers' folder as well.

See the following screenshot how the `Drivers` folder would look like:

![Drivers](docs/images/Drivers.png)


## How to write your code

### Test Cases

I wrote 4 Test Cases.

[TC1_passes](Scripts/TC1_passes/Script1638068375427.groovy)

```java:Scripts/TC1_passes/Script1638068375427.groovy
```

#### Test Listener



# How to let Test Cases in a Test Suite to quit once a Test Cases failed

## Problem to solve

A topic in the Katalon User Forum [Stop all test suite if one test case failed](https://forum.katalon.com/t/stop-all-test-suite-if-one-test-case-failed/49629) wrote :

    Hi,
    there is a way to stop a test suite if one of its cases fails?
    I need some method or form to stop the suite, does anyone have a solution?

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

Rather, I would write **each Test Cases to check if any of preceding Test Cases in the Test Suite has failed**.
If there are any failed Test Cases, then a Test Case should quit immediately. A Test Case should check it before executing the body of test processes to prevent consuming time. I will introduce a few custom Groovy classes. A jar file that includes the module will be provided. Provided that Test Cases are informed of the results of preceding Test Cases, following Test Cases can be self-deterministic.

## Demonstration

You want to run `Test Suites/TSa`. The `TSa` is comprised with 3 Test Cases: `TS1_passes`, `TS2_passes`, `TS3_passes`. When you run `TSa`, all of component Test Cases will pass.

![TSa](docs/images/TSa.png)

Next, please run the `Test Suites/TSb`. The `TSb` is comprised with 3 Test Cases: `TS1_passes`, `TS2_fails`, `TS3_passes`. When you run it, `TS2_fails` will fail intentionally, and **`TS3_passes` quits soon before doing any meaningful actions**.

![TSb](docs/images/TSb.png)

As you see, the `TSb` can shorten the duration required for the `TC3` after the failed `TC2`.

Yes, Katalon Studio still executes all of 3 Test Cases defined in the Test Case `TSb`. But the Test Cases are coded so that they quit soon if one or more preceding Test Cases failed. Therefore `TSb` can eliminate redundant duration.

## How you should write your code

### Test Cases

I wrote 4 Test Cases.

#### [TC1\_passes](Scripts/TC1_passes/Script1638068375427.groovy)

    import com.kazurayam.ks.testsuite.Advisor
    import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

    if (Advisor.shouldQuit()) return;

    WebUI.comment("TC1 ran")

    for (int i in 1..3) {
        WebUI.comment("TC1 is doing a heavy task: ${i}")
    }

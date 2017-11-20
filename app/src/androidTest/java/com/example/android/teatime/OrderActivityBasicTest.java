package com.example.android.teatime;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * This test demos a user clicking the increment/decrement button and verifying that it properly
 * increase/decrease the quantity the total cost.
 * <p>
 * Created by jane on 17-11-20.
 */
@RunWith(AndroidJUnit4.class)
public class OrderActivityBasicTest {
    /**
     * The ActivityTestRule is a rule provided by Android used for functional testing of a single
     * activity. The activity that will be tested will be launched before each test that's annotated
     * with @Test and before methods annotated with @before. The activity will be terminated after
     * the test and methods annotated with @After are complete. This rule allows you to directly
     * access the activity during the test.
     */
    @Rule
    public ActivityTestRule<OrderActivity> mActivityTestRule
            = new ActivityTestRule<>(OrderActivity.class);

    @Test
    public void clickIncrementButton_ChangesQuantityAndCost() {
        // Find the view
        // Perform action on the view
        onView(withId(R.id.increment_button)).perform(click());
        // Check if the view does what you expected
        onView(withId(R.id.quantity_text_view)).check(matches(withText("1")));
        onView(withId(R.id.cost_text_view)).check(matches(withText("£5.00")));
    }

    @Test
    public void clickDecrementButton_ChangesQuantityAndCost() {
        // Check the initial quantity variable is zero
        onView(withId(R.id.quantity_text_view)).check(matches(withText("0")));
        // Click on decrement button
        onView(withId(R.id.decrement_button)).perform(click());
        onView(withId(R.id.quantity_text_view)).check(matches(withText("0")));
        onView(withId(R.id.cost_text_view)).check(matches(withText("$0.00")));
    }
}

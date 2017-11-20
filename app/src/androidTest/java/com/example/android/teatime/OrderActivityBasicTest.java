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
 * Created by jane on 17-11-20.
 */
@RunWith(AndroidJUnit4.class)
public class OrderActivityBasicTest {
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
}

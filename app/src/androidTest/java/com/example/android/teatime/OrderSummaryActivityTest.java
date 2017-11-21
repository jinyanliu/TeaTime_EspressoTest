package com.example.android.teatime;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.app.Instrumentation.ActivityResult;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.Intents.intending;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasData;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasExtra;
import static android.support.test.espresso.intent.matcher.IntentMatchers.isInternal;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.AllOf.allOf;


/**
 * This test demonstrates Espresso Intents using the IntentsTestRule, a class that extends
 * ActivityTestRules. IntentsTestRule initializes Espresso-Intents before each test that is
 * annotated wih @Test and releases it once the test is complete. The designed Activity is also
 * terminated after each test.
 * <p>
 * Created by jane on 17-11-21.
 */
@RunWith(AndroidJUnit4.class)
public class OrderSummaryActivityTest {

    private static final Uri INTENT_DATA_MAIL_TO = Uri.parse("mailto:");

    private Context instrumentationCtx;

    private static String extra_text_email_message;

    private static String extra_subject_email_subject;

    /**
     * A JUnit{@link Rule @Rule} to init and release Espresso Intents before and after each test run.
     * <p>
     * Rules are interceptors which are ececuted for each test method and will run before any of your
     * setup code in the {@link Before @Before} method.
     * <p>
     * This rule is based on {@link ActivityTestRule} and will create and
     * launch of the activity for you and also expose the activity under test.
     */
    @Rule
    public IntentsTestRule<OrderSummaryActivity> mActivityRule
            = new IntentsTestRule<>(OrderSummaryActivity.class);

    @Before
    public void stubAllExternalIntents() {
        // By default Espresso Intents does not stub any Intents. Stubbing needs to be setup before
        // every test run. In this case all external Intents will be blocked.
        intending(not(isInternal())).respondWith(new ActivityResult(Activity.RESULT_OK, null));
    }

    @Before
    public void setUp() {
        instrumentationCtx = InstrumentationRegistry.getTargetContext();
        extra_text_email_message = instrumentationCtx.getString(R.string.email_message);
        extra_subject_email_subject = instrumentationCtx.getString(R.string.order_summary_email_subject);
    }

    @Test
    public void clickSendEmailButton_SendsEmail() {
        onView(withId(R.id.send_email_button)).perform(click());
        // intended(Matcher<Intent> matcher) asserts the given matcher matches one and only one intent
        // sent by the application.
        intended(allOf(
                hasAction(Intent.ACTION_SENDTO),
                hasData(INTENT_DATA_MAIL_TO),
                hasExtra(Intent.EXTRA_SUBJECT, extra_subject_email_subject),
                hasExtra(Intent.EXTRA_TEXT, extra_text_email_message)));
    }
}

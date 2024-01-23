package com.ankit.cryptocoinlayerapp;

import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.idling.CountingIdlingResource;
import androidx.test.rule.ActivityTestRule;

import java.util.concurrent.atomic.LongAdder;

public class LandingActivityTestRule extends ActivityTestRule<LandingActivity> {

    // Use a counting idling resource to wait for background tasks to complete
    private CountingIdlingResource idlingResource = new CountingIdlingResource("LandingActivityIdlingResource");
    private LongAdder EspressoIdlingResource;

    public LandingActivityTestRule() {
        super(LandingActivity.class, false, false);
    }

    @Override
    protected void beforeActivityLaunched() {
        // We can perform setup tasks before launching the activity if needed
        // For example, you can mock dependencies or set up test data
        super.beforeActivityLaunched();
    }

    @Override
    protected void afterActivityLaunched() {
        super.afterActivityLaunched();
        // Register the idling resource when the activity is launched
        IdlingRegistry.getInstance().register(idlingResource);
    }

    @Override
    protected void afterActivityFinished() {
        // Unregister the idling resource when the activity is finished
        IdlingRegistry.getInstance().unregister(idlingResource);
        super.afterActivityFinished();
    }

    public void incrementIdlingResource() {
        // Increment the idling resource count
        idlingResource.increment();
    }

    public void decrementIdlingResource() {
        // Decrement the idling resource count
        idlingResource.decrement();
    }

    public void waitForBackgroundTasks() {
        // Wait for background tasks to complete using the idling resource
        EspressoIdlingResource.increment();
        EspressoIdlingResource.decrement();
    }
}


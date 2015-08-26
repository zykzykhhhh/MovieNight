package com.example.yzha502.movienight;

import android.app.Instrumentation;
import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.test.ActivityUnitTestCase;
import android.test.TouchUtils;
import android.test.suitebuilder.annotation.MediumTest;
import android.widget.Button;
import android.widget.EditText;

import com.example.yzha502.movienight.entities.Users;

/**
 * Created by yzha502 on 14/06/15.
 */
public class MainActivityTest extends ActivityUnitTestCase<MainActivity> {

    private Intent mLaunchIntent;

    public MainActivityTest() {
        super(MainActivity.class);
    }


    @Override
    protected void setUp() throws Exception {
        super.setUp();
        //Create an intent to launch target Activity
        mLaunchIntent = new Intent(getInstrumentation().getTargetContext(),
                MainActivity.class);
    }

    /**
     * Tests the preconditions of this test fixture.
     */
    @MediumTest
    public void testPreconditions() {
        //Start the activity under test in isolation, without values for savedInstanceState and
        //lastNonConfigurationInstance
        startActivity(mLaunchIntent, null, null);
        final Button launchNextButton = (Button) getActivity().findViewById(R.id.LoginButton);

        final EditText text1 = (EditText) getActivity().findViewById(R.id.StudentIDText);
        final EditText text2 = (EditText) getActivity().findViewById(R.id.PasswordeditText);
        text1.setText("zyk");
        text2.setText("12345");

        assertNotNull("mLaunchActivity is null", getActivity());
        assertNotNull("mLaunchNextButton is null", launchNextButton);
        assertNotNull("EditText1 is null", text1);
        assertNotNull("EditText2 is null", text2);
    }


    @MediumTest
    public void testLaunchNextActivityButton_labelText() {
        startActivity(mLaunchIntent, null, null);
        final Button launchNextButton = (Button) getActivity().findViewById(R.id.LoginButton);

        final EditText text1 = (EditText) getActivity().findViewById(R.id.StudentIDText);
        final EditText text2 = (EditText) getActivity().findViewById(R.id.PasswordeditText);
        text1.setText("zyk");
        text2.setText("12345");

        final String expectedButtonText = "Log in";
        assertEquals("Unexpected button label text", expectedButtonText,
                launchNextButton.getText());
    }

    @MediumTest
    public void testNextActivityWasLaunchedWithIntent() {
        startActivity(mLaunchIntent, null, null);
        final Button launchNextButton = (Button) getActivity().findViewById(R.id.LoginButton);

        final EditText text1 = (EditText) getActivity().findViewById(R.id.StudentIDText);
        final EditText text2 = (EditText) getActivity().findViewById(R.id.PasswordeditText);
        text1.setText("zyk");
        text2.setText("12345");
        //Because this is an isolated ActivityUnitTestCase we have to directly click the
        //button from code
        launchNextButton.performClick();

        // Get the intent for the next started activity
        final Intent launchIntent = getStartedActivityIntent();
        //Verify the intent was not null.
        assertNotNull("Intent was null", launchIntent);
        //Verify that LaunchActivity was finished after button click
//        assertTrue(isFinishCalled());


        final Users payload = launchIntent.getParcelableExtra("user");
        //Verify that payload data was added to the intent
        assertEquals("Payload is empty", MainActivity.getUser()
                , payload);
    }

}

package com.example.yzha502.movienight;


import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


/**
 * Created by yzha502 on 14/06/15.
 */
public class TestClass extends ActivityInstrumentationTestCase2<MainActivity> {
    private MainActivity sample;
    private HomeActivity home;
    private Button searchButton;
    private Button libutton;
    private Button subutton;
    private EditText text1;
    private EditText text2;
    private TextView id;
    private TextView psw;

    public TestClass() {
        super(MainActivity.class);
    }


    /*
     * 初始设置
     * @see junit.framework.TestCase#setUp()
     */
        @Override
        protected void setUp() throws Exception{

                super.setUp();

//            mLaunchIntent = new Intent(getInstrumentation().getTargetContext(), MainActivity.class);
//            startActivity(mLaunchIntent, null, null);
//            Intent intent1 = new Intent();
//            intent.setClassName("com.example.yzha502.movienight.HomeActivity.test", HomeActivity.class.getName());
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            sample = getActivity();

            psw = (TextView)sample.findViewById(R.id.PasswordTextView);
            id = (TextView) sample.findViewById(R.id.StudentIDTextView);
            text1 = (EditText) sample.findViewById(R.id.StudentIDText);
            text2 = (EditText) sample.findViewById(R.id.PasswordeditText);
            libutton = (Button) sample.findViewById(R.id.LoginButton);
            subutton = (Button) sample.findViewById(R.id.Signupbutton);
 //           searchButton = (Button) home.findViewById(R.id.searchButton);
        }


        public void testPreconditions() {
            assertNotNull("MainActivity is null", sample);
            assertNotNull("TextView is null", text1);
            assertNotNull("TextView is null", text2);
            assertNotNull("Button is null", libutton);
            assertNotNull("Button is null", subutton);
        }

        /**
         * We have a test method here to check that the value of the TextView on the activity is equal
         * to "Hello test!"
         */
        public void testTextView() {
            String expectedValue = "Hello test!";
            String actualValue = id.getText().toString();
            String pswString = psw.getText().toString();
            assertEquals("User Email", actualValue);
            assertEquals("Password", pswString);
        }

        /**
         * We have a test method here to check that the value of the activity Title is equal to
         * "Main Activity"
         */
        public void testActivityTitle() {
            //String expectedValue = "UnitTestExample";
            String actualValue = sample.getTitle().toString();
            assertEquals("MovieNight",actualValue);
        }
}
package org.pdm.ib.login;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.Telephony;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.pdm.ib.R;
import org.pdm.ib.auth.AuthenticationHolderConfig;
import org.pdm.ib.command.UserAuthCommand;
import org.pdm.ib.context.AuthenticationHolder;
import org.pdm.ib.home.activity.HomeActivity;
import org.pdm.ib.model.UserProfile;
import org.pdm.ib.retrofit.RetrofitAPIService;
import org.pdm.ib.service.AuthService;
import org.pdm.ib.service.impl.AuthServiceImpl;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {

    /**
     * Id to identity READ_CONTACTS permission request.
     */
    private static final int REQUEST_READ_CONTACTS = 0;

    private final AuthService authService = new AuthServiceImpl();

    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */
    /* private UserLoginTask mAuthTask = null;*/

    // UI references.
    private TextView mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the login form.
        mEmailView = (TextView) findViewById(R.id.email);
        mPasswordView = (EditText) findViewById(R.id.password);
        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
    }

    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin() {
        /*if (mAuthTask != null) {
            return;
        }*/

        // Reset errors.
        mEmailView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            showProgress(true);
            RetrofitAPIService retrofitAPIService = RetrofitAPIService.aRetrofitApiService();
            final Handler handler = new Handler();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    UserAuthCommand userAuthCommand = new UserAuthCommand(email, password);
                    UserProfile userProfile = retrofitAPIService.authorizeUser(userAuthCommand);
                    Runnable runnable = new Runnable() {
                        @Override
                        public void run() {
                            if (userProfile != null) {
                                AuthenticationHolderConfig.getAuthenticationHolder().setUserProfile(userProfile);
                                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(getApplicationContext(), R.string.error_incorrect_password, Toast.LENGTH_SHORT).show();
                                showProgress(false);
                            }
                        }
                    };
                    LoginActivity.this.runOnUiThread(runnable);
                }
            }).start();
        }
    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return true;
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() >= 3;
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
    public class UserLoginTask extends AsyncTask<Void, Void, Void> {

        private final String mEmail;
        private final String mPassword;

        UserLoginTask(String email, String password) {
            mEmail = email;
            mPassword = password;
        }

        @Override
        protected Void doInBackground(Void... params) {
            /*  if (authService.login(mEmail, mPassword)) {

            }*/
            return null;
        }

        /* @Override
         protected void onPostExecute(final Boolean success) {
            *//* mAuthTask = null;*//*
            showProgress(false);

            if (AuthenticationHolderConfig.getAuthenticationHolder().getAuthentication() != null) {
                openHomeActivity();
            } else {
               *//* mPasswordView.setError(getString(R.string.error_incorrect_password));
                mPasswordView.requestFocus();*//*
                Toast.makeText(getApplicationContext(), R.string.error_incorrect_password, Toast.LENGTH_SHORT).show();
            }
        }
*/
        @Override
        protected void onCancelled() {
            /*mAuthTask = null;*/
            showProgress(false);
        }

        private void openHomeActivity() {
            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);

            startActivity(intent);
        }
    }
}


package com.example.wimuuvapplication.Login;


import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;


import android.app.Activity;
import android.content.Intent;

import android.os.Bundle;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wimuuvapplication.R;
import com.example.wimuuvapplication.UI.ForgotPassword;
import com.example.wimuuvapplication.UI.MainActivity2;
import com.example.wimuuvapplication.UI.Register;
//import com.example.wimuuvapplication.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {
   private Button loginButton;
    private Button fgtpwButton;
    private LoginViewModel loginViewMod;
    //private ActivityMainBinding binding;
    private LoginViewModel loginViewModel;
    private EditText emailEditText;
    private EditText passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        loginViewModel = new ViewModelProvider(this, new LoginViewModelFactory())
                .get(LoginViewModel.class);

        final EditText emailEditText = binding.EmaileditText;
        final EditText passwordEditText = binding.editTextTextPassword;
        final Button loginButton = binding.loginBTN;

        loginViewModel.getLoginFormState().observe(this, new Observer<LoginFormState>() {
            @Override
            public void onChanged(@Nullable LoginFormState loginFormState) {
                if (loginFormState == null) {
                    return;
                }
                loginButton.setEnabled(loginFormState.isDataValid());
                if (loginFormState.getUsernameError() != null) {
                
                    emailEditText.setError(getString(loginFormState.getUsernameError()));
                }
                if (loginFormState.getPasswordError() != null) {
                    passwordEditText.setError(getString(loginFormState.getPasswordError()));
                }
            }
        });
        loginViewModel.getLoginResult().observe(this, new Observer<LoginResult>() {
            @Override
            public void onChanged(@Nullable LoginResult loginResult) {
                if (loginResult == null) {
                    return;
                }
                //loadingProgressBar.setVisibility(View.GONE);
                if (loginResult.getError() != null) {
                    showLoginFailed(loginResult.getError());
                }
                if (loginResult.getSuccess() != null) {
                    updateUiWithUser(loginResult.getSuccess());
                }
                setResult(Activity.RESULT_OK);

                //Complete and destroy login activity once successful
                finish();
            }
        });
        TextWatcher afterTextChangedListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // ignore
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // ignore
            }

            @Override
            public void afterTextChanged(Editable s) {
                loginViewModel.loginDataChanged(emailEditText.getText().toString(),
                        passwordEditText.getText().toString());
            }
        };
        emailEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    loginViewModel.login(emailEditText.getText().toString(),
                            passwordEditText.getText().toString());
                }
                return false;
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //loadingProgressBar.setVisibility(View.VISIBLE);
                loginViewModel.login(emailEditText.getText().toString(),
                        passwordEditText.getText().toString());

            }
        });


*/


    }
/*
    private void updateUiWithUser(LoggedInUserView model) {
        String welcome = getString(R.string.welcome) + model.getDisplayName();
        Intent intent = new Intent(MainActivity.this, MainActivity.class);
        startActivity(intent);
        Toast.makeText(getApplicationContext(), welcome, Toast.LENGTH_LONG).show();
    }

    private void showLoginFailed(@StringRes Integer errorString) {
        Intent intent = new Intent(MainActivity.this, MainActivity.class);
        startActivity(intent);
        Toast.makeText(getApplicationContext(), errorString, Toast.LENGTH_SHORT).show();
    }

 */



    public void onClickLogin(View v) {
        Intent intent = new Intent(getApplicationContext(), MainActivity2.class);


        startActivity(intent);
    }

    public void onClickfgtpw(View v) {
        Intent intent = new Intent(getApplicationContext(), ForgotPassword.class);


        startActivity(intent);
    }
    public void onClickSignup(View v) {
        Intent intent = new Intent(getApplicationContext(), Register.class);


        startActivity(intent);
    }


}
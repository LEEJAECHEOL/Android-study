package com.example.fireapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

    private final int RC_SIGN_IN = 123;

    private static final String TAG = "LoginActivity";

    private TextView tvBtnJoin;
    private TextInputEditText teEmail, tePassword;
    private MaterialButton btnLogin;
    private FirebaseAuth mAuth;
    private LinearLayout loginLayout;
    private Context mContext = LoginActivity.this;
    private SignInButton btnGoogleLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: " + mContext);
        Log.d(TAG, "onCreate: " + LoginActivity.this);
        setContentView(R.layout.activity_login);

        tvBtnJoin = findViewById(R.id.tvbtn_join);

        teEmail = findViewById(R.id.te_email);
        tePassword = findViewById(R.id.te_password);
        btnLogin = findViewById(R.id.btn_login);
        mAuth = FirebaseAuth.getInstance();
        loginLayout = findViewById(R.id.login_layout);
        btnGoogleLogin = findViewById(R.id.btn_google_login);

        tvBtnJoin.setOnClickListener(v -> {
            startActivity(new Intent(LoginActivity.this, JoinActivity.class));
        });

        btnLogin.setOnClickListener(v -> {
            String email = teEmail.getText().toString().trim(); // trim 앞뒤 공백제거
            String password = tePassword.getText().toString().trim();

            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, task -> {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();

                            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK); // 이전 액티비티를 스택에서 다 지움
                            startActivity(intent);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Snackbar.make(loginLayout, "로그인 실패", Snackbar.LENGTH_LONG).show();
                        }
                    });

        });

        btnGoogleLogin.setOnClickListener(v -> {
            // Choose authentication providers
            List<AuthUI.IdpConfig> providers = Arrays.asList(
                    new AuthUI.IdpConfig.GoogleBuilder().build());


            startActivityForResult(
                    AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .setAvailableProviders(providers)
                            .build(),
                    RC_SIGN_IN);
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);

            Log.d(TAG, "onActivityResult: " + response.getEmail());

            if (resultCode == RESULT_OK) {
                // Successfully signed in
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK); // 이전 액티비티를 스택에서 다 지움
                startActivity(intent);
                Snackbar.make(loginLayout, "로그인 실패", Snackbar.LENGTH_LONG).show();
            } else {
                // Sign in failed. If response is null the user canceled the
                // sign-in flow using the back button. Otherwise check
                // response.getError().getErrorCode() and handle the error.
                // ...
            }
        }
    }
}
package com.example.fireapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;

public class JoinActivity extends AppCompatActivity {

    private static final String TAG = "JoinActivity";

    private TextInputEditText teEmail, tePassword;
    private MaterialButton btnJoin;
    private FirebaseAuth mAuth;
    private LinearLayout joinLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        teEmail = findViewById(R.id.te_email);
        tePassword = findViewById(R.id.te_password);
        btnJoin = findViewById(R.id.btn_join);
        mAuth = FirebaseAuth.getInstance();
        joinLayout = findViewById(R.id.join_layout);

        btnJoin.setOnClickListener(v -> {
            String email = teEmail.getText().toString().trim(); // trim 앞뒤 공백제거
            String password = tePassword.getText().toString().trim();

            mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if(task.isSuccessful()){
                        FirebaseUser user = mAuth.getCurrentUser();
                        Log.d(TAG, "onCreate: user : " + user.getEmail());
                        Intent intent = new Intent(JoinActivity.this, HomeActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK); // 이전 액티비티를 스택에서 다 지움
                        startActivity(intent);
                    }else{
                        Log.d(TAG, "onCreate: Fail : " + task.getException());
                        Snackbar.make(joinLayout,"로그인 실패",Snackbar.LENGTH_LONG).show();
                    }
                });
        });

    }
}
package id.ac.umn.appspet3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView register;
    private EditText editemail, editPass;
    private Button signin;

    private FirebaseAuth mAuth;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        register = (TextView) findViewById(R.id.register);
        register.setOnClickListener(this);

        signin = (Button) findViewById(R.id.signin);
        signin.setOnClickListener(this);

        editemail = (EditText) findViewById(R.id.Email);
        editPass = (EditText) findViewById(R.id.Pass);

        progressBar = (ProgressBar) findViewById(R.id.progressbar);

        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.register:
                startActivity(new Intent(this, Register.class ));
                break;

            case R.id.signin:
                userLogin();
                break;
        }
    }

    private void userLogin(){
        String email = editemail.getText().toString().trim();
        String Pass = editPass.getText().toString().trim();

        if(email.isEmpty()){
            editemail.setError("Email is required!");
            editemail.requestFocus();
            return;
        }



        if(Pass.isEmpty()){
            editPass.setError("Password Required!");
            editPass.requestFocus();
            return;
        }


        if(Pass.length() < 6){
            editPass.setError("Password length should be 6 characters!");
            editPass.requestFocus();
            return;
        }

        mAuth.signInWithEmailAndPassword(email, Pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                    if(user.isEmailVerified()){
                        startActivity(new Intent(MainActivity.this, Home.class));
                    }else{
                        user.sendEmailVerification();
                        Toast.makeText(MainActivity.this, "Check your email to verify your account", Toast.LENGTH_LONG).show();
                    }

                }else{
                    Toast.makeText(MainActivity.this, "Fail to Login! Please check your credentials!", Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.GONE);
                }
            }
        });
    }
}
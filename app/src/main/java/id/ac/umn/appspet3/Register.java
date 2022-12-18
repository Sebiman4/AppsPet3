package id.ac.umn.appspet3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Pattern;

import io.grpc.LoadBalancer;

public class Register extends AppCompatActivity implements View.OnClickListener{

    private FirebaseAuth mAuth;
    private FirebaseDatabase database;
    private DatabaseReference reference;
    private TextView banner, register;
    private EditText editname, editemail, editPass, editCon_Pass;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        banner = (TextView) findViewById(R.id.banner);
        banner.setOnClickListener(this);

        register = (Button) findViewById(R.id.register);
        register.setOnClickListener(this);

        editname = (EditText) findViewById(R.id.name);
        editemail = (EditText) findViewById(R.id.email);
        editPass = (EditText) findViewById(R.id.Pass);
        editCon_Pass = (EditText) findViewById(R.id.Con_pass);

        progressBar = (ProgressBar) findViewById(R.id.progressbar);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.banner:
                startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.register:
                register();
                break;
        }
    }

    private void register(){

        database = FirebaseDatabase.getInstance();
        reference = database.getReference("Users");

        String nama= editname.getText().toString().trim();
        String mail= editemail.getText().toString().trim();
        String password= editPass.getText().toString().trim();
        String passwordCon= editCon_Pass.getText().toString().trim();
        

        if(nama.isEmpty()){
            editname.setError("Name Required!");
            editname.requestFocus();
            return;
        }

        if(mail.isEmpty()){
            editemail.setError("Email Required!");
            editemail.requestFocus();
            return;
        }

        if(password.isEmpty()){
            editPass.setError("Password Required!");
            editPass.requestFocus();
            return;
        }

        if(passwordCon.isEmpty()){
            editCon_Pass.setError("Confirm Password Required!");
            editCon_Pass.requestFocus();
            return;
        }

        if(password.length() < 6){
            editPass.setError("Password length should be 6 characters!");
            editPass.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(mail, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){
                            User user = new User(nama, mail, password, passwordCon);

                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {

                                            if(task.isSuccessful()){
                                                Toast.makeText(Register.this, "User has been registered!", Toast.LENGTH_LONG).show();
                                                progressBar.setVisibility(View.VISIBLE);

                                            }else{
                                                Toast.makeText(Register.this, "Fail to Register! Try Again!", Toast.LENGTH_LONG).show();
                                                progressBar.setVisibility(View.GONE);
                                            }
                                        }
                                    });
                        }else {
                            Toast.makeText(Register.this, "Fail to Register! Try Again!", Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
    }
}
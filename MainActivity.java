package com.example.foodorderapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.RoomDatabase;
import androidx.room.Room;
import androidx.room.TypeConverters;
import androidx.room.migration.Migration;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;
import android.widget.EditText;
import android.widget.Button;
import com.example.foodorderapp.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {
    private EditText loginEmailEditText, loginPasswordEditText;
    private EditText signupEmailEditText, signupPasswordEditText;
    private Button loginButton, signupButton;
    private UserDao userDao;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialize Room Database
        userDao = MyDatabase.getInstance(this).userDao();

       //Intialize views
        loginEmailEditText = findViewById(R.id.login_email);
        loginPasswordEditText = findViewById(R.id.login_password);
        signupEmailEditText = findViewById(R.id.signup_email);
        signupPasswordEditText = findViewById(R.id.signup_password);
        loginButton = findViewById(R.id.login_button);
        signupButton = findViewById(R.id.signup_button);

        //Set Click Listeners
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                performLogin();
            }
        });

        signupButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                performSignup();
            }
        });

    }

    private void performLogin() {

        String email = loginEmailEditText.getText().toString().trim();
        String password = loginPasswordEditText.getText().toString().trim();

        //Check if the user with the provided credentials exists in the database
        boolean loginSuccessful = userDao.login(email, password);

        if(loginSuccessful) {
            //Navigate to the next activity or perform any desired action after successful login
            Intent intent = new Intent(MainActivity.this, Foodorder.class);
            startActivity(intent);
            finish();
        } // end if
        else {
            //Display the error message or handle the unsuccessful login
            Toast.makeText(MainActivity.this, "Invalid Login credentials", Toast.LENGTH_SHORT).show();
        } // end else


    }

    private void performSignup() {
        String email = signupEmailEditText.getText().toString().trim();
        String password = signupPasswordEditText.getText().toString().trim();

        //Check if the user already exists
        if (!userDao.is_token(email)) {
            //If the user does not exist, proceed with signup
            UserTable newUser = new UserTable(0, email, password);
            userDao.insertUser(newUser);

            //Optionally, you can navigate to the login screen after signup
            loginEmailEditText.setText(email);
            loginPasswordEditText.setText(password);
            performLogin();
        }// end if

        else
        {
            //Display an error message indicating that user already exists
            Toast.makeText(MainActivity.this, "User already exists", Toast.LENGTH_SHORT).show();
        }// end else

    }

}
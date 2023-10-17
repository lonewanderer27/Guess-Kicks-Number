package com.kicks.guesskicksnumber;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class GKN_MainActivity extends AppCompatActivity {
    private Button GuessBtn;
    private Button ResetBtn;
    private EditText UserGuess;
    private TextView Attempts;
    private TextView CorrectGuess;
    private Integer correctGuess;
    private Integer attempts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gkn_activity_main);

        // Assign the variables to the UI elements
        GuessBtn = findViewById(R.id.GuessButton);
        ResetBtn = findViewById(R.id.ResetButton);
        UserGuess = findViewById(R.id.UserGuess);
        CorrectGuess = findViewById(R.id.CorrectGuess);
        Attempts = findViewById(R.id.Attempts);

        // Set the click listener for the Guess button
        GuessBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Guess();
            }
        });

        // Set the click listener for the Reset button
        ResetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Reset();
            }
        });

        // Generate a random number
        correctGuess = new Random().nextInt(100) + 1;
    }

    public void Guess() {
        // Get the user input
        String userGuessStr = UserGuess.getText().toString();
        Log.i("userGuessStr", userGuessStr);


        // Check if the user input is empty
        if (userGuessStr.isEmpty()) {
            // Display a toast
            UserGuess.setError("Please enter a valid number");
            return;
        }

        int userGuess = Integer.parseInt(userGuessStr);

        Log.i("correctGuess", correctGuess.toString());
        // Check if the user guess is correct
        if (userGuess == correctGuess) {
            // If the user guess is correct, display the correct guess
            CorrectGuess.setText("Correct Number: " + correctGuess.toString());

            // Display a toast
            Toast.makeText(this, "Correct guess!", Toast.LENGTH_LONG).show();

            // Disable the Guess button
            GuessBtn.setEnabled(false);

            // Disable the user guess EditText
            UserGuess.setEnabled(false);

            // Enable the Reset button
            ResetBtn.setEnabled(true);
        } else {
            // If the user guess is wrong, display a toast as a hint
            if (userGuess < correctGuess) {
                Toast.makeText(this, "Higher!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Lower!", Toast.LENGTH_SHORT).show();
            }
        }

        // Increase the number of attempts
        IncreaseAttempt();

        // Check if the user has reached the maximum number of attempts
        if (attempts == 100) {
            // Display the correct guess
            CorrectGuess.setText("Correct Number: " + correctGuess.toString());

            // Disable the Guess button
            GuessBtn.setEnabled(false);

            // Disable the user guess EditText
            UserGuess.setEnabled(false);

            // Enable the Reset button
            ResetBtn.setEnabled(true);
        }
    }

    public void IncreaseAttempt() {
        // Increase the number of attempts
        attempts = (attempts != null) ? attempts + 1 : 1;

        // Display the number of attempts
        Attempts.setText("Attempts: " + attempts);
    }

    public void Reset() {
        // Reset the number of attempts
        attempts = 0;
        Attempts.setText("Attempts:");

        // Generate a random number
        correctGuess = new Random().nextInt(100) + 1;
        CorrectGuess.setText("Correct Number:");

        // Clear the user guess EditText
        UserGuess.getText().clear();

        // Disable the Reset button
        ResetBtn.setEnabled(false);

        // Enable the Guess button
        GuessBtn.setEnabled(true);

        // Enable the user guess EditText
        UserGuess.setEnabled(true);
    }
}
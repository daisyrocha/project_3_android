package com.example.project3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.project3.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    // created an object of our database
    private ProjectDatabase db;

    /**
     * This is used for viewBinding, which is code that allows us to more
     * easily write code that interacts with views.
     *
     * REPLACES findViewById
      */
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // part of view binding
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        /**
         * This checks if there is an instance of the database, if not, it will create it.
         * If there is, it will return the instance of the database and assign it to db variable
         */
        db = ProjectDatabase.getInstance(this);
        db.populateInitialData();
    }
}
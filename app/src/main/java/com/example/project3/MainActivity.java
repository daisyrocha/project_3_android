package com.example.project3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.project3.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    // created an object of our database
    private ProjectDatabase db;
    private AppBarConfiguration appBarConfiguration;

    /**
     * This is used for viewBinding, which is code that allows us to more
     * easily write code that interacts with views.
     *
     * REPLACES findViewById
      */
    private ActivityMainBinding binding;
    private static final String USER_ID_KEY = "com.example.project3.useridKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // part of view binding
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        /**
         * This checks if there is an instance of the database, if not, it will create it.
         * If there is, it will return the instance of the database and assign it to db variable
         */
        db = ProjectDatabase.getInstance(this);
        db.populateInitialData();
    }

    public static Intent intentFactory(Context context, int userId){
        Intent intent = new Intent(context, Activity.class);
        intent.putExtra(USER_ID_KEY, userId);
        return intent;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}
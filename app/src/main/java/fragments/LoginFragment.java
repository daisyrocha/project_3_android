package fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.room.Room;

import com.example.project3.MainActivity;
import com.example.project3.ProjectDatabase;
import com.example.project3.R;
import com.example.project3.User;
import com.example.project3.UserDao;
import com.example.project3.databinding.FragmentLoginBinding;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class LoginFragment extends Fragment {
    private EditText _Username;
    private EditText _Password;
    SharedPreferences sp;   // Part of Shared preference implementation
    int user_id; // Shared Preference to send user ID to other page

    private UserDao _UserDAO;
    ProjectDatabase db;

    private static final int RC_SIGN_IN = 100;
    private GoogleSignInClient googleSignInClient;

    private FirebaseAuth firebaseAuth;

    private static final String TAG = "GOOGLE_SIGN_IN_TAG";


    private String _UserString;
    private String _PasswordString;

    private User _User;

    private FragmentLoginBinding binding;


    /**
     * We need to create this Override method to be able to create
     * onclick listeners for the buttons
     *
     * LayoutInflater creates a new view/layout object from one of our xml layouts
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        /**
         * Here we get an instance of the database - We get whatever is added to it.
         */
        db = ProjectDatabase.getInstance(this.getActivity());

        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        googleSignInClient = GoogleSignIn.getClient(getContext(),googleSignInOptions);

        firebaseAuth = FirebaseAuth.getInstance();

        binding.googleSignInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: begin Google SignIn");
                Intent intent = googleSignInClient.getSignInIntent();
                startActivityForResult(intent,RC_SIGN_IN);
                NavHostFragment.findNavController(LoginFragment.this)
                        .navigate(R.id.LoginFragment_to_LandingFragment);
            }
        });

        return binding.getRoot();

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == RC_SIGN_IN){
            Log.d(TAG, "onActivityResult: Google Sign-in intent result");
            Task<GoogleSignInAccount> accountTask = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = accountTask.getResult(ApiException.class);
            }
            catch (Exception e){
                Log.d(TAG, "onActivityResult: " + e.getMessage());
            }

        }
    }
    
    private void firebaseAuthWithGoogleAccount(GoogleSignInAccount account){
        Log.d(TAG, "firebaseAuthWithGoogleAccount: begin firebase auth with google account");
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        firebaseAuth.signInWithCredential(credential)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        Log.d(TAG, "onSuccess: Logged In");

                        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

                        String uid= firebaseUser.getUid();
                        String email = firebaseUser.getEmail();

                        Log.d(TAG, "onSuccess: Email: " +email);
                        Log.d(TAG, "onSuccess: UID: " +uid);

                        if(authResult.getAdditionalUserInfo().isNewUser()){
                            Log.d(TAG, "onSuccess: Account Created...\n" +email);
                            Toast.makeText(getContext(), "Account Created...\n" +email, Toast.LENGTH_LONG).show();
                        }
                        else{
                            Log.d(TAG, "onSuccess: Existing user...\n" +email);
                            Toast.makeText(getContext(),"Existing User...\n"+email, Toast.LENGTH_LONG).show();
                        }

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d(TAG, "onFailure: ");
                    }
                });
    }

    private Boolean LoginDisplay() {
        /**
         * Will get us the Activity the fragment is currently associated with
          */
        getActivity();

        /**
         * _Username and _Password is the text we get from the user input
         * in the EditText boxes.
         *
         * getView() displays the data at the specified position of the dataset,
         * in this case, we display the data in the username edit text and
         * the password edit text, we then store it as _Username and _Password.
         */
        _Username = getView().findViewById(R.id.username);
        _Password = getView().findViewById(R.id.password);

        _UserString = _Username.getText().toString();
        _PasswordString = _Password.getText().toString();


        if (checkForUserDB()) {
            /**
             * We checked if the username existed in the db in the checkForUser() function.
             * If checkForUser returns true, then we compare the passwords to make sure they match
             */
            if (!_User.getPassword().equals(_PasswordString)) {
                Toast.makeText(getContext(), "Invalid Password", Toast.LENGTH_SHORT).show();
                return false;
            } else {
                return true;
            }
        }
        return false;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(LoginFragment.this)
                        .navigate(R.id.LoginFragment_to_RegisterFragment);

            }
        });

        /**
         * When button is clicked, we will go into the following functions
         * loginBtn onClickListener -> LoginDisplay -> checkForUserDB then back
         * checkForUserDB -> LoginDisplay -> loginBtn onClickListener
         */
        binding.loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(LoginDisplay()) {

                    /**
                     * 1 of 2 Shared Preference Implementation. From here, we go to
                     * LandingFragment
                     */
                    sp = getActivity().getSharedPreferences("MyUserPrefs", Context.MODE_PRIVATE);   // Context.MODE_PRIVATE == 0 (either works)
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("username", _UserString);
                    editor.putInt("user_id", whichUser(_UserString));
                    editor.commit();

                    NavHostFragment.findNavController(LoginFragment.this)
                            .navigate(R.id.LoginFragment_to_LandingFragment);


                }
            }
        });
    }

    /**
     * If the username is found in the database, return true, otherwise, return false.
     */
    private boolean checkForUserDB(){
        getDatabase();
        _User = _UserDAO.getUserByUsername(_UserString);
        if(_User == null){
            Toast.makeText(getContext(), "No User " + _UserString + " found ", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public static  Intent intentFactory(Context context){
        Intent intent = new Intent(context, LoginFragment.class);
        return intent;
    }

    private void getDatabase() {
        _UserDAO = Room.databaseBuilder(getActivity(), ProjectDatabase.class, "HeroBrawl.db")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
                .getUserDao();
    }

    private int whichUser(String username) {
        return _UserDAO.getUserId(_UserString);
    }
}

package fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.room.Room;

import com.example.project3.ProjectDatabase;
import com.example.project3.R;
import com.example.project3.User;
import com.example.project3.UserDao;
import com.example.project3.databinding.FragmentRegisterBinding;

public class RegisterFragment extends Fragment {

    private EditText _username;
    private EditText _password;
    private EditText _passwordConfirmation;
    private UserDao _userDAO;
    private String _usernameString;
    private String _passwordString;
    private String _passwordConfirmationString;
    private User _user;

    ProjectDatabase db;

    private FragmentRegisterBinding binding;

    /**
     * We need to create this Override method to be able to create
     * onclick listeners for the buttons
     *
     * LayoutInflater creates a new view/layout object from one of our xml layouts
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentRegisterBinding.inflate(inflater, container, false);
        /**
         * Here we get an instance of the database - We get whatever is added to it.
         */
        db = ProjectDatabase.getInstance(this.getActivity());
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkForUserDB()) {
                    if(matchingPasswords()) {
                        User newUser = new User(_usernameString, _passwordConfirmationString);
                        _userDAO.addUser(newUser);
                        NavHostFragment.findNavController(RegisterFragment.this)
                                .navigate(R.id.RegisterFragment_to_LoginFragment);
                    }
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


    /**
     * If the username is found in the database, return true, otherwise, return false.
     */
    private boolean checkForUserDB(){
        _username = getView().findViewById(R.id.username);

        // turned into String variables
        _usernameString = _username.getText().toString();

        getDatabase();
        _user = _userDAO.getUserByUsername(_usernameString);
        if(_user != null){
            Toast.makeText(getContext(), "Username unavailable", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    /**
     * This function checks both text entries in the password field
     * if they match, then we can continue, otherwise, user sees toast
     * indicating password mismatch
     */
    private boolean matchingPasswords() {
        _password = getView().findViewById(R.id.password);
        _passwordConfirmation = getView().findViewById(R.id.password_confirmation);

        _passwordString = _password.getText().toString();
        _passwordConfirmationString = _passwordConfirmation.getText().toString();

        if(_passwordConfirmationString.equals(_passwordString)) {
            return true;
        }
        Toast.makeText(getContext(), "Password mismatch", Toast.LENGTH_SHORT).show();
        return false;
    }

    private void getDatabase() {
        _userDAO = Room.databaseBuilder(getActivity(), ProjectDatabase.class, "HeroBrawl.db")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
                .getUserDao();
    }

}

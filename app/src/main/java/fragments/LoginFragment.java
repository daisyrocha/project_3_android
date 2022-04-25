package fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.project3.MainActivity;
import com.example.project3.ProjectDatabase;
import com.example.project3.R;
import com.example.project3.User;
import com.example.project3.UserDao;
import com.example.project3.databinding.FragmentLoginBinding;

public class LoginFragment extends Fragment {
    private EditText _Username;
    private EditText _Password;
    private Button _Button;

    private UserDao _UserDAO;


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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    private Boolean LoginDisplay() {

        /**
         * _Username and _Password is the text we get from the user input
         * in the EditText boxes.
         *
         * _Button is a reference to the login button
         */
        _Username = _Username.findViewById(R.id.username);
        _Password = _Password.findViewById(R.id.password);

        _UserString = (String) _Username.getText().toString();
        _PasswordString = (String) _Password.getText().toString();

        if (checkForUserDB()) {
            /**
             * Here I added the ! in front of _User.getPassword().... because we want to check that
             * the passwords don't match.
             * If the passwords don't match, then we would display the
             * "Invalid Password" toast
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
         * this definitely works, but we need to find a way to verify the username and
         * password entered are in our system.
         */
        binding.loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if(LoginDisplay()) {
                    NavHostFragment.findNavController(LoginFragment.this)
                            .navigate(R.id.LoginFragment_to_LandingFragment);
//                }
            }

        });
    }

    /**
     * If the username is found in the database,
     */
    private boolean checkForUserDB(){
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
}

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

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ){
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    private void LoginDisplay(){
        _Username = _Username.findViewById(R.id.username);
        _Password = _Password.findViewById(R.id.password);

        _Button = _Button.findViewById(R.id.loginBtn);
        _Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _UserString = _Username.getText().toString();
                _PasswordString = _Password.getText().toString();
                if(checkForUserDB()){
                    if(_User.getPassword().equals(_PasswordString)){
                        Toast.makeText(getContext(), "Invalid Password", Toast.LENGTH_SHORT).show();
                    } else{
                        Intent intent = MainActivity.intentFactory(getContext(), _User.getUserId());
                        startActivity(intent);
                    }
                }
            }
        });
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(LoginFragment.this)
                        .navigate(R.id.LoginFragment_to_RegisterFragment);
            }
        });
    }

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

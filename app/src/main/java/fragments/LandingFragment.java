package fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.room.Room;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project3.ProjectDatabase;
import com.example.project3.R;
import com.example.project3.RetrofitUser;
import com.example.project3.User;
import com.example.project3.UserApi;
import com.example.project3.UserDao;
import com.example.project3.UsersJson;
import com.example.project3.databinding.FragmentLandingBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LandingFragment extends Fragment {

    ProjectDatabase db;
    private UserDao _UserDAO;
    private User _User;
    int user_id; // used to keep track of the user that is logged in

    private FragmentLandingBinding binding;
    SharedPreferences sp;

    public LandingFragment(){
        //empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sp = getContext().getSharedPreferences("MyUserPrefs", Context.MODE_PRIVATE);

    }

    /**
     * We need to create this Override method to be able to create
     * onclick listeners for the buttons
     *
     * LayoutInflater creates a new view/layout object from one of our xml layouts
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentLandingBinding.inflate(inflater, container, false);
        /**
         * Here we get an instance of the database - We get whatever is added to it.
         */
        db = ProjectDatabase.getInstance(this.getActivity());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView textview = (TextView) getView().findViewById(R.id.welcomeUser);
        // was here

        /**
         * 2 of 2 Shared Preference Implementation. We are able to obtain the username and have it displayed
         * as a welcome message to the user.
         */
        String username = sp.getString("username", "");
        user_id = sp.getInt("user_id", 0);
        textview.setText("Welcome, " + username + "!");
        Toast.makeText(getContext(), "The ID is: " + user_id, Toast.LENGTH_SHORT).show();

        /**
         * OnClickListener that will take user to a page where they can view
         * all Teams
         */
        binding.allTeams.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(LandingFragment.this)
                        .navigate(R.id.LandingFragment_to_viewTeamsFragment);
            }
        });


        /**
         * OnClickListener that will take user to a page where they can search
         * for heroes
         */
        binding.heroSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(LandingFragment.this)
                        .navigate(R.id.LandingFragment_to_searchHeroesFragment);
            }
        });


        /**
         * OnClickListener that will take user to a page where they can
         * view their account
         */
        binding.userAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                UserApi userApi = RetrofitUser.getUserRetrofitInstance().create(UserApi.class);
//                Call<List<User>> call = userApi.getAllUsers();
//
//                call.enqueue(new Callback<List<User>>() {
//                    @Override
//                    public void onResponse(Call<List<User>> call, Response<List<User>> response) {
//                        response.
//                    }
//
//                    @Override
//                    public void onFailure(Call<List<User>> call, Throwable t) {
//
//                    }
//                });
                NavHostFragment.findNavController(LandingFragment.this)
                        .navigate(R.id.LandingFragment_to_accountViewFragment);
            }
        });

    }

}
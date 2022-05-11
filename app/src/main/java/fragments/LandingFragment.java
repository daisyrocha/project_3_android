package fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.project3.ProjectDatabase;
import com.example.project3.R;
import com.example.project3.User;
import com.example.project3.UserDao;
import com.example.project3.databinding.FragmentLandingBinding;


public class LandingFragment extends Fragment {

    ProjectDatabase db;
    private UserDao _UserDAO;
    private User _User;

    private FragmentLandingBinding binding;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
        textview.setText("HI!");
        /**
         * Here we should send data from the login fragment to this landing fragment
         * to be able to display the username instead of the "HI!"
         *
         * I tried using an Intent, but for some reason, it won't pick up the data that
         * was sent
         */


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
                NavHostFragment.findNavController(LandingFragment.this)
                        .navigate(R.id.LandingFragment_to_accountViewFragment);
            }
        });

    }
}
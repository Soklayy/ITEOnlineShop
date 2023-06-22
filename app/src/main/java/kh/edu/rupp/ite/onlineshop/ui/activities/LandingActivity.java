package kh.edu.rupp.ite.onlineshop.ui.activities;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationBarMenu;

import kh.edu.rupp.ite.onlineshop.ui.fragment.HomeFragment;
import kh.edu.rupp.ite.onlineshop.ui.fragment.MoreFragment;
import kh.edu.rupp.ite.onlineshop.ui.fragment.ProductFragment;
import kh.edu.rupp.ite.onlineshop.R;
import kh.edu.rupp.ite.onlineshop.databinding.ActivityLandingBinding;
import kh.edu.rupp.ite.onlineshop.ui.fragment.ProfileFragment;

public class LandingActivity extends AppCompatActivity {

    private ActivityLandingBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLandingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        showFragment(new HomeFragment());
        binding.bottomNavigation.setOnItemSelectedListener(item -> {

           switch (item.getItemId()){
               case R.id.action_product:
                   binding.title.setText("Product");
                   showFragment(new ProductFragment());
                   break;
               case R.id.action_profile:
                   binding.title.setText("Profile");
                   showFragment(new ProfileFragment());
                   break;
               case R.id.action_more:
                   binding.title.setText("More");
                   showFragment(new MoreFragment());
                   break;
               default:
                   binding.title.setText("Home");
                   showFragment(new HomeFragment());
           }

           return true;
        });
    }

    private void showFragment(Fragment fragment){
        // FragmentManager
        FragmentManager fragmentManager = getSupportFragmentManager();

        // FragmentTransaction
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        // Replace fragment in lytFragment
        fragmentTransaction.replace(R.id.lyFragment, fragment);

        // Commit transaction
        fragmentTransaction.commit();
    }
}

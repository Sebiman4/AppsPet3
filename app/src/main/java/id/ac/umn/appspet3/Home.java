package id.ac.umn.appspet3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.database.FirebaseDatabase;

public class Home extends AppCompatActivity {


    RecyclerView recyclerView;
    HomeAdapter homeAdapter;
    BottomNavigationView bottomNavigationView;

    HomeFragment homeFragment = new HomeFragment();
    MonitorFragment monitorFragment = new MonitorFragment();
    SettingFragment settingFragment = new SettingFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        getSupportFragmentManager().beginTransaction().replace(R.id.container, homeFragment).commit();

        recyclerView = (RecyclerView)findViewById(R.id.listhewan);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<HomeModel> options =
                new FirebaseRecyclerOptions.Builder<HomeModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Pet"), HomeModel.class)
                                .build();

        homeAdapter = new HomeAdapter(options);
        recyclerView.setAdapter(homeAdapter);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.navigation_home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, homeFragment).commit();
                        return true;
                    case R.id.navigation_monitor:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, monitorFragment).commit();
                        return true;
                    case R.id.navigation_setting:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, settingFragment).commit();
                        return true;
                }
                return false;
            }
        });

        FloatingActionButton floatingActionButton = findViewById(R.id.btn);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Home.this,AddPet.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart(){
        super.onStart();
        homeAdapter.startListening();
    }

    @Override
    protected void onStop(){
        super.onStop();
        homeAdapter.startListening();
    }
}
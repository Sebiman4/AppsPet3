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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        recyclerView = (RecyclerView)findViewById(R.id.listhewan);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<HomeModel> options =
                new FirebaseRecyclerOptions.Builder<HomeModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Pet"), HomeModel.class)
                                .build();

        homeAdapter = new HomeAdapter(options);
        recyclerView.setAdapter(homeAdapter);


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
     @Override
     public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
                case R.id.Profile:
                    startActivity(new Intent(Home.this,logout.class));
                    return true;
                case R.id.OM_logout:
                    startActivity(new Intent(Home.this,logout.class));
                    return true;
        }
     }
}

package id.ac.umn.appspet3;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class CardView extends AppCompatActivity {
    androidx.cardview.widget.CardView cardView;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cardview);

        cardView = findViewById(R.id.card_view)
    }


}

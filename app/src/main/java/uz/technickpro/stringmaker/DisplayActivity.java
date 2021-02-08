package uz.technickpro.stringmaker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DisplayActivity extends AppCompatActivity {

    private List<Verb> verbs;
    private GridView gridView;
    private DbHelper db;
    private VerbAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);


        gridView = findViewById(R.id.grid_view);
        db = new DbHelper(this);
        verbs = db.verbs();
        adapter = new VerbAdapter(this, verbs);
        gridView.setAdapter(adapter);



    }
}
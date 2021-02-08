package uz.technickpro.stringmaker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText editAdd, edtDisplay;
    private Button btnAdd;
    ImageButton btnCopy, btnList, btnChange;

    private DbHelper db;
    private SimpleDbHelper simpleDbHelper;
    private List<Verb> verbs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editAdd = findViewById(R.id.main_string_edt);
        edtDisplay = findViewById(R.id.main_result_edt);
        btnAdd = findViewById(R.id.main_add_btn);
        btnCopy = findViewById(R.id.main_copy_btn);
        btnList = findViewById(R.id.main_list_btn);
        btnChange = findViewById(R.id.main_change_btn);

        db = new DbHelper(this);
        verbs = db.verbs();
        simpleDbHelper = new SimpleDbHelper(this);

        edtDisplay.setText(simpleDbHelper.getData());


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String add = editAdd.getText().toString();

                Verb verb = new Verb(add, "", "", "");
                String result = "<string name=\"" + add.toLowerCase() + "\">" + add + "</string>\n";

                db.insertVerb(verb);
                simpleDbHelper.insertString(result);
                simpleDbHelper.insertArray(add);
                String display = simpleDbHelper.getData();
                edtDisplay.setText(display);
                editAdd.getText().clear();
            }
        });

        btnCopy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String display = edtDisplay.getText().toString();

                ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("EditText", display);
                clipboardManager.setPrimaryClip(clip);

                Toast.makeText(MainActivity.this, "Copied", Toast.LENGTH_SHORT).show();
            }
        });

        btnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, DisplayActivity.class));
            }
        });


        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String array = simpleDbHelper.getArray();
                edtDisplay.setText(array);
            }
        });

    }
}
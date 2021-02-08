package uz.technickpro.stringmaker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class VerbAdapter extends BaseAdapter {

    private Context context;
    private List<Verb> verbs;

    public VerbAdapter(Context context, List<Verb> verbs) {
        this.context = context;
        this.verbs = verbs;
    }

    @Override
    public int getCount() {
        return verbs.size();
    }

    @Override
    public Object getItem(int position) {
        return verbs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = LayoutInflater.from(context).inflate(R.layout.item_verb, parent, false);

        TextView word = convertView.findViewById(R.id.display_word_txt);
        TextView v1 = convertView.findViewById(R.id.display_v1_txt);
        TextView v2 = convertView.findViewById(R.id.display_v2_txt);
        TextView v3 = convertView.findViewById(R.id.display_v3_txt);
        Button button = convertView.findViewById(R.id.display_add_btn);

        EditText edtV1 = convertView.findViewById(R.id.display_v1_edt);
        EditText edtV2 = convertView.findViewById(R.id.display_v2_edt);
        EditText edtV3 = convertView.findViewById(R.id.display_v3_edt);


        edtV1.setText(verbs.get(position).getWord().toLowerCase());
        edtV2.setText(verbs.get(position).getV2());
        edtV3.setText(verbs.get(position).getV3());

        word.setText(verbs.get(position).getWord());
        v1.setText(verbs.get(position).getV1());
        v2.setText(verbs.get(position).getV2());
        v3.setText(verbs.get(position).getV3());


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DbHelper db = new DbHelper(context);
                SimpleDbHelper simpleDbHelper = new SimpleDbHelper(context);

                String strArr = "verbs.add(new Verb(getResources().getString(R.string."
                        + verbs.get(position).getWord().toLowerCase() + ")), \"" + edtV1.getText().toString()
                        + "\", \"" + edtV2.getText().toString() + " \", " + "\"" + edtV3.getText().toString() + "\");\n";


                String s = verbs.get(position).getId() + "";
                long l = Long.parseLong(s);

                db.updateVerb(l, word.getText().toString(), edtV1.getText().toString(), edtV2.getText().toString(), edtV3.getText().toString());
                simpleDbHelper.updateArr(l, strArr);
                Toast.makeText(context, verbs.get(position).getWord() + " was added", Toast.LENGTH_SHORT).show();
            }
        });

        return convertView;
    }
}

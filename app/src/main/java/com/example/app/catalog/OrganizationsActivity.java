package com.example.app.catalog;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import java.io.IOException;

public class OrganizationsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organizations);

        int category_id = 0;

        Bundle extras = getIntent().getExtras();
        if (extras != null)
            category_id = extras.getInt("category_id");

        DataBaseHelper DbHelper = new DataBaseHelper(this);
        try {
            DbHelper.createDataBase();
        } catch (IOException ioe) {
            throw new Error("Unable to create database");
        }

        DbHelper.openDataBase();
        String res = "";
        TableLayout tableLayout = (TableLayout) findViewById(R.id.tableLayout);
        tableLayout.setColumnShrinkable(0, true);
        tableLayout.setColumnShrinkable(1, true);
        //tableLayout.setShrinkAllColumns(true);
        Cursor cursor2 = DbHelper.myDataBase.rawQuery("SELECT * FROM organizations WHERE category_id = " + category_id, null);
        while (cursor2.moveToNext()) {
            TableRow tableRow = new TableRow(this);

            String fields[] = {"name", "address", "telephone"};
            for (String s : fields) {
                TextView textView = new TextView(this);
                textView.setText(cursor2.getString(cursor2.getColumnIndex(s)));
                tableRow.addView(textView);
            }
            tableLayout.addView(tableRow);
        }
        cursor2.close();
        res += DbHelper.myDataBase.getPath();
        res += DbHelper.myDataBase.getVersion();
        DbHelper.close();
    }
}

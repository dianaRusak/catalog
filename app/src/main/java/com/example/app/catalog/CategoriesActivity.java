package com.example.app.catalog;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;

public class CategoriesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        int parent_id = 0;

        Bundle extras = getIntent().getExtras();
        if (extras != null)
            parent_id = extras.getInt("category_id");

        DataBaseHelper DbHelper = new DataBaseHelper(this);
        try {
            DbHelper.createDataBase();
        } catch (IOException ioe) {
            throw new Error("Unable to create database");
        }

        DbHelper.openDataBase();
        String res = "";
        ListView listView = (ListView) findViewById(R.id.listView);

        class Category {
            int _id, childCount;
            String name;
            public Category(int _id, String name, int childCount) {
                this._id = _id;
                this.name = name;
                this.childCount = childCount;
            }
            public String toString() {
                return name;
            }
        }

        ArrayList<Category> ar = new ArrayList<Category>();
        String s = "";
        Cursor cursor2 = DbHelper.myDataBase.rawQuery("SELECT _id, name FROM categories WHERE parent_id = " + parent_id, null);
        while (cursor2.moveToNext()) {
            int id = cursor2.getInt(cursor2.getColumnIndex("_id"));
            ar.add(new Category(
                    id,
                    cursor2.getString(cursor2.getColumnIndex("name")),
                    DbHelper.myDataBase.rawQuery("SELECT * FROM categories WHERE parent_id = " + id, null).getCount()
            ));
        }
        Category[] categories = new Category[ar.size()];
        categories = ar.toArray(categories);
        ArrayAdapter<Category> itemsAdapter = new ArrayAdapter<Category>(
                this, android.R.layout.simple_list_item_1, categories);
        listView.setAdapter(itemsAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long arg) {
                Category cat = (Category)adapter.getItemAtPosition(position);
                Intent i = new Intent(CategoriesActivity.this,
                        cat.childCount == 0 ? OrganizationsActivity.class : CategoriesActivity.class);
                i.putExtra("category_id", cat._id);
                startActivity(i);
            }
        });
        cursor2.close();
        DbHelper.close();
    }
}

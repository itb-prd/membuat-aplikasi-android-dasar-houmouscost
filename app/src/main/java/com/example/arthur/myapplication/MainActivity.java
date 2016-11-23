package com.example.arthur.myapplication;

import android.content.DialogInterface;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static android.R.id.edit;
import static android.view.View.GONE;

public class MainActivity extends AppCompatActivity {
    private ArrayList<String> items;
    private ArrayAdapter<String> itemsAdapter;
    private ListView lvItems;

    public void onAddItem() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Tambah tugas");
        builder.setMessage("Mau mengerjakan apa?");
        final EditText inputField = new EditText(this);
        builder.setView(inputField);
        builder.setPositiveButton("Tambah", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String itemText = inputField.getText().toString();
                if (!itemText.equals("")) {
                itemsAdapter.add(itemText); }
                inputField.setText("");
                writeItems();
            }
        });

        builder.setNegativeButton("Batal", null);

        builder.create().show();
    }

    private void readItems() {
        File filesDir = getFilesDir();
        File todoFile = new File(filesDir, "todo.txt");
        try {
            items = new ArrayList<String>(FileUtils.readLines(todoFile));
        } catch (IOException e) {
            items = new ArrayList<String>();
        }
    }

    private void writeItems() {
        File filesDir = getFilesDir();
        File todoFile = new File(filesDir, "todo.txt");
        try {
            FileUtils.writeLines(todoFile, items);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // ADD HERE
        lvItems = (ListView) findViewById(R.id.lvItems);
        items = new ArrayList<String>();
        readItems(); // <---- Add this line
        itemsAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, items);
        lvItems.setAdapter(itemsAdapter);
        // ... rest of existing code
        // Setup remove listener method call
        setupListViewListener();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onAddItem();

            }
        });
    }

    private void setupListViewListener() {
        lvItems.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener()
        {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapter, View item, final int pos, long id) {
                AlertDialog.Builder editor = new AlertDialog.Builder(MainActivity.this);
                editor.setTitle("Update pekerjaan");
                String now = items.get(pos).toString();
                editor.setMessage("Mau diganti jadi apa?");
                final EditText inputField = new EditText(MainActivity.this);
                editor.setView(inputField);
                inputField.setText(now);
                editor.setPositiveButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String itemText = inputField.getText().toString();
                        if (!itemText.equals("")) {
                        items.set(pos,itemText); }
                        inputField.setText("");
                        itemsAdapter.notifyDataSetChanged();
                        writeItems();
                    }
                });
                editor.setNegativeButton("Hapus tugas", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Remove the item within array at position
                        items.remove(pos);
                        // Refresh the adapter
                        itemsAdapter.notifyDataSetChanged();
                        // Return true consumes the long click event (marks it handled)
                        writeItems();
                    }
                });
                editor.create().show();
                return true;
            }


        });


    }
}
package com.mccutech.journalpro;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

/**
 * Created by Edwards on 6/29/2018.
 */
public class EditActivity extends AppCompatActivity implements View.OnClickListener {


    private SQLiteDBHelper dbHelper ;
    EditText titleEditText;
    EditText desEditText;

    RelativeLayout buttonLayout;
    FloatingActionButton fabSave, fabEdit, fabDelete;

    int diaryID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        diaryID = getIntent().getIntExtra(MainActivity.KEY_EXTRA_CONTACT_ID, 0);

        setContentView(R.layout.activity_edit);
        titleEditText = (EditText) findViewById(R.id.editTextTitle);
        desEditText = (EditText) findViewById(R.id.editTextDescription);
        fabSave = (FloatingActionButton) findViewById(R.id.saveButton);
        fabSave.setOnClickListener(this);
        buttonLayout = (RelativeLayout) findViewById(R.id.buttonLayout);
        fabEdit = (FloatingActionButton) findViewById(R.id.editButton);
        fabEdit.setOnClickListener(this);
        fabDelete = (FloatingActionButton) findViewById(R.id.deleteButton);
        fabDelete.setOnClickListener(this);

        dbHelper = new SQLiteDBHelper(this);

        if(diaryID > 0) {
            fabSave.setVisibility(View.GONE);
            buttonLayout.setVisibility(View.VISIBLE);

            Cursor rs = dbHelper.getData(diaryID);
            rs.moveToFirst();
            String personName = rs.getString(rs.getColumnIndex(SQLiteDBHelper.DIARY_COLUMN_TITLE));
            String personGender = rs.getString(rs.getColumnIndex(SQLiteDBHelper.DIARY_COLUMN_DES));
            if (!rs.isClosed()) {
                rs.close();
            }

            titleEditText.setText(personName);
            titleEditText.setFocusable(false);
            titleEditText.setClickable(false);

            desEditText.setText(personGender);
            desEditText.setFocusable(false);
            desEditText.setClickable(false);

        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.saveButton:
               /* if (TextUtils.isEmpty((CharSequence) titleEditText)) {
                    Toast.makeText(getApplicationContext(), "Please Enter Title", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty((CharSequence) desEditText)) {
                    Toast.makeText(getApplicationContext(), "Please Write Description", Toast.LENGTH_SHORT).show();
                    return;
                }
                else {*/
                    persistData();
                    return;
               // }
            case R.id.editButton:
                fabSave.setVisibility(View.VISIBLE);
                buttonLayout.setVisibility(View.GONE);
                titleEditText.setEnabled(true);
                titleEditText.setFocusableInTouchMode(true);
                titleEditText.setClickable(true);

                desEditText.setEnabled(true);
                desEditText.setFocusableInTouchMode(true);
                desEditText.setClickable(true);

                return;
            case R.id.deleteButton:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage(R.string.deletePerson)
                        .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dbHelper.deleteData(diaryID);
                                Toast.makeText(getApplicationContext(), "Deleted Successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // User cancelled the dialog
                            }
                        });
                AlertDialog d = builder.create();
                d.setTitle("Delete Entry?");
                d.show();
                return;
        }
    }

    public void persistData() {
        if(diaryID > 0) {
            if(dbHelper.updateData(diaryID, titleEditText.getText().toString(),
                    desEditText.getText().toString())) {
                Toast.makeText(getApplicationContext(), "Update Successful", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
            else {
                Toast.makeText(getApplicationContext(), "Update Failed", Toast.LENGTH_SHORT).show();
            }
        }
        else {
            if(dbHelper.insertData(titleEditText.getText().toString(),
                    desEditText.getText().toString())) {
                Toast.makeText(getApplicationContext(), "Entry Saved", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(getApplicationContext(), "Could not save!!!", Toast.LENGTH_SHORT).show();
            }
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
    }
}

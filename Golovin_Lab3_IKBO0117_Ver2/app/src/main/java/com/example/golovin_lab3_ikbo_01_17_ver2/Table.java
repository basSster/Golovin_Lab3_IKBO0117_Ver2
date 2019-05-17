package com.example.golovin_lab3_ikbo_01_17_ver2;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class Table extends AppCompatActivity {

    TableLayout table;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (MainActivity.db.getVersion() == 1) {
            setContentView(R.layout.activity_table_first_version);
        }
        else {
            setContentView(R.layout.activity_table);
        }
        TableRow.LayoutParams trParam = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT);
        trParam.weight = 1;
        table = findViewById(R.id.tableBD);

        if (MainActivity.db.getVersion() > 1)
        {
            Cursor curs = MainActivity.db.query("Student", new String[]{"id,family,name,secondName,Date"}, null, null, null, null, null);
            if (curs.getCount() != 0) {
                curs.moveToFirst();
                do {
                    TableRow tr = new TableRow(this);
                    tr.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                            TableLayout.LayoutParams.WRAP_CONTENT));

                    TextView viewID = new TextView(this);
                    TextView viewFamily = new TextView(this);
                    TextView viewName = new TextView(this);
                    TextView viewSecondName = new TextView(this);
                    TextView viewDate = new TextView(this);
                    TextView view1[] = {viewID, viewFamily, viewName, viewSecondName, viewDate};
                    for (int i = 0; i < view1.length; i++) {
                        view1[i].setLayoutParams(trParam);
                        view1[i].setGravity(Gravity.CENTER);
                        view1[i].setText(curs.getString(i));
                    }
                    for (int j = 0; j < view1.length; j++)
                        tr.addView(view1[j]);
                    table.addView(tr);


                } while (curs.moveToNext());

                curs.close();
            }
        }
        if (MainActivity.db.getVersion() == 1)
        {
            Cursor curs = MainActivity.db.query("Student",new String[] {"ID, FIO, DATE"},null,null,null,null,null,null);
            if (curs.getCount() !=0) {
                curs.moveToFirst();

                do {
                    int FIO = curs.getColumnIndex("FIO");
                    int ID = curs.getColumnIndex("id");
                    int DATE = curs.getColumnIndex("Date");
                    TableRow tr = new TableRow(this);
                    tr.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                            TableLayout.LayoutParams.WRAP_CONTENT));
                    TextView viewFamily = new TextView(this);
                    TextView viewName = new TextView(this);
                    TextView viewSecondName = new TextView(this);
                    TextView view1[] = {viewFamily, viewName, viewSecondName};
                    for (int j = 0; j < view1.length; j++) {
                        view1[j].setLayoutParams(trParam);
                        view1[j].setGravity(Gravity.CENTER);
                    }
                    view1[0].setText(curs.getString(ID));
                    view1[1].setText(curs.getString(FIO));
                    view1[2].setText(curs.getString(DATE));
                    for (int c = 0; c < view1.length; c++) {
                        tr.addView(view1[c]);
                    }
                    table.addView(tr);
                }
                while (curs.moveToNext());
            }

            curs.close();
        }
    }
}

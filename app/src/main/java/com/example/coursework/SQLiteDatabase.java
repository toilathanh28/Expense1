package com.example.coursework;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SQLiteDatabase extends AppCompatActivity {

    EditText trip_name1, place_name1, DoT, price1, risk_assessment1;
    Button btn_insert, btn_delete, btn_view, btn_reset, btn_addfee;
    DBHelpers DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite_database3);

        trip_name1 = findViewById(R.id.trip_name1);
        place_name1 = findViewById(R.id.place_name1);
        price1 = findViewById(R.id.price1);
        DoT = findViewById(R.id.DoT);
        risk_assessment1 = findViewById(R.id.risk_assessment1);

        btn_insert = findViewById(R.id.btn_insert);
        btn_delete = findViewById(R.id.btn_delete);
        btn_view = findViewById(R.id.btn_view);
        btn_reset = findViewById(R.id.btn_reset);
        btn_addfee = findViewById(R.id.btn_addfee);
        DB = new DBHelpers(this);

        btn_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String trip_name1TXT = trip_name1.getText().toString();
                String place_name1TXT = place_name1.getText().toString();
                String price1TXT = price1.getText().toString();
                String risk_assessment1TXT = risk_assessment1.getText().toString();
                String DoTTXT = DoT.getText().toString();


                if(trip_name1TXT.equals("")){
                    trip_name1.setError("Ten khong de trang!");
                    return;
                }

                Boolean checkinsertdata = DB.insertTripData(trip_name1TXT, place_name1TXT, price1TXT, risk_assessment1TXT, DoTTXT);
                if(checkinsertdata == true)
                Toast.makeText(SQLiteDatabase.this, "New Entry Inserted", Toast.LENGTH_SHORT).show();
                else
                Toast.makeText(SQLiteDatabase.this, "New Entry not Inserted", Toast.LENGTH_SHORT).show();
            }
        });

            btn_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String trip_name1TXT = trip_name1.getText().toString();
                    Boolean checkdeletedata = DB.deleteTripData(trip_name1TXT);
                    if(checkdeletedata == true)
                        Toast.makeText(SQLiteDatabase.this, "Entry Deleted ", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(SQLiteDatabase.this, "Entry not Delete", Toast.LENGTH_SHORT).show();

                }
            });

        btn_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = DB.getdata();
                if(res.getCount() ==0){
                    Toast.makeText( SQLiteDatabase.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("Name of trip:" +res.getString(0) + "\n");
                    buffer.append("Name of place:" +res.getString(1) + "\n");
                    buffer.append("Price:" +res.getString(2) + "\n");
                    buffer.append("Risk assessment:" +res.getString(3) + "\n");
                    buffer.append("Date of trip:" +res.getString(4) + "\n\n\n");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder( SQLiteDatabase.this);
                builder.setCancelable(true);
                builder.setTitle("Trip Entries");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });



            btn_addfee.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String trip_name1TXT = trip_name1.getText().toString();
                    String price1TXT = price1.getText().toString();

                    if(price1TXT.equals("")){
                        trip_name1.setError("You need add new fee, do not delete old fee!");
                        return;
                    }

                    Boolean checkaddfee = DB.addFeeForTrip(trip_name1TXT, price1TXT);
                    if (checkaddfee==true){
                        Toast.makeText(SQLiteDatabase.this, "Add new fee successfull", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(SQLiteDatabase.this, "Add fail", Toast.LENGTH_SHORT).show();}
                }
            });


        btn_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer var = DB.resetData();
                if(var > 0) {
                    Toast.makeText(SQLiteDatabase.this, "Reset successfull!!!", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(SQLiteDatabase.this, "Delete failed!!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
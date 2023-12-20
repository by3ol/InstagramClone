package com.example.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.List;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnSave;
    private EditText edtName, edtPunchSpeed, edtPunchPower, edtKickSpeed, edtKickPower;
    private TextView txtGetData;

    private Button btnGetAllData;
    private String allKickBoxers;
    private Button btnTransition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(SignUpActivity.this);

        edtName =findViewById(R.id.edtName);
        edtPunchSpeed=findViewById(R.id.edtPunchSpeed);
        edtPunchPower=findViewById(R.id.edtPunchPower);
        edtKickSpeed=findViewById(R.id.edtKickSpeed);
        edtKickPower=findViewById(R.id.edtKickPower);

        txtGetData = findViewById(R.id.txtGetData);
        txtGetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseQuery<ParseObject> parseQuery = ParseQuery.getQuery("KickBoxer");
                parseQuery.getInBackground("k6E99oQhEK", new GetCallback<ParseObject>() {
                    @Override
                    public void done(ParseObject object, ParseException e) {
                        if(object != null && e == null){
                            txtGetData.setText(object.get("name")+" - "+"Punch Power: "+object.get("punchPower"));
                        }
                    }
                });
            }
        });
        btnGetAllData= findViewById(R.id.btnGetAllData);
        btnGetAllData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allKickBoxers = "";

                ParseQuery<ParseObject> queryAll = ParseQuery.getQuery("KickBoxer");
//                queryAll.whereGreaterThan("punchPower",500);
                queryAll.whereGreaterThanOrEqualTo("punchPower",300);
                queryAll.setLimit(1);
                queryAll.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> objects, ParseException e) {
                        if( e == null){
                            if(objects.size() > 0){
                                for(ParseObject kickBoxer : objects){
                                    allKickBoxers = allKickBoxers + kickBoxer.get("name") + "\n";
                                }
                                FancyToast.makeText(SignUpActivity.this,allKickBoxers , FancyToast.LENGTH_SHORT, FancyToast.SUCCESS, true).show();

                            }else{
                                FancyToast.makeText(SignUpActivity.this, e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();


                            }

                        }
                    }
                });
            }
        });

        btnTransition = findViewById(R.id.btnNextActivity);
        btnTransition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SignUpActivity.this, SignUpLoginActivity.class);
                startActivity(intent);

            }
        });

    }

    @Override
    public void onClick(View v) {

        try {
            ParseObject kickBoxer = new ParseObject("KickBoxer");
            kickBoxer.put("name", edtName.getText().toString());
            kickBoxer.put("punchSpeed", Integer.parseInt(edtPunchSpeed.getText().toString()));
            kickBoxer.put("punchPower", Integer.parseInt(edtPunchPower.getText().toString()));
            kickBoxer.put("kickSpeed", Integer.parseInt(edtKickSpeed.getText().toString()));
            kickBoxer.put("kickPower", Integer.parseInt(edtKickPower.getText().toString()));
            kickBoxer.saveInBackground(new SaveCallback() {
                @Override
                public void done(ParseException e) {
                    if (e == null) {
                        FancyToast.makeText(SignUpActivity.this, kickBoxer.get("name") + " is saved successfully", FancyToast.LENGTH_SHORT, FancyToast.SUCCESS, true).show();
                    } else {
                        FancyToast.makeText(SignUpActivity.this, e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
                    }
                }
            });
        }catch (Exception e){
            FancyToast.makeText(SignUpActivity.this, e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();


        }

    }

//    public void helloWorldTapped(View view){

//        ParseObject boxer = new ParseObject("Boxer");
//        boxer.put("punch_speed",200);
//        boxer.saveInBackground(new SaveCallback() {
//            @Override
//            public void done(ParseException e) {
//                if(e == null){ //the object is saved successfully to the server
//                    Toast.makeText(SignUpActivity.this, "boxer object saved successfully",Toast.LENGTH_LONG).show();
//
//                }
//            }
//        });

//        ParseObject kickBoxer = new ParseObject("KickBoxer");
//        kickBoxer.put("name","John");
//        kickBoxer.put("punchSpeed",1000);
//        kickBoxer.put("punchPower",2000);
//        kickBoxer.put("kickSpeed",3000);
//        kickBoxer.put("kickPower",4000);
//        kickBoxer.saveInBackground(new SaveCallback() {
//            @Override
//            public void done(ParseException e) {
//                Toast.makeText(SignUpActivity.this, kickBoxer.get("name")+" is saved successfully",Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
}
package com.example.lyndon.lyndondemo;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;


public class SecondActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Intent intentThatStartedThisActivity = getIntent();
        Toast.makeText(this, "Nice Day " + intentThatStartedThisActivity.getBooleanExtra("niceDay", false), Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        // save data when activity is entering stop state
        SharedPreferences sp = getSharedPreferences("personData", MODE_PRIVATE); // MODE_PRIVATE, read these other MODES too when you can
        SharedPreferences.Editor editor = sp.edit(); // Nested class Editor

        EditText etName = (EditText)findViewById(R.id.name_edittext);
        //get the data we want to pass back as a result
        String name = etName.getText().toString();

        editor.putString("name", name); //saves the string
        editor.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.second, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // Return result to MainActivity when back button is pressed
    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        EditText etName = (EditText)findViewById(R.id.name_edittext);
        //get the data we want to pass back as a result
        String name = etName.getText().toString();

        if(name != null || name.equals("")){
            setResult(RESULT_CANCELED);
        } else {
            //Put data into an intent object (as Extras)
            Intent intent = new Intent();
            intent.putExtra("name", name);
            //...
            //set the result
            setResult(RESULT_OK, intent);
        }

        //destroy this activity
        finish();
    }
}

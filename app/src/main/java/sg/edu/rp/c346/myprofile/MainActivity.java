package sg.edu.rp.c346.myprofile;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    EditText etName;
    EditText etGPA;

    //Learning Checkpoint 2
    RadioGroup rgGender;
    CheckBox ckbLike;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = (EditText) findViewById(R.id.editTextName);
        etGPA = (EditText) findViewById(R.id.editTextGPA);

        //Binding for radio and
        rgGender = (RadioGroup) findViewById(R.id.radioButtonGender);
        ckbLike = (CheckBox) findViewById(R.id.checkBoxLikeProgramming);



    }

    @Override
    protected void onPause() {
        super.onPause();
        //Step1a: Retrieve data input of the user
        String strName = etName.getText().toString();

        //Step1b: Obtain an instance of the shared preferences:
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        //Step1c: Obtain an instance of the shared preference editor for update later
        SharedPreferences.Editor prefEdit = prefs.edit();

        //Step1d: Add the key-value pair
        prefEdit.putString("name", strName);

        //Step2:
        float gpaNo = Float.valueOf(etGPA.getText().toString());
        boolean bLike = ckbLike.isChecked();
        //Obtain the id of the selected radio button
        int intGenderId = rgGender.getCheckedRadioButtonId();

        //Step1d: Add the key-value pair
        prefEdit.putFloat("gpa", gpaNo);
        prefEdit.putBoolean("like", bLike);
        prefEdit.putInt("genderId", intGenderId);

        //Step1e: Call commit() method to save the changes into the shared preference
        prefEdit.commit();



    }

    @Override
    protected void onResume() {
        super.onResume();

        //Step2a: Obtain an instance of shared preference
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        //Step2b: Retrieve the saved data with the key, name from the SharedPreferences object
        String strName = prefs.getString("name", "John");
        float gpa = prefs.getFloat("gpa", 0);

        //Retrieve a Boolean value using key "like"
        boolean bLike = prefs.getBoolean("like", false);
        int intGenderId=prefs.getInt("genderId",R.id.radioButtonGenderMale);

        //Step2c: Update the UI element with the value
        etName.setText(strName);
        etGPA.setText(gpa + "");

        //Part2
        ckbLike.setChecked(bLike);
        rgGender.check(intGenderId);




    }
}

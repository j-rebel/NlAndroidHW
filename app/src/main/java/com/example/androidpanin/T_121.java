package com.example.androidpanin;

import androidx.appcompat.widget.Toolbar;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class T_121 extends ToolbarActivity {

    private TextView formMsg;
    private TextView formErr;
    private EditText nameInput;
    private EditText emailInput;
    private Button resetButton;
    private Button sendButton;
    private String name;
    private String email;
    private String formMsgText;
    private String errText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.onActivityCreateSetTheme(this);
        setContentView(R.layout.activity_121);
        initViews();
    }

    private void initViews() {
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);


        errText = getText(R.string.subscribe_form_err).toString();

        formMsg= findViewById(R.id.formMsg);
        formErr = findViewById(R.id.formErr);
        nameInput = findViewById(R.id.nameInput);
        emailInput = findViewById(R.id.emailInput);

        clearInputs();
        clearMsgs();

        sendButton = findViewById(R.id.sendButton);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                name = nameInput.getText().toString();
                email = emailInput.getText().toString();

                formMsgText =  getText(R.string.subscribe_form_msg_name).toString() +
                        name +
                        getText(R.string.subscribe_form_msg_email).toString() +
                        email;

                if (" ".equals(name) || " ".equals(email)) {
                    formErr.setText(errText);
                } else {
                    formErr.setText(" ");
                    formMsg.setText(formMsgText);
                }
            }
        });

        resetButton = findViewById(R.id.resetButton);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearInputs();
                clearMsgs();
            }
        });
    }

    private void clearInputs() {
        nameInput.setText(" ");
        emailInput.setText(" ");
    }

    private void clearMsgs() {
        formErr.setText(" ");
        formMsg.setText(" ");
    }
}

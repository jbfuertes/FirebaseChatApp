package com.example.edhryl.firebasechatapp;

import android.content.Intent;
import android.provider.SyncStateContract;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.appevents.internal.Constants;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import android.text.format.DateFormat;

public class MainActivity extends AppCompatActivity {

    private static int SIGN_IN_REQUEST_CODE=1;
    private FirebaseListAdapter<ChatMessage> adapter;
    RelativeLayout activity_main;
    FloatingActionButton sendButton;
    EditText inputMessage;
    EditText sendUserTo;
    String user;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.menuSignOut){
            AuthUI.getInstance().signOut(this).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                     Snackbar.make(activity_main,"Sign Out Complete",Snackbar.LENGTH_SHORT).show();
                    finish();
                }
            });
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_menu,menu);
        return true;

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == SIGN_IN_REQUEST_CODE){
            if(resultCode == RESULT_OK){
                Snackbar.make(activity_main,"Sign in success!",Snackbar.LENGTH_SHORT).show();
                displayChatMessage();
            }
            else {
                Snackbar.make(activity_main,"Failed to Sign In",Snackbar.LENGTH_SHORT).show();
                finish();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activity_main = (RelativeLayout)findViewById(R.id.activity_main);
        sendButton = (FloatingActionButton)findViewById(R.id.sendButton);
        inputMessage = (EditText)findViewById(R.id.inputMessage);
        sendUserTo = (EditText)findViewById(R.id.sendUserTo);
        //check if not sign in, if not register, navigate to sign in page
        if(FirebaseAuth.getInstance().getCurrentUser() == null){
            startActivityForResult(AuthUI.getInstance().createSignInIntentBuilder().build(),SIGN_IN_REQUEST_CODE);
        }
        else{
            Snackbar.make(activity_main,"Welcome "+FirebaseAuth.getInstance().getCurrentUser().getEmail(),Snackbar.LENGTH_SHORT).show();
            //Load Content
            //user = String.valueOf(FirebaseAuth.getInstance().getCurrentUser().getUid());
            displayChatMessage();
        }
    }

    /*public void sendButtonClicked(View view) {

        FirebaseDatabase.getInstance().getReference().child("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(new ChatMessage(inputMessage.getText().toString(),
                FirebaseAuth.getInstance().getCurrentUser().getEmail(),FirebaseAuth.getInstance().getCurrentUser().getUid()));
        inputMessage.setText("");

    }*/
    private void displayChatMessage(){
        ListView messageList = (ListView)findViewById(R.id.messageList);
        adapter = new FirebaseListAdapter<ChatMessage>(MainActivity.this,ChatMessage.class,R.layout.list_item,
                FirebaseDatabase.getInstance().getReference().child("Adi")){

            @Override
            protected void populateView(View v, ChatMessage model, int position) {
                //get reference to the views of list_item.xml
                TextView messageUser, messageUserText, messageUserTime;
                messageUser = (TextView) v.findViewById(R.id.messageUser);
                messageUserText = (TextView) v.findViewById(R.id.messageUserText);
                messageUserTime = (TextView) v.findViewById(R.id.messageUserTime);

                messageUser.setText(model.getMessageUser());
                messageUserText.setText(model.getMessageUserText());
                messageUserTime.setText(model.getMessageUserTime());
                /*if(user.equals(String.valueOf(model.getuId()))) {
                    messageUserText.setText(model.getMessageText());
                    messageUser.setText(model.getMessageUser());
                    messageUserTime.setText(DateFormat.format("dd-MM-yyyy (HH:mm:ss)", model.getMessageTime()));
                }*/
            }
        };
        messageList.setAdapter(adapter);
    }
}

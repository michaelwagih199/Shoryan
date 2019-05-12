package com.example.projectui.inhomefragments;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.projectui.R;
import com.example.projectui.base.BaseFragment;
import com.example.projectui.entities.CommentsPojoAdd;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentComment extends BaseFragment {


    public FragmentComment() {
        // Required empty public constructor
    }

    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;
    String curentUser;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_fragment_comment, container, false);
        //// TODO: 04/05/2019 make comments

        //getting current user
        databaseReference = FirebaseDatabase.getInstance().getReference();
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        curentUser = user.getUid();

        view.findViewById(R.id.fab)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AddCommentDialog();
                    }
                });


        return view;
    }


    public void AddCommentDialog() {

        // get prompts.xml view
        LayoutInflater li = LayoutInflater.from(getContext());
        View promptsView = li.inflate(R.layout.prompts, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                getContext());

        // set prompts.xml to alertdialog builder
        alertDialogBuilder.setView(promptsView);

        final EditText userInput = (EditText) promptsView
                .findViewById(R.id.editTextDialogUserInput);

        // set dialog message
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // get user input and set it to result
                                // edit text
                                try {
                                    addDonner(userInput.getText().toString());
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                            }
                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();

    }


    public void addDonner(String commentContent) {
        try {
            Log.e("l",getCountComment()+"");
            CommentsPojoAdd commentsPojoAdd = new CommentsPojoAdd(commentContent,curentUser);
            databaseReference.child("Comments").child(String.valueOf(1)).setValue(commentsPojoAdd);
            toastMessage("تم الحفظ");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public String getCountComment(){

        final String[] result = {"c"};
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();
        //You can use the single or the value.. depending if you want to keep track
        Query query = myRef.child("Comments").orderByKey().limitToLast(1);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
               // String message = dataSnapshot.child("message").getValue().toString();
                result[0] =  dataSnapshot.getValue().toString();

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

                Log.e("mm",database.toString());
            }
        });
        return result[0];
    }

    /**
     * customizable toast
     *
     * @param message
     */
    private void toastMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }


}

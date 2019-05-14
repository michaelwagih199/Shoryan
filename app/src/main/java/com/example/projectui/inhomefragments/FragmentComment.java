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
import android.widget.ListView;
import android.widget.Toast;

import com.example.projectui.R;
import com.example.projectui.base.BaseFragment;
import com.example.projectui.entities.CommentsPojo;
import com.example.projectui.entities.CommentsPojoAdd;
import com.example.projectui.entities.DonorInfomation;
import com.example.projectui.entities.MyListAdapter;
import com.example.projectui.entities.PostAdapters;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.UUID;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentComment extends BaseFragment {


    public FragmentComment() {
        // Required empty public constructor
    }

    //add Firebase Database stuff
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference databaseReference;

    private FirebaseAuth firebaseAuth;
    String curentUser;
    String userEmail;
    ListView listVComments;
    int likeCounter = 0;
    boolean isUserLike = false;


    View view;
    ArrayList<CommentsPojo> arrayList = new ArrayList<CommentsPojo>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_fragment_comment, container, false);
        //// TODO: 04/05/2019 make comments

        listVComments = (ListView) view.findViewById(R.id.listVComments);

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = mFirebaseDatabase.getReference();

        firebaseAuth = FirebaseAuth.getInstance();

        getPostst();


        FirebaseUser user = firebaseAuth.getCurrentUser();
        curentUser = user.getUid();
        userEmail = user.getEmail();

        view.findViewById(R.id.fab)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AddCommentDialog();


                    }
                });


        return view;
    }


    public void getPostst() {
        Query query = databaseReference.child("Posts").orderByChild("userId");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {

                    for (DataSnapshot issue : dataSnapshot.getChildren()) {
                        CommentsPojo note = issue.getValue(CommentsPojo.class);
                        note.setUserId(userEmail);
                        arrayList.add(note);
                    }
//                   Log.e("tttt",arrayList.get(0).getName()+""+arrayList.get(1).getName());
                    PostAdapters customAdapter = new PostAdapters(getContext(), arrayList);
                    listVComments.setAdapter(customAdapter);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("ram", databaseError.getDetails());
            }
        });
    }


    public void getLikeCounter() {

        Query query = databaseReference.child("Posts").orderByChild("userId");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {

                    for (DataSnapshot issue : dataSnapshot.getChildren()) {
                        CommentsPojo note = issue.getValue(CommentsPojo.class);
                        likeCounter = note.getLike_counter();
                    }
//                   Log.e("tttt",arrayList.get(0).getName()+""+arrayList.get(1).getName());
                    PostAdapters customAdapter = new PostAdapters(getContext(), arrayList);
                    listVComments.setAdapter(customAdapter);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("ram", databaseError.getDetails());
            }
        });


    }

    public boolean getIsUserLike() {
        final boolean[] result = new boolean[1];
        Query query = databaseReference.child("Posts").orderByChild("userId");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {

                    for (DataSnapshot issue : dataSnapshot.getChildren()) {
                        CommentsPojo note = issue.getValue(CommentsPojo.class);
                        result[0] = note.isIs_user_like();
                    }
//                   Log.e("tttt",arrayList.get(0).getName()+""+arrayList.get(1).getName());
                    PostAdapters customAdapter = new PostAdapters(getContext(), arrayList);
                    listVComments.setAdapter(customAdapter);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("ram", databaseError.getDetails());
            }
        });
        return result[0];
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
                                    getIsUserLike();
                                    getLikeCounter();
                                    addPost(userInput.getText().toString(), likeCounter, isUserLike);
                                    arrayList.clear();
                                    getPostst();
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




    public void addPost(String commentContent, int likeCounter, boolean isUserLike) {
        try {
            CommentsPojoAdd commentsPojoAdd = new CommentsPojoAdd(commentContent, curentUser, likeCounter, isUserLike);
            databaseReference.child("Posts").child(generateCode()).setValue(commentsPojoAdd);
            toastMessage("تم الحفظ");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String generateCode() {
        // Creating a random UUID (Universally unique identifier).
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
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

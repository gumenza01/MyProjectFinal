package com.example.ananpengkhun.myprojectfinal.activity;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.animation.Interpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ananpengkhun.myprojectfinal.R;
import com.example.ananpengkhun.myprojectfinal.dao.ProviderDao;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.io.File;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.internal.Context;

public class DetailOfListProviderActivity extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;
    private static final int TAKE_PHOTO_REQUEST = 2;

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.activity_detail_of_list_provider) RelativeLayout activityDetailOfListProvider;
    @BindView(R.id.ed_provider_name) AppCompatEditText edProviderName;
    @BindView(R.id.tv_provider_name) TextView tvProviderName;
    @BindView(R.id.ed_provider_address) AppCompatEditText edProviderAddress;
    @BindView(R.id.tv_provider_address) TextView tvProviderAddress;
    @BindView(R.id.ed_provider_phone) AppCompatEditText edProviderPhone;
    @BindView(R.id.tv_provider_phone) TextView tvProviderPhone;
    @BindView(R.id.ed_provider_email) AppCompatEditText edProviderEmail;
    @BindView(R.id.tv_provider_email) TextView tvProviderEmail;
    @BindView(R.id.imv_box_for_edit) ImageView imvBoxForEdit;
    @BindView(R.id.imv_prov) ImageView imvProv;

    private Button takeByCamera;
    private Button takeByGallery;
    private Uri pathFile;

    private ProviderDao providerDao;
    private int index;
    private boolean swap = true;
    private String provName;
    private String provAddess;
    private String provPhone;
    private String provEmail;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_of_list_provider);
        ButterKnife.bind(this);
        setupView();
        init();


    }

    private void init() {
        Intent intent = getIntent();
        imvProv.setEnabled(false);
        if(intent.getExtras() != null){
            providerDao = intent.getParcelableExtra("provider_object_index");
            index = intent.getIntExtra("provider_index",-1);
            setTextView(providerDao.getProvName(),providerDao.getProvAddress(),providerDao.getProvPhone(),providerDao.getProvEmail(),providerDao.getProvImg());
        }


        imvBoxForEdit.setOnClickListener(imvClicklistener);

    }

    private void setupView() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationOnClickListener(toolbarClicklistener);

    }

    private View.OnClickListener toolbarClicklistener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {


//            Intent intent = new Intent();
//            intent.putExtra("provider_index",index);
//            intent.putExtra("provAddress",provAddess);
//            intent.putExtra("provEmail",provEmail);
//            intent.putExtra("provName",provName);
//            intent.putExtra("provPhone",provPhone);
//            setResult(MyDataInventoryActivity.PROVIDER,intent);
            finish();
        }
    };

    private View.OnClickListener imgProvClicklistener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            final Dialog dialog = new Dialog(DetailOfListProviderActivity.this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.dialog_import_picture);
            dialog.setCancelable(true);
            bindId(dialog);

            takeByCamera.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/picFolder/";

                    File newdir = new File(dir);
                    if (!newdir.exists()) {
                        newdir.mkdirs();
                    }
                    String file = dir + System.currentTimeMillis() + ".jpg";
                    File newfile = new File(file);
                    try {
                        newfile.createNewFile();
                    } catch (IOException e) {
                    }

                    pathFile = Uri.fromFile(newfile);
                    Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, pathFile);
                    startActivityForResult(cameraIntent, TAKE_PHOTO_REQUEST);


                }
            });

            takeByGallery.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent();
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
                }
            });

            dialog.show();
        }
    };

    private void bindId(Dialog dialog) {
        takeByCamera = (Button) dialog.findViewById(R.id.btn_add_by_take);
        takeByGallery = (Button) dialog.findViewById(R.id.btn_add_by_gallery);
    }

    private View.OnClickListener imvClicklistener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (imvBoxForEdit.isSelected()) {
                imvProv.setEnabled(false);
                imvBoxForEdit.setSelected(false);
                //textView Visible
                tvProviderName.setVisibility(View.VISIBLE);
                tvProviderAddress.setVisibility(View.VISIBLE);
                tvProviderPhone.setVisibility(View.VISIBLE);
                tvProviderEmail.setVisibility(View.VISIBLE);

                //EditText Gone
                edProviderName.setVisibility(View.GONE);
                edProviderAddress.setVisibility(View.GONE);
                edProviderPhone.setVisibility(View.GONE);
                edProviderEmail.setVisibility(View.GONE);

                provName = edProviderName.getText().toString();
                provAddess = edProviderAddress.getText().toString();
                provPhone = edProviderPhone.getText().toString();
                provEmail = edProviderEmail.getText().toString();


                setTextView(provName,provAddess,provPhone,provEmail,"");
                // save data
                DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://pipe-993d5.firebaseio.com/provider");
                Log.d("providers", "onDataChange: "+providerDao.getProvId());
                Query query = mRootRef.orderByChild("provId").equalTo(providerDao.getProvId());
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
                            Log.d("providers", "onDataChange: "+snapshot.toString());
                            snapshot.getRef().child("provAddress").setValue(provAddess);
                            snapshot.getRef().child("provEmail").setValue(provEmail);
                            snapshot.getRef().child("provName").setValue(provName);
                            snapshot.getRef().child("provPhone").setValue(provPhone);
                            if(pathFile != null) {
                                snapshot.getRef().child("provImg").setValue(pathFile.toString());
                            }
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });


            } else {
                // edit data
                imvBoxForEdit.setSelected(true);
                imvProv.setEnabled(true);
                imvProv.setOnClickListener(imgProvClicklistener);
                //textView Gone
                tvProviderName.setVisibility(View.GONE);
                tvProviderAddress.setVisibility(View.GONE);
                tvProviderPhone.setVisibility(View.GONE);
                tvProviderEmail.setVisibility(View.GONE);

                //EditText Visible
                edProviderName.setVisibility(View.VISIBLE);
                edProviderAddress.setVisibility(View.VISIBLE);
                edProviderPhone.setVisibility(View.VISIBLE);
                edProviderEmail.setVisibility(View.VISIBLE);

                if(swap){
                    swap = false;
                    setTextEdit(providerDao.getProvName(),providerDao.getProvAddress(),providerDao.getProvPhone(),providerDao.getProvEmail());
                }else{
                    setTextEdit(provName,provAddess,provPhone,provEmail);
                }
            }
        }
    };

    private void setTextEdit(String provName, String provAddess, String provPhone, String provEmail) {
        edProviderName.setText(provName);
        edProviderAddress.setText(provAddess);
        edProviderPhone.setText(provPhone);
        edProviderEmail.setText(provEmail);

    }

    private void setTextView(String provName, String provAddess, String provPhone, String provEmail, String provImg) {
        tvProviderName.setText(provName);
        tvProviderAddress.setText(provAddess);
        tvProviderPhone.setText(provPhone);
        tvProviderEmail.setText(provEmail);
        if(!"".equals(provImg)) {
            Glide.with(DetailOfListProviderActivity.this).load(provImg).placeholder(R.drawable.default_img).into(imvProv);
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == PICK_IMAGE_REQUEST) {
                pathFile = data.getData();
                Glide.with(DetailOfListProviderActivity.this).load(pathFile).into(imvProv);
            } else if (requestCode == TAKE_PHOTO_REQUEST) {
                Glide.with(DetailOfListProviderActivity.this).load(pathFile).into(imvProv);
            }
        }
    }
}

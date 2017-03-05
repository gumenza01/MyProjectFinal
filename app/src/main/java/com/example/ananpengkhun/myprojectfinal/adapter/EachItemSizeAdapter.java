package com.example.ananpengkhun.myprojectfinal.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ananpengkhun.myprojectfinal.R;
import com.example.ananpengkhun.myprojectfinal.activity.DetailOfListProductActivity;
import com.example.ananpengkhun.myprojectfinal.activity.DetailOfListProviderActivity;
import com.example.ananpengkhun.myprojectfinal.adapter.viewholder.EachItemSizeViewHolder;
import com.example.ananpengkhun.myprojectfinal.dao.Product;
import com.example.ananpengkhun.myprojectfinal.dao.ProductDao;
import com.example.ananpengkhun.myprojectfinal.dao.ProductEachSize;
import com.example.ananpengkhun.myprojectfinal.dao.Productsize;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;

/**
 * Created by ananpengkhun on 12/27/16.
 */

public class EachItemSizeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final ProductDao dao;
    private Context mContext;
    private List<ProductEachSize> productDao;

    private boolean swap = true;
    private int prodAmount;
    private Realm realm;


    public EachItemSizeAdapter(Context mContext, List<ProductEachSize> productDao, ProductDao dao) {
        this.mContext = mContext;
        this.productDao = productDao;
        this.dao = dao;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_product_size_item, parent, false);
        return new EachItemSizeViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        if (holder instanceof EachItemSizeViewHolder) {
            final EachItemSizeViewHolder eachItemSizeViewHolder = (EachItemSizeViewHolder) holder;
            eachItemSizeViewHolder.tvNameProdSize.setText(productDao.get(position).getNameItemSize());
            eachItemSizeViewHolder.tvUnitProd.setText(productDao.get(position).getUnit());
            eachItemSizeViewHolder.tvPriceClassOne.setText(productDao.get(position).getPriceUBaht().getClassOne());
            eachItemSizeViewHolder.tvPriceClassTwo.setText(productDao.get(position).getPriceUBaht().getClassTwo());
            Log.d("eachitem", "onBindViewHolder: " + productDao.get(position).getPriceUBaht().getClassTwo());
            eachItemSizeViewHolder.tvPriceClassThree.setText(productDao.get(position).getPriceUBaht().getClassThree());
            eachItemSizeViewHolder.tvPriceClassFive.setText(productDao.get(position).getPriceUBaht().getClassFive());
            eachItemSizeViewHolder.tvPriceClassEightFive.setText(productDao.get(position).getPriceUBaht().getClassEightFive());
            eachItemSizeViewHolder.tvPriceClassOneThreeFive.setText(productDao.get(position).getPriceUBaht().getClassOneThreeFive());
            eachItemSizeViewHolder.tvPricePerKilo.setText(productDao.get(position).getPriceUBaht().getPerKilo());
            eachItemSizeViewHolder.tvPricePerMeter.setText(productDao.get(position).getPriceUBaht().getPerMeter());
            eachItemSizeViewHolder.tvPricePerPiece.setText(productDao.get(position).getPriceUBaht().getPerPiece());
            eachItemSizeViewHolder.tvPricePerWrap.setText(productDao.get(position).getPriceUBaht().getPerWrap());
            eachItemSizeViewHolder.tvEfford.setText(productDao.get(position).getEffordUBaht());
            eachItemSizeViewHolder.tvAmountPerWrap.setText(productDao.get(position).getAmongPerWrap());
            eachItemSizeViewHolder.tvAlertProd.setText(productDao.get(position).getTotalItemBigUnit());
            eachItemSizeViewHolder.tvAlert.setText(String.valueOf(productDao.get(position).getProductSizeAlert()));


            eachItemSizeViewHolder.imvBoxForEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (eachItemSizeViewHolder.imvBoxForEdit.isSelected()) {

                        eachItemSizeViewHolder.imvBoxForEdit.setSelected(false);

//                        textView Visible
//                        eachItemSizeViewHolder.tvNameProdSize.setVisibility(View.VISIBLE);
//                        eachItemSizeViewHolder.tvUnitProd.setVisibility(View.VISIBLE);
//                        eachItemSizeViewHolder.tvPriceClassOne.setVisibility(View.VISIBLE);
//                        eachItemSizeViewHolder.tvPriceClassTwo.setVisibility(View.VISIBLE);
//                        eachItemSizeViewHolder.tvPriceClassThree.setVisibility(View.VISIBLE);
//                        eachItemSizeViewHolder.tvPriceClassFive.setVisibility(View.VISIBLE);
//                        eachItemSizeViewHolder.tvPriceClassEightFive.setVisibility(View.VISIBLE);
//                        eachItemSizeViewHolder.tvPriceClassOneThreeFive.setVisibility(View.VISIBLE);
//                        eachItemSizeViewHolder.tvPricePerKilo.setVisibility(View.VISIBLE);
//                        eachItemSizeViewHolder.tvPricePerMeter.setVisibility(View.VISIBLE);
//                        eachItemSizeViewHolder.tvPricePerPiece.setVisibility(View.VISIBLE);
//                        eachItemSizeViewHolder.tvPricePerWrap.setVisibility(View.VISIBLE);
//                        eachItemSizeViewHolder.tvEfford.setVisibility(View.VISIBLE);
//                        eachItemSizeViewHolder.tvAmountPerWrap.setVisibility(View.VISIBLE);
                        eachItemSizeViewHolder.tvAlertProd.setVisibility(View.VISIBLE);
                        eachItemSizeViewHolder.tvAlert.setVisibility(View.VISIBLE);
//
//                        EditText Gone
//                        eachItemSizeViewHolder.edNameProdSize.setVisibility(View.GONE);
//                        eachItemSizeViewHolder.edUnitProd.setVisibility(View.GONE);
                        //eachItemSizeViewHolder.edPriceClassOne.setVisibility(View.VISIBLE);
                        eachItemSizeViewHolder.edProdAmount.setVisibility(View.GONE);
                        eachItemSizeViewHolder.edAlert.setVisibility(View.GONE);

                        prodAmount = Integer.parseInt(productDao.get(position).getTotalItemBigUnit());

//
                        //quantity.set(position, eachItemSizeViewHolder.edAlertProd.getText().toString());
                        ProductEachSize each = new ProductEachSize();
                        ProductEachSize.PriceUBahtBean priceUBahtBean = new ProductEachSize.PriceUBahtBean();
                        priceUBahtBean.setClassOne(productDao.get(position).getPriceUBaht().getClassOne());
                        priceUBahtBean.setClassTwo(productDao.get(position).getPriceUBaht().getClassTwo());
                        priceUBahtBean.setClassThree(productDao.get(position).getPriceUBaht().getClassThree());
                        priceUBahtBean.setClassFive(productDao.get(position).getPriceUBaht().getClassFive());
                        priceUBahtBean.setClassEightFive(productDao.get(position).getPriceUBaht().getClassEightFive());
                        priceUBahtBean.setClassOneThreeFive(productDao.get(position).getPriceUBaht().getClassOneThreeFive());
                        priceUBahtBean.setPerKilo(productDao.get(position).getPriceUBaht().getPerKilo());
                        priceUBahtBean.setPerMeter(productDao.get(position).getPriceUBaht().getPerMeter());
                        priceUBahtBean.setPerPiece(productDao.get(position).getPriceUBaht().getPerPiece());
                        priceUBahtBean.setPerWrap(productDao.get(position).getPriceUBaht().getPerWrap());

                        each.setPriceUBaht(priceUBahtBean);
                        each.setUnit(productDao.get(position).getUnit());
                        each.setNameItemSize(productDao.get(position).getNameItemSize());
                        each.setTotalItemBigUnit(String.valueOf(prodAmount));
                        each.setEffordUBaht(productDao.get(position).getEffordUBaht());
                        each.setAmongPerWrap(productDao.get(position).getAmongPerWrap());
                        each.setProductSizeAlert(Integer.parseInt(eachItemSizeViewHolder.edAlert.getText().toString()));
                        each.setIndexInProduct(productDao.get(position).getIndexInProduct());
                        each.setNameItemId(productDao.get(position).getNameItemId());
                        productDao.set(position,each);
                        notifyDataSetChanged();
//                        prodName = edNameProd.getText().toString();
//                        prodPrice = edPriceProd.getText().toString();
//                        prodAmount = Integer.parseInt(edAmountProd.getText().toString());
//                        prodUnit = edUnitProd.getText().toString();
//                        prodAlert = Integer.parseInt(edAlertProd.getText().toString());
                        //eachItemSizeViewHolder.tvAlertProd.setText(quantity.get(position));


//                        save data
                        //edit realm
                        Productsize size = realm.where(Productsize.class).equalTo("nameItemId",productDao.get(position).getNameItemId()).findFirst();
                        realm.beginTransaction();
                        size.setTotalItemBigUnit(String.valueOf(prodAmount));
                        size.setProductSizeAlert(Integer.parseInt(eachItemSizeViewHolder.edAlert.getText().toString()));
                        realm.commitTransaction();

                        //edit firebase
                        DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://pipe-993d5.firebaseio.com/productType/" + dao.getProdInType());
                        mRootRef.child("data/"+ productDao.get(position).getIndexInProduct() +"/dataItem/"+position+"/totalItemBigUnit").setValue(String.valueOf(prodAmount));
                        mRootRef.child("data/"+ productDao.get(position).getIndexInProduct() +"/dataItem/"+position+"/productSizeAlert").setValue(Integer.parseInt(eachItemSizeViewHolder.edAlert.getText().toString()));


                    } else {
                        //edit data

                        eachItemSizeViewHolder.imvBoxForEdit.setSelected(true);
//                        textView Gone
                        eachItemSizeViewHolder.tvAlertProd.setVisibility(View.GONE);
                        eachItemSizeViewHolder.tvAlert.setVisibility(View.GONE);

//                        EditText Visible
                        eachItemSizeViewHolder.edProdAmount.setVisibility(View.VISIBLE);
                        eachItemSizeViewHolder.edAlert.setVisibility(View.VISIBLE);

                        eachItemSizeViewHolder.imvMinus.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                final Dialog dialog = new Dialog(mContext);
                                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                                dialog.setContentView(R.layout.minus_product_quatity);
                                dialog.setCancelable(true);

                                TextView textView = (TextView) dialog.findViewById(R.id.tv_dialog_quatity);
                                final EditText editText = (EditText) dialog.findViewById(R.id.ed_dialog_quatity);
                                Button button = (Button) dialog.findViewById(R.id.btn_dialog_confirm);

                                textView.setText("จำนวนที่ต้องการลด");

                                button.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        if (!"".equals(editText.getText().toString())) {
                                            int sum = Integer.parseInt(productDao.get(position).getTotalItemBigUnit()) - Integer.parseInt(editText.getText().toString());
                                            productDao.get(position).setTotalItemBigUnit(String.valueOf(sum));
                                            prodAmount = sum;
                                        } else {
                                            prodAmount = Integer.parseInt(productDao.get(position).getTotalItemBigUnit());
                                            Log.d("amount", "onClick: " + prodAmount);
                                        }
                                        dialog.dismiss();
                                    }
                                });
                                dialog.show();
                            }
                        });
                        eachItemSizeViewHolder.imvPlus.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                final Dialog dialog = new Dialog(mContext);
                                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                                dialog.setContentView(R.layout.minus_product_quatity);
                                dialog.setCancelable(true);

                                TextView textView = (TextView) dialog.findViewById(R.id.tv_dialog_quatity);
                                final EditText editText = (EditText) dialog.findViewById(R.id.ed_dialog_quatity);
                                Button button = (Button) dialog.findViewById(R.id.btn_dialog_confirm);

                                textView.setText("จำนวนที่ต้องการเพิ่ม");

                                button.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        if (!"".equals(editText.getText().toString())) {
                                            int sum = Integer.parseInt(productDao.get(position).getTotalItemBigUnit()) + Integer.parseInt(editText.getText().toString());
                                            productDao.get(position).setTotalItemBigUnit(String.valueOf(sum));
                                            prodAmount = sum;
                                        } else {
                                            prodAmount = Integer.valueOf(productDao.get(position).getTotalItemBigUnit());
                                            Log.d("amount", "onClick: " + prodAmount);
                                        }
                                        dialog.dismiss();
                                    }
                                });
                                dialog.show();
                            }
                        });

                        if (swap) {
                            swap = false;
                            //eachItemSizeViewHolder.edAlertProd.setText(productDao.get(position).getTotalItemBigUnit());
                            eachItemSizeViewHolder.edAlert.setText(""+productDao.get(position).getProductSizeAlert());

                        } else {
                            //eachItemSizeViewHolder.edAlertProd.setText(eachItemSizeViewHolder.tvAlertProd.getText().toString());
                            eachItemSizeViewHolder.edAlert.setText(eachItemSizeViewHolder.tvAlert.getText().toString());
                        }
                    }

                }

            });
        }
    }

    @Override
    public int getItemCount() {
        if (productDao == null) {
            return 0;
        }
        return productDao.size();
    }


    public void setRealm(Realm realm) {
        this.realm = realm;
    }
}

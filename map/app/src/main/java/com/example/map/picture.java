package com.example.map;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.PhotoMetadata;
import com.google.android.libraries.places.api.net.FetchPhotoRequest;
import com.google.android.libraries.places.api.net.PlacesClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;

public class picture extends AppCompatActivity{



    int a = 0;
    ArrayList<ImageView> litor_ImageView=new ArrayList<ImageView>();
    FetchPhotoRequest photoRequest;
    PlacesClient placesClient;
    PhotoMetadata photoMetadata;
    int count=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture);


        litor_ImageView.add(findViewById(R.id.imageView0));
        litor_ImageView.add(findViewById(R.id.imageView1));
        litor_ImageView.add(findViewById(R.id.imageView2));
        litor_ImageView.add(findViewById(R.id.imageView3));
        litor_ImageView.add(findViewById(R.id.imageView4));
        litor_ImageView.add(findViewById(R.id.imageView5));
        litor_ImageView.add(findViewById(R.id.imageView6));
        litor_ImageView.add(findViewById(R.id.imageView7));
        litor_ImageView.add(findViewById(R.id.imageView8));
        litor_ImageView.add(findViewById(R.id.imageView9));
        litor_ImageView.add(findViewById(R.id.imageView10));
        litor_ImageView.add(findViewById(R.id.imageView11));
        litor_ImageView.add(findViewById(R.id.imageView12));
        litor_ImageView.add(findViewById(R.id.imageView13));
        litor_ImageView.add(findViewById(R.id.imageView14));
        litor_ImageView.add(findViewById(R.id.imageView15));
        litor_ImageView.add(findViewById(R.id.imageView16));
        litor_ImageView.add(findViewById(R.id.imageView17));

        if (Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        Places.initialize(getApplicationContext(), "AIzaSyCZfMZxF4Ds1j8uBwRBcbgOZJT1-1cxVio", Locale.KOREA);
        placesClient = Places.createClient(this);
        if (!Places.isInitialized()) {
            Places.initialize(getApplicationContext(), "AIzaSyCZfMZxF4Ds1j8uBwRBcbgOZJT1-1cxVio");
        }


        if (MainActivity.staticmarker.getTag() == "search_m") {
            StringBuilder attr=new StringBuilder();

            Iterator<ImageView> iter1 = litor_ImageView.iterator();
            ArrayList<ImageView> litor_ImageView1=new ArrayList<ImageView>();
            for (int i = 0; i < MainActivity.place_var.getPhotoMetadatas().size(); i++) {
                photoMetadata = MainActivity.place_var.getPhotoMetadatas().get(i);
                photoRequest = FetchPhotoRequest.builder(photoMetadata)
                        .setMaxWidth(1500)
                        .setMaxHeight(1500)
                        .build();
                placesClient.fetchPhoto(photoRequest).addOnSuccessListener((fetchPhotoResponse) -> {
                    Bitmap bitmap0 = fetchPhotoResponse.getBitmap();
                    litor_ImageView.get(a).setImageBitmap(bitmap0);
                    a++;

                });
                System.out.println("iaaaaaaaaaaaaaaaaa"+i);
                String getAttributions=photoMetadata.getAttributions().substring(9,67);
                attr.append(getAttributions+"split");
                System.out.println("attrattrattrattr"+attr);

                ImageView iternext= (ImageView) iter1.next();
                litor_ImageView1.add(iternext);
                litor_ImageView1.get(i).setId(i);
                iternext.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        System.out.println("litor_ImageView1" +litor_ImageView1);

                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse(String.valueOf(MainActivity.json6_3sp[view.getId()])));
                        picture.this.startActivity(intent);

                        System.out.println("MainActivity.json6_33sp[MainActivity.count10]   " + MainActivity.json6_3sp[iternext.getId()]);
                        System.out.println("iternext.getId()   " +iternext.getId());

                        for(int i=0;i<MainActivity.json6_3sp.length;i++){
                            System.out.println("MainActivity.json6_3sp   " +MainActivity.json6_3sp[i]);
                        }
                    }
                });
            }
            MainActivity.json6_3sp = attr.toString().split("split");
        }


        if (MainActivity.staticmarker.getTag() == "click_m") {
            MainActivity.count10=0;
            ArrayList<ImageView> litor_ImageView2=new ArrayList<ImageView>();
            try {
                Iterator<ImageView> iter2 = litor_ImageView.iterator();
                while(iter2.hasNext() && MainActivity.count10<MainActivity.json6_2sp.length){
                    litor_ImageView.get(MainActivity.count10).setImageBitmap(MainActivity.getBitmapFromURL(MainActivity.geonggi6()));

                    ImageView iternext= (ImageView) iter2.next();
                    litor_ImageView2.add(iternext);
                    litor_ImageView2.get(MainActivity.count10).setId(MainActivity.count10);
                    System.out.println("litor_ImageView2.get(MainActivity.count10).getId())   " +litor_ImageView2.get(MainActivity.count10).getId());
                    System.out.println("MainActivity.count10  " +MainActivity.count10);
                    System.out.println("MainActivity.json6_2sp.length  " +MainActivity.json6_2sp.length);

                    iternext.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            System.out.println("litor_ImageView2" +litor_ImageView2);

                            Intent intent = new Intent(Intent.ACTION_VIEW);
                            intent.setData(Uri.parse(String.valueOf(MainActivity.json6_3sp[view.getId()])));
                            picture.this.startActivity(intent);

                            System.out.println("MainActivity.json6_33sp[MainActivity.count10]   " + MainActivity.json6_3sp[iternext.getId()]);
                            System.out.println("iternext.getId()   " +iternext.getId());
                        }
                    });
                    MainActivity.count10++;
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            MainActivity.json6_3sp = MainActivity.json6_3bd.toString().split("split");
        }
        if (MainActivity.staticmarker.getSnippet().contains("Aap")) {
            ArrayList<ImageView> litor_ImageView3=new ArrayList<ImageView>();
            String[] getsnip_parameter=new String[100];
            getsnip_parameter= MainActivity.staticmarker.getSnippet().split("split");

            Iterator<ImageView> iter3 = litor_ImageView.iterator();
            int i =0;
            while(iter3.hasNext()&&i<getsnip_parameter.length){
                            try {
                    StringBuilder urlBuilder = new StringBuilder("https://maps.googleapis.com/maps/api/place/photo"
                            + "?maxwidth=2500"
                            + "&photoreference=" + getsnip_parameter[i]
                            + "&key=AIzaSyCZfMZxF4Ds1j8uBwRBcbgOZJT1-1cxVio");

                    litor_ImageView.get(i).setImageBitmap(MainActivity.getBitmapFromURL(urlBuilder.toString()));

                  System.out.println("MainActivity.json6_3sp.length="+MainActivity.json6_3sp.length);
                                System.out.println("getsnip_parameter.length="+getsnip_parameter.length);
                                System.out.println("litor_ImageView="+litor_ImageView.size());

                    System.out.println();

                     ImageView iternext= (ImageView) iter3.next();
                     litor_ImageView3.add(iternext);
                     litor_ImageView3.get(i).setId(i);

                                iternext.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {

                                        System.out.println("litor_ImageView3" +litor_ImageView3);

                                        Intent intent = new Intent(Intent.ACTION_VIEW);
                                        intent.setData(Uri.parse(String.valueOf(MainActivity.json6_3sp[view.getId()])));
                                        picture.this.startActivity(intent);

                                        System.out.println("MainActivity.json6_33sp[MainActivity.count10]   " + MainActivity.json6_3sp[iternext.getId()]);
                                        System.out.println("iternext.getId()   " +iternext.getId());
                                    }
                                });
                     i++;
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            MainActivity.json6_3sp=MainActivity.staticmarker.getTag().toString().split("split");
        }
    }
}



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
import java.util.Locale;

public class picture extends AppCompatActivity implements View.OnClickListener{


    ImageView[] imageView_arr = new ImageView[100];
    int a = 0;

    FetchPhotoRequest photoRequest;
    PlacesClient placesClient;
    PhotoMetadata photoMetadata;
    int count=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture);


        imageView_arr[0] = findViewById(R.id.imageView0);
        imageView_arr[1] = findViewById(R.id.imageView1);
        imageView_arr[2] = findViewById(R.id.imageView2);
        imageView_arr[3] = findViewById(R.id.imageView3);
        imageView_arr[4] = findViewById(R.id.imageView4);
        imageView_arr[5] = findViewById(R.id.imageView5);
        imageView_arr[6] = findViewById(R.id.imageView6);
        imageView_arr[7] = findViewById(R.id.imageView7);
        imageView_arr[8] = findViewById(R.id.imageView8);
        imageView_arr[9] = findViewById(R.id.imageView9);
        imageView_arr[10] = findViewById(R.id.imageView10);
        imageView_arr[11] = findViewById(R.id.imageView11);
        imageView_arr[12] = findViewById(R.id.imageView12);
        imageView_arr[13] = findViewById(R.id.imageView13);
        imageView_arr[14] = findViewById(R.id.imageView14);
        imageView_arr[15] = findViewById(R.id.imageView15);
        imageView_arr[16] = findViewById(R.id.imageView16);
        imageView_arr[17] = findViewById(R.id.imageView17);



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
            for (int i = 0; i < MainActivity.place_var.getPhotoMetadatas().size(); i++) {
                photoMetadata = MainActivity.place_var.getPhotoMetadatas().get(i);
                photoRequest = FetchPhotoRequest.builder(photoMetadata)
                        .setMaxWidth(1500)
                        .setMaxHeight(1500)
                        .build();
                placesClient.fetchPhoto(photoRequest).addOnSuccessListener((fetchPhotoResponse) -> {
                    Bitmap bitmap0 = fetchPhotoResponse.getBitmap();
                    imageView_arr[a].setImageBitmap(bitmap0);
                    a++;

                });
                System.out.println("iaaaaaaaaaaaaaaaaa"+i);
                imageView_arr[i].setOnClickListener(this);
                String getAttributions=photoMetadata.getAttributions().substring(9,67);
                attr.append(getAttributions+"split");

                System.out.println("attrattrattrattr"+attr);
            }
            MainActivity.json6_3sp = attr.toString().split("split");
        }

        if (MainActivity.staticmarker.getTag() == "click_m") {
            MainActivity.count10 = 0;
            for (int i = 0; i < MainActivity.json6_2sp.length; i++) {

                try {

                    imageView_arr[MainActivity.count10].setImageBitmap(MainActivity.getBitmapFromURL(MainActivity.geonggi6()));

                    System.out.println("count10   " + MainActivity.count10);
                    MainActivity.count10++;
                    imageView_arr[i].setOnClickListener(this);



                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            MainActivity.json6_3sp = MainActivity.json6_3bd.toString().split("split");
        }


        if (MainActivity.staticmarker.getSnippet().contains("Aap")) {
            String[] getsnip_parameter=new String[100];
            getsnip_parameter= MainActivity.staticmarker.getSnippet().split("split");

            for (int i = 0; i < getsnip_parameter.length; i++) {
                try {
                    StringBuilder urlBuilder = new StringBuilder("https://maps.googleapis.com/maps/api/place/photo"
                            + "?maxwidth=2500"
                            + "&photoreference=" + getsnip_parameter[i]
                            + "&key=AIzaSyCZfMZxF4Ds1j8uBwRBcbgOZJT1-1cxVio");



                    imageView_arr[i].setImageBitmap(MainActivity.getBitmapFromURL(urlBuilder.toString()));

                    System.out.println("getsnip_parameter랑json6_3sp같은지확인 i="+i+getsnip_parameter[i]);
                    System.out.println();

                    imageView_arr[i].setOnClickListener(this);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            MainActivity.json6_3sp=MainActivity.staticmarker.getTag().toString().split("split");
        }


        for (int i = 0; i < MainActivity.json6_3sp.length; i++) {
            System.out.println(MainActivity.json6_3sp[i]+"\n");
        }


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case  R.id.imageView0:
                imageView_arr[0].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        Intent intent1 = getIntent().setAction(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse(String.valueOf(MainActivity.json6_3sp[0])));

                        picture.this.startActivity(intent);




                    }



                });
            case  R.id.imageView1:
                imageView_arr[1].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse(String.valueOf(MainActivity.json6_3sp[1])));
                        picture.this.startActivity(intent);
                    }
                });
            case  R.id.imageView2:
                imageView_arr[2].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse(String.valueOf(MainActivity.json6_3sp[2])));
                        picture.this.startActivity(intent);
                    }
                });
            case R.id.imageView3:
                imageView_arr[3].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse(String.valueOf(MainActivity.json6_3sp[3])));
                        picture.this.startActivity(intent);
                    }
                });
            case  R.id.imageView4:
                imageView_arr[4].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse(String.valueOf(MainActivity.json6_3sp[4])));
                        picture.this.startActivity(intent);
                    }
                });
            case  R.id.imageView5:
                imageView_arr[5].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse(String.valueOf(MainActivity.json6_3sp[5])));
                        picture.this.startActivity(intent);
                    }
                });
            case  R.id.imageView6:
                imageView_arr[6].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse(String.valueOf(MainActivity.json6_3sp[6])));
                        picture.this.startActivity(intent);
                    }
                });
            case  R.id.imageView7:
                imageView_arr[7].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse(String.valueOf(MainActivity.json6_3sp[7])));
                        picture.this.startActivity(intent);
                    }
                });
            case  R.id.imageView8:
                imageView_arr[8].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse(String.valueOf(MainActivity.json6_3sp[8])));
                        picture.this.startActivity(intent);
                    }
                });
            case  R.id.imageView9:
                imageView_arr[9].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse(String.valueOf(MainActivity.json6_3sp[9])));
                        picture.this.startActivity(intent);
                    }
                });
            case  R.id.imageView10:
                imageView_arr[10].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse(String.valueOf(MainActivity.json6_3sp[10])));
                        picture.this.startActivity(intent);
                    }
                });
            case  R.id.imageView11:
                imageView_arr[11].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse(String.valueOf(MainActivity.json6_3sp[11])));
                        picture.this.startActivity(intent);
                    }
                });

            case  R.id.imageView12:
                imageView_arr[11].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse(String.valueOf(MainActivity.json6_3sp[12])));
                        picture.this.startActivity(intent);
                    }
                });

            case  R.id.imageView13:
                imageView_arr[11].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse(String.valueOf(MainActivity.json6_3sp[13])));
                        picture.this.startActivity(intent);
                    }
                });

            case  R.id.imageView14:
                imageView_arr[11].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse(String.valueOf(MainActivity.json6_3sp[14])));
                        picture.this.startActivity(intent);
                    }
                });

            case  R.id.imageView15:
                imageView_arr[11].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse(String.valueOf(MainActivity.json6_3sp[15])));
                        picture.this.startActivity(intent);
                    }
                });

            case  R.id.imageView16:
                imageView_arr[11].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse(String.valueOf(MainActivity.json6_3sp[16])));
                        picture.this.startActivity(intent);
                    }
                });

            case  R.id.imageView17:
                imageView_arr[11].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse(String.valueOf(MainActivity.json6_3sp[17])));
                        picture.this.startActivity(intent);
                    }
                });

            case  R.id.imageView18:
                imageView_arr[11].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse(String.valueOf(MainActivity.json6_3sp[18])));
                        picture.this.startActivity(intent);
                    }
                });

            case  R.id.imageView19:
                imageView_arr[11].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse(String.valueOf(MainActivity.json6_3sp[19])));
                        picture.this.startActivity(intent);
                    }
                });

            case  R.id.imageView20:
                imageView_arr[11].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse(String.valueOf(MainActivity.json6_3sp[20])));
                        picture.this.startActivity(intent);
                    }
                });

            case  R.id.imageView21:
                imageView_arr[11].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse(String.valueOf(MainActivity.json6_3sp[21])));
                        picture.this.startActivity(intent);
                    }
                });









        }


    }


}



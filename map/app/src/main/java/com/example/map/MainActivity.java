package com.example.map;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;



import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.gms.common.api.ApiException;
import com.google.android.libraries.places.api.model.PhotoMetadata;
import com.google.android.libraries.places.api.net.FetchPhotoRequest;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;

import android.os.Build;
import android.os.StrictMode;
import android.text.Html;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import org.jetbrains.annotations.NotNull;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    public static GoogleMap map;
    private Geocoder geocoder;

    static int count = 0;
    static Marker[] marker_arr = new Marker[200];


    int count4 = 0;
    Marker[] m_arr = new Marker[200];
    static int i;
    public static LatLng search_point;
    public static LatLng marker_point;

    static View inflate_view;
    Button link2;
    Button link3;
    Button call;
    Button url;
    ImageView imageView;
    static Bitmap bitmap;
    PhotoMetadata photoMetadata;
    static Place place_var;

    String phone_var;
    Uri url_var;


    List<Address> adress = null;
    String adress_result;
    String adress_result2;
    String adress_phone;
    String adress_post;
    String adress_result5;
    String adress_result6;

    AutocompleteSupportFragment autocompleteFragment = null;

    static String json6_1 = new String();
    String json6_2 = new String();
    static String[] json6_2sp = new String[100];
    static StringBuilder json6_3bd = new StringBuilder();
    static String[] json6_3sp = new String[100];
    static int count10 = 0;
    static JSONArray json1 = new JSONArray();
    static Bitmap bitmap_str;
    static Bitmap bitmap_near;

    static Marker staticmarker;

    static String placeid = "";
    static String[] placeid_arr = new String[100];
    static JSONArray results = new JSONArray();
    static int count_near = 0;
    static StringBuilder json_bd = new StringBuilder();


    StringBuilder photoreperence_bd=new StringBuilder();
    static String phone;
    static String site;
    static String[] near_photoreperence_sp = new String[100];


    static String[] phone_str=new String[100];
    static String[] site_str=new String[100];

    static StringBuilder strbd=new StringBuilder();
    static StringBuilder strbd2=new StringBuilder();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if (Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        link2 = findViewById(R.id.link2);
        link2.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, streetview.class);
                MainActivity.this.startActivity(i);

            }
        });
        link3 = findViewById(R.id.link3);
        link3.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, picture.class);
                MainActivity.this.startActivity(i);

            }
        });

        call = findViewById(R.id.call);
        call.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phone_var));
                MainActivity.this.startActivity(i);

            }
        });

        url = findViewById(R.id.url);
        url.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(String.valueOf(url_var)));
                startActivity(intent);
            }
        });


    }


    @Override
    public void onMapReady(final GoogleMap googleMap) {
        map = googleMap;
        geocoder = new Geocoder(this, Locale.KOREA);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(37.566535, 126.9779692), 14));


        map.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
            @Nullable
            @org.jetbrains.annotations.Nullable
            @Override
            public View getInfoWindow(@NonNull @NotNull Marker marker) {
                staticmarker = marker;

                inflate_view = getLayoutInflater().inflate(R.layout.activity_adapter3, null);
                imageView = (ImageView) inflate_view.findViewById(R.id.imageView);
                TextView textView2 = (TextView) inflate_view.findViewById(R.id.textView2);

                ImageView nemo_b = (ImageView) inflate_view.findViewById(R.id.nemo_big);
                LinearLayout view = inflate_view.findViewById(R.id.linear_id);


                if (marker.getTag() == "click_m") {
                    System.out.println("click_m");

                        imageView.setImageBitmap(bitmap_str);

                    if (photoMetadata != null || bitmap_str != null && adress_result2 != null) {
                        link3.setVisibility(View.VISIBLE);
                    }

                    url.setVisibility(View.INVISIBLE);
                    call.setVisibility(View.INVISIBLE);


                }


                if (marker.getTag() == "search_m") {
                    System.out.println("search_m");
                    imageView.setImageBitmap(bitmap);
                    if (photoMetadata != null || bitmap_str != null) {
                        link3.setVisibility(View.VISIBLE);
                    }

                    textView2.getLayoutParams().width = 500;
                    textView2.getLayoutParams().height = 300;
                }


                if (marker.getTitle().contains("주소")) {
                    System.out.println("near_m");


                    String[] getsnip_parameter=new String[100];
                    getsnip_parameter= marker.getSnippet().split("split");

                    StringBuilder urlBuilder = new StringBuilder("https://maps.googleapis.com/maps/api/place/photo"
                            + "?maxwidth=2500"
                            + "&photoreference=" + getsnip_parameter[0]
                            + "&key=AIzaSyCZfMZxF4Ds1j8uBwRBcbgOZJT1-1cxVio");

                    bitmap_near = getBitmapFromURL(urlBuilder.toString());
                    System.out.println("getsnip_parameter"+getsnip_parameter[0]);
                    System.out.println("getSnippet"+marker.getSnippet());

                    imageView.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.img2));

                    if (!getsnip_parameter[0].equals("")) {
                        imageView.setImageBitmap(bitmap_near);
                        link3.setText("사진");
                        link3.setVisibility(View.VISIBLE);
                    }else{link3.setVisibility(View.INVISIBLE);}

                    int getid=0;
                    try{
                        getid= Integer.parseInt(marker.getId().substring(1,3));  }
                    catch (StringIndexOutOfBoundsException e){
                        getid= Integer.parseInt(marker.getId().substring(1,2));   }

                    for(int i=0;i<results.size();i++){
                        if(i==getid){
                            System.out.println("phone_str[i]"+phone_str[i]);
                            System.out.println("site_str[i]"+site_str[i]);


                            if (!phone_str[i].equals("null")) {
                                call.setVisibility(View.VISIBLE);
                                phone_var=phone_str[i];
                            }else{call.setVisibility(View.INVISIBLE);}
                            if (!site_str[i].equals("null")) {
                                url.setVisibility(View.VISIBLE);
                                Uri uri=Uri.parse(site_str[i]);
                                url_var=uri;
                            }else{url.setVisibility(View.INVISIBLE);}

                        }
                    }
                }


                    textView2.setText(marker.getTitle().replaceAll("null", ""));


                link2.setVisibility(View.VISIBLE);

                System.out.println("클릭된 모든마커의 위치" + marker.getPosition());
                marker_point = marker.getPosition();

                return inflate_view;
            }

            @Nullable
            @org.jetbrains.annotations.Nullable
            @Override
            public View getInfoContents(@NonNull @NotNull Marker marker) {
                return null;
            }
        });


        map.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onMapClick(@NonNull @NotNull LatLng latLng) {
                System.out.println("latLng" + latLng);
                link2.setVisibility(View.INVISIBLE);
                link3.setVisibility(View.INVISIBLE);
                call.setVisibility(View.INVISIBLE);
                url.setVisibility(View.INVISIBLE);


                try {
                    adress = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);
                    adress_result = adress.get(0).getAddressLine(0).toString();
                    adress_result2 = adress.get(0).getSubLocality().toString();


                } catch (NullPointerException | IOException | IndexOutOfBoundsException e) {
                    e.printStackTrace();
                    System.out.println("데이터없을때");

                    bitmap_str = BitmapFactory.decodeResource(getResources(), R.drawable.img2);


                    json1 = null;
                    json6_2sp[0] = null;
                    adress_result2 = null;
                }

                MarkerOptions markerOptions2 = new MarkerOptions();
                markerOptions2.position(latLng);
                m_arr[count4] = map.addMarker(markerOptions2);
                m_arr[count4].setTitle(adress_result);
                m_arr[count4].setTag("click_m");
                m_arr[count4].setSnippet("");
                if (count4 > 0) {
                    m_arr[count4 - 1].remove();
                }
                count4++;


                System.out.println("adress" + adress);
                System.out.println("getAddressLine=" + adress_result);
                System.out.println("getSubLocality=" + adress_result2);
                System.out.println("adress_phone=" + adress_phone);
                System.out.println("adress_post=" + adress_post);


                try {
                    geonggi1();
                    geonggi2();
                    geonggi3();
                    geonggi4();

                    geonggi5();

                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        });


//ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ검색자동완성ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ

        Places.initialize(getApplicationContext(), "AIzaSyCZfMZxF4Ds1j8uBwRBcbgOZJT1-1cxVio", Locale.KOREA);
        PlacesClient placesClient = Places.createClient(this);
        if (!Places.isInitialized()) {
            Places.initialize(getApplicationContext(), "AIzaSyCZfMZxF4Ds1j8uBwRBcbgOZJT1-1cxVio");
        }

        autocompleteFragment = (AutocompleteSupportFragment)
                getSupportFragmentManager().findFragmentById(R.id.autocomplete_fragment);


        autocompleteFragment.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.ADDRESS, Place.Field.NAME, Place.Field.LAT_LNG, Place.Field.PHOTO_METADATAS,
                Place.Field.WEBSITE_URI, Place.Field.PHONE_NUMBER));
        autocompleteFragment.setHint("검색하세요");


        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(@NonNull Place place) {
                place_var = place;
                System.out.println("setPlaceFields리턴=  " + place);




                search_point = new LatLng(place.getLatLng().latitude, place.getLatLng().longitude);
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(search_point, 15));
                MarkerOptions markerOptions2 = new MarkerOptions();
                markerOptions2.position(search_point);
                m_arr[count4] = map.addMarker(markerOptions2);

                if(place.getPhoneNumber()!=null) {
                    phone_var = place.getPhoneNumber();
                }
                if(place.getWebsiteUri()!=null) {
                    url_var = place.getWebsiteUri();
                }

                m_arr[count4].setTitle(place.getName() + "\n" + place.getAddress() + "\n" + phone_var + "\n" + url_var);

                m_arr[count4].setSnippet("");
                m_arr[count4].setTag("search_m");
                if (count4 > 0) {
                    m_arr[count4 - 1].remove();
                }
                count4++;


//ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ검색 PhotoMetadataㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
                try {
                    photoMetadata = place.getPhotoMetadatas().get(0);
                    final String attributions = photoMetadata.getAttributions();
                    System.out.println("attributions=" + attributions);

                    final FetchPhotoRequest photoRequest = FetchPhotoRequest.builder(photoMetadata)
                            .setMaxWidth(1500)
                            .setMaxHeight(1500)
                            .build();
                    placesClient.fetchPhoto(photoRequest).addOnSuccessListener((fetchPhotoResponse) -> {
                        bitmap = fetchPhotoResponse.getBitmap();


                    }).addOnFailureListener((exception) -> {
                        if (exception instanceof ApiException) {
                            final ApiException apiException = (ApiException) exception;
                            System.out.println("Place not found: " + exception.getMessage());
                            final int statusCode = apiException.getStatusCode();
                            System.out.println("statusCode=" + statusCode);
                        }
                    });

                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("오류" + e);
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.nophoto);

                }




            }


            @Override
            public void onError(@NonNull Status status) {
                // TODO: Handle the error.
                System.out.println("An error occurred: " + status);
            }


        });


        map.setOnCameraMoveStartedListener(new GoogleMap.OnCameraMoveStartedListener() {
            @Override
            public void onCameraMoveStarted(int i) {
                System.out.println("onCameraMoveStarted" + i);


            }
        });

        try {
            near1();
            near2();
            for(int i=0;i<results.size();i++) {
                near3();
                near4();
                System.out.println("count_near"+count_near);
                count_near++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    public String geonggi1() throws IOException {

        String asd = adress_result2.replaceAll(" ", "+");

        System.out.print("좌표구역" + asd + adress_result2);
        StringBuilder urlBuilder = new StringBuilder("https://maps.googleapis.com/maps/api/place/textsearch/json"
                + "?query=" + adress_result2
                + "&key=AIzaSyCZfMZxF4Ds1j8uBwRBcbgOZJT1-1cxVio"
                + "&language=ko"
        );


        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line + "\n");
        }

        rd.close();
        conn.disconnect();
        return sb.toString();
    }

    public void geonggi2() throws Exception {


        StringBuilder json6_2bd = new StringBuilder();

        System.out.print("장소검색" + geonggi1());

        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(geonggi1());
        JSONArray json0 = (JSONArray) jsonObject.get("results");


        for (int i = 0; i < json0.size(); i++) {
            JSONObject json5 = (JSONObject) json0.get(i);
            json6_1 = (String) json5.get("place_id");
            System.out.println("json0.size()" + json0.size());

            json6_2bd.append(json6_1);
        }


    }

    public String geonggi3() throws IOException {
        StringBuilder urlBuilder = new StringBuilder("https://maps.googleapis.com/maps/api/place/details/json"
                + "?place_id=" + json6_1
                + "&key=AIzaSyCZfMZxF4Ds1j8uBwRBcbgOZJT1-1cxVio"
                + "&language=ko"
        );

        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line + "\n");
        }

        rd.close();
        conn.disconnect();
        return sb.toString();
    }

    public void geonggi4() throws Exception {
        StringBuilder json6_2bd = new StringBuilder();

        System.out.print("\n\n\n\n\n\n\n장소상세검색" + geonggi3());

        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(geonggi3());
        JSONObject json0 = (JSONObject) jsonObject.get("result");

        json1 = (JSONArray) json0.get("photos");
        for (int i = 0; i < json1.size(); i++) {
            JSONObject json5 = (JSONObject) json1.get(i);
            String json6_1 = (String) json5.get("photo_reference");

            JSONArray html_attributions = (JSONArray) json5.get("html_attributions");
            for (int j = 0; j < html_attributions.size(); j++) {
                String href_String = (String) html_attributions.get(0);
                String href_cut = href_String.substring(9, 67);
                json6_3bd.append(href_cut + "split");
            }
            json6_2bd = json6_2bd.append(json6_1 + "split");
        }


        System.out.println("json6_2bd" + json6_2bd);
        System.out.println("json6_3bd" + json6_3bd);
        json6_2sp = json6_2bd.toString().split("split");


    }

    public void geonggi5() throws IOException {
        StringBuilder urlBuilder = new StringBuilder("https://maps.googleapis.com/maps/api/place/photo"
                + "?maxwidth=2500"
                + "&photoreference=" + json6_2sp[0]
                + "&key=AIzaSyCZfMZxF4Ds1j8uBwRBcbgOZJT1-1cxVio"

        );

            bitmap_str = getBitmapFromURL(urlBuilder.toString());

    }

    public static String geonggi6() throws IOException {
        StringBuilder urlBuilder = new StringBuilder("https://maps.googleapis.com/maps/api/place/photo"
                + "?maxwidth=2000"
                + "&photoreference=" + json6_2sp[count10]
                + "&key=AIzaSyCZfMZxF4Ds1j8uBwRBcbgOZJT1-1cxVio"

        );

        return urlBuilder.toString();
    }

    public static Bitmap getBitmapFromURL(String src) {
        try {
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            System.out.println("Response code: " + connection.getResponseCode());
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {

            return null;
        }
    }

    public static String near1() throws IOException {



        StringBuilder urlBuilder = new StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json"
                + "?location=37.566535, 126.9779692"
                + "&radius=500"
                + "&key=AIzaSyCZfMZxF4Ds1j8uBwRBcbgOZJT1-1cxVio"
                + "&language=ko"

        );


        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line + "\n");
        }

        rd.close();
        conn.disconnect();
        return sb.toString();
    }

    public static void near2() throws Exception {

        System.out.print("주변검색" + near1());

        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(near1());
        results = (JSONArray) jsonObject.get("results");

        for (int i = 0; i < results.size(); i++) {
            JSONObject json5 = (JSONObject) results.get(i);
            placeid_arr[i] = (String) json5.get("place_id");

        }


    }

    public static String near3() throws IOException {
        StringBuilder urlBuilder = new StringBuilder("https://maps.googleapis.com/maps/api/place/details/json"
                + "?place_id=" + placeid_arr[count_near]
                + "&key=AIzaSyCZfMZxF4Ds1j8uBwRBcbgOZJT1-1cxVio"
                + "&language=ko"
        );


        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line + "\n");
        }

        rd.close();
        conn.disconnect();
        return sb.toString();
    }

    public static void near4() throws Exception   {


        Boolean open_now;
        String photo_reference="";
        String	adress="";
        String today_openning = null;
        String html_attribution="";
        String Double_rating="";
        String Long_rating="";
        JSONArray photos=new JSONArray();
        String site_local="";
        String phone_local="";


        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject)jsonParser.parse(near3());
        JSONObject json0 = (JSONObject)jsonObject.get("result");


         StringBuilder near_photoreperence_bd = new StringBuilder();
        StringBuilder near_attribute_bd = new StringBuilder();


        try {
            phone=(String)json0.get("international_phone_number");
            if(phone!=null){
                phone_local="전화: "+phone;}

            adress=(String)json0.get("formatted_address");

            site=(String)json0.get("website");
            if(site!=null){
                site_local="홈페이지: "+site;}

            photos= (JSONArray)json0.get("photos");

            for (int k = 0; k < photos.size(); k++) {
                JSONObject photos_Array = (JSONObject)photos.get(k);
                photo_reference= (String)photos_Array.get("photo_reference");
                near_photoreperence_bd.append(photo_reference+"split");


                JSONArray html_attributions = (JSONArray) photos_Array.get("html_attributions");
                for (int j = 0; j < html_attributions.size(); j++) {
                    String href_String = (String) html_attributions.get(0);
                    String href_String_cut=href_String.substring(9,67);
                    near_attribute_bd.append(href_String_cut+"split");
                }
            }

        }catch(NullPointerException e) {
            System.out.println(e);
            //phone=null; site=null;
            //adress=null;photo_reference=null;
        }

        JSONObject geometry= (JSONObject)json0.get("geometry");
        JSONObject location= (JSONObject)geometry.get("location");
        Double lat= (Double)location.get("lat");
        Double lng= (Double)location.get("lng");

        String icon= (String)json0.get("icon");

        String name= (String)json0.get("name");




        JSONObject opening_hours= (JSONObject)json0.get("opening_hours");
        JSONArray weekopen=new JSONArray();
        Calendar cal = Calendar.getInstance();
        Date day = cal.getTime();
        SimpleDateFormat sdf1 = new SimpleDateFormat("E요일", Locale.KOREAN);
        String[] asd=new String[100];
        try {
            weekopen= (JSONArray)opening_hours.get("weekday_text");
            }catch(NullPointerException e) {
                today_openning=null;
                 }
        for(int i=0;i<weekopen.size();i++) {
            asd[i]=(String)weekopen.get(i);
            if(asd[i].contains(sdf1.format(day))) {
                today_openning=asd[i];
                System.out.print("asd[i]"+asd[i]);
            }
        }


        try {
            Double rating_double= (Double)json0.get("rating");
            if(rating_double!=null){
            Double_rating="별점: "+rating_double;}
        }catch(ClassCastException e){
            System.out.print("ClassCastException"+e);

            Long rating_long=(Long)json0.get("rating");
            if(Long_rating!=null){
            Long_rating="별점: "+rating_long;}
        }









        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(new LatLng(lat, lng));
        Bitmap b =getBitmapFromURL(icon);
        Bitmap bitmarker = Bitmap.createScaledBitmap(b, 50, 50, false);
        markerOptions.icon(BitmapDescriptorFactory.fromBitmap(bitmarker));

        Marker near_marker=map.addMarker(markerOptions);


        near_marker.setSnippet(near_photoreperence_bd.toString());

        near_photoreperence_sp=near_marker.getSnippet().split("split");

        near_marker.setTag(near_attribute_bd);


        if(today_openning!=null) {
            near_marker.setTitle(name + "\n" + "주소: " + adress + "\n" + today_openning + "\n" + Double_rating + Long_rating + "\n" + phone_local + "\n" + site_local);
        }else {
            near_marker.setTitle(name + "\n" + "주소: " + adress + "\n" + Double_rating + Long_rating + "\n" + phone_local + "\n" + site_local);
        }



        phone_str=strbd.append(phone+"split").toString().split("split");


        site_str=strbd2.append(site+"split").toString().split("split");





    }




}
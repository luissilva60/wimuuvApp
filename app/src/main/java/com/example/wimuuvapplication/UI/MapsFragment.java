package com.example.wimuuvapplication.UI;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Looper;
import android.text.Spannable;
import android.text.TextUtils;
import android.text.style.URLSpan;
import android.text.util.Linkify;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import android.provider.Settings;


import com.directions.route.AbstractRouting;
import com.directions.route.Routing;
import com.example.wimuuvapplication.R;
import com.example.wimuuvapplication.UI.directions.Route;
import com.example.wimuuvapplication.UI.directions.RouteException;
import com.example.wimuuvapplication.UI.directions.RoutingListener;
import com.example.wimuuvapplication.downloaders.JSONArrayDownloader;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;


public class MapsFragment extends Fragment implements GoogleMap.OnMarkerClickListener, GoogleMap.OnInfoWindowClickListener, RoutingListener  {
    double tvLatitude, tvLongitude;
    FusedLocationProviderClient client;
    private ArrayList<Integer> spotId;
    private ArrayList<String> spotName;

    private ArrayList<String> spotDescription;
    private ArrayList<LatLng> spotlocation;
    private ArrayList<Marker> markers;
    private GoogleMap googleMap;
    private JSONArray objspots;

    private List<Polyline> polylines = null;
    private static final int[] COLORS = new int[]{R.color.primary_dark_material_light};

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MapsInitializer.initialize(getContext());

        polylines = new ArrayList<>();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {




        //download spots

        JSONArrayDownloader task = new JSONArrayDownloader();
        try {
            objspots = task.execute("https://wimuuv.herokuapp.com/api/spot").get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        JSONObject obj;
        spotId = new ArrayList<>();
        spotName = new ArrayList<>();
        spotDescription = new ArrayList<>();
        spotlocation = new ArrayList<>();
        if(objspots != null) {
            for(int i = 0; i < objspots.length(); i++) {
                try {
                    obj = objspots.getJSONObject(i);
                    spotId.add(obj.getInt("id"));
                    spotName.add(obj.getString("name"));
                    spotlocation.add(new LatLng(Double.parseDouble(obj.getString("latitude")),
                            Double.parseDouble(obj.getString("longitude"))));
                    spotDescription.add(obj.getString("description"));
                }
                catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }
        //Log.e("Array List", spots.toString());

        // Initialize view
        View view = inflater.inflate(R.layout.fragment_maps, container, false);


        // Initialize map fragment
        SupportMapFragment supportMapFragment = (SupportMapFragment)
                getChildFragmentManager().findFragmentById(R.id.mapsFragment);


        // Async map
        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull GoogleMap googleMap) {
                LatLng santos = new LatLng(38.70843814152426, -9.15501526730533);

                if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                googleMap.setMyLocationEnabled(true);

                markers = new ArrayList<>();
                for(int i = 0; i < spotlocation.size(); i++){
                    markers.add(googleMap.addMarker(new MarkerOptions().position(spotlocation.get(i)).title(spotName.get(i)).snippet(spotDescription.get(i)).icon
                            (BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA))));
                    Log.e("error", "add marker in : " + spotlocation);
                }
                googleMap.setOnInfoWindowClickListener(MapsFragment.this);
                googleMap.setOnMarkerClickListener(MapsFragment.this);
                ///
                //
                //Marcadores dos spots
                //                    mMap.addMarker(new MarkerOptions()
                //                            .position(Spots)
                //                            .title(spotname1)
                //                            .snippet(spotdescription)
                //                            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA)));
                //


                //mMap.setMyLocationEnabled(true);
                googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                //When map is loaded
                LatLng iade = new LatLng(38.707300302202206, -9.152475617141915);
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(iade, 16));
                Log.e("YOOOOOOOOOOOOOO","marker in iade" + iade  );




                googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                    @Override
                    public void onMapClick(@NonNull LatLng latLng) {
                        // When clicked on map
                        // Initialize marker options
                        MarkerOptions markerOptions = new MarkerOptions();
                        // Set position of marker
                        markerOptions.position(latLng);
                        // Set title of marker
                        markerOptions.title(latLng.latitude + " : " + latLng.longitude);

                        // Animating to zoom the marker
                        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
                        //Add marker on map
                        //googleMap.clear();

                        googleMap.addMarker(markerOptions);




                    }
                });
            }
        });
        //Initialize Location client
        client = LocationServices.getFusedLocationProviderClient(getActivity());
        //Check condition
        if (ContextCompat.checkSelfPermission(getActivity()
                , Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(getActivity()
                        , Manifest.permission.ACCESS_COARSE_LOCATION)
                        == PackageManager.PERMISSION_GRANTED) {
            //When permission is granted
            //Call method
            getCurrentLocation();
        }else{
            //When permissiojn is not granted
            //REquest permission
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 100);
        }

        return view;


    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //Check condition
        if(requestCode == 100 && (grantResults.length > 0) &&(grantResults[0] + grantResults[1] == PackageManager.PERMISSION_GRANTED)){
            //When permission is granted
            //Call method
            getCurrentLocation();
        }else{
            //When permission is denied
            //Display toast
            Toast.makeText(getActivity(), "Permission Denied", Toast.LENGTH_SHORT).show();
        }
    }

    @SuppressLint("MissingPermission")
    private void getCurrentLocation() {
        //Initialize location manager
        LocationManager locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        //check condition
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
                || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            //When location service is enabled
            //Get last location
            client.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
                @Override
                public void onComplete(@NonNull Task<Location> task) {
                    //Initialize location
                    Location location = task.getResult();
                    //Check condition
                    if(location != null){
                        //When Location result is not null
                        //Set latitude
                        tvLatitude = location.getLatitude();
                        Log.e("INFOOO", ""+location.getLatitude());
                        //Set longitude
                        tvLongitude = location.getLongitude();
                        Log.e("INFOO", ""+location.getLongitude());
                    }else{
                        //When location result is null
                        //Initialize location request
                        com.google.android.gms.location.LocationRequest locationRequest = new com.google.android.gms.location.LocationRequest();
                        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                                .setInterval(10000)
                                .setFastestInterval(1000)
                                .setNumUpdates(1);
                        //Initialize location call back
                        LocationCallback locationCallback = new LocationCallback() {
                            @Override
                            public void onLocationResult(@NonNull LocationResult locationResult) {
                                //Initialize location
                                Location location1 = locationResult.getLastLocation();
                                //set latitude
                                tvLatitude = location.getLatitude();
                                //Set longitude
                                tvLongitude = location.getLongitude();
                            }
                        };
                        //Request location updates
                        client.requestLocationUpdates(locationRequest, locationCallback, Looper.myLooper());

                    }
                }

            });
        }else{
            //When location service is not enabled
            //Open location setting
            startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
        }

    }

    @Override
    public boolean onMarkerClick(@NonNull Marker marker) {

        marker.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));


        for (int i = 0; i < markers.size(); i++){
            markers.get(i).setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));
            if (markers.get(i).getId().equals(marker.getId())) {

                LatLng location = spotlocation.get(i);
                Log.e("sadasdsadsadasda", "spots location: "+ location );
                //getRouteToMarker(location);
            }
        }


        




        return false;
    }

    private void getRouteToMarker(LatLng location) {
        Routing routing = new Routing.Builder()
                .key("AIzaSyDVe28Yx5jnxbaE6HyGVdmly60yIS5k2Io")
                .travelMode(AbstractRouting.TravelMode.WALKING)
                .withListener((com.directions.route.RoutingListener) this)
                .alternativeRoutes(false)
                .waypoints(new LatLng(tvLatitude, tvLongitude), location)
                .build();
        routing.execute();


    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        Intent intent = new Intent(getContext(),SpotDetailsActivity.class);
        for (int i = 0; i < markers.size(); i++){
            if (markers.get(i).getId().equals(marker.getId())) {
                int id = spotId.get(i);
                intent.putExtra("id", id);
                Log.e("esaeseasdsadsa", "Spot id: "+ id );
            }
        }
        startActivity(intent);
    }

    @Override
    public void onRoutingFailure(RouteException e) {
        if(e != null) {
            Toast.makeText(getContext(), "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(getContext(), "Something went wrong, Try again", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRoutingStart() {

    }

    @Override
    public void onRoutingSuccess(List<Route> route, int shortestRouteIndex) {
        CameraUpdate center = CameraUpdateFactory.newLatLng(new LatLng(tvLatitude, tvLongitude));
        CameraUpdate zoom = CameraUpdateFactory.zoomTo(16);
        if(polylines.size()>0) {
            for (Polyline poly : polylines) {
                poly.remove();
            }
        }

        polylines = new ArrayList<>();
        //add route(s) to the map.
        for (int i = 0; i <route.size(); i++) {

            //In case of more than 5 alternative routes
            int colorIndex = i % COLORS.length;

            PolylineOptions polyOptions = new PolylineOptions();
            polyOptions.color(getResources().getColor(COLORS[colorIndex]));
            polyOptions.width(10 + i * 3);
            polyOptions.addAll(route.get(i).getPoints());
            Polyline polyline = googleMap.addPolyline(polyOptions);
            polylines.add(polyline);

            Toast.makeText(getContext(),"Route "+ (i+1) +": distance - "+ route.get(i).getDistanceValue()+": duration - "+ route.get(i).getDurationValue(),Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRoutingCancelled() {

    }

    private void erasePolylines(){
        for(Polyline line : polylines){
            line.remove();
        }
        polylines.clear();
    }


    /*private BitmapDescriptor bitmapDescriptorFromVector (Context context, int vectorResId) {
        Drawable vectorDrawable -ContextCompat.getDrawable(getContext(),);
        vectorDrawable.setBounds(left=0, top:0, vectorDrawable.getIntrinsicWidth(),
                vectorDrawable.getIntrinsicHeight());
        Bitmap bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(),
                vectorDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        vectorDrawable.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }*/
}
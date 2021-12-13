package com.example.wimuuvapplication.UI.directions;

import com.example.wimuuvapplication.UI.directions.Route;

import java.util.List;

public interface RoutingListener {
    void onRoutingFailure(RouteException e);

    void onRoutingStart();

    void onRoutingSuccess(List<Route> route, int shortestRouteIndex);

    void onRoutingCancelled();
}

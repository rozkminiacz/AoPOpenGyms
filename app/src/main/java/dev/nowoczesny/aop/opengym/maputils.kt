package dev.nowoczesny.aop.opengym

import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds

private val POLAND_LATLNG_BOUNDS = LatLngBounds(LatLng(48.0, 18.0), LatLng(53.0, 21.0))

val PlaceListState.latLngBounds: LatLngBounds
    get() {
        return if (gymList.isEmpty()) POLAND_LATLNG_BOUNDS
        else {
            LatLngBounds.builder().apply {
                this@latLngBounds.gymList.forEach {
                    this.include(it.toLatLng())
                }
            }.build()
        }
    }
/*
 * Copyright 2013 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cc.bikeon.app.internal.decoder;

import java.util.ArrayList;
import java.util.List;

import cc.bikeon.app.domain.directions.Coordinate;

/**
 * Responsible for decode Polyline
 *
 * This algorithm is the same used on:
 * https://github.com/googlemaps/android-maps-utils
 *
 * Created by cristianoliveira on 28/08/15.
 */
public class PolylinePointDecoder {

    public List<Coordinate> decode(String encodedPolyline) {
        int len = encodedPolyline.length();

        // For speed we preallocate to an upper bound on the final length, then
        // truncate the array before returning.
        final List<Coordinate> path = new ArrayList<Coordinate>();
        int index = 0;
        int lat = 0;
        int lng = 0;

        while (index < len) {
            int result = 1;
            int shift = 0;
            int b;
            do {
                b = encodedPolyline.charAt(index++) - 63 - 1;
                result += b << shift;
                shift += 5;
            } while (b >= 0x1f);
            lat += (result & 1) != 0 ? ~(result >> 1) : (result >> 1);

            result = 1;
            shift = 0;
            do {
                b = encodedPolyline.charAt(index++) - 63 - 1;
                result += b << shift;
                shift += 5;
            } while (b >= 0x1f);
            lng += (result & 1) != 0 ? ~(result >> 1) : (result >> 1);

            path.add(new Coordinate(lat * 1e-5, lng * 1e-5));
        }

        return path;
    }
}

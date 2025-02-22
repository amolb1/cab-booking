package com.cabbooking.core.service.impl;

import com.cabbooking.core.service.AreaService;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@Service
public class AreaServiceImpl implements AreaService {

    private static final double CELL_SIZE_KM = 2.0; // Each cell is 2 km x 2 km (4 sq km)
    private static final double KM_PER_DEGREE_LAT = 111.32;

    // City boundaries and starting area codes
    private static final Map<String, City> cities = new HashMap<>();

    //TODO this will be in DB persistant storage with redis
    static {
        cities.put("San Francisco", new City("San Francisco",37.7034, 37.8120, -122.527, -122.357, 1000));
        cities.put("New York", new City("New York",40.4774, 40.9176, -74.2591, -73.7004, 2000));
        // Add more cities as needed
    }

    private static final Map<String, String> areaCodeMap = new HashMap<>();


   /**
    public static void main(String[] args) {
        assignAreaCodes();
        double latitudeSF = 37.7749;
        double longitudeSF = -122.4194;
        String areaCodeSF = getAreaCodeForCoordinates(latitudeSF, longitudeSF);
        System.out.println("San Francisco Area Code: " + areaCodeSF);

        double latitudeNY = 40.7128;
        double longitudeNY = -74.0060;
        String areaCodeNY = getAreaCodeForCoordinates(latitudeNY, longitudeNY);
        System.out.println("New York Area Code: " + areaCodeNY);
    }**/


    @PostConstruct
    private void assignAreaCodes() {
        for (City city : cities.values()) {
            double kmPerDegreeLng = 111.32 * Math.cos(Math.toRadians((city.latMin + city.latMax) / 2));
            int areaCodeCounter = city.startingAreaCode;

            for (double lat = city.latMin; lat < city.latMax; lat += CELL_SIZE_KM / KM_PER_DEGREE_LAT) {
                for (double lng = city.lngMin; lng < city.lngMax; lng += CELL_SIZE_KM / kmPerDegreeLng) {
                    String cellKey = generateCellKey(city.name, lat, lng);
                    areaCodeMap.put(cellKey, String.valueOf(areaCodeCounter++));
                }
            }
        }
    }


    /**
     *  Search areacode for a particualar city
     * @param latitude
     * @param longitude
     * @param cityName
     * @return
     */
    public Mono<String> getAreaCodeForCityCoordinates(double latitude, double longitude, String cityName) {
        City city = cities.get(cityName);
        if (city == null) {
            return Mono.just("Unknown");
        }

        if (latitude >= city.latMin && latitude <= city.latMax && longitude >= city.lngMin && longitude <= city.lngMax) {
            String cellKey = generateCellKey(city.name, latitude, longitude);
            return Mono.just(areaCodeMap.getOrDefault(cellKey, "Unknown"));
        } else {
            return Mono.just("Unknown");
        }
    }

    /**
     *  Get Areacode by searching in all cities
     * @param latitude
     * @param longitude
     * @return
     */
    public Mono<String> getAreaCodeForCoordinates(double latitude, double longitude) {
        for (City city : cities.values()) {
            if (latitude >= city.latMin && latitude <= city.latMax && longitude >= city.lngMin && longitude <= city.lngMax) {
                String cellKey = generateCellKey(city.name, latitude, longitude);
                return Mono.just(areaCodeMap.getOrDefault(cellKey, "Unknown"));
            }
        }
        return Mono.just("Unknown");
    }

    private static String generateCellKey(String cityName, double latitude, double longitude) {
        int latIndex = (int) ((latitude - cities.get(cityName).latMin) / (CELL_SIZE_KM / KM_PER_DEGREE_LAT));
        int lngIndex = (int) ((longitude - cities.get(cityName).lngMin) / (111.32 * Math.cos(Math.toRadians((cities.get(cityName).latMin + cities.get(cityName).latMax) / 2))));
        return cityName + "_" + latIndex + "_" + lngIndex;
    }

    static class City {
        String name;
        double latMin;
        double latMax;
        double lngMin;
        double lngMax;
        int startingAreaCode;

        public City(String name, double latMin, double latMax, double lngMin, double lngMax, int startingAreaCode) {
            this.latMin = latMin;
            this.latMax = latMax;
            this.lngMin = lngMin;
            this.lngMax = lngMax;
            this.startingAreaCode = startingAreaCode;
            this.name = name;
        }
    }
}

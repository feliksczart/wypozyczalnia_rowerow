package bikerent.service.bikerentingapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BikeRentingApplication {

    public static void main(String[] args) {
        //TNS
        System.setProperty("oracle.net.tns_admin",
                "C:/Users/czart/Downloads/WINDOWS.X64_193000_db_home/network/admin");

        SpringApplication.run(BikeRentingApplication.class, args);
    }

}
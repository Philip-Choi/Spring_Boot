package ca.cmpt213.asn5.Tokimon;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/*
Rest controller for MVC. This RESTful API handles the @RequestMapping
which are requested from client through localhost.
This controller handles five @RequesMapping methods: GET for all, GET for specific id, POST and DELETE.
It has PostConstruct which uploads json data from the json file to the server.

The ID of the tokimon starts from 1
 */

@RestController
public class TokimonController {
        //global variable
        private List<Tokimon> tokimonList = new ArrayList<>();

        //initialization
        @PostConstruct
        public ResponseEntity<String> init() {
                try {
                        BufferedReader bufferedReader = new BufferedReader(new FileReader("data/tokimon.json"));
                        Gson gson = new Gson();
                        JsonArray ja = gson.fromJson(bufferedReader, JsonArray.class);
                        bufferedReader.close();
                        for (JsonElement obj: ja){
                                Tokimon tokimon = gson.fromJson(obj, Tokimon.class);
                                tokimonList.add(tokimon);
                        }

                } catch (IOException e) {
                    System.out.println("Failed loading JSON file. JSON file might not exist " +
                            "if you did not save any data. Check for the directory or file itself");
                }
                return new ResponseEntity<String>(HttpStatus.OK);
        }

        // get mapping for all
        @GetMapping("/api/tokimon/all")
        public ResponseEntity<List<Tokimon>> getAllTokimon(){
                return new ResponseEntity<List<Tokimon>>(tokimonList,HttpStatus.OK);
        }

        // get mapping for specific id
        @GetMapping("/api/tokimon/{id}")
        @ResponseBody
        public ResponseEntity<Tokimon> returnSpecificTokimon(@PathVariable int id) {

                if (id > tokimonList.size()){
                    System.out.println("Invalid id received. Require valid id");
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }
                return new ResponseEntity<Tokimon>(tokimonList.get(id-1),HttpStatus.OK);
        }

        // receives json format as string, parse, and adds to tokimonList. Then update the file
        @PostMapping("/api/tokimon/add")
        public ResponseEntity<String> addTokimon(@RequestBody String string){
                Gson gson = new Gson();
                Type type = new TypeToken<Map<String, String>>(){}.getType();
                //parse
                Map<String, String> map = gson.fromJson(string, type);
                String name = (String) map.get("name");
                float weight = Float.parseFloat((String) map.get("weight"));
                float height = Float.parseFloat((String) map.get("height"));
                String ability = (String) map.get("ability");
                int strength = Integer.parseInt((String) map.get("strength"));
                String color = (String) map.get("color");
                String gender = map.get("gender");

                Tokimon tokimon = new Tokimon(name, weight, height, ability, strength, color, gender);
                tokimonList.add(tokimon);

                try {
                        Writer writer = new FileWriter("data/tokimon.json");
                        gson.toJson(tokimonList, writer);
                        writer.flush();
                        writer.close();
                } catch (IOException e){
                        System.out.println("Incorrect json file entered or file missing");
                }
                return new ResponseEntity<String>(HttpStatus.CREATED);
        }

        // delete tokimon at specific id with index error handling. Then updates the json file
        @DeleteMapping("/api/tokimon/{id}")
        public ResponseEntity<String> deleteTokimon(@PathVariable int id){
                if (id > tokimonList.size()){
                    System.out.println("Invalid id received. Require valid id");
                    return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
                }
                else {
                        tokimonList.remove(id - 1);
                        try {
                                Writer writer = new FileWriter("data/tokimon.json");
                                Gson gson = new Gson();
                                gson.toJson(tokimonList, writer);
                                writer.flush();
                                writer.close();
                        } catch (IOException e){
                                System.out.println("Incorrect json file entered or missing");
                        }
                }
                return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
        }
}

package com.example.mongorest.model;

import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;

@Data
@Document(collection = "car")
public class CarModel {
//	  private String id; 
	  private String maker;
	  private String model;
	  private String description;
}

/*
use MongoREST
db.createCollection("car")
db.car.remove({})
db.car.insert([
	{"maker":"BMW"          , "model":"4 Series" , "description":null},
	{"maker":"Mercedes Benz", "model":"E Class"  , "description":null},
	{"maker":"HyunDai"      , "model":"VelosterN", "description":null},
	{"maker":"KIA"          , "model":"K9"       , "description":null},
])
*/
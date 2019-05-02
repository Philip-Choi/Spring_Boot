package ca.cmpt213.asn5.Tokimon;

import java.awt.*;
import java.io.Serializable;

/*
JavaBean for Tokimon. It contains format required for tokimon object
 */

public class Tokimon implements Serializable {

    private String name;
    private  float weight;
    private  float height;
    private  String ability;
    private  int strength;
    private String color;
    private String gender;

    public Tokimon(String name, float weight, float height, String ability, int strength, String color, String gender){
        this.name = name;
        this.weight = weight;
        this.height = height;
        this.ability = ability;
        this.strength = strength;
        this.color = color;
        this.gender = gender;
    }

    public String getTokimonName(){
        return this.name;
    }
    public float getTokimonWeight(){
        return this.weight;
    }
    public float getTokimonHeight(){
        return this.height;
    }
    public String getTokimonAbility(){
        return this.ability;
    }
    public int getTokimonStrength(){
        return this.strength;
    }
    public String getTokimonColor(){
        return this.color;
    }
    public String gender(){
        return this.gender;
    }

    public void setTokimonName(String name) {
        this.name = name;
    }
    public void setTokimonWeight(float weight){
        this.weight = weight;
    }
    public void setTokimonHeight(float height){
        this.height = height;
    }
    public void setTokimonAbility(String ability){
        this.ability = ability;
    }
    public void setTokimonStrength(int strength){
        this.strength = strength;
    }
    public void setTokimonColor(String color){
        this.color = color;
    }
    public void setTokimonGender(String gender) {
        this.gender= gender;
    }

}

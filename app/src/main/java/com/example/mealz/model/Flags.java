package com.example.mealz.model;

public class Flags {
    public static String getFlag (String name){
        if(name.equals("American"))return "https://www.worldometers.info/img/flags/us-flag.gif";
        else if(name.equals("British"))return "https://flagsapi.com/GB/flat/64.png";
        else if(name.equals("Canadian"))return "https://www.worldometers.info/img/flags/ca-flag.gif";
        else if(name.equals("Chinese"))return "https://www.worldometers.info/img/flags/ch-flag.gif";
        else if(name.equals("Croatian"))return "https://www.worldometers.info/img/flags/hr-flag.gif";
        else if(name.equals("Dutch"))return "https://flagsapi.com/DO/flat/64.png";
        else if(name.equals("Egyptian"))return "https://www.worldometers.info/img/flags/eg-flag.gif";
        else if(name.equals("Filipino"))return "https://flagsapi.com/FJ/flat/64.png";
        else if(name.equals("French"))return "https://www.worldometers.info/img/flags/fr-flag.gif";
        else if(name.equals("Greek"))return "https://flagsapi.com/GR/flat/64.png";
        else if(name.equals("Indian"))return "https://flagsapi.com/IN/flat/64.png";
        else if(name.equals("Irish"))return "https://flagsapi.com/IR/flat/64.png";
        else if(name.equals("Italian"))return "https://flagsapi.com/IT/flat/64.png";
        else if(name.equals("Jamaican"))return "https://flagsapi.com/JM/flat/64.png";
        else if(name.equals("Japanese"))return "https://flagsapi.com/JP/flat/64.png";
        else if(name.equals("Kenyan"))return "https://flagsapi.com/KN/flat/64.png";
        else if(name.equals("Malaysian"))return "https://flagsapi.com/MY/flat/64.png";
        else if(name.equals("Mexican"))return "https://flagsapi.com/MX/flat/64.png";
        else if(name.equals("Moroccan"))return "https://flagsapi.com/MA/flat/64.png";
        else if(name.equals("Polish"))return "https://flagsapi.com/PL/flat/64.png";
        else if(name.equals("Portuguese"))return "https://flagsapi.com/PT/flat/64.png";
        else if(name.equals("Russian"))return "https://flagsapi.com/RU/flat/64.png";
        else if(name.equals("Spanish"))return "https://flagsapi.com/ES/flat/64.png";
        else if(name.equals("Thai"))return "https://flagsapi.com/TH/flat/64.png";
        else if(name.equals("Tunisian"))return "https://flagsapi.com/TN/flat/64.png";
        else if(name.equals("Turkish"))return "https://flagsapi.com/TR/flat/64.png";
        else if(name.equals("Vietnamese"))return "https://flagsapi.com/VN/flat/64.png";
        else return "https://countryflagsapi.com/png/mayotte";
    }
}

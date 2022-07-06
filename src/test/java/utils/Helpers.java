package utils;


import dataProvider.jsonDataReader;
import exceptions.ElementoNoVisibleException;

import java.io.IOException;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;



public class Helpers {
    private static final jsonDataReader jdr = new jsonDataReader();
    public void sleep (int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public List<String> orders (){
        List<String> typeOrder = new ArrayList<>();
        typeOrder.add("Pedidos");
        typeOrder.add("Ordens");
        return typeOrder;
    }

    public List<String> favs (){
        List<String> typeFav = new ArrayList<>();
        typeFav.add("Big Mac");
        typeFav.add("Feliz");
        typeFav.add("McFlurry");
        return typeFav;
    }

    public List<String> dataRegister (String pais){
        List<String> typeRegister = new ArrayList<>();
        if (pais.equals("Brasil")){
            typeRegister.add("com.mcdo.mcdonalds_debug.debug:id/mail_input_text");
            typeRegister.add("com.mcdo.mcdonalds_debug.debug:id/password_input_text");
            typeRegister.add("com.mcdo.mcdonalds_debug.debug:id/confirmPasswordInputText");
            typeRegister.add("com.mcdo.mcdonalds_debug.debug:id/cpfInputText");
        }
        else  {
            typeRegister.add("com.mcdo.mcdonalds_debug.debug:id/mail_input_text");
            typeRegister.add("com.mcdo.mcdonalds_debug.debug:id/password_input_text");
            typeRegister.add("com.mcdo.mcdonalds_debug.debug:id/confirmPasswordInputText");
        }
        return typeRegister;
    }

    public String getDataUser (String pais, int index){
        System.out.println("Pais: " + pais);
        List <String> typeDataUser = new ArrayList<>();
        switch (pais) {
            case "Brasil":
                typeDataUser.add("1234567890");
                break;
            case "Mexico":
                typeDataUser.add("MX.038.475.776-03");
                break;
            case "Panama":
                typeDataUser.add("2-10-2018");
                break;
            case "Argentina":
                typeDataUser.add("81544670");
                break;
            case "Puerto Rico":
                typeDataUser.add("Pruebas");
                typeDataUser.add("17874244564");
                typeDataUser.add("55555");
                break;
        }
        return typeDataUser.get(index);
    }




    public List<String> xpathPay (String pais, String nTarjeta, String Fvencimiento, String month, String year, String cvv, String nombre){
        System.out.println("Pais: " + pais);
        pais = Normalizer.normalize(pais, Normalizer.Form.NFD);
        pais = pais.replaceAll("[^\\p{ASCII}]", "");
        List<String> dataPay = new ArrayList<>();
        if (pais.equals("Chile") | pais.equals("Argentina") | pais.equals("Colombia")){
            dataPay.add(nTarjeta);
            dataPay.add(month);
            dataPay.add(year);
            dataPay.add(cvv);
        }
        else if (pais.contains("Mexico" )){
            dataPay.add(nTarjeta);
            dataPay.add(Fvencimiento);
            dataPay.add(cvv);
            dataPay.add(nombre);

        } else if (pais.equals("Brasil")) {
            dataPay.add(nTarjeta);
            dataPay.add(Fvencimiento);
            dataPay.add(cvv);
            dataPay.add(nombre);
            dataPay.add(getDataUser(pais,0));
        }
        else if (pais.equals("Costa Rica") |  pais.equals("Panama") | pais.equals("Uruguay")){
            dataPay.add(nombre);
            dataPay.add(nTarjeta);
            dataPay.add(Fvencimiento);
            dataPay.add(cvv);
        }
        else if (pais.equals("Puerto Rico")) {
            dataPay.add(nombre);
            System.out.println(pais);
            dataPay.add(getDataUser(pais,0));
            dataPay.add(getDataUser(pais,1));
            dataPay.add(nTarjeta);
            dataPay.add(Fvencimiento);
            dataPay.add(cvv);
            dataPay.add(getDataUser(pais,2));
        }
        return dataPay;
    }

    public String getJsonName(String config) throws ElementoNoVisibleException {
        String data = null;
        switch (config) {
            case "smokeTestDelivery":
            {
                data = "deliveryDataSmokeTest.json";
                break;
            }
            case "smokeTestPickup":{
                data = "pickupDataSmokeTest.json";
                break;
            }
            case "regresionDelivery":{
                data = "deliveryDataRegresion.json";
                break;
            }
            case "regresionPickup":{
                data = "pickupDataRegresion.json";
                break;
            }
            case "smokeTestDeliveryNR":{
                data = "deliveryNoRegisterDataSmokeTest.json";
                break;
            }
            case "smokeTestPickupNR":{
                data = "pickupNoRegisterDataSmokeTest.json";
                break;
            }
            case "regresionDeliveryNR":{
                data = "deliveryNoRegisterDataRegresion.json";
                break;
            }
            case "regresionPickupNR":{
                data = "pickupNoRegisterDataRegresion.json";
                break;
            }
            default:
                Logger.error("No se encontro la configuracion: " + config);
                throw new ElementoNoVisibleException("No se encontro la configuracion: " + config);
        }

        return data;
    }

    public String changeNameTest (String name) throws IOException, ElementoNoVisibleException {
        String newName;
        String[] nuevoEscenario = name.split(",");
        String data = getJsonName(nuevoEscenario[1]);
        String pais = jdr.dataReader(data, Integer.parseInt(nuevoEscenario[2]), "Pais");
        pais = Normalizer.normalize(pais, Normalizer.Form.NFD);
        pais = pais.replaceAll("[^\\p{ASCII}]", "");
        newName = nuevoEscenario[0] + pais;
        return newName;
    }





}

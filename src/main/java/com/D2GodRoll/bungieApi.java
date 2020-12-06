package com.D2GodRoll;


import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class bungieApi {
    String apiKey = "321fa1c1df6d453a8c43cdc1c758ab00";
    String baseUrl = "https://www.bungie.net";


    public bungieApi() throws MalformedURLException {
        System.out.println("Default Constructor.");

    }

    public StringBuilder get(String URL) throws IOException {

        String getURL = new StringBuilder().append(baseUrl).append(URL).toString();
        System.out.println(getURL);

        URL completeURL = new URL(getURL);
        HttpURLConnection connection = (HttpURLConnection) completeURL.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("X-API-KEY", apiKey);
        connection.setRequestProperty("accept", "application/json");
        InputStream responseStream;
        int responseCode = connection.getResponseCode();

        if(200 <= responseCode && responseCode <= 299){
            responseStream = connection.getInputStream();
        } else {
            responseStream = connection.getErrorStream();
        }

        BufferedReader in = new BufferedReader(
                new InputStreamReader(responseStream)
        );

        StringBuilder response = new StringBuilder();
        String currentLine;

        while((currentLine = in.readLine()) != null)
            response.append(currentLine);

        in.close();
        //System.out.println(response);

        return response;
    }

    public String getMembership(String property) throws IOException {
        StringBuilder memPayload = get("/Platform/User/GetMembershipsByID/" + property + "/-1/");
        JSONObject obj = new JSONObject(memPayload.toString());
        JSONArray d2  = new JSONArray(obj.getJSONObject("Response").getJSONArray("destinyMemberships"));
        return d2.getJSONObject(0).getString("membershipId");
    }

    public List getCharacters(String d2memID) throws IOException {
        StringBuilder charactersURL = get("/Platform/Destiny2/3/Profile/" + d2memID + "/?components=Characters");
        JSONObject obj = new JSONObject(charactersURL.toString());
        //System.out.println(obj);
        JSONObject characters = obj.getJSONObject("Response").getJSONObject("characters").getJSONObject("data");
        Iterator characterKeys = characters.keys();
        List characterList = new ArrayList();
        while(characterKeys.hasNext()) {
            String currentCharacter = (String) characterKeys.next();
            JSONObject currentCharacterID = characters.getJSONObject(currentCharacter);
            characterList.add(currentCharacterID.getString("characterId"));
        }

        return characterList;
    }

    public List getItems(List characters, String membership) throws IOException {
        for (int i=0; i <= characters.size(); i++){
            ///Destiny2/{membershipType}/Profile/{destinyMembershipId}/Character/{characterId}
            StringBuilder itemURL = get("/Platform/Destiny2/3/Profile/" + membership + "/Character/" + characters.get(i) + "/?components=CharacterInventories");
            System.out.println(itemURL);
        }
        List blah = null;
        return blah;
    }
}

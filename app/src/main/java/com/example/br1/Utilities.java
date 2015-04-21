package com.example.br1;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Holds basic utility functions for our application, as well as utility task listener objects
 * for implementing asynchronous utility tasks.
 * Created by juanvallejo on 3/9/15.
 */
public class Utilities {

    /**
     * Interface implementing a "callback" method that gets executed on thread end
     */
    public static interface OnUtilityTaskEndListener {
        // called when a task is done
        public void onUtilityTaskEnd(Object taskResponse);
    }

    /**
     * Parses a given JSON feed string into a JSONObject object. Returns null on exception.
     *
     * @param arrayKeyName =
     * @return
     */
    public static JSONArray parseJsonArrayFromString(String jsonString, String arrayKeyName) {

        try {

            JSONObject jsonObjectFromString = new JSONObject(jsonString);
            JSONArray jsonArray             = (JSONArray)(jsonObjectFromString).get(arrayKeyName);

            return jsonArray;

        } catch(JSONException exception) {
            exception.printStackTrace();
        }

        return null;
    }

    /**
     * Returns all values from a specific property in an array of json objects
     *
     * @param jsonArray a json array containing a set of json objects
     * @param keyValueToReturn the name of the property in each json array object to return the value for
     */
    public static String[] getStringArrayOfJsonValuesFromKey(JSONArray jsonArray, String keyValueToReturn) {

        // populate array list with json results
        try {

            String[] stringArray    = new String[jsonArray.length()];

            for(int i = 0; i < jsonArray.length(); i++) {

                // get the value of each requested property on each of the json objects in the array
                // of json objects passed as @param jsonObject
                String jsonArrayItem = ((JSONObject)jsonArray.get(i)).get(keyValueToReturn).toString();
                stringArray[i] = jsonArrayItem;

            }

            return stringArray;

        } catch(JSONException exception) {
            exception.printStackTrace();
        }

        return null;

    }

}

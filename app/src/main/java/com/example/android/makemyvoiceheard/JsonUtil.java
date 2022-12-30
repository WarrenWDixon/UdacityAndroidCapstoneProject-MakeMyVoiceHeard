package com.example.android.makemyvoiceheard;

import android.content.SharedPreferences;
import android.util.Log;

import org.json.JSONArray;

import org.json.JSONException;
import org.json.JSONObject;
import android.os.Bundle;
import java.util.ArrayList;
import android.support.v7.app.AppCompatActivity;
import java.util.List;

class JsonUtil {
    private static JSONObject jOBJ;
    private static JSONArray jsonOffices;
    private static JSONArray jsonOfficials;
    private static JSONArray official1AddressArray;
    private static Integer senator1Index = -1;
    private static Integer senator2Index = -1;
    private static Integer representativeIndex = -1;
    private static List<JSONObject> officialJSONArray;
    private static List<JSONArray> addressJSONArray;
    private static List<JSONArray> phoneJSONArray;
    private static List<JSONObject> officesJSONArray;
    private static List<JSONArray> officialURLArray;
    private static List<String> officialNameArray;
    private static List<String> officialPartyArray;
    private static List<String> officialPhotoURLArray;
    private static List<String> officialLine1Array;
    private static List<String> officialCityArray;
    private static List<String> officialStateArray;
    private static List<String> officialZipCodeArray;
    private static List<String> officeNameArray;
    private static List<JSONArray> officialIndexJSONArray;

    private static String senator1Name = "";
    private static String senator2Name = "";
    private static String representativeName = "";


    //member variables for storing data in shared preferences
    private static SharedPreferences mPreferences;
    private static String sharedPrefFile = "com.example.android.makemyvoiceheardprefs";


    public static Boolean parseCivicsJson(String json) {
        // first convert entire response to JSON object
        jOBJ = null;
        jsonOffices = null;
        jsonOfficials = null;
        official1AddressArray = null;
        officialJSONArray = new ArrayList<>();
        addressJSONArray = new ArrayList<>();
        phoneJSONArray = new ArrayList<>();
        officesJSONArray = new ArrayList<>();
        officialNameArray = new ArrayList<>();
        officialPartyArray = new ArrayList<>();
        officialLine1Array = new ArrayList<>();
        officialCityArray = new ArrayList<>();
        officialStateArray = new ArrayList<>();
        officialZipCodeArray = new ArrayList<>();
        officeNameArray = new ArrayList<>();
        officialURLArray = new ArrayList<>();
        officialPhotoURLArray = new ArrayList<>();
        officialIndexJSONArray = new ArrayList<>();

        if (!parseJSONObject(json))
            return false;

        if (!parseNormalizedInput())
            return false;

        if (!parseOffices())
            return false;

        if (!parseOfficials())
            return false;

        if (!parseIndicies())
            return false;

        return true;
    }

    private static Boolean parseJSONObject(String json) {
        try {
            jOBJ = new JSONObject(json);
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private static Boolean parseNormalizedInput() {
        // parse normalizedInput
        try {
            JSONObject normalizedInput = jOBJ.getJSONObject("normalizedInput");
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private static Boolean parseOffices() {
        try {
            jsonOffices = new JSONArray();
            jsonOffices = jOBJ.getJSONArray("offices");
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }

        int numOffices = jsonOffices.length();
        if (numOffices > 0) {
            for (int i = 0; i < numOffices; i++) {
                try {
                    officesJSONArray.add(jsonOffices.getJSONObject(i));
                } catch (JSONException e) {
                    e.printStackTrace();
                    return false;
                }

                try {
                    officeNameArray.add(officesJSONArray.get(i).getString("name"));
                } catch (JSONException e) {
                    e.printStackTrace();
                    return false;
                }

                try {
                    officialIndexJSONArray.add(officesJSONArray.get(i).getJSONArray("officialIndices"));
                } catch (JSONException e) {
                    e.printStackTrace();
                    return false;
                }
            }
        } else {
            return false;
        }
        return true;
    }

    private static Boolean parseOfficials() {
        try {
            jsonOfficials = new JSONArray();
            jsonOfficials = jOBJ.getJSONArray("officials");
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }

        int numOfficials = jsonOfficials.length();
        if (numOfficials > 0) {
            for (int i = 0; i < numOfficials; i++) {
                try {
                    officialJSONArray.add(jsonOfficials.getJSONObject(i));
                } catch (JSONException e) {
                    e.printStackTrace();
                    return false;
                }

                try {
                    officialNameArray.add(officialJSONArray.get(i).getString("name"));
                } catch (JSONException e1) {
                    e1.printStackTrace();
                    return false;
                }

                try {
                    addressJSONArray.add(officialJSONArray.get(i).getJSONArray("address"));
                } catch (JSONException e3) {
                    e3.printStackTrace();
                    return false;
                }

                try {
                    officialPartyArray.add(officialJSONArray.get(i).getString("party"));
                } catch (JSONException e2) {
                    e2.printStackTrace();
                    return false;
                }

                try {
                    phoneJSONArray.add(officialJSONArray.get(i).getJSONArray("phones"));
                } catch (JSONException e6) {
                    e6.printStackTrace();
                    return false;
                }

                try {
                    officialURLArray.add(officialJSONArray.get(i).getJSONArray("urls"));
                } catch (Exception e5) {
                    officialURLArray.add(new JSONArray());
                }

                try {
                    officialPhotoURLArray.add(officialJSONArray.get(i).getString("photoUrl"));
                } catch (Exception e4) {
                    officialPhotoURLArray.add("");
                }

                // now parse elements of address
                try {
                    officialLine1Array.add(addressJSONArray.get(i).getJSONObject(0).getString("line1"));
                } catch (JSONException e7) {
                    e7.printStackTrace();
                    return false;
                }

                try {
                    officialCityArray.add(addressJSONArray.get(i).getJSONObject(0).getString("city"));
                } catch (JSONException e8) {
                    e8.printStackTrace();
                    return false;
                }

                try {
                    officialStateArray.add(addressJSONArray.get(i).getJSONObject(0).getString("state"));
                } catch (JSONException e9) {
                    e9.printStackTrace();
                    return false;
                }

                try {
                    officialZipCodeArray.add(addressJSONArray.get(i).getJSONObject(0).getString("zip"));
                } catch (JSONException e10) {
                    e10.printStackTrace();
                    return false;
                }
            }
        } else {
            return false;
        }
        return true;
    }

    private static Boolean parseIndicies() {
        int numNames = officeNameArray.size();
        int numIndices;
        if (numNames > 0) {
            for (int i = 0; i < numNames; i++) {
                String name = officeNameArray.get(i);
                if (name.contains("Senator")) {
                    try {
                        senator1Index = (Integer) (officialIndexJSONArray.get(i).get(0));
                    } catch (JSONException e) {
                        e.printStackTrace();
                        return false;
                    }
                    try {
                        senator2Index = (Integer) (officialIndexJSONArray.get(i).get(1));
                    } catch (JSONException e) {
                        e.printStackTrace();
                        return false;
                    }
                } else if (name.contains("Representative")) {
                    try {
                        representativeIndex = (Integer) (officialIndexJSONArray.get(i).get(0));
                    } catch (JSONException e) {
                        e.printStackTrace();
                        return false;
                    }
                }
            }
        }
        return true;
    }

    // ---------------------------------------------------------------------------------------------
    // access methods for senator 1
    // ---------------------------------------------------------------------------------------------
    public static String getSenator1PhotoURL() {
        if ((senator1Index < 0) || (senator1Index > 2))
            return "";
        if (officialPhotoURLArray.get(senator1Index).length() > 0) {
            return officialPhotoURLArray.get(senator1Index);
        }
        return "";
    }

    public static String getSenator1URL() {
        if ((senator1Index < 0) || (senator1Index > 2))
            return "";
        try {
            if (officialURLArray.get(senator1Index).get(0).toString().length() > 0) {
                return officialURLArray.get(senator1Index).get(0).toString();
            }
        } catch (JSONException e) {
            return "";
        }
        return "";
    }

    public static String getSenator1Phone() {
        if ((senator1Index < 0) || (senator1Index > 2))
            return "";
        try {
            if (phoneJSONArray.get(senator1Index).get(0).toString().length() > 0) {
                return phoneJSONArray.get(senator1Index).get(0).toString();
            }
        } catch (JSONException e) {
            return "";
        }
        return "";
    }

    public static String getSenator1Name() {
        if ((senator1Index < 0) || (senator1Index > 2))
            return "";
        if (officialNameArray.get(senator1Index).length() > 0) {
            return officialNameArray.get(senator1Index);
        }
        return "";
    }

    public static String getSenator1Party() {
        if ((senator1Index < 0) || (senator1Index > 2))
            return "";
        if (officialPartyArray.get(senator1Index).length() > 0) {
            return officialPartyArray.get(senator1Index);
        }
        return "";
    }

    public static String getSenator1AddressLine1() {
        if ((senator1Index < 0) || (senator1Index > 2))
            return "";
        if (officialLine1Array.get(senator1Index).length() > 0) {
            return officialLine1Array.get(senator1Index);
        }
        return "";
    }

    public static String getSenator1AddressLine2() {
        String line2 = "";
        if ((senator1Index < 0) || (senator1Index > 2))
            return "";
        if (officialCityArray.get(senator1Index).length() > 0) {
            line2 += officialCityArray.get(senator1Index);
        }
        if (officialStateArray.get(senator1Index).length() > 0) {
            line2 += ", " + officialStateArray.get(senator1Index);
        }
        if (officialZipCodeArray.get(senator1Index).length() > 0) {
            line2 += " " + officialZipCodeArray.get(senator1Index);
        }
        return line2;
    }

    // ---------------------------------------------------------------------------------------------
    // access methods for senator 2
    // ---------------------------------------------------------------------------------------------
    public static String getSenator2URL() {
        if ((senator2Index < 0) || (senator2Index > 2))
            return "";
        try {
            if (officialURLArray.get(senator2Index).get(0).toString().length() > 0) {
                return officialURLArray.get(senator2Index).get(0).toString();
            }
        } catch (JSONException e) {
            return "";
        }
        return "";
    }

    public static String getSenator2Phone() {
        if ((senator2Index < 0) || (senator2Index > 2))
            return "";
        try {
            if (phoneJSONArray.get(senator2Index).get(0).toString().length() > 0) {
                return phoneJSONArray.get(senator2Index).get(0).toString();
            }
        } catch (JSONException e) {
            return "";
        }
        return "";
    }

    public static String getSenator2PhotoURL() {
        if ((senator2Index < 0) || (senator2Index > 2))
            return "";
        if (officialPhotoURLArray.get(senator2Index).length() > 0) {
            return officialPhotoURLArray.get(senator2Index);
        }
        return "";
    }

    public static String getSenator2Name() {
        if ((senator2Index < 0) || (senator2Index > 2))
            return "";
        if (officialNameArray.get(senator2Index).length() > 0) {
            return officialNameArray.get(senator2Index);
        }
        return "";
    }

    public static String getSenator2Party() {
        if ((senator2Index < 0) || (senator2Index > 2))
            return "";
        if (officialPartyArray.get(senator2Index).length() > 0) {
            return officialPartyArray.get(senator2Index);
        }
        return "";
    }

    public static String getSenator2AddressLine1() {
        if ((senator2Index < 0) || (senator2Index > 2))
            return "";
        if (officialLine1Array.get(senator2Index).length() > 0) {
            return officialLine1Array.get(senator2Index);
        }
        return "";
    }

    public static String getSenator2AddressLine2() {
        String line2 = "";
        if ((senator2Index < 0) || (senator2Index > 2))
            return "";
        if (officialCityArray.get(senator2Index).length() > 0) {
            line2 += officialCityArray.get(senator2Index);
        }
        if (officialStateArray.get(senator2Index).length() > 0) {
            line2 += ", " + officialStateArray.get(senator2Index);
        }
        if (officialZipCodeArray.get(senator2Index).length() > 0) {
            line2 += " " + officialZipCodeArray.get(senator2Index);
        }
        return line2;
    }

    // ---------------------------------------------------------------------------------------------
    // access methods for representative
    // ---------------------------------------------------------------------------------------------

    public static String getRepresentativePhotoURL() {
        if ((representativeIndex < 0) || (representativeIndex > 2))
            return "";
        if (officialURLArray.get(representativeIndex).length() > 0) {
            return officialPhotoURLArray.get(representativeIndex);
        }
        return "";
    }

    public static String getRepresentativeURL() {
        if ((representativeIndex < 0) || (representativeIndex > 2))
            return "";
        try {
            if (officialURLArray.get(representativeIndex).get(0).toString().length() > 0) {
                return officialURLArray.get(representativeIndex).get(0).toString();
            }
        } catch (JSONException e) {
            return "";
        }
        return "";
    }

    public static String getRepresentativePhone() {
        if ((representativeIndex < 0) || (representativeIndex > 2))
            return "";
        try {
            if (phoneJSONArray.get(representativeIndex).get(0).toString().length() > 0) {
                return phoneJSONArray.get(representativeIndex).get(0).toString();
            }
        } catch (JSONException e) {
            return "";
        }
        return "";
    }

    public static String getRepresentativeName() {
        if ((representativeIndex < 0) || (representativeIndex > 2))
            return "";
        if (officialNameArray.get(representativeIndex).length() > 0) {
            return officialNameArray.get(representativeIndex);
        }
        return "";
    }

    public static String getRepresentativeParty() {
        if ((representativeIndex < 0) || (representativeIndex > 2))
            return "";
        if (officialPartyArray.get(representativeIndex).length() > 0) {
            return officialPartyArray.get(representativeIndex);
        }
        return "";
    }

    public static String getRepresentativeAddressLine1() {
        if ((representativeIndex < 0) || (representativeIndex > 2))
            return "";
        if (officialLine1Array.get(representativeIndex).length() > 0) {
            return officialLine1Array.get(representativeIndex);
        }
        return "";
    }

    public static String getRepresentativeAddressLine2() {
        String line2 = "";
        if ((representativeIndex < 0) || (representativeIndex > 2))
            return "";
        if (officialCityArray.get(representativeIndex).length() > 0) {
            line2 += officialCityArray.get(representativeIndex);
        }
        if (officialStateArray.get(representativeIndex).length() > 0) {
            line2 += ", " + officialStateArray.get(representativeIndex);
        }
        if (officialZipCodeArray.get(representativeIndex).length() > 0) {
            line2 += " " + officialZipCodeArray.get(representativeIndex);
        }
        return line2;
    }

}

package com.ccm.android.ccm;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by KishanSubhashMiryala on 05/05/16.
 */
public class ExpandableListDataSource {

    /**
     * Returns
     * @return navbar List
     */
    public static LinkedHashMap<String, List<String>> getData(final String languagesArr[],final String genresArr[]) {
        LinkedHashMap<String, List<String>> expandableListData = new LinkedHashMap<>();

            Log.d("isDataReceived","in loop");
            String sections[] = {"Saved Leads","Profile",
                    "Rate Us", "Share With Friends", "Privacy Policy", "Terms & Conditions","Logout"
            };
            List<String> filmGenres = new ArrayList<String>();
            for (int i = 0; i < sections.length; ++i) {
                filmGenres.add(i, sections[i]);
            }

            List<String> languages = Arrays.asList(languagesArr);
            List<String> genres = Arrays.asList(genresArr);
            expandableListData.put(filmGenres.get(0), languages);
            expandableListData.put(filmGenres.get(1), genres);
            expandableListData.put(filmGenres.get(2), new ArrayList<String>());
            expandableListData.put(filmGenres.get(3), new ArrayList<String>());
            expandableListData.put(filmGenres.get(4), new ArrayList<String>());
            expandableListData.put(filmGenres.get(5), new ArrayList<String>());
            expandableListData.put(filmGenres.get(6), new ArrayList<String>());
            return expandableListData;
    }
}

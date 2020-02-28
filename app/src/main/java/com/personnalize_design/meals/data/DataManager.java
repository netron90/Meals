package com.personnalize_design.meals.data;

import com.personnalize_design.meals.data.db.DbHelper;
import com.personnalize_design.meals.data.network.ApiHelper;
import com.personnalize_design.meals.data.preferences.PreferenceHelper;


public interface DataManager extends DbHelper, ApiHelper, PreferenceHelper {

}

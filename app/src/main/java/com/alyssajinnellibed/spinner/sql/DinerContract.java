package com.alyssajinnellibed.spinner.sql;

import android.provider.BaseColumns;

public class DinerContract {

    public static final class DinerEntry implements BaseColumns {

        public static final String TABLE_NAME = "dining";
        public static final String COLUMN_LOCATION = "diner_location";
        public static final String COLUMN_TYPE = "diner_type";
        public static final String COLUMN_DINERNAME = "diner_name";
        public static final String COLUMN_PRICERANGE = "diner_pricerange";
    }
}

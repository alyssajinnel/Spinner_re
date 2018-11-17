package com.alyssajinnellibed.spinner.sql;

import android.provider.BaseColumns;

public class DinerContract {

    public static final class DinerEntry implements BaseColumns {

        public static final String TABLE_NAME = "dinersss";
        public static final String COLUMN_DINER_NAME = "diner_location";
        public static final String COLUMN_DINER_EMAIL = "diner_type";
        public static final String COLUMN_DINER_ADDRESS = "diner_name";
        public static final String COLUMN_DINER_COUNTRY = "diner_pricerange";
    }
}

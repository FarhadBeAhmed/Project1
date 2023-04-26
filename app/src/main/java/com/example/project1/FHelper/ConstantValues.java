package com.example.project1.FHelper;


import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.project1.FHelper.Annotations.Field;
import com.example.project1.FHelper.Annotations.RequestUrl;
import com.example.project1.FHelper.Annotations.SendMethod;
import com.google.android.material.textfield.TextInputLayout;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Proxy;
import java.util.ArrayList;



public final class ConstantValues {
    public static final String web_url = "https://load16.com/";
    public static final @Nullable
    String KEY_EMPTY = "";
    public static int badgeValue = 0;
    public static final String ARGUMENT1 = "arg1";
    public static final String NAME = "name";
    public static final String SUB_CAT_ID = "scat_id";
    public static final String BRAND_ID = "brand_id";
    public static final String CAT_ID = "cat_id";
    public static final String ID = "id";
    public static final String VALUE = "value";
    public static final String PASSWORD = "password";
    public static final String NEW_PASSWORD = "new_password";
    public static final String ERROR = "error";
    public static final String SUCCESS = "success";
    public static final String STATUS = "status";
    public static final String MSG = "msg";
    public static final String USERNAME = "username";
    public static int PIN_NUM;



    public interface Login {
        String STATUS = "error";
        String MESSAGE = "msg";
        String FULL_NAME = "name";
        String USERNAME = "username";
        String PASSWORD = "password";
        String IMAGE = "img";
        String MOBILE = "mobile";
        String BKASHW = "bkash";
        String ROCKETW = "rocket";
        String NAGADW = "nagad";
        String TYPE = "type";
        String PIN = "pin";
    }
    public interface Verification {
        String TASK = "task";
        String TASK_NUMBER = "taskNo";
        String PERCENT = "percent";
        public interface mobile{
            String VEF = "vef";
            String MOB = "mob";
            String CODE2 = "code2";
        }
    }

    public interface SignUp {
        String ID = "id";
        String MOBILE = "mobile";
        String NAME = "name";
        String COUNTRY = "country";
        String DISTRICT = "diven";
        String REFER = "refer";
        String EMAIL = "email";
        String V_MOBILE = "vmobile";
        String DIVISION = "division";
        String CODE = "code";

    }
    public interface account_status {
        String SERIAL = "Serial";
        String BOLD = "Bold";
        String TITLE = "Title";
        String MONEY = "Money";

    }
    public interface blog{
        String SERIAL= "serial";
        String SUBJECT= "subject";
        String NAME= "name";
        String BLOGGER_IMAGE= "img";
        String TEXT= "text";
        String DATE= "date";
        String BLOG_IMAGE= "image";
    }
    public interface Cart {
        String SERIAL = "serial";
        String PRODUCT_ID = "p_id";
        String DEVICE_ID = "u_id";
        String POINT = "point";
        String QUANTITY = "qty";
        String PRICE = "price";
        String TOTAL = "total";
        String DIRECT = "direct";
        String COLOR = "color";
        String SIZE = "size";
        String MODEL = "model";
        String SUB_TOTAL = "tprice";
        String SUB_TOTAL_POINT = "total_point";
        String S_POINT = "spoint";
        String TYPE = "type";

    }
    public interface order{
        String SERIAL = "serial";
        String ESHOP = "eshop";
        String ADDRESS = "address";
        String ADJUST = "adjust";
        String SHIPPING = "shipping";
        String SPONSOR_NAME = "sponsor_name";
        String SPONSOR_NUMBER = "sponsor_mobile";
        String DATE = "date";
        String POINT = "point";
        String USED = "used";
        String COUNT = "count";
        String PIN = "pin";
    }
    public interface applied{
        String SERIAL = "serial";
        String ESHOP = "eshop";
        String ADDRESS = "address";
        String ADJUST = "adjust";
        String SHIPPING = "shipping";
        String INVOICE = "invoice";
        String SHOP_NAME = "shop_name";
        String SHOP_MOBILE = "shop_mobile";
        String PAID = "paid";
        String DATE = "date";
        String ACTION = "action";
        String RECEIVED = "received";
        String RETURN = "returned";
        String POSITION = "position";
        String UPLINK = "uplink";


    }


    public interface Product {
        String NAME = "name";
        String S_ID = "s_id";
        String IMAGE = "img";
        String PREVIOUS_PRICE = "sprice";
        String PRICE = "price";
        String DISCOUNT = "discount";
        String PRODUCT_ID = "serial";
        String LIMIT = "limit";
        String UPLOAD_BY = "upload_by";
        String CAT_ID = "cat_id";
        String SCAT_ID = "scat_id";
        String BRAND_ID = "brand_id";
        String MODEL = "model";
        String P_PRICE = "pprice";
        String POINT = "point";
        String DIRECT = "direct";
        String CASHBACK = "cashback";
        String TE_PRICE = "teprice";
        String SH_PRICE = "shprice";
        String MI_PRICE = "miprice";
        String ENTRY = "entry";
        String STOCK = "stock";
        String SOLD = "sold";
        String REPLACE = "repleace";
        String DAMAGE = "damage";
        String COLOR = "color";
        String SIZE = "size";
        String WARRANTY = "warrenty";
        String INFO = "info";
        String DETAILS = "details";
        String VIDEO = "video";
        String FEATURE = "feature";
        String SPACIAL = "special";
        String HOT_DEAL = "hot_deal";
        String OFFER_DATE = "offer_date";
        String E_SHOP = "eshop";
        String OUT_STOCK = "out_stock";
        String RATING = "rating";
        String PERSON = "person";
        String DELIVERY = "delivery";
        String CHK = "chk";
        String DATE = "date";

        String PARCEL = "parcel";
        String PARCEL_TOP_SEL = "parcel_top_sel";
        String VALUE = "value";
        String REVIEW = "review";


    }

    public interface PopularProduct {
        String VALUES = "value";
        String NAME = "name";
        String IMAGE = "img";
        String PREVIOUS_PRICE = "sprice";
        String PRICE = "price";
        String DISCOUNT = "discount";
        String PRODUCT_ID = "serial";
        String PARCEL = "parcel";
        String UPLOAD_BY = "upload_by";
        String OFFERED_DATE = "offer_date";
        String POINT = "point";

    }

    public interface Categories {
        String ID = "id";
        String CAT_ID = "cat_id";
        String CAT_ICON = "cat_icon";
        String CAT_NAME = "cat";
        String SCAT_NAME = "scat";
        String SCAT_ICON = "scat_img";
        String SCAT_ID = "scat_id";
        String BRAND_ID = "brand_id";
        String BRAND_NAME = "brand";
        String BRAND_IMG = "brand_img";

    }
    public interface Category {
        String CATEGORY_ID = "cat_id";
        String SUB_CATEGORY_ID = "scat_id";
        String BRAND_ID = "brand_id";
        String ORDERING = "ordering ";

        String CATEGORY_NAME = "cat";
        String SUB_CATEGORY_NAME = "scat";
        String SUB_CATEGORY_IMAGE = "scat_img";
        String CATEGORY_IMAGE = "cat_img";
        String BRAND_NAME = "brand";
    }


    public interface balance{
        String NetBalance = "balance";
    }
    public interface location{
        String ID="id";
        String LOCATION="location";
        String LOCATION_TYPE="locationType";
        String UNION = "union";
        String WORD = "word";
        String ADDRESS = "address";
        String FULL_ADDRESS = "full_address";
        String THANA = "thana";
        String COUNTRY = "country";
        String DIVISION = "division";
        String DISTRICT = "district";
    }
    public interface admin_login{
        String SHOP_NAME="name";
        String TYPE_ID="type_id";
        String ID="id";
        String MOBILE="mobile";
        String ADDRESS="address";
        String SHOP_ID="shop_id";
        String SHOP_TYPE="type_name";
        String IMAGE="img";
        String DISTRICT="district";
        String THANA="thana";
        String UNION="union";
        String PHOTO="photo";
    }

    public interface profile {
        String NAME = "name";
        String USER_ID = "id";
        String PACKAGE = "package";
        String DESIGNATION = "designation";
        String PERCENT = "percent";
        String EMAIL = "email";
        String IMAGE = "image";
        String KYC = "kyc";
        String KYC1 = "kyc1";
        String SIGN = "sign";
        String BKASH = "bkash";
        String ROCKET = "rocket";
        String NAGAD = "nagad";
        String BANK_NAME = "bankName";
        String ACCOUNT_NAME = "accountName";
        String ACCOUNT_NUMBER = "accountNo";
        String BRANCH_NAME = "branchName";
        String ROUTING = "routing";
        String CREDIT_CARD = "creditCard";
        String PAYPAL = "paypal";
        String SKRILL = "skrill";
        String BITCOIN = "bitcoin";
        String NETELLER = "neteller";
        String FATHER = "father";
        String MOBILE = "mobile";
        String BIRTH = "birth";
        String NID = "nid";
        String MOTHER = "mother";
        String GENDER = "gender";
        String PROFESSION = "prof";
        String BLOOD = "blood";
        String EDUCATION = "education";
        String RELIGION = "religion";
        String NOMINEE = "nominee";
        String NOMINEE_RELATION = "nomi_relation";
        String UNION = "union";
        String WORD = "word";
        String ADDRESS = "address";
        String THANA = "thana";
        String COUNTRY = "country";
        String DIVISION = "division";
        String DISTRICT = "district";
        String DISTRICT_ID = "districtId";
        String DIVISION_ID = "divisionId";
        String COUNTRY_ID = "countryId";
        String THANA_ID = "thanaId";
        String UNION_ID = "unionId";
        String PIN = "pin";
    }
    public interface vendor {
        String NAME = "name";
        String VEN_ID = "id";
        String VEN_TYPE = "type_name";
        String ADDRESS = "address";
        String VEN_NUMBER = "number";
        String VEN_NAME = "name";

    }
    @NonNull
    public static String getTextFromTIL(@NonNull TextInputLayout textInputLayout) {
        return textInputLayout.getEditText().getText().toString();
    }
  /*  @NotNull
    public static String getFileName(@NonNull String... strings) {
        return getFixedImageUrl(new Joiner<>("/", web_url, "", strings).toString());
    }*/


    public static void addMultipleClickListener(View.OnClickListener v, @NonNull View... views) {
        for (View b : views) b.setOnClickListener(v);
    }

    /**
     * If you write blank string, It can return only <b>website</b>.<br>
     * We've create a only website value, So use that.
     *
     * @param strings Use ("api", "*.php") instead of ("api/*.php") in this inner method.
     * @return Get File Name As Host Name Shortcut.
     */

    @NotNull
    public static String getFileNameAsHost(@NonNull String @NotNull ... strings) {
        String result = web_url;
        for (String f : strings) {
            result += f + "/";
        }
        result += "/";
        result = result.replace("//", "");
        return result;
    }

    @NotNull
    public static String getImageUrl(@NonNull String @NotNull ... strings) {
        String result = web_url;
        for (String f : strings) {
            result += f + "/";
        }
        result += "/";
        result = result.replace("//", "");
        return result;
    }

    /**
     * What all of {@link TextInputLayout} checked is text completed or not.
     *
     * @param textInputLayouts enter the all {@link TextInputLayout}
     * @return is {@link TextInputLayout}(s) text completed or not.
     */
    public static boolean validation(TextInputLayout @NotNull ... textInputLayouts) {
        boolean result = true;
        for (TextInputLayout til : textInputLayouts) {
            if (!(til.getEditText().getText().toString().length() < 0)) {
                til.setError("");
            }
            if (til.getEditText().getText().toString().isEmpty()) {
                til.setError("Field can't be empty.");
                result = false;
            }
        }
        return result;
    }
    public static boolean validation2(EditText @NotNull ... textInputLayouts) {
        boolean result = true;
        for (EditText til : textInputLayouts) {
            if (!(til.getText().toString().length() < 0)) {
                til.setError("");
            }
            if (til.getText().toString().isEmpty()) {
                til.setError("Field can't be empty.");
                result = false;
            }
        }
        return result;
    }

    public static boolean passwordValidation(TextInputLayout @NotNull ... textInputLayouts) {
        boolean result = true;
        for (TextInputLayout til : textInputLayouts) {
            if (!(til.getEditText().getText().toString().length() < 6)) {
                til.setError("");
            }
            if (til.getEditText().getText().toString().length() < 6) {
                til.setError("Minimum 6 character");
                result = false;
            }
        }
        return result;
    }

    @NonNull
    public static String getTextFromTextInputLayout(@NonNull TextInputLayout textInputLayout) {
        return textInputLayout.getEditText().getText().toString();
    }

    @NonNull
    public static String getRealStringEscape(String s) {
        String result = s;
        if (result == null) result = "";
        result = result.replace("`", "");
        result = result.replace("'", "");
        result = result.replace("\"", "");
        result = result.replace("null", "");
        return result;
    }

    /**
     * You get the url with submit value.
     *
     * @param directory enter your directory on getFileNameAsHost()
     * @param variable  enter your variable name on _String ... [0] <br>
     *                  Then enter your variable on _String ... [1]
     * @return you get the url with submit value.
     */
    @NonNull
    public static String GetMethodURL(String directory, @NonNull String[]... variable) {
        String result = directory + "?&";
        for (String[] var : variable)
            result += "&" + var[0] + "=" + var[1];
        result = result.replace("&&", "");
        return result;
    }

    /**
     * Requires this method for using {@link API}.
     */
    @NonNull
    @SuppressWarnings("unchecked")
    public static API getAPI() {
        return (API) Proxy.newProxyInstance(API.class.getClassLoader(), new Class<?>[]{API.class}, (proxy, method, args) -> {
            JSONObject jsonObject = new JSONObject();
            try {
                for (int i = 0; i < args.length - 1; i++)
                    jsonObject.put(((Field) method.getParameterAnnotations()[i][0]).value(), args[i]);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return new JsonObjectRequest(method.getAnnotation(SendMethod.class).value(), web_url + method.getAnnotation(RequestUrl.class).value(),
                    jsonObject, (Response.Listener<JSONObject>) args[args.length - 1], Throwable::printStackTrace);
        });
    }
}

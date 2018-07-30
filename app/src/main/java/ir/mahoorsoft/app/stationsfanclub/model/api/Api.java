package ir.mahoorsoft.app.stationsfanclub.model.api;


import java.util.ArrayList;

import ir.mahoorsoft.app.stationsfanclub.model.struct.RFServer;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;


/**
 * Created by MAD on 30/10/2017.
 */

public interface Api {


    @GET("f/{pelak}")
    Call<ArrayList<RFServer>> pelakValidation(
            @Path("pelak") String pelak
    );

    @FormUrlEncoded
    @POST("save")
    Call<ArrayList<RFServer>> saveCustomerSelfData(

            @Field("name") String name,
            @Field("family") String family,
            @Field("phone") String phone
    );

    @FormUrlEncoded
    @POST("save")
    Call<ArrayList<RFServer>> saveCustomerCarData(

            @Field("pelak") String pelak,
            @Field("carType") String carType,
            @Field("sokhtType") String sokhtType
    );
}

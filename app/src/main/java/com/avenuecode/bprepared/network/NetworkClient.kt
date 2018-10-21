package com.avenuecode.bprepared.network

import com.avenuecode.bprepared.models.CurrentUser
import com.avenuecode.bprepared.network.networkModel.BasicResponse
import io.reactivex.Single
import retrofit2.http.*


interface NetworkClient {

    //get_list_for_disaster?user_id=<user_id>&disaster_id=<disaster_id>
    @GET("get_list_for_disaster?user_id=-LPIy7P5NJTaRPQcGiLw&disaster_id=1")
    fun getList() : Single<BasicResponse>

    //USERS

    @GET("user/{user_id}")
    fun getUser(@Path("user_id") userId:String) : Single<CurrentUser>

    @POST("/user/insert/")
    fun inserUser(@Body user: CurrentUser)

    //DISASTERS

    @GET("/disasters")
    fun getDisasters(): Single<CurrentUser>



    /*** User **
    Get a single user
    @app.route('/user/<user_id>', methods=["GET"])

    Update a single user
    @app.route('/user/<user_id>/update', methods=["PATCH"])

    Insert a single user
    @app.route('/user/insert/', methods=["POST", "PUT"])

     ** Disasters **
    Get all the disasters
    @app.route('/disasters', methods=["GET"])

    Get a single disaster
    @app.route('/disaster/<disaster_id>', methods=["GET"])

    Update a single disaster
    @app.route('/disaster/<disaster_id>/update', methods=["PATCH"])

    Insert a single disaster
    @app.route('/disaster/insert/', methods=["POST", "PUT"])

    Delete a disaster
    @app.route('/disaster/<disaster_id>', methods=["DELETE"])


     ** List items **
    Get a list item
    @app.route('/list_item/<list_item_id>', methods=["GET"])

    Update a single list item
    @app.route('/list_item/<list_item_id>/update', methods=["PATCH"])

    Insert a single list item
    @app.route('/list_item/insert/', methods=["POST", "PUT"])

    Deletes a list item
    @app.route('/list_item/<list_item_id>', methods=["DELETE"])

    Get the initial list for a given user
    @app.route('get_list_for_disaster?user_id=<user_id>&disaster_id=<disaster_id>', methods=["GET"])*/
}
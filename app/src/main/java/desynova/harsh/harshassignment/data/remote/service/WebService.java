package desynova.harsh.harshassignment.data.remote.service;

import desynova.harsh.harshassignment.data.remote.dto.TabOne;
import desynova.harsh.harshassignment.data.remote.dto.TabThree;
import desynova.harsh.harshassignment.data.remote.dto.TabTwo;
import retrofit2.Call;
import retrofit2.http.GET;

public interface WebService {
    @GET("bins/1gh0zr")
    Call<TabOne> fetchTabOneRecyclerOne();

    @GET("bins/1brpbb")
    Call<TabOne> fetchTabOneRecyclerTwo();

    @GET("bins/irw5j")
    Call<TabTwo> fetchTabTwo();

    @GET("bins/1hfbk7")
    Call<TabThree> fetchTabThree();

}

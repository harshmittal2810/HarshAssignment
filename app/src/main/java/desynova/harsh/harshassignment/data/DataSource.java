package desynova.harsh.harshassignment.data;

import desynova.harsh.harshassignment.data.remote.dto.TabOne;
import desynova.harsh.harshassignment.data.remote.dto.TabThree;
import desynova.harsh.harshassignment.data.remote.dto.TabTwo;
import io.reactivex.Single;

interface DataSource {
    Single<TabOne> requestTabDataOne();

    Single<TabOne> requestTabDataTwo();

    Single<TabTwo> requestTabTwo();

    Single<TabThree> requestTabThree();

}

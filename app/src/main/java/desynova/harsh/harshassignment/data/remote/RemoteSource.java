package desynova.harsh.harshassignment.data.remote;

import io.reactivex.Single;

interface RemoteSource {

    Single getTabDataOne();

    Single getTabDataTwo();

    Single getTabTwo();

    Single getTabThree();
}

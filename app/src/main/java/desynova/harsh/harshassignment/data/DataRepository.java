package desynova.harsh.harshassignment.data;

import javax.inject.Inject;

import desynova.harsh.harshassignment.data.local.LocalRepository;
import desynova.harsh.harshassignment.data.remote.RemoteRepository;
import desynova.harsh.harshassignment.data.remote.dto.TabOne;
import desynova.harsh.harshassignment.data.remote.dto.TabThree;
import desynova.harsh.harshassignment.data.remote.dto.TabTwo;
import io.reactivex.Single;

public class DataRepository implements DataSource {
    private RemoteRepository remoteRepository;
    private LocalRepository localRepository;

    @Inject
    public DataRepository(RemoteRepository remoteRepository, LocalRepository localRepository) {
        this.remoteRepository = remoteRepository;
        this.localRepository = localRepository;
    }

    @Override
    public Single<TabOne> requestTabDataOne() {
        return remoteRepository.getTabDataOne();
    }

    @Override
    public Single<TabOne> requestTabDataTwo() {
        return remoteRepository.getTabDataTwo();
    }

    @Override
    public Single<TabTwo> requestTabTwo() {
        return remoteRepository.getTabTwo();
    }

    @Override
    public Single<TabThree> requestTabThree() {
        return remoteRepository.getTabThree();
    }
}

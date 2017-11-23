package desynova.harsh.harshassignment.data.remote;

import android.accounts.NetworkErrorException;
import android.support.annotation.NonNull;
import android.util.Log;

import java.io.IOException;

import javax.inject.Inject;

import desynova.harsh.harshassignment.DesynovaApp;
import desynova.harsh.harshassignment.data.remote.dto.TabOne;
import desynova.harsh.harshassignment.data.remote.dto.TabThree;
import desynova.harsh.harshassignment.data.remote.dto.TabTwo;
import desynova.harsh.harshassignment.data.remote.service.WebService;
import desynova.harsh.harshassignment.utils.Constants;
import io.reactivex.Single;
import io.reactivex.plugins.RxJavaPlugins;
import retrofit2.Call;

import static desynova.harsh.harshassignment.data.remote.ServiceError.NETWORK_ERROR;
import static desynova.harsh.harshassignment.data.remote.ServiceError.SUCCESS_CODE;
import static desynova.harsh.harshassignment.utils.Constants.ERROR_UNDEFINED;
import static desynova.harsh.harshassignment.utils.NetworkUtils.isConnected;
import static desynova.harsh.harshassignment.utils.ObjectUtil.isNull;


public class RemoteRepository implements RemoteSource {
    private final String UNDELIVERABLE_EXCEPTION_TAG = "Undeliverable exception received, not sure what to do";
    private ServiceGenerator serviceGenerator;

    @Inject
    public RemoteRepository(ServiceGenerator serviceGenerator) {
        this.serviceGenerator = serviceGenerator;
    }


    @Override
    public Single getTabDataOne() {
        RxJavaPlugins.setErrorHandler(throwable -> {
            Log.i(UNDELIVERABLE_EXCEPTION_TAG, throwable.getMessage());
            return;
        });
        Single<TabOne> tabOneDataOneSingle = Single.create(singleOnSubscribe -> {
                    if (!isConnected(DesynovaApp.getContext())) {
                        Exception exception = new NetworkErrorException();
                        singleOnSubscribe.onError(exception);
                    } else {
                        try {
                            WebService webService = serviceGenerator.createService(WebService.class, Constants.BASE_URL);
                            ServiceResponse serviceResponse = processCall(webService.fetchTabOneRecyclerOne(), false);
                            if (serviceResponse.getCode() == SUCCESS_CODE) {
                                TabOne tabOne = (TabOne) serviceResponse.getData();
                                singleOnSubscribe.onSuccess(tabOne);
                            } else {
                                Throwable throwable = new NetworkErrorException();
                                singleOnSubscribe.onError(throwable);
                            }
                        } catch (Exception e) {
                            singleOnSubscribe.onError(e);
                        }
                    }
                }
        );
        return tabOneDataOneSingle;
    }

    @Override
    public Single getTabDataTwo() {
        RxJavaPlugins.setErrorHandler(throwable -> {
            Log.i(UNDELIVERABLE_EXCEPTION_TAG, throwable.getMessage());
            return;
        });
        Single<TabOne> tabOneDataTwoSingle = Single.create(singleOnSubscribe -> {
                    if (!isConnected(DesynovaApp.getContext())) {
                        Exception exception = new NetworkErrorException();
                        singleOnSubscribe.onError(exception);
                    } else {
                        try {
                            WebService webService = serviceGenerator.createService(WebService.class, Constants.BASE_URL);
                            ServiceResponse serviceResponse = processCall(webService.fetchTabOneRecyclerTwo(), false);
                            if (serviceResponse.getCode() == SUCCESS_CODE) {
                                TabOne tabOne = (TabOne) serviceResponse.getData();
                                singleOnSubscribe.onSuccess(tabOne);
                            } else {
                                Throwable throwable = new NetworkErrorException();
                                singleOnSubscribe.onError(throwable);
                            }
                        } catch (Exception e) {
                            singleOnSubscribe.onError(e);
                        }
                    }
                }
        );
        return tabOneDataTwoSingle;
    }

    @Override
    public Single getTabTwo() {
        RxJavaPlugins.setErrorHandler(throwable -> {
            Log.i(UNDELIVERABLE_EXCEPTION_TAG, throwable.getMessage());
            return;
        });
        Single<TabTwo> tabTwoSingle = Single.create(singleOnSubscribe -> {
                    if (!isConnected(DesynovaApp.getContext())) {
                        Exception exception = new NetworkErrorException();
                        singleOnSubscribe.onError(exception);
                    } else {
                        try {
                            WebService webService = serviceGenerator.createService(WebService.class, Constants.BASE_URL);
                            ServiceResponse serviceResponse = processCall(webService.fetchTabTwo(), false);
                            if (serviceResponse.getCode() == SUCCESS_CODE) {
                                TabTwo tabTwo = (TabTwo) serviceResponse.getData();
                                singleOnSubscribe.onSuccess(tabTwo);
                            } else {
                                Throwable throwable = new NetworkErrorException();
                                singleOnSubscribe.onError(throwable);
                            }
                        } catch (Exception e) {
                            singleOnSubscribe.onError(e);
                        }
                    }
                }
        );
        return tabTwoSingle;
    }

    @Override
    public Single getTabThree() {
        RxJavaPlugins.setErrorHandler(throwable -> {
            Log.i(UNDELIVERABLE_EXCEPTION_TAG, throwable.getMessage());
            return;
        });
        Single<TabThree> tabThreeSingle = Single.create(singleOnSubscribe -> {
                    if (!isConnected(DesynovaApp.getContext())) {
                        Exception exception = new NetworkErrorException();
                        singleOnSubscribe.onError(exception);
                    } else {
                        try {
                            WebService webService = serviceGenerator.createService(WebService.class, Constants.BASE_URL);
                            ServiceResponse serviceResponse = processCall(webService.fetchTabThree(), false);
                            if (serviceResponse.getCode() == SUCCESS_CODE) {
                                TabThree tabThree = (TabThree) serviceResponse.getData();
                                singleOnSubscribe.onSuccess(tabThree);
                            } else {
                                Throwable throwable = new NetworkErrorException();
                                singleOnSubscribe.onError(throwable);
                            }
                        } catch (Exception e) {
                            singleOnSubscribe.onError(e);
                        }
                    }
                }
        );
        return tabThreeSingle;
    }

    @NonNull
    private ServiceResponse processCall(Call call, boolean isVoid) {
        if (!isConnected(DesynovaApp.getContext())) {
            return new ServiceResponse(new ServiceError());
        }
        try {
            retrofit2.Response response = call.execute();
            /*Gson gson = new Gson();
            L.json(GroupModel.class.getName(), gson.toJson(response.body()));*/
            if (isNull(response)) {
                return new ServiceResponse(new ServiceError(NETWORK_ERROR, ERROR_UNDEFINED));
            }
            int responseCode = response.code();
            if (response.isSuccessful()) {
                return new ServiceResponse(responseCode, isVoid ? null : response.body());
            } else {
                ServiceError ServiceError;
                ServiceError = new ServiceError(response.message(), responseCode);
                return new ServiceResponse(ServiceError);
            }
        } catch (IOException e) {
            return new ServiceResponse(new ServiceError(NETWORK_ERROR, ERROR_UNDEFINED));
        }
    }

}

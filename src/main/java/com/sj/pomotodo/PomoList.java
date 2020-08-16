package com.sj.pomotodo;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PomoList extends ArrayList<Pomo> {
    public static class AsyncCallback<T> implements Callback<PomoList> {
        static PomoList mPomoList = new PomoList();

        @Override
        public void onResponse(Call<PomoList> call, Response<PomoList> response) {
            System.out.println("onResponse(" + call + ", " + response.code() + ")");

            if (response.isSuccessful()) {
                PomoList pomoList = response.body();
//                for (Pomo pomo : pomoList) {
//                    System.out.print(pomo.toText());
//                }
                mPomoList.addAll(pomoList);
            }
        }

        @Override
        public void onFailure(Call<PomoList> call, Throwable t) {
            System.out.println("onFailure(" + call + ", " + t + ")");
        }
    }

    public static class SyncCallback<T> extends AsyncCallback<T> {
        private final CountDownLatch mLocker;

        public SyncCallback(CountDownLatch locker) {
            mLocker = locker;
        }

        @Override
        public void onResponse(Call call, Response response) {
            super.onResponse(call, response);
            mLocker.countDown();
        }

        @Override
        public void onFailure(Call call, Throwable t) {
            super.onFailure(call, t);
            mLocker.countDown();
        }
    }
}

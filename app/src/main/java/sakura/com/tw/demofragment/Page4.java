package sakura.com.tw.demofragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Page4 extends Fragment {
    private View mainView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        //保留上一次頁面狀態
        if (mainView == null) {
            mainView = inflater.inflate(R.layout.page4, container, false);
        }
        return mainView;
    }
}

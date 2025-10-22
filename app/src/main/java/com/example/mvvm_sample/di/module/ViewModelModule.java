    package com.example.mvvm_sample.di.module;

    import androidx.lifecycle.ViewModel;
    import androidx.lifecycle.ViewModelProvider;

    import com.example.mvvm_sample.DaggerViewModelFactory;
    import com.example.mvvm_sample.di.scoped.ViewModelKey;
    import com.example.mvvm_sample.ui.FeatureViewModel;
    import com.example.mvvm_sample.ui.NotificationViewModel;
    import com.example.mvvm_sample.ui.WebviewViewModel;

    import dagger.Binds;
    import dagger.Module;
    import dagger.multibindings.IntoMap;

    @Module
    abstract class ViewModelModule {
        @Binds
        @IntoMap
        @ViewModelKey(FeatureViewModel.class)
        abstract ViewModel bindFeatureViewModel(FeatureViewModel viewModel);

        @Binds
        @IntoMap
        @ViewModelKey(NotificationViewModel.class)
        abstract ViewModel bindNotificationViewModel(NotificationViewModel viewModel);

        @Binds
        @IntoMap
        @ViewModelKey(WebviewViewModel.class)
        abstract ViewModel bindWebviewViewModel(WebviewViewModel viewModel);

        @Binds
        abstract ViewModelProvider.Factory bindVmFactory(DaggerViewModelFactory factory);
    }

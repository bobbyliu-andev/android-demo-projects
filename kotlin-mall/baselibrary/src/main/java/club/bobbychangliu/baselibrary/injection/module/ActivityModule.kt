package club.bobbychangliu.baselibrary.injection.module

import android.app.Activity
import club.bobbychangliu.baselibrary.injection.scope.ActivityScope
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(private val activity: Activity) {

    @Provides
    @ActivityScope
    fun providesActivity(): Activity {
        return activity
    }
}
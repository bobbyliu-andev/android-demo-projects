package club.bobbychangliu.usercenter.injection.module

import club.bobbychangliu.usercenter.service.UserService
import club.bobbychangliu.usercenter.service.UserServiceImpl
import dagger.Module
import dagger.Provides

@Module
class UserModule {

    @Provides
    fun providesUserService(service: UserServiceImpl): UserService {
        return service
    }
}
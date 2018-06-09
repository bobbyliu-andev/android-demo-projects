# Shopping Mall App Demo
----
## Overview
* Native Android shopping.
* Kotlin 1.2.41 and Java 1.8.
* Android Studio 3.1.
* Gradle 3.1.2.

## Features
* Modulization: separate base module and business modules with the main app to achieve independent developing in teamwork
* MVP Architecture: separate view and business logic for better code management and feature update
* Kotlin Extension
* Anko
* Reactive Programming: RxJava, RxAndroid, RxKotlin
* Dagger2: dependency injection for decoupling
* Retrofit2: REST client
* Gson: convert JSON
* Glide: load image
* Arouter
* ETC...

## Documentation
The business layers adopt the Reactive pattern. RxLifecycle imported to cancel subscription to resolve memory leak.

## Dependencies
The dependencies and versions:

```xml
    ext.anko_version = '0.10.5'

    ext.rx_kotlin_version = '1.0.0'
    ext.rx_android_version ='1.2.1'

    ext.ok_http_version = '3.4.1'
    ext.retrofit_version = '2.1.0'

    ext.dagger_version = '2.11'

    ext.rx_lifecycle_version = '1.0'

    ext.glide_version = "3.7.0"
    ext.arouter_api_version = '1.2.2'
    ```

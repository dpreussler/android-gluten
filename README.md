android-gluten
==============

Gluten is what it all holds together.
This is the glue code between popular android libraries.


Logging and Autolayout moved, only glue-code classes for loggings are left here
================================================================================
As gluten grew too large I moved some things to seperate frameworks:
the logging can be found here:
https://github.com/dpreussler/jodamoblog

The famous autolayout moved to:
https://github.com/dpreussler/android-autolayout

Other utils moved to:
https://github.com/dpreussler/androtil


Gradle
======

```groovy
compile 'de.jodamob.android:android-gluten:0.6' {
        // exclude android classed, will be provided in android project
        exclude group: 'org.robolectric'
        // exclude all dependencies you dont need i.e.
        exclude group: 'com.newrelic.agent.android'
}

```


SafePhrase
==========
Runtime safe version of the Phrase class

Usage:
Same as Phrase:

```java
SafePhrase.from(...).put(...).format()
```

Will not crash if elements are not found. Will do a wtf log instead (when combined with Crashlytics
logger this would still send the stacktrace so you can fix it easily with next version).


https://github.com/square/phrase


New Relic Tracer for Retrofit
====================================
New Relic is an app monitoring tool. It can log HTTP results for HttpUrlConnection and Apache and is supposed to support OkHttp
 since release 264 but seems to have some problems. So here are alternative trace implementations


Usage:

set a profiler when building the RestAdapter:

```java
return new RestAdapter.Builder()
            .setProfiler(new TracedRetrofitProfiler(new NewRelicTracer()));
```

if you also want the body (on erorrs only) be logged to NewRelic
you have to use wrap the client, the profiler does not work:

```java
return new RestAdapter.Builder()
            .setClient(new TracedRetrofitClient(
                    new OkHttpClient(), // or whatever you use
                    new NewRelicTracer()));
```


https://github.com/square/retrofit
https://docs.newrelic.com/docs/mobile-apps/android-api
https://github.com/square/okhttp



Small helpers
====================================

check out helper classes

```java

ViewUtils.hideView(view1, view2,..)
ViewUtils.showView(view1, view2,..)
ViewUtils.removeView(view1, view2,..)
ViewUtils.showOrHide(condition, view1, view2...)
ViewUtils.showOrRemove(condition, view1, view2...)
```



Licensed under MIT license
(c) 2014 Danny Preussler

This software binds to other software, please acknowledge those copyrights
or make sure to exclude them at compile time:
Espresso and Android Copyright Google Inc.
OkHTTP, Picasso, Phrase Copyright Square, Inc.
Crashlytics, Copyright Crashlytics
New Relic, Copyright New Relic, Inc.


android-gluten
==============

Gluten is what it all holds together.
This is the glue code between popular android libraries.


Gradle
======

```groovy
compile ('de.jodamob.android:android-gluten:0.3-3-g89a07a5') {
    // exclude some dependencies because it was build as java not android project
        exclude group: 'org.robolectric'
}
```

or grab from build folder and put in your libs:

```groovy
compile files('libs/android-gluten-0.2-16-gfbf52e4.jar')
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
StreamUtils.copy(inputstream, outputstream)

Closeables.closeQuietly(stream1, stream2,...)

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


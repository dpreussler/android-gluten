android-gluten
==============

Gluten is what it all holds together.
This is the glue code between popular android libraries. 

Espresso:
=========
Espresso is a testing library based on Android Instrumentation tests
https://code.google.com/p/android-test-kit/wiki/Espresso

Espresso waits before checking assertions to be idle:
it checks the mainlooper and AsyncTasks default-executor

But custom threads and threadpools but be registerd as "IdleResource" to be monitored.

IdleResource for Android Priority-JobQueue
------------------------------------------------------------------
https://github.com/path/android-priority-jobqueue

Usage:
Espresso.registerIdlingResources(new PriorityJobQueueIdleMonitor(manager));



New Relic implementations for OkHttp
====================================
New Relic is an app monitoring tool
It can log HTTP results for HttpUrlConnection and Apache but not OkHttp
https://docs.newrelic.com/docs/mobile-apps/android-api

New Relic Trace for Retrofit
-------------------------------------------

Usage: 
instead of
new OkClient(new OkHttpClient()) for the RestAdapter use:
new TracedRetrofitClient(new OkClient(new OkHttpClient()), new NewRelicTracer())

https://github.com/square/retrofit
https://github.com/square/okhttp

TODO: very basic for now, no load tracking

New Relic Trace for Picasso
---------------------------

Usage: 
create your Picasso instance with something like this:
new Picasso.Builder(context).downloader(
	new TracedPicassoDownloader(new NewRelicTracer(), context)).build();

https://github.com/square/picasso

TODO: very basic for now, no load tracking


These source samples are licensed under MIT license
(c) 2013 Danny Preussler

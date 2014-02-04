android-gluten
==============

Gluten is what it all holds together.
This is the glue code between popular android libraries. 


Espresso IdleResource implementation for Android Priority-JobQueue
https://code.google.com/p/android-test-kit/wiki/Espresso
https://github.com/path/android-priority-jobqueue

Usage:
Espresso.registerIdlingResources(new PriorityJobQueueIdleMonitor(manager));



New Relic implementations
=========================
new relic is an app monitoring tool
It can log HTTP results for HttpUrlConnection and Apache but not OkHttp
https://docs.newrelic.com/docs/mobile-apps/android-api

New Relic Trace implementation for Retrofit (+OkHttp)
https://github.com/square/retrofit
https://github.com/square/okhttp

Usage: 
instead of
new OkClient(new OkHttpClient()) for the RestAdapter use:
new TracedRetrofitClient(new OkClient(new OkHttpClient()), new NewRelicTracer())


New Relic Trace implementation for Picasso Image Loader (based on OkHttp)
https://github.com/square/picasso

Usage: 
create your Picasso instance with something like this:
new Picasso.Builder(context).downloader(
	new TracedPicassoDownloader(new NewRelicTracer(), context)).build();



These source samples are licensed under MIT license
(c) 2013 Danny Preussler

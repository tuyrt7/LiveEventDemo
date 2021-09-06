# LiveData封装的事件库LiveEventBus，在Github上作者“cyixlq”的源码基础上修改了一下接口使用方式

使用方式：
```

    gradle 依赖：
        //implementation 'com.tuyrt:live-event-bus:1.0.0'
        implementation 'com.github.tuyrt7:live-data-bus:1.0.0'

    LiveEventBus
        .get("EVENT_KEY", String::class.java)
        .observe(this) {
            Log.d("TAG", "MainActivity 通过String作为key订阅接收到的值：$it")
        }

```
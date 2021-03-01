# LiveData封装的事件库LiveEventBus，在Github上作者“cyixlq”的源码基础上修改了一下接口使用方式

使用方式：
```
    LiveEventBus
        .get("EVENT_KEY", String::class.java)
        .observe(this) {
            Log.d("TAG", "MainActivity 通过String作为key订阅接收到的值：$it")
        }

```
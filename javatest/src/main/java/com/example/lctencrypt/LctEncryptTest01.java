package com.example.lctencrypt;

/**
 * Created by wuhai on 2018/01/10 14:47.
 * 描述：
 */

public class LctEncryptTest01 {

    public static void main(String[] args){
//        encryptAES("40");//加密
        decryptAES("FM67HTHGpBHR4+GTE8WfzVS7yyyKeXOGxY1gqIvfGlYf9vBAB8KNs6d9oJ0ZFzgkUvsJk5ipSpIH\n" +
                "FgrFYO1fkeT19qUs4c9ksaURAMuPTI5qEu75Ysxu6MWT1jshYKoP83sEQiU8RTk6OsJ09b/lR+v6\n" +
                "75P2lYS/t+oK5RIl6w49Uiasu0VpESr92x+A5eMK3yWkN7UUVLq6ABqbTMUklJ8Z3sezIz4hEApX\n" +
                "nlS/vTn/1AqZqToluYlE51KHiN3nQqA/FvUkZVXKN35iXVMEVnbO1XbZ6rBpee5EPb72CdWbfv6+\n" +
                "Z2TERm8cNbY4xBVIzkQ3eAe726Mr83sQfsP8v2E/FzG2B2rpTECc3Ljqh9k/pOSmbpjryl8QtydA\n" +
                "l8EAU2naAePZ4J/k+9IJdDk4Pp4kOULoYo7RgDL8tm+Py4SxAfv3+uXtkARk/gKVvX/bVGGXyYby\n" +
                "JVzZp7Px7S3lzUk6Mkk6VcwbQebteuI8AmkL/s5BSf5LseTWp9hUJ3zeJankrXyJzue63QPps5vn\n" +
                "exqtgYVvuasl5dbTY6u7uBjlcO+rjupZtUxYVEA5W9MybXY+ppardSjswb95tdH0RQPW4QhcDYhg\n" +
                "m6P9sXbbG3RboSjGOQohDIUQYEtSlrmVjsVZ/X3AQ7knLIlvBhr0LJcM2mcM6ELx6N1jvzshUtW1\n" +
                "GbTT64bobLIj4MjW3ZGeNDgFKpZb8xPCj9rFyZmLNclaLnsqntzOklOoqBu1T5qbTJBZYuahbjOm\n" +
                "reH2ek8h/9C9tb/nUW3JfDW0kerO467eoLDHIvfZcX+8nQlcJTh1Qmhf/oze38h/oxfn0tFQ5/mI\n" +
                "5bwyJwMaxtwUj5oDVm242lGjg1XxXnl2JxKw2MUjH9fwVpgr015Gm+LotdF9B0MjyJQy0nYA/++h\n" +
                "P3ThYPMDeTwdmmfm1g28lc4ut5DH53cSN6AAjEM6m3b8D6NP3wtfbmRWSU3lVKZ9KVY4zr8ufn1x\n" +
                "N6k97AWFsypYZAPax+80oxyYMcuAVGFR0n1Oiyv90A7ZYAhEN4Vac2VzAPbM4WqG48w6bdorJ82p\n" +
                "3XOmB+o0gF0Ih+w8s8dH3DRGGcW/ck1YMNlC+L7qeKUPWEbS0vZPZ7Qx22NajblXIQtO5u+VjJb9\n" +
                "eOHkMLkOdF/GyC+MdjVzDKUv7zwI+E8Ojdi/r66Dy/f2wJ9UJf4Y8YiMPMWDwQkokeXkZSLIn0vL\n" +
                "MevkmkZMtPxaINNIKjlJ6XEnchEoHKMxCmClqmMM369OmMxEOhKO6jQ1hb/ZSZCtVc8iFjADiWzp\n" +
                "xnc2DkF8rVSOh+ydEF70QFRWi+lrNf0=\n");//解密
    }

    /**
     * 解密
     * @param msg
     */
    public static void decryptAES(String msg){
        if(msg.equals("") || msg == null){
            return;
        }

        String msgDecrypt = PosEncryptUtils.decryptAES(msg);
        System.out.println("解密后："+msgDecrypt);
    }

    /**
     * 加密
     * @param msg
     */
    public static void encryptAES(String msg){
        if(msg.equals("") || msg == null){
            return;
        }

        String msgEncrypt = PosEncryptUtils.encryptAES(msg);
        System.out.println("加密后："+msgEncrypt);
    }

    public static void method01(){
        String msg = "15";
        String msgEncrypt = PosEncryptUtils.encryptAES(msg);
        System.out.println("加密后："+msgEncrypt);

        String msgDecrypt = PosEncryptUtils.decryptAES(msgEncrypt);
        System.out.println("解密后："+msgDecrypt);
    }

}

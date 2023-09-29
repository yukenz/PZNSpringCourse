package com.awan.pznspring.configproc;

/*
 * Melakukan conditional Bean dengan @Profile
 * tergantung pada spring.profiles.active=production
 * Class Kandidat harus diwakilkan onleh Interface saat AutoWired
 *
 * */
public interface DateHandlerEnv {
    int getDayOfYear();
}

package com.haokuo.housemanage.houserentmanage.Utils;


import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;

public class SPHelper {
public static final String PREFS_NAME = "ui";
public static final String KEY_TOKEN = "token";

public static final String VALUE_TOKEN_DEFAULT = "";



private static SPHelper instance;
private Context context;
private SharedPreferences settings;
private SharedPreferences.Editor editor;

private SPHelper(Context context) {
        this.context = context;
        }

public static void init(Context ctx) {
        if (instance == null)
        instance = new SPHelper(ctx);
        }

public static SPHelper getInstance() {
        if (instance == null)
        throw new IllegalArgumentException("SPHelper");
        return instance;
        }

private void initSettings() {
        if (this.settings == null)
        this.settings = context.getSharedPreferences(PREFS_NAME,
        Context.MODE_PRIVATE);
        }

private void initEditor() {
        initSettings();
        if (this.editor == null)
        this.editor = this.settings.edit();
        }

public SPHelper putString(String key, String value) {
        initEditor();
        this.editor.putString(key, value);
        return this;
        }
public SPHelper putBoolean(String key, boolean value) {
        initEditor();
        this.editor.putBoolean(key, value);
        return this;
        }
public SPHelper remove(String key) {
        initEditor();
        this.editor.remove(key);
        return this;
        }

public SPHelper commit() {
        if (this.editor != null)
        this.editor.commit();
        return this;
        }

public String getString(String key, String defValue) {

        initSettings();
        return this.settings.getString(key, defValue);
        }
public boolean getBoolean(String key, boolean defValue) {
        initSettings();
        return this.settings.getBoolean(key, defValue);
        }

public boolean contains(String key) {
        initSettings();
        return this.settings.contains(key);
        }
public SPHelper putObject(String key, Object object) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream out = null;
        try {
        out = new ObjectOutputStream(baos);
        out.writeObject(object);
        String objectVal = new String(Base64.encode(baos.toByteArray(), Base64.DEFAULT));
        initEditor();
        this.editor.putString(key, objectVal);
        return this;
        } catch (IOException e) {
        e.printStackTrace();
        }finally {
        try {
        if (baos != null) {
        baos.close();
        }
        if (out != null) {
        out.close();
        }
        } catch (IOException e) {
        e.printStackTrace();
        }
        }
        return this;
        }

@SuppressWarnings("unchecked")
public <T> T getObject(String key, Class<T> clazz) {
        initSettings();
        String objectVal =this.settings.getString(key, null);
        if (objectVal!=null) {
        byte[] buffer = Base64.decode(objectVal, Base64.DEFAULT);
        ByteArrayInputStream bais = new ByteArrayInputStream(buffer);
        ObjectInputStream ois = null;
        try {
        ois = new ObjectInputStream(bais);
        T t = (T) ois.readObject();
        return t;
        } catch (StreamCorruptedException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        } catch (ClassNotFoundException e) {
        e.printStackTrace();
        } finally {
        try {
        if (bais != null) {
        bais.close();
        }
        if (ois != null) {
        ois.close();
        }
        } catch (IOException e) {
        e.printStackTrace();
        }
        }
        }
        return null;
        }

public SPHelper putInt(String key, int value) {
        initEditor();
        this.editor.putInt(key, value);
        return this;
        }

public int getInt(String key, int defValue) {
        initSettings();
        return this.settings.getInt(key, defValue);
        }
}
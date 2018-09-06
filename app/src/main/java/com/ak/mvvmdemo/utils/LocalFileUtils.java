package com.ak.mvvmdemo.utils;

import android.annotation.SuppressLint;
import android.content.ContentUris;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;

import com.ak.mvvmdemo.R;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by ak on 5/5/17.
 */

public class LocalFileUtils {


    private static final String FILE_SEPARATOR = "/";

    private static String getRootDirectory() {
        return Environment.getExternalStorageDirectory().getPath();
    }

    public static String getAppDirectory(Context context) {
        String appName = context.getString(R.string.app_name);
        File directory = new File(getRootDirectory() + FILE_SEPARATOR + appName);

        if (!directory.exists()) {
            directory.mkdir();
        }
        return directory.getAbsolutePath();
    }

    public static String getImageDirectory(Context context) {
        String appDirectory = getAppDirectory(context);
        File file = new File(appDirectory + FILE_SEPARATOR + "Images");
        if (!file.exists()) {
            file.mkdir();
        }
        return file.getAbsolutePath();
    }

    public static String getOtherDirectory(Context context) {
        String appDirectory = getAppDirectory(context);
        File file = new File(appDirectory + FILE_SEPARATOR + "Other");
        if (!file.exists()) {
            file.mkdir();
        }
        return file.getAbsolutePath();
    }

    public static String getProfilrDirectory(Context context) {
        String appDirectory = getAppDirectory(context);
        File file = new File(appDirectory + FILE_SEPARATOR + "Profile");
        if (!file.exists()) {
            file.mkdir();
        }
        return file.getAbsolutePath();
    }

    public static String getAudioDirectory(Context context) {
        String appDirectory = getAppDirectory(context);
        File file = new File(appDirectory + FILE_SEPARATOR + "Audios");
        if (!file.exists()) {
            file.mkdir();
        }
        return file.getAbsolutePath();
    }

    public static String getSignatureDirectory(Context context) {
        String appDirectory = getAppDirectory(context);
        File file = new File(appDirectory + FILE_SEPARATOR + "Signatures");
        if (!file.exists()) {
            file.mkdir();
        }
        return file.getAbsolutePath();
    }

    public static String getBytesFromFile(String filePath) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        BufferedInputStream in;
        try {
            in = new BufferedInputStream(new FileInputStream(filePath));
            int read;
            byte[] buff = new byte[1024];
            while ((read = in.read(buff)) > 0) {
                out.write(buff, 0, read);
            }
            out.flush();
            byte[] audioBytes = out.toByteArray();
            return Base64.encodeToString(audioBytes, Base64.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }


    public static String base64StringToFile(String base64AudioData, String fullPath) {

        byte[] decoded = Base64.decode(base64AudioData, Base64.DEFAULT);
        //decoded=Base64.decode(decoded, Base64.DEFAULT);
        try {
            File file2 = new File(fullPath);
            FileOutputStream os = new FileOutputStream(file2, true);
            os.write(decoded);
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fullPath;
    }

    public static String getFileName(String filePath) {
        if (filePath != null && filePath.lastIndexOf('/') != -1) {
            String fileName = filePath.substring(filePath.lastIndexOf('/') + 1);
            Log.v("rv911", "File Name : " + fileName);
            return fileName;
        }
        return filePath;
    }

    public static boolean deleteFile(String filePath) {
        if (TextUtils.isEmpty(filePath)) return false;
        try {
            File file = new File(filePath);
            return file.exists() && file.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static String getRealPathFromURI(Context context, Uri contentUri) {
        String path = "";
        try {
            if (Build.VERSION.SDK_INT < 19) {
                path = getRealPathFromURI_API11to18(context, contentUri);
            } else {
                path = getRealPathFromURI_API11to18(context, contentUri);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return path;
    }

    @SuppressLint("NewApi")
    public static String getRealPathFromURI_API19(Context context, Uri uri) {
        String filePath = "";
        String wholeID = DocumentsContract.getDocumentId(uri);
        if (wholeID.contains(":")) {
            String id = wholeID.split(":")[1];
            String[] column = {MediaStore.Images.Media.DATA};
            String sel = MediaStore.Images.Media._ID + "=?";
            Cursor cursor = context.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                    column, sel, new String[]{id}, null);
            int columnIndex;
            if (cursor != null) {
                columnIndex = cursor.getColumnIndex(column[0]);
                if (cursor.moveToFirst()) {
                    filePath = cursor.getString(columnIndex);
                }
                cursor.close();
            }
            return filePath;
        } else {
            Cursor returnCursor = context.getContentResolver().query(uri, null, null, null, null);
            int nameIndex;
            if (returnCursor != null) {
                nameIndex = returnCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
                returnCursor.moveToFirst();
                filePath = returnCursor.getString(nameIndex);
            }
        }
        return filePath;
    }

    @SuppressLint("NewApi")
    public static String getRealPathFromURI_API11to18(Context context, Uri contentUri) {
        String[] proj = {MediaStore.Images.Media.DATA};
        String result = null;
        CursorLoader cursorLoader = new CursorLoader(
                context,
                contentUri, proj, null, null, null);
        Cursor cursor = cursorLoader.loadInBackground();
        if (cursor != null) {
            int column_index =
                    cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            result = cursor.getString(column_index);
        }
        return result;
    }

    private static String getFileExtension(String filePath) {
        return filePath.substring(filePath.lastIndexOf("."));
    }

    public static boolean isDocument(String filePath) {
        String extension = getFileExtension(filePath);
        for (String type : getSupportedDocumentTypes()) {
            if (type.equals(extension.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    private static String[] getSupportedDocumentTypes() {
        return new String[]{
                ".txt",
                ".xml",
                ".csv",
                ".htm",
                ".html",
                ".pdf",
                ".doc",
                ".docx",
                ".xls",
                ".xlsx",
                ".rtf"
        };
    }

    public static String getTimeStamp() {
        return new SimpleDateFormat("yyyyMMdd-HHmmss", Locale.getDefault()).format(new Date());
    }


    public static String getPath(final Context context, final Uri uri) {

        if ( DocumentsContract.isDocumentUri(context, uri)) {
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];
                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }
            } else if (isDownloadsDocument(uri)) {
                final String id = DocumentsContract.getDocumentId(uri);
                final Uri contentUri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));
                return getDataColumn(context, contentUri, null, null);

            } else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                Uri contentUri = null;
                switch (type) {
                    case "image":
                        contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                        break;
                    case "video":
                        contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                        break;
                    case "audio":
                        contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                        break;
                }

                final String selection = "_id=?";
                final String[] selectionArgs = new String[]{
                        split[1]
                };

                return getDataColumn(context, contentUri, selection, selectionArgs);
            }
        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
            if (isGooglePhotosUri(uri))
                return uri.getLastPathSegment();

            return getDataColumn(context, uri, null, null);

        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }

        return null;
    }

    private static String getDataColumn(Context context, Uri uri, String selection,
                                        String[] selectionArgs) {
        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {column};
        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs, null);
            if (cursor != null && cursor.moveToFirst()) {
                final int index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }

    private static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    private static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    private static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    public static boolean isGooglePhotosUri(Uri uri) {
        return uri.getAuthority().contains("com.google.android.apps.photos.content");
    }

    public static boolean isGoogleContentUri(Uri uri) {
        return uri.getAuthority().contains("com.google.android.apps");
    }

    public static Bitmap scaleDown(Bitmap realImage, float maxImageSize,
                                   boolean filter) {
        float ratio = Math.min(
                maxImageSize / realImage.getWidth(),
                maxImageSize / realImage.getHeight());
        int width = Math.round(ratio * realImage.getWidth());
        int height = Math.round(ratio * realImage.getHeight());

        return Bitmap.createScaledBitmap(realImage, width, height, filter);
    }

    public static String getImageUrlWithAuthority(Context context, Uri uri) {
        InputStream is = null;
        if (uri.getAuthority() != null) {
            try {
                is = context.getContentResolver().openInputStream(uri);
                Bitmap bmp = BitmapFactory.decodeStream(is);
                return writeToTempImageAndGetPathUri(context, bmp).toString();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (is != null) {
                        is.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public static Uri writeToTempImageAndGetPathUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        Log.v("rv911", "Google Image Path : " + Uri.parse(path).getPath());
        return Uri.parse(path);
    }

    public static void deleteFile(File fileOrDirectory) {
        if (fileOrDirectory.isDirectory())
            for (File child : fileOrDirectory.listFiles())
                deleteFile(child);

        fileOrDirectory.delete();
    }

    public static Intent createGetContentIntent() {
        // Implicitly allow the user to select a particular kind of data
        final Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        // The MIME data type filter
        intent.setType("*/*");
        // Only return URIs that can be opened with ContentResolver
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        return intent;
    }

    public static final int THUMBNAIL_SIZE = 1000;


    public static Bitmap getBitmap(String path) {

        return BitmapFactory.decodeFile(path);

    }
}

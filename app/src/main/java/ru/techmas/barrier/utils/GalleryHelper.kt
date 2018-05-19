package ru.techmas.barrier.utils

import android.content.Intent
import android.app.Activity
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.provider.MediaStore
import java.io.File
import java.io.IOException
import android.provider.DocumentsContract
import android.content.ContentUris
import android.os.Build.VERSION
import android.os.Build.VERSION.SDK_INT
import android.annotation.SuppressLint
import android.content.CursorLoader
import android.os.Build
import android.os.Environment
import android.provider.OpenableColumns
import java.net.URISyntaxException
import android.media.MediaScannerConnection
import android.util.Log
import android.view.View
import java.io.ByteArrayOutputStream
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*


class GalleryHelper(private val activity: Activity) {

    private lateinit var imageFilePath: String
    private lateinit var imageFile: File
    private lateinit var successListener: OnSuccessListener
    private lateinit var errorListener: OnErrorListener
    private var width: Int = 0
    private var height: Int = 0

    interface OnSuccessListener {
        fun successGalleryPhoto(name: String, bitmap: Bitmap)
    }

    interface OnErrorListener {
        fun errorGallery(message: String)
    }

    fun onSuccess(listener: OnSuccessListener): GalleryHelper {
        this.successListener = listener
        return this
    }

    fun onError(listener: OnErrorListener): GalleryHelper {
        this.errorListener = listener
        return this
    }

    fun execute(): GalleryHelper {
        val galleryIntent = Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        activity.startActivityForResult(galleryIntent, GALLERY_REQUEST_CODE)
        return this
    }

    fun setViewDimensions(view: View): GalleryHelper {
        width = view.width
        height = view.height
        return this
    }

    fun setFilePrefix(prefix: String): GalleryHelper {
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val imageFileName: String = prefix + timeStamp
        val storageDir: File = activity.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        if (!storageDir.exists()) storageDir.mkdirs()
        imageFile = File.createTempFile(imageFileName, ".jpg", storageDir)
        imageFilePath = imageFile.absolutePath
        return this
    }

    fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {
        when (requestCode) {
            GALLERY_REQUEST_CODE -> {
                if (resultCode == Activity.RESULT_OK) {
                    if (intent != null) {
                        try {
                            val bitmap = MediaStore.Images.Media.getBitmap(activity.contentResolver, intent.data)
                            saveImage(bitmap)
                            successListener.successGalleryPhoto(imageFilePath, setScaledBitmap())
                        } catch (e: Exception) {
                            e.printStackTrace()
                            errorListener.errorGallery("Failed get image")
                        }
                    }
                }
            }
            else -> errorListener.errorGallery("Unrecognized request code")
        }
    }

    fun saveImage(myBitmap: Bitmap) {
        val bytes = ByteArrayOutputStream()
        myBitmap.compress(Bitmap.CompressFormat.JPEG, 90, bytes)
        try {
            val fo = FileOutputStream(imageFile)
            fo.write(bytes.toByteArray())
            MediaScannerConnection.scanFile(activity,
                    arrayOf(imageFilePath),
                    arrayOf("image/jpeg"), null)
            fo.close()
        } catch (e1: IOException) {
            errorListener.errorGallery("Can't write file")
        }
    }
//    fun getRealPathFromURI(contentUri: Uri): String {
//        val proj = arrayOf(MediaStore.Images.Media.DATA)
//        val loader = CursorLoader(activity, contentUri, proj, null, null, null)
//        val cursor = loader.loadInBackground()
//        val column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
//        cursor.moveToFirst()
//        val result = cursor.getString(column_index)
//        cursor.close()
//        return result
//    }

//    private fun getFileName(uri: Uri): String {
//        if (uri.scheme == "content") {
//            val cursor = activity.contentResolver.query(uri, null, null, null, null)
//            cursor.use {
//                if(cursor.moveToFirst()) {
//                    return cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME))
//                }
//            }
//        }
//
//        return uri.path.substring(uri.path.lastIndexOf('/') + 1)
//    }
//
//    fun getRealPathFromURI(contentUri: Uri): String {
//        var cursor: Cursor? = null
//        try {
//            val proj = arrayOf(MediaStore.Images.Media.DATA)
//            cursor = activity.getContentResolver().query(contentUri, proj, null, null, null)
//            val column_index = cursor!!.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
//            cursor.moveToFirst()
//            return cursor.getString(column_index)
//        } finally {
//            if (cursor != null) {
//                cursor.close()
//            }
//        }
//    }


//
//    @SuppressLint("NewApi")
//    @Throws(URISyntaxException::class)
//    fun getFilePath(uri: Uri): String? {
//        var uri = uri
//        var selection: String? = null
//        var selectionArgs: Array<String>? = null
//        // Uri is different in versions after KITKAT (Android 4.4), we need to
//        if (Build.VERSION.SDK_INT >= 19 && DocumentsContract.isDocumentUri(activity.getApplicationContext(), uri)) {
//            if (isExternalStorageDocument(uri)) {
//                val docId = DocumentsContract.getDocumentId(uri)
//                val split = docId.split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
//                val str = Environment.getExternalStorageDirectory().absolutePath
//                return  str + "/" + split[1]
//            } else if (isDownloadsDocument(uri)) {
//                val id = DocumentsContract.getDocumentId(uri)
//                uri = ContentUris.withAppendedId(
//                        Uri.parse("content://downloads/public_downloads"), java.lang.Long.valueOf(id))
//            } else if (isMediaDocument(uri)) {
//                val docId = DocumentsContract.getDocumentId(uri)
//                val split = docId.split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
//                val type = split[0]
//                if ("image" == type) {
//                    uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
//                } else if ("video" == type) {
//                    uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI
//                } else if ("audio" == type) {
//                    uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
//                }
//                selection = "_id=?"
//                selectionArgs = arrayOf(split[1])
//            }
//        }
//        if ("content".equals(uri.scheme, ignoreCase = true)) {
//            val projection = arrayOf(MediaStore.Images.Media.DATA)
//            var cursor: Cursor? = null
//            try {
//                cursor = activity.getContentResolver()
//                        .query(uri, projection, selection, selectionArgs, null)
//                val column_index = cursor!!.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
//                if (cursor.moveToFirst()) {
//                    return cursor.getString(column_index)
//                }
//            } catch (e: Exception) {
//            }
//
//        } else if ("file".equals(uri.scheme, ignoreCase = true)) {
//            return uri.path
//        }
//        return null
//    }
//
//    fun isExternalStorageDocument(uri: Uri): Boolean {
//        return "com.android.externalstorage.documents" == uri.authority
//    }
//
//    fun isDownloadsDocument(uri: Uri): Boolean {
//        return "com.android.providers.downloads.documents" == uri.authority
//    }
//
//    fun isMediaDocument(uri: Uri): Boolean {
//        return "com.android.providers.media.documents" == uri.authority
//    }


    fun setScaledBitmap(): Bitmap {
        val imageViewWidth = width
        val imageViewHeight = height

        val bmOptions = BitmapFactory.Options()
        bmOptions.inJustDecodeBounds = true
        BitmapFactory.decodeFile(imageFilePath, bmOptions)
        val bitmapWidth = bmOptions.outWidth
        val bitmapHeight = bmOptions.outHeight

        val scaleFactor = Math.min(bitmapWidth/imageViewWidth, bitmapHeight/imageViewHeight)

        bmOptions.inJustDecodeBounds = false
        bmOptions.inSampleSize = scaleFactor

        return BitmapFactory.decodeFile(imageFilePath, bmOptions)

    }

    //    /**
//     * This method change listeners reference to avoid memory leaks
//     * Don't forget setup callbacks and your settings again!
//     */
//    private fun unbind() {
//        errorListener = null
//        successListener = null
//    }
//
    companion object {
        private val GALLERY_REQUEST_CODE = 152
        private val DIALOG_WITHOUT_CUSTOM_COLOR = 0
    }

    //    @SuppressLint("NewApi")
//    fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
//        if (requestCode == PERMISSION_REQUEST_CODE) {
//            for (permission in permissions) {
//                if (isPermissionNotGranted(permission)) {
//                    runDeniedOrNeverAskAgain(permission)
//                    return
//                }
//            }
//        }
//        successListener!!.run()
//        unbind()
//    }

    /**
     * This method start application settings activity
     * Note: is not possible to open at once screen with application permissions.
     */
//    fun startApplicationSettingsActivity() {
//        val context = context
//        val intent = Intent()
//        val uri = Uri.fromParts("package", context.getPackageName(), null)
//        intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
//        intent.data = uri
//        context.startActivity(intent)
//    }


    //    /**
//     * This method return context, depending on what you use: activity or fragment
//     *
//     * @return context
//     */
//    private val context: Context
//        get() = activity ?: fragment.getContext()


    //    /**
//     * Default fragment constructor
//     *
//     * @param fragment is fragment instance. Use it only in fragments
//     */
//    constructor(fragment: Fragment) {
//        this.fragment = fragment
//    }


}
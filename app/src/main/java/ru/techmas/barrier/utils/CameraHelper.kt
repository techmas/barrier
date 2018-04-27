package ru.techmas.barrier.utils

import android.content.Intent
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat
import android.content.DialogInterface
import android.support.annotation.RequiresApi
import android.app.Activity
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Environment
import android.provider.MediaStore
import android.support.v4.content.FileProvider
import android.view.View
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*


class CameraHelper(private val activity: Activity) {

    private lateinit var imageFilePath: String
    private lateinit var imageFile: File
    private lateinit var successListener: OnSuccessListener
    private lateinit var errorListener: OnErrorListener
    private var width: Int = 0
    private var height: Int = 0

    interface OnSuccessListener {
        fun successCameraPhoto(name: String, bitmap: Bitmap)
    }

    interface OnErrorListener {
        fun errorCamera(message: String)
    }

    fun onSuccess(listener: OnSuccessListener): CameraHelper {
        this.successListener = listener
        return this
    }

    fun onError(listener: OnErrorListener): CameraHelper {
        this.errorListener = listener
        return this
    }

    fun execute(): CameraHelper {
        try {
            val callCameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            if(callCameraIntent.resolveActivity(activity.packageManager) != null) {
                val authorities = activity.packageName + ".fileprovider"
                val imageUri = FileProvider.getUriForFile(activity, authorities, imageFile)
                callCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri)
                activity.startActivityForResult(callCameraIntent, CAMERA_REQUEST_CODE)
            }
        } catch (e: IOException) {
            errorListener.errorCamera("Could not create file!")
        }
        return this
    }

    fun setViewDimensions(view: View): CameraHelper {
        width = view.width
        height = view.height
        return this
    }

    fun setFilePrefix(prefix: String): CameraHelper {
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val imageFileName: String = prefix + timeStamp
        val storageDir: File = activity.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        if (!storageDir.exists()) storageDir.mkdirs()
        imageFile = File.createTempFile(imageFileName, ".jpg", storageDir)
        imageFilePath = imageFile.absolutePath
        return this
    }

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when(requestCode) {
            CAMERA_REQUEST_CODE -> {
                if (resultCode == Activity.RESULT_OK) {
                    successListener.successCameraPhoto(imageFilePath, setScaledBitmap())
//                    photoImageView.setImageBitmap(setScaledBitmap())
                }
            }
            else -> errorListener.errorCamera("Unrecognized request code")
        }
    }

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
        private val CAMERA_REQUEST_CODE = 101
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
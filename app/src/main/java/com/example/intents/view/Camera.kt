package com.example.intents.view

import androidx.compose.runtime.Composable
import android.Manifest
import android.util.Log
import android.view.ViewGroup
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.view.LifecycleCameraController
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import com.example.intents.Data
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import java.io.File
import java.util.concurrent.Executor

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun Camera(navController: NavController){
    val permissionState = rememberPermissionState(permission = Manifest.permission.CAMERA)
    val context = LocalContext.current
    val cameraController = remember{
        LifecycleCameraController(context)
    }
    val lifecycle = LocalLifecycleOwner.current

    LaunchedEffect(Unit){
        permissionState.launchPermissionRequest()
    }
    Scaffold (
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    val executor = ContextCompat.getMainExecutor(context)
                    takePicture(cameraController, executor)
                    navController.navigate("home")
                }
            ){
                Text(text = "Tomar foto")
            }
        }
    ){
        if(permissionState.status.isGranted){
            CameraX(modifier = Modifier.padding(it), cameraController = cameraController, lifecycle = lifecycle)
        } else{
            Text(text = "Permisos denegados", modifier = Modifier.padding(it))
        }
    }

}

private fun takePicture(
    cameraController: LifecycleCameraController,
    executor: Executor
) {
    val file = File.createTempFile("phototest", ".jpg")
    val outputDirectory = ImageCapture.OutputFileOptions.Builder(file).build()
    cameraController.takePicture(
        outputDirectory,
        executor,
        object: ImageCapture.OnImageSavedCallback{
            override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                Data.uri.value = outputFileResults.savedUri
                Log.d("Photo", outputFileResults.savedUri.toString())
            }

            override fun onError(exception: ImageCaptureException) {
                println("Error taking picture")
            }
        },
    )
}

@Composable
fun CameraX(
    modifier: Modifier = Modifier,
    cameraController: LifecycleCameraController,
    lifecycle: LifecycleOwner
){
    cameraController.bindToLifecycle(lifecycle)
    AndroidView(factory = { context ->
        val previewView = PreviewView(context).apply{
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, // fill max width
                ViewGroup.LayoutParams.MATCH_PARENT // fill max height
            )
        }
        previewView.controller = cameraController

        previewView
    })
}
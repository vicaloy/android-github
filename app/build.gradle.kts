import java.io.FileNotFoundException
import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.ksp)
}

fun getLocalProperty(propertyName: String, project: Project): String {
    val propertiesFile = project.rootProject.file("local.properties")
    if (propertiesFile.exists()) {
        val properties = Properties().apply {
            load(propertiesFile.inputStream())
        }
        return properties.getProperty(propertyName)
            ?: throw IllegalArgumentException("Property '$propertyName' not found in local.properties")
    } else {
        throw FileNotFoundException("local.properties file not found")
    }
}

android {
    namespace = "com.example.github"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.github"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildFeatures {
        buildConfig = true
    }

    buildTypes {
        debug {
            buildConfigField("String", "TOKEN", "\"${getLocalProperty("API_TOKEN", project)}\"")
            buildConfigField("String", "URL", "\"${getLocalProperty("BASE_URL", project)}\"")
        }
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField("String", "TOKEN", "\"${getLocalProperty("API_TOKEN", project)}\"")
            buildConfigField("String", "URL", "\"${getLocalProperty("BASE_URL", project)}\"")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.14"
    }
    buildFeatures {
        viewBinding = true
        compose = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.material)
    implementation(libs.androidx.compose.ui.tooling)
    implementation(libs.androidx.compose.runtime)
    implementation(libs.androidx.compose.foundation)
    implementation(libs.paging.runtime)
    implementation(libs.paging.compose)
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter)
    implementation(libs.moshi)
    implementation(libs.androidx.legacy.support.v4)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.fragment.ktx)
    implementation(libs.kotlinx.collections.immutable)
    ksp(libs.moshi.kotlin.codegen)
    implementation(libs.logging.interceptor)
    implementation(libs.kotlinx.coroutines)
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.coil.compose)
    implementation(libs.androidx.room.ktx)
    implementation(libs.androidx.room.runtime)
    ksp(libs.androidx.room.compiler)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}
version = LibraryAndroidCoordinates.LIBRARY_VERSION

plugins {
    id("com.android.library")
    kotlin("android")
    id("maven-publish")
}

android {
    compileSdkVersion(Sdk.COMPILE_SDK_VERSION)

    defaultConfig {
        minSdkVersion(Sdk.MIN_SDK_VERSION)
        targetSdkVersion(Sdk.TARGET_SDK_VERSION)

        versionCode = LibraryAndroidCoordinates.LIBRARY_VERSION_CODE
        versionName = LibraryAndroidCoordinates.LIBRARY_VERSION

        resConfigs("en")

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }

        getByName("debug") {
            isMinifyEnabled = false
            isShrinkResources = false
        }
    }

    lintOptions {
        isWarningsAsErrors = true
        isAbortOnError = true
        fatal("StopShip")
    }
}

dependencies {
    implementation(kotlin("stdlib"))

    implementation(KotlinLibs.KOTLINX_COROUTINES_ANDROID)

    implementation(AndroidxLibs.ANDROIDX_CORE_KTX)
    implementation(AndroidxLibs.ANDROIDX_APPCOMPAT)
    implementation(AndroidxLibs.ANDROIDX_CONSTRAINT_LAYOUT)
    implementation(AndroidxLibs.ANDROIDX_LIFECYCLE_KTX)
    implementation(AndroidxLibs.ANDROIDX_VIEW_MODEL_KTX)
    implementation(AndroidxLibs.ANDROIDX_ACTIVITY_KTX)
    implementation(AndroidxLibs.ANDROIDX_FRAGMENT_KTX)

    implementation(UiLibs.MATERIAL)

    debugImplementation(DebugLibs.LEAK_CANARY)

    testImplementation(TestLibs.JUNIT)
    androidTestImplementation(TestLibs.ANDROIDX_TEST_RULES)
    androidTestImplementation(TestLibs.ANDROIDX_TEST_RUNNER)
    androidTestImplementation(TestLibs.ANDROIDX_TEST_EXT_JUNIT)
    androidTestImplementation(TestLibs.ESPRESSO_CORE)
}

afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("release") {
                from(components["release"])
            }
        }
    }
}

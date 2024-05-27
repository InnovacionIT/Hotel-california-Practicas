// Top-level build file where you can add configuration options common to all sub-projects/modules.
import java.util.Properties

val localProperties = Properties()
val localPropertiesFile = rootProject.file("local.properties")
if (localPropertiesFile.exists()) {
    localPropertiesFile.inputStream().use { localProperties.load(it) }
}

localProperties.forEach { name, value ->
    project.extra[name.toString()] = value
}

plugins {
    id("com.android.application") version "8.1.1" apply false
}
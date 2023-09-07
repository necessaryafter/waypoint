plugins {
    id("java")
    id("java-library")
    id("com.diffplug.spotless") version ("6.11.0")
    id("com.github.johnrengelman.shadow") version ("7.1.1")
}

allprojects {
    apply(plugin = "java")
    apply(plugin = "java-library")
    apply(plugin = "com.diffplug.spotless")
    apply(plugin = "com.github.johnrengelman.shadow")

    repositories {
        mavenCentral()

        maven(url = "https://jitpack.io")
    }

    dependencies {
        compileOnlyApi("redis.clients:jedis:4.3.1")
        compileOnlyApi("org.projectlombok:lombok:1.18.24")
        annotationProcessor("org.projectlombok:lombok:1.18.24")
    }


    tasks.withType<JavaCompile> {
        options.encoding = "UTF-8"
    }

    java {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    spotless {
        java {
            removeUnusedImports()
            palantirJavaFormat()
            formatAnnotations()
            importOrder()
        }
    }

}
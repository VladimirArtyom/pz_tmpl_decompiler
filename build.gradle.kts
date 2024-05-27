import java.io.FileInputStream
import java.util.*

plugins {
    java
    id("io.pzstorm.capsid") version "0.4.2"
}

group = "io.xor"
version = "1.0.0"

repositories {
    mavenCentral()
}

tasks.register<GradleBuild>("setupWorkspace") {
    group = "zomboid"
    tasks = listOf("zomboidJar", "decompileZomboid", "annotateZomboid", "compileZomboid", "zomboidLuaJar")
}

// STEP 1: Run gradle setupWorkspace
// STEP 2: Remove below comments
// STEP 3: Code :D
/*
// Project-setup
val props = Properties()
props.load(FileInputStream(rootProject.file("local.properties")))

val zomboidJar=props.getProperty("zomboidJar")
val zomboidLua=props.getProperty("zomboidLua")
val zomboidJarSources=props.getProperty("zomboidJarSources")
val zomboidGameMedia=props.getProperty("zomboidGameMedia")


dependencies {
    implementation(files(zomboidJar))
    implementation(files(zomboidLua))
    implementation(files(zomboidJarSources))
    implementation(files(zomboidGameMedia))


    //sourceSets.clear()
   /* sourceSets.create("media"){
        java.srcDir("media")

        compileClasspath += sourceSets.main.get().compileClasspath
    }
    */
}


val projectName = if (project.hasProperty("betaBuild")) "${project.name}-beta" else project.name

val buildPath = "$buildDir/workshop/${projectName}"
val modPath = "$buildPath/Contents/mods/${projectName}"

val localPath = "${System.getProperties()["user.home"]}/Zomboid/Workshop"
val localModPath = "$localPath/${projectName}"

val buildWorkshop by tasks.registering {

    group = "build"
    outputs.dir("$buildDir/workshop")

    doLast {
        copy {
            from(if (project.hasProperty("betaBuild")) "workshop/preview_beta.png" else "workshop/preview.png",
                if (project.hasProperty("betaBuild")) "workshop/workshop_beta.txt" else "workshop/workshop.txt")
            into(buildPath)
            rename("preview_beta.png", "preview.png")
            rename("workshop_beta.txt", "workshop.txt")
        }

        copy {
            from(if (project.hasProperty("betaBuild")) "workshop/poster_beta.png" else "workshop/poster.png", "workshop/mod.info")
            into(modPath)
            rename("poster_beta.png", "poster.png")
        }
        copy {
            from("media")
            into("$modPath/media")
        }
    }
}

val localDeploy by tasks.registering {

    group = "build"
    outputs.dir("$localPath/${projectName}")

    dependsOn(buildWorkshop)

    doFirst {
        if (project.hasProperty("betaBuild")) {
            File("$modPath/mod.info").writeText(File("$modPath/mod.info").readText().replace("(id=.*)".toRegex(), "$1-beta"))
            File("$modPath/mod.info").writeText(File("$modPath/mod.info").readText().replaceFirst("(name=.*)".toRegex(), "$1 [Beta]"))
        }
    }

    doLast {
        copy {
            from(buildWorkshop.get().outputs.files)
            into(localPath)
        }
    }
}


val localUndeploy by tasks.registering {
    val localPath = "${System.getProperties()["user.home"]}/Zomboid/Workshop"

    group = "build"

    doLast {
        delete(localDeploy.get().outputs.files)
    }
}

 */